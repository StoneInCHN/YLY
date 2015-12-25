package com.yly.dao.impl;

import org.springframework.stereotype.Repository;

import com.yly.dao.NursePlanDao;
import com.yly.entity.NursePlan;
import com.yly.framework.dao.impl.BaseDaoImpl;

@Repository("nursePlanDaoImpl")
public class NursePlanDaoImpl extends BaseDaoImpl<NursePlan, Long> implements NursePlanDao {

}
