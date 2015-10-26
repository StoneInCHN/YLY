package com.yly.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
import com.yly.utils.DateTimeUtils;
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
      Filter enableFilter = new Filter("isEnabled",Operator.eq,true);
      filters.add(enableFilter);
      filters.add(keyFilter);
      if (direction!=null) {
        Ordering ordering = new Ordering();
        ordering.setProperty("configOrder");
        ordering.setDirection(direction);
        orderings.add(ordering);
     }
     List<SystemConfig> systemConfigs = super.findList(null, filters, orderings,true,null);
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
  
  @Override
  public Map<String,Object> getBillingDate(Date currentDate) {
	  Map<String,Object> map = new HashMap<String, Object>();
    List<Map<String, Object>> dayList = findByConfigKey(ConfigKey.BILLDAY, null);
    int billDay=0;
    if(dayList!= null && dayList.size() ==1){
      billDay = Integer.parseInt(dayList.get(0).get("configValue").toString());
      Date billDate = DateTimeUtils.getBillDate(currentDate, billDay);
      map.put("billDate",DateTimeUtils.convertDateToString(billDate, "yyyy-MM-dd"));//账单结算日期
      map.put("periodMonth",1);//最少缴费时间段为1个月
      map.put("periodDay",DateTimeUtils.getBillDays(currentDate, billDay));//结算时需单独计算的天数
      return map;
    }
    return null;
  }

}
