package com.yly.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.yly.common.log.LogUtil;
import com.yly.controller.BillingController;
import com.yly.dao.BillingDao;
import com.yly.entity.BedChargeConfig;
import com.yly.entity.BedNurseCharge;
import com.yly.entity.Billing;
import com.yly.entity.BillingSupplyment;
import com.yly.entity.Deposit;
import com.yly.entity.ElderlyInfo;
import com.yly.entity.MealCharge;
import com.yly.entity.NurseChargeConfig;
import com.yly.entity.SystemConfig;
import com.yly.entity.commonenum.CommonEnum.ConfigKey;
import com.yly.framework.filter.Filter;
import com.yly.framework.filter.Filter.Operator;
import com.yly.service.BedChargeConfigService;
import com.yly.service.BedNurseChargeService;
import com.yly.service.BillingService;
import com.yly.service.DepositService;
import com.yly.service.MealChargeService;
import com.yly.service.NurseChargeConfigService;
import com.yly.service.SystemConfigService;
import com.yly.service.TenantAccountService;
import com.yly.utils.DateTimeUtils;
import com.yly.utils.FieldFilterUtils;
import com.yly.utils.ToolsUtils;

/**
 * 日常缴费账单
 * 
 * @author sujinxuan
 *
 */
@Service("billingServiceImpl")
public class BillingServiceImpl extends ChargeRecordServiceImpl<Billing, Long> implements
    BillingService {

  @Resource(name = "billingDaoImpl")
  private BillingDao billingDao;

  @Resource(name = "bedChargeConfigServiceImpl")
  private BedChargeConfigService bedChargeConfigService;

  @Resource(name = "nurseChargeConfigServiceImpl")
  private NurseChargeConfigService nurseChargeConfigService;
  
  @Resource(name = "depositServiceImpl")
  private DepositService depositService;
  
  @Resource(name = "bedNurseChargeServiceImpl")
  private BedNurseChargeService bedNurseChargeService;
  
  @Resource(name = "mealChargeServiceImpl")
  private MealChargeService mealChargeService;
  
  @Resource(name = "systemConfigServiceImpl")
  private SystemConfigService systemConfigService;
  
  @Resource(name = "tenantAccountServiceImpl")
  private TenantAccountService tenantAccountService;

  
  @Resource
  public void setBaseDao(BillingDao billingDao) {
    super.setBaseDao(billingDao);
  }

  @Override
  public List<Map<String, Object>> getBedNurseConfigByElderly(String[] properties,
      ElderlyInfo elderlyInfo) {
    List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
    if (elderlyInfo.getBed() != null) {
      List<Filter> filters = new ArrayList<Filter>();
      Filter filter =
          new Filter("chargeItem", Operator.eq, elderlyInfo.getBed().getRoom().getRoomType());
      filters.add(filter);
      List<BedChargeConfig> bedChargeConfigs =
          bedChargeConfigService.findList(null, filters, null, true, null);
      if (bedChargeConfigs != null && bedChargeConfigs.size() == 1) {
        Map<String, Object> bedChargeMap =
            FieldFilterUtils.filterEntityMap(properties, bedChargeConfigs.get(0));
        list.add(bedChargeMap);
      }

    }

    if (elderlyInfo.getNursingLevel() != null) {
      List<Filter> filters = new ArrayList<Filter>();
      Filter filter = new Filter("chargeItem", Operator.eq, elderlyInfo.getNursingLevel());
      filters.add(filter);
      List<NurseChargeConfig> nurseChargeConfigs =
          nurseChargeConfigService.findList(null, filters, null, true, null);
      if (nurseChargeConfigs != null && nurseChargeConfigs.size() == 1) {
        Map<String, Object> nurseChargeConfigMap =
            FieldFilterUtils.filterEntityMap(properties, nurseChargeConfigs.get(0));
        list.add(nurseChargeConfigMap);
      }

    }
    return list;
  }

  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
  public void updateUnpaidCheckInBill(Billing originBill, Billing editBill) {
    depositService.delete(originBill.getDeposit());
    editBill.getDeposit().setBilling(originBill);
    originBill.setDeposit(editBill.getDeposit());
    
    bedNurseChargeService.delete(originBill.getBedNurseCharge());
    editBill.getBedNurseCharge().setBilling(originBill);
    originBill.setBedNurseCharge(editBill.getBedNurseCharge());
    
    mealChargeService.delete(originBill.getMealCharge());
    if (editBill.getMealCharge()!=null) {
      editBill.getMealCharge().setBilling(originBill);
      originBill.setMealCharge(editBill.getMealCharge());
    }
    
    if (LogUtil.isDebugEnabled(BillingServiceImpl.class)) {
      LogUtil.debug(BillingServiceImpl.class, "Check In Charge Update unpaid bill", "Bill Entity=%s",
          ToolsUtils.entityToString(originBill));
    }
    billingDao.merge(originBill);
  }

  @Override
  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
  public Billing updatePaidCheckInBill(Billing originBill, Billing editBill,Long mealTypeId,Boolean isMonthlyMeal) {
    Boolean depositeChanged = false;
    Boolean bedNuresChanged = false;
    Boolean mealChanged = false;
    /**
     * 判定押金是否修改
     */
    if (!originBill.getDeposit().getDepositAmount().equals(editBill.getDeposit().getDepositAmount())) {
      depositeChanged=true;
    }
    /**
     * 判断床位护理费是否修改
     */
    if(originBill.getBedNurseCharge().getPeriodStartDate().getTime() != editBill.getBedNurseCharge().getPeriodStartDate().getTime()){
      bedNuresChanged=true;
    }
    /**
     * 判断伙食费是否修改
     */
    if ((originBill.getMealCharge()==null&&editBill.getMealCharge()!=null) 
        || (originBill.getMealCharge()!=null && editBill.getMealCharge()==null) 
        || (originBill.getMealCharge().getPeriodStartDate().getTime()!=editBill.getMealCharge().getPeriodStartDate().getTime())
        || (originBill.getMealCharge().getPeriodStartDate().getTime()==editBill.getMealCharge().getPeriodStartDate().getTime() && !originBill.getElderlyInfo().getMealType().getId().equals(mealTypeId))) {
     
      mealChanged=true;
    }
    
    if (depositeChanged || bedNuresChanged || mealChanged) {
        BillingSupplyment billingSupplyment = new BillingSupplyment();
        billingSupplyment.setBilling(originBill);
        billingSupplyment.setBillingNo(ToolsUtils.generateBillNo(tenantAccountService
            .getCurrentTenantOrgCode()));
        billingSupplyment.setOperator(tenantAccountService.getCurrentUsername());
        billingSupplyment.setTenantID(tenantAccountService.getCurrentTenantID());
        
        if (depositeChanged) {
          // 押金
          Deposit deposit = editBill.getDeposit();
          deposit.setBillingSupply(billingSupplyment);
          deposit.setBillingNo(billingSupplyment.getBillingNo());
          deposit.setOperator(billingSupplyment.getOperator());
          deposit.setTenantID(billingSupplyment.getTenantID());
          billingSupplyment.setDeposit(deposit);
          billingSupplyment.setDepositAmount(deposit.getDepositAmount());
        }
        
        if (bedNuresChanged) {
          BedNurseCharge bedNurseCharge = editBill.getBedNurseCharge();
          bedNurseCharge.setBillingSupply(billingSupplyment);
          bedNurseCharge.setBillingNo(billingSupplyment.getBillingNo());
          bedNurseCharge.setOperator(billingSupplyment.getOperator());
          bedNurseCharge.setTenantID(billingSupplyment.getTenantID());
          billingSupplyment.setBedNurseCharge(bedNurseCharge);
          billingSupplyment.setBedAmount(bedNurseCharge.getBedAmount());
          billingSupplyment.setNurseAmount(bedNurseCharge.getNurseAmount());
        }
        
        if (mealChanged) {
          if (isMonthlyMeal!=null) {
            MealCharge mealCharge = editBill.getMealCharge();
            mealCharge.setBillingSupply(billingSupplyment);
            mealCharge.setBillingNo(billingSupplyment.getBillingNo());
            mealCharge.setOperator(billingSupplyment.getOperator());
            mealCharge.setTenantID(billingSupplyment.getTenantID());
            billingSupplyment.setMealCharge(mealCharge);
            billingSupplyment.setMealAmount(mealCharge.getMealAmount());
          }
        }
        
        billingSupplyment.setTotalAmount(editBill.getTotalAmount());
        originBill.setBillingSupply(billingSupplyment);
        if (LogUtil.isDebugEnabled(BillingServiceImpl.class)) {
          LogUtil.debug(BillingController.class, "Check In Charge Update paid Bill", "Bill Entity=%s",
              ToolsUtils.entityToString(billingSupplyment));
        }
        
        return billingDao.merge(originBill);
    }
    return null;
  }

  @Override
  public void genMonthlyBill() {
      List<Filter> filters = new ArrayList<Filter>();
      Filter keyFilter = new Filter("configKey", Operator.eq, ConfigKey.BILLDAY);
      Filter enableFilter = new Filter("isEnabled",Operator.eq,true);
      filters.add(enableFilter);
      filters.add(keyFilter);
      List<SystemConfig> systemConfigs = systemConfigService.findList(null, filters, null);
      Calendar c = Calendar.getInstance();
      c.setTime(new Date());
      for(SystemConfig config:systemConfigs){
        Integer billDay = Integer.parseInt(config.getConfigValue());
        if (billDay == c.get(Calendar.DAY_OF_MONTH)) {
            genBillByTenantBillDate(c.getTime(),config.getTenantID());
        }
      }
      
  }
  
  private void genBillByTenantBillDate(Date billDate,Long tenantId){
      Date startDate = DateTimeUtils.getSpecifyTimeForDate(billDate,null,-1,1,null,null,null);
     
  }

}
