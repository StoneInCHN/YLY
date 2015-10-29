package com.yly.controller;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yly.beans.Message;
import com.yly.controller.base.BaseController;
import com.yly.entity.RepairRecord;
import com.yly.framework.paging.Page;
import com.yly.framework.paging.Pageable;
import com.yly.service.RepairRecordService;
import com.yly.service.TenantAccountService;

/**
 * Controller 维修记录
 * 
 * @author pengyanan
 *
 */
@Controller("repairRecordController")
@RequestMapping(value = "console/repairRecord")
public class RepairRecordController extends BaseController {

  @Resource(name = "repairRecordServiceImpl")
  private RepairRecordService repairRecordService;

  @Resource(name = "tenantAccountServiceImpl")
  private TenantAccountService tenantAccountService;

  /**
   * 维修记录页面
   * 
   * @param model
   * @return
   */
  @RequestMapping(value = "/repairRecord", method = RequestMethod.GET)
  public String list(ModelMap model) {
    return "/repairRecord/repairRecord";
  }

  /**
   * 维修记录列表
   * 
   * @param pageable
   * @return
   */
  @RequestMapping(value = "/list", method = RequestMethod.POST)
  public @ResponseBody Page<RepairRecord> list(RepairRecord repairRecord, Date begainDate,
      Date endDate, Pageable pageable) {
    if (repairRecord.getRepairContent() != null || repairRecord.getRepairPlace() != null
        || begainDate != null || endDate != null) {
      return repairRecordService.searchListByFilter(pageable, begainDate, endDate, repairRecord);
    }
    return repairRecordService.findPage(pageable);
  }

  /**
   * 添加维修记录
   * 
   * @param repairRecord
   * @return
   */
  @RequestMapping(value = "/add", method = RequestMethod.POST)
  public @ResponseBody Message add(RepairRecord repairRecord) {
    if (repairRecord != null) {
      repairRecord.setTenantID(tenantAccountService.getCurrentTenantID());
      repairRecordService.save(repairRecord);
    }
    return SUCCESS_MESSAGE;
  }

  /**
   * 详情
   * 
   * @param model
   * @param id
   * @return
   */
  @RequestMapping(value = "/details", method = RequestMethod.GET)
  public String detail(ModelMap model, Long id) {
    RepairRecord repairRecord = repairRecordService.find(id);
    if (repairRecord != null) {
      model.addAttribute("repairRecord", repairRecord);
    }
    return "/repairRecord/details";
  }

  /**
   * 编辑页面
   * 
   * @param model
   * @param id
   * @return
   */
  @RequestMapping(value = "/edit", method = RequestMethod.GET)
  public String edit(ModelMap model, Long id) {
    RepairRecord repairRecord = repairRecordService.find(id);
    if (repairRecord != null) {
      model.addAttribute("repairRecord", repairRecord);
    }
    return "/repairRecord/edit";
  }

  /**
   * 更新页面
   * 
   * @param repairRecord
   * @return
   */
  @RequestMapping(value = "/update", method = RequestMethod.POST)
  public @ResponseBody Message update(RepairRecord repairRecord) {
    if (repairRecord != null) {
      repairRecordService.update(repairRecord);
    }
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
      repairRecordService.delete(ids);
    }
    return SUCCESS_MESSAGE;
  }
}
