package com.yly.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yly.dao.MealChargeConfigDao;
import com.yly.entity.MealChargeConfig;
import com.yly.framework.service.impl.BaseServiceImpl;
import com.yly.service.MealChargeConfigService;

/**
 * 伙食收费标准配置
 * @author sujinxuan
 *
 */
@Service("mealChargeConfigServiceImpl")
public class MealChargeConfigServiceImpl extends BaseServiceImpl<MealChargeConfig, Long> implements MealChargeConfigService {

  @Resource(name = "mealChargeConfigDaoImpl")
  private MealChargeConfigDao mealChargeConfigDao;
  
  @Resource
  public void setBaseDao(MealChargeConfigDao mealChargeConfigDao) {
    super.setBaseDao(mealChargeConfigDao);
  }

}
