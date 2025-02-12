package com.yly.controller;

import java.util.Date;

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
import com.yly.entity.MedicalRecord;
import com.yly.framework.paging.Page;
import com.yly.framework.paging.Pageable;
import com.yly.service.ElderlyInfoService;
import com.yly.service.MedicalRecordService;
import com.yly.utils.DateTimeUtils;

/**
 * 病历
 * @author huyong
 *
 */
@Controller ("medicalRecordController")
@RequestMapping ("console/medicalRecord")
public class MedicalRecordController extends BaseController
{

  @Resource (name = "medicalRecordServiceImpl")
  private MedicalRecordService medicalRecordService;
  @Resource(name="elderlyInfoServiceImpl")
  private ElderlyInfoService elderlyInfoService;


  @RequestMapping (value = "/medicalRecord", method = RequestMethod.GET)
  public String list (ModelMap model)
  {
    return "medicalRecord/medicalRecord";
  }

  @RequestMapping (value = "/list", method = RequestMethod.POST)
  public @ResponseBody Page<MedicalRecord> list (String name,Date beginDate, Date endDate, Pageable pageable, ModelMap model)
  {
    String startDateStr = null;
    String endDateStr = null;

    IKAnalyzer analyzer = new IKAnalyzer ();
    analyzer.setMaxWordLength (true);
    BooleanQuery query = new BooleanQuery ();

    QueryParser nameParser = new QueryParser (Version.LUCENE_35, "elderlyInfo.name",
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
      return medicalRecordService.search (query, pageable, analyzer,filter,true);
    }
    return medicalRecordService.findPage (pageable,true);
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
    model.put ("medicalRecord", medicalRecordService.find (id));
    return "medicalRecord/edit";
  }

  @RequestMapping (value = "/add", method = RequestMethod.POST)
  public @ResponseBody Message add (MedicalRecord medicalRecord,Long elderlyInfoID)
  {
    ElderlyInfo elderlyInfo = elderlyInfoService.find (elderlyInfoID);
    medicalRecord.setElderlyInfo (elderlyInfo);
    medicalRecordService.save (medicalRecord,true);
    return SUCCESS_MESSAGE;
  }

  @RequestMapping (value = "/update", method = RequestMethod.POST)
  public @ResponseBody Message update (MedicalRecord medicalRecord,Long elderlyInfoID)
  {
    ElderlyInfo elderlyInfo = elderlyInfoService.find (elderlyInfoID);
    medicalRecord.setElderlyInfo (elderlyInfo);
    medicalRecordService.update (medicalRecord,"createDate","tenantID");
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
      medicalRecordService.delete (ids);
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
    MedicalRecord medicalRecord = medicalRecordService.find(id);
    model.addAttribute("medicalRecord", medicalRecord);
    return "medicalRecord/details";
  }
}
