package com.yly.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yly.controller.base.BaseController;
import com.yly.entity.ReportElderlyAgeStatus;
import com.yly.framework.paging.Pageable;
import com.yly.service.ReportElderlyAgeStatusService;

/**
 * Controller - 老人在院情况报表
 * 
 * @author yohu
 *
 */
@Controller("reportElderlyAgeStatusController")
@RequestMapping("console/reportElderlyAgeStatus")
public class ReportElderlyAgeStatusController extends BaseController {
  @Resource(name = "reportElderlyAgeStatusServiceImpl")
  private ReportElderlyAgeStatusService reportElderlyAgeStatusService;

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
   * 列表
   * 
   * @param model
   * @param pageable
   * @return
   */
  @RequestMapping(value = "/list", method = RequestMethod.POST)
  public @ResponseBody List<ReportElderlyAgeStatus> list(Model model, Pageable pageable) {
    List<ReportElderlyAgeStatus>  reportElderlyAgeStatuList = reportElderlyAgeStatusService.findList (null, null, null, true,null);
    return reportElderlyAgeStatuList;
  }
}
