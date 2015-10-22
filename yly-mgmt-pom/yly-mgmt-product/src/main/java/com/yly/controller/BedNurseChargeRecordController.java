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
import com.yly.entity.BedNurseCharge;
import com.yly.framework.paging.Page;
import com.yly.framework.paging.Pageable;
import com.yly.json.request.QueryParam;
import com.yly.service.BedNurseChargeService;
import com.yly.utils.FieldFilterUtils;

@Controller("bedNurseChargeRecordController")
@RequestMapping("/console/bedNurseChargeRecord")
public class BedNurseChargeRecordController extends BaseController {

  @Resource(name = "bedNurseChargeServiceImpl")
  private BedNurseChargeService bedNurseChargeService;


  /**
   * 列表页面
   * 
   * @param model
   * @return
   */
  @RequestMapping(value = "/bedNurseChargeRecord", method = RequestMethod.GET)
  public String bedNurseChargeRecord(ModelMap model) {
    return "/bedNurseChargeRecord/bedNurseChargeRecord";
  }

  /**
   * 列表数据
   * 
   * @param pageable
   * @param model
   * @return
   */
  @RequestMapping(value = "/list", method = RequestMethod.POST)
  public @ResponseBody Page<Map<String, Object>> list(QueryParam queryParam, Pageable pageable, ModelMap model) {
    Page<BedNurseCharge> page = new Page<BedNurseCharge>();
    if (queryParam.getRealName() == null && queryParam.getIdentifier() == null && queryParam.getBeginDate() == null && queryParam.getEndDate() == null && queryParam.getStatus() == null) {
      page = bedNurseChargeService.findPage(pageable, true);
    } else {
      if (LogUtil.isDebugEnabled(BedNurseChargeRecordController.class)) {
        LogUtil.debug(BedNurseChargeRecordController.class, "search", "elderlyName: " + queryParam.getRealName()
            + ",identifier: " + queryParam.getIdentifier() + "" + ",status: " + queryParam.getStatus() + ""+",start date: " + queryParam.getBeginDate() + ", end date: "
            + queryParam.getEndDate());
      }
      queryParam.setIsPeriod(true);
      queryParam.setIsTenant(true);
//      page = bedNurseChargeService.chargeRecordSearch(true,beginDate, endDate, realName, identifier,status,null,true,pageable);
      page = bedNurseChargeService.chargeRecordSearch(queryParam,pageable);
    }


    String[] properties =
        {"id", "elderlyInfo.name", "elderlyInfo.identifier", "elderlyInfo.bedLocation",
            "elderlyInfo.nursingLevel", "bedAmount", "nurseAmount", "totalAmount", "operator",
            "periodEndDate", "chargeStatus"};

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
    BedNurseCharge record = bedNurseChargeService.find(id);
    model.addAttribute("bedNurseCharge", record);
    return "bedNurseChargeRecord/details";
  }


}
