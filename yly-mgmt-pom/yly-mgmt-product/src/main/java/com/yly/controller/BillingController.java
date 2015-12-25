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
import com.yly.entity.Deposit;
import com.yly.entity.ElderlyInfo;
import com.yly.entity.MealCharge;
import com.yly.entity.PaymentRecord;
import com.yly.entity.SystemConfig;
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
import com.yly.service.SystemConfigService;
import com.yly.service.TenantAccountService;
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
            "elderlyInfo.bedLocation", "elderlyInfo.nursingLevel", "nurseAmount", "mealAmount",
            "depositAmount", "totalAmount", "bedAmount", "waterAmount", "electricityAmount",
            "personalizedAmount", "advanceChargeAmount", "payTime", "chargeStatus", "payStaff",
            "operator"};

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
  public @ResponseBody Message updateChekcinBill(Billing checkinBill, Long mealTypeId,Boolean isMonthlyMeal) {
    Billing billing = billingService.find(checkinBill.getId());
    if (PaymentStatus.UNPAID.equals(billing.getChargeStatus())) {//未缴费的入院账单可以多次修改
          billingService.updateUnpaidCheckInBill(billing, checkinBill);
      } else {//已缴费的入院账单修改
      if (billing.getBillingSupply() != null) {//在缴费后已经修改过一次的入院缴费账单不能再次修改
        return Message.error("yly.checkin.billEdit.unable");
      } else {//缴费后修改入院账单


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
    checkinBill.setPayTime(new Date());
    // 押金
    Deposit deposit = checkinBill.getDeposit();
    deposit.setBilling(checkinBill);
    deposit.setBillingNo(checkinBill.getBillingNo());
    deposit.setElderlyInfo(elderlyInfo);
    deposit.setChargeStatus(checkinBill.getChargeStatus());
    deposit.setInvoiceNo(checkinBill.getInvoiceNo());
    deposit.setPayTime(checkinBill.getPayTime());
    deposit.setPaymentType(checkinBill.getPaymentType());
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
    bedNurseCharge.setPayTime(checkinBill.getPayTime());
    bedNurseCharge.setPaymentType(checkinBill.getPaymentType());
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
      mealCharge.setPayTime(checkinBill.getPayTime());
      mealCharge.setPaymentType(checkinBill.getPaymentType());
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
      BigDecimal cardAmount, BigDecimal cashAmount, BigDecimal payTotalAmount) {
    Billing checkinBill = billingService.find(billingId);
    checkinBill.setPayStaff(tenantAccountService.getCurrentUsername());
    checkinBill.setPaymentType(paymentType);
    checkinBill.setRemark(remark);
    ElderlyInfo elderlyInfo = checkinBill.getElderlyInfo();
    elderlyInfo.setElderlyStatus(ElderlyStatus.IN_NURSING_HOME);

    // 账单缴费 (case1:未调整金额或修改订单; case2:缴费之前调整了金额或修改订单)
    if (checkinBill.getChargeStatus().equals(PaymentStatus.UNPAID)) {
      if (checkinBill.getDeposit() != null) {
        checkinBill.getDeposit().setChargeStatus(PaymentStatus.PAID);
      }
      if (checkinBill.getBedNurseCharge() != null) {
        checkinBill.getBedNurseCharge().setChargeStatus(PaymentStatus.PAID);
      }
      if (checkinBill.getMealCharge() != null) {
        checkinBill.getMealCharge().setChargeStatus(PaymentStatus.PAID);
      }


    }
    // 账单缴费(case3:缴费之后修改订单或调整金额)
    else if (checkinBill.getChargeStatus().equals(PaymentStatus.UNPAID_ADJUSTMENT)) {

    }

    // 如果账单存在调整金额，设置调整金额记录为已付款
    for (BillingAdjustment billingAdjustment : checkinBill.getBillingAdjustment()) {
      if (!billingAdjustment.getChargeStatus().equals(PaymentStatus.PAID)) {
        billingAdjustment.setChargeStatus(PaymentStatus.PAID);
      }
    }


    // 支付记录
    if (paymentType.equals(PaymentType.MIXTURE)) {// 混合支付 现金+卡
      PaymentRecord paymentRecordCard = new PaymentRecord();
      paymentRecordCard.setBilling(checkinBill);
      paymentRecordCard.setPaymentType(PaymentType.CARD);
      paymentRecordCard.setPayAmount(cardAmount);
      checkinBill.getPaymentRecords().add(paymentRecordCard);

      PaymentRecord paymentRecordCash = new PaymentRecord();
      paymentRecordCash.setBilling(checkinBill);
      paymentRecordCash.setPaymentType(PaymentType.CASH);
      paymentRecordCash.setPayAmount(cashAmount);
      checkinBill.getPaymentRecords().add(paymentRecordCash);
    } else {
      PaymentRecord paymentRecord = new PaymentRecord();
      paymentRecord.setBilling(checkinBill);
      paymentRecord.setPaymentType(checkinBill.getPaymentType());
      paymentRecord.setPayAmount(payTotalAmount);
      checkinBill.getPaymentRecords().add(paymentRecord);
    }

    checkinBill.setChargeStatus(PaymentStatus.PAID);
    if (LogUtil.isDebugEnabled(BillingController.class)) {
      LogUtil.debug(BillingController.class, "Check In Charge", "Bill Entity=%s",
          ToolsUtils.entityToString(checkinBill));
    }
    billingService.update(checkinBill);
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
    model.addAttribute("billing", record);
    return path + "/details";
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
    if (record.getBillingAdjustment() != null && record.getBillingAdjustment().size() > 0) {
      if (record.getChargeStatus().equals(PaymentStatus.UNPAID_ADJUSTMENT)) {
        BigDecimal paidAmount = record.getTotalAmount();
        BigDecimal payAmount = new BigDecimal(0);
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
        BigDecimal payAmount = record.getTotalAmount();
        for (BillingAdjustment billingAdjustment : record.getBillingAdjustment()) {
          if (billingAdjustment.getChargeStatus().equals(PaymentStatus.UNPAID)) {
            payAmount = payAmount.add(billingAdjustment.getAdjustmentAmount());
          }
        }
        record.setTotalAmount(payAmount);
      }
    }

    model.addAttribute("billing", record);
    return path + "/pay";
  }

}
