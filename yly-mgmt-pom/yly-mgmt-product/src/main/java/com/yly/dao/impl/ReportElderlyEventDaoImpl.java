package com.yly.dao.impl;

import org.springframework.stereotype.Repository;

import com.yly.dao.ReportElderlyEventDao;
import com.yly.entity.ReportElderlyEvent;
import com.yly.framework.dao.impl.BaseDaoImpl;

/**
 * DaoImpl - 老人事件统计报表
 * @author yohu
 *
 */
@Repository("reportElderlyEventDaoImpl")
public class ReportElderlyEventDaoImpl extends BaseDaoImpl<ReportElderlyEvent, Long> implements ReportElderlyEventDao {

}
