package com.yly.controller;


import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yly.beans.Message;
import com.yly.controller.base.BaseController;
import com.yly.entity.WaterElectricityChargeConfig;
import com.yly.framework.paging.Page;
import com.yly.framework.paging.Pageable;
import com.yly.service.SystemConfigService;
import com.yly.service.WaterElectricityChargeConfigService;

@Controller("waterElectricityChargeConfigController")
@RequestMapping("/console/waterElectricityChargeConfig")
public class WaterElectricityChargeConfigController extends BaseController {

  @Resource(name = "waterElectricityChargeConfigServiceImpl")
  private WaterElectricityChargeConfigService waterElectricityChargeConfigService;
  
  @Resource(name = "systemConfigServiceImpl")
  private SystemConfigService systemConfigService;
  
  /**
   * 列表页面
   * @param model
   * @return
   */
  @RequestMapping(value = "/main", method = RequestMethod.GET)
  public String list(ModelMap model) {
    return "/waterElectricityChargeConfig/list";
  }

  /**
   * 列表数据
   * @param pageable
   * @param model
   * @return
   */
  @RequestMapping(value = "/list", method = RequestMethod.POST)
  public @ResponseBody Page<WaterElectricityChargeConfig> list(Pageable pageable, ModelMap model) {
    
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
    
    
    return waterElectricityChargeConfigService.findPage(pageable);
  }


  /**
   * 编辑页面
   * @param model
   * @param vendorId
   * @return
   */
  @RequestMapping(value = "/edit", method = RequestMethod.GET)
  public String edit(ModelMap model, Long id) {
    model.addAttribute("billing", waterElectricityChargeConfigService.find(id));
    return "/waterElectricityChargeConfig/edit";
  }
  
  /**
   *  添加页面
   * @param model
   * @param id
   * @return
   */
  @RequestMapping(value = "/add", method = RequestMethod.GET)
  public String add(ModelMap model, Long id) {
    return "/waterElectricityChargeConfig/add";
  }

  /**
   * 保存
   * @return
   */
  @RequestMapping(value = "/save", method = RequestMethod.POST)
  public @ResponseBody Message save(WaterElectricityChargeConfig waterElectricityChargeConfig) {
    waterElectricityChargeConfigService.save(waterElectricityChargeConfig);
    return SUCCESS_MESSAGE;
  }
  
  /**
   * 更新
   * @return
   */
  @RequestMapping(value = "/update", method = RequestMethod.POST)
  public @ResponseBody Message update(WaterElectricityChargeConfig waterElectricityChargeConfig) {
    waterElectricityChargeConfigService.update(waterElectricityChargeConfig);
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
      waterElectricityChargeConfigService.delete(ids);
    }
    return SUCCESS_MESSAGE;
  }

}
