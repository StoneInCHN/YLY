package com.yly.dao.impl;

import org.springframework.stereotype.Repository;

import com.yly.dao.EvaluatingFormDao;
import com.yly.entity.EvaluatingForm;
import com.yly.framework.dao.impl.BaseDaoImpl;

/**
 * 评估表 Dao Implement
 * 
 * @author luzhang
 *
 */
@Repository("evaluatingFormDaoImpl")
public class EvaluatingFormDaoImpl extends BaseDaoImpl<EvaluatingForm, Long> implements
    EvaluatingFormDao {

}
