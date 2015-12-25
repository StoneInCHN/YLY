package com.yly.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yly.dao.NursePlanDao;
import com.yly.entity.NursePlan;
import com.yly.framework.service.impl.BaseServiceImpl;
import com.yly.service.NursePlanService;

@Service("nursePlanServiceImpl")
public class NursePlanServiceImpl extends BaseServiceImpl<NursePlan, Long> implements
    NursePlanService {

  @Resource(name="nursePlanDaoImpl")
  public void setBaseDao(NursePlanDao nursePlanDao) {
    super.setBaseDao(nursePlanDao);
  }



}
