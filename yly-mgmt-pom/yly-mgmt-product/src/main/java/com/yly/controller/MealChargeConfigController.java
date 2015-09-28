package com.yly.controller;


import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yly.beans.Message;
import com.yly.controller.base.BaseController;
import com.yly.entity.MealChargeConfig;
import com.yly.framework.paging.Page;
import com.yly.framework.paging.Pageable;
import com.yly.service.MealChargeConfigService;
import com.yly.service.SystemConfigService;

@Controller("mealChargeConfigController")
@RequestMapping("/console/mealChargeConfig")
public class MealChargeConfigController extends BaseController {

  @Resource(name = "mealChargeConfigServiceImpl")
  private MealChargeConfigService mealChargeConfigService;
  
  @Resource(name = "systemConfigServiceImpl")
  private SystemConfigService systemConfigService;
  
  /**
   * 列表页面
   * @param model
   * @return
   */
  @RequestMapping(value = "/main", method = RequestMethod.GET)
  public String list(ModelMap model) {
    return "/mealChargeConfig/list";
  }

  /**
   * 列表数据
   * @param pageable
   * @param model
   * @return
   */
  @RequestMapping(value = "/list", method = RequestMethod.POST)
  public @ResponseBody Page<MealChargeConfig> list(Pageable pageable, ModelMap model) {
    
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
    
    
    return mealChargeConfigService.findPage(pageable);
  }


  /**
   * 编辑页面
   * @param model
   * @param vendorId
   * @return
   */
  @RequestMapping(value = "/edit", method = RequestMethod.GET)
  public String edit(ModelMap model, Long id) {
    model.addAttribute("billing", mealChargeConfigService.find(id));
    return "/mealChargeConfig/edit";
  }
  
  /**
   *  添加页面
   * @param model
   * @param id
   * @return
   */
  @RequestMapping(value = "/add", method = RequestMethod.GET)
  public String add(ModelMap model, Long id) {
    return "/mealChargeConfig/add";
  }

  /**
   * 保存
   * @return
   */
  @RequestMapping(value = "/save", method = RequestMethod.POST)
  public @ResponseBody Message save(MealChargeConfig mealChargeConfig) {
    mealChargeConfigService.save(mealChargeConfig);
    return SUCCESS_MESSAGE;
  }
  
  /**
   * 更新
   * @return
   */
  @RequestMapping(value = "/update", method = RequestMethod.POST)
  public @ResponseBody Message update(MealChargeConfig mealChargeConfig) {
    mealChargeConfigService.update(mealChargeConfig);
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
      mealChargeConfigService.delete(ids);
    }
    return SUCCESS_MESSAGE;
  }

}
