package com.yly.controller;


import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yly.beans.Message;
import com.yly.common.log.LogUtil;
import com.yly.controller.base.BaseController;
import com.yly.entity.DrugsInfo;
import com.yly.framework.paging.Page;
import com.yly.framework.paging.Pageable;
import com.yly.json.request.DrugsInfoSearchRequest;
import com.yly.service.DrugsInfoService;
import com.yly.utils.DateTimeUtils;

@Controller
@RequestMapping("console/drugs")
public class DrugsInfoController extends BaseController {

  @Resource(name = "drugsInfoServiceImpl")
  private DrugsInfoService drugsService;

  @RequestMapping(value = "/drugsInfo", method = RequestMethod.GET)
  public String list(ModelMap model) {
    return "drugsInfo/drugsInfo";
  }

  /**
   * 添加
   * @param model
   * @return
   */
  @RequestMapping(value = "/add", method = RequestMethod.GET)
  public String add(ModelMap model) {
    return "drugsInfo/add";
  }
  
  @RequestMapping(value = "/list", method = RequestMethod.POST)
  public @ResponseBody Page<DrugsInfo> list(Date beginDate,Date endDate, Pageable pageable, ModelMap model) {
    
    return drugsService.findPage (pageable);
  }


  /**
   * get data for vendor edit page
   * 
   * @param model
   * @param vendorId
   * @return
   */
  @RequestMapping(value = "/edit", method = RequestMethod.GET)
  public String edit(ModelMap model, Long id) {
    model.addAttribute("drugs", drugsService.find(id));
    return "drugsInfo/edit";
  }



  @RequestMapping(value = "/save", method = RequestMethod.POST)
  public @ResponseBody Message save(DrugsInfo drugsInfo) {
    drugsService.save(drugsInfo);
    return SUCCESS_MESSAGE;
  }
  
  
  @RequestMapping(value = "/update", method = RequestMethod.POST)
  public @ResponseBody Message update(DrugsInfo drugsInfo) {
    drugsService.update(drugsInfo,"createDate");
    return SUCCESS_MESSAGE;
  }
  
  @RequestMapping(value = "/search", method = RequestMethod.POST)
  public @ResponseBody Page<DrugsInfo> search(@RequestBody DrugsInfoSearchRequest request) {
    String startDateStr = null;
    String endDateStr = null;
    if (request.getStartDate () != null && DateTimeUtils.isValidDate (request.getStartDate ()))
    {
      startDateStr = request.getStartDate ();
    }
    if (request.getEndDate () != null && DateTimeUtils.isValidDate (request.getEndDate ()))
    {
      endDateStr = request.getEndDate ();
    }
    if (LogUtil.isDebugEnabled(DrugsInfoController.class)) {
      LogUtil.debug(DrugsInfoController.class, "search", "Search keyword: "+ request.getKeyword ()+""
          + ", start date: "+startDateStr+", end date: "+endDateStr
         );
    }
    
    return drugsService.search (request.getKeyword (), null,startDateStr,endDateStr);
  }


  /**
   * 删除
   */
  @RequestMapping(value = "/delete", method = RequestMethod.POST)
  public @ResponseBody Message delete(Long[] ids) {
    if (ids != null) {
      // 检查是否能被删除
      // if()
      drugsService.delete(ids);
    }
    return SUCCESS_MESSAGE;
  }
  /**
   * 删除
   */
  @RequestMapping(value = "/refresh", method = RequestMethod.POST)
  public @ResponseBody Message refreshIndex() {
    drugsService.refreshIndex ();
    return SUCCESS_MESSAGE;
  }
}
