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
import com.yly.entity.Prescription;
import com.yly.entity.PrescriptionDrugsItems;
import com.yly.framework.paging.Page;
import com.yly.framework.paging.Pageable;
import com.yly.service.DrugsInfoService;
import com.yly.service.ElderlyInfoService;
import com.yly.service.PrescriptionService;
import com.yly.service.SystemConfigService;
import com.yly.utils.DateTimeUtils;

/**
 * 药方
 * @author huyong
 *
 */
@Controller ("prescriptionController")
@RequestMapping ("console/prescription")
public class PrescriptionController extends BaseController
{

  @Resource (name = "prescriptionServiceImpl")
  private PrescriptionService prescriptionService;
  @Resource(name = "drugsInfoServiceImpl")
  private DrugsInfoService drugsInfoService;
  @Resource(name= "systemConfigServiceImpl")
  private SystemConfigService systemConfigService;
  @Resource(name="elderlyInfoServiceImpl")
  private ElderlyInfoService elderlyInfoService;
  
  
  @RequestMapping (value = "/prescription", method = RequestMethod.GET)
  public String list (ModelMap model)
  {
    return "prescription/prescription";
  }

  @RequestMapping (value = "/list", method = RequestMethod.POST)
  public @ResponseBody Page<Prescription> list (Pageable pageable, ModelMap model,
      Date beginDate, Date endDate, String elderNameSearch)
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
    if (elderNameSearch != null)
    {
      String text = QueryParser.escape (elderNameSearch);
        try
        {
          nameQuery = nameParser.parse (text);
          query.add (nameQuery, Occur.MUST);
          
          if (LogUtil.isDebugEnabled (FixedAssetsController.class))
          {
            LogUtil.debug (FixedAssetsController.class, "search", "Search real name: "
                + elderNameSearch );
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
    if (nameQuery != null || rangeQuery != null)
    {
      return prescriptionService.search (query, pageable, analyzer,filter);
    }
      return prescriptionService.findPage (pageable);
    
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
    model.put ("prescription", prescriptionService.find (id));
    return "prescription/edit";
  }

  @RequestMapping (value = "/add", method = RequestMethod.GET)
  public String add (ModelMap model)
  {
    return "prescription/add";
  }
 
  @RequestMapping (value = "/save", method = RequestMethod.POST)
  public @ResponseBody Message add (Prescription prescription,Long elderlyInfoID,Long prescriptionDrugsUseMethodId)
  {
    ElderlyInfo elderlyInfo = elderlyInfoService.find (elderlyInfoID);
    List<PrescriptionDrugsItems> itemsList=prescription.getPrescriptionDrugsItems ();
    for (PrescriptionDrugsItems items : itemsList)
    {
      //西药情况
      if (items.getDrugUseMethod () != null)
      {
        items.setDrugUseMethod (systemConfigService.find (items.getDrugUseMethod ().getId ()));
      }
      
      items.setDrugsInfo (drugsInfoService.find (items.getDrugsInfo ().getId ()));
      items.setPrescription (prescription);
    }
    if (prescriptionDrugsUseMethodId != null)
    {
      prescription.setDrugUseMethod (systemConfigService.find (prescriptionDrugsUseMethodId));
    }
    prescription.setElderlyInfo (elderlyInfo);
    prescription.setPrescriptionDrugsItems (itemsList);
    prescriptionService.save (prescription,true);
    return SUCCESS_MESSAGE;
  }

  
  @RequestMapping (value = "/update", method = RequestMethod.POST)
  public @ResponseBody Message update (Prescription prescription,Long prescriptionUseMethodId,Long elderlyInfoID)
  {
    ElderlyInfo elderlyInfo = elderlyInfoService.find (elderlyInfoID);
    List<PrescriptionDrugsItems> itemsList=prescription.getPrescriptionDrugsItems ();
    List<PrescriptionDrugsItems> newItemList=new ArrayList<PrescriptionDrugsItems>();
    for (PrescriptionDrugsItems items : itemsList)
    {
      //删除item时，item存在，但所有属性都为null
      //drugsInfo 为null为错误数据
      if(items.getDrugsInfo () ==null){
        continue;
      }
      //西药情况
      if (items.getDrugUseMethod () != null)
      {
        items.setDrugUseMethod (systemConfigService.find (items.getDrugUseMethod ().getId ()));
      }
      
      items.setDrugsInfo (drugsInfoService.find (items.getDrugsInfo ().getId ()));
      items.setPrescription (prescription);
      
      newItemList.add (items);
    }
    
    if (prescriptionUseMethodId != null)
    {
      prescription.setDrugUseMethod (systemConfigService.find (prescriptionUseMethodId));
    }
    prescription.setElderlyInfo (elderlyInfo);
    prescription.setPrescriptionDrugsItems (newItemList);
    prescriptionService.update (prescription);
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
      prescriptionService.delete (ids);
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
    Prescription prescription = prescriptionService.find(id);
    model.addAttribute("tenantUser", prescription);
    return "prescription/details";
  }
  
  //打开药品选择框
  
  @RequestMapping (value = "/showDrugs", method = RequestMethod.GET)
  public String addDrugs (ModelMap model,Long drugsInfoId,Prescription prescription
      , PrescriptionDrugsItems item)
  {
//    Set<PrescriptionDrugsItems> itemSet=prescription.getPrescriptionDrugsItems ();
//    if (itemSet != null)
//    {
//      itemSet.add (item);
//    }else {
//      itemSet=new HashSet ();
//      itemSet.add (item);
//    }
//    prescription.setPrescriptionDrugsItems (itemSet);
    return "prescription/showDrugs"; 

  }
}
