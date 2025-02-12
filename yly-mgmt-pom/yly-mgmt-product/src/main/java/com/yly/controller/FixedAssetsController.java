package com.yly.controller;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.lucene.index.Term;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.Filter;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TermRangeFilter;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.TermRangeQuery;
import org.apache.lucene.util.Version;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.yly.beans.Message;
import com.yly.common.log.LogUtil;
import com.yly.controller.base.BaseController;
import com.yly.entity.Department;
import com.yly.entity.Deposit;
import com.yly.entity.DonateRecord;
import com.yly.entity.FixedAssets;
import com.yly.framework.paging.Page;
import com.yly.framework.paging.Pageable;
import com.yly.service.DepartmentService;
import com.yly.service.DonateRecordService;
import com.yly.service.FixedAssetsService;
import com.yly.utils.DateTimeUtils;

/**
 * 固定资产
 * @author huyong
 *
 */
@Controller ("fixedAssetsController")
@RequestMapping ("console/fixedAssets")
public class FixedAssetsController extends BaseController
{

  @Resource (name = "fixedAssetsServiceImpl")
  private FixedAssetsService fixedAssetsService;
  
  @Resource(name = "departmentServiceImpl")
  private DepartmentService departmentService;


  @RequestMapping (value = "/fixedAssets", method = RequestMethod.GET)
  public String list (ModelMap model)
  {
    return "fixedAssets/fixedAssets";
  }

  @RequestMapping (value = "/list", method = RequestMethod.POST)
  public @ResponseBody Page<FixedAssets> list (String assetName,Date beginDate, Date endDate,
     String departmentSearchId, Pageable pageable, ModelMap model)
  {
    String startDateStr = null;
    String endDateStr = null;

    IKAnalyzer analyzer = new IKAnalyzer ();
    analyzer.setMaxWordLength (true);
    BooleanQuery query = new BooleanQuery ();

    QueryParser nameParser = new QueryParser (Version.LUCENE_35, "assetName",
        analyzer);
    Query nameQuery = null;
    TermRangeQuery rangeQuery = null;
    Filter filter = null;
    if (beginDate != null)
    {
      startDateStr = DateTimeUtils.convertDateToString (beginDate, null);
    }
    if (endDate != null)
    {
      endDateStr = DateTimeUtils.convertDateToString (endDate, null);
    }
    if (assetName != null)
    {
      String text = QueryParser.escape (assetName);
        try
        {
          nameQuery = nameParser.parse (text);
          query.add (nameQuery, Occur.MUST);
          
          if (LogUtil.isDebugEnabled (FixedAssetsController.class))
          {
            LogUtil.debug (FixedAssetsController.class, "search", "Search assetName: "
                + assetName );
          }
        }
        catch (ParseException e)
        {
          e.printStackTrace();
        }
        
    }
    //过滤部门
    if (departmentSearchId != null)
    {
      TermQuery departmentQuery = new TermQuery(new Term("department.id", departmentSearchId));  
      query.add (departmentQuery,Occur.MUST);
    }
    
    if (startDateStr != null || endDateStr != null)
    {
      rangeQuery = new TermRangeQuery ("assetTime", startDateStr, endDateStr, true, true);
      query.add (rangeQuery,Occur.MUST);
      
      if (LogUtil.isDebugEnabled (FixedAssetsController.class))
      {
        LogUtil.debug (FixedAssetsController.class, "search", "Search start date: "+startDateStr
            +" end date: "+endDateStr);
      }
    }
    if (nameQuery != null || rangeQuery != null || departmentSearchId != null)
    {
      return fixedAssetsService.search (query, pageable, analyzer,filter,true);
    }
    Page<FixedAssets> fixedAssetsPage=fixedAssetsService.findPage (pageable,true);
    return fixedAssetsPage;
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
    model.put ("fixedAssets", fixedAssetsService.find (id));
    return "fixedAssets/edit";
  }

  @RequestMapping (value = "/add", method = RequestMethod.POST)
  public @ResponseBody Message add (FixedAssets fixedAssets,Long departmentId)
  {
    Department department = departmentService.find (departmentId);
    fixedAssets.setDepartment (department);
    fixedAssetsService.save (fixedAssets,true);
    return SUCCESS_MESSAGE;
  }

  @RequestMapping (value = "/update", method = RequestMethod.POST)
  public @ResponseBody Message update (FixedAssets fixedAssets,Long departmentId)
  {
    Department department =departmentService.find (departmentId);
    fixedAssets.setDepartment (department);
    fixedAssetsService.update (fixedAssets,"createDate","tenantID");
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
      fixedAssetsService.delete (ids);
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
    FixedAssets record = fixedAssetsService.find(id);
    model.addAttribute("fixedAssets", record);
    return "fixedAssets/details";
  }
}
