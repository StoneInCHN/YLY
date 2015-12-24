package com.yly.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yly.dao.ReportElderlyGenderRateDao;
import com.yly.entity.ReportElderlyGenderRate;
import com.yly.framework.service.impl.BaseServiceImpl;
import com.yly.service.ReportElderlyGenderRateService;

/**
 * 
 * @author yohu
 *
 */
@Service ("reportElderlyGenderRateServiceImpl")
public class ReportElderlyGenderRateServiceImpl extends
    BaseServiceImpl<ReportElderlyGenderRate, Long> implements
    ReportElderlyGenderRateService
{

  @Resource (name = "reportElderlyGenderRateDaoImpl")
  private void setBaseDao (ReportElderlyGenderRateDao reportElderlyGenderRateDao)
  {
    super.setBaseDao (reportElderlyGenderRateDao);
  }
}
