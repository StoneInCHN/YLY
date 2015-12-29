package com.yly.dao;

import com.yly.entity.ReportNurseLevelStatistics;
import com.yly.framework.dao.BaseDao;

public interface ReportNurseLevelStatisticsDao extends BaseDao<ReportNurseLevelStatistics, Long> {

  public void report();
}
