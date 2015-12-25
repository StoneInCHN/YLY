package com.yly.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yly.dao.ReportEvaluatingResultDao;
import com.yly.entity.ReportEvaluatingResult;
import com.yly.framework.service.impl.BaseServiceImpl;
import com.yly.service.ReportEvaluatingResultService;

/**
 * 
 * @author yohu
 *
 */
@Service ("reportEvaluatingResultServiceImpl")
public class ReportEvaluatingResultServiceImpl extends
    BaseServiceImpl<ReportEvaluatingResult, Long> implements
    ReportEvaluatingResultService
{

  @Resource (name = "reportEvaluatingResultDaoImpl")
  private void setBaseDao (ReportEvaluatingResultDao reportEvaluatingResultDao)
  {
    super.setBaseDao (reportEvaluatingResultDao);
  }
}
