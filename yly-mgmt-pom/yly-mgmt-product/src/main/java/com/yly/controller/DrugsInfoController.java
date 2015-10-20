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
import com.yly.entity.DonateRecord;
import com.yly.entity.DrugsInfo;
import com.yly.entity.SystemConfig;
import com.yly.entity.commonenum.CommonEnum.ConfigKey;
import com.yly.framework.paging.Page;
import com.yly.framework.paging.Pageable;
import com.yly.json.request.DrugsInfoSearchRequest;
import com.yly.service.DrugsInfoService;
import com.yly.service.SystemConfigService;
import com.yly.utils.DateTimeUtils;

/**
 * 药品管理
 * @author huyong
 *
 */
@Controller ("drugsInfoController")
@RequestMapping ("console/drugs")
public class DrugsInfoController extends BaseController
{

  @Resource (name = "drugsInfoServiceImpl")
  private DrugsInfoService drugsService;
  @Resource (name = "systemConfigServiceImpl")
  private SystemConfigService systemConfigService;

  @RequestMapping (value = "/drugsInfo", method = RequestMethod.GET)
  public String list (ModelMap model)
  {
    return "drugsInfo/drugsInfo";
  }

  @RequestMapping (value = "/list", method = RequestMethod.POST)
  public @ResponseBody Page<DrugsInfo> list (Date beginDate, Date endDate,
      String drugName, Pageable pageable, ModelMap model)
  {
    String startDateStr = null;
    String endDateStr = null;

    IKAnalyzer analyzer = new IKAnalyzer ();
    analyzer.setMaxWordLength (true);
    BooleanQuery query = new BooleanQuery ();

    QueryParser nameParser = new QueryParser (Version.LUCENE_35, "name",
        analyzer);
    Query nameQuery = null;
    Filter filter = null;
    if (beginDate != null)
    {
      startDateStr = DateTimeUtils.convertDateToString (beginDate, null);
    }
    if (endDate != null)
    {
      endDateStr = DateTimeUtils.convertDateToString (endDate, null);
    }
    if (drugName != null)
    {
      String text = QueryParser.escape (drugName);
      try
      {
        nameQuery = nameParser.parse (text);
        query.add (nameQuery, Occur.SHOULD);
        
        if (LogUtil.isDebugEnabled (DrugsInfoController.class))
        {
          LogUtil.debug (DrugsInfoController.class, "search", "Search name: "
              + null + ", start date: " + startDateStr + ", end date: "
              + endDateStr);
        }
        if (startDateStr != null || endDateStr != null)
        {
          filter = new TermRangeFilter ("createDate", startDateStr,
              endDateStr, true, true);
        }

        return drugsService.search (query, null, analyzer,filter);
      }
      catch (ParseException e)
      {
        e.printStackTrace ();
      }
    }
      return drugsService.findPage (pageable);
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
    List<Map<String, Object>> drugCategorys = systemConfigService
        .findByConfigKey (ConfigKey.DRUGSCATEGORY, null);
    List<Map<String, Object>> units = systemConfigService.findByConfigKey (
        ConfigKey.UNITS, null);
    List<Map<String, Object>> drugUseMethods = systemConfigService
        .findByConfigKey (ConfigKey.DRUGSMETHOD, null);

    model.addAttribute ("drugs", drugsService.find (id));
    model.addAttribute ("drugCategorys", drugCategorys);
    model.addAttribute ("units", units);
    model.addAttribute ("drugUseMethods", drugUseMethods);
    return "drugsInfo/edit";
  }

