package com.yly.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yly.dao.PhysicalExaminationItemsDao;
import com.yly.entity.PhysicalExaminationItems;
import com.yly.framework.service.impl.BaseServiceImpl;
import com.yly.service.PhysicalExaminationItemsService;

@Service("physicalExaminationItemsServiceImpl")
public class PhysicalExaminationItemsServiceImpl extends BaseServiceImpl<PhysicalExaminationItems, Long> implements PhysicalExaminationItemsService {

  @Resource(name = "physicalExaminationItemsDaoImpl")
  private PhysicalExaminationItemsDao physicalExaminationItemsDao;
  
  @Resource
  public void setBaseDao(PhysicalExaminationItemsDao physicalExaminationItemsDao) {
    super.setBaseDao(physicalExaminationItemsDao);
  }

}
