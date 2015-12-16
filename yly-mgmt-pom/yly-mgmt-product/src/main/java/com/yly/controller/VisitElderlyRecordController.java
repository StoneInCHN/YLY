package com.yly.controller;

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
import com.yly.entity.ElderlyInfo;
import com.yly.entity.VisitElderlyRecord;
import com.yly.framework.paging.Page;
import com.yly.framework.paging.Pageable;
import com.yly.service.ElderlyInfoService;
import com.yly.service.TenantAccountService;
import com.yly.service.VisitElderlyRecordService;
import com.yly.utils.FieldFilterUtils;

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

  @Resource(name = "elderlyInfoServiceImpl")
  private ElderlyInfoService elderlyInfoService;

  /**
   * 列表页面
   * 
   * @param model
   * @return
   */
  @RequestMapping(value = "/visitElderly", method = RequestMethod.GET)
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
  public @ResponseBody Page<Map<String, Object>> list(Date visitDateBeginDate,
      Date visitDateEndDate, VisitElderlyRecord visitElderlyRecord, Pageable pageable,
      ModelMap model) {

    Page<VisitElderlyRecord> page = null;
    if (visitDateBeginDate != null || visitDateEndDate != null
        || visitElderlyRecord.getVisitor() != null
        || visitElderlyRecord.getElderlyInfo() != null) {
      page =
          visitElderlyRecordService.searchElderlyRecord(visitDateBeginDate, visitDateEndDate,
              visitElderlyRecord, pageable);
    } else {
      page = visitElderlyRecordService.findPage(pageable, true);
    }

    String[] properties =
        {"id", "visitor", "visitDate", "dueLeaveDate", "visitPersonnelNumber", "IDCard",
            "phoneNumber", "reasonForVisit", "relation", "remark", "elderlyInfo.id",
            "elderlyInfo.name"};

    List<Map<String, Object>> rows =
        FieldFilterUtils.filterCollectionMap(properties, page.getRows());

    Page<Map<String, Object>> filteredPage =
        new Page<Map<String, Object>>(rows, page.getTotal(), pageable);

    return filteredPage;
  }

  /**
   * 添加
   * 
   * @param visitElderlyRecord
   * @return
   */
  @RequestMapping(value = "/add", method = RequestMethod.POST)
  public @ResponseBody Message add(Long elderlyInfoID, VisitElderlyRecord visitElderlyRecord) {

    ElderlyInfo elderlyInfo = elderlyInfoService.find(elderlyInfoID);

    if (visitElderlyRecord != null) {
      visitElderlyRecord.setTenantID(tenantAccountService.getCurrentTenantID());
      visitElderlyRecord.setElderlyInfo(elderlyInfo);
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
    VisitElderlyRecord visitElderlyRecord = visitElderlyRecordService.find(id);
    model.addAttribute("visitElderlyRecord", visitElderlyRecord);
    return "visitElderly/edit";
  }

  /**
   * 更新
   * 
   * @param visitElderlyRecord
   * @return
   */
  @RequestMapping(value = "/update", method = RequestMethod.POST)
  public @ResponseBody Message update(Long elderlyInfoID, VisitElderlyRecord visitElderlyRecord) {
    ElderlyInfo elderlyInfo = elderlyInfoService.find(elderlyInfoID);
    visitElderlyRecord.setElderlyInfo(elderlyInfo);
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
  /**
   * 导出数据前，计算当前呈现给用户的有多少条数据
   * @return
   */
  @RequestMapping(value = "/count", method = RequestMethod.POST)
  public @ResponseBody Map<String, Long> count(String elderlyNameHidden, String vistorHidden, Date visitDateBeginDateHidden,
      Date visitDateEndDateHidden) {
    Long count = new Long(0);
    count = new Long(visitElderlyRecordService.countByFilter(elderlyNameHidden, vistorHidden, visitDateBeginDateHidden, visitDateEndDateHidden));
    Map<String, Long> levelMap = new HashMap<String, Long>(); 
    levelMap.put("count", count);
    return levelMap;
  }
  /**
   * 导出列表数据，即用户已经查询出来的数据
   * @param withDays
   */
  @RequestMapping(value = "/exportData", method = {RequestMethod.GET,RequestMethod.POST})
  public void exportData(HttpServletResponse response,  String elderlyNameHidden, String vistorHidden, Date visitDateBeginDateHidden,
      Date visitDateEndDateHidden) {
    List<VisitElderlyRecord> visitElderlyRecordList = null;
    visitElderlyRecordList = visitElderlyRecordService.searchListByFilter(elderlyNameHidden, vistorHidden, visitDateBeginDateHidden, visitDateEndDateHidden);
    if (visitElderlyRecordList != null && visitElderlyRecordList.size() > 0) {
      String title = "Visit Elderly Record"; // 工作簿标题，同时也是excel文件名前缀
      String[] headers = {"elderlyInfo.name", "visitor", "IDCard", "phoneNumber", "visitPersonnelNumber", "relation", "visitDate", "dueLeaveDate", "reasonForVisit", "remark"}; // 需要导出的字段
      String[] headersName = {"被探望老人", "来访者", "身份证号码", "电话号码", "来访人数", "与老人关系", "来访时间", "预计离开时间", "来访原因", "备注"}; // 字段对应列的列名
      // 导出数据到Excel
      List<Map<String, String>> eventRecordMapList = visitElderlyRecordService.prepareMap(visitElderlyRecordList);
      if (eventRecordMapList.size() > 0) {
        exportListToExcel(response, eventRecordMapList, title, headers, headersName);
      }
    }
  } 
}
