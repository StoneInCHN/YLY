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
import com.yly.entity.NurseArrangementRecord;
import com.yly.entity.TenantUser;
import com.yly.framework.paging.Page;
import com.yly.framework.paging.Pageable;
import com.yly.service.NurseArrangementRecordService;
import com.yly.service.NurseArrangementService;
import com.yly.service.ElderlyInfoService;
import com.yly.service.TenantAccountService;
import com.yly.service.TenantUserService;
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
  @Resource(name = "nurseArrangementRecordServiceImpl")
  private NurseArrangementRecordService nurseArrangementRecordService;
  
  @Resource(name = "elderlyInfoServiceImpl")
  private ElderlyInfoService elderlyInfoService;

  @Resource(name = "tenantAccountServiceImpl")
  private TenantAccountService tenantAccountService;
  
  @Resource(name = "tenantUserServiceImpl")
  private TenantUserService tenantUserService;
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
  public @ResponseBody Page<NurseArrangement> list(String nurseNameSearch, Date nurseStartDateSearch,
      Date nurseEndDateSearch, Long elderlyIDSearch, Long nurseAssistantIDSearch, Pageable pageable) {
    Page<NurseArrangement> nurseArrangementPage = null;
    if (nurseNameSearch == null && nurseStartDateSearch == null && nurseEndDateSearch == null &&
        elderlyIDSearch == null&& nurseAssistantIDSearch == null) {
      nurseArrangementPage = nurseArrangementService.findPage(pageable, true);
    } else {
      nurseArrangementPage = nurseArrangementService.searchPageByFilter(nurseNameSearch, nurseStartDateSearch, nurseEndDateSearch,
          elderlyIDSearch, nurseAssistantIDSearch, pageable, true);
    }
    return nurseArrangementPage;
  }
  /**
   * 查询护理明细list
   * 
   * @param pageable
   * @return
   */
  @RequestMapping(value = "/listRecord", method = RequestMethod.POST)
  public @ResponseBody Page<NurseArrangementRecord> listRecord(String nurseNameSearchForRecord, Long nurseArrangemenIDSearch, 
      Pageable pageable) {
    Page<NurseArrangementRecord> nurseArrangementRecords = null;
    if (nurseNameSearchForRecord == null && nurseArrangemenIDSearch == null) {
      nurseArrangementRecords = nurseArrangementRecordService.findPage(pageable, true);
    } else {
      nurseArrangementRecords = nurseArrangementRecordService.searchPageByFilter(nurseNameSearchForRecord, 
          nurseArrangemenIDSearch, pageable, true);
    }   
    return nurseArrangementRecords;
  }
  
  @RequestMapping(value = "/findAll", method = RequestMethod.POST)
  public @ResponseBody List<Map<String, Object>> findAll() {
    String[] propertys = {"id", "buildingName"};
    return FieldFilterUtils.filterCollectionMap(propertys, nurseArrangementService.findAll(true));
  }


  /**
   * 编辑护理员安排
   * 
   * @param model
   * @param id
   * @return
   */
  @RequestMapping(value = "/detail", method = RequestMethod.GET)
  public String detail(ModelMap model, Long id, String handle) {
    if (id != null && handle != null) {
      model.addAttribute("nurseArrangement", nurseArrangementService.find(id));
      return "nurseArrangement/" + handle;
    }
    return "";
  }
  /**
   * 编辑护理员管理明细
   * 
   * @param model
   * @param id
   * @return
   */
  @RequestMapping(value = "/detailRecord", method = RequestMethod.GET)
  public String detailRecord(ModelMap model, Long id, String handle) {
    if (id != null && handle != null) {
      model.addAttribute("nurseArrangementRecord", nurseArrangementRecordService.find(id));
      return "nurseArrangement/" + handle;
    }
    return "";
  }
  
  /**
   * 添加护理员安排
   * 
   * @param nurseArrangement
   * @return
   */
  @RequestMapping(value = "/add", method = RequestMethod.POST)
  public @ResponseBody Message add(NurseArrangement nurseArrangement) {
    if (nurseArrangement.getElderlyInfo() != null && nurseArrangement.getElderlyInfo().getId() != null && 
        nurseArrangement.getNurseAssistant() != null && nurseArrangement.getNurseAssistant().getId() != null) {
      ElderlyInfo elderlyInfo = elderlyInfoService.find(nurseArrangement.getElderlyInfo().getId());
      TenantUser nurseAssistant = tenantUserService.find(nurseArrangement.getNurseAssistant().getId());
      if (elderlyInfo != null && nurseAssistant != null) {
        nurseArrangement.setTenantID(tenantAccountService.getCurrentTenantID());
        nurseArrangement.setElderlyInfo(elderlyInfo);
        nurseArrangement.setNurseAssistant(nurseAssistant);
        nurseArrangementService.save(nurseArrangement);
        return SUCCESS_MESSAGE;
      }
    }
    return ERROR_MESSAGE; 
  }
  /**
   * 添加护理员管理明细
   * 
   * @param nurseArrangement
   * @return
   */
  @RequestMapping(value = "/addRecord", method = RequestMethod.POST)
  public @ResponseBody Message addRecord(NurseArrangementRecord nurseArrangementRecord, Long nurseArrangementID) {
    if (nurseArrangementID != null) {
      NurseArrangement nurseArrangementDB = nurseArrangementService.find(nurseArrangementID);
      if (nurseArrangementDB != null) {
        nurseArrangementRecord.setTenantID(tenantAccountService.getCurrentTenantID());
        nurseArrangementRecord.setNurseArrangement(nurseArrangementDB);
        nurseArrangementRecord.setElderlyName(nurseArrangementDB.getElderlyInfo().getName());
        nurseArrangementRecord.setNurseOperator(nurseArrangementDB.getNurseAssistant().getRealName());
        if (nurseArrangementRecord.getNurseServiceTime() == null) {
          nurseArrangementRecord.setNurseServiceTime(new Date());
        }
        nurseArrangementRecordService.save(nurseArrangementRecord);
        return SUCCESS_MESSAGE;
      }
    }
    return ERROR_MESSAGE; 
  }
  
  /**
   * 更新护理员安排
   * 
   * @param nurseArrangement
   * @param elderlyInfoID
   * @return
   */
  @RequestMapping(value = "/update", method = RequestMethod.POST)
  public @ResponseBody Message update(NurseArrangement nurseArrangement) {
    if (nurseArrangement.getId() != null &&
        nurseArrangement.getElderlyInfo() != null && nurseArrangement.getElderlyInfo().getId() != null && 
        nurseArrangement.getNurseAssistant() != null && nurseArrangement.getNurseAssistant().getId() != null) {
      NurseArrangement nurseArrangementDB = nurseArrangementService.find(nurseArrangement.getId());
      if (nurseArrangementDB != null) {
        nurseArrangement.setTenantID(tenantAccountService.getCurrentTenantID());
        nurseArrangementService.update(nurseArrangement, "createDate");
        return SUCCESS_MESSAGE;
      }else {
        return ERROR_MESSAGE;
      }
    }
    return ERROR_MESSAGE;
  }
  /**
   * 更新护理员管理明细
   * 
   * @param nurseArrangement
   * @param elderlyInfoID
   * @return
   */
  @RequestMapping(value = "/updateRecord", method = RequestMethod.POST)
  public @ResponseBody Message updateRecord(NurseArrangementRecord nurseArrangementRecord) {
    if (nurseArrangementRecord.getId() != null) {
      NurseArrangementRecord nurseArrangementRecordDB = nurseArrangementRecordService.find(nurseArrangementRecord.getId());
      if (nurseArrangementRecordDB != null) {
        nurseArrangementRecord.setTenantID(tenantAccountService.getCurrentTenantID());
        nurseArrangementRecord.setNurseArrangement(nurseArrangementRecordDB.getNurseArrangement());
        nurseArrangementRecordService.update(nurseArrangementRecord, "createDate");
        return SUCCESS_MESSAGE;
      }else {
        return ERROR_MESSAGE;
      }
    }
    return ERROR_MESSAGE;
  }
  /**
   * 删除护理员安排
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
  /**
   * 删除护理员安排
   * 
   * @param ids
   * @return
   */
  @RequestMapping(value = "/deleteRecord", method = RequestMethod.POST)
  public @ResponseBody Message deleteRecord(Long[] ids) {
    if (ids != null) {
      for (Long id : ids) {
        NurseArrangementRecord nurseArrangementRecord = nurseArrangementRecordService.find(id);
        if (nurseArrangementRecord != null) {
          nurseArrangementRecordService.delete(ids);
        }
      }
    }
    return SUCCESS_MESSAGE;
  }
  /**
   * 护理员安排公共搜索页面
   * 
   * @param model
   * @return
   */
  @RequestMapping(value = "/nurseArrangementSearch", method = RequestMethod.GET)
  public String nurseArrangementSearch(ModelMap model) {
    return "/common/nurseArrangementSearch";
  }
}
