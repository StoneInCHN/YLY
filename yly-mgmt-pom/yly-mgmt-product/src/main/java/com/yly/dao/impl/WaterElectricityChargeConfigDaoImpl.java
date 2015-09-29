package com.yly.dao.impl;

import org.springframework.stereotype.Repository;

import com.yly.dao.WaterElectricityChargeConfigDao;
import com.yly.entity.WaterElectricityChargeConfig;
import com.yly.framework.dao.impl.BaseDaoImpl;

/**
 * 水电收费标准配置
 * @author sujinxuan
 *
 */
@Repository("waterElectricityChargeConfigDaoImpl")
public class WaterElectricityChargeConfigDaoImpl extends BaseDaoImpl<WaterElectricityChargeConfig, Long> implements WaterElectricityChargeConfigDao {

}
