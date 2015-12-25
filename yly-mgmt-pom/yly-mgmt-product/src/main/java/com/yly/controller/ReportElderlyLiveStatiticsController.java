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
import com.yly.entity.ReportElderlyLiveStatitics;
import com.yly.entity.ReportEvaluatingResult;
import com.yly.framework.paging.Pageable;
import com.yly.service.ReportElderlyLiveStatiticsService;

/**
 * Controller - 老人居住情况统计报表
 * 
 * @author yohu
 *
 */
@Controller("reportElderlyLiveStatiticsController")
@RequestMapping("console/reportElderlyLiveStatitics")
public class ReportElderlyLiveStatiticsController extends BaseController {
  @Resource(name = "reportElderlyLiveStatiticsServiceImpl")
  private ReportElderlyLiveStatiticsService reportElderlyLiveStatiticsService;

  /**
   * 界面展示
   * 
   * @param model
   * @return
   */
  @RequestMapping(value = "/reportElderlyLiveStatitics", method = RequestMethod.GET)
  public String list(ModelMap model) {
    return "/report/reportElderlyLiveStatitics";
  }

  /**
   * 列表
   * 
   * @param model
   * @param pageable
   * @return
   */
  @RequestMapping(value = "/report", method = RequestMethod.POST)
  public @ResponseBody List<ReportElderlyLiveStatitics> report(Model model, Pageable pageable) {
    
    
    List<ReportElderlyLiveStatitics>  reportElderlyLiveStatiticsList = reportElderlyLiveStatiticsService.findList (null, null, null, true,null);
    return reportElderlyLiveStatiticsList;
  }
}
