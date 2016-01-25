package com.yly.dao.impl;

import org.springframework.stereotype.Repository;

import com.yly.dao.BedChangeRecordDao;
import com.yly.entity.BedChangeRecord;
import com.yly.framework.dao.impl.BaseDaoImpl;

@Repository("bedChangeRecordDaoImpl")
public class BedChangeRecordDaoImpl extends BaseDaoImpl<BedChangeRecord, Long> implements
    BedChangeRecordDao {


}
