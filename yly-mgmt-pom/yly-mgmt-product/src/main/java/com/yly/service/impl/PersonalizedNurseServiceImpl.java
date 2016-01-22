package com.yly.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yly.dao.PersonalizedNurseDao;
import com.yly.entity.PersonalizedNurse;
import com.yly.framework.service.impl.BaseServiceImpl;
import com.yly.service.PersonalizedNurseService;

@Service("personalizedNurseServiceImpl")
public class PersonalizedNurseServiceImpl extends BaseServiceImpl<PersonalizedNurse, Long>
    implements PersonalizedNurseService {

  @Resource(name = "personalizedNurseDaoImpl")
  public void setBaseDao(PersonalizedNurseDao personalizedNurseDao) {
    super.setBaseDao(personalizedNurseDao);
  }


}
