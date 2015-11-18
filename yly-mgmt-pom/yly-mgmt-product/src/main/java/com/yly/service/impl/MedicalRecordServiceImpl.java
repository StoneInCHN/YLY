/*package com.yly.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yly.dao.MedicalRecordDao;
import com.yly.entity.MedicalRecord;
import com.yly.framework.service.impl.BaseServiceImpl;
import com.yly.service.MedicalRecordService;

@Service("medicalRecordServiceImpl")
public class MedicalRecordServiceImpl extends BaseServiceImpl<MedicalRecord, Long> implements MedicalRecordService {

  @Resource(name = "medicalRecordDaoImpl")
  private MedicalRecordDao medicalRecordDao;
  
  @Resource
  public void setBaseDao(MedicalRecordDao medicalRecordDao) {
    super.setBaseDao(medicalRecordDao);
  }



}
*/