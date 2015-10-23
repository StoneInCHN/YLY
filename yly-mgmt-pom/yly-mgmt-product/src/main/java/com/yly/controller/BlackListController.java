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
import com.yly.entity.BlackList;
import com.yly.framework.paging.Page;
import com.yly.framework.paging.Pageable;
import com.yly.service.BlackListService;
import com.yly.service.TenantAccountService;

/**
 * Controller - 黑名单
 * 
 * @author pengyanan
 *
 */

@Controller("blackListController")
@RequestMapping("console/blackList")
public class BlackListController extends BaseController {

  @Resource(name = "blackListServiceImpl")
  private BlackListService blackListService;

  @Resource(name = "tenantAccountServiceImpl")
  private TenantAccountService tenantAccountService;

  @RequestMapping(value = "/blacklist", method = RequestMethod.GET)
  public String list(ModelMap model) {
    return "/blackList/blackList";
  }

  /**
   * 列表
   * 
   * @param beginDate
   * @param endDate
   * @param pageable
   * @param modelMap
   * @return
   */
  @RequestMapping(value = "/list", method = RequestMethod.POST)
  public @ResponseBody Page<BlackList> list(Date beginDate, Date endDate, Pageable pageable,
      String blackListName, ModelMap modelMap) {

    if(blackListName!=null){
      return blackListService.searchList(beginDate, endDate, blackListName);
    }
    
      return blackListService.findPage (pageable);
  }


  /**
   * 添加黑名单
   * 
   * @param blackList
   * @return
   */
  @RequestMapping(value = "/add", method = RequestMethod.POST)
  public @ResponseBody Message add(BlackList blackList) {
    if (blackList != null) {
      blackList.setTenantID(tenantAccountService.getCurrentTenantID());
      blackListService.save(blackList);
    }

    return SUCCESS_MESSAGE;
  }

  /**
   * 删除黑名单
   * 
   * @param ids
   * @return
   */
  @RequestMapping(value = "/delete", method = RequestMethod.POST)
  public @ResponseBody Message delete(Long[] ids) {
    if (ids != null) {
      blackListService.delete(ids);
    }
    return SUCCESS_MESSAGE;
  }

  /**
   * 显示详情
   * 
   * @param model
   * @param id
   * @return
   */
  @RequestMapping(value = "/details", method = RequestMethod.GET)
  public String details(ModelMap model, Long id) {
    BlackList blackList = blackListService.find(id);
    model.addAttribute("blackList", blackList);
    return "blackList/details";
  }

  /**
   * 获取数据进入编辑页面
   * 
   * @param model
   * @param id
   * @return
   */
  @RequestMapping(value = "/edit", method = RequestMethod.GET)
  public String edit(ModelMap model, Long id) {
    BlackList blackList = blackListService.find(id);
    model.addAttribute("blackList", blackList);
    return "blackList/edit";
  }

  /**
   * 更新
   * 
   * @param volunteer
   * @return
   */
  @RequestMapping(value = "/update", method = RequestMethod.POST)
  public @ResponseBody Message update(BlackList blackList) {
    blackListService.update(blackList);
    return SUCCESS_MESSAGE;
  }

}
