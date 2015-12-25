package com.yly.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yly.dao.ReportElderlyLiveStatiticsDao;
import com.yly.entity.ReportElderlyLiveStatitics;
import com.yly.framework.service.impl.BaseServiceImpl;
import com.yly.service.ReportElderlyLiveStatiticsService;

/**
 * 
 * @author yohu
 *
 */
@Service ("reportElderlyLiveStatiticsServiceImpl")
public class ReportElderlyLiveStatiticsServiceImpl extends
    BaseServiceImpl<ReportElderlyLiveStatitics, Long> implements
    ReportElderlyLiveStatiticsService
{

  @Resource (name = "reportElderlyLiveStatiticsDaoImpl")
  private void setBaseDao (ReportElderlyLiveStatiticsDao reportElderlyLiveStatiticsDao)
  {
    super.setBaseDao (reportElderlyLiveStatiticsDao);
  }
}
