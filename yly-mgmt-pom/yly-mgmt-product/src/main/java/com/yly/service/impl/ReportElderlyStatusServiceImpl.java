package com.yly.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yly.dao.ReportElderlyStatusDao;
import com.yly.entity.ReportElderlyStatus;
import com.yly.framework.service.impl.BaseServiceImpl;
import com.yly.service.ReportElderlyStatusService;

/**
 * 
 * @author yohu
 *
 */
@Service ("reportElderlyStatusServiceImpl")
public class ReportElderlyStatusServiceImpl extends
    BaseServiceImpl<ReportElderlyStatus, Long> implements
    ReportElderlyStatusService
{

  @Resource (name = "reportElderlyStatusDaoImpl")
  private void setBaseDao (ReportElderlyStatusDao reportElderlyStatusDao)
  {
    super.setBaseDao (reportElderlyStatusDao);
  }
}
