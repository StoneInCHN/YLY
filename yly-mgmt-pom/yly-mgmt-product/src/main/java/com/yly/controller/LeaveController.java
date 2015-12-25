package com.yly.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.yly.beans.FileInfo.FileType;
import com.yly.beans.Message;
import com.yly.controller.base.BaseController;
import com.yly.entity.ElderlyInfo;
import com.yly.entity.SystemConfig;
import com.yly.entity.commonenum.CommonEnum.DeleteStatus;
import com.yly.entity.commonenum.CommonEnum.ElderlyStatus;
import com.yly.framework.filter.Filter;
import com.yly.framework.filter.Filter.Operator;
import com.yly.framework.paging.Page;
import com.yly.framework.paging.Pageable;
import com.yly.service.ElderlyInfoService;
import com.yly.service.FileService;
import com.yly.service.SystemConfigService;
import com.yly.service.TenantAccountService;

/**
 * 办理出院以及退住结算
 * 
 * @author luzhang
 *
 */
@Controller("leaveController")
@RequestMapping("console/leave")
public class LeaveController extends BaseController {

  @Resource(name = "elderlyInfoServiceImpl")
  private ElderlyInfoService elderlyInfoService;

  @Resource(name = "tenantAccountServiceImpl")
  private TenantAccountService tenantAccountService;

  @Resource(name = "systemConfigServiceImpl")
  private SystemConfigService systemConfigService;

  @Resource(name = "fileServiceImpl")
  private FileService fileService;

  /**
   * 列表页面
   * 
   * @param model
   * @return
   */
  @RequestMapping(value = "/leave", method = RequestMethod.GET)
  public String list(ModelMap model) {
    return "/leave/leave";
  }

  /**
   * 查询出院的咨询记录
   * 
   * @param beginDate
   * @param endDate
   * @param pageable
   * @param model
   * @return
   */
  @RequestMapping(value = "/list", method = RequestMethod.POST)
  public @ResponseBody Page<ElderlyInfo> list(Date beHospitalizedBeginDate,
      Date beHospitalizedEndDate, ElderlyInfo elderlyInfo, Pageable pageable, ModelMap model) {
    Page<ElderlyInfo> elderlyInfoPage = null;
    if (elderlyInfo.getIdentifier() != null || elderlyInfo.getName() != null
        || elderlyInfo.getElderlyStatus() != null || beHospitalizedBeginDate != null
        || beHospitalizedEndDate != null) {
      elderlyInfoPage = elderlyInfoService.searchElderlyInfo(beHospitalizedBeginDate, beHospitalizedEndDate, elderlyInfo, pageable);
      return elderlyInfoPage;
    }

    List<Filter> filters = new ArrayList<Filter>();
    Filter delFilter = new Filter("deleteStatus", Operator.eq, DeleteStatus.NOT_DELETED);//逻辑未删除
    filters.add(delFilter);
    Filter inNursingHome = new Filter("elderlyStatus", Operator.in, ElderlyStatus.IN_PROGRESS_CHECKOUT, ElderlyStatus.OUT_NURSING_HOME);//出院或者出院办理
    filters.add(inNursingHome);  
    pageable.setFilters(filters);
    elderlyInfoPage = elderlyInfoService.findPage(pageable, true);
    return elderlyInfoPage;
  }

