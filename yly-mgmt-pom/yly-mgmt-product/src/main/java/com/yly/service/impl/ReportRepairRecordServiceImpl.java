package com.yly.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yly.dao.ReportRepairRecordDao;
import com.yly.entity.ReportRepairRecord;
import com.yly.framework.service.impl.BaseServiceImpl;
import com.yly.service.ReportRepairRecordService;

/**
 * 
 * @author yohu
 *
 */
@Service ("reportRepairRecordServiceImpl")
public class ReportRepairRecordServiceImpl extends
    BaseServiceImpl<ReportRepairRecord, Long> implements
    ReportRepairRecordService
{

  @Resource (name = "reportRepairRecordDaoImpl")
  private void setBaseDao (ReportRepairRecordDao reportRepairRecordDao)
  {
    super.setBaseDao (reportRepairRecordDao);
  }
}
