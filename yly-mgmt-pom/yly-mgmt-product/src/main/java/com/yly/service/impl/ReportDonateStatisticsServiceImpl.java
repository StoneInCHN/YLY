package com.yly.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yly.dao.ReportDonateStatisticsDao;
import com.yly.entity.ReportDonateStatistics;
import com.yly.framework.service.impl.BaseServiceImpl;
import com.yly.service.ReportDonateStatisticsService;

/**
 * 
 * @author yohu
 *
 */
@Service ("reportDonateStatisticsServiceImpl")
public class ReportDonateStatisticsServiceImpl extends
    BaseServiceImpl<ReportDonateStatistics, Long> implements
    ReportDonateStatisticsService
{

  @Resource (name = "reportDonateStatisticsDaoImpl")
  private void setBaseDao (ReportDonateStatisticsDao reportDonateStatisticsDao)
  {
    super.setBaseDao (reportDonateStatisticsDao);
  }
}
