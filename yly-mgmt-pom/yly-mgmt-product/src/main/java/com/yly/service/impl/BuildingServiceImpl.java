package com.yly.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yly.dao.BuildingDao;
import com.yly.entity.Building;
import com.yly.framework.service.impl.BaseServiceImpl;
import com.yly.service.BuildingService;

@Service("buildingServiceImpl")
public class BuildingServiceImpl extends BaseServiceImpl<Building, Long> implements BuildingService {

  @Resource
  public void setBaseDao(BuildingDao buildingDao) {
    super.setBaseDao(buildingDao);
  }

}
