package com.yly.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.util.Version;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.yly.dao.CheckoutDao;
import com.yly.dao.PersonalizedRecordDao;
import com.yly.entity.AdvanceCharge;
import com.yly.entity.BedChargeConfig;
import com.yly.entity.BedNurseCharge;
import com.yly.entity.Billing;
import com.yly.entity.BillingAdjustment;
import com.yly.entity.Deposit;
import com.yly.entity.ElderlyInfo;
import com.yly.entity.MealCharge;
import com.yly.entity.MealChargeConfig;
import com.yly.entity.NurseChargeConfig;
import com.yly.entity.PersonalizedCharge;
import com.yly.entity.PersonalizedNurse;
import com.yly.entity.PersonalizedRecord;
import com.yly.entity.WaterElectricityCharge;
import com.yly.entity.WaterElectricityChargeConfig;
import com.yly.entity.commonenum.CommonEnum.BillingType;
import com.yly.entity.commonenum.CommonEnum.BudgetType;
import com.yly.entity.commonenum.CommonEnum.PaymentStatus;
import com.yly.framework.filter.Filter;
import com.yly.framework.filter.Filter.Operator;
import com.yly.framework.service.impl.BaseServiceImpl;
import com.yly.json.request.ChargeSearchRequest;
import com.yly.service.AdvanceChargeService;
import com.yly.service.BedChargeConfigService;
import com.yly.service.BedNurseChargeService;
import com.yly.service.BillingService;
import com.yly.service.CheckoutService;
import com.yly.service.DepositService;
import com.yly.service.MealChargeConfigService;
import com.yly.service.MealChargeService;
import com.yly.service.NurseChargeConfigService;
import com.yly.service.PersonalizedRecordService;
import com.yly.service.WaterElectricityChargeConfigService;
import com.yly.utils.DateTimeUtils;
import com.yly.utils.SpringUtils;

@Service("checkoutServiceImpl")
public class CheckoutServiceImpl extends BaseServiceImpl<Billing, Long> implements CheckoutService{
  @Resource(name = "checkoutDaoImpl")
  private CheckoutDao checkoutDao;
  
  @Resource
  public void setBaseDao(CheckoutDao checkoutDao) {
    super.setBaseDao(checkoutDao);
  }
  
  @Resource(name = "billingServiceImpl")
  private BillingService billingService;
  
  @Resource(name = "bedChargeConfigServiceImpl")
  private BedChargeConfigService bedChargeConfigService;

  @Resource(name = "nurseChargeConfigServiceImpl")
  private NurseChargeConfigService nurseChargeConfigService;
  
  @Resource(name = "mealChargeConfigServiceImpl")
  private MealChargeConfigService mealChargeConfigService; 
  
  @Resource(name = "depositServiceImpl")
  private DepositService depositService;
  
  @Resource(name = "bedNurseChargeServiceImpl")
  private BedNurseChargeService bedNurseChargeService;
  
  @Resource(name = "mealChargeServiceImpl")
  private MealChargeService mealChargeService;
    
  @Resource(name = "personalizedRecordServiceImpl")
  private PersonalizedRecordService personalizedRecordService;
  
  @Resource(name = "advanceChargeServiceImpl")
  private AdvanceChargeService advanceChargeService;
  
