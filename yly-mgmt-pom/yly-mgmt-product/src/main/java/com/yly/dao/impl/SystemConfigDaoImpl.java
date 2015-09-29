package com.yly.dao.impl;

import org.springframework.stereotype.Repository;

import com.yly.dao.SystemConfigDao;
import com.yly.entity.SystemConfig;
import com.yly.framework.dao.impl.BaseDaoImpl;

/**
 * 数据字典
 * @author sujinxuan
 *
 */
@Repository("systemConfigDaoImpl")
public class SystemConfigDaoImpl extends BaseDaoImpl<SystemConfig, Long> implements SystemConfigDao {

}
