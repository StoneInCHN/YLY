package com.yly.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yly.dao.ConfigMetaDao;
import com.yly.dao.MetaRelationDao;
import com.yly.entity.ConfigMeta;
import com.yly.entity.commonenum.CommonEnum.MetaRelation;
import com.yly.framework.filter.Filter;
import com.yly.framework.filter.Filter.Operator;
import com.yly.framework.service.impl.BaseServiceImpl;
import com.yly.service.ConfigMetaService;

@Service ("configMetaServiceImpl")
public class ConfigMetaServiceImpl extends BaseServiceImpl<ConfigMeta, Long>
    implements ConfigMetaService
{
  
  @Resource (name = "configMetaDaoImpl")
  private ConfigMetaDao configMetaDao;
  @Resource (name = "metaRelationDaoImpl")
  private MetaRelationDao metaRelationDao;
  
  @Resource
  public void setBaseDao (ConfigMetaDao configMetaDao)
  {
    super.setBaseDao (configMetaDao);
  }

  /**
   * 查询所有功能包
   */
  @Override
  public List<ConfigMeta> getPackageConfigMeta ()
  {
    List<ConfigMeta> configMetas = new ArrayList<ConfigMeta> ();
    List<Filter> filters =new ArrayList<Filter>();
    
    //查询所有为include的关系，
    Filter relationFilter =new Filter ("metaRelation", Operator.eq, MetaRelation.INCLUDE);
    filters.add (relationFilter);
    List<com.yly.entity.MetaRelation> metaRelationList= metaRelationDao.findList (null, null, filters, null);
    
    for (com.yly.entity.MetaRelation metaRelation : metaRelationList)
    {
      //判断configMeta是否为packageFunction
      if (!configMetas.contains (metaRelation.getMainID ()) && metaRelation.getMainID ().getConfigKey () != null /*&& metaRelation.getMainID ().getConfigType ().equals (configType)*/)
      {
        configMetas.add (metaRelation.getMainID ());
      }
    }
    return configMetas;
  }

  @Override
  public List<ConfigMeta> findRelationFunction (ConfigMeta configMeta)
  {
    List<ConfigMeta> configMetas = new ArrayList<ConfigMeta> ();
    List<Filter> filters =new ArrayList<Filter>();
    
    
    Filter mainFilter =new Filter ("mainID", Operator.eq, configMeta);
    Filter relationFilter =new Filter ("metaRelation", Operator.eq, MetaRelation.INCLUDE);
    filters.add (relationFilter);
    filters.add (mainFilter);
    
    List<com.yly.entity.MetaRelation> metaRelationList= metaRelationDao.findList (null, null, filters, null);
    
    //根据关系表，查询对应功能包下面的功能
    for (com.yly.entity.MetaRelation metaRelation : metaRelationList)
    {
      if (metaRelation.getRelationID ().getConfigKey () != null)
      {
        configMetas.add (metaRelation.getRelationID ());
      }
      
    }
    return configMetas;
  }

}
