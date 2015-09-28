package com.yly.dao.impl;

import org.springframework.stereotype.Repository;

import com.yly.dao.NurseChargeConfigDao;
import com.yly.entity.NurseChargeConfig;
import com.yly.framework.dao.impl.BaseDaoImpl;

/**
 * 护理收费标准配置
 * @author sujinxuan
 *
 */
@Repository("nurseChargeConfigDaoImpl")
public class NurseChargeConfigDaoImpl extends BaseDaoImpl<NurseChargeConfig, Long> implements NurseChargeConfigDao {

}
