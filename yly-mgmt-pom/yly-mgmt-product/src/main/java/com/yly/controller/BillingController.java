package com.yly.controller;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yly.beans.Message;
import com.yly.common.log.LogUtil;
import com.yly.controller.base.BaseController;
import com.yly.entity.BedNurseCharge;
import com.yly.entity.Billing;
import com.yly.entity.BillingAdjustment;
import com.yly.entity.BillingSupplyment;
import com.yly.entity.Deposit;
import com.yly.entity.ElderlyInfo;
import com.yly.entity.MealCharge;
import com.yly.entity.PaymentRecord;
import com.yly.entity.SystemConfig;
import com.yly.entity.WaterElectricityCharge;
import com.yly.entity.WaterElectricityChargeConfig;
import com.yly.entity.commonenum.CommonEnum.BillingType;
import com.yly.entity.commonenum.CommonEnum.ConfigKey;
import com.yly.entity.commonenum.CommonEnum.ElderlyStatus;
import com.yly.entity.commonenum.CommonEnum.PaymentStatus;
import com.yly.entity.commonenum.CommonEnum.PaymentType;
import com.yly.framework.filter.Filter;
import com.yly.framework.filter.Filter.Operator;
import com.yly.framework.paging.Page;
import com.yly.framework.paging.Pageable;
import com.yly.json.request.ChargeSearchRequest;
import com.yly.service.BillingService;
import com.yly.service.ElderlyInfoService;
import com.yly.service.PersonalizedChargeService;
import com.yly.service.SystemConfigService;
import com.yly.service.TenantAccountService;
import com.yly.service.WaterElectricityChargeConfigService;
import com.yly.utils.FieldFilterUtils;
import com.yly.utils.ToolsUtils;

@Controller("billingController")
@RequestMapping("/console/billing")
public class BillingController extends BaseController {

  @Resource(name = "billingServiceImpl")
  private BillingService billingService;

  @Resource(name = "elderlyInfoServiceImpl")
  private ElderlyInfoService elderlyInfoService;

  @Resource(name = "systemConfigServiceImpl")
  private SystemConfigService systemConfigService;

  @Resource(name = "tenantAccountServiceImpl")
  private TenantAccountService tenantAccountService;

  @Resource(name = "personalizedChargeServiceImpl")
  private PersonalizedChargeService personalizedChargeService;

  @Resource(name = "waterElectricityChargeConfigServiceImpl")
  private WaterElectricityChargeConfigService waterElectricityChargeConfigService;


  /**
   * 入院缴费(交钱)
   * 
   * @param model
   * @return
   */
  @RequestMapping(value = "/checkinPay", method = RequestMethod.GET)
  public String checkinPay(ModelMap model) {
    return "/checkinPay/checkin";
  }

  /**
   * 日常缴费
   * 
   * @param model
   * @return
   */
  @RequestMapping(value = "/dailyBill", method = RequestMethod.GET)
  public String dailyBill(ModelMap model) {
    return "/dailyBill/bill";
  }

  /**
   * 入院收费（形成账单）
   * 
   * @param model
   * @return
   */
  @RequestMapping(value = "/checkinCharge", method = RequestMethod.GET)
  public String checkinCharge(ModelMap model) {
    return "/checkinCharge/checkin";
  }

  /**
   * 退住结算页面
   * 
   * @param model
   * @return
   */
  @RequestMapping(value = "/checkoutCharge", method = RequestMethod.GET)
  public String checkoutCharge(ModelMap model) {
    return "/checkoutCharge/checkout";
  }

  /**
   * 日常缴费页面
   * 
   * @param model
   * @return
   */
  @RequestMapping(value = "/billing", method = RequestMethod.GET)
  public String dailyCharge(ModelMap model) {
    return "/billing/billing";
  }

