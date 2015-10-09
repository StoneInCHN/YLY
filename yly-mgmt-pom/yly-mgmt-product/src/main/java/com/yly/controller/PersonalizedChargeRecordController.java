package com.yly.controller;


import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yly.controller.base.BaseController;
import com.yly.entity.PersonalizedCharge;
import com.yly.framework.paging.Page;
import com.yly.framework.paging.Pageable;
import com.yly.service.PersonalizedChargeService;

@Controller("personalizedChargeRecordController")
@RequestMapping("/console/personalizedChargeRecord")
public class PersonalizedChargeRecordController extends BaseController {

  @Resource(name = "personalizedChargeServiceImpl")
  private PersonalizedChargeService personalizedChargeService;
 
  
  /**
   * 列表页面
   * @param model
   * @return
   */
  @RequestMapping(value = "/personalizedChargeRecord", method = RequestMethod.GET)
  public String personalizedChargeRecord(ModelMap model) {
    return "/personalizedChargeRecord/personalizedChargeRecord";
  }

  /**
   * 列表数据
   * @param pageable
   * @param model
   * @return
   */
  @RequestMapping(value = "/list", method = RequestMethod.POST)
  public @ResponseBody Page<PersonalizedCharge> list(Date beginDate, Date endDate,String realName,String identifier,Pageable pageable, ModelMap model) {
    return personalizedChargeService.findPage(pageable, true);
  }
  
  /**
   * 获取数据进入详情页面
   * @param model
   * @param id
   * @return
   */
  @RequestMapping(value = "/details", method = RequestMethod.GET)
  public String details(ModelMap model, Long id) {
    PersonalizedCharge record =  personalizedChargeService.find(id);
    model.addAttribute("personalizedCharge", record);
    return "personalizedChargeRecord/details";
  }


}
