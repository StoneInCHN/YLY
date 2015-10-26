package com.yly.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yly.controller.base.BaseController;
import com.yly.entity.RepairRecord;
import com.yly.framework.paging.Page;
import com.yly.service.RepairRecordService;
import com.yly.service.TenantAccountService;

/**
 * Controller 维修记录
 * @author pengyanan
 *
 */
@Controller("repairRecordController")
@RequestMapping(value="console/repairRecord")
public class RepairRecordController extends BaseController{

  @Resource(name="repairRecordServiceImpl")
  private RepairRecordService repairRecordService;
  
  @Resource(name="tenantAccountServiceImpl")
  private TenantAccountService tenantAccountService;
  
  @RequestMapping(value = "/repairRecord", method = RequestMethod.GET)
  public String list(ModelMap model) {
    return "/repairRecord/repairRecord";
  }
  
  public Page<RepairRecord> list(){
    return null;
  }
  
}
