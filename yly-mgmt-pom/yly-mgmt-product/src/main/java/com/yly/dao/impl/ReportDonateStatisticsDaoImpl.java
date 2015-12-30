package com.yly.dao.impl;

import org.springframework.stereotype.Repository;

import com.yly.dao.ReportDonateStatisticsDao;
import com.yly.entity.ReportDonateStatistics;
import com.yly.framework.dao.impl.BaseDaoImpl;

/**
 * DaoImpl - 捐赠统计报表
 * @author yohu
 *
 */
@Repository("reportDonateStatisticsDaoImpl")
public class ReportDonateStatisticsDaoImpl extends BaseDaoImpl<ReportDonateStatistics, Long> implements ReportDonateStatisticsDao {

}
