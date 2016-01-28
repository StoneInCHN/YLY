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
import com.yly.entity.ReportRepairRecord;
import com.yly.framework.filter.Filter;
import com.yly.framework.filter.Filter.Operator;
import com.yly.framework.ordering.Ordering;
import com.yly.framework.ordering.Ordering.Direction;
import com.yly.framework.paging.Pageable;
import com.yly.service.ReportRepairRecordService;
import com.yly.utils.ReportDataComparator;

/**
 * Controller - 维修记录统计报表
 * 
 * @author yohu
 *
 */
@Controller("reportRepairRecordController")
@RequestMapping("console/reportRepairRecord")
public class ReportRepairRecordController extends BaseController {
  @Resource(name = "reportRepairRecordServiceImpl")
  private ReportRepairRecordService reportRepairRecordService;

  /**
   * 界面展示
   * 
   * @param model
   * @return
   */
  @RequestMapping(value = "/reportRepairRecord", method = RequestMethod.GET)
  public String list(ModelMap model) {
    return "/report/reportRepairRecord";
  }

  /**
   * 列表
   * 
   * @param model
   * @param pageable
   * @return
   */
  @RequestMapping(value = "/report", method = RequestMethod.POST)
  public @ResponseBody List<ReportRepairRecord> list(Model model, Pageable pageable
      ,Date beginDate, Date endDate) {
    
    //时间倒序
    List<Ordering> orderings = new ArrayList<Ordering> ();
    Ordering dateCycleOrdering = new Ordering ("repairedStatiticsCycle",
        Direction.desc);
    orderings.add (dateCycleOrdering);
    
    List<Filter> filters = new ArrayList<Filter> ();
    if (beginDate != null)
    {
      Filter startDateFilter = new Filter();
      startDateFilter.setOperator (Operator.gt);
      startDateFilter.setProperty ("repairedStatiticsCycle");
      startDateFilter.setValue (beginDate);
      filters.add (startDateFilter);
    }
    
    if (endDate != null)
    {
      Filter endDateFilter = new Filter();
      endDateFilter.setProperty ("repairedStatiticsCycle");
      endDateFilter.setValue (endDate);
      endDateFilter.setOperator (Operator.lt);
      filters.add (endDateFilter);
    }
    
    List<ReportRepairRecord>  reportRepairRecordList = reportRepairRecordService.findList (12, filters, orderings, true,null);
    ReportDataComparator comparator =new ReportDataComparator ("repairedStatiticsCycle");
    Collections.sort (reportRepairRecordList, comparator);
    return reportRepairRecordList;
  }
}
