package com.yly.dao.impl;

import org.springframework.stereotype.Repository;

import com.yly.dao.NurseLevelChangeRecordDao;
import com.yly.entity.NurseLevelChangeRecord;
import com.yly.framework.dao.impl.BaseDaoImpl;

@Repository("nurseLevelChangeRecordDaoImpl")
public class NurseLevelChangeRecordDaoImpl extends BaseDaoImpl<NurseLevelChangeRecord, Long>
    implements NurseLevelChangeRecordDao {

}
