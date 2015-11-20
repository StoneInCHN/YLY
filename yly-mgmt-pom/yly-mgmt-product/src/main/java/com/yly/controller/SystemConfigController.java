package com.yly.controller;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yly.beans.Message;
import com.yly.common.log.LogUtil;
import com.yly.controller.base.BaseController;
import com.yly.entity.SystemConfig;
import com.yly.entity.commonenum.CommonEnum.ConfigKey;
import com.yly.framework.ordering.Ordering.Direction;
import com.yly.framework.paging.Page;
import com.yly.framework.paging.Pageable;
import com.yly.json.response.TreeNodeResponse;
import com.yly.service.SystemConfigService;
import com.yly.service.TenantAccountService;

@Controller("systemConfigController")
@RequestMapping("/console/systemConfig")
public class SystemConfigController extends BaseController {

  @Resource(name = "systemConfigServiceImpl")
  private SystemConfigService systemConfigService;

  @Resource(name = "tenantAccountServiceImpl")
  private TenantAccountService tenantAccountService;

  /**
   * 列表页面
   * 
   * @param model
   * @return
   */
  @RequestMapping(value = "/systemConfig", method = RequestMethod.GET)
  public String list(ModelMap model) {
    return "/systemConfig/systemConfig";
  }

  /**
   * 展示某个配置项对应的配置项值
   * 
   * @param beginDate
   * @param endDate
   * @param pageable
   * @param model
   * @return
   */
  @RequestMapping(value = "/getListConfigValueByKey", method = RequestMethod.POST)
  public @ResponseBody List<SystemConfig> getListConfigValueByKey(Pageable pageable,
      ModelMap model, Integer configKey) {
    if (configKey == null) {
      return null;
    }
    return systemConfigService.getListConfigValueByKey(configKey);
  }

  /**
   * 返回所有ConfigKey
   * 
   * @param pageable
   * @param model
   * @return
   */
  @RequestMapping(value = "/getAllConfigKey", method = RequestMethod.POST)
  public @ResponseBody List<TreeNodeResponse> getAllConfigKey(Pageable pageable, ModelMap model) {
    List<TreeNodeResponse> treeList = new ArrayList<TreeNodeResponse>();
    for (ConfigKey configKey : ConfigKey.class.getEnumConstants()) {
      if(configKey==ConfigKey.PHYSICALEXAMITEM){
        continue;
      }
      TreeNodeResponse treeNodeResponse = new TreeNodeResponse();
      treeNodeResponse.setId(Integer.toUnsignedLong(configKey.ordinal()));
      treeNodeResponse.setText(configKey.getKeyName());
      treeNodeResponse.setIconCls("icon-large-shapes");
      treeList.add(treeNodeResponse);
    }
    return treeList;
  }

  /**
   * 添加
   * 
   * @param systemConfig
   * @return
   */
  @RequestMapping(value = "/add", method = RequestMethod.POST)
  public @ResponseBody Message add(SystemConfig systemConfig, Integer configKeyId) {
    if (systemConfig != null && configKeyId != null) {
      systemConfig.setConfigKey(ConfigKey.values()[configKeyId]);
      systemConfig.setTenantID(tenantAccountService.getCurrentTenantID());
      systemConfigService.save(systemConfig);
    }
    return SUCCESS_MESSAGE;
  }

  @RequestMapping(value = "/listConfigValue", method = RequestMethod.POST)
  public @ResponseBody Page<SystemConfig> list(Date beginDate, Date endDate, Pageable pageable,
      ModelMap model) {
    return systemConfigService.findPage(pageable);
  }

  /**
   * 根据configKey查询
   * 
   * @param configKey
   * @param direction
   * @return
   */
  @RequestMapping(value = "/findByConfigKey", method = RequestMethod.POST)
  public @ResponseBody List<Map<String, Object>> findByConfigKey(ConfigKey configKey,
      Direction direction) {
    return systemConfigService.findByConfigKey(configKey, direction);
  }

  /**
   * 根据结算日期获取缴费结束时间
   * 
   * @param currentDay
   * @return
   */
  @RequestMapping(value = "/getBillEndDate", method = RequestMethod.POST)
  public @ResponseBody Map<String, Object> findByConfigKey(Date currentDay) {

    return systemConfigService.getBillingDate(currentDay);
  }

  /**
   * 编辑页面
   * 
   * @param model
   * @param vendorId
   * @return
   */
  @RequestMapping(value = "/edit", method = RequestMethod.GET)
  public String edit(ModelMap model, Long id) {
    model.addAttribute("systemConfig", systemConfigService.find(id));
    return "/systemConfig/edit";
  }

  /**
   * 更新
   * 
   * @return
   */
  @RequestMapping(value = "/update", method = RequestMethod.POST)
  public @ResponseBody Message update(SystemConfig systemConfig) {
    systemConfigService.update(systemConfig);
    return SUCCESS_MESSAGE;
  }

  /**
   * 删除
   * 
   * @param ids
   * @return
   */
  @RequestMapping(value = "/delete", method = RequestMethod.POST)
  public @ResponseBody Message delete(Long[] ids) {
    if (ids != null) {
      // 检查是否能被删除
      try {
        systemConfigService.delete(ids);
      } catch (Exception e) {
        e.printStackTrace();
        LogUtil.debug(SystemConfigController.class, "SystemConfigController",
            "systemConfig already used by other table, could can't delete, current tenant ID=%s",
            tenantAccountService.getCurrentTenantID());
        return Message.error("yly.systemConfig.exception");
      }
    }
    return SUCCESS_MESSAGE;
  }

}
