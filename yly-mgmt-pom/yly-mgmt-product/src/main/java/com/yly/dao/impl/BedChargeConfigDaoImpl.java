package com.yly.dao.impl;

import org.springframework.stereotype.Repository;

import com.yly.dao.BedChargeConfigDao;
import com.yly.entity.BedChargeConfig;
import com.yly.framework.dao.impl.BaseDaoImpl;

/**
 * 床位收费标准配置
 * @author sujinxuan
 *
 */
@Repository("bedChargeConfigDaoImpl")
public class BedChargeConfigDaoImpl extends BaseDaoImpl<BedChargeConfig, Long> implements BedChargeConfigDao {

}
