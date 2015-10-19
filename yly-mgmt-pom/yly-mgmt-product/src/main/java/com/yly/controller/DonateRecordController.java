package com.yly.controller;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.Filter;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermRangeFilter;
import org.apache.lucene.search.BooleanClause.Occur;
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
import com.yly.entity.DonateRecord;
import com.yly.framework.paging.Page;
import com.yly.framework.paging.Pageable;
import com.yly.service.DonateRecordService;
import com.yly.utils.DateTimeUtils;

/**
 * 捐赠记录
 * @author huyong
 *
 */
@Controller ("donateRecordController")
@RequestMapping ("console/donateRecord")
public class DonateRecordController extends BaseController
{

  @Resource (name = "donateRecordServiceImpl")
  private DonateRecordService donateRecordService;


  @RequestMapping (value = "/donateRecord", method = RequestMethod.GET)
  public String list (ModelMap model)
  {
    return "donateRecord/donateRecord";
  }

  @RequestMapping (value = "/list", method = RequestMethod.POST)
  public @ResponseBody Page<DonateRecord> list (String donatorName,Date beginDate, Date endDate,
      Pageable pageable, ModelMap model)
  {
    String startDateStr = null;
    String endDateStr = null;

    IKAnalyzer analyzer = new IKAnalyzer ();
    analyzer.setMaxWordLength (true);
    BooleanQuery query = new BooleanQuery ();

    QueryParser nameParser = new QueryParser (Version.LUCENE_35, "donatorName",
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
    if (donatorName != null)
    {
      String text = QueryParser.escape (donatorName);
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

        return donateRecordService.search (query, pageable, analyzer,filter);
      }
      catch (ParseException e)
      {
        e.printStackTrace ();
      }
    }
    return donateRecordService.findPage (pageable);
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
    model.put ("donateRecord", donateRecordService.find (id));
    return "donateRecord/edit";
  }

  @RequestMapping (value = "/add", method = RequestMethod.POST)
  public @ResponseBody Message add (DonateRecord donateRecord)
  {
    donateRecordService.save (donateRecord,true);
    return SUCCESS_MESSAGE;
  }

  @RequestMapping (value = "/update", method = RequestMethod.POST)
  public @ResponseBody Message update (DonateRecord donateRecord)
  {
    donateRecordService.update (donateRecord);
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
      donateRecordService.delete (ids);
    }
    return SUCCESS_MESSAGE;
  }
}
