package com.yly.dao.impl;

import org.springframework.stereotype.Repository;

import com.yly.dao.ElderlyEvaluatingRecordDao;
import com.yly.entity.ElderlyEvaluatingRecord;
import com.yly.framework.dao.impl.BaseDaoImpl;

/**
 * 入院评估记录 Dao Implement
 * 
 * @author luzhang
 *
 */
@Repository("elderlyEvaluatingRecordDaoImpl")
public class ElderlyEvaluatingRecordDaoImpl extends BaseDaoImpl<ElderlyEvaluatingRecord, Long> implements
    ElderlyEvaluatingRecordDao {

}
