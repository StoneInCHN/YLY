package com.yly.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yly.dao.ConsultationDao;
import com.yly.entity.ConsultationRecord;
import com.yly.framework.service.impl.BaseServiceImpl;
import com.yly.service.ConsultationService;

/**
 * 咨询
 * 
 * @author shijun
 *
 */
@Service("consultationServiceImpl")
public class ConsultationServiceImpl extends BaseServiceImpl<ConsultationRecord, Long> implements
    ConsultationService {

  @Resource(name = "consultationDaoImpl")
  private ConsultationDao consultationDao;
  
  @Resource
  public void setBaseDao(ConsultationDao consultationDao) {
    super.setBaseDao(consultationDao);
  }
}
