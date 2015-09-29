package com.yly.dao.impl;

import org.springframework.stereotype.Repository;

import com.yly.dao.BillingDao;
import com.yly.entity.Billing;
import com.yly.framework.dao.impl.BaseDaoImpl;

/**
 * 日常缴费账单
 * @author sujinxuan
 *
 */
@Repository("billingDaoImpl")
public class BillingDaoImpl extends BaseDaoImpl<Billing, Long> implements BillingDao {

}
