package com.yly.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yly.beans.Message;
import com.yly.common.log.LogUtil;
import com.yly.controller.base.BaseController;
import com.yly.entity.AdvanceCharge;
import com.yly.entity.BedChargeConfig;
import com.yly.entity.BedNurseCharge;
import com.yly.entity.Billing;
import com.yly.entity.BillingAdjustment;
import com.yly.entity.ElderlyInfo;
import com.yly.entity.MealCharge;
import com.yly.entity.MealChargeConfig;
import com.yly.entity.NurseChargeConfig;
import com.yly.entity.PaymentRecord;
import com.yly.entity.PersonalizedCharge;
import com.yly.entity.PersonalizedRecord;
import com.yly.entity.SystemConfig;
import com.yly.entity.WaterElectricityCharge;
import com.yly.entity.WaterElectricityChargeConfig;
import com.yly.entity.commonenum.CommonEnum.BillingType;
import com.yly.entity.commonenum.CommonEnum.BudgetType;
import com.yly.entity.commonenum.CommonEnum.DeleteStatus;
import com.yly.entity.commonenum.CommonEnum.ElderlyStatus;
import com.yly.entity.commonenum.CommonEnum.PaymentStatus;
import com.yly.entity.commonenum.CommonEnum.PaymentType;
import com.yly.framework.filter.Filter;
import com.yly.framework.filter.Filter.Operator;
import com.yly.json.request.ChargeSearchRequest;
import com.yly.service.AdvanceChargeService;
import com.yly.service.BillingService;
import com.yly.service.CheckoutService;
import com.yly.service.ElderlyInfoService;
import com.yly.service.FileService;
import com.yly.service.PersonalizedRecordService;
import com.yly.service.SystemConfigService;
import com.yly.service.TenantAccountService;
import com.yly.service.WaterElectricityChargeConfigService;
import com.yly.utils.FieldFilterUtils;
import com.yly.utils.SpringUtils;
import com.yly.utils.ToolsUtils;

/**
 * 办理出院以及退住结算
 * 
 * @author luzhang
 *
 */
@Controller("checkoutController")
@RequestMapping("console/checkout")
public class CheckoutController extends BaseController {

  @Resource(name = "elderlyInfoServiceImpl")
  private ElderlyInfoService elderlyInfoService;

  @Resource(name = "tenantAccountServiceImpl")
  private TenantAccountService tenantAccountService;

  @Resource(name = "systemConfigServiceImpl")
  private SystemConfigService systemConfigService;

  @Resource(name = "fileServiceImpl")
  private FileService fileService;
  
  @Resource(name = "billingServiceImpl")
  private BillingService billingService;
  
  @Resource(name = "checkoutServiceImpl")
  private CheckoutService checkoutService;
  @Resource(name = "waterElectricityChargeConfigServiceImpl")
  private WaterElectricityChargeConfigService waterElecChargeConfigService;
  

  /**
   * 办理出院页面
   * 
   * @param model
   * @return
   */
  @RequestMapping(value = "/checkoutCharge", method = RequestMethod.GET)
  public String checkoutCharge(ModelMap model) {
    return "/checkoutCharge/checkoutCharge";
  }
  /**
   * 退住结算页面
   * 
   * @param model
   * @return
   */
  @RequestMapping(value = "/checkoutPay", method = RequestMethod.GET)
  public String checkoutPay(ModelMap model) {
    return "/checkoutPay/checkoutPay";
  }

