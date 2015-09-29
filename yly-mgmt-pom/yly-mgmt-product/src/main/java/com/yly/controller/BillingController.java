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
import com.yly.entity.Billing;
import com.yly.framework.paging.Page;
import com.yly.framework.paging.Pageable;
import com.yly.service.BillingService;

@Controller("billingController")
@RequestMapping("/console/billing")
public class BillingController extends BaseController {

  @Resource(name = "billingServiceImpl")
  private BillingService billingService;
  
  /**
   * 列表页面
   * @param model
   * @return
   */
  @RequestMapping(value = "/main", method = RequestMethod.GET)
  public String list(ModelMap model) {
    return "/billing/list";
  }

  /**
   * 列表数据
   * @param beginDate
   * @param endDate
   * @param pageable
   * @param model
   * @return
   */
  @RequestMapping(value = "/list", method = RequestMethod.POST)
  public @ResponseBody Page<Billing> list(Date beginDate,Date endDate, Pageable pageable, ModelMap model) {
    
  /*  List<Filter> filters = new ArrayList<Filter>();
    if(beginDate !=null){
      Filter beginDateFilter = new Filter("createDate", Operator.gt, beginDate);
      filters.add(beginDateFilter);
    }
    if(endDate!= null){
      Filter endDateFilter = new Filter("createDate", Operator.lt, endDate);
      filters.add(endDateFilter);
    }
    pageable.setFilters(filters);*/
    
    
    return billingService.findPage(pageable);
  }


  /**
   * 编辑页面
   * @param model
   * @param vendorId
   * @return
   */
  @RequestMapping(value = "/edit", method = RequestMethod.GET)
  public String edit(ModelMap model, Long id) {
    model.addAttribute("billing", billingService.find(id));
    return "/billing/edit";
  }
  
  /**
   *  添加页面
   * @param model
   * @param id
   * @return
   */
  @RequestMapping(value = "/add", method = RequestMethod.GET)
  public String add(ModelMap model, Long id) {
    return "/billing/add";
  }

  /**
   * 保存
   * @return
   */
  @RequestMapping(value = "/save", method = RequestMethod.POST)
  public @ResponseBody Message save(Billing billing) {
    billingService.save(billing);
    return SUCCESS_MESSAGE;
  }
  
  /**
   * 更新
   * @return
   */
  @RequestMapping(value = "/update", method = RequestMethod.POST)
  public @ResponseBody Message update(Billing billing) {
    billingService.update(billing);
    return SUCCESS_MESSAGE;
  }
  
  /**
   * 删除
   * @param ids
   * @return
   */
  @RequestMapping(value = "/delete", method = RequestMethod.POST)
  public @ResponseBody Message delete(Long[] ids) {
    if (ids != null) {
      // 检查是否能被删除
      // if()
      billingService.delete(ids);
    }
    return SUCCESS_MESSAGE;
  }

}
