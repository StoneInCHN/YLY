package com.yly.controller;


import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yly.controller.base.BaseController;
import com.yly.entity.BedNurseCharge;
import com.yly.framework.paging.Page;
import com.yly.framework.paging.Pageable;
import com.yly.service.BedNurseChargeService;

@Controller("bedNurseChargeRecordController")
@RequestMapping("/console/bedNurseChargeRecord")
public class BedNurseChargeRecordController extends BaseController {

  @Resource(name = "bedNurseChargeServiceImpl")
  private BedNurseChargeService bedNurseChargeService;
 
  
  /**
   * 列表页面
   * @param model
   * @return
   */
  @RequestMapping(value = "/bedNurseChargeRecord", method = RequestMethod.GET)
  public String bedNurseChargeRecord(ModelMap model) {
    return "/bedNurseChargeRecord/bedNurseChargeRecord";
  }

  /**
   * 列表数据
   * @param pageable
   * @param model
   * @return
   */
  @RequestMapping(value = "/list", method = RequestMethod.POST)
  public @ResponseBody Page<BedNurseCharge> list(Date beginDate, Date endDate,String realName,String identifier,Pageable pageable, ModelMap model) {
    return bedNurseChargeService.findPage(pageable,true);
  }

}
