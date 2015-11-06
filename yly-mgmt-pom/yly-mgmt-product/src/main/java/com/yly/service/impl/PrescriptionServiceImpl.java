package com.yly.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yly.dao.PrescriptionDao;
import com.yly.entity.Prescription;
import com.yly.framework.service.impl.BaseServiceImpl;
import com.yly.service.PrescriptionService;

@Service("prescriptionServiceImpl")
public class PrescriptionServiceImpl extends BaseServiceImpl<Prescription, Long> implements PrescriptionService {

  @Resource(name = "prescriptionDaoImpl")
  private PrescriptionDao prescriptionDao;
  
  @Resource
  public void setBaseDao(PrescriptionDao prescriptionDao) {
    super.setBaseDao(prescriptionDao);
  }



}