  @Override
  public List<Billing> searchListByFilter(ChargeSearchRequest chargeSearch) {
    IKAnalyzer analyzer = new IKAnalyzer();
    analyzer.setMaxWordLength(true);
    try {
      BooleanQuery query = getQuery(analyzer, chargeSearch);
      return checkoutDao.searchList(query, analyzer, null);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }
  /**
   * 根据查询条件中的老人id， 账单类型，返回带条件的查询Query
   * @return
   */
  private BooleanQuery getQuery(IKAnalyzer analyzer, ChargeSearchRequest chargeSearch) {
    BooleanQuery booleanQuery  = new BooleanQuery();
    Query query = null;
    Term term = null;
    try {
      Long tennateId = tenantAccountService.getCurrentTenantID();
      if (tennateId != null) {
        QueryParser queryParser = new QueryParser(Version.LUCENE_35, "tenantID", analyzer);
        query = queryParser.parse(tennateId.toString());
        booleanQuery.add(query, Occur.MUST);
      }

      if (chargeSearch != null) {//账单类型为入院交费或者日常缴费，就是非退住结算
        if (chargeSearch.getBillingType() != null) {
          term = new Term("billType", chargeSearch.getBillingType().toString());
          query = new TermQuery(term);
          booleanQuery.add(query, Occur.MUST_NOT);
        }  
//        if (chargeSearch.getStatus() != null) {
//          term = new Term("chargeStatus", chargeSearch.getStatus().toString());
//          query = new TermQuery(term);
//          booleanQuery.add(query, Occur.MUST);
//        } 
        if(chargeSearch.getElderlyId() != null){
          QueryParser queryParser = new QueryParser(Version.LUCENE_35, "elderlyInfo.id", analyzer);
          query = queryParser.parse(chargeSearch.getElderlyId().toString());
          booleanQuery.add(query, Occur.MUST);
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return booleanQuery;
  }
  /**
   * 通过计算，返回结算的简单账单
   * checkoutDate 办理出院日期，即截止计算日期
   */
  @Override
  public List<Billing> calculateCheckout(List<Billing> filteredBillings, Date checkoutDate) {

    BigDecimal totalAmount = new BigDecimal(0); 
    BigDecimal depositAmount = new BigDecimal(0); 
    BigDecimal bedAmount = new BigDecimal(0); 
    BigDecimal nurseAmount = new BigDecimal(0); 
    BigDecimal mealAmount = new BigDecimal(0); 
    BigDecimal waterAmount = new BigDecimal(0); 
    BigDecimal electricityAmount = new BigDecimal(0); 
    BigDecimal personalizedAmount = new BigDecimal(0); 
    StringBuffer bedNurseRemark = new StringBuffer(" ");
    StringBuffer mealRemark = new StringBuffer(" ");
    StringBuffer waterElectricityRemark = new StringBuffer(" ");
    StringBuffer personalizedRemark = new StringBuffer(" ");

    Map<String, Object> configMap = getConfigMap(filteredBillings.get(0).getElderlyInfo());
    BigDecimal bedAmountPerDay = (BigDecimal)configMap.get("bedAmountPerDay");
    BigDecimal nurseAmountPerDay = (BigDecimal)configMap.get("nurseAmountPerDay");
    BigDecimal mealAmountPerDay = (BigDecimal)configMap.get("mealAmountPerDay");
    BigDecimal bedAmountPerMonth = (BigDecimal)configMap.get("bedAmountPerMonth");
    BigDecimal nurseAmountPerMonth = (BigDecimal)configMap.get("nurseAmountPerMonth");
    BigDecimal mealAmountPerMonth = (BigDecimal)configMap.get("mealAmountPerMonth");
    String bedType = (String)configMap.get("bedType");
    String nurseLevel = (String)configMap.get("nurseLevel");
    String mealType = (String)configMap.get("mealType");
//    BigDecimal waterUnitPrice = (BigDecimal)configMap.get("waterUnitPrice");
//    BigDecimal elecUnitPrice = (BigDecimal)configMap.get("elecUnitPrice");
    for (Billing billing : filteredBillings) {
      /**
       * 押金
       */
      Deposit deposit = billing.getDeposit();
      if (deposit != null && deposit.getDepositAmount() != null
          && deposit.getChargeStatus().equals(PaymentStatus.PAID)) {
        depositAmount = deposit.getDepositAmount().negate();
      }
      /**
       * 床位护理费
       */
      BedNurseCharge bedNurseCharge = billing.getBedNurseCharge();
      if (bedNurseCharge != null && bedNurseCharge.getBedAmount() != null && bedNurseCharge.getNurseAmount() != null 
          && bedNurseCharge.getPeriodStartDate() != null && bedNurseCharge.getPeriodEndDate() != null) {
          Long futureDays = DateTimeUtils.getDays(bedNurseCharge.getPeriodEndDate(), checkoutDate) + new Long(1);//还差多少天
          Long pastDays = DateTimeUtils.getDays(checkoutDate, bedNurseCharge.getPeriodStartDate());//已经过了多少天            
              
          BigDecimal bedRefundPayAmount = null; //床位的多退少补的金额，多退为负数，少补为正数
          BigDecimal nurseRefundPayAmount = null; //护理的多退少补的金额，多退为负数，少补为正数
          //已缴费：多退 （负数）
          if (bedNurseCharge.getChargeStatus().equals(PaymentStatus.PAID)) {
            if (pastDays > 0 && futureDays > 0) {//已缴费，提前退床位护理，退款按天计算，不享受包月优惠价格
              bedRefundPayAmount = bedNurseCharge.getBedAmount().subtract(bedAmountPerDay.multiply(new BigDecimal(pastDays)));//已缴费（包月费） - 单价*已经住了的天数
              nurseRefundPayAmount = bedNurseCharge.getNurseAmount().subtract(nurseAmountPerDay.multiply(new BigDecimal(pastDays)));//已缴费（包月费） - 单价*已经住了的天数
              if (bedRefundPayAmount.signum() == -1) {
                bedRefundPayAmount = new BigDecimal(0);
                bedNurseRemark.append(noRefundDetail(bedNurseCharge.getPeriodStartDate(),bedNurseCharge.getPeriodEndDate(),
                    bedType, bedAmountPerMonth, bedAmountPerDay, "bedCharge", bedNurseCharge.getBedAmount()));
              }else{
                bedRefundPayAmount = bedRefundPayAmount.negate();
                bedNurseRemark.append(refundDetail(bedNurseCharge.getPeriodStartDate(),bedNurseCharge.getPeriodEndDate(),
                    bedType, bedAmountPerMonth, bedAmountPerDay, "bedCharge", bedNurseCharge.getBedAmount(),
                    pastDays, bedAmountPerDay.multiply(new BigDecimal(pastDays)), bedRefundPayAmount));
              }
              if (nurseRefundPayAmount.signum() == -1) {
                nurseRefundPayAmount = new BigDecimal(0);
                bedNurseRemark.append(noRefundDetail(bedNurseCharge.getPeriodStartDate(),bedNurseCharge.getPeriodEndDate(),
                    nurseLevel, nurseAmountPerMonth, nurseAmountPerDay, "nurseCharge", bedNurseCharge.getBedAmount()));
              }else {
                nurseRefundPayAmount = nurseRefundPayAmount.negate();
                bedNurseRemark.append(refundDetail(bedNurseCharge.getPeriodStartDate(), bedNurseCharge.getPeriodEndDate(),
                    nurseLevel, nurseAmountPerMonth, nurseAmountPerDay, "nurseCharge", bedNurseCharge.getBedAmount(),
                    pastDays, nurseAmountPerDay.multiply(new BigDecimal(pastDays)), nurseRefundPayAmount));
              }
            }
          }
          //未缴费，未缴费(调账)或者已退款 ：少补 （正数）
          else {
            if (futureDays <= 0) {//补缴当时的包月价格
              bedRefundPayAmount = bedNurseCharge.getBedAmount();
              nurseRefundPayAmount = bedNurseCharge.getNurseAmount();
            }else if(pastDays > 0 && futureDays > 0){//未缴费，提前退床位护理，退款按天计算，不享受包月优惠价格
              bedRefundPayAmount  = bedAmountPerDay.multiply(new BigDecimal(pastDays));//单价*已经住了的天数
              nurseRefundPayAmount  = nurseAmountPerDay.multiply(new BigDecimal(pastDays));//单价*已经住了的天数
              if ((bedRefundPayAmount.subtract(bedNurseCharge.getBedAmount())).signum() != -1) {
                bedRefundPayAmount = bedNurseCharge.getBedAmount();
                bedNurseRemark.append(fullPayDetail(bedNurseCharge.getPeriodStartDate(), bedNurseCharge.getPeriodEndDate(),
                    bedType, bedAmountPerMonth, bedAmountPerDay, "bedCharge", bedNurseCharge.getBedAmount()));
              }else {
                bedNurseRemark.append(payDetail(bedNurseCharge.getPeriodStartDate(), bedNurseCharge.getPeriodEndDate(),
                    bedType, bedAmountPerMonth, bedAmountPerDay, "bedCharge", bedNurseCharge.getBedAmount(), 
                    pastDays, bedRefundPayAmount));
              }
              if ((nurseRefundPayAmount.subtract(bedNurseCharge.getNurseAmount())).signum() != -1) {
                nurseRefundPayAmount = bedNurseCharge.getNurseAmount();
                bedNurseRemark.append(fullPayDetail(bedNurseCharge.getPeriodStartDate(), bedNurseCharge.getPeriodEndDate(),
                    nurseLevel, nurseAmountPerMonth, nurseAmountPerDay, "nurseCharge", bedNurseCharge.getNurseAmount()));  
              }else {
                bedNurseRemark.append(payDetail(bedNurseCharge.getPeriodStartDate(), bedNurseCharge.getPeriodEndDate(),
                    nurseLevel, nurseAmountPerMonth, nurseAmountPerDay, "nurseCharge", bedNurseCharge.getNurseAmount(),
                    pastDays, nurseRefundPayAmount)); 
              }
            }
          }
          bedAmount = bedAmount.add(bedRefundPayAmount);
          nurseAmount = nurseAmount.add(nurseRefundPayAmount);
          
      }
      /**
       * 伙食费
       */
      MealCharge mealCharge = billing.getMealCharge();
      if (mealCharge != null && mealCharge.getMealAmount() != null 
          && mealCharge.getPeriodStartDate() != null && mealCharge.getPeriodEndDate() != null) {
        Long futureDays = DateTimeUtils.getDays(mealCharge.getPeriodEndDate(), checkoutDate) + new Long(1);;//还差多少天
        Long pastDays = DateTimeUtils.getDays(checkoutDate, mealCharge.getPeriodStartDate());//已经过了多少天
        BigDecimal mealRefundPayAmount = null; //伙食的多退少补的金额，多退为负数，少补为正数
        //已缴费：多退 （负数）
        if (mealCharge.getChargeStatus().equals(PaymentStatus.PAID)) {
          if (pastDays > 0 && futureDays > 0) {//已缴费，提前退伙食费，退款按天计算，不享受包月优惠价格
            mealRefundPayAmount = mealCharge.getMealAmount().subtract(mealAmountPerDay.multiply(new BigDecimal(pastDays)));//已缴费（包月费） - 单价*已经过了的天数
            if (mealRefundPayAmount.signum() == -1) {
              mealRefundPayAmount = new BigDecimal(0);
              mealRemark.append(noRefundDetail(mealCharge.getPeriodStartDate(), mealCharge.getPeriodEndDate(),
                  mealType, mealAmountPerMonth, mealAmountPerDay, "mealCharge", mealCharge.getMealAmount()));
            }else {
              mealRefundPayAmount = mealRefundPayAmount.negate();
              mealRemark.append(refundDetail(mealCharge.getPeriodStartDate(), mealCharge.getPeriodEndDate(),
                  mealType, mealAmountPerMonth, mealAmountPerDay, "mealCharge", mealCharge.getMealAmount(),
                  pastDays, mealAmountPerDay.multiply(new BigDecimal(pastDays)), mealRefundPayAmount));
            }
          }
        }
        //未缴费，未缴费(调账)或者已退款 ：少补 （正数）
        else {
          if (futureDays <= 0) {//补缴当时的包月价格
            mealRefundPayAmount = mealCharge.getMealAmount();
          }else if(pastDays > 0 && futureDays > 0){//未缴费，提前退伙食费，退款按天计算，不享受包月优惠价格
            mealRefundPayAmount  = mealAmountPerDay.multiply(new BigDecimal(pastDays));//单价*已经过了的天数
            if ((mealRefundPayAmount.subtract(mealCharge.getMealAmount())).signum() != -1) {
              mealRefundPayAmount = mealCharge.getMealAmount();
              mealRemark.append(fullPayDetail(mealCharge.getPeriodStartDate(), mealCharge.getPeriodEndDate(),
                  mealType, mealAmountPerMonth, mealAmountPerDay, "mealCharge", mealCharge.getMealAmount())); 
            }else {
              mealRemark.append(payDetail(mealCharge.getPeriodStartDate(), mealCharge.getPeriodEndDate(),
                  mealType, mealAmountPerMonth, mealAmountPerDay, "mealCharge", mealCharge.getMealAmount(),
                  pastDays, mealRefundPayAmount)); 
            }
          }
        }
        mealAmount = mealAmount.add(mealRefundPayAmount);
      }
      /**
       * 水电费
       */
//      WaterElectricityCharge waterElectricityCharge = billing.getWaterElectricityCharge();
//      if (waterElectricityCharge != null && waterElectricityCharge.getWaterAmount() != null && 
//          waterElectricityCharge.getElectricityAmount() != null) {
//        
//       if (waterElectricityCharge.getChargeStatus().equals(PaymentStatus.UNPAID) || 
//           waterElectricityCharge.getChargeStatus().equals(PaymentStatus.UNPAID_ADJUSTMENT)) {
//           waterAmount = waterAmount.add(waterElectricityCharge.getWaterAmount());
//           electricityAmount = electricityAmount.add(waterElectricityCharge.getElectricityAmount());
//           waterElectricityRemark.append(waterElecDetail(waterElectricityCharge.getPeriodStartDate(),
//               waterElectricityCharge.getPeriodEndDate(), waterUnitPrice, elecUnitPrice, 
//               waterElectricityCharge.getWaterCount(), waterElectricityCharge.getWaterAmount(),
//               waterElectricityCharge.getElectricityCount(), waterElectricityCharge.getElectricityAmount()));
//       }
//       
//      }
      /**
       * 个性化服务费
       */
      PersonalizedCharge personalizedCharge = billing.getPersonalizedCharge();
      if (personalizedCharge != null && personalizedCharge.getPersonalizedAmount() != null) {
        if (personalizedCharge.getChargeStatus().equals(PaymentStatus.UNPAID) || 
            personalizedCharge.getChargeStatus().equals(PaymentStatus.UNPAID_ADJUSTMENT)) {
          personalizedAmount = personalizedAmount.add(personalizedCharge.getPersonalizedAmount());
          Set<PersonalizedRecord> personalizedRecords = personalizedCharge.getPersonalizedRecords();
          for (PersonalizedRecord personalizedRecord : personalizedRecords) {
            PersonalizedNurse personalizedNurse = personalizedRecord.getPersonalizedNurse();
            personalizedRemark.append(personalizedDetail(personalizedRecord.getServiceTime(),
                personalizedRecord.getServiceNurse(), personalizedNurse.getServicePrice(), personalizedRecord.getNurseContent()));
          }
        }
      }
    }
    
    //还要结算已经享受过但是没有录入形成账单的个性化服务
    if(filteredBillings != null && filteredBillings.size() > 0 && checkoutDate != null){
      ChargeSearchRequest chargeSearch = new ChargeSearchRequest();
      chargeSearch.setElderlyId(filteredBillings.get(0).getElderlyInfo().getId());
      chargeSearch.setCheckoutDate(checkoutDate);
      List<PersonalizedRecord> personalizedRecords = personalizedRecordService.searchRecentRecords(chargeSearch);
      //List<PersonalizedRecord> personalizedRecords = personalizedRecordService.findAll();
      for (PersonalizedRecord personalizedRecord : personalizedRecords) {
        if (personalizedRecord.getPersonalizedCharge() == null) {
          PersonalizedNurse personalizedNurse = personalizedRecord.getPersonalizedNurse();
          personalizedAmount = personalizedAmount.add(personalizedNurse.getServicePrice());//一次费用
          personalizedRemark.append(personalizedDetail(personalizedRecord.getServiceTime(),
              personalizedRecord.getServiceNurse(), personalizedNurse.getServicePrice(), personalizedRecord.getNurseContent()));
        }
      }
    }
    
    //简单的退住总账单
    Billing checkoutBilling = new Billing();
    checkoutBilling.setBillType(BillingType.CHECK_OUT);
    
    BedNurseCharge bedNurseCharge = new BedNurseCharge();
    bedNurseCharge.setBedAmount(bedAmount);
    bedNurseCharge.setNurseAmount(nurseAmount);
    if (StringUtils.isNotBlank(bedNurseRemark.toString().trim())) {
      bedNurseRemark.append(
          SpringUtils.getMessage("yly.checkout.refundPayNote", formatDate(checkoutDate)));
    }
    bedNurseCharge.setRemark(bedNurseRemark.toString());
    
    checkoutBilling.setBedNurseCharge(bedNurseCharge);
    
    MealCharge mealCharge = new MealCharge();
    mealCharge.setMealAmount(mealAmount);
    if (StringUtils.isNotBlank(mealRemark.toString().trim())) {
      mealRemark.append(
          SpringUtils.getMessage("yly.checkout.refundPayNote", formatDate(checkoutDate)));
    }
    mealCharge.setRemark(mealRemark.toString());
    checkoutBilling.setMealCharge(mealCharge);
    
    WaterElectricityCharge waterElectricityCharge = new WaterElectricityCharge();
    waterElectricityCharge.setWaterAmount(waterAmount);
    waterElectricityCharge.setElectricityAmount(electricityAmount);
    waterElectricityCharge.setRemark(waterElectricityRemark.toString());
    checkoutBilling.setWaterElectricityCharge(waterElectricityCharge);
    
    PersonalizedCharge personalizedCharge = new PersonalizedCharge();
    personalizedCharge.setPersonalizedAmount(personalizedAmount);
    personalizedCharge.setRemark(personalizedRemark.toString());
    checkoutBilling.setPersonalizedCharge(personalizedCharge);
    
    totalAmount = bedAmount.add(depositAmount).add(nurseAmount).add(mealAmount)
        .add(waterAmount).add(electricityAmount).add(personalizedAmount);
    checkoutBilling.setTotalAmount(totalAmount);
    
    filteredBillings.add(checkoutBilling);
    
    return filteredBillings;
  }
  
  @Override 
  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
  public void checkoutBillTransaction(ChargeSearchRequest chargeSearch, Billing checkoutBill) {
    //处理还未生成账单的个性化服务的记录
    List<PersonalizedRecord> personalizedRecords = personalizedRecordService.searchRecentRecords(chargeSearch);
    for (PersonalizedRecord personalizedRecord : personalizedRecords) {
      if (personalizedRecord.getPersonalizedCharge() == null) {
        personalizedRecord.setPersonalizedCharge(checkoutBill.getPersonalizedCharge());
        personalizedRecordService.update(personalizedRecord);
      }
    }
    billingService.save(checkoutBill);
    
  }
  @Override
  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
  public void checkoutBillPayTransaction(Billing checkoutBill) throws Exception {
    //之前该老人下所有的账单（包括入院办理账单和日常缴费账单）的状态都会改成 “已缴费”
    ChargeSearchRequest chargeSearch = new ChargeSearchRequest();
    chargeSearch.setElderlyId(checkoutBill.getElderlyInfo().getId());
    chargeSearch.setBillingType(BillingType.CHECK_OUT);
    //chargeSearch.setStatus(PaymentStatus.UNPAID);
    List<Billing> allOtherBillings = searchListByFilter(chargeSearch);
    for (Billing otherBilling : allOtherBillings) {
      if (otherBilling.getDeposit() != null) {
        otherBilling.getDeposit().setChargeStatus(PaymentStatus.PAID);
        otherBilling.getDeposit().setPayTime(checkoutBill.getPayTime());
        otherBilling.getDeposit().setPaymentType(checkoutBill.getPaymentType());
        otherBilling.getDeposit().setRemark(
            otherBilling.getDeposit().getRemark().replaceAll(
                SpringUtils.getMessage("yly.charge.unpaid.label"), SpringUtils.getMessage("yly.charge.paid.label")));
      }

      if (otherBilling.getBedNurseCharge() != null) {
        otherBilling.getBedNurseCharge().setChargeStatus(PaymentStatus.PAID);
        otherBilling.getBedNurseCharge().setPayTime(checkoutBill.getPayTime());
        otherBilling.getBedNurseCharge().setPaymentType(checkoutBill.getPaymentType());
        otherBilling.getBedNurseCharge().setRemark(
            otherBilling.getBedNurseCharge().getRemark().replaceAll(
                SpringUtils.getMessage("yly.charge.unpaid.label"), SpringUtils.getMessage("yly.charge.paid.label")));
      }

      if (otherBilling.getMealCharge() != null) {
        otherBilling.getMealCharge().setChargeStatus(PaymentStatus.PAID);
        otherBilling.getMealCharge().setPayTime(checkoutBill.getPayTime());
        otherBilling.getMealCharge().setPaymentType(checkoutBill.getPaymentType());
        otherBilling.getMealCharge().setRemark(
            otherBilling.getMealCharge().getRemark().replaceAll(
                SpringUtils.getMessage("yly.charge.unpaid.label"), SpringUtils.getMessage("yly.charge.paid.label")));
      }

      if (otherBilling.getWaterElectricityCharge() != null) {
        otherBilling.getWaterElectricityCharge().setChargeStatus(PaymentStatus.PAID);
        otherBilling.getWaterElectricityCharge().setPayTime(checkoutBill.getPayTime());
        otherBilling.getWaterElectricityCharge().setPaymentType(checkoutBill.getPaymentType());
//        otherBilling.getWaterElectricityCharge().setRemark(
//            otherBilling.getWaterElectricityCharge().getRemark().replaceAll(
//                SpringUtils.getMessage("yly.charge.unpaid.label"), SpringUtils.getMessage("yly.charge.paid.label")));
      }

      if (otherBilling.getPersonalizedCharge() != null) {
        otherBilling.getPersonalizedCharge().setChargeStatus(PaymentStatus.PAID);
        otherBilling.getPersonalizedCharge().setPayTime(checkoutBill.getPayTime());
        otherBilling.getPersonalizedCharge().setPaymentType(checkoutBill.getPaymentType());
        otherBilling.getPersonalizedCharge().setRemark(
            otherBilling.getPersonalizedCharge().getRemark().replaceAll(
                SpringUtils.getMessage("yly.charge.unpaid.label"), SpringUtils.getMessage("yly.charge.paid.label")));
      }
      
      otherBilling.setChargeStatus(PaymentStatus.PAID);
      
      billingService.update(otherBilling);
    }
    //之前的所有调整金额，状态都会改成 “已缴费”
    if (checkoutBill.getBillingAdjustment() != null && checkoutBill.getBillingAdjustment().size() > 0) {
      Set<BillingAdjustment> billingAdjustments = checkoutBill.getBillingAdjustment();
      for (BillingAdjustment billingAdjustment : billingAdjustments) {
        billingAdjustment.setChargeStatus(PaymentStatus.PAID);
        billingAdjustment.setOperator(tenantAccountService.getCurrentUsername());
      }
    }
    //重置预存款
    if (checkoutBill.getAdvanceChargeAmount() != null) {
      checkoutBill.getElderlyInfo().setAdvanceChargeAmount(new BigDecimal(0));
      AdvanceCharge advanceCharge = new AdvanceCharge();
        advanceCharge.setAdvanceAmount(checkoutBill.getAdvanceChargeAmount().negate());//负的
        advanceCharge.setElderlyInfo(checkoutBill.getElderlyInfo());
        advanceCharge.setBudgetType(BudgetType.INCOME);
        advanceCharge.setPayTime(new Date());
        advanceCharge.setOperator(tenantAccountService.getCurrentUsername());
        advanceCharge.setBillingNo(checkoutBill.getBillingNo());
        advanceChargeService.save(advanceCharge, true);
    } 
    
    billingService.update(checkoutBill);
    
  }
  //得到老人的配置以及所享受服务
  public Map<String, Object> getConfigMap(ElderlyInfo elderlyInfo){
    Map<String, Object> configMap = new HashMap<String,Object>();
    
    if (elderlyInfo.getBed() != null) {
      List<Filter> filters = new ArrayList<Filter>();
      Filter filter =
          new Filter("chargeItem", Operator.eq, elderlyInfo.getBed().getRoom().getRoomType());
      filters.add(filter);
      List<BedChargeConfig> bedChargeConfigs =
          bedChargeConfigService.findList(null, filters, null, true, null);
      BedChargeConfig bedChargeConfig = bedChargeConfigs.get(0);
      configMap.put("bedAmountPerMonth", bedChargeConfig.getAmountPerMonth());
      configMap.put("bedAmountPerDay", bedChargeConfig.getAmountPerDay());
      configMap.put("bedType", bedChargeConfig.getChargeItem().getConfigValue());
    }    
    if (elderlyInfo.getNursingLevel() != null) {
      List<Filter> filters = new ArrayList<Filter>();
      Filter filter = new Filter("chargeItem", Operator.eq, elderlyInfo.getNursingLevel());
      filters.add(filter);
      List<NurseChargeConfig> nurseChargeConfigs = nurseChargeConfigService.findList(null, filters, null, true, null);
      NurseChargeConfig nurseChargeConfig = nurseChargeConfigs.get(0);
      configMap.put("nurseAmountPerMonth", nurseChargeConfig.getAmountPerMonth());
      configMap.put("nurseAmountPerDay", nurseChargeConfig.getAmountPerDay());
      configMap.put("nurseLevel", nurseChargeConfig.getChargeItem().getConfigValue());
    }
    if (elderlyInfo.getMealType() != null) {
      List<Filter> filters = new ArrayList<Filter>();
      Filter filter = new Filter("chargeItem", Operator.eq, elderlyInfo.getMealType());
      filters.add(filter);
      List<MealChargeConfig> mealChargeConfigs = mealChargeConfigService.findList(null, filters, null, true, null);
      MealChargeConfig mealChargeConfig = mealChargeConfigs.get(0);
      configMap.put("mealAmountPerMonth", mealChargeConfig.getAmountPerMonth());
      configMap.put("mealAmountPerDay", mealChargeConfig.getAmountPerDay());
      configMap.put("mealType", mealChargeConfig.getChargeItem().getConfigValue());
    }
    
    return configMap;
  }
  

  
  /*  {0}-{1}：[{2} 月收费： ￥{3} 日收费： ￥{4}]\n
    已缴{5}：￥ {6}，按天结算总金额达到包月价格，故实际消费了：￥ {6}，无需退款。*/
  private String noRefundDetail(Date start, Date end, String service, 
      BigDecimal aPerMonth, BigDecimal aPerDay, String chargeType, BigDecimal amount){
    return SpringUtils.getMessage("yly.checkout.noRefundDetail",
        formatDate(start),formatDate(end), service, aPerMonth, aPerDay, SpringUtils.getMessage("yly.charge." + chargeType), amount);
  }
  
  /*  {0}-{1}：[{2} 月收费： ￥{3} 日收费： ￥{4}]\n
     已缴{5}：￥ {6}，由于本次账单已用{7}天，按每天￥{4}的标准收取{5}，故实际消费了：￥ {8}，需退{5}：￥ {9}\n*/
  private String refundDetail(Date start, Date end, String service, 
      BigDecimal aPerMonth, BigDecimal aPerDay, String chargeType, BigDecimal amount,
      Long pastDays, BigDecimal caculAmount, BigDecimal finalAmount){
    return SpringUtils.getMessage("yly.checkout.refundDetail",
        formatDate(start), formatDate(end),service, aPerMonth, aPerDay, SpringUtils.getMessage("yly.charge." + chargeType), 
        amount, pastDays, caculAmount, finalAmount);
  } 
  
  /*  {0}-{1}：[{2} 月收费： ￥{3} 日收费： ￥{4}]\n 
      未缴{5}：￥ {6},按天结算总金额达到包月价格，故需缴{5}：￥ {6}\n */
  private String fullPayDetail(Date start, Date end, String service, 
      BigDecimal aPerMonth, BigDecimal aPerDay, String chargeType, BigDecimal amount){
    return SpringUtils.getMessage("yly.checkout.fullPayDetail",
        formatDate(start),formatDate(end), service, aPerMonth, aPerDay, SpringUtils.getMessage("yly.charge." + chargeType), amount);
  }
  
  /*  {0}-{1}：[{2} 月收费： ￥{3} 日收费： ￥{4}]\n 
      未缴{5}：￥ {6},由于本次账单已用{7}天，按每天￥{4}的标准收取{5}，故需缴{5}：￥ {8}\n */
  private String payDetail(Date start, Date end, String service, 
      BigDecimal aPerMonth, BigDecimal aPerDay, String chargeType, BigDecimal amount,
      Long pastDays, BigDecimal finalAmount){
    return SpringUtils.getMessage("yly.checkout.payDetail",
        formatDate(start), formatDate(end),service, aPerMonth, aPerDay, SpringUtils.getMessage("yly.charge." + chargeType), 
        amount, pastDays, finalAmount);
  }
  /*  {0}-{1}：[水价：￥{2}/吨  电价：￥{3}/度]\n 
    周期内用水的吨数：{4}吨,按￥{2}/吨的单价计算，故需缴水费：￥ {5}\n 
    周期内用电的度数：{6}度,按￥{3}/度的单价计算，故需缴电费：￥ {7}\n */
//  private String waterElecDetail(Date start, Date end, BigDecimal waterUnitPrice, BigDecimal elecUnitPrice,
//      BigDecimal waterCount, BigDecimal waterAmout, BigDecimal elecCount, BigDecimal elecAmount){
//    return SpringUtils.getMessage("yly.checkout.waterElecDetail",
//        formatDate(start), formatDate(end), waterUnitPrice, elecUnitPrice, waterCount, waterAmout, elecCount, elecAmount);
//  }
  /*  服务时间：{0} 服务护工：{1} 服务单价：{2} 服务内容：{3}\n*/
  private String personalizedDetail(Date svcTime, String svcNurse, BigDecimal svcPrice, String nurseContent){
    return SpringUtils.getMessage("yly.checkout.personalizedDetail",
        formatDate(svcTime), svcNurse, svcPrice, nurseContent);
  }
  
  //返回 比如 2015.12.5 的格式
  private String formatDate(Date date){
    return DateTimeUtils.getSimpleFormatString(DateTimeUtils.shortPointDateFormat, date);
  }


}
