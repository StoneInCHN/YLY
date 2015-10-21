package com.yly.dao.impl;

import org.springframework.stereotype.Repository;

import com.yly.dao.ElderlyEventRecordDao;
import com.yly.entity.ElderlyEventRecord;
import com.yly.framework.dao.impl.BaseDaoImpl;

/**
 * 老人事件 Dao Implement
 * @author luzhang
 *
 */
@Repository("elderlyEventRecordDaoImpl")
public class ElderlyEventRecordDaoImpl extends BaseDaoImpl<ElderlyEventRecord, Long> implements ElderlyEventRecordDao {

}
