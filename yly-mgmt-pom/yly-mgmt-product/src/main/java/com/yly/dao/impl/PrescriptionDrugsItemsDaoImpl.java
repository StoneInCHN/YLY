package com.yly.dao.impl;

import org.springframework.stereotype.Repository;

import com.yly.dao.PrescriptionDrugsItemsDao;
import com.yly.entity.PrescriptionDrugsItems;
import com.yly.framework.dao.impl.BaseDaoImpl;

@Repository("prescriptionDrugsItemsDaoImpl")
public class PrescriptionDrugsItemsDaoImpl extends BaseDaoImpl<PrescriptionDrugsItems, Long> implements PrescriptionDrugsItemsDao {


}
