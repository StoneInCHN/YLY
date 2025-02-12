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
import com.yly.entity.Bed;
import com.yly.entity.ElderlyInfo;
import com.yly.entity.SystemConfig;
import com.yly.entity.commonenum.CommonEnum.DeleteStatus;
import com.yly.entity.commonenum.CommonEnum.ElderlyStatus;
import com.yly.entity.commonenum.CommonEnum.UsageState;
import com.yly.framework.filter.Filter;
import com.yly.framework.filter.Filter.Operator;
import com.yly.framework.paging.Page;
import com.yly.framework.paging.Pageable;
import com.yly.service.BedService;
import com.yly.service.ElderlyInfoService;
import com.yly.service.FileService;
import com.yly.service.SystemConfigService;
import com.yly.service.TenantAccountService;

/**
 * 办理入院
 * 
 * @author shijun
 *
 */
@Controller("admissionController")
@RequestMapping("console/admission")
public class AdmissionController extends BaseController {

  @Resource(name = "elderlyInfoServiceImpl")
  private ElderlyInfoService elderlyInfoService;

  @Resource(name = "tenantAccountServiceImpl")
  private TenantAccountService tenantAccountService;

  @Resource(name = "systemConfigServiceImpl")
  private SystemConfigService systemConfigService;

  @Resource(name = "fileServiceImpl")
  private FileService fileService;
  
  @Resource(name = "bedServiceImpl")
  private BedService bedService;

  /**
   * 列表页面
   * 
   * @param model
   * @return
   */
  @RequestMapping(value = "/admission", method = RequestMethod.GET)
  public String list(ModelMap model) {
    return "/admission/admission";
  }

  /**
   * 查询咨询记录
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

    if (elderlyInfo.getIdentifier() != null || elderlyInfo.getName() != null
        || elderlyInfo.getElderlyStatus() != null || beHospitalizedBeginDate != null
        || beHospitalizedEndDate != null) {
      elderlyInfo.setDeleteStatus(DeleteStatus.NOT_DELETED);
      return elderlyInfoService.searchElderlyInfo(beHospitalizedBeginDate, beHospitalizedEndDate, elderlyInfo, pageable);
    }

    List<Filter> filters = new ArrayList<Filter>();
    Filter delFilter = new Filter("deleteStatus", Operator.eq, DeleteStatus.NOT_DELETED);
    filters.add(delFilter);
    pageable.setFilters(filters);

    return elderlyInfoService.findPage(pageable, true);
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
      
      if(elderlyInfo.getBed() != null && elderlyInfo.getBed().getId() != null){//办理入院的时候选择了床位
          Bed bed = bedService.find(elderlyInfo.getBed().getId());
          if (bed != null) {
            bed.setElderlyInfo(elderlyInfo);
            bed.setUsageState(UsageState.OCCUPIED);
            elderlyInfo.setBed(bed);
            elderlyInfoService.save(elderlyInfo);
          }else{
            return Message.error("yly.elderlyInfo.choose.bed.error");
          }                   
      }else {
        return Message.error("yly.elderlyInfo.choose.bed.error");
      }
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
    return "admission/edit";
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
    return "admission/details";
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

  @RequestMapping(value = "/uploadProfilePhoto", method = RequestMethod.POST)
  public @ResponseBody Message uploadProfilePhoto(@RequestParam("file") MultipartFile file,
      String identifier, Long elderlyInfoId) {
    Map<String, String> paramMap = new HashMap<String, String>();
    paramMap.put("identifier", identifier);
//    String filePath = fileService.upload(FileType.PROFILE_PICTURE, file, identifier);
    String filePath = fileService.upload(FileType.PROFILE_PICTURE, file, paramMap);
    if (filePath != null && elderlyInfoId != null) {
      ElderlyInfo elderlyInfo = elderlyInfoService.find(elderlyInfoId);
      elderlyInfo.setProfilePhoto(filePath);
      elderlyInfoService.update(elderlyInfo);
      return Message.success(filePath);
    } else {
      return ERROR_MESSAGE;
    }

  }

}
