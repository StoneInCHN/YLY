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

import com.yly.controller.base.BaseController;
import com.yly.entity.MealCharge;
import com.yly.framework.paging.Page;
import com.yly.framework.paging.Pageable;
import com.yly.service.MealChargeService;
import com.yly.utils.FieldFilterUtils;

@Controller("mealChargeRecordController")
@RequestMapping("/console/mealChargeRecord")
public class MealChargeRecordController extends BaseController {

  @Resource(name = "mealChargeServiceImpl")
  private MealChargeService mealChargeService;
 
  
  /**
   * 列表页面
   * @param model
   * @return
   */
  @RequestMapping(value = "/mealChargeRecord", method = RequestMethod.GET)
  public String mealChargeRecord(ModelMap model) {
    return "/mealChargeRecord/mealChargeRecord";
  }

  /**
   * 列表数据
   * @param pageable
   * @param model
   * @return
   */
  @RequestMapping(value = "/list", method = RequestMethod.POST)
  public @ResponseBody Page<Map<String, Object>> list(Date beginDate, Date endDate,String realName,String identifier,Pageable pageable, ModelMap model) {
    Page<MealCharge> page = mealChargeService.findPage(pageable, true);

    String[] properties = { "id","elderlyInfo.name", "elderlyInfo.identifier", "elderlyInfo.bedLocation", "elderlyInfo.nursingLevel","elderlyInfo.mealType",
            "mealAmount","operator","periodEndDate", "chargeStatus" };
    
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
    MealCharge record =  mealChargeService.find(id);
    model.addAttribute("mealCharge", record);
    return "mealChargeRecord/details";
  }

}
