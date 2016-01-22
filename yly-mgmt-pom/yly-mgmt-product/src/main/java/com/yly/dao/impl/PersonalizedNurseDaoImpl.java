package com.yly.dao.impl;

import org.springframework.stereotype.Repository;

import com.yly.dao.PersonalizedNurseDao;
import com.yly.entity.PersonalizedNurse;
import com.yly.framework.dao.impl.BaseDaoImpl;

@Repository("personalizedNurseDaoImpl")
public class PersonalizedNurseDaoImpl extends BaseDaoImpl<PersonalizedNurse, Long> implements
    PersonalizedNurseDao {

}
