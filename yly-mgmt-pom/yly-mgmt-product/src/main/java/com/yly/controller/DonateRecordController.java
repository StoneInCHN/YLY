package com.yly.controller;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yly.beans.Message;
import com.yly.controller.base.BaseController;
import com.yly.entity.DonateRecord;
import com.yly.framework.paging.Page;
import com.yly.framework.paging.Pageable;
import com.yly.service.DonateRecordService;

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
  public @ResponseBody Page<DonateRecord> list (Date beginDate, Date endDate,
      Pageable pageable, ModelMap model)
  {
    Page<DonateRecord> donateRecordPage= donateRecordService.findPage (pageable);
    return donateRecordPage;
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
