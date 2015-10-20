package com.yly.dao.impl;

import org.springframework.stereotype.Repository;

import com.yly.dao.BlackListDao;
import com.yly.entity.BlackList;
import com.yly.framework.dao.impl.BaseDaoImpl;

/**
 * BlackListDaoImpl
 * @author pengyanan
 *
 */

@Repository("blackListDaoImpl")
public class BlackListDaoImpl extends BaseDaoImpl<BlackList, Long> implements BlackListDao{

}
