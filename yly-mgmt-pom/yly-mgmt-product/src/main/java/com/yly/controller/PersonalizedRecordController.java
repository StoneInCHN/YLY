package com.yly.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yly.beans.Message;
import com.yly.common.log.LogUtil;
import com.yly.controller.base.BaseController;
import com.yly.entity.ElderlyInfo;
import com.yly.entity.PersonalizedRecord;
import com.yly.entity.TenantUser;
import com.yly.framework.paging.Page;
import com.yly.framework.paging.Pageable;
import com.yly.service.ElderlyInfoService;
import com.yly.service.PersonalizedRecordService;
import com.yly.service.TenantUserService;

@Controller("personalizedRecordController")
@RequestMapping("/console/personalizedRecord")
public class PersonalizedRecordController extends BaseController {

  @Resource(name = "personalizedRecordServiceImpl")
  private PersonalizedRecordService personalizedRecordService;

  @Resource(name = "elderlyInfoServiceImpl")
  private ElderlyInfoService elderlyInfoService;

  @Resource(name = "tenantUserServiceImpl")
  private TenantUserService tenantUserService;

  @RequestMapping(value = "/personalizedRecord", method = RequestMethod.GET)
  public String personalizedRecord() {
    return "personalizedRecord/personalizedRecord";
  }

  @RequestMapping(value = "/list", method = RequestMethod.POST)
  public @ResponseBody Page<PersonalizedRecord> list(Pageable pageable) {
    return personalizedRecordService.findPage(pageable);
  }

  /**
   * 编辑
   * 
   * @param model
   * @param id
   * @return
   */
  @RequestMapping(value = "/edit", method = RequestMethod.GET)
  public String edit(ModelMap model, Long id) {
    model.addAttribute("personalizedRecord", personalizedRecordService.find(id));
    return "personalizedRecord/edit";
  }

  /**
   * 保存
   * 
   * @param building
   * @return
   */
  @RequestMapping(value = "/add", method = RequestMethod.POST)
  public @ResponseBody Message add(PersonalizedRecord personalizedRecord, Long elderlyInfoId,
      Long operatorId) {
    try {
      if (elderlyInfoId != null) {
        ElderlyInfo elderlyInfo = elderlyInfoService.find(elderlyInfoId);
        personalizedRecord.setElderlyInfo(elderlyInfo);
      }
      if (operatorId != null) {
        TenantUser tenantUser = tenantUserService.find(operatorId);
        personalizedRecord.setOperator(tenantUser.getRealName());
      }

      personalizedRecordService.save(personalizedRecord, true);

      return SUCCESS_MESSAGE;
    } catch (Exception e) {
      LogUtil.debug(PersonalizedNurseController.class, "add", "add personalizedRecord +%s",
          personalizedRecord);
      return ERROR_MESSAGE;
    }


  }


  @RequestMapping(value = "/update", method = RequestMethod.POST)
  public @ResponseBody Message update(PersonalizedRecord personalizedRecord, Long elderlyInfoId,
      Long operatorId) {
    try {
      if (elderlyInfoId != null) {
        ElderlyInfo elderlyInfo = elderlyInfoService.find(elderlyInfoId);
        personalizedRecord.setElderlyInfo(elderlyInfo);
      }
      if (operatorId != null) {
        TenantUser tenantUser = tenantUserService.find(operatorId);
        personalizedRecord.setOperator(tenantUser.getRealName());
      }

      personalizedRecordService.update(personalizedRecord, "tenantID");

      return SUCCESS_MESSAGE;
    } catch (Exception e) {
      LogUtil.debug(PersonalizedNurseController.class, "update", "update personalizedRecord : %s",
          personalizedRecord);
      return ERROR_MESSAGE;
    }
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
      personalizedRecordService.delete(ids);
    }
    return SUCCESS_MESSAGE;
  }
}
