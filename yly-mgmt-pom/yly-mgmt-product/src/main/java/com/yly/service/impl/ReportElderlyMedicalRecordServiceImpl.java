package com.yly.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yly.dao.ReportElderlyMedicalRecordDao;
import com.yly.entity.ReportElderlyMedicalRecord;
import com.yly.framework.service.impl.BaseServiceImpl;
import com.yly.service.ReportElderlyMedicalRecordService;

/**
 * 
 * @author yohu
 *
 */
@Service ("reportElderlyMedicalRecordServiceImpl")
public class ReportElderlyMedicalRecordServiceImpl extends
    BaseServiceImpl<ReportElderlyMedicalRecord, Long> implements
    ReportElderlyMedicalRecordService
{

  @Resource (name = "reportElderlyMedicalRecordDaoImpl")
  private void setBaseDao (ReportElderlyMedicalRecordDao reportElderlyMedicalRecordDao)
  {
    super.setBaseDao (reportElderlyMedicalRecordDao);
  }
}
