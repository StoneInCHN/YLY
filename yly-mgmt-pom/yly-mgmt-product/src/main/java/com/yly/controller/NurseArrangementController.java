package com.yly.controller;

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
import com.yly.controller.base.BaseController;
import com.yly.entity.NurseArrangement;
import com.yly.entity.ElderlyInfo;
import com.yly.framework.paging.Page;
import com.yly.framework.paging.Pageable;
import com.yly.service.NurseArrangementService;
import com.yly.service.ElderlyInfoService;
import com.yly.service.TenantAccountService;
import com.yly.utils.FieldFilterUtils;

/**
 * 护理员安排controller
 * 
 * @author luzhang
 *
 */
@Controller("nurseArrangementController")
@RequestMapping("/console/nurseArrangement")
public class NurseArrangementController extends BaseController {

  @Resource(name = "nurseArrangementServiceImpl")
  private NurseArrangementService nurseArrangementService;

  @Resource(name = "elderlyInfoServiceImpl")
  private ElderlyInfoService elderlyInfoService;

  @Resource(name = "tenantAccountServiceImpl")
  private TenantAccountService tenantAccountService;

  /**
   * 列表页面
   * 
   * @param model
   * @return
   */
  @RequestMapping(value = "/nurseArrangement", method = RequestMethod.GET)
  public String nurseArrangement(ModelMap model) {
    return "/nurseArrangement/nurseArrangement";
  }

  /**
   * 查询list
   * 
   * @param pageable
   * @return
   */
  @RequestMapping(value = "/list", method = RequestMethod.POST)
  public @ResponseBody Page<NurseArrangement> list(String keysOfElderlyName, Date beginDate,
      Date endDate, Pageable pageable) {
    Page<NurseArrangement> nurseArrangementPage = null;
    if (keysOfElderlyName == null && beginDate == null && endDate == null) {
      nurseArrangementPage = nurseArrangementService.findPage(pageable, true);
    } else {
      nurseArrangementPage = nurseArrangementService.searchPageByFilter(keysOfElderlyName, beginDate, endDate,
          pageable);
    }
    return nurseArrangementPage;
  }

  @RequestMapping(value = "/findAll", method = RequestMethod.POST)
  public @ResponseBody List<Map<String, Object>> findAll() {
    String[] propertys = {"id", "buildingName"};
    return FieldFilterUtils.filterCollectionMap(propertys, nurseArrangementService.findAll(true));
  }


  /**
   * 编辑
   * 
   * @param model
   * @param id
   * @return
   */
  @RequestMapping(value = "/detail", method = RequestMethod.GET)
  public String edit(ModelMap model, Long id, String handle) {
    if (id != null && handle != null) {
      model.addAttribute("nurseArrangement", nurseArrangementService.find(id));
      return "nurseArrangement/" + handle;
    }
    return "";
  }

  /**
   * 添加
   * 
   * @param nurseArrangement
   * @return
   */
  @RequestMapping(value = "/add", method = RequestMethod.POST)
  public @ResponseBody Message save(NurseArrangement nurseArrangement, Long elderlyInfoID) {
    ElderlyInfo elderlyInfo = elderlyInfoService.find(elderlyInfoID);
    if (elderlyInfo != null && nurseArrangement != null) {
      nurseArrangement.setTenantID(tenantAccountService.getCurrentTenantID());
      if (nurseArrangement.getElderlyInfo() != null) {
        nurseArrangementService.save(nurseArrangement);
        return SUCCESS_MESSAGE;
      }
    }
    return ERROR_MESSAGE;
  }

  /**
   * 更新
   * 
   * @param nurseArrangement
   * @param elderlyInfoID
   * @return
   */
  @RequestMapping(value = "/update", method = RequestMethod.POST)
  public @ResponseBody Message update(NurseArrangement nurseArrangement, Long elderlyInfoID) {
    ElderlyInfo elderlyInfo = elderlyInfoService.find(elderlyInfoID);
    if (elderlyInfo != null) {
      nurseArrangement.setTenantID(tenantAccountService.getCurrentTenantID());
      nurseArrangementService.update(nurseArrangement, "createDate");
      return SUCCESS_MESSAGE;
    }
    return ERROR_MESSAGE;
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
      for (Long id : ids) {
        NurseArrangement nurseArrangement = nurseArrangementService.find(id);
        if (nurseArrangement != null) {
          nurseArrangementService.delete(ids);
        }
      }
    }
    return SUCCESS_MESSAGE;
  }
}
