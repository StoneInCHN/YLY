package com.yly.dao.impl;

import org.springframework.stereotype.Repository;

import com.yly.dao.EvaluatingItemsDao;
import com.yly.entity.EvaluatingItems;
import com.yly.framework.dao.impl.BaseDaoImpl;

/**
 * 提目表 Dao Implement
 * 
 * @author luzhang
 *
 */
@Repository("evaluatingItemsDaoImpl")
public class EvaluatingItemsDaoImpl extends BaseDaoImpl<EvaluatingItems, Long> implements
    EvaluatingItemsDao {

}
