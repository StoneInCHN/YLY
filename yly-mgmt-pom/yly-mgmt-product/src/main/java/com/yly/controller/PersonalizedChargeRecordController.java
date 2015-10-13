package com.yly.controller;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
import com.yly.entity.PersonalizedCharge;
import com.yly.entity.PersonalizedRecord;
import com.yly.framework.paging.Page;
import com.yly.framework.paging.Pageable;
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
  public @ResponseBody Page<Map<String, Object>> list(Date beginDate, Date endDate,String realName,String identifier,Pageable pageable, ModelMap model) {
    Page<PersonalizedCharge> page = new Page<PersonalizedCharge>();
    if (realName == null && identifier == null && beginDate == null && endDate == null) {
      page = personalizedChargeService.findPage(pageable, true);
    } else {
      if (LogUtil.isDebugEnabled(PersonalizedChargeRecordController.class)) {
        LogUtil.debug(PersonalizedChargeRecordController.class, "search", "elderlyName: " + realName
            + ",identifier: " + identifier + "" + ", start date: " + beginDate + ", end date: "
            + endDate);
      }
      page = personalizedChargeService.chargeRecordSearch(beginDate, endDate, realName, identifier, pageable);
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


}
