package com.yly.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yly.beans.Message;
import com.yly.controller.base.BaseController;
import com.yly.entity.NursePlan;
import com.yly.framework.paging.Page;
import com.yly.framework.paging.Pageable;
import com.yly.service.NursePlanService;

@RequestMapping("console/nursePlan")
@Controller("nursePlanController")
public class NursePlanController extends BaseController {

  @Resource(name="nursePlanServiceImpl")
  private NursePlanService nursePlanService;


  @RequestMapping(value = "/nursePlan", method = RequestMethod.GET)
  public String bed(ModelMap model) {
    return "nursePlan/nursePlan";
  }

  @RequestMapping(value = "/list", method = RequestMethod.POST)
  public @ResponseBody Page<NursePlan> list(Pageable pageable) {
    return nursePlanService.findPage(pageable, true);
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
    model.addAttribute("nursePlan", nursePlanService.find(id));
    return "nursePlan/edit";
  }

  /**
   * 保存
   * 
   * @param building
   * @return
   */
  @RequestMapping(value = "/add", method = RequestMethod.POST)
  public @ResponseBody Message add(NursePlan nursePlan, Long roomId) {
    nursePlanService.save(nursePlan, true);
    return SUCCESS_MESSAGE;
  }


  @RequestMapping(value = "/update", method = RequestMethod.POST)
  public @ResponseBody Message update(NursePlan nursePlan) {
    nursePlanService.update(nursePlan, "tenantID","createDate");
    return SUCCESS_MESSAGE;

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
      nursePlanService.delete(ids);
    }
    return SUCCESS_MESSAGE;
  }

}
