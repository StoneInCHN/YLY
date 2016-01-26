package com.yly.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.yly.entity.Billing;
import com.yly.entity.ElderlyInfo;
import com.yly.entity.SystemConfig;
import com.yly.entity.commonenum.CommonEnum.ConfigKey;


/**
 * 缴费账单
 * 
 * @author sujinxuan
 *
 */
public interface BillingService extends ChargeRecordService<Billing, Long> {


  /**
   * 根据老人获取伙食費用
   * 
   * @param properties
   * @param elderlyInfo
   * @return
   */
  Map<String, Object> getMealConfigByElderly(String[] properties, ElderlyInfo elderlyInfo);

  /**
   * 根据老人获取床位护理费用
   * 
   * @param properties
   * @param elderlyInfo
   * @return
   */
  List<Map<String, Object>> getBedNurseConfigByElderly(String[] properties, ElderlyInfo elderlyInfo);

  /**
   * 修改未付款的入院缴费账单
   * 
   * @param originOrder
   * @param editOrder
   */
  void updateUnpaidCheckInBill(Billing originBill, Billing editBill);

  /**
   * 修改已付款的入院缴费账单
   * 
   * @param originOrder
   * @param editOrder
   */
  Billing updatePaidCheckInBill(Billing originBill, Billing editBill, Long mealTypeId,
      Boolean isMonthlyMeal);

  /**
   * 根据各租户结算日期生成老人日常月结算账单
   */
  void genMonthlyBill();

  /**
   * 按租户生成结算账单
   * 
   * @param billDate
   * @param tenantId
   */
  void genBillByTenantBillDate(Date billDate, Long tenantId);

  /**
   * 计算费用差价
   * 
   * @param key
   * @param days
   * @param oldConfig
   * @param newConfig
   * @return
   */
  BigDecimal calDiffPriceConfig(ConfigKey key, Long days, SystemConfig oldConfig,
      SystemConfig newConfig);

}
