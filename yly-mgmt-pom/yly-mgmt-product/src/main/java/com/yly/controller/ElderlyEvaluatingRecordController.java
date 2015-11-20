package com.yly.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yly.beans.Message;
import com.yly.controller.base.BaseController;
import com.yly.entity.ElderlyEvaluatingRecord;
import com.yly.entity.ElderlyInfo;
import com.yly.entity.ElderlyPhotoes;
import com.yly.entity.EvaluatingForm;
import com.yly.entity.EvaluatingItemOptions;
import com.yly.entity.EvaluatingItems;
import com.yly.entity.EvaluatingItemsAnswer;
import com.yly.entity.EvaluatingItemsOptions;
import com.yly.entity.EvaluatingSection;
import com.yly.entity.SystemConfig;
import com.yly.entity.commonenum.CommonEnum.DeleteStatus;
import com.yly.entity.commonenum.CommonEnum.ElderlyStatus;
import com.yly.entity.commonenum.CommonEnum.EvaluatingReason;
import com.yly.framework.paging.Page;
import com.yly.framework.paging.Pageable;
import com.yly.service.ElderlyEvaluatingRecordService;
import com.yly.service.ElderlyInfoService;
import com.yly.service.EvaluatingFormService;
import com.yly.service.EvaluatingItemOptionsService;
import com.yly.service.EvaluatingItemsAnswerService;
import com.yly.service.EvaluatingItemsOptionsService;
import com.yly.service.EvaluatingItemsService;
import com.yly.service.EvaluatingSectionService;
import com.yly.service.SystemConfigService;
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
  
  @Resource(name = "evaluatingFormServiceImpl")
  private EvaluatingFormService evaluatingFormService;
  
  @Resource(name = "systemConfigServiceImpl")
  private SystemConfigService systemConfigService;
  
  @Resource(name = "evaluatingItemsOptionsServiceImpl")
  private EvaluatingItemsOptionsService evaluatingItemsOptionsService;
  
  @Resource(name = "evaluatingItemsAnswerServiceImpl")
  private EvaluatingItemsAnswerService evaluatingItemsAnswerService;
  
  @Resource(name = "evaluatingItemsServiceImpl")
  private EvaluatingItemsService evaluatingItemsService;
  @Resource(name = "evaluatingItemOptionsServiceImpl")
  private EvaluatingItemOptionsService evaluatingItemOptionsService;
  
  /**
   * 列表页面
   * @param model
   * @return
   */
  @RequestMapping(value = "/elderlyEvaluatingRecord", method = RequestMethod.GET)
  public String elderlyEvaluatingRecord(ModelMap model) {
    return "/elderlyEvaluatingRecord/elderlyEvaluatingRecord";
  }
  /**
   * 创建评估表
   * @param model
   * @return
   */
  @RequestMapping(value = "/createEvaluatingFrom", method = RequestMethod.GET)
  public String createEvaluatingFrom(ModelMap model) {
//    List<EvaluatingSection> evaluatingSections = evaluatingSectionService.findAll();
//    model.addAttribute("evaluatingSections",evaluatingSections);
    return "/elderlyEvaluatingRecord/createEvaluatingFrom";
  }
  
  /**
   * 获取所有评估模块
   * @param model
   * @return
   */
  @RequestMapping(value = "/getAllSections", method = RequestMethod.GET)
  public @ResponseBody List<EvaluatingSection> getAllSections(ModelMap model){
    List<EvaluatingSection> evaluatingSections = evaluatingSectionService.findAll();
    if (evaluatingSections != null && evaluatingSections.size() > 0) {
      return evaluatingSections;
    }
    return null;
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
   * @param model
   * @param id
   * @return
   */
  @RequestMapping(value = "/edit", method = RequestMethod.GET)
  public String edit(ModelMap model, Long id) {
    EvaluatingForm evaluatingForm = evaluatingFormService.find(new Long(1));
    if (evaluatingForm != null) {
      model.addAttribute("evaluatingForm", evaluatingForm);
      //每个模块对应评分规则
      Map<String,String> sectionScoreRuleMap = elderlyEvaluatingRecordService.getSectionScoreRuleMap();
      model.addAttribute("sectionScoreRuleMap", sectionScoreRuleMap);
      model.addAttribute("formScoreRule",elderlyEvaluatingRecordService.getFormScoreRule());
   }
    ElderlyEvaluatingRecord elderlyEvaluatingRecord = elderlyEvaluatingRecordService.find(id);
    //每个模块对应总得分
    Map<String,Integer> sectionScoreMap = new HashMap<String, Integer>();
    //每个模块对应级别
    Map<String,Integer> sectionLevelMap = new HashMap<String, Integer>();
    if (StringUtils.isNotBlank(elderlyEvaluatingRecord.getSectionsResult())) {
      // 日常生活活动:总分,等级;精神状态:总分,等级;感知觉与沟通:总分,等级;社会参与:总分,等级; 
      String sectionsResult = elderlyEvaluatingRecord.getSectionsResult();
      String[] sectionNameScoreLevels = sectionsResult.split(";");
      for (int i = 0; i < sectionNameScoreLevels.length; i++) {
        String[] sectionNameScoreLevel = sectionNameScoreLevels[i].split(":");
        if (sectionNameScoreLevel.length == 2) {
             String[] sectionScoreLevel = sectionNameScoreLevel[1].split(",");
             sectionScoreMap.put(sectionNameScoreLevel[0], Integer.parseInt(sectionScoreLevel[0]));
             sectionLevelMap.put(sectionNameScoreLevel[0], Integer.parseInt(sectionScoreLevel[1]));
        }
      }
    }else {
      List<EvaluatingSection> evaluatingSections = evaluatingSectionService.findAll();
      if (evaluatingSections != null && evaluatingSections.size() > 0) {
        sectionScoreMap = elderlyEvaluatingRecordService.getSectionScoreMap(evaluatingSections, elderlyEvaluatingRecord);
        sectionLevelMap = elderlyEvaluatingRecordService.getSectionLevelMap(evaluatingSections, elderlyEvaluatingRecord, sectionScoreMap);
      }
    }
    model.addAttribute("sectionScoreMap", sectionScoreMap);
    model.addAttribute("sectionLevelMap", sectionLevelMap);
    model.addAttribute("elderlyEvaluatingRecord", elderlyEvaluatingRecord);
    model.addAttribute("elderlyInfo", elderlyEvaluatingRecord.getElderlyInfo());
    if (elderlyEvaluatingRecord.getEvaluatingResult() != null) {
      model.addAttribute("formPrimaryLevel", elderlyEvaluatingRecord.getEvaluatingResult());
    }else {
      model.addAttribute("formPrimaryLevel", elderlyEvaluatingRecordService.getFormPrimaryLevel(sectionLevelMap));
    }
    return "elderlyEvaluatingRecord/edit";
  }
  /**
   * 详情
   * @param model
   * @param id
   * @return
   */
  @RequestMapping(value = "/view", method = RequestMethod.GET)
  public String view(ModelMap model, Long id) {
    if (id != null) {
      ElderlyEvaluatingRecord elderlyEvaluatingRecord = elderlyEvaluatingRecordService.find(id);
      if (elderlyEvaluatingRecord != null) {
        //每个模块对应评分规则
        Map<String,String> sectionScoreRuleMap = elderlyEvaluatingRecordService.getSectionScoreRuleMap();
        //每个模块对应总得分
        Map<String,Integer> sectionScoreMap = new HashMap<String, Integer>();
        //每个模块对应级别
        Map<String,Integer> sectionLevelMap = new HashMap<String, Integer>();

        if (StringUtils.isNotBlank(elderlyEvaluatingRecord.getSectionsResult())) {
          // 日常生活活动:总分,等级;精神状态:总分,等级;感知觉与沟通:总分,等级;社会参与:总分,等级; 
          String sectionsResult = elderlyEvaluatingRecord.getSectionsResult();
          String[] sectionNameScoreLevels = sectionsResult.split(";");
          for (int i = 0; i < sectionNameScoreLevels.length; i++) {
            String[] sectionNameScoreLevel = sectionNameScoreLevels[i].split(":");
            if (sectionNameScoreLevel.length == 2) {
                 String[] sectionScoreLevel = sectionNameScoreLevel[1].split(",");
                 sectionScoreMap.put(sectionNameScoreLevel[0], Integer.parseInt(sectionScoreLevel[0]));
                 sectionLevelMap.put(sectionNameScoreLevel[0], Integer.parseInt(sectionScoreLevel[1]));
            }
          }
        }else {
          List<EvaluatingSection> evaluatingSections = evaluatingSectionService.findAll();
          if (evaluatingSections != null && evaluatingSections.size() > 0) {
            sectionScoreMap = elderlyEvaluatingRecordService.getSectionScoreMap(evaluatingSections, elderlyEvaluatingRecord);
            sectionLevelMap = elderlyEvaluatingRecordService.getSectionLevelMap(evaluatingSections, elderlyEvaluatingRecord, sectionScoreMap);
          }
        }
        model.addAttribute("sectionScoreRuleMap", sectionScoreRuleMap);
        model.addAttribute("sectionScoreMap", sectionScoreMap);
        model.addAttribute("sectionLevelMap", sectionLevelMap);
        model.addAttribute("formScoreRule",elderlyEvaluatingRecordService.getFormScoreRule());
        if (elderlyEvaluatingRecord.getEvaluatingResult() != null) {
          model.addAttribute("formPrimaryLevel", elderlyEvaluatingRecord.getEvaluatingResult());
        }else {
          model.addAttribute("formPrimaryLevel", elderlyEvaluatingRecordService.getFormPrimaryLevel(sectionLevelMap));
        }
        
        model.addAttribute("elderlyEvaluatingRecord", elderlyEvaluatingRecord);
      }
      return "elderlyEvaluatingRecord/view";
    }
    return "";
  }
  /**
   * 添加评估题目
   * @param evaluatingItems
   * @return
   */
  @RequestMapping(value = "/addItem", method = RequestMethod.POST)
  public @ResponseBody Message addItem(EvaluatingItems evaluatingItems, Long evaluatingSectionId) {
    if (evaluatingSectionId == null) {
      return ERROR_MESSAGE;
    }
    EvaluatingSection evaluatingSection = evaluatingSectionService.find(evaluatingSectionId);
    Long currnetTenantId = tenantAccountService.getCurrentTenantID();//获取租户ID
    if (evaluatingSection == null || currnetTenantId == null) {
      return ERROR_MESSAGE;
    }
    if (evaluatingItems != null && evaluatingItems.getItemName() != null) {
      evaluatingItems.setTenantID(currnetTenantId);
      evaluatingItems.setAllowMutipleAnswers(false);
      evaluatingItems.setAnswerRequired(true);
      evaluatingItems.setEvaluatingSection(evaluatingSection);
      evaluatingItemsService.save(evaluatingItems);//保存题目
      
      if (evaluatingItems.getEvaluatingItemsOptions() != null && evaluatingItems.getEvaluatingItemsOptions().size() > 0) {
        List<EvaluatingItemsOptions> evaluatingItemsOptionsList = evaluatingItems.getEvaluatingItemsOptions();
        for (int i = 0; i < evaluatingItemsOptionsList.size(); i ++) {
          EvaluatingItemsOptions evaluatingItemsOptions = evaluatingItemsOptionsList.get(i);
          EvaluatingItemOptions evaluatingItemOptions = evaluatingItemsOptions.getEvaluatingItemOptions();
          evaluatingItemOptions.setTenantID(currnetTenantId);
          evaluatingItemOptionsService.save(evaluatingItemOptions);//依次保存选项
          
          evaluatingItemsOptions.setEvaluatingItemOptions(evaluatingItemOptions);
          evaluatingItemsOptions.setEvaluatingItems(evaluatingItems);
          evaluatingItemsOptionsService.save(evaluatingItemsOptions);//依次保存选项关系（含得分）
        }
      }
    }
    
    return SUCCESS_MESSAGE;
  }
  /**
   * 添加评估模块
   * @param evaluatingItems
   * @return
   */
  @RequestMapping(value = "/addSection", method = RequestMethod.POST)
  public @ResponseBody Message addSection(EvaluatingSection evaluatingSection) {
    Long currnetTenantId = tenantAccountService.getCurrentTenantID();//获取租户ID
    if (evaluatingSection != null && evaluatingSection.getSectionName() != null) {
      evaluatingSection.setTenantID(currnetTenantId);
      evaluatingSection.setSystemSection(false);
      evaluatingSectionService.save(evaluatingSection);
    }
    
    return SUCCESS_MESSAGE;
  }
  
  /**
   * 添加入院评估 （数据准备）
   * 
   * @param model
   * @param id
   * @return
   */
  @RequestMapping(value = "/addEvaluating", method = RequestMethod.GET)
  public String addEvaluating(ModelMap model) {
     EvaluatingForm evaluatingForm = evaluatingFormService.find(new Long(1));
     if (evaluatingForm != null) {
       model.addAttribute("evaluatingForm", evaluatingForm);
       //每个模块对应评分规则
       Map<String,String> sectionScoreRuleMap = elderlyEvaluatingRecordService.getSectionScoreRuleMap();
       model.addAttribute("sectionScoreRuleMap", sectionScoreRuleMap);
       model.addAttribute("formScoreRule",elderlyEvaluatingRecordService.getFormScoreRule());
    }
      return "elderlyEvaluatingRecord/addEvaluating";
  }
  
  /**
   * 保存入院评估
   * 
   * @param elderlyEvaluatingRecord
   * @return
   */
  @RequestMapping(value = "/save", method = RequestMethod.POST)
  //@Transactional(readOnly = true)
  public @ResponseBody Message save(Long personnelCategoryId, Long nursingLevelId,
      Long evaluatingResultId,Long evaluatintFormID, ElderlyEvaluatingRecord elderlyEvaluatingRecord, ElderlyInfo elderlyInfo) {
    
      Long currnetTenantId = tenantAccountService.getCurrentTenantID();//获取租户ID
      /**
       * 保存老人信息
       */
      elderlyInfo.setTenantID(currnetTenantId);//设置租户ID
      elderlyInfo.setPersonnelCategory(personnelCategoryId != null?systemConfigService.find(personnelCategoryId):null);//设置人员类别
      elderlyInfo.setNursingLevel(nursingLevelId != null?systemConfigService.find(nursingLevelId):null);//设置护理级别
      elderlyInfo.setEvaluatingResult(evaluatingResultId != null?systemConfigService.find(evaluatingResultId):null);//设置评估结果
      elderlyInfo.setDeleteStatus(DeleteStatus.NOT_DELETED);//表示没有删除
      elderlyInfo.getElderlyConsigner().setElderlyInfo(elderlyInfo);//设置委托人
      elderlyInfo.getElderlyConsigner().setTenantID(currnetTenantId);//设置委托人的租户ID
      elderlyInfo.setElderlyStatus(ElderlyStatus.IN_PROGRESS_EVALUATING);//设置老人状态 ：入院评估
      elderlyInfoService.save(elderlyInfo);
      /**
       * 保存入院评估记录
       */      
      elderlyEvaluatingRecord.setTenantID(currnetTenantId);//设置租户ID
      elderlyEvaluatingRecord.setElderlyInfo(elderlyInfo);//设置
      elderlyEvaluatingRecord.setEvaluatingForm(evaluatingFormService.find(evaluatintFormID));//设置评估记录用的评估表
      //elderlyEvaluatingRecord.setOperator(tenantAccountService.getCurrentUsername());//设置操作人员
      elderlyEvaluatingRecord.setStaffID("");//设置操作人员员工号
      elderlyEvaluatingRecordService.save(elderlyEvaluatingRecord);
      /**
       * 依次保存每个项的评估选择结果
       */
      List<EvaluatingItemsAnswer> evaluatingItemsAnswers = elderlyEvaluatingRecord.getEvaluatingItemsAnswers();    
      for (EvaluatingItemsAnswer evaluatingItemsAnswer : evaluatingItemsAnswers) {    
        Long evaluatingItemsOptionsId = evaluatingItemsAnswer.getEvaluatingItemsOptions().getId();//从页面获取用户的选项Id
        if (evaluatingItemsOptionsId != null) {
          EvaluatingItemsOptions evaluatingItemsOptions = evaluatingItemsOptionsService.find(evaluatingItemsOptionsId);
          evaluatingItemsAnswer.setEvaluatingItemsOptions(evaluatingItemsOptions);//设置评估选择结果
        }
        evaluatingItemsAnswer.setTenantID(currnetTenantId);//设置租户ID
        evaluatingItemsAnswer.setElderlyEvaluatingRecord(elderlyEvaluatingRecord);
        
        evaluatingItemsAnswerService.save(evaluatingItemsAnswer);
      } 
      /**
       * 更新入院评估记录的评估结果
       */
    //应该根据form表的外键找？？？？？？？？？？？？？？不应该全部找
      List<EvaluatingSection> evaluatingSections = evaluatingSectionService.findAll();
      //每个模块对应总得分
      Map<String,Integer> sectionScoreMap = elderlyEvaluatingRecordService.getSectionScoreMap(evaluatingSections, elderlyEvaluatingRecord);
      //每个模块对应级别
      Map<String,Integer> sectionLevelMap = elderlyEvaluatingRecordService.getSectionLevelMap(evaluatingSections, elderlyEvaluatingRecord, sectionScoreMap);
      //日常生活活动:总分,等级;精神状态:总分,等级;感知觉与沟通:总分,等级;社会参与:总分,等级;    
      StringBuffer sectionResultSB = new StringBuffer();
      for (int i = 0; i < evaluatingSections.size(); i++) {
        String sectionName = evaluatingSections.get(i).getSectionName();
        sectionResultSB.append(sectionName);
        sectionResultSB.append(":");
        sectionResultSB.append(sectionScoreMap.get(sectionName));
        sectionResultSB.append(",");
        sectionResultSB.append(sectionLevelMap.get(sectionName));
        sectionResultSB.append(";");
      }
      //设置模块总分和等级(字符串)
      elderlyEvaluatingRecord.setSectionsResult(sectionResultSB.toString());
      //设置评估表最终结果等级
      elderlyEvaluatingRecord.setEvaluatingResult(elderlyEvaluatingRecordService.getFormPrimaryLevel(sectionLevelMap));  
      elderlyEvaluatingRecordService.update(elderlyEvaluatingRecord);
      
      return SUCCESS_MESSAGE;
  }

  /**
   * 更新
   * 
   * @param elderlyEvaluatingRecord
   * @param elderlyInfoID
   * @return
   */
  @RequestMapping(value = "/update", method = RequestMethod.POST)
  //@Transactional(readOnly = true)
  public @ResponseBody Message update(Long personnelCategoryId, Long nursingLevelId,
      Long evaluatingResultId, Long evaluatintFormID, ElderlyEvaluatingRecord elderlyEvaluatingRecord, ElderlyInfo elderlyInfo,
      Long elderlyInfoId, Long elderlyEvaluatingRecordId) {
    Long currnetTenantId = tenantAccountService.getCurrentTenantID();//获取租户ID
    /**
     * 更新老人信息
     */
    elderlyInfo.setId(elderlyInfoId);
    //elderlyInfo.setTenantID(currnetTenantId);//设置租户ID
    elderlyInfo.setPersonnelCategory(personnelCategoryId != null?systemConfigService.find(personnelCategoryId):null);//设置人员类别
    elderlyInfo.setNursingLevel(nursingLevelId != null?systemConfigService.find(nursingLevelId):null);//设置护理级别
    elderlyInfo.setEvaluatingResult(evaluatingResultId != null?systemConfigService.find(evaluatingResultId):null);//设置评估结果
    elderlyInfo.setDeleteStatus(DeleteStatus.NOT_DELETED);//表示没有删除
    elderlyInfo.getElderlyConsigner().setElderlyInfo(elderlyInfo);//设置委托人
    elderlyInfo.getElderlyConsigner().setTenantID(currnetTenantId);//设置委托人的租户ID
    elderlyInfo.setElderlyStatus(ElderlyStatus.IN_PROGRESS_EVALUATING);//设置老人状态 ：入院评估
    elderlyInfoService.update(elderlyInfo,"tenantID","createDate");
    /**
     * 更新评估记录
     */
    elderlyEvaluatingRecord.setId(elderlyEvaluatingRecordId);
    //elderlyEvaluatingRecord.setTenantID(currnetTenantId);//设置租户ID
    elderlyEvaluatingRecord.setElderlyInfo(elderlyInfo);//设置
    elderlyEvaluatingRecord.setEvaluatingForm(evaluatingFormService.find(evaluatintFormID));//设置评估记录用的评估表
    //elderlyEvaluatingRecord.setOperator(tenantAccountService.getCurrentUsername());//设置操作人员
    elderlyEvaluatingRecord.setStaffID("");//设置操作人员员工号
    elderlyEvaluatingRecordService.update(elderlyEvaluatingRecord,"tenantID","createDate");
    /**
     * 依次更新每个项的评估选择结果
     */
    List<EvaluatingItemsAnswer> evaluatingItemsAnswers = elderlyEvaluatingRecord.getEvaluatingItemsAnswers();    
    for (EvaluatingItemsAnswer evaluatingItemsAnswer : evaluatingItemsAnswers) {    
      Long evaluatingItemsOptionsId = evaluatingItemsAnswer.getEvaluatingItemsOptions().getId();//从页面获取用户的选项Id
      if (evaluatingItemsOptionsId != null) {
        EvaluatingItemsOptions evaluatingItemsOptions = evaluatingItemsOptionsService.find(evaluatingItemsOptionsId);
        evaluatingItemsAnswer.setEvaluatingItemsOptions(evaluatingItemsOptions);//设置评估选择结果
      }
      evaluatingItemsAnswer.setTenantID(currnetTenantId);//设置租户ID
      evaluatingItemsAnswer.setElderlyEvaluatingRecord(elderlyEvaluatingRecord);
      
      evaluatingItemsAnswerService.update(evaluatingItemsAnswer);
    } 
    /**
     * 更新入院评估记录的评估结果
     */
  //应该根据form表的外键找？？？？？？？？？？？？？？不应该全部找
    List<EvaluatingSection> evaluatingSections = evaluatingSectionService.findAll();
    //每个模块对应总得分
    Map<String,Integer> sectionScoreMap = elderlyEvaluatingRecordService.getSectionScoreMap(evaluatingSections, elderlyEvaluatingRecord);
    //每个模块对应级别
    Map<String,Integer> sectionLevelMap = elderlyEvaluatingRecordService.getSectionLevelMap(evaluatingSections, elderlyEvaluatingRecord, sectionScoreMap);
    //日常生活活动:总分,等级;精神状态:总分,等级;感知觉与沟通:总分,等级;社会参与:总分,等级;    
    StringBuffer sectionResultSB = new StringBuffer();
    for (int i = 0; i < evaluatingSections.size(); i++) {
      String sectionName = evaluatingSections.get(i).getSectionName();
      sectionResultSB.append(sectionName);
      sectionResultSB.append(":");
      sectionResultSB.append(sectionScoreMap.get(sectionName));
      sectionResultSB.append(",");
      sectionResultSB.append(sectionLevelMap.get(sectionName));
      sectionResultSB.append(";");
    }
    //设置模块总分和等级(字符串)
    elderlyEvaluatingRecord.setSectionsResult(sectionResultSB.toString());
    //设置评估表最终结果等级
    elderlyEvaluatingRecord.setEvaluatingResult(elderlyEvaluatingRecordService.getFormPrimaryLevel(sectionLevelMap));  
    elderlyEvaluatingRecordService.update(elderlyEvaluatingRecord,"tenantID","createDate");
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
      for (Long id : ids) {
        ElderlyEvaluatingRecord elderlyEvaluatingRecord = elderlyEvaluatingRecordService.find(id);
        if (elderlyEvaluatingRecord != null) {
          List<EvaluatingItemsAnswer> evaluatingItemsAnswerList = elderlyEvaluatingRecord.getEvaluatingItemsAnswers();
          if (evaluatingItemsAnswerList != null && evaluatingItemsAnswerList.size() > 0) {
            //依次删除评估选择结果
            for (EvaluatingItemsAnswer evaluatingItemsAnswer : evaluatingItemsAnswerList) {
              evaluatingItemsAnswerService.remove(evaluatingItemsAnswer);
            }
          }
        }
            //在删除整个评估记录
          elderlyEvaluatingRecordService.delete(id);
        }
    }
    return SUCCESS_MESSAGE;
  }
  /**
   * 计算并返回单个模块的等级
   * @return
   */
  @RequestMapping(value = "/getSectionLevel", method = RequestMethod.GET)
  public @ResponseBody Map<String, Integer> getSectionLevel(ModelMap model, String sectionName, String answerScores) {
    int level=-1;
    level = elderlyEvaluatingRecordService.getSectionLevel(sectionName,answerScores);
    Map<String, Integer> levelMap = new HashMap<String, Integer>();   
    levelMap.put("level", level);
    return levelMap;
  }
  /**
   * 计算并返回单个评估表的等级
   * @return
   */
  @RequestMapping(value = "/getFormLevel", method = RequestMethod.GET)
  public @ResponseBody Map<String, String> getFormLevel(ModelMap model, String sectionLevels) {
    String level= "";
    level = elderlyEvaluatingRecordService.getFormLevel(sectionLevels);
    Map<String, String> levelMap = new HashMap<String, String>();   
    levelMap.put("level", level);
    return levelMap;
  }
  
}
