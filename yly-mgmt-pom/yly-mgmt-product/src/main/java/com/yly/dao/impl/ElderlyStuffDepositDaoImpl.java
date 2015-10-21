package com.yly.dao.impl;

import org.springframework.stereotype.Repository;

import com.yly.dao.ElderlyStuffDepositDao;
import com.yly.entity.ElderlyStuffDeposit;
import com.yly.framework.dao.impl.BaseDaoImpl;

/**
 * 物品寄存 Dao Implement
 * @author luzhang
 *
 */
@Repository("elderlyStuffDepositDaoImpl")
public class ElderlyStuffDepositDaoImpl extends BaseDaoImpl<ElderlyStuffDeposit, Long> implements ElderlyStuffDepositDao {

}
