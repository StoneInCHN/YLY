package com.yly.controller;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yly.beans.Message;
import com.yly.controller.base.BaseController;
import com.yly.entity.Volunteer;
import com.yly.framework.paging.Page;
import com.yly.framework.paging.Pageable;
import com.yly.service.TenantAccountService;
import com.yly.service.VolunteerService;

/**
 * 志愿者
 * 
 * @author chenyoujun
 *
 */
@Controller("volunteerController")
@RequestMapping("console/volunteer")
public class VolunteerController extends BaseController {

  @Resource(name = "volunteerServiceImpl")
  private VolunteerService volunteerService;
  
  @Resource(name = "tenantAccountServiceImpl")
  private TenantAccountService tenantAccountService;

  /**
   * 列表页面
   * 
   * @param model
   * @return
   */
  @RequestMapping(value = "/volunteer", method = RequestMethod.GET)
  public String list(ModelMap model) {
    return "/volunteer/volunteer";
  }

  /**
   * 志愿者记录
   * 
   * @param beginDate
   * @param endDate
   * @param pageable
   * @param model
   * @return
   */
  @RequestMapping(value = "/list", method = RequestMethod.POST)
  public @ResponseBody Page<Volunteer> list(Date beginDate, Date endDate,
      Pageable pageable, ModelMap model) {
    return volunteerService.findPage(pageable, true);
  }

  /**
   * 添加
   * 
   * @param volunteer
   * @return
   */
  @RequestMapping(value = "/add", method = RequestMethod.POST)
  public @ResponseBody Message add(Volunteer volunteer) {

    if (volunteer != null) {
      volunteer.setTenantID(tenantAccountService.getCurrentTenantID());
      volunteerService.save(volunteer);
    }

    return SUCCESS_MESSAGE;
  }
  
  
  /**
   * 获取数据进入编辑页面
   * @param model
   * @param id
   * @return
   */
  @RequestMapping(value = "/edit", method = RequestMethod.GET)
  public String edit(ModelMap model, Long id) {
    Volunteer volunteer =  volunteerService.find(id);
    model.addAttribute("volunteer", volunteer);
    return "volunteer/edit";
  }
  
  /**
   * 获取数据进入详情页面
   * @param model
   * @param id
   * @return
   */
  @RequestMapping(value = "/details", method = RequestMethod.GET)
  public String details(ModelMap model, Long id) {
    Volunteer volunteer =  volunteerService.find(id);
    model.addAttribute("volunteer", volunteer);
    return "volunteer/details";
  }

  /**
   * 更新
   * 
   * @param volunteer
   * @return
   */
  @RequestMapping(value = "/update", method = RequestMethod.POST)
  public @ResponseBody Message update(Volunteer volunteer) {
    volunteerService.update(volunteer);
    return SUCCESS_MESSAGE;
  }

  /**
   * 删除
   * @param id arrays
   */
  @RequestMapping(value = "/delete", method = RequestMethod.POST)
  public @ResponseBody Message delete(Long[] ids) {
    if(ids != null){
      volunteerService.delete(ids);
    }
    return SUCCESS_MESSAGE;
  }
}
