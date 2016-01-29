package com.yly.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yly.dao.ReportChargeStatisticsDao;
import com.yly.entity.ReportChargeStatistics;
import com.yly.framework.service.impl.BaseServiceImpl;
import com.yly.service.ReportChargeStatisticsService;

/**
 * 
 * @author yohu
 *
 */
@Service ("reportChargeStatisticsServiceImpl")
public class ReportChargeStatisticsServiceImpl extends
    BaseServiceImpl<ReportChargeStatistics, Long> implements
    ReportChargeStatisticsService
{

  @Resource (name = "reportChargeStatisticsDaoImpl")
  private void setBaseDao (ReportChargeStatisticsDao reportChargeStatisticsDao)
  {
    super.setBaseDao (reportChargeStatisticsDao);
  }
}
