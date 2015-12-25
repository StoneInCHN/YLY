package com.yly.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yly.controller.base.BaseController;
import com.yly.entity.ReportElderlyGenderRate;
import com.yly.framework.paging.Pageable;
import com.yly.service.ReportElderlyGenderRateService;

/**
 * Controller - 老人年龄比例统计报表
 * 
 * @author yohu
 *
 */
@Controller("reportElderlyGenderRateController")
@RequestMapping("console/reportElderlyGenderRate")
public class ReportElderlyGenderRateController extends BaseController {
  @Resource(name = "reportElderlyGenderRateServiceImpl")
  private ReportElderlyGenderRateService reportElderlyGenderRateService;

   /**
   * 列表
   * 
   * @param model
   * @param pageable
   * @return
   */
  @RequestMapping(value = "/list", method = RequestMethod.POST)
  public @ResponseBody List<ReportElderlyGenderRate> list(Model model, Pageable pageable) {
    List<ReportElderlyGenderRate>  reportElderlyGenderRateList = reportElderlyGenderRateService.findList (null, null, null, true,null);
    return reportElderlyGenderRateList;
  }
}
