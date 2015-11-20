package com.yly.dao.impl;

import org.springframework.stereotype.Repository;

import com.yly.dao.NurseDutyTypeDao;
import com.yly.entity.NurseDutyType;
import com.yly.framework.dao.impl.BaseDaoImpl;

@Repository("nurseDutyTypeDaoImpl")
public class NurseDutyTypeDaoImpl extends BaseDaoImpl<NurseDutyType, Long> implements
    NurseDutyTypeDao {

}
