package com.yly.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yly.dao.NurseDutyTypeDao;
import com.yly.entity.NurseDutyType;
import com.yly.framework.service.impl.BaseServiceImpl;
import com.yly.service.NurseDutyTypeService;

@Service("nurseDutyTypeServiceImpl")
public class NurseDutyTypeServiceImpl extends BaseServiceImpl<NurseDutyType, Long> implements
    NurseDutyTypeService {

  @Resource(name="nurseDutyTypeDaoImpl")
  public void setBaseDao(NurseDutyTypeDao nurseDutyTypeDao) {
    super.setBaseDao(nurseDutyTypeDao);
  }



}
