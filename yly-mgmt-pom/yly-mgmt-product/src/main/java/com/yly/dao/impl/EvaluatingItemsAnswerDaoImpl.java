package com.yly.dao.impl;

import org.springframework.stereotype.Repository;

import com.yly.dao.EvaluatingItemsAnswerDao;
import com.yly.entity.EvaluatingItemsAnswer;
import com.yly.framework.dao.impl.BaseDaoImpl;

/**
 * 每个项的评估选择结果 Dao Implement
 * 
 * @author luzhang
 *
 */
@Repository("evaluatingItemsAnswerDaoImpl")
public class EvaluatingItemsAnswerDaoImpl extends BaseDaoImpl<EvaluatingItemsAnswer, Long> implements
    EvaluatingItemsAnswerDao {

}
