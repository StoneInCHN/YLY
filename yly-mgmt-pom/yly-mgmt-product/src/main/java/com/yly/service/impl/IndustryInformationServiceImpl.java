package com.yly.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yly.dao.IndustryInformationDao;
import com.yly.entity.IndustryInformation;
import com.yly.framework.service.impl.BaseServiceImpl;
import com.yly.service.IndustryInformationService;

@Service("industryInformationServiceImpl")
public class IndustryInformationServiceImpl extends BaseServiceImpl<IndustryInformation, Long> implements IndustryInformationService {

  @Resource(name = "industryInformationDaoImpl")
  private IndustryInformationDao industryInformationDao;
  
  @Resource
  public void setBaseDao(IndustryInformationDao industryInformationDao) {
    super.setBaseDao(industryInformationDao);
  }

}
