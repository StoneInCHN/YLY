package com.yly.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yly.dao.ReportProcedureDao;
import com.yly.entity.base.BaseEntity;
import com.yly.framework.service.impl.BaseServiceImpl;
import com.yly.service.ReportProcedureService;

/**
 * 
 * @author yohu
 *
 */
@Service ("reportProcedureServiceImpl")
public class ReportProcedureServiceImpl extends
    BaseServiceImpl<BaseEntity, Long> implements
    ReportProcedureService
{

  @Resource (name = "reportProcedureDaoImpl")
  private void setBaseDao (ReportProcedureDao reportProcedureDao)
  {
    super.setBaseDao (reportProcedureDao);
  }
}
