package com.yly.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yly.dao.NurseScheduleDao;
import com.yly.entity.NurseSchedule;
import com.yly.framework.service.impl.BaseServiceImpl;
import com.yly.service.NurseScheduleService;

@Service("nurseScheduleServiceImpl")
public class NurseScheduleServiceImpl extends BaseServiceImpl<NurseSchedule, Long> implements
    NurseScheduleService {

  @Resource(name = "nurseScheduleDaoImpl")
  public void setBaseDao(NurseScheduleDao nurseScheduleDao) {
    super.setBaseDao(nurseScheduleDao);
  }



}
