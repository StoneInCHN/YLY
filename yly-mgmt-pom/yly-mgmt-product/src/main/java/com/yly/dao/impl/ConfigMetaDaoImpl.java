package com.yly.dao.impl;

import org.springframework.stereotype.Repository;

import com.yly.dao.ConfigMetaDao;
import com.yly.entity.ConfigMeta;
import com.yly.framework.dao.impl.BaseDaoImpl;

/**
 * DaoImpl - 元数据
 * @author yohu
 *
 */
@Repository("configMetaDaoImpl")
public class ConfigMetaDaoImpl extends BaseDaoImpl<ConfigMeta, Long> implements ConfigMetaDao {

}
