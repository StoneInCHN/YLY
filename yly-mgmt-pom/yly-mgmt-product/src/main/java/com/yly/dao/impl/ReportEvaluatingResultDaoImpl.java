package com.yly.dao.impl;

import org.springframework.stereotype.Repository;

import com.yly.dao.ReportEvaluatingResultDao;
import com.yly.entity.ReportEvaluatingResult;
import com.yly.framework.dao.impl.BaseDaoImpl;

/**
 * DaoImpl - 老人评估结果统计报表
 * @author yohu
 *
 */
@Repository("reportEvaluatingResultDaoImpl")
public class ReportEvaluatingResultDaoImpl extends BaseDaoImpl<ReportEvaluatingResult, Long> implements ReportEvaluatingResultDao {

}
