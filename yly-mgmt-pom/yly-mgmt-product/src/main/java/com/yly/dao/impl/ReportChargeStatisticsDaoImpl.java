package com.yly.dao.impl;

import org.springframework.stereotype.Repository;

import com.yly.dao.ReportChargeStatisticsDao;
import com.yly.entity.ReportChargeStatistics;
import com.yly.framework.dao.impl.BaseDaoImpl;

/**
 * DaoImpl - 费用统计表
 * @author yohu
 *
 */
@Repository("reportChargeStatisticsDaoImpl")
public class ReportChargeStatisticsDaoImpl extends BaseDaoImpl<ReportChargeStatistics, Long> implements ReportChargeStatisticsDao {

}
