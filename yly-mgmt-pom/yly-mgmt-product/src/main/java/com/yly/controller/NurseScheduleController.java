package com.yly.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yly.beans.Message;
import com.yly.common.log.LogUtil;
import com.yly.controller.base.BaseController;
import com.yly.entity.NurseDutyType;
import com.yly.entity.NurseSchedule;
import com.yly.entity.TenantUser;
import com.yly.framework.paging.Page;
import com.yly.framework.paging.Pageable;
import com.yly.json.response.DutyTypeResponse;
import com.yly.service.NurseDutyTypeService;
import com.yly.service.NurseScheduleService;
import com.yly.service.TenantUserService;

@Controller("nurseScheduleController")
@RequestMapping("console/nurseSchedule")
public class NurseScheduleController extends BaseController {

  @Resource(name = "nurseScheduleServiceImpl")
  private NurseScheduleService nurseScheduleService;

  @Resource(name = "nurseDutyTypeServiceImpl")
  private NurseDutyTypeService nurseDutyTypeService;
  
  @Resource(name="tenantUserServiceImpl")
  private TenantUserService tenantUserService;


  @RequestMapping(value = "/nurseSchedule", method = RequestMethod.GET)
  public String bed(ModelMap model) {
    model.addAttribute("nurseDutyTypes", nurseDutyTypeService.findAll(true));
    return "nurseSchedule/nurseSchedule";
  }

  @RequestMapping(value = "/list", method = RequestMethod.POST)
  public @ResponseBody Page<NurseSchedule> list(Pageable pageable) {
    return nurseScheduleService.findPage(pageable, true);
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
  public @ResponseBody Message add(NurseSchedule nurseSchedule, Long dutyTypeId ,Long dutyStaffId) {
    if (dutyTypeId != null) {
      NurseDutyType dutyType = nurseDutyTypeService.find(dutyTypeId);
      nurseSchedule.setDutyType(dutyType);
    }
    
    if(dutyStaffId !=null){
     TenantUser tenantUser =  tenantUserService.find(dutyStaffId);
     nurseSchedule.setDutyStaff(tenantUser.getRealName());
     nurseSchedule.setTenantUser(tenantUser);
    }
    nurseScheduleService.save(nurseSchedule, true);
    return SUCCESS_MESSAGE;

  }


  @RequestMapping(value = "/update", method = RequestMethod.POST)
  public @ResponseBody Message update(NurseSchedule nurseSchedule, Long dutyTypeId,Long dutyStaffId) {
    try {
      if (dutyTypeId != null) {
        NurseDutyType dutyType = nurseDutyTypeService.find(dutyTypeId);
        nurseSchedule.setDutyType(dutyType);
      }
      if(dutyStaffId !=null){
        TenantUser tenantUser =  tenantUserService.find(dutyStaffId);
        nurseSchedule.setDutyStaff(tenantUser.getRealName());
        nurseSchedule.setTenantUser(tenantUser);
       }
      nurseScheduleService.update(nurseSchedule, "tenantID");
      return SUCCESS_MESSAGE;
    } catch (Exception e) {
      LogUtil.debug(NurseScheduleController.class, "update", "", e);
      return ERROR_MESSAGE;
    }
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
  
  @RequestMapping(value = "/getAllDutyType", method = RequestMethod.GET)
  public @ResponseBody List<DutyTypeResponse> getAllDutyType(){
    List<DutyTypeResponse>  responses = new ArrayList<DutyTypeResponse>();
    List<NurseDutyType> nurseDutyTypes = nurseDutyTypeService.findAll(true);
    for (NurseDutyType nurseDutyType : nurseDutyTypes) {
      DutyTypeResponse res = new DutyTypeResponse();
      res.setId(nurseDutyType.getId());
      res.setText(nurseDutyType.getDutyName());
      responses.add(res);
    }
    return responses;
  }
  
}
