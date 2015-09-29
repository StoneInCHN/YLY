package com.yly.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yly.dao.VisitElderlyRecordDao;
import com.yly.entity.VisitElderlyRecord;
import com.yly.framework.service.impl.BaseServiceImpl;
import com.yly.service.VisitElderlyRecordService;

/**
 * 老人探望
 * 
 * @author shijun
 *
 */
@Service("visitElderlyRecordServiceImpl")
public class VisitElderlyRecordServiceImpl extends BaseServiceImpl<VisitElderlyRecord, Long>
    implements VisitElderlyRecordService {

  @Resource(name = "visitElderlyRecordDaoImpl")
  private VisitElderlyRecordDao visitElderlyRecordDao;

  @Resource
  public void setBaseDao(VisitElderlyRecordDao visitElderlyRecordDao) {
    super.setBaseDao(visitElderlyRecordDao);
  }
}
