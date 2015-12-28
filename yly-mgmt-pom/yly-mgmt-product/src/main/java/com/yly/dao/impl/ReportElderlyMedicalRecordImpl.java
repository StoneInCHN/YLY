package com.yly.dao.impl;

import org.springframework.stereotype.Repository;

import com.yly.dao.ReportElderlyMedicalRecordDao;
import com.yly.entity.ReportElderlyMedicalRecord;
import com.yly.framework.dao.impl.BaseDaoImpl;

/**
 * DaoImpl - 老人看病情况报表
 * @author yohu
 *
 */
@Repository("reportElderlyMedicalRecordDaoImpl")
public class ReportElderlyMedicalRecordImpl extends BaseDaoImpl<ReportElderlyMedicalRecord, Long> implements ReportElderlyMedicalRecordDao {

}
