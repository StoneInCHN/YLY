package com.yly.dao.impl;

import org.springframework.stereotype.Repository;

import com.yly.dao.ReportRepairRecordDao;
import com.yly.entity.ReportRepairRecord;
import com.yly.framework.dao.impl.BaseDaoImpl;

/**
 * DaoImpl - 维修记录统计报表
 * @author yohu
 *
 */
@Repository("reportRepairRecordDaoImpl")
public class ReportRepairRecordDaoImpl extends BaseDaoImpl<ReportRepairRecord, Long> implements ReportRepairRecordDao {

}
