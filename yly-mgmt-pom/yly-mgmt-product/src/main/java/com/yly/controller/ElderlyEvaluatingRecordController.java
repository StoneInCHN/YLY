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
import com.yly.entity.ElderlyEvaluatingRecord;
import com.yly.entity.ElderlyInfo;
import com.yly.entity.EvaluatingSection;
import com.yly.framework.paging.Page;
import com.yly.framework.paging.Pageable;
import com.yly.service.ElderlyEvaluatingRecordService;
import com.yly.service.ElderlyInfoService;
import com.yly.service.EvaluatingSectionService;
import com.yly.service.TenantAccountService;
import com.yly.utils.FieldFilterUtils;

/**
 * 入院评估记录controller
 * 
 * @author luzhang
 *
 */
@Controller("elderlyEvaluatingRecordController")
@RequestMapping("/console/elderlyEvaluatingRecord")
public class ElderlyEvaluatingRecordController extends BaseController {

  @Resource(name = "elderlyEvaluatingRecordServiceImpl")
  private ElderlyEvaluatingRecordService elderlyEvaluatingRecordService;

  @Resource(name = "elderlyInfoServiceImpl")
  private ElderlyInfoService elderlyInfoService;

  @Resource(name = "tenantAccountServiceImpl")
  private TenantAccountService tenantAccountService;
  
  @Resource(name = "evaluatingSectionServiceImpl")
  private EvaluatingSectionService evaluatingSectionService;
  /**
   * 列表页面
   * 
   * @param model
   * @return
   */
  @RequestMapping(value = "/elderlyEvaluatingRecord", method = RequestMethod.GET)
  public String elderlyEvaluatingRecord(ModelMap model) {
    return "/elderlyEvaluatingRecord/elderlyEvaluatingRecord";
  }

  /**
   * 查询list
   * 
   * @param pageable
   * @return
   */
  @RequestMapping(value = "/list", method = RequestMethod.POST)
  public @ResponseBody Page<ElderlyEvaluatingRecord> list(String keysOfElderlyName, Date beginDate,
      Date endDate, Pageable pageable) {
    Page<ElderlyEvaluatingRecord> elderlyEvaluatingRecordPage = null;
    if (keysOfElderlyName == null && beginDate == null && endDate == null) {
      elderlyEvaluatingRecordPage = elderlyEvaluatingRecordService.findPage(pageable, true);
    } else {
      elderlyEvaluatingRecordPage = elderlyEvaluatingRecordService.searchPageByFilter(keysOfElderlyName, beginDate, endDate,
          pageable);
    }
    return elderlyEvaluatingRecordPage;
  }

  @RequestMapping(value = "/findAll", method = RequestMethod.POST)
  public @ResponseBody List<Map<String, Object>> findAll() {
    String[] propertys = {"id", "buildingName"};
    return FieldFilterUtils.filterCollectionMap(propertys, elderlyEvaluatingRecordService.findAll(true));
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
      List<EvaluatingSection> evaluatingSections = evaluatingSectionService.findAll();
      ElderlyEvaluatingRecord elderlyEvaluatingRecord = elderlyEvaluatingRecordService.find(id);
      if (elderlyEvaluatingRecord != null && evaluatingSections != null && evaluatingSections.size() > 0) {
        //每个模块对应评分规则
        Map<String,String> sectionScoreRuleMap = elderlyEvaluatingRecordService.getSectionScoreRuleMap();
        //每个模块对应总得分
        Map<String,Integer> sectionScoreMap = elderlyEvaluatingRecordService.getSectionScoreMap(evaluatingSections, elderlyEvaluatingRecord);
        //每个模块对应级别
        Map<String,Integer> sectionLevelMap = elderlyEvaluatingRecordService.getSectionLevelMap(evaluatingSections, elderlyEvaluatingRecord, sectionScoreMap);

        model.addAttribute("sectionScoreRuleMap", sectionScoreRuleMap);
        model.addAttribute("sectionScoreMap", sectionScoreMap);
        model.addAttribute("sectionLevelMap", sectionLevelMap);
        model.addAttribute("formScoreRule",elderlyEvaluatingRecordService.getFormScoreRule());
        model.addAttribute("formPrimaryLevel", elderlyEvaluatingRecordService.getFormPrimaryLevel(sectionLevelMap));
        model.addAttribute("elderlyEvaluatingRecord", elderlyEvaluatingRecord);
        
      }
      return "elderlyEvaluatingRecord/" + handle;
    }
    return "";
  }

  /**
   * 添加
   * 
   * @param elderlyEvaluatingRecord
   * @return
   */
  @RequestMapping(value = "/add", method = RequestMethod.POST)
  public @ResponseBody Message save(ElderlyEvaluatingRecord elderlyEvaluatingRecord, Long elderlyInfoID) {
    ElderlyInfo elderlyInfo = elderlyInfoService.find(elderlyInfoID);
    if (elderlyInfo != null && elderlyEvaluatingRecord != null) {
      elderlyEvaluatingRecord.setElderlyInfo(elderlyInfo);
      elderlyEvaluatingRecord.setTenantID(tenantAccountService.getCurrentTenantID());
      if (elderlyEvaluatingRecord.getEvaluatingResult() != null) {
        elderlyEvaluatingRecordService.save(elderlyEvaluatingRecord);
        return SUCCESS_MESSAGE;
      }
    }
    return ERROR_MESSAGE;
  }

  /**
   * 更新
   * 
   * @param elderlyEvaluatingRecord
   * @param elderlyInfoID
   * @return
   */
  @RequestMapping(value = "/update", method = RequestMethod.POST)
  public @ResponseBody Message update(ElderlyEvaluatingRecord elderlyEvaluatingRecord, Long elderlyInfoID) {
    ElderlyInfo elderlyInfo = elderlyInfoService.find(elderlyInfoID);
    if (elderlyInfo != null) {
      elderlyEvaluatingRecord.setElderlyInfo(elderlyInfo);
      elderlyEvaluatingRecord.setTenantID(tenantAccountService.getCurrentTenantID());
      elderlyEvaluatingRecordService.update(elderlyEvaluatingRecord, "createDate");
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
        ElderlyEvaluatingRecord elderlyEvaluatingRecord = elderlyEvaluatingRecordService.find(id);
        if (elderlyEvaluatingRecord != null) {
          elderlyEvaluatingRecordService.delete(ids);
        }
      }
    }
    return SUCCESS_MESSAGE;
  }
}
