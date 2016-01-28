package com.yly.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yly.controller.base.BaseController;
import com.yly.entity.ReportDonateStatistics;
import com.yly.framework.filter.Filter;
import com.yly.framework.filter.Filter.Operator;
import com.yly.framework.ordering.Ordering;
import com.yly.framework.ordering.Ordering.Direction;
import com.yly.framework.paging.Pageable;
import com.yly.service.ReportDonateStatisticsService;
import com.yly.utils.ReportDataComparator;

/**
 * Controller - 捐赠统计报表
 * 
 * @author yohu
 *
 */
@Controller("reportDonateStatisticsController")
@RequestMapping("console/reportDonateStatistics")
public class ReportDonateStatisticsController extends BaseController {
  @Resource(name = "reportDonateStatisticsServiceImpl")
  private ReportDonateStatisticsService reportDonateStatisticsService;

  /**
   * 界面展示
   * 
   * @param model
   * @return
   */
  @RequestMapping(value = "/reportDonateStatistics", method = RequestMethod.GET)
  public String list(ModelMap model) {
    return "/report/reportDonateStatistics";
  }

  /**
   * 列表
   * 
   * @param model
   * @param pageable
   * @return
   */
  @RequestMapping(value = "/report", method = RequestMethod.POST)
  public @ResponseBody List<ReportDonateStatistics> report(Date donateStatisticsCycle,Model model, Pageable pageable) {
    
    //时间倒序
    List<Ordering> orderings = new ArrayList<Ordering> ();
    Ordering dateCycleOrdering = new Ordering ("donateStatisticsCycle",
        Direction.desc);
    orderings.add (dateCycleOrdering);
    
    //filter
    List<Filter> filters = new ArrayList<Filter>();
    
    if (dateCycleOrdering != null)
    {
      Filter dateCycleFilter = new Filter();
      dateCycleFilter.setOperator (Operator.eq);
      dateCycleFilter.setProperty ("donateStatisticsCycle");
      dateCycleFilter.setValue (donateStatisticsCycle);
      
      filters.add (dateCycleFilter);
    }
   
    
    List<ReportDonateStatistics>  reportDonateStatisticList = reportDonateStatisticsService.findList (null, filters, orderings, true,null);
    ReportDataComparator comparator =new ReportDataComparator ("donateStatisticsCycle");
    Collections.sort (reportDonateStatisticList, comparator);
    
    return reportDonateStatisticList;
  }
  
}
