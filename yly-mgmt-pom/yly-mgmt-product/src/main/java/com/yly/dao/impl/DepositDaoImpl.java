package com.yly.dao.impl;

import org.springframework.stereotype.Repository;

import com.yly.dao.DepositDao;
import com.yly.entity.Deposit;
import com.yly.framework.dao.impl.BaseDaoImpl;

/**
 * 押金
 * @author sujinxuan
 *
 */
@Repository("depositDaoImpl")
public class DepositDaoImpl extends BaseDaoImpl<Deposit, Long> implements DepositDao {

}
