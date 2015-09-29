package com.yly.dao.impl;

import org.springframework.stereotype.Repository;

import com.yly.dao.ConsultationDao;
import com.yly.entity.ConsultationRecord;
import com.yly.framework.dao.impl.BaseDaoImpl;

/**
 * 咨询
 * 
 * @author shijun
 *
 */
@Repository("consultationDaoImpl")
public class ConsultationDaoImpl extends BaseDaoImpl<ConsultationRecord, Long> implements ConsultationDao{

}
