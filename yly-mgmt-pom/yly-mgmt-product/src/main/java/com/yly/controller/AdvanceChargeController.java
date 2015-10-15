package com.yly.controller;


import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yly.beans.Message;
import com.yly.common.log.LogUtil;
import com.yly.controller.base.BaseController;
import com.yly.entity.AdvanceCharge;
import com.yly.entity.ElderlyInfo;
import com.yly.entity.commonenum.CommonEnum.BudgetType;
import com.yly.framework.paging.Page;
import com.yly.framework.paging.Pageable;
import com.yly.service.AdvanceChargeService;
import com.yly.service.ElderlyInfoService;
import com.yly.utils.FieldFilterUtils;

@Controller("advanceChargeController")
@RequestMapping("/console/advanceCharge")
public class AdvanceChargeController extends BaseController {

  @Resource(name = "advanceChargeServiceImpl")
  private AdvanceChargeService advanceChargeService;

  @Resource(name = "elderlyInfoServiceImpl")
  private ElderlyInfoService elderlyInfoService;


  /**
   * 列表页面
   * 
   * @param model
   * @return
   */
  @RequestMapping(value = "/advanceCharge", method = RequestMethod.GET)
  public String advanceChargeRecord(ModelMap model) {
    return "/advanceCharge/advanceCharge";
  }

  /**
   * 预存款账户列表
   * 
   * @param realName
   * @param identifier
   * @param pageable
   * @param model
   * @return
   */
  @RequestMapping(value = "/chargeAccounts", method = RequestMethod.POST)
  public @ResponseBody Page<Map<String, Object>> accountList(String realName, String identifier,
      Pageable pageable, ModelMap model) {
    Page<ElderlyInfo> page = new Page<ElderlyInfo>();
    if (realName == null && identifier == null) {
      page = elderlyInfoService.findPage(pageable, true);
    } else {
      if (LogUtil.isDebugEnabled(AdvanceChargeController.class)) {
        LogUtil.debug(AdvanceChargeController.class, "search", "elderlyName: " + realName
            + ",identifier: " + identifier);
      }
      page = elderlyInfoService.elderlyInfoSearch(realName, identifier, pageable);
    }

    String[] properties =
        {"id", "name", "identifier", "bedLocation", "nursingLevel", "advanceChargeAmount"};

    List<Map<String, Object>> rows =
        FieldFilterUtils.filterCollectionMap(properties, page.getRows());

    Page<Map<String, Object>> filteredPage =
        new Page<Map<String, Object>>(rows, page.getTotal(), pageable);

    return filteredPage;
  }

  /**
   * 预存款收支明细列表数据
   * 
   * @param pageable
   * @param model
   * @return
   */
  @RequestMapping(value = "/chargeList", method = RequestMethod.POST)
  public @ResponseBody Page<Map<String, Object>> list(Date beginDate, Date endDate,
      String realName, String identifier, BudgetType budgetType, Pageable pageable, ModelMap model) {
    Page<AdvanceCharge> page = new Page<AdvanceCharge>();
    if (realName == null && identifier == null && beginDate == null && endDate == null
        && budgetType == null) {
      page = advanceChargeService.findPage(pageable, true);
    } else {
      if (LogUtil.isDebugEnabled(AdvanceChargeController.class)) {
        LogUtil.debug(AdvanceChargeController.class, "search", "elderlyName: " + realName
            + ",identifier: " + identifier + "" + ", budgetType: " + budgetType + ", start date: "
            + beginDate + ", end date: " + endDate);
      }
      page =
          advanceChargeService.chargeRecordSearch(beginDate, endDate, realName, identifier, null,
              budgetType, false, pageable);
    }


    String[] properties =
        {"id", "elderlyInfo.name", "advanceAmount", "budgetType", "payTime", "operator"};

    List<Map<String, Object>> rows =
        FieldFilterUtils.filterCollectionMap(properties, page.getRows());

    Page<Map<String, Object>> filteredPage =
        new Page<Map<String, Object>>(rows, page.getTotal(), pageable);

    return filteredPage;
  }


  /**
   * 获取数据进入详情页面
   * 
   * @param model
   * @param id
   * @return
   */
  @RequestMapping(value = "/details", method = RequestMethod.GET)
  public String details(ModelMap model, Long id) {
    AdvanceCharge record = advanceChargeService.find(id);
    model.addAttribute("advanceCharge", record);
    return "advanceCharge/details";
  }

 
  @RequestMapping(value = "/add", method = RequestMethod.POST)
  public @ResponseBody Message add() {
    return SUCCESS_MESSAGE;
  }

}
