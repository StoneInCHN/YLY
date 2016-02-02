package com.yly.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yly.beans.Message;
import com.yly.controller.base.BaseController;
import com.yly.entity.NurseLevelChangeRecord;
import com.yly.entity.ElderlyInfo;
import com.yly.entity.SystemConfig;
import com.yly.entity.commonenum.CommonEnum.NurseChangeStatus;
import com.yly.framework.paging.Page;
import com.yly.framework.paging.Pageable;
import com.yly.service.NurseLevelChangeRecordService;
import com.yly.service.ElderlyInfoService;
import com.yly.service.SystemConfigService;
import com.yly.service.TenantAccountService;
import com.yly.utils.FieldFilterUtils;

/**
 * 护理变更controller
 * 
 * @author luzhang
 *
 */
@Controller("nurseLevelChangeRecordController")
@RequestMapping("/console/nurseLevelChangeRecord")
public class NurseLevelChangeRecordController extends BaseController {

  @Resource(name = "nurseLevelChangeRecordServiceImpl")
  private NurseLevelChangeRecordService nurseLevelChangeRecordService;

  @Resource(name = "elderlyInfoServiceImpl")
  private ElderlyInfoService elderlyInfoService;

  @Resource(name = "tenantAccountServiceImpl")
  private TenantAccountService tenantAccountService;
  
  @Resource(name = "systemConfigServiceImpl")
  private SystemConfigService systemConfigService;

  /**
   * 列表页面
   * 
   * @param model
   * @return
   */
  @RequestMapping(value = "/nurseLevelChangeRecord", method = RequestMethod.GET)
  public String nurseLevelChangeRecord(ModelMap model) {
    return "/nurseLevelChangeRecord/nurseLevelChangeRecord";
  }

  /**
   * 查询list
   * 
   * @param pageable
   * @return
   */
  @RequestMapping(value = "/list", method = RequestMethod.POST)
  public @ResponseBody Page<NurseLevelChangeRecord> list(Long nurseChangeElderlyID, Date nurseChangeStartDate,
      Date nurseChangeEndDate, String nurseChangeStatus, Pageable pageable) {
    Page<NurseLevelChangeRecord> nurseLevelChangeRecordPage = null;
    if (nurseChangeElderlyID == null && nurseChangeStartDate == null && nurseChangeEndDate == null && nurseChangeStatus == null) {
      nurseLevelChangeRecordPage = nurseLevelChangeRecordService.findPage(pageable, true);
    } else {
      nurseLevelChangeRecordPage = nurseLevelChangeRecordService.searchPageByFilter(nurseChangeElderlyID, 
          nurseChangeStartDate, nurseChangeEndDate, nurseChangeStatus, pageable, true);
    }
    return nurseLevelChangeRecordPage;
  }

  @RequestMapping(value = "/findAll", method = RequestMethod.POST)
  public @ResponseBody List<Map<String, Object>> findAll() {
    String[] propertys = {"id", "buildingName"};
    return FieldFilterUtils.filterCollectionMap(propertys, nurseLevelChangeRecordService.findAll(true));
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
      model.addAttribute("nurseLevelChangeRecord", nurseLevelChangeRecordService.find(id));
      return "nurseLevelChangeRecord/" + handle;
    }
    return "";
  }

  /**
   * 添加
   * 
   * @param nurseLevelChangeRecord
   * @return
   */
  @RequestMapping(value = "/add", method = RequestMethod.POST)
  public @ResponseBody Message save(NurseLevelChangeRecord nurseLevelChangeRecord, Long newNursingLevelId) {
    if (nurseLevelChangeRecord.getElderlyInfo() != null && nurseLevelChangeRecord.getElderlyInfo().getId() != null &&
        newNursingLevelId != null) {
      ElderlyInfo elderlyInfo = elderlyInfoService.find(nurseLevelChangeRecord.getElderlyInfo().getId());
      nurseLevelChangeRecord.setElderlyInfo(elderlyInfo);
      nurseLevelChangeRecord.setOldNurseLevel(elderlyInfo.getNursingLevel());
      SystemConfig newNursingLevel = systemConfigService.find(newNursingLevelId);
      nurseLevelChangeRecord.setNewNurseLevel(newNursingLevel);
      nurseLevelChangeRecord.setChangeDate(new Date());
      nurseLevelChangeRecord.setTenantID(tenantAccountService.getCurrentTenantID());
      nurseLevelChangeRecord.setNurseChangeStatus(NurseChangeStatus.ENABLE);
      
      Set<NurseLevelChangeRecord> nurseLevelChangeRecords = elderlyInfo.getNurseLevelChangeRecords();
      for (NurseLevelChangeRecord nurseLevelChangeRecordOld : nurseLevelChangeRecords) {
          if (nurseLevelChangeRecordOld.getNurseChangeStatus().equals(NurseChangeStatus.ENABLE)) {
            nurseLevelChangeRecordOld.setNurseChangeStatus(NurseChangeStatus.DISABLE);
          }
      }
      elderlyInfo.getNurseLevelChangeRecords().add(nurseLevelChangeRecord);
      elderlyInfo.setNursingLevel(newNursingLevel);
      
      elderlyInfoService.update(elderlyInfo);
      
      return SUCCESS_MESSAGE;
    }
    return ERROR_MESSAGE;
  }

  /**
   * 更新
   * 
   * @param nurseLevelChangeRecord
   * @param elderlyInfoID
   * @return
   */
  @RequestMapping(value = "/update", method = RequestMethod.POST)
  public @ResponseBody Message update(NurseLevelChangeRecord nurseLevelChangeRecord) {
    NurseLevelChangeRecord nurseLevelChangeRecordDB = nurseLevelChangeRecordService.find(nurseLevelChangeRecord.getId());
    if (nurseLevelChangeRecordDB != null) {
      SystemConfig newNursingLevel = systemConfigService.find(nurseLevelChangeRecord.getNewNurseLevel().getId());
      nurseLevelChangeRecordDB.setNewNurseLevel(newNursingLevel);
      nurseLevelChangeRecordDB.setRemark(nurseLevelChangeRecord.getRemark());
      nurseLevelChangeRecordService.update(nurseLevelChangeRecordDB);
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
        NurseLevelChangeRecord nurseLevelChangeRecord = nurseLevelChangeRecordService.find(id);
        if (nurseLevelChangeRecord != null) {
          nurseLevelChangeRecordService.delete(ids);
        }
      }
    }
    return SUCCESS_MESSAGE;
  }
}
