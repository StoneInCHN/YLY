package com.yly.dao.impl;

import org.springframework.stereotype.Repository;

import com.yly.dao.PrescriptionDao;
import com.yly.entity.Prescription;
import com.yly.framework.dao.impl.BaseDaoImpl;

@Repository("prescriptionDaoImpl")
public class PrescriptionDaoImpl extends BaseDaoImpl<Prescription, Long> implements PrescriptionDao {


}
