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
import com.yly.entity.ReportElderlyMedicalRecord;
import com.yly.framework.ordering.Ordering;
import com.yly.framework.ordering.Ordering.Direction;
import com.yly.framework.paging.Pageable;
import com.yly.service.ReportElderlyMedicalRecordService;

/**
 * Controller - 老人看病情况报表
 * 
 * @author yohu
 *
 */
@Controller ("reportElderlyMedicalRecordController")
@RequestMapping ("console/reportElderlyMedicalRecord")
public class ReportElderlyMedicalRecordController extends BaseController
{
  @Resource (name = "reportElderlyMedicalRecordServiceImpl")
  private ReportElderlyMedicalRecordService reportElderlyMedicalRecordService;

  /**
   * 界面展示
   * 
   * @param model
   * @return
   */
  @RequestMapping (value = "/reportElderlyMedicalRecord", method = RequestMethod.GET)
  public String list (ModelMap model)
  {
    return "/report/reportElderlyMedicalRecord";
  }

  /**
   * 列表
   * 
   * @param model
   * @param pageable
   * @return
   */
  @RequestMapping (value = "/report", method = RequestMethod.POST)
  public @ResponseBody List<ReportElderlyMedicalRecord> list (Model model,
      Pageable pageable)
  {

    //时间倒序
    List<Ordering> orderings = new ArrayList<Ordering> ();
    Ordering dateCycleOrdering = new Ordering ("medicalStatiticsCycle",
        Direction.asc);
    orderings.add (dateCycleOrdering);

    List<ReportElderlyMedicalRecord> reportElderlyMedicalRecordList = reportElderlyMedicalRecordService
        .findList (12, null, orderings, true, null);
    return reportElderlyMedicalRecordList;
  }
}
