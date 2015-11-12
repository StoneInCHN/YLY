package com.yly.dao.impl;

import org.springframework.stereotype.Repository;

import com.yly.dao.PhysicalExaminationDao;
import com.yly.entity.PhysicalExamination;
import com.yly.framework.dao.impl.BaseDaoImpl;

@Repository("physicalExaminationDaoImpl")
public class PhysicalExaminationDaoImpl extends BaseDaoImpl<PhysicalExamination, Long> implements PhysicalExaminationDao {

}
