package com.yly.dao.impl;

import org.springframework.stereotype.Repository;

import com.yly.dao.AuthorityDao;
import com.yly.entity.ConfigMeta;
import com.yly.framework.dao.impl.BaseDaoImpl;

/**
 * DaoImpl - 权限
 * @author pengyanan
 *
 */
@Repository("authorityDaoImpl")
public class AuthorityDaoImpl extends BaseDaoImpl<ConfigMeta, Long> implements AuthorityDao{

}
