package com.yly.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yly.dao.ReportElderlyAgeStatusDao;
import com.yly.entity.ReportElderlyAgeStatus;
import com.yly.framework.service.impl.BaseServiceImpl;
import com.yly.service.ReportElderlyAgeStatusService;

/**
 * 
 * @author yohu
 *
 */
@Service ("reportElderlyAgeStatusServiceImpl")
public class ReportElderlyAgeStatusServiceImpl extends
    BaseServiceImpl<ReportElderlyAgeStatus, Long> implements
    ReportElderlyAgeStatusService
{

  @Resource (name = "reportElderlyAgeStatusDaoImpl")
  private void setBaseDao (ReportElderlyAgeStatusDao reportElderlyAgeStatusDao)
  {
    super.setBaseDao (reportElderlyAgeStatusDao);
  }
}