  /**
   * 列表数据
   * 
   * @param beginDate
   * @param endDate
   * @param pageable
   * @param model
   * @return
   */
  @RequestMapping(value = "/list", method = RequestMethod.POST)
  public @ResponseBody Page<Map<String, Object>> list(ChargeSearchRequest queryParam,
      Pageable pageable, ModelMap model) {
    Page<Billing> page = new Page<Billing>();
    if (queryParam.getRealName() == null && queryParam.getIdentifier() == null
        && queryParam.getBeginDate() == null && queryParam.getEndDate() == null
        && queryParam.getStatus() == null) {
      List<Filter> filters = new ArrayList<Filter>();
      Filter filter = new Filter("billType", Operator.eq, queryParam.getBillingType());
      filters.add(filter);
      pageable.setFilters(filters);
      page = billingService.findPage(pageable, true);
    } else {
      if (LogUtil.isDebugEnabled(BillingController.class)) {
        LogUtil
            .debug(
                BillingController.class,
                "Searching billing records with params",
                "elderlyName=%s,identifier=%s,chargeStatus=%s,billType=%s,beginDate=%s,endDate=%s,isCreateTime=%s",
                queryParam.getRealName(),
                queryParam.getIdentifier(),
                queryParam.getStatus() != null ? queryParam.getStatus() : null,
                queryParam.getBillingType() != null ? queryParam.getBillingType().toString() : null,
                queryParam.getBeginDate() != null ? queryParam.getBeginDate().toString() : null,
                queryParam.getEndDate() != null ? queryParam.getEndDate().toString() : null,
                queryParam.getIsCreateTime());
      }
      if (queryParam.getBillingType() != BillingType.DAILY) {
        queryParam.setIsPeriod(false);
      }

      page = billingService.chargeRecordSearch(queryParam, pageable);
    }


    String[] properties =
        {"id", "createDate", "elderlyInfo.name", "elderlyInfo.identifier",
            "elderlyInfo.bedLocation", "elderlyInfo.nursingLevel",
            "elderlyInfo.beHospitalizedDate", "elderlyInfo.outHospitalizedDate",
            "elderlyInfo.elderlyStatus", "nurseAmount", "mealAmount", "depositAmount",
            "totalAmount", "bedAmount", "waterAmount", "electricityAmount", "personalizedAmount",
            "advanceChargeAmount", "payTime", "chargeStatus", "payStaff", "operator"};

    List<Map<String, Object>> rows =
        FieldFilterUtils.filterCollectionMap(properties, page.getRows());

    Page<Map<String, Object>> filteredPage =
        new Page<Map<String, Object>>(rows, page.getTotal(), pageable);

    return filteredPage;
  }



  /**
   * 根据老人获取床位护理费配置
   * 
   * @param model
   * @param id
   * @return
   */
  @RequestMapping(value = "/getBedNurseConfig", method = RequestMethod.POST)
  public @ResponseBody List<Map<String, Object>> getBedNurseConfig(ModelMap model,
      Long elderlyInfoID) {
    ElderlyInfo elderlyInfo = elderlyInfoService.find(elderlyInfoID);
    if (!ElderlyStatus.IN_PROGRESS_CHECKIN.equals(elderlyInfo.getElderlyStatus())) {
      List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
      Map<String, Object> map = new HashMap<String, Object>();
      map.put("errMsg", Message.error("yly.checkin.elderlyStatus.invalid").getContent());
      list.add(map);
      return list;
    }
    String[] properties = {"chargeItem.configValue", "amountPerDay", "amountPerMonth"};

    return billingService.getBedNurseConfigByElderly(properties, elderlyInfo);
  }


  /**
   * 修改订单
   * 
   * @param checkinBill
   * @param mealTypeId
   * @param elderlyInfoID
   * @param isMonthlyMeal
   * @return
   */
  @RequestMapping(value = "/updateCheckinBill", method = RequestMethod.POST)
  public @ResponseBody Message updateChekcinBill(Billing checkinBill, Long mealTypeId,
      Boolean isMonthlyMeal) {
    Billing originBilling = billingService.find(checkinBill.getId());
    if (PaymentStatus.UNPAID.equals(originBilling.getChargeStatus())) {// 未缴费的入院账单可以多次修改
      billingService.updateUnpaidCheckInBill(originBilling, checkinBill);
    } else {// 已缴费的入院账单修改
      if (originBilling.getBillingSupply() != null) {// 在缴费后已经修改过一次的入院缴费账单不能再次修改
        return Message.error("yly.checkin.billEdit.unable");
      } else {// 缴费后修改入院账单
        Billing updateBilling =
            billingService.updatePaidCheckInBill(originBilling, checkinBill, mealTypeId,
                isMonthlyMeal);
        if (updateBilling == null) {
          return Message.error("yly.checkin.bill.unchanged");
        }
      }
    }

    return SUCCESS_MESSAGE;

  }

