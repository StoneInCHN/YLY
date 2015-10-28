package com.yly.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yly.beans.Message;
import com.yly.controller.base.BaseController;
import com.yly.entity.TenantUser;
import com.yly.framework.paging.Page;
import com.yly.framework.paging.Pageable;
import com.yly.service.TenantUserService;

/**
 * 租户用户
 * @author huyong
 *
 */
@Controller ("tenantUserController")
@RequestMapping ("console/tenantUser")
public class TenantUserController extends BaseController
{

  @Resource (name = "tenantUserServiceImpl")
  private TenantUserService tenantUserService;


  @RequestMapping (value = "/tenantUser", method = RequestMethod.GET)
  public String list (ModelMap model)
  {
    return "tenantUser/tenantUser";
  }

  @RequestMapping (value = "/list", method = RequestMethod.POST)
  public @ResponseBody Page<TenantUser> list (Pageable pageable, ModelMap model)
  {
      return tenantUserService.findPage (pageable);
    
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
    model.put ("tenantUser", tenantUserService.find (id));
    return "tenantUser/edit";
  }

  @RequestMapping (value = "/add", method = RequestMethod.POST)
  public @ResponseBody Message add (TenantUser tenantUser)
  {
    tenantUserService.save (tenantUser,true);
    return SUCCESS_MESSAGE;
  }

  @RequestMapping (value = "/update", method = RequestMethod.POST)
  public @ResponseBody Message update (TenantUser tenantUser)
  {
    tenantUserService.update (tenantUser);
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
      tenantUserService.delete (ids);
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
    TenantUser record = tenantUserService.find(id);
    model.addAttribute("tenantUser", record);
    return "tenantUser/details";
  }
}
