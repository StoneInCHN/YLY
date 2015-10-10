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
import com.yly.entity.BedNurseCharge;
import com.yly.framework.paging.Page;
import com.yly.framework.paging.Pageable;
import com.yly.service.BedNurseChargeService;
import com.yly.utils.FieldFilterUtils;

@Controller("bedNurseChargeRecordController")
@RequestMapping("/console/bedNurseChargeRecord")
public class BedNurseChargeRecordController extends BaseController {

  @Resource(name = "bedNurseChargeServiceImpl")
  private BedNurseChargeService bedNurseChargeService;
 
  
  /**
   * 列表页面
   * @param model
   * @return
   */
  @RequestMapping(value = "/bedNurseChargeRecord", method = RequestMethod.GET)
  public String bedNurseChargeRecord(ModelMap model) {
    return "/bedNurseChargeRecord/bedNurseChargeRecord";
  }

  /**
   * 列表数据
   * @param pageable
   * @param model
   * @return
   */
  @RequestMapping(value = "/list", method = RequestMethod.POST)
  public @ResponseBody Page<Map<String, Object>> list(Date beginDate, Date endDate,String realName,String identifier,Pageable pageable, ModelMap model) {
    Page<BedNurseCharge> page = bedNurseChargeService.findPage(pageable, true);

    String[] properties = { "id","elderlyInfo.name", "elderlyInfo.identifier", "elderlyInfo.bedLocation", "elderlyInfo.nursingLevel",
            "bedAmount", "nurseAmount", "totalAmount", "operator","periodEndDate", "chargeStatus" };

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
    BedNurseCharge record =  bedNurseChargeService.find(id);
    model.addAttribute("bedNurseCharge", record);
    return "bedNurseChargeRecord/details";
  }


}
