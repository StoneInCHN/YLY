package com.yly.controller;

import java.util.ArrayList;
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
import com.yly.entity.ReportNurseLevelStatistics;
import com.yly.framework.filter.Filter;
import com.yly.framework.filter.Filter.Operator;
import com.yly.framework.ordering.Ordering;
import com.yly.framework.ordering.Ordering.Direction;
import com.yly.framework.paging.Pageable;
import com.yly.service.ReportNurseLevelStatisticsService;

/**
 * Controller - 老人事件报表
 * 
 * @author yohu
 *
 */
@Controller("reportNurseLevelStatisticsController")
@RequestMapping("console/reportNurseLevelStatistics")
public class ReportNurseLevelStatisticsController extends BaseController {
  @Resource(name = "reportNurseLevelStatisticsServiceImpl")
  private ReportNurseLevelStatisticsService reportNurseLevelStatisticsService;

  /**
   * 界面展示
   * 
   * @param model
   * @return
   */
  @RequestMapping(value = "/reportNurseLevelStatistics", method = RequestMethod.GET)
  public String list(ModelMap model) {
    return "/report/reportNurseLevelStatistics";
  }

  /**
   * 列表
   * 
   * @param model
   * @param pageable
   * @return
   */
  @RequestMapping(value = "/report", method = RequestMethod.POST)
  public @ResponseBody List<ReportNurseLevelStatistics> list(Model model, Pageable pageable 
      ,Date beginDate, Date endDate) {
    
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
    //时间倒序
    List<Ordering> orderings = new ArrayList<Ordering> ();
    Ordering dateCycleOrdering = new Ordering ("statisticsDate",
        Direction.asc);
    orderings.add (dateCycleOrdering);
    List<ReportNurseLevelStatistics>  reportNurseLevelStatisticsList = reportNurseLevelStatisticsService.findList (null, filters, orderings, true,null);
    return reportNurseLevelStatisticsList;
  }
}
