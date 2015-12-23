package com.yly.dao.impl;

import org.springframework.stereotype.Repository;

import com.yly.dao.ReportElderlyAgeStatusDao;
import com.yly.entity.ReportElderlyAgeStatus;
import com.yly.framework.dao.impl.BaseDaoImpl;

/**
 * DaoImpl - 老人年龄段统计报表
 * @author yohu
 *
 */
@Repository("reportElderlyAgeStatusDaoImpl")
public class ReportElderlyAgeStatusDaoImpl extends BaseDaoImpl<ReportElderlyAgeStatus, Long> implements ReportElderlyAgeStatusDao {

}
