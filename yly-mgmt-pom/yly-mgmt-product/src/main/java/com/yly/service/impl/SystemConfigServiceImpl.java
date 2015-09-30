package com.yly.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yly.dao.SystemConfigDao;
import com.yly.entity.SystemConfig;
import com.yly.entity.commonenum.CommonEnum.ConfigKey;
import com.yly.framework.filter.Filter;
import com.yly.framework.filter.Filter.Operator;
import com.yly.framework.ordering.Ordering;
import com.yly.framework.ordering.Ordering.Direction;
import com.yly.framework.service.impl.BaseServiceImpl;
import com.yly.service.SystemConfigService;
import com.yly.utils.FieldFilterUtils;

/**
 * 数据字典
 * @author sujinxuan
 *
 */
@Service("systemConfigServiceImpl")
public class SystemConfigServiceImpl extends BaseServiceImpl<SystemConfig, Long> implements SystemConfigService {

  @Resource(name = "systemConfigDaoImpl")
  private SystemConfigDao systemConfigDao;
  
  @Resource
  public void setBaseDao(SystemConfigDao systemConfigDao) {
    super.setBaseDao(systemConfigDao);
  }
  @Override
  public List<Map<String, Object>> findByConfigKey(ConfigKey configKey, Direction direction) {
    if(configKey!=null){
      List<Filter> filters = new ArrayList<Filter>();
      List<Ordering> orderings = new ArrayList<Ordering>();
      Filter keyFilter = new Filter("configKey", Operator.eq, configKey);
      filters.add(keyFilter);
      if (direction!=null) {
        Ordering ordering = new Ordering();
        ordering.setProperty("configOrder");
        ordering.setDirection(direction);
        orderings.add(ordering);
     }
     List<SystemConfig> systemConfigs = super.findList(null, filters, orderings,true,null);
     String[] propertys =
       {"id", "configValue"};
     return FieldFilterUtils.filterCollectionMap(propertys, systemConfigs);
   }
   return null;
  }

}
