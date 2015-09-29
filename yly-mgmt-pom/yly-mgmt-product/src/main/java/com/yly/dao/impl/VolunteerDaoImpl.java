package com.yly.dao.impl;

import org.springframework.stereotype.Repository;

import com.yly.dao.VolunteerDao;
import com.yly.entity.Volunteer;
import com.yly.framework.dao.impl.BaseDaoImpl;

/**
 * 
 * @author chenyoujun
 *
 */
@Repository("volunteerDaoImpl")
public class VolunteerDaoImpl extends BaseDaoImpl<Volunteer, Long> implements VolunteerDao {

}
