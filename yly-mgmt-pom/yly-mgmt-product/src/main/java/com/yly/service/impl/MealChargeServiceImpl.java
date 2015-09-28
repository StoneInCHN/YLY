package com.yly.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yly.dao.MealChargeDao;
import com.yly.entity.MealCharge;
import com.yly.framework.service.impl.BaseServiceImpl;
import com.yly.service.MealChargeService;

/**
 * 伙食费收费记录
 * @author sujinxuan
 *
 */
@Service("mealChargeServiceImpl")
public class MealChargeServiceImpl extends BaseServiceImpl<MealCharge,Long> implements MealChargeService {

  @Resource(name = "mealChargeDaoImpl")
  private MealChargeDao mealChargeDao;
  
  @Resource
  public void setBaseDao(MealChargeDao mealChargeDao) {
    super.setBaseDao(mealChargeDao);
  }

}
