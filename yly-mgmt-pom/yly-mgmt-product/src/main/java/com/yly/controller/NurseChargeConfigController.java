package com.yly.controller;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yly.beans.Message;
import com.yly.controller.base.BaseController;
import com.yly.entity.NurseChargeConfig;
import com.yly.entity.SystemConfig;
import com.yly.framework.filter.Filter;
import com.yly.framework.filter.Filter.Operator;
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
  @RequestMapping(value = "/nurseChargeConfig", method = RequestMethod.GET)
  public String list(ModelMap model) {
    return "/nurseChargeConfig/nurseChargeConfig";
  }

  /**
   * 列表数据
   * @param pageable
   * @param model
   * @return
   */
  @RequestMapping(value = "/list", method = RequestMethod.POST)
  public @ResponseBody Page<NurseChargeConfig> list(Pageable pageable, ModelMap model) {
    return nurseChargeConfigService.findPage(pageable,true);
  }


  /**
   * 编辑页面
   * @param model
   * @param id
   * @return
   */
  @RequestMapping(value = "/edit", method = RequestMethod.GET)
  public String edit(ModelMap model, Long id) {
    model.addAttribute("nurseChargeConfig", nurseChargeConfigService.find(id));
    return "/nurseChargeConfig/edit";
  }
  
  /**
   *  添加
   * @param model
   * @param id
   * @return
   */
  @RequestMapping(value = "/add", method = RequestMethod.POST)
  public @ResponseBody Message add(NurseChargeConfig nurseChargeConfig, Long chargeItemId) {
    SystemConfig chargeItem = systemConfigService.find(chargeItemId);
    List<Filter> filters = new ArrayList<Filter>();
    Filter itemFilter = new Filter("chargeItem",Operator.eq,chargeItem);
    filters.add(itemFilter);
    List<NurseChargeConfig> nurseChargeConfigs = nurseChargeConfigService.findList(null,filters,null,true,null);
    if (nurseChargeConfigs!=null && nurseChargeConfigs.size()>0) {
       return Message.error("yly.nurseCharge.config.duplicate");
    }
    nurseChargeConfig.setChargeItem(chargeItem); 
    nurseChargeConfigService.save(nurseChargeConfig,true);
    return SUCCESS_MESSAGE;
  }

  /**
   * 更新
   * @return
   */
  @RequestMapping(value = "/update", method = RequestMethod.POST)
  public @ResponseBody Message update(NurseChargeConfig nurseChargeConfig) {
    nurseChargeConfigService.update(nurseChargeConfig,"chargeItem","tenantID");
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
