package com.yly.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yly.dao.ReportElderlyEventDao;
import com.yly.entity.ReportElderlyEvent;
import com.yly.framework.service.impl.BaseServiceImpl;
import com.yly.service.ReportElderlyEventService;

/**
 * 
 * @author yohu
 *
 */
@Service ("reportElderlyEventServiceImpl")
public class ReportElderlyEventServiceImpl extends
    BaseServiceImpl<ReportElderlyEvent, Long> implements
    ReportElderlyEventService
{

  @Resource (name = "reportElderlyEventDaoImpl")
  private void setBaseDao (ReportElderlyEventDao reportElderlyEventDao)
  {
    super.setBaseDao (reportElderlyEventDao);
  }
}
