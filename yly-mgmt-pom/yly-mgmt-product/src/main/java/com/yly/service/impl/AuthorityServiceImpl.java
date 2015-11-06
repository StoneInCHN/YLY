package com.yly.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.yly.dao.AuthorityDao;
import com.yly.entity.AuthorityResource;
import com.yly.framework.service.impl.BaseServiceImpl;
import com.yly.service.AuthorityService;

/**
 * ServiceImpl - 权限
 * @author pengyanan
 *
 */
@Repository("authorityServiceImpl")
public class AuthorityServiceImpl extends BaseServiceImpl<AuthorityResource, Long> implements AuthorityService{
  @Resource(name="authorityDaoImpl")
  private AuthorityDao authorityDao;
  
  @Resource
  private void setBaseDao(AuthorityDao authorityDao){
    super.setBaseDao(authorityDao);
  }
}