  /**
   * 添加
   * 
   * @param elderlyInfo
   * @return
   */
  @RequestMapping(value = "/add", method = RequestMethod.POST)
  public @ResponseBody Message add(Long personnelCategoryId, Long nursingLevelId,
      Long evaluatingResultId, ElderlyInfo elderlyInfo) {

    SystemConfig personnelCategory = null;
    SystemConfig nursingLevel = null;
    SystemConfig evaluatingResult = null;

    if (personnelCategoryId != null) {
      personnelCategory = systemConfigService.find(personnelCategoryId);
    }

    if (nursingLevelId != null) {
      nursingLevel = systemConfigService.find(nursingLevelId);
    }

    if (evaluatingResultId != null) {
      evaluatingResult = systemConfigService.find(evaluatingResultId);
    }

    if (elderlyInfo != null) {
      Long currnetTenantId = tenantAccountService.getCurrentTenantID();
      /**
       * 设置租户
       */
      elderlyInfo.setTenantID(currnetTenantId);
      elderlyInfo.setPersonnelCategory(personnelCategory);
      elderlyInfo.setNursingLevel(nursingLevel);
      elderlyInfo.setEvaluatingResult(evaluatingResult);
      elderlyInfo.setDeleteStatus(DeleteStatus.NOT_DELETED);

      elderlyInfo.getElderlyConsigner().setTenantID(currnetTenantId);
      elderlyInfo.getElderlyConsigner().setElderlyInfo(elderlyInfo);

      /**
       * 设置老人状态
       */
      elderlyInfo.setElderlyStatus(ElderlyStatus.IN_PROGRESS_CHECKIN);

      elderlyInfoService.save(elderlyInfo);
    }

    return SUCCESS_MESSAGE;
  }


  /**
   * 获取数据进入编辑页面
   * 
   * @param model
   * @param id
   * @return
   */
  @RequestMapping(value = "/edit", method = RequestMethod.GET)
  public String edit(ModelMap model, Long id) {
    ElderlyInfo elderlyInfo = elderlyInfoService.find(id);
    model.addAttribute("elderlyInfo", elderlyInfo);
    return "checkoutCharge/edit";
  }
  
/*  *//**
   * 获取数据进入详情页面
   * 
   * @param model
   * @param id
   * @return
   *//*
  @RequestMapping(value = "/details", method = RequestMethod.GET)
  public String details(ModelMap model, Long id, String path) {
    Billing record = billingService.find(id);
    model.addAttribute("billing", record);
    return path + "/details";
  }*/
  
  /**
   * 获取billing数据详情
   * 
   * @param id
   * @return
   */
  @RequestMapping(value = "/details", method = RequestMethod.GET)
  public @ResponseBody Billing details(ModelMap model, Long id) {
    Billing billing = billingService.find(id);
    if (billing != null) {
      return billing;
    }else {
      return new Billing();
    }
  }

  /**
   * 更新
   * 
   * @param elderlyInfo
   * @return
   */
  @RequestMapping(value = "/update", method = RequestMethod.POST)
  public @ResponseBody Message update(Long personnelCategoryEditId, Long nursingLevelEditId,
      Long evaluatingResultEditId, ElderlyInfo elderlyInfo) {

    SystemConfig personnelCategory = null;
    SystemConfig nursingLevel = null;
    SystemConfig evaluatingResult = null;

    if (personnelCategoryEditId != null) {
      personnelCategory = systemConfigService.find(personnelCategoryEditId);
    }

    if (nursingLevelEditId != null) {
      nursingLevel = systemConfigService.find(nursingLevelEditId);
    }

    if (evaluatingResultEditId != null) {
      evaluatingResult = systemConfigService.find(evaluatingResultEditId);
    }
    /**
     * 设置租户
     */
    Long currnetTenantId = tenantAccountService.getCurrentTenantID();

    elderlyInfo.setTenantID(currnetTenantId);
    elderlyInfo.setDeleteStatus(DeleteStatus.NOT_DELETED);
    elderlyInfo.getElderlyConsigner().setTenantID(currnetTenantId);
    elderlyInfo.getElderlyConsigner().setElderlyInfo(elderlyInfo);

    elderlyInfo.setPersonnelCategory(personnelCategory);
    elderlyInfo.setNursingLevel(nursingLevel);
    elderlyInfo.setEvaluatingResult(evaluatingResult);
    /**
     * 更新入院老人数据的时候老人状态始终为入院办理
     */
    elderlyInfo.setElderlyStatus(ElderlyStatus.IN_PROGRESS_CHECKIN);

    elderlyInfoService.update(elderlyInfo,"profilePhoto");
    return SUCCESS_MESSAGE;
  }

