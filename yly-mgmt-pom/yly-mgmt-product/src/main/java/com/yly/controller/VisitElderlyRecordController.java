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
import com.yly.entity.VisitElderlyRecord;
import com.yly.framework.paging.Page;
import com.yly.framework.paging.Pageable;
import com.yly.service.TenantAccountService;
import com.yly.service.VisitElderlyRecordService;

/**
 * 老人探望
 * 
 * @author shijun
 *
 */
@Controller("visitElderlyRecordController")
@RequestMapping("console/visitElderly")
public class VisitElderlyRecordController extends BaseController {


  @Resource(name = "visitElderlyRecordServiceImpl")
  private VisitElderlyRecordService visitElderlyRecordService;

  @Resource(name = "tenantAccountServiceImpl")
  private TenantAccountService tenantAccountService;

  /**
   * 列表页面
   * 
   * @param model
   * @return
   */
  @RequestMapping(value = "/visitelderly", method = RequestMethod.GET)
  public String list(ModelMap model) {
    return "/visitElderly/visitElderly";
  }

  /**
   * 查询探望记录
   * 
   * @param beginDate
   * @param endDate
   * @param pageable
   * @param model
   * @return
   */
  @RequestMapping(value = "/list", method = RequestMethod.POST)
  public @ResponseBody Page<VisitElderlyRecord> list(Date beginDate, Date endDate,
      Pageable pageable, ModelMap model) {
    return visitElderlyRecordService.findPage(pageable, true);
  }

  /**
   * 添加
   * 
   * @param visitElderlyRecord
   * @return
   */
  @RequestMapping(value = "/add", method = RequestMethod.POST)
  public @ResponseBody Message add(VisitElderlyRecord visitElderlyRecord) {

    if (visitElderlyRecord != null) {
      visitElderlyRecord.setTenantID(tenantAccountService.getCurrentTenantID());
      visitElderlyRecordService.save(visitElderlyRecord);
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
    VisitElderlyRecord record = visitElderlyRecordService.find(id);
    model.addAttribute("visitElderlyRecord", record);
    return "visitElderly/edit";
  }


  /**
   * 更新
   * 
   * @param visitElderlyRecord
   * @return
   */
  @RequestMapping(value = "/update", method = RequestMethod.POST)
  public @ResponseBody Message update(VisitElderlyRecord visitElderlyRecord) {
    visitElderlyRecordService.update(visitElderlyRecord);
    return SUCCESS_MESSAGE;
  }

  /**
   * 删除
   */
  @RequestMapping(value = "/delete", method = RequestMethod.POST)
  public @ResponseBody Message delete(Long[] ids) {
    if (ids != null) {
      visitElderlyRecordService.delete(ids);
    }
    return SUCCESS_MESSAGE;
  }

}
