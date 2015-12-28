package com.yly.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yly.dao.ReportWaterElectricityRecordDao;
import com.yly.entity.ReportWaterElectricityRecord;
import com.yly.framework.service.impl.BaseServiceImpl;
import com.yly.service.ReportWaterElectricityRecordService;

/**
 * 
 * @author yohu
 *
 */
@Service ("reportWaterElectricityRecordServiceImpl")
public class ReportWaterElectricityRecordServiceImpl extends
    BaseServiceImpl<ReportWaterElectricityRecord, Long> implements
    ReportWaterElectricityRecordService
{

  @Resource (name = "reportWaterElectricityRecordDaoImpl")
  private void setBaseDao (ReportWaterElectricityRecordDao reportWaterElectricityRecordDao)
  {
    super.setBaseDao (reportWaterElectricityRecordDao);
  }
}
