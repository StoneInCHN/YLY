package com.yly.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yly.dao.VolunteerDao;
import com.yly.entity.Volunteer;
import com.yly.framework.service.impl.BaseServiceImpl;
import com.yly.service.VolunteerService;

/**
 * 
 * @author chenyoujun
 *
 */
@Service("volunteerServiceImpl")
public class VolunteerServiceImpl extends BaseServiceImpl<Volunteer, Long> implements VolunteerService {

  @Resource(name = "volunteerDaoImpl")
  private VolunteerDao volunteerDao;
  
  @Resource
  public void setBaseDao(VolunteerDao volunteerDao) {
    super.setBaseDao(volunteerDao);
  }
  
}
