package com.yly.controller;

import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yly.beans.Message;
import com.yly.controller.base.BaseController;
import com.yly.entity.ElderlyEventRecord;
import com.yly.entity.ElderlyInfo;
import com.yly.framework.paging.Page;
import com.yly.framework.paging.Pageable;
import com.yly.service.ElderlyEventRecordService;
import com.yly.service.ElderlyInfoService;
import com.yly.service.TenantAccountService;
import com.yly.utils.DateTimeUtils;
import com.yly.utils.ExportExcel;
import com.yly.utils.FieldFilterUtils;
import com.yly.utils.ToolsUtils;

/**
 * 老人事件controller
 * 
 * @author luzhang
 *
 */
@Controller("elderlyEventRecordController")
@RequestMapping("/console/elderlyEventRecord")
public class ElderlyEventRecordController extends BaseController {
 
  @Resource(name = "elderlyEventRecordServiceImpl")
  private ElderlyEventRecordService elderlyEventRecordService;

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
  @RequestMapping(value = "/elderlyEventRecord", method = RequestMethod.GET)
  public String elderlyEventRecord(ModelMap model) {
    return "/elderlyEventRecord/elderlyEventRecord";
  }

  /**
   * 查询list
   * 
   * @param pageable
   * @return
   */
  @RequestMapping(value = "/list", method = RequestMethod.POST)
  public @ResponseBody Page<ElderlyEventRecord> list(String keysOfElderlyName, Date beginDate,
      Date endDate, Pageable pageable) {
    if (keysOfElderlyName == null && beginDate == null && endDate == null) {
      return elderlyEventRecordService.findPage(pageable, true);
    } else {
      return elderlyEventRecordService.SearchPageByFilter(keysOfElderlyName, beginDate, endDate,
          pageable);
    }
  }

  @RequestMapping(value = "/findAll", method = RequestMethod.POST)
  public @ResponseBody List<Map<String, Object>> findAll() {
    String[] propertys = {"id", "buildingName"};
    return FieldFilterUtils.filterCollectionMap(propertys, elderlyEventRecordService.findAll(true));
  }


  /**
   * 编辑
   * 
   * @param model
   * @param id
   * @return
   */
  @RequestMapping(value = "/detail", method = RequestMethod.GET)
  public String edit(ModelMap model, Long id, String handle) {
    if (id != null && handle != null) {
      model.addAttribute("elderlyEventRecord", elderlyEventRecordService.find(id));
      return "elderlyEventRecord/" + handle;
    }
    return "";
  }

  /**
   * 添加
   * 
   * @param elderlyEventRecord
   * @return
   */
  @RequestMapping(value = "/add", method = RequestMethod.POST)
  public @ResponseBody Message save(ElderlyEventRecord elderlyEventRecord, Long elderlyInfoID) {
    ElderlyInfo elderlyInfo = elderlyInfoService.find(elderlyInfoID);
    if (elderlyInfo != null && elderlyEventRecord != null) {
      elderlyEventRecord.setEventDate(ToolsUtils.addTime(elderlyEventRecord.getEventDate(),
          Calendar.HOUR, 8));// 加8个小时
      elderlyEventRecord.setElderlyInfo(elderlyInfo);
      elderlyEventRecord.setTenantID(tenantAccountService.getCurrentTenantID());
      if (elderlyEventRecord.getEventContent() != null) {
        elderlyEventRecordService.save(elderlyEventRecord);
        return SUCCESS_MESSAGE;
      }
    }
    return ERROR_MESSAGE;
  }

