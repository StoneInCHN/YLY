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

import com.yly.beans.Message;
import com.yly.controller.base.BaseController;
import com.yly.entity.ElderlyInfo;
import com.yly.entity.commonenum.CommonEnum.DeleteStatus;
import com.yly.framework.filter.Filter;
import com.yly.framework.filter.Filter.Operator;
import com.yly.framework.paging.Page;
import com.yly.framework.paging.Pageable;
import com.yly.service.ChangeRoomService;
import com.yly.service.ElderlyInfoService;

@Controller("changeRoomController")
@RequestMapping("console/changeRoom")
public class ChangeRoomController extends BaseController {

  @Resource(name = "elderlyInfoServiceImpl")
  private ElderlyInfoService elderlyInfoService;
  
  @Resource(name = "changeRoomServiceImpl")
  private ChangeRoomService changeRoomService;

  /**
   * 跳转到changeRoom页面并且列出所有老人信息
   * 
   * @param modelMap
   * @return
   */
  @RequestMapping(value = "/changeRoom", method = RequestMethod.GET)
  public String changeRoom(ModelMap modelMap) {
    return "changeRoom/changeRoom";
  }

  /**
   * 查询在院老人记录用于用户选择具体的换房老人
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
    return "changeRoom/details";
  }
  
  /**
   * 换房
   * 
   * @param elderlyId
   * @param originalBedId
   * @param newBedId
   * @return
   */
  @RequestMapping(value = "/changeToNewRoom", method = RequestMethod.POST)
  public @ResponseBody Message changeRoom(Long elderlyInfoId, Long originalBedId, Long newBedId) {

    Boolean rest = changeRoomService.changeRoom(elderlyInfoId, originalBedId, newBedId);
    if(rest){
      return SUCCESS_MESSAGE;  
    }else{
      return ERROR_MESSAGE;
    }
  }

}
