package com.yly.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yly.dao.WaterElectricityChargeDao;
import com.yly.entity.WaterElectricityCharge;
import com.yly.service.WaterElectricityChargeService;

/**
 * 水电费收费记录
 * @author sujinxuan
 *
 */
@Service("waterElectricityChargeServiceImpl")
public class WaterElectricityChargeServiceImpl extends ChargeRecordServiceImpl<WaterElectricityCharge, Long> implements WaterElectricityChargeService {

  @Resource(name = "waterElectricityChargeDaoImpl")
  private WaterElectricityChargeDao waterElectricityChargeDao;
  
  @Resource
  public void setBaseDao(WaterElectricityChargeDao waterElectricityChargeDao) {
    super.setBaseDao(waterElectricityChargeDao);
  }

}
