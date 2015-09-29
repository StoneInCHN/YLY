package com.yly.dao.impl;

import org.springframework.stereotype.Repository;

import com.yly.dao.VisitElderlyRecordDao;
import com.yly.entity.VisitElderlyRecord;
import com.yly.framework.dao.impl.BaseDaoImpl;

/**
 * 老人探望
 * 
 * @author shijun
 *
 */
@Repository("visitElderlyRecordDaoImpl")
public class VisitElderlyRecordDaoImpl extends BaseDaoImpl<VisitElderlyRecord, Long> implements VisitElderlyRecordDao{

}
