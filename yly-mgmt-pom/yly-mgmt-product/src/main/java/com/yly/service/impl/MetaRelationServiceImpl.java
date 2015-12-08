package com.yly.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yly.dao.MetaRelationDao;
import com.yly.entity.MetaRelation;
import com.yly.framework.service.impl.BaseServiceImpl;
import com.yly.service.MetaRelationService;

@Service ("metaRelationServiceImpl")
public class MetaRelationServiceImpl extends
    BaseServiceImpl<MetaRelation, Long> implements MetaRelationService
{

  @Resource (name = "metaRelationDaoImpl")
  private MetaRelationDao metaRelationDao;

  @Resource
  public void setBaseDao (MetaRelationDao metaRelationDao)
  {
    super.setBaseDao (metaRelationDao);
  }

}
