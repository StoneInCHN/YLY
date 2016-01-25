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
import com.yly.entity.PersonalizedChargeConfig;
import com.yly.framework.filter.Filter;
import com.yly.framework.filter.Filter.Operator;
import com.yly.framework.paging.Page;
import com.yly.framework.paging.Pageable;
import com.yly.json.response.PersonalizedChargeConfigResponse;
import com.yly.service.PersonalizedChargeConfigService;

@Controller("personalizedChargeConfigController")
@RequestMapping("/console/personalizedChargeConfig")
public class PersonalizedChargeConfigController extends BaseController {

  @Resource(name = "personalizedChargeConfigServiceImpl")
  private PersonalizedChargeConfigService personalizedChargeConfigService;


  /**
   * 列表页面
   * 
   * @param model
   * @return
   */
  @RequestMapping(value = "/personalizedChargeConfig", method = RequestMethod.GET)
  public String list(ModelMap model) {
    return "/personalizedChargeConfig/personalizedChargeConfig";
  }

  /**
   * 列表数据
   * 
   * @param pageable
   * @param model
   * @return
   */
  @RequestMapping(value = "/list", method = RequestMethod.POST)
  public @ResponseBody Page<PersonalizedChargeConfig> list(Pageable pageable, ModelMap model) {

    return personalizedChargeConfigService.findPage(pageable, true);
  }


  /**
   * 编辑页面
   * 
   * @param model
   * @param
   * @return
   */
  @RequestMapping(value = "/edit", method = RequestMethod.GET)
  public String edit(ModelMap model, Long id) {
    model.addAttribute("personalizedChargeConfig", personalizedChargeConfigService.find(id));
    return "/personalizedChargeConfig/edit";
  }

  /**
   * 添加
   * 
   * @return
   */
  @RequestMapping(value = "/add", method = RequestMethod.POST)
  public @ResponseBody Message add(PersonalizedChargeConfig personalizedChargeConfig) {
    List<Filter> filters = new ArrayList<Filter>();
    Filter itemFilter =
        new Filter("chargeItem", Operator.eq, personalizedChargeConfig.getChargeItem());
    filters.add(itemFilter);
    List<PersonalizedChargeConfig> personalizedChargeConfigs =
        personalizedChargeConfigService.findList(null, filters, null, true, null);
    if (personalizedChargeConfigs != null && personalizedChargeConfigs.size() > 0) {
      return Message.error("yly.personalizedCharge.config.duplicate");
    }
    personalizedChargeConfigService.save(personalizedChargeConfig, true);
    return SUCCESS_MESSAGE;
  }

  /**
   * 更新
   * 
   * @return
   */
  @RequestMapping(value = "/update", method = RequestMethod.POST)
  public @ResponseBody Message update(PersonalizedChargeConfig personalizedChargeConfig) {
    personalizedChargeConfigService.update(personalizedChargeConfig, "chargeItem", "tenantID");
    return SUCCESS_MESSAGE;
  }

  /**
   * 删除
   * 
   * @param ids
   * @return
   */
  @RequestMapping(value = "/delete", method = RequestMethod.POST)
  public @ResponseBody Message delete(Long[] ids) {
    if (ids != null) {
      // 检查是否能被删除
      // if()
      personalizedChargeConfigService.delete(ids);
    }
    return SUCCESS_MESSAGE;
  }

  @RequestMapping(value = "/findAll", method = RequestMethod.GET)
  public @ResponseBody List<PersonalizedChargeConfigResponse> findAll() {
    List<PersonalizedChargeConfigResponse> responses =
        new ArrayList<PersonalizedChargeConfigResponse>();
    List<PersonalizedChargeConfig> lists = personalizedChargeConfigService.findAll(true);
    if (lists.size() > 0) {
      for (PersonalizedChargeConfig personalizedChargeConfig : lists) {
        PersonalizedChargeConfigResponse configResponse = new PersonalizedChargeConfigResponse();
        configResponse.setId(personalizedChargeConfig.getId());
        configResponse.setText(personalizedChargeConfig.getChargeItem());
        responses.add(configResponse);
      }
    }
    return responses;
  }

}
