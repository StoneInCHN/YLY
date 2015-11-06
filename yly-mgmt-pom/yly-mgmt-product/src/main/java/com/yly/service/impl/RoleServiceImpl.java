package com.yly.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yly.dao.RoleDao;
import com.yly.entity.*;
import com.yly.framework.service.impl.BaseServiceImpl;
import com.yly.service.RoleService;

/**
 * 
 * @author pengyanan
 *
 */
@Service("roleServiceImpl")
public class RoleServiceImpl extends BaseServiceImpl<Role, Long> implements RoleService {

  @Resource(name = "roleDaoImpl")
  private void setBaseDao(RoleDao roleDao) {
    super.setBaseDao(roleDao);
  }
}
