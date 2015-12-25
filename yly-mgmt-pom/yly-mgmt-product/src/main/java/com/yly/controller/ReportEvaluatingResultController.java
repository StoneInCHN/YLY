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
import com.yly.entity.ReportEvaluatingResult;
import com.yly.entity.commonenum.CommonEnum.EvaluatingFormType;
import com.yly.framework.filter.Filter;
import com.yly.framework.filter.Filter.Operator;
import com.yly.framework.ordering.Ordering;
import com.yly.framework.ordering.Ordering.Direction;
import com.yly.framework.paging.Pageable;
import com.yly.service.ReportEvaluatingResultService;

/**
 * Controller - 老人评估结果统计报表
 * 
 * @author yohu
 *
 */
@Controller("reportEvluatingResultController")
@RequestMapping("console/reportEvaluatingResult")
public class ReportEvaluatingResultController extends BaseController {
  @Resource(name = "reportEvaluatingResultServiceImpl")
  private ReportEvaluatingResultService reportEvaluatingResultService;

  /**
   * 界面展示
   * 
   * @param model
   * @return
   */
  @RequestMapping(value = "/reportEvaluatingResult", method = RequestMethod.GET)
  public String list(ModelMap model) {
    return "/report/reportEvaluatingResult";
  }

  /**
   * 列表
   * 
   * @param model
   * @param pageable
   * @return
   */
  @RequestMapping(value = "/report", method = RequestMethod.POST)
  public @ResponseBody List<ReportEvaluatingResult> report(Model model, Pageable pageable) {
    
    List<Filter> filters = new ArrayList<Filter>();
    
    Filter evaluateTypefilter = new Filter();
    evaluateTypefilter.setOperator (Operator.eq);
    evaluateTypefilter.setProperty ("evaluatingType");
    evaluateTypefilter.setValue (EvaluatingFormType.SYSTEM_FORM);
    
    filters.add (evaluateTypefilter);
    
    List<Ordering> orderings = new ArrayList<Ordering> ();
    
    Ordering order = new Ordering ("evaluatingResultName", Direction.asc);
    
    orderings.add (order);
    
    List<ReportEvaluatingResult>  reportEvluatingResultList = reportEvaluatingResultService.findList (null, filters, orderings, true,null);
    return reportEvluatingResultList;
  }
}
