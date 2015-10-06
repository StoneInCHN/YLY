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
import com.yly.entity.ElderlyInfo;
import com.yly.framework.paging.Page;
import com.yly.framework.paging.Pageable;
import com.yly.service.ElderlyInfoService;
import com.yly.service.TenantAccountService;

/**
 * 办理入院
 * 
 * @author shijun
 *
 */
@Controller("admissionController")
@RequestMapping("console/admission")
public class AdmissionController extends BaseController{
  
  @Resource(name = "elderlyInfoServiceImpl")
  private ElderlyInfoService elderlyInfoService;
  
  @Resource(name = "tenantAccountServiceImpl")
  private TenantAccountService tenantAccountService;

  /**
   * 列表页面
   * 
   * @param model
   * @return
   */
  @RequestMapping(value = "/admission", method = RequestMethod.GET)
  public String list(ModelMap model) {
    return "/admission/admission";
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
  public @ResponseBody Page<ElderlyInfo> list(Date beginDate, Date endDate,
      Pageable pageable, ModelMap model) {
    return elderlyInfoService.findPage(pageable, true);
  }

  /**
   * 添加
   * 
   * @param elderlyInfo
   * @return
   */
  @RequestMapping(value = "/add", method = RequestMethod.POST)
  public @ResponseBody Message add(ElderlyInfo elderlyInfo) {

    if (elderlyInfo != null) {
      elderlyInfo.setTenantID(tenantAccountService.getCurrentTenantID());
      elderlyInfoService.save(elderlyInfo);
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
    ElderlyInfo elderlyInfo =  elderlyInfoService.find(id);
    model.addAttribute("elderlyInfo", elderlyInfo);
    return "admission/edit";
  }
  
  /**
   * 获取数据进入详情页面
   * @param model
   * @param id
   * @return
   */
  @RequestMapping(value = "/details", method = RequestMethod.GET)
  public String details(ModelMap model, Long id) {
    ElderlyInfo elderlyInfo =  elderlyInfoService.find(id);
    model.addAttribute("elderlyInfo", elderlyInfo);
    return "admission/details";
  }

  /**
   * 更新
   * 
   * @param elderlyInfo
   * @return
   */
  @RequestMapping(value = "/update", method = RequestMethod.POST)
  public @ResponseBody Message update(ElderlyInfo elderlyInfo) {
    elderlyInfoService.update(elderlyInfo);
    return SUCCESS_MESSAGE;
  }

  /**
   * 删除
   */
  @RequestMapping(value = "/delete", method = RequestMethod.POST)
  public @ResponseBody Message delete(Long[] ids) {
    if(ids != null){
      elderlyInfoService.delete(ids);
    }
    return SUCCESS_MESSAGE;
  }

  
}
