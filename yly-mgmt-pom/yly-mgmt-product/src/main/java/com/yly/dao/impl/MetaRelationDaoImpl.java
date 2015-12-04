package com.yly.dao.impl;

import org.springframework.stereotype.Repository;

import com.yly.dao.MetaRelationDao;
import com.yly.entity.MetaRelation;
import com.yly.framework.dao.impl.BaseDaoImpl;

/**
 * DaoImpl - 配置元关系
 * @author yohu
 *
 */
@Repository("metaRelationDaoImpl")
public class MetaRelationDaoImpl extends BaseDaoImpl<MetaRelation, Long> implements MetaRelationDao {

}
