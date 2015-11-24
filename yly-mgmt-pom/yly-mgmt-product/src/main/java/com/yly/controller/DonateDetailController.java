package com.yly.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yly.beans.Message;
import com.yly.controller.base.BaseController;
import com.yly.entity.DonateDetail;
import com.yly.entity.DonateItemType;
import com.yly.entity.DonateRecord;
import com.yly.framework.paging.Page;
import com.yly.framework.paging.Pageable;
import com.yly.service.DonateDetailService;
import com.yly.service.DonateItemTypeService;
import com.yly.service.DonateRecordService;

/**
 * 捐赠详情
 * @author huyong
 *
 */
@Controller ("donateDetailController")
@RequestMapping ("console/donateDetail")
public class DonateDetailController extends BaseController
{

  @Resource (name = "donateDetailServiceImpl")
  private DonateDetailService donateDetailService;
  @Resource (name = "donateItemTypeServiceImpl")
  private DonateItemTypeService donateItemTypeService;
  @Resource (name = "donateRecordServiceImpl")
  private DonateRecordService donateRecordService;

  @RequestMapping (value = "/donateDetail", method = RequestMethod.GET)
  public String list (ModelMap model)
  {
    return "donateDetail/donateDetail";
  }

  @RequestMapping (value = "/list", method = RequestMethod.POST)
  public @ResponseBody Page<DonateDetail> list (Pageable pageable,
      ModelMap model)
  {
    return donateDetailService.findPage (pageable);

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
    model.put ("donateDetail", donateDetailService.find (id));
    return "donateDetail/edit";
  }

  @RequestMapping (value = "/add", method = RequestMethod.POST)
  public @ResponseBody Message add (DonateDetail donateDetail,
      Long donateItemTypeId, Long recordId)
  {
    DonateRecord record = donateRecordService.find (recordId);
    DonateItemType itemType = donateItemTypeService.find (donateItemTypeId);
    donateDetail.setDonateItemType (itemType);
    donateDetail.setDonateRecord (record);
    donateDetailService.save (donateDetail, true);
    return SUCCESS_MESSAGE;
  }

  @RequestMapping (value = "/add")
  public String add ()
  {
    return "donateDetail/add";
  }

  @RequestMapping (value = "/update", method = RequestMethod.POST)
  public @ResponseBody Message update (DonateDetail donateDetail,
      Long donateItemTypeId, Long recordId)
  {
    DonateRecord record = donateRecordService.find (recordId);
    DonateItemType itemType = donateItemTypeService.find (donateItemTypeId);
    donateDetail.setDonateItemType (itemType);
    donateDetail.setDonateRecord (record);
    donateDetailService.update (donateDetail,"createDate","tenantID");
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
      donateDetailService.delete (ids);
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
  @RequestMapping (value = "/details", method = RequestMethod.GET)
  public String details (ModelMap model, Long id)
  {
    DonateDetail donateDetail = donateDetailService.find (id);
    model.addAttribute ("donateDetail", donateDetail);
    return "donateDetail/details";
  }

  /**
   * 获取对于record的关联信息
   * 
   * @param pageable
   * @param id
   * @return
   */
  @RequestMapping (value = "/donateDetails", method = RequestMethod.POST)
  public @ResponseBody Page<DonateDetail> details (Pageable pageable, Long id)
  {
    Page<DonateDetail> detailPage = donateDetailService
        .findDonateDetailByRecordId (pageable, id);
    return detailPage;
  }
}
