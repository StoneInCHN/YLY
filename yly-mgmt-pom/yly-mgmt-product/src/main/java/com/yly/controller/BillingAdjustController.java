package com.yly.controller;


import java.math.BigDecimal;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yly.beans.Message;
import com.yly.common.log.LogUtil;
import com.yly.controller.base.BaseController;
import com.yly.entity.Billing;
import com.yly.entity.BillingAdjustment;
import com.yly.entity.ElderlyInfo;
import com.yly.entity.PaymentRecord;
import com.yly.entity.commonenum.CommonEnum.ElderlyStatus;
import com.yly.entity.commonenum.CommonEnum.PaymentStatus;
import com.yly.entity.commonenum.CommonEnum.PaymentType;
import com.yly.service.BillingService;
import com.yly.service.ElderlyInfoService;
import com.yly.service.SystemConfigService;
import com.yly.service.TenantAccountService;
import com.yly.utils.ToolsUtils;

@Controller("billingAdjustController")
@RequestMapping("/console/billingAdjust")
public class BillingAdjustController extends BaseController {

  @Resource(name = "billingServiceImpl")
  private BillingService billingService;

  @Resource(name = "elderlyInfoServiceImpl")
  private ElderlyInfoService elderlyInfoService;
  
  @Resource(name = "systemConfigServiceImpl")
  private SystemConfigService systemConfigService;
  
  @Resource(name = "tenantAccountServiceImpl")
  private TenantAccountService tenantAccountService;
  
  

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
   * 账单金额调账
   * @param billAdjust
   * @param billId
   * @return
   */
  @RequestMapping(value = "/billAmountAdjust", method = RequestMethod.POST)
  public @ResponseBody Message chekcinBill(BillingAdjustment billAdjust, Long billId) {
    Billing billing = billingService.find(billId);
    billAdjust.setBilling(billing);
    billAdjust.setChargeStatus(PaymentStatus.UNPAID);
    billAdjust.setOperator(tenantAccountService.getCurrentUsername());
    billing.getBillingAdjustment().add(billAdjust);
    if (billing.getChargeStatus().equals(PaymentStatus.PAID)) {
       billing.setChargeStatus(PaymentStatus.UNPAID_ADJUSTMENT);
       
    }
//    if (billing.getChargeStatus().equals(PaymentStatus.UNPAID)) {
//       billing.setTotalAmount(billing.getTotalAmount().add(billAdjust.getAdjustmentAmount()));
//    }
    billingService.update(billing);
    return SUCCESS_MESSAGE;
  }
  
  
  /**
   * 账单支付
   * @param checkinBill
   * @param elderlyInfoID
   * @param cardAmount
   * @param cashAmount
   * @return
   */
  @RequestMapping(value = "/billPay", method = RequestMethod.POST)
  public @ResponseBody Message billPay(Long billingId,String remark,PaymentType paymentType,BigDecimal payTotalAmount,BigDecimal cardAmount,BigDecimal cashAmount) {
    Billing checkinBill = billingService.find(billingId);
    checkinBill.setPaymentType(paymentType);
    checkinBill.setRemark(remark);
    
    ElderlyInfo elderlyInfo = checkinBill.getElderlyInfo();
  
    elderlyInfo.setElderlyStatus(ElderlyStatus.IN_NURSING_HOME);
    checkinBill.setChargeStatus(PaymentStatus.PAID);
    if (checkinBill.getDeposit()!=null) {
      checkinBill.getDeposit().setChargeStatus(PaymentStatus.PAID);
    }
    if (checkinBill.getBedNurseCharge()!=null) {
      checkinBill.getBedNurseCharge().setChargeStatus(PaymentStatus.PAID);
    }
    if (checkinBill.getMealCharge()!=null) {
      checkinBill.getMealCharge().setChargeStatus(PaymentStatus.PAID);
    }
    
    
    //支付记录
    if (checkinBill.getPaymentType().equals(PaymentType.MIXTURE)) {//混合支付 现金+卡
      PaymentRecord paymentRecordCard = new PaymentRecord();
      paymentRecordCard.setBilling(checkinBill);
      paymentRecordCard.setPaymentType(PaymentType.CARD);
      paymentRecordCard.setPayAmount(cardAmount);
      checkinBill.getPaymentRecords().add(paymentRecordCard);
      
      PaymentRecord paymentRecordCash= new PaymentRecord();
      paymentRecordCash.setBilling(checkinBill);
      paymentRecordCash.setPaymentType(PaymentType.CASH);
      paymentRecordCash.setPayAmount(cashAmount);
      checkinBill.getPaymentRecords().add(paymentRecordCash);
    }else {
      PaymentRecord paymentRecord = new PaymentRecord();
      paymentRecord.setBilling(checkinBill);
      paymentRecord.setPaymentType(checkinBill.getPaymentType());
      paymentRecord.setPayAmount(payTotalAmount);
      checkinBill.getPaymentRecords().add(paymentRecord);
    }
    
    if (LogUtil.isDebugEnabled(BillingAdjustController.class)) {
      LogUtil.debug(BillingAdjustController.class, "Check In Charge",
          "Bill Entity=%s",ToolsUtils.entityToString(checkinBill));
    }
    billingService.update(checkinBill);
    return SUCCESS_MESSAGE;
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
   * @param model
   * @param id
   * @return
   */
  @RequestMapping(value = "/payPage", method = RequestMethod.GET)
  public String payPage(ModelMap model, Long id,String path) {
    Billing record = billingService.find(id);
    model.addAttribute("billing", record);
    return path+"/pay";
  }

}
