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
import com.yly.entity.WaterElectricityCharge;
import com.yly.entity.WaterElectricityChargeConfig;
import com.yly.entity.commonenum.CommonEnum.PaymentStatus;
import com.yly.framework.paging.Page;
import com.yly.framework.paging.Pageable;
import com.yly.service.WaterElectricityChargeConfigService;
import com.yly.service.WaterElectricityChargeService;
import com.yly.utils.FieldFilterUtils;

@Controller("waterElectricityChargeRecordController")
@RequestMapping("/console/waterElectricityChargeRecord")
public class WaterElectricityChargeRecordController extends BaseController {

  @Resource(name = "waterElectricityChargeServiceImpl")
  private WaterElectricityChargeService waterElectricityChargeService;
  
  @Resource(name = "waterElectricityChargeConfigServiceImpl")
  private WaterElectricityChargeConfigService waterElectricityChargeConfigService;


  /**
   * 列表页面
   * 
   * @param model
   * @return
   */
  @RequestMapping(value = "/waterElectricityChargeRecord", method = RequestMethod.GET)
  public String waterElectricityChargeRecord(ModelMap model) {
    return "/waterElectricityChargeRecord/waterElectricityChargeRecord";
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
    Page<WaterElectricityCharge> page = new Page<WaterElectricityCharge>();
    if (realName == null && identifier == null && beginDate == null && endDate == null && status == null) {
      page = waterElectricityChargeService.findPage(pageable, true);
    } else {
      if (LogUtil.isDebugEnabled(WaterElectricityChargeRecordController.class)) {
        LogUtil.debug(WaterElectricityChargeRecordController.class, "search", "elderlyName: " + realName
            + ",identifier: " + identifier + "" + ",status: " + status + ", start date: " + beginDate + ", end date: "
            + endDate);
      }
      page = waterElectricityChargeService.chargeRecordSearch(true,beginDate, endDate, realName, identifier,status,null,true,pageable);
    }
    
    String[] properties =
        {"id", "elderlyInfo.name", "elderlyInfo.identifier", "elderlyInfo.bedLocation",
            "elderlyInfo.nursingLevel", "waterAmount", "electricityAmount", "totalAmount", "operator",
            "periodStartDate", "chargeStatus"};

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
    WaterElectricityCharge record = waterElectricityChargeService.find(id);
    List<WaterElectricityChargeConfig> configs = waterElectricityChargeConfigService.findAll(true);
    if (configs!=null && configs.size()==1) {
      model.addAttribute("waterElectricityChargeConfig", configs.get(0));
    }
    model.addAttribute("waterElectricityCharge", record);
    return "waterElectricityChargeRecord/details";
  }


}
