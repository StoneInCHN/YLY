package com.yly.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yly.beans.Message;
import com.yly.controller.base.BaseController;
import com.yly.entity.DrugsInfo;
import com.yly.entity.Prescription;
import com.yly.entity.PrescriptionDrugsItems;
import com.yly.framework.paging.Page;
import com.yly.framework.paging.Pageable;
import com.yly.service.DrugsInfoService;
import com.yly.service.PrescriptionDrugsItemsService;
import com.yly.service.PrescriptionService;

/**
 * 药方
 * @author huyong
 *
 */
@Controller ("prescriptionDrugsItemsController")
@RequestMapping ("console/prescriptionDrugsItem")
public class PrescriptionDrugsItemsController extends BaseController
{

  @Resource (name = "prescriptionServiceImpl")
  private PrescriptionService prescriptionService;
  @Resource(name = "drugsInfoServiceImpl")
  private DrugsInfoService drugsInfoService;
  @Resource(name = "prescriptionDrugsItemsServiceImpl")
  private PrescriptionDrugsItemsService prescriptionDrugsItemsService;
  
  @RequestMapping (value = "/prescriptionDrugsItems", method = RequestMethod.GET)
  public String list (ModelMap model)
  {
    return "prescriptionDrugsItem/prescriptionDrugsItem";
  }

  @RequestMapping (value = "/list", method = RequestMethod.POST)
  public @ResponseBody Page<PrescriptionDrugsItems> list (Pageable pageable, ModelMap model)
  {
      return prescriptionDrugsItemsService.findPage (pageable);
    
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
    model.put ("prescriptionDrugsItem", prescriptionDrugsItemsService.find (id));
    return "prescriptionDrugsItem/edit";
  }

  @RequestMapping (value = "/add", method = RequestMethod.POST)
  public @ResponseBody Message add (PrescriptionDrugsItems prescriptionDrugsItems)
  {
    prescriptionDrugsItemsService.save (prescriptionDrugsItems,true);
    return SUCCESS_MESSAGE;
  }
  @RequestMapping (value = "/prescriptionDrugs", method = RequestMethod.GET)
  public String prescriptionDrugs (ModelMap model, Long drugsId)
  {
    model.put ("drugsInfo", drugsInfoService.find (drugsId));
    return "prescription/prescriptionDrugs";
  }
  @RequestMapping (value = "/addDrugs", method = RequestMethod.POST)
  public @ResponseBody Message addDrugs (ModelMap model,PrescriptionDrugsItems prescriptionDrugsItems,
      Long drugsId,Long prescriptionId)
  {
    DrugsInfo drugsInfo = drugsInfoService.find (drugsId);
    Prescription prescription = prescriptionService.find (prescriptionId);
    prescriptionDrugsItems.setPrescription (prescription);
    prescriptionDrugsItems.setDrugsInfo (drugsInfo);
    model.put ("prescriptionDrugsItems", prescriptionDrugsItems);
    return SUCCESS_MESSAGE;
  }
  
  @RequestMapping (value = "/update", method = RequestMethod.POST)
  public @ResponseBody Message update (PrescriptionDrugsItems prescriptionDrugsItems)
  {
    prescriptionDrugsItemsService.update (prescriptionDrugsItems);
    return SUCCESS_MESSAGE;
  }
 
 

  /**
   * 删除
   */
  @RequestMapping (value = "/delete", method = RequestMethod.POST)
  public @ResponseBody Message delete (Long id)
  {
    if (id != null)
    {
      // 检查是否能被删除
      // if()
      prescriptionDrugsItemsService.delete (id);
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
    PrescriptionDrugsItems prescriptionDrugsItems = prescriptionDrugsItemsService.find(id);
    model.addAttribute("prescriptionDrugsItems", prescriptionDrugsItems);
    return "prescriptionDrugsItems/details";
  }
}
