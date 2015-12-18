package com.yly.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

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
import com.yly.json.request.WaterElectricitySearchRequest;
import com.yly.service.BillingService;
import com.yly.service.ElderlyInfoService;
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
//      queryParam.setIsPeriod(true);
//      queryParam.setIsTenant(true);
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
  
  @Resource(name = "elderlyInfoServiceImpl")
  private ElderlyInfoService elderlyInfoService;
  @Resource(name = "billingServiceImpl")
  private BillingService billingService;
  /**
   * 导出数据前，计算当前呈现给用户的有多少条数据
   * @return
   */
  @RequestMapping(value = "/count", method = RequestMethod.POST)
  public @ResponseBody Map<String, Long> count(WaterElectricitySearchRequest waterElectricitySearch) {
//    WaterElectricityCharge waterElectricityCharge = new WaterElectricityCharge();
//    waterElectricityCharge.setBillingNo("1");
//    waterElectricityCharge.setChargeStatus(PaymentStatus.PAID);
//    ElderlyInfo elderlyInfo = elderlyInfoService.find(new Long(73));
//    waterElectricityCharge.setElderlyInfo(elderlyInfo);
//    waterElectricityCharge.setElectricityAmount(new BigDecimal(1.0));
//    waterElectricityCharge.setWaterAmount(new BigDecimal(1.0));
//    waterElectricityCharge.setElectricityCount(new BigDecimal(1.0));
//    waterElectricityCharge.setWaterCount(new BigDecimal(1.0));
//    waterElectricityCharge.setInvoiceNo("0");
//    waterElectricityCharge.setOperator("小红");
//    waterElectricityCharge.setPayTime(new Date());
//    waterElectricityCharge.setPeriodStartDate(new Date());
//    waterElectricityCharge.setPeriodEndDate(new Date());
//    waterElectricityCharge.setRemark("备注");
//    waterElectricityCharge.setTenantID(new Long(1));
//    waterElectricityCharge.setBilling(billingService.find(new Long(1)));
//    waterElectricityChargeService.save(waterElectricityCharge);
    Long count = new Long(0);
    count = new Long(waterElectricityChargeService.countByFilter(waterElectricitySearch));
    Map<String, Long> countMap = new HashMap<String, Long>(); 
    countMap.put("count", count);
    return countMap;
  }
  /**
   * 导出列表数据，即用户已经查询出来的数据
   * @param withDays
   */
  @RequestMapping(value = "/exportData", method = {RequestMethod.GET,RequestMethod.POST})
  public void exportData(HttpServletResponse response, WaterElectricitySearchRequest waterElectricitySearch) {
    List<WaterElectricityCharge> waterElectricityChargeList = waterElectricityChargeService.searchListByFilter(waterElectricitySearch);
    if (waterElectricityChargeList != null && waterElectricityChargeList.size() > 0) {
      String title = "Water Electricity Charge"; // 工作簿标题，同时也是excel文件名前缀
      String[] headers = {"elderlyInfo.name", "elderlyInfo.identifier", "elderlyInfo.bedLocation", "elderlyInfo.nursingLevel.configValue",
          "waterAmount", "electricityAmount", "totalAmount", "operator", "periodStartDate","chargeStatus"}; // 需要导出的字段
      String[] headersName = {"老人姓名", "编号", "房间", "护理等级", "水费(元)", "电费(元)", "总金额(元)", "经办人", "收款时间段", "状态"}; // 字段对应列的列名
      // 导出数据到Excel
      List<Map<String, String>> waterElectricityMapList = waterElectricityChargeService.prepareMap(waterElectricityChargeList);
      if (waterElectricityMapList.size() > 0) {
        exportListToExcel(response, waterElectricityMapList, title, headers, headersName);
      }
    }
  } 

}
