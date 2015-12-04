package com.yly.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yly.dao.MetaPropertyDao;
import com.yly.entity.MetaProperty;
import com.yly.framework.service.impl.BaseServiceImpl;
import com.yly.service.MetaPropertyService;

@Service("metaRelationServiceImpl")
public class MetaPropertyServiceImpl extends BaseServiceImpl<MetaProperty, Long> implements MetaPropertyService {

  @Resource(name = "metaPropertyDaoImpl")
  private MetaPropertyDao metaPropertyDao;
  
  @Resource
  public void setBaseDao(MetaPropertyDao metaPropertyDao) {
    super.setBaseDao(metaPropertyDao);
  }

}
