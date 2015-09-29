package com.yly.dao.impl;

import org.springframework.stereotype.Repository;

import com.yly.dao.MealChargeConfigDao;
import com.yly.entity.MealChargeConfig;
import com.yly.framework.dao.impl.BaseDaoImpl;

/**
 * 伙食收费标准配置
 * @author sujinxuan
 *
 */
@Repository("mealChargeConfigDaoImpl")
public class MealChargeConfigDaoImpl extends BaseDaoImpl<MealChargeConfig, Long> implements MealChargeConfigDao {

}
