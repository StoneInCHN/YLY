package com.yly.controller;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
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
import com.yly.entity.ReportElderlyStatus;
import com.yly.framework.ordering.Ordering;
import com.yly.framework.ordering.Ordering.Direction;
import com.yly.framework.paging.Pageable;
import com.yly.service.ReportElderlyStatusService;
import com.yly.utils.ReportDataComparator;

/**
 * Controller - 老人在院情况报表
 * 
 * @author yohu
 *
 */
@Controller("reportElderlyStatusController")
@RequestMapping("console/reportElderlyStatus")
public class ReportElderlyStatusController extends BaseController {
  @Resource(name = "reportElderlyStatusServiceImpl")
  private ReportElderlyStatusService reportElderlyStatusService;

  /**
   * 界面展示
   * 
   * @param model
   * @return
   */
  @RequestMapping(value = "/role", method = RequestMethod.GET)
  public String list(ModelMap model) {
    return "/report/reportElderlyStatus";
  }
  
  /**
   * 首页老人在院情况图
   * 
   * @param model
   * @param pageable
   * @return
   */
  @RequestMapping(value = "/reportElderlyStatusOnMain", method = RequestMethod.POST)
  public @ResponseBody List<ReportElderlyStatus> reportElderlyStatusOnMain(Model model, Pageable pageable) {
    
    List<Ordering> orderingList = new ArrayList<Ordering>();

    //统计倒序，查询最新的一条记录
    Ordering dataOrdering = new Ordering ();
    dataOrdering.setDirection (Direction.desc);
    dataOrdering.setProperty ("statisticsDate");
    orderingList.add (dataOrdering);
    
    List<ReportElderlyStatus>  reportElderlyStatuList = reportElderlyStatusService.findList (1, null, orderingList, true,null);
    return reportElderlyStatuList;
  }
  
  /**
   * 首页每月在院老人人数
   * 
   * @param model
   * @param pageable
   * @return
   */
  @RequestMapping(value = "/reportElderlyInStatusOnMain", method = RequestMethod.POST)
  public @ResponseBody List<ReportElderlyStatus> reportElderlyInStatusOnMain(Model model, Pageable pageable) {
    
    List<Ordering> orderingList = new ArrayList<Ordering>();

    //统计倒序，查询最近12个月记录
    Ordering dataOrdering = new Ordering ();
    dataOrdering.setDirection (Direction.desc);
    dataOrdering.setProperty ("statisticsDate");
    orderingList.add (dataOrdering);
    
    List<ReportElderlyStatus>  reportElderlyStatuList = reportElderlyStatusService.findList (12, null, orderingList, true,null);
    ReportDataComparator comparator =new ReportDataComparator ("statisticsDate");
    Collections.sort (reportElderlyStatuList, comparator);
    return reportElderlyStatuList;
  }
  
  /**
   * 新增老人
   * 
   * @param model
   * @param pageable
   * @return
   */
  @RequestMapping (value = "/reportElderlyNewComming", method = RequestMethod.POST)
  public @ResponseBody Map<String, Object> reportElderlyNewComming (
      Model model, Pageable pageable)
  {

    //上个月在院老人人数
    int lastElderlyCount = 0;
    //本月在院老人人数
    int currentElderlyCount = 0;
    Map<String, Object> map = new HashMap<String, Object> ();

    List<Ordering> orderingList = new ArrayList<Ordering> ();

    //统计倒序，查询最近三个月记录
    Ordering dataOrdering = new Ordering ();
    dataOrdering.setDirection (Direction.desc);
    dataOrdering.setProperty ("statisticsDate");
    orderingList.add (dataOrdering);

    List<ReportElderlyStatus> reportElderlyStatuList = reportElderlyStatusService
        .findList (3, null, orderingList, true, null);

    if (reportElderlyStatuList != null && reportElderlyStatuList.size () == 2)
    {
      currentElderlyCount = reportElderlyStatuList.get (0).getInNursingHome ();
      lastElderlyCount = reportElderlyStatuList.get (1).getInNursingHome ();

      map.put ("newElderlyCount", currentElderlyCount - lastElderlyCount);
      if (lastElderlyCount != 0)
      {
       
        float increasePercent= (currentElderlyCount - lastElderlyCount)
        / (float) lastElderlyCount;   


        NumberFormat nt = NumberFormat.getPercentInstance ();
        //设置百分数精确度2即保留两位小数
        nt.setMinimumFractionDigits(2);

        map.put ("increasePercent",nt.format(Math.abs (increasePercent)));
      }
      else
      {
        map.put ("increasePercent", currentElderlyCount);
      }
    }
    else if (reportElderlyStatuList != null
        && reportElderlyStatuList.size () == 1)
    {

      currentElderlyCount = reportElderlyStatuList.get (0).getInNursingHome ();
      map.put ("newElderlyCount", currentElderlyCount);
      map.put ("increasePercent", currentElderlyCount);
    }
    else
    {
      map.put ("increasePercent", 0);
      map.put ("newElderlyCount", 0);
    }
    return map;
  }
}
