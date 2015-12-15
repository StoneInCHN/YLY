package com.yly.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yly.beans.Message;
import com.yly.controller.base.BaseController;
import com.yly.entity.ConsultationRecord;
import com.yly.entity.ElderlyEventRecord;
import com.yly.entity.commonenum.CommonEnum.CheckinIntention;
import com.yly.entity.commonenum.CommonEnum.Gender;
import com.yly.entity.commonenum.CommonEnum.InfoChannel;
import com.yly.entity.commonenum.CommonEnum.Relation;
import com.yly.framework.paging.Page;
import com.yly.framework.paging.Pageable;
import com.yly.json.request.ConsultationRecordSearchRequest;
import com.yly.service.ConsultationService;
import com.yly.service.TenantAccountService;
import com.yly.utils.DateTimeUtils;

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
  /**
   * 导出数据前，计算当前呈现给用户的有多少条数据
   * @return
   */
  @RequestMapping(value = "/count", method = RequestMethod.POST)
  public @ResponseBody Map<String, Long> count(ConsultationRecordSearchRequest consultationSearch) {
    Long count = null;
    if(consultationSearch.getVisitorHidden() == null && consultationSearch.getElderlyNameHidden() == null
        && consultationSearch.getCheckinIntentionHidden() == null && consultationSearch.getCheckinIntentionHidden() == null
        && consultationSearch.getReturnVisitDateBeginDateHidden() == null
        && consultationSearch.getReturnVisitDateEndDateHidden() == null){
      count = consultationService.count();
    }else {
      count = new Long(consultationService.countByFilter(consultationSearch));
    }
    Map<String, Long> levelMap = new HashMap<String, Long>(); 
    levelMap.put("count", count);
    return levelMap;
  }
  /**
   * 导出列表数据，即用户已经查询出来的数据
   * @param withDays
   */
  @RequestMapping(value = "/exportData", method = {RequestMethod.GET,RequestMethod.POST})
  public void exportData(HttpServletResponse response,  ConsultationRecordSearchRequest consultationSearch) {
    List<ConsultationRecord> consultationList = null;
    if(consultationSearch.getVisitorHidden() == null && consultationSearch.getElderlyNameHidden() == null
        && consultationSearch.getCheckinIntentionHidden() == null && consultationSearch.getCheckinIntentionHidden() == null
        && consultationSearch.getReturnVisitDateBeginDateHidden() == null
        && consultationSearch.getReturnVisitDateEndDateHidden() == null){
      consultationList = consultationService.findAll();
    }else {
      consultationList = consultationService.searchListByFilter(consultationSearch);
    }
    if (consultationList != null && consultationList.size() > 0) {
      String title = "Consultation Record"; // 工作簿标题，同时也是excel文件名前缀
      String[] headers = {"visitor", "phoneNumber", "elderlyName", "gender", "checkinIntention", "relation", "infoChannel", "returnVisit", "returnVisitDate"}; // 需要导出的字段
      String[] headersName = {"咨询人", "电话号码", "老人姓名", "性别", "入住意向", "与老人关系", "信息来源", "是否回访", "回访时间"}; // 字段对应列的列名
      // 导出数据到Excel
      List<Map<String, String>> eventRecordMapList = consultationService.prepareMap(consultationList);
      if (eventRecordMapList.size() > 0) {
        exportListToExcel(response, eventRecordMapList, title, headers, headersName);
      }
    }
  } 
   
}
