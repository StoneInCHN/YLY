package com.yly.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yly.dao.PhysicalExaminationItemConfigDao;
import com.yly.entity.PhysicalExaminationItemConfig;
import com.yly.entity.commonenum.CommonEnum.ConfigKey;
import com.yly.framework.filter.Filter;
import com.yly.framework.filter.Filter.Operator;
import com.yly.framework.ordering.Ordering;
import com.yly.framework.ordering.Ordering.Direction;
import com.yly.framework.service.impl.BaseServiceImpl;
import com.yly.service.PhysicalExaminationItemConfigService;
import com.yly.utils.FieldFilterUtils;

@Service("physicalExaminationItemConfigServiceImpl")
public class PhysicalExaminationItemConfigServiceImpl extends BaseServiceImpl<PhysicalExaminationItemConfig, Long> implements PhysicalExaminationItemConfigService {

  @Resource(name = "physicalExaminationItemConfigDaoImpl")
  private PhysicalExaminationItemConfigDao physicalExaminationItemConfigDao;
  
  @Resource
  public void setBaseDao(PhysicalExaminationItemConfigDao physicalExaminationItemConfigDao) {
    super.setBaseDao(physicalExaminationItemConfigDao);
  }

  @Override
  public List<Map<String, Object>> findByConfigKey (ConfigKey configKey,
      Direction direction)
  {
    if(configKey!=null){
      List<Filter> filters = new ArrayList<Filter>();
      List<Ordering> orderings = new ArrayList<Ordering>();
      Filter keyFilter = new Filter("configKey", Operator.eq, configKey);
      Filter enableFilter = new Filter("isEnabled",Operator.eq,true);
      filters.add(enableFilter);
      filters.add(keyFilter);
      if (direction!=null) {
        Ordering ordering = new Ordering();
        ordering.setProperty("configOrder");
        ordering.setDirection(direction);
        orderings.add(ordering);
     }
     List<PhysicalExaminationItemConfig> systemConfigs = super.findList(null, filters, orderings,true,null);
     if (systemConfigs == null || systemConfigs.size() == 0) {
       filters.clear();
       filters.add(keyFilter);
       systemConfigs = super.findList(null,filters,null,true,null);
       if (systemConfigs == null || systemConfigs.size() == 0) {
         filters.clear();
         filters.add(keyFilter);
         systemConfigs = super.findList(null, filters, orderings);
       }else {
         return null;
       }
     }
     String[] propertys =
       {"id", "configValue"};
     return FieldFilterUtils.filterCollectionMap(propertys, systemConfigs);
   }
   return null;
  }

}
