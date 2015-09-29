package com.yly.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yly.dao.BedDao;
import com.yly.entity.Bed;
import com.yly.framework.service.impl.BaseServiceImpl;
import com.yly.service.BedService;

@Service("bedServiceImpl")
public class BedServiceImpl extends BaseServiceImpl<Bed, Long> implements BedService {

  @Resource(name = "bedDaoImpl")
  public void setBaseDao(BedDao bedDao) {
    super.setBaseDao(bedDao);
  }

}
