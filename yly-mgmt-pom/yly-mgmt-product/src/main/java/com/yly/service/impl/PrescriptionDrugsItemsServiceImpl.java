package com.yly.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yly.dao.PrescriptionDrugsItemsDao;
import com.yly.entity.PrescriptionDrugsItems;
import com.yly.framework.service.impl.BaseServiceImpl;
import com.yly.service.PrescriptionDrugsItemsService;

@Service("prescriptionDrugsItemsServiceImpl")
public class PrescriptionDrugsItemsServiceImpl extends BaseServiceImpl<PrescriptionDrugsItems, Long> implements PrescriptionDrugsItemsService {

  @Resource(name = "prescriptionDrugsItemsDaoImpl")
  private PrescriptionDrugsItemsDao prescriptionDrugsItemsDao;
  
  @Resource
  public void setBaseDao(PrescriptionDrugsItemsDao prescriptionDrugsItemsDao) {
    super.setBaseDao(prescriptionDrugsItemsDao);
  }



}
