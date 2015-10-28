package com.yly.controller;

import java.util.List;
import java.util.Map;

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
import com.yly.framework.ordering.Ordering.Direction;
import com.yly.framework.paging.Page;
import com.yly.framework.paging.Pageable;
import com.yly.service.DonateItemTypeService;
import com.yly.service.DonateItemTypeService;

/**
 * 捐赠物品类型
 * @author huyong
 *
 */
@Controller ("donateItemTypeController")
@RequestMapping ("console/donateItemType")
public class DonateItemTypeController extends BaseController
{

  @Resource (name = "donateDetailServiceImpl")
  private DonateItemTypeService donateItemTypeService;


  @RequestMapping (value = "/donateItemType", method = RequestMethod.GET)
  public String list (ModelMap model)
  {
    return "donateItemType/donateItemType";
  }

  @RequestMapping (value = "/list", method = RequestMethod.POST)
  public @ResponseBody Page<DonateItemType> list (Pageable pageable, ModelMap model)
  {
      return donateItemTypeService.findPage (pageable);
    
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
    model.put ("donateItemType", donateItemTypeService.find (id));
    return "donateItemType/edit";
  }

  @RequestMapping (value = "/add", method = RequestMethod.POST)
  public @ResponseBody Message add (DonateItemType donateItemType)
  {
    donateItemTypeService.save (donateItemType,true);
    return SUCCESS_MESSAGE;
  }

  @RequestMapping (value = "/update", method = RequestMethod.POST)
  public @ResponseBody Message update (DonateItemType donateItemType)
  {
    donateItemTypeService.update (donateItemType);
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
      donateItemTypeService.delete (ids);
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
    DonateItemType donateItemType = donateItemTypeService.find(id);
    model.addAttribute("donateItemType", donateItemType);
    return "donateDetail/details";
  }
  
  /**
   * 获取捐赠物品类型
   * 
   * @param model
   * @param id
   * @return
   */
  @RequestMapping(value = "/findAllDonateItemTypes", method = RequestMethod.GET)
  public @ResponseBody List<Map<String, Object>>  findAllDonateItemTypes(ModelMap model,Direction direction) {
    return donateItemTypeService.findAllDonateItemTypes (direction);
  }
}
