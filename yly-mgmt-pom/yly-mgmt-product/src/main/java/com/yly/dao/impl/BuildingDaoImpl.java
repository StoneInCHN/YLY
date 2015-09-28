package com.yly.dao.impl;

import org.springframework.stereotype.Repository;

import com.yly.dao.BuildingDao;
import com.yly.entity.Building;
import com.yly.framework.dao.impl.BaseDaoImpl;

@Repository("buildingDaoImpl")
public class BuildingDaoImpl extends BaseDaoImpl<Building, Long> implements BuildingDao {

}
