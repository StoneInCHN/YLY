package com.yly.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yly.beans.Message;
import com.yly.common.log.LogUtil;
import com.yly.controller.base.BaseController;
import com.yly.entity.BlackList;
import com.yly.entity.ElderlyInfo;
import com.yly.entity.TenantInfo;
import com.yly.framework.paging.Page;
import com.yly.framework.paging.Pageable;
import com.yly.service.BlackListService;
import com.yly.service.ElderlyInfoService;
import com.yly.service.TenantAccountService;
import com.yly.service.TenantInfoService;

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

  @Resource(name = "elderlyInfoServiceImpl")
  private ElderlyInfoService elderlyInfoService;

  @Resource(name = "tenantInfoServiceImpl")
  private TenantInfoService tenantInfoService;

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
      BlackList blackList, ModelMap modelMap) {
    if (blackList.getElderlyInfo() != null && blackList.getElderlyInfo().getName() != null
        || beginDate != null || endDate != null) {
      return blackListService.searchList(beginDate, endDate, pageable, blackList);
    }
    return blackListService.findPage(pageable);
  }


  /**
   * 添加黑名单
   * 
   * @param blackList
   * @return
   */
  @RequestMapping(value = "/add", method = RequestMethod.POST)
  public @ResponseBody Message add(Long elderlyInfoID, BlackList blackList) {
    ElderlyInfo elderlyInfo = elderlyInfoService.find(elderlyInfoID);
    if (elderlyInfo != null && blackList != null) {
      blackList.setTenantID(tenantAccountService.getCurrentTenantID());
      blackList.setElderlyInfo(elderlyInfo);
      blackList.setBlackDate(new Date());
      try {
        blackListService.save(blackList);
      } catch (Exception e) {
        // 老人唯一性 验证
        e.printStackTrace();
        LogUtil
            .debug(
                BlackListController.class,
                "BlackListController",
                "elderlyInfo.id=%s already existed in yly_blacklist table, can't add again, current tenant ID=%s",
                elderlyInfo.getId().toString(), tenantAccountService.getCurrentTenantID());
        return Message.error("yly.blacklist.exception.unique");
      }

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
    TenantInfo tenantInfo = null;
    if (tenantAccountService.getCurrentTenantOrgCode() != null) {
      tenantInfo =
          tenantInfoService.findTenantWithOrgCode(tenantAccountService.getCurrentTenantOrgCode());
    }
    model.addAttribute("blackList", blackList);
    model.addAttribute("tenantInfo", tenantInfo);
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
  public @ResponseBody Message update(BlackList blackList, Long elderlyInfoID) {
    ElderlyInfo elderlyInfo = elderlyInfoService.find(elderlyInfoID);
    blackList.setElderlyInfo(elderlyInfo);
    blackListService.update(blackList,"blackDate");
    return SUCCESS_MESSAGE;
  }
  /**
   * 导出数据前，计算当前呈现给用户的有多少条数据
   */
  @RequestMapping(value = "/count", method = RequestMethod.POST)
  public @ResponseBody Map<String, Long> count(String nameHidden, Date beginDateHidden, Date endDateHidden) {
    Long count = new Long(0);
    count = new Long(blackListService.countByFilter(nameHidden, beginDateHidden, endDateHidden));
    Map<String, Long> countMap = new HashMap<String, Long>(); 
    countMap.put("count", count);
    return countMap;
  }
  /**
   * 导出列表数据，即用户已经查询出来的数据
   */
  @RequestMapping(value = "/exportData", method = {RequestMethod.GET,RequestMethod.POST})
  public void exportData(HttpServletResponse response, String nameHidden, Date beginDateHidden, Date endDateHidden) {
    List<BlackList> blackListList = null;
    blackListList = blackListService.searchListByFilter(nameHidden, beginDateHidden, endDateHidden);
    if (blackListList != null && blackListList.size() > 0) {
      String title = "Black List"; // 工作簿标题，同时也是excel文件名前缀
      String[] headers = {"elderlyInfo.name", "blackDate", "elderlyInfo.elderlyPhoneNumber", "elderlyInfo.age", "cause"}; // 需要导出的字段
      String[] headersName = {"黑名单老人姓名", "录入时间", "电话号码", "年龄", "加入黑名单原因"}; // 字段对应列的列名
      // 导出数据到Excel
      List<Map<String, String>> blackListMapList = blackListService.prepareMap(blackListList);
      if (blackListMapList.size() > 0) {
        exportListToExcel(response, blackListMapList, title, headers, headersName);
      }
    }
  } 

}