  /**
   * 入院缴费账单
   * 
   * @param checkinBill
   * @param mealTypeId
   * @param elderlyInfoID
   * @param isMonthlyMeal
   * @return
   */
  @RequestMapping(value = "/checkinBill", method = RequestMethod.POST)
  public @ResponseBody Message chekcinBill(Billing checkinBill, Long mealTypeId,
      Long elderlyInfoID, Boolean isMonthlyMeal) {
    ElderlyInfo elderlyInfo = elderlyInfoService.find(elderlyInfoID);
    if (!ElderlyStatus.IN_PROGRESS_CHECKIN.equals(elderlyInfo.getElderlyStatus())) {
      return Message.error("yly.checkin.elderlyStatus.invalid");
    }
    elderlyInfo.setElderlyStatus(ElderlyStatus.IN_PROGRESS_CHECKIN_BILL);
    checkinBill.setChargeStatus(PaymentStatus.UNPAID);
    checkinBill.setBillType(BillingType.CHECK_IN);
    checkinBill.setElderlyInfo(elderlyInfo);
    checkinBill.setBillingNo(ToolsUtils.generateBillNo(tenantAccountService
        .getCurrentTenantOrgCode()));
    checkinBill.setOperator(tenantAccountService.getCurrentUsername());
    checkinBill.setTenantID(tenantAccountService.getCurrentTenantID());
    // checkinBill.setPayTime(new Date());
    // 押金
    Deposit deposit = checkinBill.getDeposit();
    deposit.setBilling(checkinBill);
    deposit.setBillingNo(checkinBill.getBillingNo());
    deposit.setElderlyInfo(elderlyInfo);
    deposit.setChargeStatus(checkinBill.getChargeStatus());
    deposit.setInvoiceNo(checkinBill.getInvoiceNo());
    // deposit.setPayTime(checkinBill.getPayTime());
    // deposit.setPaymentType(checkinBill.getPaymentType());
    deposit.setOperator(checkinBill.getOperator());
    deposit.setTenantID(checkinBill.getTenantID());
    checkinBill.setDepositAmount(deposit.getDepositAmount());

    // 床位护理费
    BedNurseCharge bedNurseCharge = checkinBill.getBedNurseCharge();
    bedNurseCharge.setBilling(checkinBill);
    bedNurseCharge.setBillingNo(checkinBill.getBillingNo());
    bedNurseCharge.setElderlyInfo(elderlyInfo);
    bedNurseCharge.setChargeStatus(checkinBill.getChargeStatus());
    bedNurseCharge.setInvoiceNo(checkinBill.getInvoiceNo());
    // bedNurseCharge.setPayTime(checkinBill.getPayTime());
    // bedNurseCharge.setPaymentType(checkinBill.getPaymentType());
    bedNurseCharge.setOperator(checkinBill.getOperator());
    bedNurseCharge.setTenantID(checkinBill.getTenantID());
    checkinBill.setBedAmount(bedNurseCharge.getBedAmount());
    checkinBill.setNurseAmount(bedNurseCharge.getNurseAmount());

    // 伙食费
    if (isMonthlyMeal != null) {
      SystemConfig mealType = systemConfigService.find(mealTypeId);
      elderlyInfo.setMealFeeMonthlyPayment(true);
      elderlyInfo.setMealType(mealType);

      MealCharge mealCharge = checkinBill.getMealCharge();
      mealCharge.setBilling(checkinBill);
      mealCharge.setBillingNo(checkinBill.getBillingNo());
      mealCharge.setElderlyInfo(elderlyInfo);
      mealCharge.setChargeStatus(checkinBill.getChargeStatus());
      mealCharge.setInvoiceNo(checkinBill.getInvoiceNo());
      // mealCharge.setPayTime(checkinBill.getPayTime());
      // mealCharge.setPaymentType(checkinBill.getPaymentType());
      mealCharge.setOperator(checkinBill.getOperator());
      mealCharge.setTenantID(checkinBill.getTenantID());
      checkinBill.setMealAmount(mealCharge.getMealAmount());

    }

    if (LogUtil.isDebugEnabled(BillingController.class)) {
      LogUtil.debug(BillingController.class, "Check In Charge", "Bill Entity=%s",
          ToolsUtils.entityToString(checkinBill));
    }
    billingService.save(checkinBill);
    return SUCCESS_MESSAGE;
  }


