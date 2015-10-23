package com.yly.controller;


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
import com.yly.framework.paging.Page;
import com.yly.framework.paging.Pageable;
import com.yly.json.request.ChargeSearchRequest;
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
  public @ResponseBody Page<Map<String, Object>> list(ChargeSearchRequest queryParam, Pageable pageable, ModelMap model) {
    Page<WaterElectricityCharge> page = new Page<WaterElectricityCharge>();
    if (queryParam.getRealName() == null && queryParam.getIdentifier() == null && queryParam.getBeginDate() == null && queryParam.getEndDate() == null && queryParam.getStatus() == null) {
      page = waterElectricityChargeService.findPage(pageable, true);
    } else {
      if (LogUtil.isDebugEnabled(WaterElectricityChargeRecordController.class)) {
        LogUtil.debug(WaterElectricityChargeRecordController.class, "Searching water and electricity records with params",
            "elderlyName=%s,identifier=%s,chargeStatus=%s,beginDate=%s,endDate=%s", queryParam
                .getRealName(), queryParam.getIdentifier(),queryParam.getStatus()!=null?queryParam.getStatus().toString():null,
                    queryParam.getBeginDate()!=null?queryParam.getBeginDate().toString():null, queryParam.getEndDate()!=null?queryParam.getEndDate().toString():null);
      }
      queryParam.setIsPeriod(true);
      queryParam.setIsTenant(true);
      page = waterElectricityChargeService.chargeRecordSearch(queryParam,pageable);
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
