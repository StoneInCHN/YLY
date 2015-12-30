package com.yly.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yly.controller.base.BaseController;
import com.yly.entity.ReportDonateStatistics;
import com.yly.framework.ordering.Ordering;
import com.yly.framework.ordering.Ordering.Direction;
import com.yly.framework.paging.Pageable;
import com.yly.service.ReportDonateStatisticsService;

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
  public @ResponseBody List<ReportDonateStatistics> list(Model model, Pageable pageable) {
    
    //时间倒序
    List<Ordering> orderings = new ArrayList<Ordering> ();
    Ordering dateCycleOrdering = new Ordering ("donateStatisticsCycle",
        Direction.asc);
    orderings.add (dateCycleOrdering);
    List<ReportDonateStatistics>  reportDonateStatisticList = reportDonateStatisticsService.findList (null, null, orderings, true,null);
    return reportDonateStatisticList;
  }
}
