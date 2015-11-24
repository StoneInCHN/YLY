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
import com.yly.entity.DrugsInfo;
import com.yly.entity.SystemConfig;
import com.yly.entity.commonenum.CommonEnum.ConfigKey;
import com.yly.framework.paging.Page;
import com.yly.framework.paging.Pageable;
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
    QueryParser phoneticCodeParser = new QueryParser (Version.LUCENE_35, "phoneticCode",
        analyzer);
    QueryParser aliasParser = new QueryParser (Version.LUCENE_35, "phoneticCode",
        analyzer);
    Query nameQuery = null;
    Query phoneticCodeQuery = null;
    Query aliasQuery = null;
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
        phoneticCodeQuery=phoneticCodeParser.parse (text);
        aliasQuery = aliasParser.parse (text);
        
        query.add (nameQuery, Occur.SHOULD);
        query.add (phoneticCodeQuery,Occur.SHOULD);
        query.add (aliasQuery,Occur.SHOULD);
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

        return drugsService.search (query, null, analyzer,filter,true);
      }
      catch (ParseException e)
      {
        e.printStackTrace ();
      }
    }
      return drugsService.findPage (pageable,true);
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
    drugsService.update (drugsInfo,"createDate","tenantID");
    return SUCCESS_MESSAGE;
  }

  @RequestMapping (value = "/drugsSearch", method = RequestMethod.GET)
  public String drugsSearch ()
  {
    return "prescription/addDrugs";
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
  @RequestMapping (value = "/drugsSearch", method = RequestMethod.POST)
  public @ResponseBody Page<DrugsInfo> drugsSerach (Date allDrugsBeginDate, Date allDrugsEndDate,
      String allDrugName, Pageable pageable, ModelMap model)
  {
    return drugsService.drugsSerach (allDrugsBeginDate, allDrugsEndDate, allDrugName, pageable);
  }
}
