package com.yly.dao.impl;

import org.springframework.stereotype.Repository;

import com.yly.dao.EvaluatingItemOptionsDao;
import com.yly.entity.EvaluatingItemOptions;
import com.yly.framework.dao.impl.BaseDaoImpl;

/**
 * 选项表 Dao Implement
 * 
 * @author luzhang
 *
 */
@Repository("evaluatingItemOptionsDaoImpl")
public class EvaluatingItemOptionsDaoImpl extends BaseDaoImpl<EvaluatingItemOptions, Long> implements
    EvaluatingItemOptionsDao {

}
