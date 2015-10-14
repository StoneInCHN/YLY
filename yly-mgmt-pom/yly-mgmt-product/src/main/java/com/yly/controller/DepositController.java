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

import com.yly.common.log.LogUtil;
import com.yly.controller.base.BaseController;
import com.yly.entity.Deposit;
import com.yly.entity.commonenum.CommonEnum.PaymentStatus;
import com.yly.framework.paging.Page;
import com.yly.framework.paging.Pageable;
import com.yly.service.DepositService;
import com.yly.utils.FieldFilterUtils;

@Controller("depositController")
@RequestMapping("/console/deposit")
public class DepositController extends BaseController {

  @Resource(name = "depositServiceImpl")
  private DepositService depositService;


  /**
   * 列表页面
   * 
   * @param model
   * @return
   */
  @RequestMapping(value = "/deposit", method = RequestMethod.GET)
  public String depositChargeRecord(ModelMap model) {
    return "/deposit/deposit";
  }

  /**
   * 列表数据
   * 
   * @param pageable
   * @param model
   * @return
   */
  @RequestMapping(value = "/list", method = RequestMethod.POST)
  public @ResponseBody Page<Map<String, Object>> list(Date beginDate, Date endDate,
      String realName, String identifier,PaymentStatus status, Pageable pageable, ModelMap model) {
    Page<Deposit> page = new Page<Deposit>();
    if (realName == null && identifier == null && beginDate == null && endDate == null && status == null) {
      page = depositService.findPage(pageable, true);
    } else {
      if (LogUtil.isDebugEnabled(DepositController.class)) {
        LogUtil.debug(DepositController.class, "search", "elderlyName: " + realName
            +",identifier: " + identifier + "" + ",status: " + status + "" + ", start date: " + beginDate + ", end date: "
            + endDate);
      }
      page = depositService.chargeRecordSearch(beginDate, endDate, realName, identifier,status,false,pageable);
    }


    String[] properties =
        {"id", "elderlyInfo.name", "elderlyInfo.identifier", "elderlyInfo.bedLocation",
            "elderlyInfo.nursingLevel", "depositAmount", "payTime","operator","chargeStatus"};

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
    Deposit record = depositService.find(id);
    model.addAttribute("deposit", record);
    return "deposit/details";
  }


}