  @RequestMapping (value = "/add", method = RequestMethod.POST)
  public @ResponseBody Message add (DrugsInfo drugsInfo, Long drugCategoryId,
      Long conventionalUnitId, Long minUnitId, Long drugUseMethodId)
  {

    SystemConfig drugCategory = systemConfigService.find (drugCategoryId);
    SystemConfig conventionalUnit = systemConfigService
        .find (conventionalUnitId);
    SystemConfig minUnit = systemConfigService.find (minUnitId);
    SystemConfig drugUseMethod = systemConfigService.find (drugUseMethodId);

    if (drugsInfo != null)
    {
      drugsInfo.setDrugCategory (drugCategory);
      drugsInfo.setConventionalUnit (conventionalUnit);
      drugsInfo.setMinUnit (minUnit);
      drugsInfo.setDrugUseMethod (drugUseMethod);
    }

    drugsService.save (drugsInfo);
    return SUCCESS_MESSAGE;
  }

  @RequestMapping (value = "/update", method = RequestMethod.POST)
  public @ResponseBody Message update (DrugsInfo drugsInfo,
      Long drugCategoryId, Long conventionalUnitId, Long minUnitId,
      Long drugUseMethodId)
  {

    SystemConfig drugCategory = systemConfigService.find (drugCategoryId);
    SystemConfig conventionalUnit = systemConfigService
        .find (conventionalUnitId);
    SystemConfig minUnit = systemConfigService.find (minUnitId);
    SystemConfig drugUseMethod = systemConfigService.find (drugUseMethodId);

    if (drugsInfo != null)
    {
      drugsInfo.setDrugCategory (drugCategory);
      drugsInfo.setConventionalUnit (conventionalUnit);
      drugsInfo.setMinUnit (minUnit);
      drugsInfo.setDrugUseMethod (drugUseMethod);
    }
    drugsService.update (drugsInfo, "createDate");
    return SUCCESS_MESSAGE;
  }

  @RequestMapping (value = "/search", method = RequestMethod.POST)
  public @ResponseBody Page<DrugsInfo> search (
      @RequestBody DrugsInfoSearchRequest request)
  {
    String startDateStr = null;
    String endDateStr = null;
    if (request.getStartDate () != null
        && DateTimeUtils.isValidDate (request.getStartDate ()))
    {
      startDateStr = request.getStartDate ();
    }
    if (request.getEndDate () != null
        && DateTimeUtils.isValidDate (request.getEndDate ()))
    {
      endDateStr = request.getEndDate ();
    }
    if (LogUtil.isDebugEnabled (DrugsInfoController.class))
    {
      LogUtil.debug (DrugsInfoController.class, "search", "Search name: "
          + request.getName () + "" + ", start date: " + startDateStr
          + ", end date: " + endDateStr);
    }

    String text = QueryParser.escape (request.getName ());
    IKAnalyzer analyzer = new IKAnalyzer ();
    analyzer.setMaxWordLength (true);

    QueryParser name = new QueryParser (Version.LUCENE_35, "name", analyzer);
    Query nameQuery;
    try
    {
      nameQuery = name.parse (text);
      TermRangeQuery tQuery = new TermRangeQuery ("createDate", startDateStr,
          endDateStr, true, true);

      BooleanQuery query = new BooleanQuery ();
      query.add (nameQuery, Occur.MUST);
      query.add (tQuery, Occur.MUST);
//      return drugsService.search (query, null, analyzer);
    }
    catch (ParseException e)
    {
      e.printStackTrace ();
    }
    return null;

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
      drugsService.delete (ids);
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
    List<Map<String, Object>> drugCategorys = systemConfigService
        .findByConfigKey (ConfigKey.DRUGSCATEGORY, null);
    List<Map<String, Object>> units = systemConfigService.findByConfigKey (
        ConfigKey.UNITS, null);
    List<Map<String, Object>> drugUseMethods = systemConfigService
        .findByConfigKey (ConfigKey.DRUGSMETHOD, null);

    model.addAttribute ("drugs", drugsService.find (id));
    model.addAttribute ("drugCategorys", drugCategorys);
    model.addAttribute ("units", units);
    model.addAttribute ("drugUseMethods", drugUseMethods);
    return "drugsInfo/details";
  }
}
