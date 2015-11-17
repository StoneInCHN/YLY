package com.yly.dao.impl;

import org.springframework.stereotype.Repository;

import com.yly.dao.PhysicalExaminationItemConfigDao;
import com.yly.entity.PhysicalExaminationItemConfig;
import com.yly.framework.dao.impl.BaseDaoImpl;

@Repository("physicalExaminationItemConfigDaoImpl")
public class PhysicalExaminationItemConfigDaoImpl extends BaseDaoImpl<PhysicalExaminationItemConfig, Long> implements PhysicalExaminationItemConfigDao {

}
