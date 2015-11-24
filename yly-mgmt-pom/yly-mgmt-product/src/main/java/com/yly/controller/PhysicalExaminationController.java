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
import com.yly.entity.ElderlyInfo;
import com.yly.entity.PhysicalExamination;
import com.yly.entity.PhysicalExaminationItemConfig;
import com.yly.entity.PhysicalExaminationItems;
import com.yly.entity.TenantAccount;
import com.yly.framework.paging.Page;
import com.yly.framework.paging.Pageable;
import com.yly.service.ElderlyInfoService;
import com.yly.service.PhysicalExaminationItemConfigService;
import com.yly.service.PhysicalExaminationService;
import com.yly.service.TenantAccountService;
import com.yly.utils.DateTimeUtils;

/**
 * 体检实例
 * @author huyong
 *
 */
@Controller ("physicalExaminationController")
@RequestMapping ("console/physicalExamination")
public class PhysicalExaminationController extends BaseController
{

  @Resource (name = "physicalExaminationServiceImpl")
  private PhysicalExaminationService physicalExaminationService;
  @Resource(name="elderlyInfoServiceImpl")
  private ElderlyInfoService elderlyInfoService;
  @Resource (name = "physicalExaminationItemConfigServiceImpl")
  private PhysicalExaminationItemConfigService physicalExaminationItemConfigService;
  @Resource(name="tenantAccountServiceImpl")
  private TenantAccountService tenantAccountService;
  @RequestMapping (value = "/physicalExamination", method = RequestMethod.GET)
  public String list (ModelMap model)
  {
    return "physicalExamination/physicalExamination";
  }

  @RequestMapping (value = "/list", method = RequestMethod.POST)
  public @ResponseBody Page<PhysicalExamination> list (String elderlyInfoName,String operatorName,Date beginDate, Date endDate, 
      Pageable pageable, ModelMap model
      )
  {
    String startDateStr = null;
    String endDateStr = null;

    IKAnalyzer analyzer = new IKAnalyzer ();
    analyzer.setMaxWordLength (true);
    BooleanQuery query = new BooleanQuery ();

    QueryParser elderlyInfoNameParser = new QueryParser (Version.LUCENE_35, "elderlyInfo.name",
        analyzer);
    
    QueryParser operateNameParser = new QueryParser (Version.LUCENE_35, "operator.realName",
        analyzer);
    Query elderlyInfoNameQuery = null;
    Query operateNameQuery = null;
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
    if (elderlyInfoName != null)
    {
      String text = QueryParser.escape (elderlyInfoName);
        try
        {
          elderlyInfoNameQuery = elderlyInfoNameParser.parse (text);
          query.add (elderlyInfoNameQuery, Occur.MUST);
          
          if (LogUtil.isDebugEnabled (FixedAssetsController.class))
          {
            LogUtil.debug (FixedAssetsController.class, "search", "Search elderlyInfoName: "
                + elderlyInfoName );
          }
        }
        catch (ParseException e)
        {
          e.printStackTrace();
        }
        
    }
    if (operatorName != null)
    {
      String text = QueryParser.escape (operatorName);
        try
        {
          operateNameQuery = operateNameParser.parse (text);
          query.add (operateNameQuery, Occur.MUST);
          
          if (LogUtil.isDebugEnabled (FixedAssetsController.class))
          {
            LogUtil.debug (FixedAssetsController.class, "search", "Search operatorName: "
                + operatorName );
          }
        }
        catch (ParseException e)
        {
          e.printStackTrace();
        }
        
    }
    if (startDateStr != null || endDateStr != null)
    {
      rangeQuery = new TermRangeQuery ("createDate", startDateStr, endDateStr, true, true);
      query.add (rangeQuery,Occur.MUST);
      
      if (LogUtil.isDebugEnabled (FixedAssetsController.class))
      {
        LogUtil.debug (FixedAssetsController.class, "search", "Search start date: "+startDateStr
            +" end date: "+endDateStr);
      }
    }
    if (elderlyInfoNameQuery != null || rangeQuery != null || operateNameQuery != null)
    {
      return physicalExaminationService.search (query, pageable, analyzer,filter,true);
    }
    return physicalExaminationService.findPage (pageable,true);
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
    model.put ("physicalExamination", physicalExaminationService.find (id));
    return "physicalExamination/edit";
  }

  @RequestMapping (value = "/add", method = RequestMethod.POST)
  public @ResponseBody Message add (PhysicalExamination physicalExamination,Long elderlyInfoID)
  {
    ElderlyInfo elderlyInfo= elderlyInfoService.find (elderlyInfoID);
    if (physicalExamination.getPhysicalExaminationItems () != null 
        && !physicalExamination.getPhysicalExaminationItems ().isEmpty ())
    {
      for (PhysicalExaminationItems item: physicalExamination.getPhysicalExaminationItems ())
      {
        PhysicalExaminationItemConfig itemConfig=physicalExaminationItemConfigService.find (item.getPhysicalExaminationItem ().getId ());
        item.setPhysicalExaminationItem (itemConfig);
        item.setPhysicalExamination (physicalExamination);
      }
    }
    TenantAccount account=tenantAccountService.getCurrent ();
    physicalExamination.setOperator (account.getTenantUser ());
    physicalExamination.setElderlyInfo (elderlyInfo);
    physicalExaminationService.save (physicalExamination,true);
    return SUCCESS_MESSAGE;
  }

  @RequestMapping (value = "/update", method = RequestMethod.POST)
  public @ResponseBody Message update (PhysicalExamination physicalExamination,Long elderlyInfoID)
  {
    ElderlyInfo elderlyInfo= elderlyInfoService.find (elderlyInfoID);
    
    List<PhysicalExaminationItems> oldItems = physicalExamination.getPhysicalExaminationItems ();
    List<PhysicalExaminationItems> newItems= new ArrayList<PhysicalExaminationItems>();
    if ( oldItems!= null 
        && !oldItems.isEmpty ())
    {
      
      for (PhysicalExaminationItems item: physicalExamination.getPhysicalExaminationItems ())
      {
        if (item.getPhysicalExaminationItem () == null)
        {
          continue;
        }
        PhysicalExaminationItemConfig itemConfig=physicalExaminationItemConfigService.find (item.getPhysicalExaminationItem ().getId ());
        item.setPhysicalExaminationItem (itemConfig);
        item.setPhysicalExamination (physicalExamination);
        newItems.add (item);
      }
    }
    TenantAccount account=tenantAccountService.getCurrent ();
    physicalExamination.setOperator (account.getTenantUser ());
    physicalExamination.setElderlyInfo (elderlyInfo);
    physicalExamination.setPhysicalExaminationItems (newItems);
    physicalExaminationService.update (physicalExamination,"createDate","tenantID");
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
      physicalExaminationService.delete (ids);
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
    PhysicalExamination physicalExamination = physicalExaminationService.find(id);
    model.addAttribute("physicalExamination", physicalExamination);
    return "physicalExamination/details";
  }
}
