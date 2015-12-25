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
import com.yly.entity.ReportElderlyStatus;
import com.yly.framework.paging.Pageable;
import com.yly.service.ReportElderlyStatusService;

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
   * 列表
   * 
   * @param model
   * @param pageable
   * @return
   */
  @RequestMapping(value = "/list", method = RequestMethod.POST)
  public @ResponseBody List<ReportElderlyStatus> list(Model model, Pageable pageable) {
    List<ReportElderlyStatus>  reportElderlyStatuList = reportElderlyStatusService.findList (null, null, null, true,null);
    return reportElderlyStatuList;
  }
}