  /**
   * 添加
   * 
   * @param elderlyInfo
   * @return
   */
  @RequestMapping(value = "/add", method = RequestMethod.POST)
  public @ResponseBody Message add(Long personnelCategoryId, Long nursingLevelId,
      Long evaluatingResultId, ElderlyInfo elderlyInfo) {

    SystemConfig personnelCategory = null;
    SystemConfig nursingLevel = null;
    SystemConfig evaluatingResult = null;

    if (personnelCategoryId != null) {
      personnelCategory = systemConfigService.find(personnelCategoryId);
    }

    if (nursingLevelId != null) {
      nursingLevel = systemConfigService.find(nursingLevelId);
    }

    if (evaluatingResultId != null) {
      evaluatingResult = systemConfigService.find(evaluatingResultId);
    }

    if (elderlyInfo != null) {
      Long currnetTenantId = tenantAccountService.getCurrentTenantID();
      /**
       * 设置租户
       */
      elderlyInfo.setTenantID(currnetTenantId);
      elderlyInfo.setPersonnelCategory(personnelCategory);
      elderlyInfo.setNursingLevel(nursingLevel);
      elderlyInfo.setEvaluatingResult(evaluatingResult);
      elderlyInfo.setDeleteStatus(DeleteStatus.NOT_DELETED);

      elderlyInfo.getElderlyConsigner().setTenantID(currnetTenantId);
      elderlyInfo.getElderlyConsigner().setElderlyInfo(elderlyInfo);

      /**
       * 设置老人状态
       */
      elderlyInfo.setElderlyStatus(ElderlyStatus.IN_PROGRESS_CHECKIN);

      elderlyInfoService.save(elderlyInfo);
    }

    return SUCCESS_MESSAGE;
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
    ElderlyInfo elderlyInfo = elderlyInfoService.find(id);
    model.addAttribute("elderlyInfo", elderlyInfo);
    return "leave/edit";
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
    ElderlyInfo elderlyInfo = elderlyInfoService.find(id);
    model.addAttribute("elderlyInfo", elderlyInfo);
    return "leave/details";
  }

  /**
   * 更新
   * 
   * @param elderlyInfo
   * @return
   */
  @RequestMapping(value = "/update", method = RequestMethod.POST)
  public @ResponseBody Message update(Long personnelCategoryEditId, Long nursingLevelEditId,
      Long evaluatingResultEditId, ElderlyInfo elderlyInfo) {

    SystemConfig personnelCategory = null;
    SystemConfig nursingLevel = null;
    SystemConfig evaluatingResult = null;

    if (personnelCategoryEditId != null) {
      personnelCategory = systemConfigService.find(personnelCategoryEditId);
    }

    if (nursingLevelEditId != null) {
      nursingLevel = systemConfigService.find(nursingLevelEditId);
    }

    if (evaluatingResultEditId != null) {
      evaluatingResult = systemConfigService.find(evaluatingResultEditId);
    }
    /**
     * 设置租户
     */
    Long currnetTenantId = tenantAccountService.getCurrentTenantID();

    elderlyInfo.setTenantID(currnetTenantId);
    elderlyInfo.setDeleteStatus(DeleteStatus.NOT_DELETED);
    elderlyInfo.getElderlyConsigner().setTenantID(currnetTenantId);
    elderlyInfo.getElderlyConsigner().setElderlyInfo(elderlyInfo);

    elderlyInfo.setPersonnelCategory(personnelCategory);
    elderlyInfo.setNursingLevel(nursingLevel);
    elderlyInfo.setEvaluatingResult(evaluatingResult);
    /**
     * 更新入院老人数据的时候老人状态始终为入院办理
     */
    elderlyInfo.setElderlyStatus(ElderlyStatus.IN_PROGRESS_CHECKIN);

    elderlyInfoService.update(elderlyInfo,"profilePhoto");
    return SUCCESS_MESSAGE;
  }

  /**
   * 逻辑删除
   */
  @RequestMapping(value = "/delete", method = RequestMethod.POST)
  public @ResponseBody Message delete(Long[] ids) {
    if (ids != null) {
      List<ElderlyInfo> elderlyInfoList = elderlyInfoService.findList(ids);

      for (ElderlyInfo elderlyInfo : elderlyInfoList) {
        elderlyInfo.setDeleteStatus(DeleteStatus.DELETED);
        elderlyInfoService.update(elderlyInfo);
      }
    }
    return SUCCESS_MESSAGE;
  }


}