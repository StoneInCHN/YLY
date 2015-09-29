package com.yly.dao.impl;

import org.springframework.stereotype.Repository;

import com.yly.dao.ElderlyInfoDao;
import com.yly.entity.ElderlyInfo;
import com.yly.framework.dao.impl.BaseDaoImpl;

/**
 * 老人信息
 * 
 * @author shijun
 *
 */
@Repository("elderlyInfoDaoImpl")
public class ElderlyInfoDaoImpl extends BaseDaoImpl<ElderlyInfo, Long> implements ElderlyInfoDao{

}
