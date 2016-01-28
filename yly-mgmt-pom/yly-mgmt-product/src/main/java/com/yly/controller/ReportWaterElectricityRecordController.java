package com.yly.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yly.controller.base.BaseController;
import com.yly.entity.ReportWaterElectricityRecord;
import com.yly.framework.filter.Filter;
import com.yly.framework.filter.Filter.Operator;
import com.yly.framework.ordering.Ordering;
import com.yly.framework.ordering.Ordering.Direction;
import com.yly.framework.paging.Pageable;
import com.yly.service.ReportWaterElectricityRecordService;
import com.yly.utils.ReportDataComparator;

/**
 * Controller - 老人事件报表
 * 
 * @author yohu
 *
 */
@Controller("ReportWaterElectricityRecordController")
@RequestMapping("console/reportWaterElectricityRecord")
public class ReportWaterElectricityRecordController extends BaseController {
  @Resource(name = "reportWaterElectricityRecordServiceImpl")
  private ReportWaterElectricityRecordService reportWaterElectricityRecordService;

  /**
   * 界面展示
   * 
   * @param model
   * @return
   */
  @RequestMapping(value = "/reportWaterElectricityRecord", method = RequestMethod.GET)
  public String list(ModelMap model) {
    return "/report/ReportWaterElectricityRecord";
  }

  /**
   * 列表
   * 
   * @param model
   * @param pageable
   * @return
   */
  @RequestMapping(value = "/report", method = RequestMethod.POST)
  public @ResponseBody List<ReportWaterElectricityRecord> list(Model model, Pageable pageable
      ,Date beginDate, Date endDate) {
    
    //时间倒序
    List<Ordering> orderings = new ArrayList<Ordering> ();
    Ordering dateCycleOrdering = new Ordering ("waterElectricityStatiticsCycle",
        Direction.desc);
    orderings.add (dateCycleOrdering);
    
    List<Filter> filters = new ArrayList<Filter> ();
    if (beginDate != null)
    {
      Filter startDateFilter = new Filter();
      startDateFilter.setOperator (Operator.gt);
      startDateFilter.setProperty ("waterElectricityStatiticsCycle");
      startDateFilter.setValue (beginDate);
      filters.add (startDateFilter);
    }
    
    if (endDate != null)
    {
      Filter endDateFilter = new Filter();
      endDateFilter.setProperty ("waterElectricityStatiticsCycle");
      endDateFilter.setValue (endDate);
      endDateFilter.setOperator (Operator.lt);
      filters.add (endDateFilter);
    }
    
    List<ReportWaterElectricityRecord>  reportWaterElectricityRecordList = reportWaterElectricityRecordService.findList (12, filters, orderings, true,null);
    ReportDataComparator comparator =new ReportDataComparator ("waterElectricityStatiticsCycle");
    Collections.sort (reportWaterElectricityRecordList, comparator);
    return reportWaterElectricityRecordList;
  }
}
