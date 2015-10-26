package com.yly.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yly.controller.base.BaseController;
import com.yly.entity.ElderlyInfo;
import com.yly.entity.commonenum.CommonEnum.DeleteStatus;
import com.yly.framework.filter.Filter;
import com.yly.framework.filter.Filter.Operator;
import com.yly.framework.paging.Page;
import com.yly.framework.paging.Pageable;
import com.yly.service.ElderlyInfoService;

/**
 * 老人信息
 * 
 * @author shijun
 *
 */
@Controller("elderlyInfoController")
@RequestMapping("console/elderlyInfo")
public class ElderlyInfoController extends BaseController {

  @Resource(name = "elderlyInfoServiceImpl")
  private ElderlyInfoService elderlyInfoService;

  /**
   * 列表页面
   * 
   * @param model
   * @return
   */
  @RequestMapping(value = "/checkedInElderly", method = RequestMethod.GET)
  public String list(ModelMap model) {
    return "/checkedInElderly/checkedInElderly";
  }
  

  /**
   * 老人信息公共搜索页面
   * 
   * @param model
   * @return
   */
  @RequestMapping(value = "/commonElderlySearch", method = RequestMethod.GET)
  public String elderlyInfoSearch(ModelMap model) {
    return "/common/commonElderlySearch";
  }

  /**
   * 查询咨询记录
   * 
   * @param beginDate
   * @param endDate
   * @param pageable
   * @param model
   * @return
   */
  @RequestMapping(value = "/list", method = RequestMethod.POST)
  public @ResponseBody Page<ElderlyInfo> list(Date beHospitalizedBeginDate,
      Date beHospitalizedEndDate, ElderlyInfo elderlyInfo, Pageable pageable, ModelMap model) {

    if (elderlyInfo.getIdentifier() != null || elderlyInfo.getName() != null
        || elderlyInfo.getElderlyStatus() != null || beHospitalizedBeginDate != null
        || beHospitalizedEndDate != null) {
      return elderlyInfoService.searchElderlyInfo(beHospitalizedBeginDate, beHospitalizedEndDate,
          elderlyInfo, pageable);
    }

    List<Filter> filters = new ArrayList<Filter>();
    Filter delFilter = new Filter("deleteStatus", Operator.eq, DeleteStatus.NOT_DELETED);
    filters.add(delFilter);
    pageable.setFilters(filters);

    return elderlyInfoService.findPage(pageable, true);
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
    ElderlyInfo elderlyInfo = elderlyInfoService.find(id);
    model.addAttribute("elderlyInfo", elderlyInfo);
    return "checkedInElderly/details";
  }
}
