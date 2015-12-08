package com.yly.dao.impl;

import org.springframework.stereotype.Repository;

import com.yly.dao.VersionConfigDao;
import com.yly.entity.VersionConfig;
import com.yly.framework.dao.impl.BaseDaoImpl;

/**
 * DaoImpl - 版本配置
 * @author yohu
 *
 */
@Repository("versionConfigDaoImpl")
public class VersionConfigDaoImpl extends BaseDaoImpl<VersionConfig, Long> implements VersionConfigDao {

}
