package com.yly.dao.impl;

import org.springframework.stereotype.Repository;

import com.yly.dao.NurseScheduleDao;
import com.yly.entity.NurseSchedule;
import com.yly.framework.dao.impl.BaseDaoImpl;

@Repository("nurseScheduleDaoImpl")
public class NurseScheduleDaoImpl extends BaseDaoImpl<NurseSchedule, Long> implements
    NurseScheduleDao {

}
