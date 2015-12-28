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
import com.yly.entity.ReportWaterElectricityRecord;
import com.yly.framework.ordering.Ordering;
import com.yly.framework.ordering.Ordering.Direction;
import com.yly.framework.paging.Pageable;
import com.yly.service.ReportWaterElectricityRecordService;

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
  public @ResponseBody List<ReportWaterElectricityRecord> list(Model model, Pageable pageable) {
    
    //时间倒序
    List<Ordering> orderings = new ArrayList<Ordering> ();
    Ordering dateCycleOrdering = new Ordering ("waterElectricityStatiticsCycle",
        Direction.desc);
    orderings.add (dateCycleOrdering);
    List<ReportWaterElectricityRecord>  reportWaterElectricityRecordList = reportWaterElectricityRecordService.findList (12, null, orderings, true,null);
    return reportWaterElectricityRecordList;
  }
}
