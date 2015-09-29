package com.yly.controller;


import java.util.List;

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
import com.yly.service.WaterElectricityChargeConfigService;

@Controller("waterElectricityChargeConfigController")
@RequestMapping("/console/waterElectricityChargeConfig")
public class WaterElectricityChargeConfigController extends BaseController {

  @Resource(name = "waterElectricityChargeConfigServiceImpl")
  private WaterElectricityChargeConfigService waterElectricityChargeConfigService;
  
  /**
   * 列表页面
   * @param model
   * @return
   */
  @RequestMapping(value = "/waterElectricityChargeConfig", method = RequestMethod.GET)
  public String list(ModelMap model) {
    return "/waterElectricityChargeConfig/waterElectricityChargeConfig";
  }

  /**
   * 列表数据
   * @param pageable
   * @param model
   * @return
   */
  @RequestMapping(value = "/list", method = RequestMethod.POST)
  public @ResponseBody Page<WaterElectricityChargeConfig> list(Pageable pageable, ModelMap model) {
    return waterElectricityChargeConfigService.findPage(pageable,true);
  }


  /**
   * 编辑页面
   * @param model
   * @param 
   * @return
   */
  @RequestMapping(value = "/edit", method = RequestMethod.GET)
  public String edit(ModelMap model, Long id) {
    model.addAttribute("waterElectricityChargeConfig", waterElectricityChargeConfigService.find(id));
    return "/waterElectricityChargeConfig/edit";
  }
  
  
  /**
   * 添加
   * @return
   */
  @RequestMapping(value = "/add", method = RequestMethod.POST)
  public @ResponseBody Message add(WaterElectricityChargeConfig waterElectricityChargeConfig) {
    List<WaterElectricityChargeConfig> waterElectricityChargeConfigs = waterElectricityChargeConfigService.findList(null, null, null, true, null);
    if (waterElectricityChargeConfigs!=null && waterElectricityChargeConfigs.size()>0) {
      return Message.error("yly.waterElectricityCharge.config.duplicate");
   }
    waterElectricityChargeConfigService.save(waterElectricityChargeConfig,true);
    return SUCCESS_MESSAGE;
  }
  
  /**
   * 更新
   * @return
   */
  @RequestMapping(value = "/update", method = RequestMethod.POST)
  public @ResponseBody Message update(WaterElectricityChargeConfig waterElectricityChargeConfig) {
    waterElectricityChargeConfigService.update(waterElectricityChargeConfig,"chargeItem","tenantID");
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
