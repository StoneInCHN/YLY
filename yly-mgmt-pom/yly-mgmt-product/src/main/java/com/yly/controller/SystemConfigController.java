package com.yly.controller;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yly.beans.Message;
import com.yly.controller.base.BaseController;
import com.yly.entity.SystemConfig;
import com.yly.entity.commonenum.CommonEnum.ConfigKey;
import com.yly.framework.filter.Filter;
import com.yly.framework.filter.Filter.Operator;
import com.yly.framework.ordering.Ordering;
import com.yly.framework.ordering.Ordering.Direction;
import com.yly.framework.paging.Page;
import com.yly.framework.paging.Pageable;
import com.yly.service.SystemConfigService;
import com.yly.utils.FieldFilterUtils;

@Controller("systemConfigController")
@RequestMapping("/console/systemConfig")
public class SystemConfigController extends BaseController {

  @Resource(name = "systemConfigServiceImpl")
  private SystemConfigService systemConfigService;
  
  /**
   * 列表页面
   * @param model
   * @return
   */
  @RequestMapping(value = "/main", method = RequestMethod.GET)
  public String list(ModelMap model) {
    return "/systemConfig/list";
  }

  /**
   * 列表数据
   * @param beginDate
   * @param endDate
   * @param pageable
   * @param model
   * @return
   */
  @RequestMapping(value = "/list", method = RequestMethod.POST)
  public @ResponseBody Page<SystemConfig> list(Date beginDate,Date endDate, Pageable pageable, ModelMap model) {
    
  /*  List<Filter> filters = new ArrayList<Filter>();
    if(beginDate !=null){
      Filter beginDateFilter = new Filter("createDate", Operator.gt, beginDate);
      filters.add(beginDateFilter);
    }
    if(endDate!= null){
      Filter endDateFilter = new Filter("createDate", Operator.lt, endDate);
      filters.add(endDateFilter);
    }
    pageable.setFilters(filters);*/
    
    
    return systemConfigService.findPage(pageable);
  }

  
  /**
   * 根据configKey查询
   * @param configKey
   * @param direction
   * @return
   */
  @RequestMapping(value = "/findByConfigKey", method = RequestMethod.POST)
  public @ResponseBody List<Map<String, Object>> findByConfigKey(ConfigKey configKey,Direction direction) {
   
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
      List<SystemConfig> systemConfigs = systemConfigService.findList(null, filters, orderings,true,null);
      if (systemConfigs == null || systemConfigs.size() == 0) {
        filters.clear();
        filters.add(keyFilter);
        systemConfigs = systemConfigService.findList(null,filters,null,true,null);
        if (systemConfigs == null || systemConfigs.size() == 0) {
          filters.clear();
          filters.add(keyFilter);
          systemConfigs = systemConfigService.findList(null, filters, orderings);
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
  /**
   * 编辑页面
   * @param model
   * @param vendorId
   * @return
   */
  @RequestMapping(value = "/edit", method = RequestMethod.GET)
  public String edit(ModelMap model, Long id) {
    model.addAttribute("systemConfig", systemConfigService.find(id));
    return "/systemConfig/edit";
  }
  
  /**
   *  添加页面
   * @param model
   * @param id
   * @return
   */
  @RequestMapping(value = "/add", method = RequestMethod.GET)
  public String add(ModelMap model, Long id) {
    return "/systemConfig/add";
  }

  /**
   * 保存
   * @return
   */
  @RequestMapping(value = "/save", method = RequestMethod.POST)
  public @ResponseBody Message save(SystemConfig systemConfig) {
    systemConfigService.save(systemConfig);
    return SUCCESS_MESSAGE;
  }
  
  /**
   * 更新
   * @return
   */
  @RequestMapping(value = "/update", method = RequestMethod.POST)
  public @ResponseBody Message update(SystemConfig systemConfig) {
    systemConfigService.update(systemConfig);
    return SUCCESS_MESSAGE;
  }
  
  /**
   * 删除
   * @param ids
   * @return
   */
  @RequestMapping(value = "/delete", method = RequestMethod.POST)
  public @ResponseBody Message delete(Long[] ids) {
    if (ids != null) {
      // 检查是否能被删除
      // if()
      systemConfigService.delete(ids);
    }
    return SUCCESS_MESSAGE;
  }

}
