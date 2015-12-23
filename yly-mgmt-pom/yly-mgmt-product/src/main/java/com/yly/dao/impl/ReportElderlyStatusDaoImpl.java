package com.yly.dao.impl;

import org.springframework.stereotype.Repository;

import com.yly.dao.ReportElderlyStatusDao;
import com.yly.entity.ReportElderlyStatus;
import com.yly.framework.dao.impl.BaseDaoImpl;

/**
 * DaoImpl - 老人在院情况报表
 * @author yohu
 *
 */
@Repository("reportElderlyStatusDaoImpl")
public class ReportElderlyStatusDaoImpl extends BaseDaoImpl<ReportElderlyStatus, Long> implements ReportElderlyStatusDao {

}
