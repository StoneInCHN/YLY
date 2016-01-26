package com.yly.dao.impl;

import org.springframework.stereotype.Repository;

import com.yly.dao.NurseArrangementRecordDao;
import com.yly.entity.NurseArrangementRecord;
import com.yly.framework.dao.impl.BaseDaoImpl;

/**
 * 护理员安排明细 Dao Implement
 * 
 * @author luzhang
 *
 */
@Repository("nurseArrangementRecordDaoImpl")
public class NurseArrangementRecordDaoImpl extends BaseDaoImpl<NurseArrangementRecord, Long> implements
    NurseArrangementRecordDao {

}
