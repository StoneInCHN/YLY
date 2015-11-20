package com.yly.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.yly.dao.BillingDao;
import com.yly.entity.BedChargeConfig;
import com.yly.entity.Billing;
import com.yly.entity.ElderlyInfo;
import com.yly.entity.NurseChargeConfig;
import com.yly.framework.filter.Filter;
import com.yly.framework.filter.Filter.Operator;
import com.yly.service.BedChargeConfigService;
import com.yly.service.BedNurseChargeService;
import com.yly.service.BillingService;
import com.yly.service.DepositService;
import com.yly.service.MealChargeService;
import com.yly.service.NurseChargeConfigService;
import com.yly.utils.FieldFilterUtils;

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
    
    
    billingDao.merge(originBill);
  }

}
