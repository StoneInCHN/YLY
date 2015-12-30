package com.yly.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yly.dao.ReportNurseLevelStatisticsDao;
import com.yly.entity.ReportNurseLevelStatistics;
import com.yly.framework.service.impl.BaseServiceImpl;
import com.yly.service.ReportNurseLevelStatisticsService;

/**
 * 
 * @author yohu
 *
 */
@Service ("reportNurseLevelStatisticsServiceImpl")
public class ReportNurseLevelStatisticsServiceImpl extends
    BaseServiceImpl<ReportNurseLevelStatistics, Long> implements
    ReportNurseLevelStatisticsService
{

  @Resource (name = "reportNurseLevelStatisticsDaoImpl")
  private void setBaseDao (ReportNurseLevelStatisticsDao reportNurseLevelStatisticsDao)
  {
    super.setBaseDao (reportNurseLevelStatisticsDao);
  }
}
