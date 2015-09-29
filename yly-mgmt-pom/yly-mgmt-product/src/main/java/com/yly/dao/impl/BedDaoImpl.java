package com.yly.dao.impl;

import org.springframework.stereotype.Repository;

import com.yly.dao.BedDao;
import com.yly.entity.Bed;
import com.yly.framework.dao.impl.BaseDaoImpl;

@Repository("bedDaoImpl")
public class BedDaoImpl extends BaseDaoImpl<Bed, Long> implements BedDao {

}
