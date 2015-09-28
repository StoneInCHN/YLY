package com.yly.controller;


import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yly.beans.Message;
import com.yly.controller.base.BaseController;
import com.yly.entity.NurseChargeConfig;
import com.yly.framework.paging.Page;
import com.yly.framework.paging.Pageable;
import com.yly.service.NurseChargeConfigService;
import com.yly.service.SystemConfigService;

@Controller("nurseChargeConfigController")
@RequestMapping("/console/nurseChargeConfig")
public class NurseChargeConfigController extends BaseController {

  @Resource(name = "nurseChargeConfigServiceImpl")
  private NurseChargeConfigService nurseChargeConfigService;
  
  @Resource(name = "systemConfigServiceImpl")
  private SystemConfigService systemConfigService;
  
  /**
   * 列表页面
   * @param model
   * @return
   */
  @RequestMapping(value = "/main", method = RequestMethod.GET)
  public String list(ModelMap model) {
    return "/nurseChargeConfig/list";
  }

  /**
   * 列表数据
   * @param pageable
   * @param model
   * @return
   */
  @RequestMapping(value = "/list", method = RequestMethod.POST)
  public @ResponseBody Page<NurseChargeConfig> list(Pageable pageable, ModelMap model) {
    
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
    
    
    return nurseChargeConfigService.findPage(pageable);
  }


  /**
   * 编辑页面
   * @param model
   * @param vendorId
   * @return
   */
  @RequestMapping(value = "/edit", method = RequestMethod.GET)
  public String edit(ModelMap model, Long id) {
    model.addAttribute("billing", nurseChargeConfigService.find(id));
    return "/nurseChargeConfig/edit";
  }
  
  /**
   *  添加页面
   * @param model
   * @param id
   * @return
   */
  @RequestMapping(value = "/add", method = RequestMethod.GET)
  public String add(ModelMap model, Long id) {
    return "/nurseChargeConfig/add";
  }

  /**
   * 保存
   * @return
   */
  @RequestMapping(value = "/save", method = RequestMethod.POST)
  public @ResponseBody Message save(NurseChargeConfig nurseChargeConfig) {
    nurseChargeConfigService.save(nurseChargeConfig);
    return SUCCESS_MESSAGE;
  }
  
  /**
   * 更新
   * @return
   */
  @RequestMapping(value = "/update", method = RequestMethod.POST)
  public @ResponseBody Message update(NurseChargeConfig nurseChargeConfig) {
    nurseChargeConfigService.update(nurseChargeConfig);
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
      nurseChargeConfigService.delete(ids);
    }
    return SUCCESS_MESSAGE;
  }

}
