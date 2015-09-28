package com.yly.dao.impl;

import org.springframework.stereotype.Repository;

import com.yly.dao.PersonalizedChargeConfigDao;
import com.yly.entity.PersonalizedChargeConfig;
import com.yly.framework.dao.impl.BaseDaoImpl;

/**
 * 个性化服务收费标准配置
 * @author sujinxuan
 *
 */
@Repository("personalizedChargeConfigDaoImpl")
public class PersonalizedChargeConfigDaoImpl extends BaseDaoImpl<PersonalizedChargeConfig, Long> implements PersonalizedChargeConfigDao {

}
