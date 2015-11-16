package com.yly.dao.impl;

import org.springframework.stereotype.Repository;

import com.yly.dao.EvaluatingItemsOptionsDao;
import com.yly.entity.EvaluatingItemsOptions;
import com.yly.framework.dao.impl.BaseDaoImpl;

/**
 * 评估项与选项关系表 Dao Implement
 * 
 * @author luzhang
 *
 */
@Repository("evaluatingItemsOptionsDaoImpl")
public class EvaluatingItemsOptionsDaoImpl extends BaseDaoImpl<EvaluatingItemsOptions, Long> implements
    EvaluatingItemsOptionsDao {

}