  /**
   * 账单支付
   * 
   * @param checkinBill
   * @param elderlyInfoID
   * @param cardAmount
   * @param cashAmount
   * @return
   */
  @RequestMapping(value = "/billPay", method = RequestMethod.POST)
  public @ResponseBody Message billPay(Long billingId, String remark, PaymentType paymentType,
      BigDecimal cardAmount, BigDecimal cashAmount, BigDecimal payTotalAmount,
      String waterElectricity_remark, BigDecimal waterCount, BigDecimal waterAmount,
      BigDecimal electricityCount, BigDecimal electricityAmount) {
    Billing payBill = billingService.find(billingId);
    payBill.setPayStaff(tenantAccountService.getCurrentUsername());
    payBill.setPaymentType(paymentType);
    payBill.setRemark(remark);
    payBill.setPayTime(new Date());
    ElderlyInfo elderlyInfo = payBill.getElderlyInfo();
    elderlyInfo.setElderlyStatus(ElderlyStatus.IN_NURSING_HOME);

    // 账单缴费 (case1:未调整金额或修改订单; case2:缴费之前调整了金额或修改订单)
    if (payBill.getChargeStatus().equals(PaymentStatus.UNPAID)) {
      if (payBill.getDeposit() != null) {
        payBill.getDeposit().setChargeStatus(PaymentStatus.PAID);
        payBill.getDeposit().setPayTime(payBill.getPayTime());
        payBill.getDeposit().setPaymentType(payBill.getPaymentType());
      }
      if (payBill.getBedNurseCharge() != null) {
        payBill.getBedNurseCharge().setChargeStatus(PaymentStatus.PAID);
        payBill.getBedNurseCharge().setPayTime(payBill.getPayTime());
        payBill.getBedNurseCharge().setPaymentType(payBill.getPaymentType());
      }
      if (payBill.getMealCharge() != null) {
        payBill.getMealCharge().setChargeStatus(PaymentStatus.PAID);
        payBill.getMealCharge().setPayTime(payBill.getPayTime());
        payBill.getMealCharge().setPaymentType(payBill.getPaymentType());
      }
      if (payBill.getPersonalizedCharge() != null) {
        payBill.getPersonalizedCharge().setChargeStatus(PaymentStatus.PAID);
        payBill.getPersonalizedCharge().setPayTime(payBill.getPayTime());
        payBill.getPersonalizedCharge().setPaymentType(payBill.getPaymentType());
      }
      if (waterAmount != null && electricityAmount != null) {
        WaterElectricityCharge waterElectricityCharge = new WaterElectricityCharge();
        waterElectricityCharge.setBilling(payBill);
        waterElectricityCharge.setBillingNo(payBill.getBillingNo());
        waterElectricityCharge.setElderlyInfo(elderlyInfo);
        waterElectricityCharge.setTenantID(payBill.getTenantID());
        waterElectricityCharge.setPeriodStartDate(payBill.getPeriodStartDate());
        waterElectricityCharge.setPeriodEndDate(payBill.getPeriodEndDate());
        waterElectricityCharge.setChargeStatus(PaymentStatus.PAID);
        waterElectricityCharge.setPayTime(payBill.getPayTime());
        waterElectricityCharge.setPaymentType(payBill.getPaymentType());
        waterElectricityCharge.setElectricityCount(electricityCount);
        waterElectricityCharge.setWaterCount(waterCount);
        waterElectricityCharge.setElectricityAmount(electricityAmount);
        waterElectricityCharge.setWaterAmount(waterAmount);
        payBill.setWaterElectricityCharge(waterElectricityCharge);
      }


    }
    // 账单缴费(case3:缴费之后修改订单或调整金额)--用于入院账单
    else if (payBill.getChargeStatus().equals(PaymentStatus.UNPAID_ADJUSTMENT)) {

    }

    // 如果账单存在调整金额，设置调整金额记录为已付款
    for (BillingAdjustment billingAdjustment : payBill.getBillingAdjustment()) {
      if (!billingAdjustment.getChargeStatus().equals(PaymentStatus.PAID)) {
        billingAdjustment.setChargeStatus(PaymentStatus.PAID);
      }
    }


    // 支付记录
    if (paymentType.equals(PaymentType.MIXTURE)) {// 混合支付 现金+卡
      PaymentRecord paymentRecordCard = new PaymentRecord();
      paymentRecordCard.setBilling(payBill);
      paymentRecordCard.setPaymentType(PaymentType.CARD);
      paymentRecordCard.setPayAmount(cardAmount);
      payBill.getPaymentRecords().add(paymentRecordCard);

      PaymentRecord paymentRecordCash = new PaymentRecord();
      paymentRecordCash.setBilling(payBill);
      paymentRecordCash.setPaymentType(PaymentType.CASH);
      paymentRecordCash.setPayAmount(cashAmount);
      payBill.getPaymentRecords().add(paymentRecordCash);
    } else {
      PaymentRecord paymentRecord = new PaymentRecord();
      paymentRecord.setBilling(payBill);
      paymentRecord.setPaymentType(payBill.getPaymentType());
      paymentRecord.setPayAmount(payTotalAmount);
      payBill.getPaymentRecords().add(paymentRecord);
      if (paymentType.equals(PaymentType.ADVANCE)) {

      }
    }

    payBill.setChargeStatus(PaymentStatus.PAID);
    if (LogUtil.isDebugEnabled(BillingController.class)) {
      LogUtil.debug(BillingController.class, "Check In Charge", "Bill Entity=%s",
          ToolsUtils.entityToString(payBill));
    }
    billingService.update(payBill);
    return SUCCESS_MESSAGE;
  }