  /**
   * 逻辑删除
   */
  @RequestMapping(value = "/delete", method = RequestMethod.POST)
  public @ResponseBody Message delete(Long[] ids) {
    if (ids != null) {
      List<ElderlyInfo> elderlyInfoList = elderlyInfoService.findList(ids);

      for (ElderlyInfo elderlyInfo : elderlyInfoList) {
        elderlyInfo.setDeleteStatus(DeleteStatus.DELETED);
        elderlyInfoService.update(elderlyInfo);
      }
    }
    return SUCCESS_MESSAGE;
  }
  /**
   * 账单信息搜索页面
   * 
   * @param model
   * @return
   */
  @RequestMapping(value = "/billingSearch", method = RequestMethod.GET)
  public String billingSearch(ModelMap model) {
    return "/checkoutCharge/billingSearch";
  }
  /**
   * 办理出院账单
   * 
   * @param checkinBill
   * @param elderlyInfoID
   * @return
   */
  @RequestMapping(value = "/checkoutBill", method = RequestMethod.POST)
  public @ResponseBody Message chekcoutBill(Billing checkoutBill, ChargeSearchRequest chargeSearch) {
    ElderlyInfo elderlyInfo = elderlyInfoService.find(chargeSearch.getElderlyId());
    if (ElderlyStatus.OUT_NURSING_HOME.equals(elderlyInfo.getElderlyStatus()) || 
        ElderlyStatus.IN_PROGRESS_CHECKOUT.equals(elderlyInfo.getElderlyStatus())) {
      return Message.error("yly.checkout.elderlyStatus.invalid", elderlyInfo.getName());
    }
    elderlyInfo.setElderlyStatus(ElderlyStatus.IN_PROGRESS_CHECKOUT);
    if (chargeSearch.getCheckoutDate() == null) {
      chargeSearch.setCheckoutDate(new Date());
    }
    elderlyInfo.setOutHospitalizedDate(chargeSearch.getCheckoutDate());
    checkoutBill.setChargeStatus(PaymentStatus.UNPAID);
    checkoutBill.setBillType(BillingType.CHECK_OUT);
    checkoutBill.setElderlyInfo(elderlyInfo);
    checkoutBill.setBillingNo(ToolsUtils.generateBillNo(tenantAccountService.getCurrentTenantOrgCode()));
    checkoutBill.setOperator(tenantAccountService.getCurrentUsername());
    checkoutBill.setTenantID(tenantAccountService.getCurrentTenantID());
    //checkoutBill.setPayTime(new Date());

    // 床位护理费
    BedNurseCharge bedNurseCharge = checkoutBill.getBedNurseCharge();   
    bedNurseCharge.setBilling(checkoutBill);
    bedNurseCharge.setBillingNo(checkoutBill.getBillingNo());
    bedNurseCharge.setElderlyInfo(elderlyInfo);
    bedNurseCharge.setChargeStatus(checkoutBill.getChargeStatus());
    bedNurseCharge.setInvoiceNo(checkoutBill.getInvoiceNo());
    bedNurseCharge.setPayTime(checkoutBill.getPayTime());
    bedNurseCharge.setPaymentType(checkoutBill.getPaymentType());
    bedNurseCharge.setOperator(checkoutBill.getOperator());
    bedNurseCharge.setTenantID(checkoutBill.getTenantID());
    checkoutBill.setBedAmount(bedNurseCharge.getBedAmount());
    checkoutBill.setNurseAmount(bedNurseCharge.getNurseAmount());

    // 伙食费
    MealCharge mealCharge = checkoutBill.getMealCharge();
    mealCharge.setBilling(checkoutBill);
    mealCharge.setBillingNo(checkoutBill.getBillingNo());
    mealCharge.setElderlyInfo(elderlyInfo);
    mealCharge.setChargeStatus(checkoutBill.getChargeStatus());
    mealCharge.setInvoiceNo(checkoutBill.getInvoiceNo());
    mealCharge.setPayTime(checkoutBill.getPayTime());
    mealCharge.setPaymentType(checkoutBill.getPaymentType());
    mealCharge.setOperator(checkoutBill.getOperator());
    mealCharge.setTenantID(checkoutBill.getTenantID());
    checkoutBill.setMealAmount(mealCharge.getMealAmount());
    
    //水电费
    WaterElectricityCharge waterElectricityCharge = checkoutBill.getWaterElectricityCharge();
    waterElectricityCharge.setBilling(checkoutBill);
    waterElectricityCharge.setBillingNo(checkoutBill.getBillingNo());
    waterElectricityCharge.setElderlyInfo(elderlyInfo);
    waterElectricityCharge.setChargeStatus(checkoutBill.getChargeStatus());
    waterElectricityCharge.setInvoiceNo(checkoutBill.getInvoiceNo());
    waterElectricityCharge.setPayTime(checkoutBill.getPayTime());
    waterElectricityCharge.setPaymentType(checkoutBill.getPaymentType());
    waterElectricityCharge.setOperator(checkoutBill.getOperator());
    waterElectricityCharge.setTenantID(checkoutBill.getTenantID());   
    checkoutBill.setWaterAmount(waterElectricityCharge.getWaterAmount());
    checkoutBill.setElectricityAmount(waterElectricityCharge.getElectricityAmount());
    
    //个性化服务费
    PersonalizedCharge personalizedCharge = checkoutBill.getPersonalizedCharge();
    personalizedCharge.setBilling(checkoutBill);
    personalizedCharge.setBillingNo(checkoutBill.getBillingNo());
    personalizedCharge.setElderlyInfo(elderlyInfo);
    personalizedCharge.setChargeStatus(checkoutBill.getChargeStatus());
    personalizedCharge.setInvoiceNo(checkoutBill.getInvoiceNo());
    personalizedCharge.setPayTime(checkoutBill.getPayTime());
    personalizedCharge.setPaymentType(checkoutBill.getPaymentType());
    personalizedCharge.setOperator(checkoutBill.getOperator());
    personalizedCharge.setTenantID(checkoutBill.getTenantID());
    checkoutBill.setPersonalizedAmount(personalizedCharge.getPersonalizedAmount());
    
    if (LogUtil.isDebugEnabled(BillingController.class)) {
      LogUtil.debug(BillingController.class, "Check Out Charge", "Bill Entity=%s",ToolsUtils.entityToString(checkoutBill));
    }
    if(chargeSearch.getIsAdvanceCharge() != null && chargeSearch.getIsAdvanceCharge()){//使用预存款付费
      checkoutBill.setAdvanceChargeAmount(elderlyInfo.getAdvanceChargeAmount());
    }
    
    checkoutService.checkoutBillTransaction(chargeSearch, checkoutBill);
    
    return SUCCESS_MESSAGE;
  }
  /**
   * 退住结算账单支付
   * 
   * @param checkinBill
   * @param elderlyInfoID
   * @param cardAmount
   * @param cashAmount
   * @return
   */
  @RequestMapping(value = "/checkoutBillPay", method = RequestMethod.POST)
  public @ResponseBody Message checkoutBillPay(Long checkoutBillingId, String remark, PaymentType paymentType,
      BigDecimal cardAmount, BigDecimal cashAmount, BigDecimal payTotalAmount) {
    Billing checkoutBill = billingService.find(checkoutBillingId);
    checkoutBill.setPayStaff(tenantAccountService.getCurrentUsername());//收款人
    checkoutBill.setPaymentType(paymentType);
    checkoutBill.setRemark(remark);
    checkoutBill.setPayTime(new Date());
    ElderlyInfo elderlyInfo = checkoutBill.getElderlyInfo();
    elderlyInfo.setElderlyStatus(ElderlyStatus.OUT_NURSING_HOME);   

    //重置退住结算各个子账单，把“未缴费”改成“已缴费”
    if (checkoutBill.getChargeStatus().equals(PaymentStatus.UNPAID)) {
      if (checkoutBill.getDeposit() != null) {
        checkoutBill.getDeposit().setRemark(
            checkoutBill.getDeposit().getRemark().replaceAll(
                SpringUtils.getMessage("yly.charge.unpaid.label"), SpringUtils.getMessage("yly.charge.paid.label")));
      }
      if (checkoutBill.getBedNurseCharge() != null) {
        checkoutBill.getBedNurseCharge().setChargeStatus(PaymentStatus.PAID);
        checkoutBill.getBedNurseCharge().setPayTime(checkoutBill.getPayTime());
        checkoutBill.getBedNurseCharge().setPaymentType(checkoutBill.getPaymentType());
        checkoutBill.getBedNurseCharge().setRemark(
            checkoutBill.getBedNurseCharge().getRemark().replaceAll(
                SpringUtils.getMessage("yly.charge.unpaid.label"), SpringUtils.getMessage("yly.charge.paid.label")));
      }
      if (checkoutBill.getMealCharge() != null) {
        checkoutBill.getMealCharge().setChargeStatus(PaymentStatus.PAID);
        checkoutBill.getMealCharge().setPayTime(checkoutBill.getPayTime());
        checkoutBill.getMealCharge().setPaymentType(checkoutBill.getPaymentType());
        checkoutBill.getMealCharge().setRemark(
            checkoutBill.getMealCharge().getRemark().replaceAll(
                SpringUtils.getMessage("yly.charge.unpaid.label"), SpringUtils.getMessage("yly.charge.paid.label")));
      }
      if (checkoutBill.getWaterElectricityCharge() != null) {
        checkoutBill.getWaterElectricityCharge().setChargeStatus(PaymentStatus.PAID);
        checkoutBill.getWaterElectricityCharge().setPayTime(checkoutBill.getPayTime());
        checkoutBill.getWaterElectricityCharge().setPaymentType(checkoutBill.getPaymentType());
      }
      if (checkoutBill.getPersonalizedCharge()!= null) {
        checkoutBill.getPersonalizedCharge().setChargeStatus(PaymentStatus.PAID);
        checkoutBill.getPersonalizedCharge().setPayTime(checkoutBill.getPayTime());
        checkoutBill.getPersonalizedCharge().setPaymentType(checkoutBill.getPaymentType());
        checkoutBill.getPersonalizedCharge().setRemark(
            checkoutBill.getPersonalizedCharge().getRemark().replaceAll(
                SpringUtils.getMessage("yly.charge.unpaid.label"), SpringUtils.getMessage("yly.charge.paid.label")));
      }

    }
    // 账单缴费(case3:缴费之后修改订单或调整金额)
    else if (checkoutBill.getChargeStatus().equals(PaymentStatus.UNPAID_ADJUSTMENT)) {

    }

    // 如果账单存在调整金额，设置调整金额记录为已付款
    for (BillingAdjustment billingAdjustment : checkoutBill.getBillingAdjustment()) {
      if (!billingAdjustment.getChargeStatus().equals(PaymentStatus.PAID)) {
        billingAdjustment.setChargeStatus(PaymentStatus.PAID);
      }
    }

    // 支付记录
    if (paymentType.equals(PaymentType.MIXTURE)) {// 混合支付 现金+卡
      PaymentRecord paymentRecordCard = new PaymentRecord();
      paymentRecordCard.setBilling(checkoutBill);
      paymentRecordCard.setPaymentType(PaymentType.CARD);
      paymentRecordCard.setPayAmount(cardAmount);
      checkoutBill.getPaymentRecords().add(paymentRecordCard);

      PaymentRecord paymentRecordCash = new PaymentRecord();
      paymentRecordCash.setBilling(checkoutBill);
      paymentRecordCash.setPaymentType(PaymentType.CASH);
      paymentRecordCash.setPayAmount(cashAmount);
      checkoutBill.getPaymentRecords().add(paymentRecordCash);
    } else {
      PaymentRecord paymentRecord = new PaymentRecord();
      paymentRecord.setBilling(checkoutBill);
      paymentRecord.setPaymentType(checkoutBill.getPaymentType());
      paymentRecord.setPayAmount(payTotalAmount);
      checkoutBill.getPaymentRecords().add(paymentRecord);
    }

    checkoutBill.setChargeStatus(PaymentStatus.PAID);
    if (LogUtil.isDebugEnabled(BillingController.class)) {
      LogUtil.debug(BillingController.class, "Check Out Pay", "Bill Entity=%s",
          ToolsUtils.entityToString(checkoutBill));
    }
    
    try {
      checkoutService.checkoutBillPayTransaction(checkoutBill);
    } catch (Exception e) {
      e.printStackTrace();
      return ERROR_MESSAGE;
    } 
    return SUCCESS_MESSAGE;
  }
  /**
   * 退住结算账单生成后，为缴费时候可以进行调整金额
   * @param billAdjust
   * @param billId
   * @return
   */
  @RequestMapping(value = "/checkoutBillAdjust", method = RequestMethod.POST)
  public @ResponseBody Message checkoutBillAdjust(BillingAdjustment billAdjust, Long billId) {
    Billing billing = billingService.find(billId);
    billAdjust.setBilling(billing);
    billAdjust.setChargeStatus(PaymentStatus.UNPAID);
    billAdjust.setOperator(tenantAccountService.getCurrentUsername());
    billing.getBillingAdjustment().add(billAdjust);
    if (billAdjust.getAdjustmentAmount() != null && !billing.getChargeStatus().equals(PaymentStatus.PAID)) {
       billing.setTotalAmount(billing.getTotalAmount().add(billAdjust.getAdjustmentAmount()));
       billingService.update(billing);
    }else {
      return ERROR_MESSAGE;
    }
    
    return SUCCESS_MESSAGE;
  }
  /**
   * 返回账单List
   */
  @RequestMapping(value="/searchListByFilter", method = RequestMethod.GET)
  public @ResponseBody List<Billing> searchListByFilter(ChargeSearchRequest chargeSearch){
    List<Billing> totalBillings = new ArrayList<Billing>();
    
    List<Billing> filteredBillings = checkoutService.searchListByFilter(chargeSearch);
    if (filteredBillings != null && filteredBillings.size() > 0) {
      Date checkoutDate = null; //出院时间
      if (chargeSearch.getCheckoutDate() != null) {
          checkoutDate = chargeSearch.getCheckoutDate();
      }else {
        checkoutDate = new Date();
      }
      List<Billing> checkoutBillings = checkoutService.calculateCheckout(filteredBillings, checkoutDate);
      totalBillings.addAll(checkoutBillings);
    }    
    return totalBillings;    
  }
  /**
   * 得到水电配置
   * @return
   */
  @RequestMapping(value = "/getWaterElecConfig", method = RequestMethod.GET)
  public @ResponseBody Map<String, Object> getWaterElecConfig() {
    Map<String, Object> configMap = new HashMap<String,Object>();
    List<WaterElectricityChargeConfig> waterElecChargeConfigs = waterElecChargeConfigService.findAll(true);
    WaterElectricityChargeConfig waterElecChargeConfig = waterElecChargeConfigs.get(0);
    configMap.put("waterUnitPrice", waterElecChargeConfig.getWaterUnitPrice());
    configMap.put("elecUnitPrice", waterElecChargeConfig.getElectricityUnitPrice());
    return configMap;
  }
}
