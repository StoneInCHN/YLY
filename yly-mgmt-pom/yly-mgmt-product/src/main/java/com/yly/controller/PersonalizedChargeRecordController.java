package com.yly.controller;


import java.math.BigDecimal;
import java.util.ArrayList;
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
import com.yly.entity.PersonalizedCharge;
import com.yly.entity.PersonalizedRecord;
import com.yly.framework.paging.Page;
import com.yly.framework.paging.Pageable;
import com.yly.json.request.ChargeSearchRequest;
import com.yly.json.request.WaterElectricitySearchRequest;
import com.yly.service.PersonalizedChargeService;
import com.yly.utils.FieldFilterUtils;

@Controller("personalizedChargeRecordController")
@RequestMapping("/console/personalizedChargeRecord")
public class PersonalizedChargeRecordController extends BaseController {

  @Resource(name = "personalizedChargeServiceImpl")
  private PersonalizedChargeService personalizedChargeService;
 
  
  /**
   * 列表页面
   * @param model
   * @return
   */
  @RequestMapping(value = "/personalizedChargeRecord", method = RequestMethod.GET)
  public String personalizedChargeRecord(ModelMap model) {
    return "/personalizedChargeRecord/personalizedChargeRecord";
  }

  /**
   * 列表数据
   * @param pageable
   * @param model
   * @return
   */
  @RequestMapping(value = "/list", method = RequestMethod.POST)
  public @ResponseBody Page<Map<String, Object>> list(ChargeSearchRequest queryParam,Pageable pageable, ModelMap model) {
    Page<PersonalizedCharge> page = new Page<PersonalizedCharge>();
    if (queryParam.getRealName() == null && queryParam.getIdentifier() == null && queryParam.getBeginDate() == null && queryParam.getEndDate() == null && queryParam.getStatus() == null) {
      page = personalizedChargeService.findPage(pageable, true);
    } else {
      if (LogUtil.isDebugEnabled(PersonalizedChargeRecordController.class)) {
        LogUtil.debug(PersonalizedChargeRecordController.class, "Searching personalized service charge records with params",
            "elderlyName=%s,identifier=%s,chargeStatus=%s,beginDate=%s,endDate=%s", queryParam
                .getRealName(), queryParam.getIdentifier(),queryParam.getStatus()!=null?queryParam.getStatus().toString():null,
                    queryParam.getBeginDate()!=null?queryParam.getBeginDate().toString():null, queryParam.getEndDate()!=null?queryParam.getEndDate().toString():null);
      }
//      queryParam.setIsPeriod(true);
//      queryParam.setIsTenant(true);
      page = personalizedChargeService.chargeRecordSearch(queryParam,pageable);
    }
    
    String[] properties = { "id","elderlyInfo.name", "elderlyInfo.identifier", "elderlyInfo.bedLocation", "elderlyInfo.nursingLevel",
            "personalizedAmount", "operator","periodStartDate", "chargeStatus" };

    List<Map<String, Object>> rows = FieldFilterUtils.filterCollectionMap(properties, page.getRows());

    Page<Map<String, Object>> filteredPage = new Page<Map<String, Object>>(rows, page.getTotal(), pageable);

    return filteredPage;
  }
  
  /**
   * 获取数据进入详情页面
   * @param model
   * @param id
   * @return
   */
  @RequestMapping(value = "/details", method = RequestMethod.GET)
  public String details(ModelMap model, Long id) {
    PersonalizedCharge record =  personalizedChargeService.find(id);
    model.addAttribute("personalizedCharge", record);
    
    List<Map<String, Object>> serviceDetails = new ArrayList<Map<String,Object>>();
    for(PersonalizedRecord personalizedRecord:record.getPersonalizedRecords()){
      Boolean flag=false;
      Map<String, Object> serviceItem = new HashMap<String, Object>();
      for(Map<String , Object> map:serviceDetails){
        if (map.get("serviceName").equals(personalizedRecord.getNurseContent())) {
            map.put("serviceCount", ((BigDecimal)map.get("serviceCount")).add(new BigDecimal(1)));
            map.put("serviceAmount", ((BigDecimal)map.get("serviceUnitPrice")).multiply((BigDecimal)map.get("serviceCount")));
            flag=true;
            break;
        }
      }
      if (flag) {
        continue;
      }
      serviceItem.put("serviceName", personalizedRecord.getNurseContent());
      serviceItem.put("serviceUnitPrice", personalizedRecord.getPersonalizedNurse().getServicePrice());
      serviceItem.put("serviceAmount", personalizedRecord.getPersonalizedNurse().getServicePrice());
      serviceItem.put("serviceCount", new BigDecimal(1));
      serviceDetails.add(serviceItem);
    }
    model.addAttribute("serviceDetails", serviceDetails);
    return "personalizedChargeRecord/details";
  }
  /**
   * 导出数据前，计算当前呈现给用户的有多少条数据
   * @return
   */
  @RequestMapping(value = "/count", method = RequestMethod.POST)
  public @ResponseBody Map<String, Long> count(WaterElectricitySearchRequest waterElectricitySearch) {
    Long count = new Long(0);
    count = new Long(personalizedChargeService.countByFilter(waterElectricitySearch));
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
    List<PersonalizedCharge> personalizedChargeList = personalizedChargeService.searchListByFilter(waterElectricitySearch);
    if (personalizedChargeList != null && personalizedChargeList.size() > 0) {
      String title = "Water Electricity Charge"; // 工作簿标题，同时也是excel文件名前缀
      String[] headers = {"elderlyInfo.name", "elderlyInfo.identifier", "elderlyInfo.bedLocation", "elderlyInfo.nursingLevel.configValue",
          "personalizedAmount", "operator", "periodStartDate","chargeStatus"}; // 需要导出的字段
      String[] headersName = {"老人姓名", "编号", "房间", "护理等级", "服务费(元)", "经办人", "收款时间段", "状态"}; // 字段对应列的列名
      // 导出数据到Excel
      List<Map<String, String>> personalizedChargeMapList = personalizedChargeService.prepareMap(personalizedChargeList);
      if (personalizedChargeMapList.size() > 0) {
        exportListToExcel(response, personalizedChargeMapList, title, headers, headersName);
      }
    }
  } 

}
