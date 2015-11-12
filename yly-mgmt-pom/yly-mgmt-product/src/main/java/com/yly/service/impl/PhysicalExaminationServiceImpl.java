package com.yly.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yly.dao.PhysicalExaminationDao;
import com.yly.entity.PhysicalExamination;
import com.yly.framework.service.impl.BaseServiceImpl;
import com.yly.service.PhysicalExaminationService;

@Service("physicalExaminationServiceImpl")
public class PhysicalExaminationServiceImpl extends BaseServiceImpl<PhysicalExamination, Long> implements PhysicalExaminationService {

  @Resource(name = "physicalExaminationDaoImpl")
  private PhysicalExaminationDao physicalExaminationDao;
  
  @Resource
  public void setBaseDao(PhysicalExaminationDao physicalExaminationDao) {
    super.setBaseDao(physicalExaminationDao);
  }

}
