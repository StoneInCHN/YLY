package com.yly.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yly.beans.Message;
import com.yly.controller.base.BaseController;
import com.yly.entity.Bed;
import com.yly.framework.paging.Page;
import com.yly.framework.paging.Pageable;
import com.yly.service.NurseDutyTypeService;
import com.yly.service.NurseScheduleService;

@Controller("nurseScheduleController")
@RequestMapping("console/nurseSchedule")
public class NurseScheduleController extends BaseController{

  @Resource(name="nurseScheduleServiceImpl")
  private NurseScheduleService nurseScheduleService;
  
  @Resource(name="nurseDutyTypeServiceImpl")
  private NurseDutyTypeService nurseDutyTypeService;
  
  
  @RequestMapping(value = "/nurseSchedule", method = RequestMethod.GET)
  public String bed(ModelMap model) {
    model.addAttribute("nurseDutyTypes", nurseDutyTypeService.findAll(true));
    return "nurseSchedule/nurseSchedule";
  }

  @RequestMapping(value = "/list", method = RequestMethod.POST)
  public @ResponseBody Page<Bed> list(Pageable pageable) {
    return null;
  }

  /**
   * 编辑
   * 
   * @param model
   * @param id
   * @return
   */
  @RequestMapping(value = "/edit", method = RequestMethod.GET)
  public String edit(ModelMap model, Long id) {
    model.addAttribute("nurseSchedule", nurseScheduleService.find(id));
    return "nurseSchedule/edit";
  }

  /**
   * 保存
   * 
   * @param building
   * @return
   */
  @RequestMapping(value = "/add", method = RequestMethod.POST)
  public @ResponseBody Message add(Bed bed, Long roomId) {
    return null;

  }


  @RequestMapping(value = "/update", method = RequestMethod.POST)
  public @ResponseBody Message update(Bed bed, Long roomId) {
    return null;

  }



  /**
   * 删除
   * 
   * @param ids
   * @return
   */
  @RequestMapping(value = "/delete", method = RequestMethod.POST)
  public @ResponseBody Message delete(Long[] ids) {
    if (ids != null) {
      nurseScheduleService.delete(ids);
    }
    return SUCCESS_MESSAGE;
  }

  
}
