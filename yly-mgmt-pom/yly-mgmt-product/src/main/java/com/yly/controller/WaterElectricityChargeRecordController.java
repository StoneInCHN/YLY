package com.yly.controller;


import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yly.controller.base.BaseController;
import com.yly.entity.WaterElectricityCharge;
import com.yly.framework.paging.Page;
import com.yly.framework.paging.Pageable;
import com.yly.service.WaterElectricityChargeService;

@Controller("waterElectricityChargeRecordController")
@RequestMapping("/console/waterElectricityChargeRecord")
public class WaterElectricityChargeRecordController extends BaseController {

  @Resource(name = "waterElectricityChargeServiceImpl")
  private WaterElectricityChargeService waterElectricityChargeService;
 
  
  /**
   * 列表页面
   * @param model
   * @return
   */
  @RequestMapping(value = "/waterElectricityChargeRecord", method = RequestMethod.GET)
  public String waterElectricityChargeRecord(ModelMap model) {
    return "/waterElectricityChargeRecord/waterElectricityChargeRecord";
  }

  /**
   * 列表数据
   * @param pageable
   * @param model
   * @return
   */
  @RequestMapping(value = "/list", method = RequestMethod.POST)
  public @ResponseBody Page<WaterElectricityCharge> list(Date beginDate, Date endDate,String realName,String identifier,Pageable pageable, ModelMap model) {
    return waterElectricityChargeService.findPage(pageable,true);
  }
  
  /**
   * 获取数据进入详情页面
   * @param model
   * @param id
   * @return
   */
  @RequestMapping(value = "/details", method = RequestMethod.GET)
  public String details(ModelMap model, Long id) {
    WaterElectricityCharge record =  waterElectricityChargeService.find(id);
    model.addAttribute("waterElectricityCharge", record);
    return "waterElectricityChargeRecord/details";
  }


}
