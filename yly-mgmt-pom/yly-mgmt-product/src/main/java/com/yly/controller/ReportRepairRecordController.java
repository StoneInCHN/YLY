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
import com.yly.entity.ReportRepairRecord;
import com.yly.framework.ordering.Ordering;
import com.yly.framework.ordering.Ordering.Direction;
import com.yly.framework.paging.Pageable;
import com.yly.service.ReportRepairRecordService;

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
  public @ResponseBody List<ReportRepairRecord> list(Model model, Pageable pageable) {
    
    //时间倒序
    List<Ordering> orderings = new ArrayList<Ordering> ();
    Ordering dateCycleOrdering = new Ordering ("repairedStatiticsCycle",
        Direction.asc);
    orderings.add (dateCycleOrdering);
    List<ReportRepairRecord>  reportRepairRecordList = reportRepairRecordService.findList (12, null, orderings, true,null);
    return reportRepairRecordList;
  }
}
