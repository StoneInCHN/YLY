package com.yly.dao.impl;

import org.springframework.stereotype.Repository;

import com.yly.dao.PersonalizedChargeDao;
import com.yly.entity.PersonalizedCharge;
import com.yly.framework.dao.impl.BaseDaoImpl;

/**
 * 个性化服务收费记录
 * @author sujinxuan
 *
 */
@Repository("personalizedChargeDaoImpl")
public class PersonalizedChargeDaoImpl extends BaseDaoImpl<PersonalizedCharge, Long> implements PersonalizedChargeDao {

}
