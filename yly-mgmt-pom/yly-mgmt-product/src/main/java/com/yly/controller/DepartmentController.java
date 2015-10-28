package com.yly.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.Filter;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermRangeFilter;
import org.apache.lucene.search.TermRangeQuery;
import org.apache.lucene.util.Version;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.yly.beans.Message;
import com.yly.common.log.LogUtil;
import com.yly.controller.base.BaseController;
import com.yly.entity.Department;
import com.yly.entity.DonateRecord;
import com.yly.entity.DrugsInfo;
import com.yly.entity.SystemConfig;
import com.yly.entity.commonenum.CommonEnum.ConfigKey;
import com.yly.framework.paging.Page;
import com.yly.framework.paging.Pageable;
import com.yly.json.request.DrugsInfoSearchRequest;
import com.yly.service.DepartmentService;
import com.yly.service.DrugsInfoService;
import com.yly.service.SystemConfigService;
import com.yly.utils.DateTimeUtils;

/**
 * 部门管理
 * @author huyong
 *
 */
@Controller ("departmentController")
@RequestMapping ("console/department")
public class DepartmentController extends BaseController
{

  @Resource (name = "departmentServiceImpl")
  private DepartmentService departmentService;

  @RequestMapping (value = "/department", method = RequestMethod.GET)
  public String list (ModelMap model)
  {
    return "department/department";
  }

  @RequestMapping (value = "/list", method = RequestMethod.POST)
  public @ResponseBody Page<Department> list (Date beginDate, Date endDate,
      String drugName, Pageable pageable, ModelMap model)
  {
    
      return departmentService.findPage (pageable);
  }

  /**
   * get data for vendor edit page
   * 
   * @param model
   * @param vendorId
   * @return
   */
  @RequestMapping (value = "/edit", method = RequestMethod.GET)
  public String edit (ModelMap model, Long id)
  {
    

    model.addAttribute ("department", departmentService.find (id));

    return "department/edit";
  }

  @RequestMapping (value = "/add", method = RequestMethod.POST)
  public @ResponseBody Message add (Department department)
  {


    departmentService.save (department);
    return SUCCESS_MESSAGE;
  }

  @RequestMapping (value = "/update", method = RequestMethod.POST)
  public @ResponseBody Message update (Department department,
      Long drugCategoryId, Long conventionalUnitId, Long minUnitId,
      Long drugUseMethodId)
  {

    departmentService.update (department, "createDate");
    return SUCCESS_MESSAGE;
  }


  /**
   * 删除
   */
  @RequestMapping (value = "/delete", method = RequestMethod.POST)
  public @ResponseBody Message delete (Long[] ids)
  {
    if (ids != null)
    {
      // 检查是否能被删除
      // if()
      departmentService.delete (ids);
    }
    return SUCCESS_MESSAGE;
  }
  /**
   * 获取数据进入详情页面
   * 
   * @param model
   * @param id
   * @return
   */
  @RequestMapping(value = "/details", method = RequestMethod.GET)
  public String details(ModelMap model, Long id) {

    model.addAttribute ("department", departmentService.find (id));
    return "department/details";
  }
  
  @RequestMapping(value = "/findAllDepartments", method = RequestMethod.POST)
  public @ResponseBody List<Map<String, Object>> findAddDepartments() {
    return departmentService.findAllDepartments ();
  }
}