  /**
   * 更新
   * 
   * @param elderlyEventRecord
   * @param elderlyInfoID
   * @return
   */
  @RequestMapping(value = "/update", method = RequestMethod.POST)
  public @ResponseBody Message update(ElderlyEventRecord elderlyEventRecord, Long elderlyInfoID) {
    ElderlyInfo elderlyInfo = elderlyInfoService.find(elderlyInfoID);
    if (elderlyInfo != null) {
      elderlyEventRecord.setElderlyInfo(elderlyInfo);
      elderlyEventRecord.setTenantID(tenantAccountService.getCurrentTenantID());
      elderlyEventRecordService.update(elderlyEventRecord, "createDate");
      return SUCCESS_MESSAGE;
    }
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
      for (Long id : ids) {
        ElderlyEventRecord elderlyEventRecord = elderlyEventRecordService.find(id);
        if (elderlyEventRecord != null) {
          elderlyEventRecordService.delete(ids);
        }
      }
    }
    return SUCCESS_MESSAGE;
  }
  
//  @RequestMapping(value = "/exportData", method = {RequestMethod.GET,RequestMethod.POST})
//  public void exportData(HttpServletResponse response) {
//      List<ElderlyEventRecord> eventRecordList = elderlyEventRecordService.findAll();
//      Long startLong = System.currentTimeMillis();
//      if (eventRecordList != null && eventRecordList.size() > 0) {
//        String title = "Elderly Event Record"; //工作簿标题，同时也是excel文件名前缀
//        String[] headers = {"elderlyInfo.name","operator","eventDate","eventContent"};  //需要导出的字段 
//        String[] headersName = {"老人姓名","记录人","事件发生时间","事件内容"}; //字段对应列的列名
//        //导出数据到Excel
//        //List<Map<String, String>> eventRecordMapList = prepareMap(eventRecordList);
//        if (eventRecordList.size() > 0) {
//          exportListToExcel(response, eventRecordList, title, headers, headersName);  
//        }
//      }
//      Long endLong = System.currentTimeMillis();
//      System.out.println("end - start = "+ (endLong - startLong));
//  } 
  /**
   * 导出列表数据
   * @param withDays
   */
  @RequestMapping(value = "/exportData", method = {RequestMethod.GET,RequestMethod.POST})
  public void exportData(HttpServletResponse response) {
      List<ElderlyEventRecord> eventRecordList = elderlyEventRecordService.findAll();
      Long startLong = System.currentTimeMillis();
      if (eventRecordList != null && eventRecordList.size() > 0) {
        String title = "Elderly Event Record"; //工作簿标题，同时也是excel文件名前缀
        String[] headers = {"elderlyInfo.name","operator","eventDate","eventContent"};  //需要导出的字段 
        String[] headersName = {"老人姓名","记录人","事件发生时间","事件内容"}; //字段对应列的列名
        //导出数据到Excel
        List<Map<String, String>> eventRecordMapList = prepareMap(eventRecordList);
        if (eventRecordMapList.size() > 0) {
          exportListToExcel(response, eventRecordMapList, title, headers, headersName);  
        }
      }
      Long endLong = System.currentTimeMillis();
      System.out.println("end - start = "+ (endLong - startLong));
  } 
  /**
   * 准备数据，将list转化成HashMap,作为需要导出的数据
   * @param eventRecordList
   * @return
   */
  private List<Map<String, String>> prepareMap(List<ElderlyEventRecord> eventRecordList){
    
    List<Map<String, String>> mapList = new ArrayList<Map<String,String>>();
    
    for (ElderlyEventRecord eventRecord : eventRecordList) {
      Map<String, String> eventRecordMap = new HashMap<String, String>();
      eventRecordMap.put("elderlyInfo.name", eventRecord.getElderlyInfo()!=null?eventRecord.getElderlyInfo().getName():"");
      eventRecordMap.put("operator", eventRecord.getOperator());
      eventRecordMap.put("eventContent", eventRecord.getEventContent());
      eventRecordMap.put("eventDate", DateTimeUtils.getSimpleFormatString(DateTimeUtils.shortDateFormat, eventRecord.getEventDate()));
      mapList.add(eventRecordMap);
    }
    
    return mapList;
  }
}
