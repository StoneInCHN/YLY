package com.yly.dao.impl;

import org.springframework.stereotype.Repository;

import com.yly.dao.MetaPropertyDao;
import com.yly.entity.MetaProperty;
import com.yly.framework.dao.impl.BaseDaoImpl;

/**
 * DaoImpl - 配置元属性
 * @author yohu
 *
 */
@Repository("metaPropertyDaoImpl")
public class MetaPropertyDaoImpl extends BaseDaoImpl<MetaProperty, Long> implements MetaPropertyDao {

}
