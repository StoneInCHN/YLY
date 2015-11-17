package com.yly.dao.impl;

import org.springframework.stereotype.Repository;

import com.yly.dao.PhysicalExaminationItemsDao;
import com.yly.entity.PhysicalExaminationItems;
import com.yly.framework.dao.impl.BaseDaoImpl;

@Repository("physicalExaminationItemsDaoImpl")
public class PhysicalExaminationItemsDaoImpl extends BaseDaoImpl<PhysicalExaminationItems, Long> implements PhysicalExaminationItemsDao {

}
