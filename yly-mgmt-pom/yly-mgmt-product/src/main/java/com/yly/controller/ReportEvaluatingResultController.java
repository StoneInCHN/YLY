package com.yly.controller;

import java.util.ArrayList;
import java.util.Date;
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
import com.yly.entity.EvaluatingForm;
import com.yly.entity.FixedAssets;
import com.yly.entity.ReportEvaluatingResult;
import com.yly.entity.commonenum.CommonEnum.EvaluatingFormStatus;
import com.yly.entity.commonenum.CommonEnum.EvaluatingFormType;
import com.yly.framework.filter.Filter;
import com.yly.framework.filter.Filter.Operator;
import com.yly.framework.ordering.Ordering;
import com.yly.framework.ordering.Ordering.Direction;
import com.yly.framework.paging.Page;
import com.yly.framework.paging.Pageable;
import com.yly.service.EvaluatingFormService;
import com.yly.service.ReportEvaluatingResultService;
import com.yly.utils.FieldFilterUtils;
import com.yly.utils.ReportDataComparator;

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
  
  @Resource(name = "evaluatingFormServiceImpl")
  private EvaluatingFormService evaluatingFormService;

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
   * 报表 自定义卷
   * 
   * @param model
   * @param pageable
   * @return
   * @throws Exception 
   */
  @RequestMapping(value = "/report", method = RequestMethod.POST)
  public @ResponseBody List<ReportEvaluatingResult> report(Long evaluatingFormId,Model model, Pageable pageable) {
    
    
    EvaluatingForm form=evaluatingFormService.find (evaluatingFormId);
    
    if (form == null)
    {
      List<Filter> formFilters = new ArrayList<Filter> ();
      
      Filter statusFilter = new Filter();
      
      statusFilter.setOperator (Operator.eq);
      statusFilter.setProperty ("evaluatingFormStatus");
      statusFilter.setValue (EvaluatingFormStatus.ENABLE);
      
      formFilters.add (statusFilter);
      
     List<EvaluatingForm> formList= evaluatingFormService.findList (null, formFilters, null, true, null);
     if (formList != null && formList.size () == 1)
    {
      form = formList.get (0);
    }else {
      return null;
    }
     
    }
    List<Filter> filters = new ArrayList<Filter>();
    //自定义卷
    Filter evaluateTypefilter = new Filter();
    evaluateTypefilter.setOperator (Operator.eq);
    evaluateTypefilter.setProperty ("evaluatingType");
    evaluateTypefilter.setValue (form.getEvaluatingFormType ());
    //Form 名称
    Filter evaluateFormfilter = new Filter();
    evaluateFormfilter.setOperator (Operator.eq);
    evaluateFormfilter.setProperty ("evaluatingFormId");
    evaluateFormfilter.setValue (form.getId ());
    
    filters.add (evaluateTypefilter);
    filters.add (evaluateFormfilter);
    
    List<Ordering> orderings = new ArrayList<Ordering> ();
    
    Ordering order = new Ordering ("evaluatingResultName", Direction.desc);
    
    orderings.add (order);
    
    List<ReportEvaluatingResult>  reportEvluatingResultList = reportEvaluatingResultService.findList (null, filters, orderings, true,null);
    
    return reportEvluatingResultList;
  }
  
  @RequestMapping(value = "/findAllFormName", method = RequestMethod.POST)
  public @ResponseBody List<Map<String, Object>> findAllFormName(Model model) {
  
    Filter evaluateTypefilter = new Filter();
    evaluateTypefilter.setOperator (Operator.eq);
    evaluateTypefilter.setProperty ("evaluatingType");
    evaluateTypefilter.setValue (EvaluatingFormType.CUSTOM_FORM);
    
    List<EvaluatingForm> evaluatingFormList = evaluatingFormService.findAll (true);
    
    String[] properties =
      {"id", "formName","evaluatingFormStatus"};

  List<Map<String, Object>> rows =
      FieldFilterUtils.filterCollectionMap(properties, evaluatingFormList);
  return rows;
  }
}
