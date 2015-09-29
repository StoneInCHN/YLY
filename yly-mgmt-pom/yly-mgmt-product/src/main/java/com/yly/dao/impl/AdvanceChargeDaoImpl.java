package com.yly.dao.impl;

import org.springframework.stereotype.Repository;

import com.yly.dao.AdvanceChargeDao;
import com.yly.entity.AdvanceCharge;
import com.yly.framework.dao.impl.BaseDaoImpl;

/**
 * 预存款
 * @author sujinxuan
 *
 */
@Repository("advanceChargeDaoImpl")
public class AdvanceChargeDaoImpl extends BaseDaoImpl<AdvanceCharge, Long> implements AdvanceChargeDao {

}
