package com.yly.dao.impl;

import org.springframework.stereotype.Repository;

import com.yly.dao.TenantUserDao;
import com.yly.entity.TenantUser;
import com.yly.framework.dao.impl.BaseDaoImpl;

@Repository("tenantUserDaoImpl")
public class TenantUserDaoImpl extends BaseDaoImpl<TenantUser, Long> implements TenantUserDao {


}
