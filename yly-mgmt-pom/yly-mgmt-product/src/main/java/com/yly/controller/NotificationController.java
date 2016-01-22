package com.yly.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.Filter;
import org.apache.lucene.search.Query;
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
import com.yly.entity.Notification;
import com.yly.framework.ordering.Ordering;
import com.yly.framework.ordering.Ordering.Direction;
import com.yly.framework.paging.Page;
import com.yly.framework.paging.Pageable;
import com.yly.service.ElderlyInfoService;
import com.yly.service.NotificationService;
import com.yly.utils.DateTimeUtils;

/**
 * 通知公告
 * @author huyong
 *
 */
@Controller ("NotificationController")
@RequestMapping ("console/notification")
public class NotificationController extends BaseController
{

  @Resource (name = "notificationServiceImpl")
  private NotificationService notificationService;
  @Resource(name="elderlyInfoServiceImpl")
  private ElderlyInfoService elderlyInfoService;


  @RequestMapping (value = "/notification", method = RequestMethod.GET)
  public String list (ModelMap model)
  {
    return "notification/notification";
  }

  @RequestMapping (value = "/list", method = RequestMethod.POST)
  public @ResponseBody Page<Notification> list (String name,Date beginDate, Date endDate, Pageable pageable, ModelMap model)
  {
    String startDateStr = null;
    String endDateStr = null;

    IKAnalyzer analyzer = new IKAnalyzer ();
    analyzer.setMaxWordLength (true);
    BooleanQuery query = new BooleanQuery ();

    QueryParser nameParser = new QueryParser (Version.LUCENE_35, "operator",
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
    if (name != null)
    {
      String text = QueryParser.escape (name);
        try
        {
          nameQuery = nameParser.parse (text);
          query.add (nameQuery, Occur.MUST);
          
          if (LogUtil.isDebugEnabled (FixedAssetsController.class))
          {
            LogUtil.debug (FixedAssetsController.class, "search", "Search assetName: "
                + name );
          }
        }
        catch (ParseException e)
        {
          e.printStackTrace();
        }
        
    }
    
    if (startDateStr != null || endDateStr != null)
    {
      rangeQuery = new TermRangeQuery ("physicalExaminationDate", startDateStr, endDateStr, true, true);
      query.add (rangeQuery,Occur.MUST);
      
      if (LogUtil.isDebugEnabled (FixedAssetsController.class))
      {
        LogUtil.debug (FixedAssetsController.class, "search", "Search start date: "+startDateStr
            +" end date: "+endDateStr);
      }
    }
    if (nameQuery != null || rangeQuery != null)
    {
      return notificationService.search (query, pageable, analyzer,filter);
    }
    return notificationService.findPage (pageable);
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
    model.put ("notification", notificationService.find (id));
    return "notification/edit";
  }

  @RequestMapping (value = "/add", method = RequestMethod.POST)
  public @ResponseBody Message add (Notification notification)
  {
    notificationService.save (notification,true);
    return SUCCESS_MESSAGE;
  }

  @RequestMapping (value = "/update", method = RequestMethod.POST)
  public @ResponseBody Message update (Notification notification)
  {
    notificationService.update (notification,"createDate");
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
      notificationService.delete (ids);
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
    Notification notification = notificationService.find(id);
    model.addAttribute("notification", notification);
    return "notification/details";
  }
  
  @RequestMapping(value = "/showOnMain", method = RequestMethod.GET)
  public @ResponseBody List<Notification> showOnMain(ModelMap model) {
    
    List<Ordering> orders = new ArrayList<Ordering> ();
    
    Ordering ordering =new Ordering ();
    ordering.setProperty ("createDate");
    ordering.setDirection (Direction.desc);
    return notificationService.findList (4, null, orders, true, null);
  }
  @RequestMapping(value = "/showOne", method = RequestMethod.GET)
  public String showOne(ModelMap model, Long id) {
    
    model.put ("notification", notificationService.find (id));
    return "notification/showNotify";
  }
}