  /**
   * 编辑页面
   * 
   * @param model
   * @param id
   * @return
   */
  @RequestMapping(value = "/edit", method = RequestMethod.GET)
  public String edit(ModelMap model, Long id) {
    Billing record = billingService.find(id);
    model.addAttribute("billing", record);
    List<Map<String, Object>> systemConfigs =
        systemConfigService.findByConfigKey(ConfigKey.BILLDAY, null);
    if (systemConfigs != null && systemConfigs.size() > 0) {
      model.addAttribute("billDay", systemConfigs.get(0).get("configValue"));
    }
    if (record.getChargeStatus().equals(PaymentStatus.PAID)) {

    }
    String[] properties = {"chargeItem.configValue", "amountPerDay", "amountPerMonth"};
    model.addAttribute("bedNurseConfig",
        billingService.getBedNurseConfigByElderly(properties, record.getElderlyInfo()));
    if (record.getMealCharge() != null) {
      Map<String, Object> map =
          systemConfigService.getBillingDate(record.getMealCharge().getPeriodStartDate());
      model.addAttribute("billDateMap", map);
    }

    return "/checkinCharge/edit";
  }

  /**
   * 获取数据进入详情页面
   * 
   * @param model
   * @param id
   * @return
   */
  @RequestMapping(value = "/details", method = RequestMethod.GET)
  public String details(ModelMap model, Long id, String path) {
    Billing record = billingService.find(id);
    if (record.getBillingSupply() != null) {
      BillingSupplyment billingSupplyment = record.getBillingSupply();
      if (billingSupplyment.getBedNurseCharge() != null) {
        BedNurseCharge bedNurseCharge = billingSupplyment.getBedNurseCharge();
        record.setBedAmount(bedNurseCharge.getBedAmount());
        record.setNurseAmount(bedNurseCharge.getNurseAmount());
      }

      if (billingSupplyment.getDeposit() != null) {
        Deposit deposit = billingSupplyment.getDeposit();
        record.setDepositAmount(deposit.getDepositAmount());
      }

      if (billingSupplyment.getMealCharge() != null) {
        MealCharge mealCharge = billingSupplyment.getMealCharge();
        record.setMealAmount(mealCharge.getMealAmount());
      }

      record.setTotalAmount(billingSupplyment.getTotalAmount());
    }

    model.addAttribute("billing", record);
    if ("dailyBill".equals(path)) {
      model.addAttribute("serviceDetails",
          personalizedChargeService.getServiceDetailsByBill(record.getPersonalizedCharge()));
    }
    return path + "/details";
  }

