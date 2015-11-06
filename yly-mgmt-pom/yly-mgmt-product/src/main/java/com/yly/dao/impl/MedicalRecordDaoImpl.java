package com.yly.dao.impl;

import org.springframework.stereotype.Repository;

import com.yly.dao.MedicalRecordDao;
import com.yly.entity.MedicalRecord;
import com.yly.framework.dao.impl.BaseDaoImpl;

@Repository("medicalRecordDaoImpl")
public class MedicalRecordDaoImpl extends BaseDaoImpl<MedicalRecord, Long> implements MedicalRecordDao {


}
