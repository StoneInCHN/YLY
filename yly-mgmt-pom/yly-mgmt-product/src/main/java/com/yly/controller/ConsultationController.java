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
import com.yly.entity.ConsultationRecord;
import com.yly.framework.paging.Page;
import com.yly.framework.paging.Pageable;
import com.yly.service.ConsultationService;
import com.yly.service.TenantAccountService;

/**
 * 咨询
 * 
 * @author shijun
 *
 */
@Controller("consultationController")
@RequestMapping("console/consultation")
public class ConsultationController extends BaseController {

  @Resource(name = "consultationServiceImpl")
  private ConsultationService consultationService;

  @Resource(name = "tenantAccountServiceImpl")
  private TenantAccountService tenantAccountService;

  /**
   * 列表页面
   * 
   * @param model
   * @return
   */
  @RequestMapping(value = "/consultation", method = RequestMethod.GET)
  public String list(ModelMap model) {
    return "/consultation/consultation";
  }

  /**
   * 查询咨询记录
   * 
   * @param returnVisitDateBeginDate
   * @param returnVisitDateEndDate
   * @param consultationRecord
   * @param pageable
   * @param model
   * @return
   */
  @RequestMapping(value = "/list", method = RequestMethod.POST)
  public @ResponseBody Page<ConsultationRecord> list(Date returnVisitDateBeginDate,
      Date returnVisitDateEndDate, ConsultationRecord consultationRecord, Pageable pageable,
      ModelMap model) {

    if (consultationRecord.getVisitor() != null || consultationRecord.getElderlyName() != null
        || consultationRecord.getCheckinIntention() != null
        || consultationRecord.getInfoChannel() != null || returnVisitDateBeginDate != null
        || returnVisitDateEndDate != null) {

      return consultationService.consultationSearch(returnVisitDateBeginDate,
          returnVisitDateEndDate, consultationRecord, pageable);
    }
    return consultationService.findPage(pageable, true);
  }

  /**
   * 添加
   * 
   * @param consultationRecord
   * @return
   */
  @RequestMapping(value = "/add", method = RequestMethod.POST)
  public @ResponseBody Message add(ConsultationRecord consultationRecord) {

    if (consultationRecord != null) {
      consultationRecord.setTenantID(tenantAccountService.getCurrentTenantID());
      consultationService.save(consultationRecord);
    }

    return SUCCESS_MESSAGE;
  }


  /**
   * 获取数据进入编辑页面
   * 
   * @param model
   * @param id
   * @return
   */
  @RequestMapping(value = "/edit", method = RequestMethod.GET)
  public String edit(ModelMap model, Long id) {
    ConsultationRecord record = consultationService.find(id);
    model.addAttribute("consultation", record);
    return "consultation/edit";
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
    ConsultationRecord record = consultationService.find(id);
    model.addAttribute("consultation", record);
    return "consultation/details";
  }

  /**
   * 更新
   * 
   * @param consultationRecord
   * @return
   */
  @RequestMapping(value = "/update", method = RequestMethod.POST)
  public @ResponseBody Message update(ConsultationRecord consultationRecord) {
    consultationService.update(consultationRecord);
    return SUCCESS_MESSAGE;
  }

  /**
   * 删除
   */
  @RequestMapping(value = "/delete", method = RequestMethod.POST)
  public @ResponseBody Message delete(Long[] ids) {
    if (ids != null) {
      consultationService.delete(ids);
    }
    return SUCCESS_MESSAGE;
  }
}