  /**
   * 入院账单是否已经在缴费后修改过一次，目前缴费后只允许修改一次
   * 
   * @param model
   * @param id
   * @param path
   * @return
   */
  @RequestMapping(value = "/isChargeinBillUpdated", method = RequestMethod.GET)
  public @ResponseBody Boolean isChargeinBillUpdated(ModelMap model, Long id) {
    Billing record = billingService.find(id);
    if (record.getBillingSupply() != null) {
      return true;
    }
    return false;
  }


  /**
   * 支付页面详情
   * 
   * @param model
   * @param id
   * @return
   */
  @RequestMapping(value = "/payPage", method = RequestMethod.GET)
  public String payPage(ModelMap model, Long id, String path) {
    Billing record = billingService.find(id);
    BigDecimal paidAmount = record.getTotalAmount();
    BigDecimal payAmount = new BigDecimal(0);

    if (record.getBillingSupply() != null) {
      BillingSupplyment billingSupplyment = record.getBillingSupply();
      if (billingSupplyment.getBedNurseCharge() != null) {
        BedNurseCharge bedNurseCharge = billingSupplyment.getBedNurseCharge();
        record.setBedAmount(bedNurseCharge.getBedAmount());
        record.setNurseAmount(bedNurseCharge.getNurseAmount());
        model.addAttribute("isBedNurseSupp", true);
      }

      if (billingSupplyment.getDeposit() != null) {
        Deposit deposit = billingSupplyment.getDeposit();
        record.setDepositAmount(deposit.getDepositAmount());
        model.addAttribute("isDepositSupp", true);
      }

      if (billingSupplyment.getMealCharge() != null) {
        MealCharge mealCharge = billingSupplyment.getMealCharge();
        record.setMealAmount(mealCharge.getMealAmount());
        model.addAttribute("isMealSupp", true);
      }

      BigDecimal diffAmount = billingSupplyment.getTotalAmount().subtract(record.getTotalAmount());
      payAmount = payAmount.add(diffAmount);

    }
    if (record.getBillingAdjustment() != null && record.getBillingAdjustment().size() > 0) {
      if (record.getChargeStatus().equals(PaymentStatus.UNPAID_ADJUSTMENT)) {
        for (BillingAdjustment billingAdjustment : record.getBillingAdjustment()) {
          if (billingAdjustment.getChargeStatus().equals(PaymentStatus.PAID)) {
            paidAmount = paidAmount.add(billingAdjustment.getAdjustmentAmount());
          } else {
            payAmount = payAmount.add(billingAdjustment.getAdjustmentAmount());
          }
        }
        model.addAttribute("paidAmount", paidAmount);
        record.setTotalAmount(payAmount);
      } else if (record.getChargeStatus().equals(PaymentStatus.UNPAID)) {
        BigDecimal payUnpaidAmount = record.getTotalAmount();
        for (BillingAdjustment billingAdjustment : record.getBillingAdjustment()) {
          if (billingAdjustment.getChargeStatus().equals(PaymentStatus.UNPAID)) {
            payUnpaidAmount = payUnpaidAmount.add(billingAdjustment.getAdjustmentAmount());
          }
        }
        record.setTotalAmount(payUnpaidAmount);
      }
    }

    model.addAttribute("billing", record);
    if ("dailyBill".equals(path)) {
      BigDecimal curAmount =
          record.getBedAmount().add(record.getNurseAmount()).add(record.getMealAmount())
              .add(record.getPersonalizedAmount());
      model.addAttribute("currentAmount", curAmount);
      model.addAttribute("serviceDetails",
          personalizedChargeService.getServiceDetailsByBill(record.getPersonalizedCharge()));
      List<WaterElectricityChargeConfig> configs =
          waterElectricityChargeConfigService.findAll(true);
      model.addAttribute("waterElectricityConfig", configs.get(0));
    }
    return path + "/pay";
  }

}
