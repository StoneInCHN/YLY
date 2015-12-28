package com.yly.dao.impl;

import org.springframework.stereotype.Repository;

import com.yly.dao.ReportWaterElectricityRecordDao;
import com.yly.entity.ReportWaterElectricityRecord;
import com.yly.framework.dao.impl.BaseDaoImpl;

/**
 * DaoImpl - 水电费统计报表
 * @author yohu
 *
 */
@Repository("reportWaterElectricityRecordDaoImpl")
public class ReportWaterElectricityRecordDaoImpl extends BaseDaoImpl<ReportWaterElectricityRecord, Long> implements ReportWaterElectricityRecordDao {

}
