package com.yly.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yly.beans.Message;
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
      Date beginDate, Date endDate, String realNameSearch, String staffStatusSearch,
      String departmentSearchId, String positionSearchId)
  {
//    String startDateStr = null;
//    String endDateStr = null;
//
//    IKAnalyzer analyzer = new IKAnalyzer ();
//    analyzer.setMaxWordLength (true);
//    BooleanQuery query = new BooleanQuery ();
//
//    QueryParser nameParser = new QueryParser (Version.LUCENE_35, "realName",
//        analyzer);
//    Query nameQuery = null;
//    TermRangeQuery rangeQuery = null;
//    Filter filter = null;
//    if (beginDate != null)
//    {
//      startDateStr = DateTimeUtils.convertDateToString (beginDate, null);
//    }
//    if (endDate != null)
//    {
//      endDateStr = DateTimeUtils.convertDateToString (endDate, null);
//    }
//    if (realNameSearch != null)
//    {
//      String text = QueryParser.escape (realNameSearch);
//        try
//        {
//          nameQuery = nameParser.parse (text);
//          query.add (nameQuery, Occur.MUST);
//          
//          if (LogUtil.isDebugEnabled (FixedAssetsController.class))
//          {
//            LogUtil.debug (FixedAssetsController.class, "search", "Search real name: "
//                + realNameSearch );
//          }
//        }
//        catch (ParseException e)
//        {
//          e.printStackTrace();
//        }
//        
//    }
//    //过滤部门
//    if (departmentSearchId != null)
//    {
//      TermQuery departmentQuery = new TermQuery(new Term("department.id", departmentSearchId));  
//      query.add (departmentQuery,Occur.MUST);
//    }
//    //过滤职位
//    if (positionSearchId != null)
//    {
//      TermQuery positionQuery = new TermQuery(new Term("position.id", positionSearchId));  
//      query.add (positionQuery,Occur.MUST);
//    }
//    //过滤状态
//    if (staffStatusSearch != null)
//    {
//      TermQuery statusQuery = new TermQuery(new Term("staffStatus", staffStatusSearch));  
//      query.add (statusQuery,Occur.MUST);
//    }
//    if (startDateStr != null || endDateStr != null)
//    {
//      rangeQuery = new TermRangeQuery ("hireDate", startDateStr, endDateStr, true, true);
//      query.add (rangeQuery,Occur.MUST);
//      
//      if (LogUtil.isDebugEnabled (FixedAssetsController.class))
//      {
//        LogUtil.debug (FixedAssetsController.class, "search", "Search start date: "+startDateStr
//            +" end date: "+endDateStr);
//      }
//    }
//    if (nameQuery != null || rangeQuery != null || 
//        departmentSearchId != null || positionSearchId != null || staffStatusSearch != null)
//    {
//      return tenantUserService.search (query, pageable, analyzer,filter);
//    }
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
  public @ResponseBody Message update (Prescription prescription,Long prescriptionDrugsUseMethodId,Long elderlyInfoID)
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
