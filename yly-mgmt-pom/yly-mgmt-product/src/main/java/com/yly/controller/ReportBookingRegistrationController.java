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
import com.yly.entity.ReportBookingRegistration;
import com.yly.framework.ordering.Ordering;
import com.yly.framework.ordering.Ordering.Direction;
import com.yly.framework.paging.Pageable;
import com.yly.service.ReportBookingRegistrationService;

/**
 * Controller - 老人事件报表
 * 
 * @author yohu
 *
 */
@Controller("reportBookingRegistrationController")
@RequestMapping("console/reportBookingRegistration")
public class ReportBookingRegistrationController extends BaseController {
  @Resource(name = "reportBookingRegistrationServiceImpl")
  private ReportBookingRegistrationService reportBookingRegistrationService;

  /**
   * 界面展示
   * 
   * @param model
   * @return
   */
  @RequestMapping(value = "/reportBookingRegistration", method = RequestMethod.GET)
  public String list(ModelMap model) {
    return "/report/reportBookingRegistration";
  }

  /**
   * 列表
   * 
   * @param model
   * @param pageable
   * @return
   */
  @RequestMapping(value = "/report", method = RequestMethod.POST)
  public @ResponseBody List<ReportBookingRegistration> list(Model model, Pageable pageable) {
    
    //时间倒序
    List<Ordering> orderings = new ArrayList<Ordering> ();
    Ordering dateCycleOrdering = new Ordering ("bookingDateStatitics",
        Direction.asc);
    orderings.add (dateCycleOrdering);
    List<ReportBookingRegistration>  reportBookingRegistrationList = reportBookingRegistrationService.findList (24, null, orderings, true,null);
    return reportBookingRegistrationList;
  }
}
