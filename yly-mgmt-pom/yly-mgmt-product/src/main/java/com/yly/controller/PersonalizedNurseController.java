package com.yly.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yly.beans.Message;
import com.yly.controller.base.BaseController;
import com.yly.entity.PersonalizedNurse;
import com.yly.framework.paging.Page;
import com.yly.framework.paging.Pageable;
import com.yly.service.PersonalizedNurseService;

@RequestMapping("console/personalizedNurse")
@Controller("personalizedNurseController")
public class PersonalizedNurseController extends BaseController {

  @Resource(name = "personalizedNurseServiceImpl")
  private PersonalizedNurseService personalizedNurseService;

  @RequestMapping(value = "/personalizedNurse", method = RequestMethod.GET)
  public String personalizedNurse(ModelMap model) {
    return "personalizedNurse/personalizedNurse";
  }

  @RequestMapping(value = "/list", method = RequestMethod.POST)
  public @ResponseBody Page<PersonalizedNurse> list(Pageable pageable) {
    return personalizedNurseService.findPage(pageable);
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
    model.addAttribute("personalizedNurse", personalizedNurseService.find(id));
    return "personalizedNurse/edit";
  }

  /**
   * 保存
   * 
   * @param building
   * @return
   */
  @RequestMapping(value = "/add", method = RequestMethod.POST)
  public @ResponseBody Message add(PersonalizedNurse personalizedNurse) {
    return ERROR_MESSAGE;

  }


  @RequestMapping(value = "/update", method = RequestMethod.POST)
  public @ResponseBody Message update(PersonalizedNurse personalizedNurse) {
    return ERROR_MESSAGE;
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
      personalizedNurseService.delete(ids);
    }
    return SUCCESS_MESSAGE;
  }
}
