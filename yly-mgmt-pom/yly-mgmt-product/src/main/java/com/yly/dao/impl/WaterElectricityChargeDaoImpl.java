package com.yly.dao.impl;

import org.springframework.stereotype.Repository;

import com.yly.dao.WaterElectricityChargeDao;
import com.yly.entity.WaterElectricityCharge;
import com.yly.framework.dao.impl.BaseDaoImpl;

/**
 * 水电费收费记录
 * @author sujinxuan
 *
 */
@Repository("waterElectricityChargeDaoImpl")
public class WaterElectricityChargeDaoImpl extends BaseDaoImpl<WaterElectricityCharge, Long> implements WaterElectricityChargeDao {

}
