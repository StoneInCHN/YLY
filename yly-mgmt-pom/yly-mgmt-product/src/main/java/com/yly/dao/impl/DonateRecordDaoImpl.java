package com.yly.dao.impl;

import org.springframework.stereotype.Repository;

import com.yly.dao.DonateRecordDao;
import com.yly.entity.DonateRecord;
import com.yly.framework.dao.impl.BaseDaoImpl;

@Repository("donateRecordDaoImpl")
public class DonateRecordDaoImpl extends BaseDaoImpl<DonateRecord, Long> implements DonateRecordDao {


}
