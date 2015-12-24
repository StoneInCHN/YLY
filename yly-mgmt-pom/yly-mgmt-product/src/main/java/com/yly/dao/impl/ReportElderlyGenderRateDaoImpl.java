package com.yly.dao.impl;

import org.springframework.stereotype.Repository;

import com.yly.dao.ReportElderlyGenderRateDao;
import com.yly.entity.ReportElderlyGenderRate;
import com.yly.framework.dao.impl.BaseDaoImpl;

/**
 * DaoImpl - 老人性别统计报表
 * @author yohu
 *
 */
@Repository("reportElderlyGenderRateDaoImpl")
public class ReportElderlyGenderRateDaoImpl extends BaseDaoImpl<ReportElderlyGenderRate, Long> implements ReportElderlyGenderRateDao {

}
