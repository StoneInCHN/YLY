package com.yly.dao.impl;

import org.springframework.stereotype.Repository;

import com.yly.dao.ReportElderlyLiveStatiticsDao;
import com.yly.entity.ReportElderlyLiveStatitics;
import com.yly.framework.dao.impl.BaseDaoImpl;

/**
 * DaoImpl - 老人居住情况统计报表
 * @author yohu
 *
 */
@Repository("reportElderlyLiveStatiticsDaoImpl")
public class ReportElderlyLiveStatiticsDaoImpl extends BaseDaoImpl<ReportElderlyLiveStatitics, Long> implements ReportElderlyLiveStatiticsDao {

}
