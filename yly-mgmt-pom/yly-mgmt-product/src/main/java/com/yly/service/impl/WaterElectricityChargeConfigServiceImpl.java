package com.yly.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yly.dao.WaterElectricityChargeConfigDao;
import com.yly.entity.WaterElectricityChargeConfig;
import com.yly.framework.service.impl.BaseServiceImpl;
import com.yly.service.WaterElectricityChargeConfigService;

/**
 * 水电收费标准配置
 * @author sujinxuan
 *
 */
@Service("waterElectricityChargeConfigServiceImpl")
public class WaterElectricityChargeConfigServiceImpl extends BaseServiceImpl<WaterElectricityChargeConfig, Long> implements WaterElectricityChargeConfigService {

  @Resource(name = "waterElectricityChargeConfigDaoImpl")
  private WaterElectricityChargeConfigDao waterElectricityChargeConfigDao;
  
  @Resource
  public void setBaseDao(WaterElectricityChargeConfigDao waterElectricityChargeConfigDao) {
    super.setBaseDao(waterElectricityChargeConfigDao);
  }

}
