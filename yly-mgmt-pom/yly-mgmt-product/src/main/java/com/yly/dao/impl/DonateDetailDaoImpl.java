package com.yly.dao.impl;

import org.springframework.stereotype.Repository;

import com.yly.dao.DonateDetailDao;
import com.yly.entity.DonateDetail;
import com.yly.framework.dao.impl.BaseDaoImpl;

@Repository("donateDetailDaoImpl")
public class DonateDetailDaoImpl extends BaseDaoImpl<DonateDetail, Long> implements DonateDetailDao {


}
