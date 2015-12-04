package com.yly.dao.impl;

import org.springframework.stereotype.Repository;

import com.yly.dao.ConfigTypeDao;
import com.yly.entity.ConfigType;
import com.yly.framework.dao.impl.BaseDaoImpl;

/**
 * DaoImpl - 元数据
 * @author yohu
 *
 */
@Repository("configTypeDaoImpl")
public class ConfigTypeDaoImpl extends BaseDaoImpl<ConfigType, Long> implements ConfigTypeDao {

}
