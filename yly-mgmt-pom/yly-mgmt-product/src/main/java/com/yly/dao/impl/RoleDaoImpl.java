package com.yly.dao.impl;

import org.springframework.stereotype.Repository;

import com.yly.dao.RoleDao;
import com.yly.entity.Role;
import com.yly.framework.dao.impl.BaseDaoImpl;

/**
 * DaoImpl - 角色
 * @author pengyanan
 *
 */
@Repository("roleDaoImpl")
public class RoleDaoImpl extends BaseDaoImpl<Role, Long> implements RoleDao {

}
