package com.yly.dao.impl;

import org.springframework.stereotype.Repository;

import com.yly.dao.IndustryInformationDao;
import com.yly.entity.IndustryInformation;
import com.yly.framework.dao.impl.BaseDaoImpl;

@Repository("industryInformationDaoImpl")
public class IndustryInformationDaoImpl extends BaseDaoImpl<IndustryInformation, Long> implements IndustryInformationDao {

}
