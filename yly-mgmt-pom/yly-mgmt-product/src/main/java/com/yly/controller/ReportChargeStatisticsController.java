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
import com.yly.entity.ReportChargeStatistics;
import com.yly.entity.ReportRepairRecord;
import com.yly.framework.filter.Filter;
import com.yly.framework.filter.Filter.Operator;
import com.yly.framework.ordering.Ordering;
import com.yly.framework.ordering.Ordering.Direction;
import com.yly.framework.paging.Pageable;
import com.yly.service.ReportChargeStatisticsService;
import com.yly.utils.ReportDataComparator;

/**
 * Controller - 缴费报表
 * 
 * @author yohu
 *
 */
@Controller("reportChargeStatisticsController")
@RequestMapping("console/reportChargeStatistics")
public class ReportChargeStatisticsController extends BaseController {
  @Resource(name = "reportChargeStatisticsServiceImpl")
  private ReportChargeStatisticsService reportChargeStatisticsService;

  /**
   * 界面展示
   * 
   * @param model
   * @return
   */
  @RequestMapping(value = "/reportChargeStatistics", method = RequestMethod.GET)
  public String list(ModelMap model) {
    return "/report/reportChargeStatistics";
  }

  /**
   * 列表
   * 
   * @param model
   * @param pageable
   * @return
   */
  @RequestMapping(value = "/report", method = RequestMethod.POST)
  public @ResponseBody List<ReportChargeStatistics> list(Model model, Pageable pageable
      ,Date beginDate, Date endDate) {
    
    //时间倒序
    List<Ordering> orderings = new ArrayList<Ordering> ();
    Ordering dateCycleOrdering = new Ordering ("statisticsDate",
        Direction.desc);
    orderings.add (dateCycleOrdering);
    
    List<Filter> filters = new ArrayList<Filter> ();
    if (beginDate != null)
    {
      Filter startDateFilter = new Filter();
      startDateFilter.setOperator (Operator.gt);
      startDateFilter.setProperty ("statisticsDate");
      startDateFilter.setValue (beginDate);
      filters.add (startDateFilter);
    }
    
    if (endDate != null)
    {
      Filter endDateFilter = new Filter();
      endDateFilter.setProperty ("statisticsDate");
      endDateFilter.setValue (endDate);
      endDateFilter.setOperator (Operator.lt);
      filters.add (endDateFilter);
    }
    
    List<ReportChargeStatistics>  reportChargeStatisticsList = reportChargeStatisticsService.findList (12, filters, orderings, true,null);
    ReportDataComparator comparator =new ReportDataComparator ("statisticsDate");
    Collections.sort (reportChargeStatisticsList, comparator);
    return reportChargeStatisticsList;
  }
}
