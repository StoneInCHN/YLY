package com.yly.dao.impl;

import org.springframework.stereotype.Repository;

import com.yly.dao.NurseArrangementDao;
import com.yly.entity.NurseArrangement;
import com.yly.framework.dao.impl.BaseDaoImpl;

/**
 * 护理员安排 Dao Implement
 * 
 * @author luzhang
 *
 */
@Repository("nurseArrangementDaoImpl")
public class NurseArrangementDaoImpl extends BaseDaoImpl<NurseArrangement, Long> implements
    NurseArrangementDao {

}
