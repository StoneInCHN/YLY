package com.yly.controller;


import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yly.controller.base.BaseController;
import com.yly.entity.MealCharge;
import com.yly.framework.paging.Page;
import com.yly.framework.paging.Pageable;
import com.yly.service.MealChargeService;

@Controller("mealChargeRecordController")
@RequestMapping("/console/mealChargeRecord")
public class MealChargeRecordController extends BaseController {

  @Resource(name = "mealChargeServiceImpl")
  private MealChargeService mealChargeService;
 
  
  /**
   * 列表页面
   * @param model
   * @return
   */
  @RequestMapping(value = "/mealChargeRecord", method = RequestMethod.GET)
  public String mealChargeRecord(ModelMap model) {
    return "/mealChargeRecord/mealChargeRecord";
  }

  /**
   * 列表数据
   * @param pageable
   * @param model
   * @return
   */
  @RequestMapping(value = "/list", method = RequestMethod.POST)
  public @ResponseBody Page<MealCharge> list(Date beginDate, Date endDate,String realName,String identifier,Pageable pageable, ModelMap model) {
    return mealChargeService.findPage(pageable,true);
  }

}
