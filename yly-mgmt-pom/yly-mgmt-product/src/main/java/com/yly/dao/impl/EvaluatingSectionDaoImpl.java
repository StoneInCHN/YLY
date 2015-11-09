package com.yly.dao.impl;

import org.springframework.stereotype.Repository;

import com.yly.dao.EvaluatingSectionDao;
import com.yly.entity.EvaluatingSection;
import com.yly.framework.dao.impl.BaseDaoImpl;

/**
 * 评估模块 Dao Implement
 * 
 * @author luzhang
 *
 */
@Repository("evaluatingSectionDaoImpl")
public class EvaluatingSectionDaoImpl extends BaseDaoImpl<EvaluatingSection, Long> implements
    EvaluatingSectionDao {

}
