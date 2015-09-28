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
import com.yly.entity.BedChargeConfig;
import com.yly.entity.SystemConfig;
import com.yly.framework.filter.Filter;
import com.yly.framework.filter.Filter.Operator;
import com.yly.framework.paging.Page;
import com.yly.framework.paging.Pageable;
import com.yly.service.BedChargeConfigService;
import com.yly.service.SystemConfigService;

@Controller("bedChargeConfigController")
@RequestMapping("/console/bedChargeConfig")
public class BedChargeConfigController extends BaseController {

  @Resource(name = "bedChargeConfigServiceImpl")
  private BedChargeConfigService bedChargeConfigService;
  
  @Resource(name = "systemConfigServiceImpl")
  private SystemConfigService systemConfigService;
  
  /**
   * 列表页面
   * @param model
   * @return
   */
  @RequestMapping(value = "/bedChargeConfig", method = RequestMethod.GET)
  public String list(ModelMap model) {
    return "/bedChargeConfig/bedChargeConfig";
  }

  /**
   * 列表数据
   * @param pageable
   * @param model
   * @return
   */
  @RequestMapping(value = "/list", method = RequestMethod.POST)
  public @ResponseBody Page<BedChargeConfig> list(Pageable pageable, ModelMap model) {
    
    return bedChargeConfigService.findPage(pageable,true);
  }


  /**
   * 编辑页面
   * @param model
   * @param vendorId
   * @return
   */
  @RequestMapping(value = "/edit", method = RequestMethod.GET)
  public String edit(ModelMap model, Long id) {
    model.addAttribute("bedChargeConfig", bedChargeConfigService.find(id));
    return "/bedChargeConfig/edit";
  }
  


  /**
   * 添加
   * @return
   */
  @RequestMapping(value = "/add", method = RequestMethod.POST)
  public @ResponseBody Message add(BedChargeConfig bedChargeConfig,Long chargeItemId) {
    SystemConfig chargeItem = systemConfigService.find(chargeItemId);
    List<Filter> filters = new ArrayList<Filter>();
    Filter itemFilter = new Filter("chargeItem",Operator.eq,chargeItem);
    filters.add(itemFilter);
    List<BedChargeConfig> bedChargeConfigs = bedChargeConfigService.findList(null,filters,null,true,null);
    if (bedChargeConfigs!=null && bedChargeConfigs.size()>0) {
       return Message.error("yly.bedCharge.config.duplicate");
    }
    bedChargeConfig.setChargeItem(chargeItem); 
    bedChargeConfigService.save(bedChargeConfig,true);
    return SUCCESS_MESSAGE;
  }
  
  /**
   * 更新
   * @return
   */
  @RequestMapping(value = "/update", method = RequestMethod.POST)
  public @ResponseBody Message update(BedChargeConfig bedChargeConfig) {
    bedChargeConfigService.update(bedChargeConfig, "chargeItem","tenantID");
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
      //if()
      bedChargeConfigService.delete(ids);
    }
    return SUCCESS_MESSAGE;
  }

}
