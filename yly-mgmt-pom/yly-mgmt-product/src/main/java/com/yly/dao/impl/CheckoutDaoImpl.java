package com.yly.dao.impl;

import org.springframework.stereotype.Repository;

import com.yly.dao.CheckoutDao;
import com.yly.entity.Billing;
import com.yly.framework.dao.impl.BaseDaoImpl;

/**
 * 退住结算 Dao Implement
 * 
 * @author luzhang
 *
 */
@Repository("checkoutDaoImpl")
public class CheckoutDaoImpl extends BaseDaoImpl<Billing, Long> implements
    CheckoutDao {

}
