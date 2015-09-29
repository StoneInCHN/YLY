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
import com.yly.entity.MealChargeConfig;
import com.yly.entity.SystemConfig;
import com.yly.framework.filter.Filter;
import com.yly.framework.filter.Filter.Operator;
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
  @RequestMapping(value = "/mealChargeConfig", method = RequestMethod.GET)
  public String mealChargeConfig(ModelMap model) {
    return "/mealChargeConfig/mealChargeConfig";
  }

  /**
   * 列表数据
   * @param pageable
   * @param model
   * @return
   */
  @RequestMapping(value = "/list", method = RequestMethod.POST)
  public @ResponseBody Page<MealChargeConfig> list(Pageable pageable, ModelMap model) {
    
    return mealChargeConfigService.findPage(pageable,true);
  }


  /**
   * 编辑页面
   * @param model
   * @param vendorId
   * @return
   */
  @RequestMapping(value = "/edit", method = RequestMethod.GET)
  public String edit(ModelMap model, Long id) {
    model.addAttribute("mealChargeConfig", mealChargeConfigService.find(id));
    return "/mealChargeConfig/edit";
  }
  

  /**
   * 添加
   * @return
   */
  @RequestMapping(value = "/add", method = RequestMethod.POST)
  public @ResponseBody Message add(MealChargeConfig mealChargeConfig,Long chargeItemId) {
    SystemConfig chargeItem = systemConfigService.find(chargeItemId);
    List<Filter> filters = new ArrayList<Filter>();
    Filter itemFilter = new Filter("chargeItem",Operator.eq,chargeItem);
    filters.add(itemFilter);
    List<MealChargeConfig> mealChargeConfigs = mealChargeConfigService.findList(null,filters,null,true,null);
    if (mealChargeConfigs!=null && mealChargeConfigs.size()>0) {
       return Message.error("yly.mealCharge.config.duplicate");
    }
    mealChargeConfig.setChargeItem(chargeItem); 
    mealChargeConfigService.save(mealChargeConfig,true);
    return SUCCESS_MESSAGE;
  }
  
  /**
   * 更新
   * @return
   */
  @RequestMapping(value = "/update", method = RequestMethod.POST)
  public @ResponseBody Message update(MealChargeConfig mealChargeConfig) {
    mealChargeConfigService.update(mealChargeConfig,"chargeItem","tenantID");
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
