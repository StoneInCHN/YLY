package com.yly.dao.impl;

import org.springframework.stereotype.Repository;

import com.yly.dao.MealChargeDao;
import com.yly.entity.MealCharge;
import com.yly.framework.dao.impl.BaseDaoImpl;

/**
 * 伙食费收费记录
 * @author sujinxuan
 *
 */
@Repository("mealChargeDaoImpl")
public class MealChargeDaoImpl extends BaseDaoImpl<MealCharge, Long> implements MealChargeDao {

}
