package com.yly.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.bouncycastle.crypto.agreement.srp.SRP6Client;
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
import com.yly.entity.ElderlyPhotoAlbum;
import com.yly.entity.ElderlyPhotoes;
import com.yly.entity.EvaluatingForm;
import com.yly.entity.EvaluatingItemOptions;
import com.yly.entity.EvaluatingItems;
import com.yly.entity.EvaluatingItemsAnswer;
import com.yly.entity.EvaluatingItemsOptions;
import com.yly.entity.EvaluatingSection;
import com.yly.entity.SystemConfig;
import com.yly.entity.commonenum.CommonEnum;
import com.yly.entity.commonenum.CommonEnum.ConfigKey;
import com.yly.entity.commonenum.CommonEnum.DeleteStatus;
import com.yly.entity.commonenum.CommonEnum.ElderlyStatus;
import com.yly.entity.commonenum.CommonEnum.EvaluatingFormStatus;
import com.yly.entity.commonenum.CommonEnum.EvaluatingReason;
import com.yly.framework.paging.Page;
import com.yly.framework.paging.Pageable;
import com.yly.json.request.EvaluatingItemScoreRequest;
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
import com.yly.utils.ToolsUtils;

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
    model.addAttribute("systemConfigs", systemConfigService.findByConfigKey(ConfigKey.EVALUATINGLEVEL, null));
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
   * 获取所有用户自定义评估模块,且可编辑模块（未被现有的评估表使用）
   * @param model
   * @return
   */
  @RequestMapping(value = "/getAllCustomSections", method = RequestMethod.GET)
  public @ResponseBody List<EvaluatingSection> getAllCustomSections(ModelMap model){
    List<EvaluatingSection> evaluatingSectionList = evaluatingSectionService.findAll();
    int count = 1;
    
    while (count <= evaluatingSectionList.size()) {
      EvaluatingSection evaluatingSection = evaluatingSectionList.get(count - 1);
      if (evaluatingSection.getSystemSection() || evaluatingSection.getEvaluatingForm() != null){//移除系统定义的评估模块
          evaluatingSectionList.remove(count - 1);    // 从List中移除         
     }else{
           count ++;
     }  
    }
    if (evaluatingSectionList != null && evaluatingSectionList.size() > 0) {
      return evaluatingSectionList;
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
  /**
   * 评估表list
   * @return
   */
  @RequestMapping(value = "/listForm", method = RequestMethod.GET)
  public String listForm(ModelMap model) {
    List<EvaluatingForm> evaluatingFormlist = evaluatingFormService.findAll();
    if (evaluatingFormlist != null && evaluatingFormlist.size() > 0) {
      model.addAttribute("evaluatingFormlist", evaluatingFormlist);
      return "/elderlyEvaluatingRecord/listForm";
    } 
    return "";
  }  
  

  @RequestMapping(value = "/findAll", method = RequestMethod.POST)
  public @ResponseBody List<Map<String, Object>> findAll() {
    String[] propertys = {"id", "buildingName"};
    return FieldFilterUtils.filterCollectionMap(propertys, elderlyEvaluatingRecordService.findAll(true));
  }
  /**
   * 编辑模块
   * @param model
   * @param id
   * @return
   */
  @RequestMapping(value = "/editSection", method = RequestMethod.GET)
  public String editSection(ModelMap model, Long evaluatingSectionId) {
    if (evaluatingSectionId != null) {
      EvaluatingSection evaluatingSection = evaluatingSectionService.find(evaluatingSectionId);
      if (evaluatingSection != null) {
        model.addAttribute("evaluatingSection", evaluatingSection);
      }
    }    
    return "elderlyEvaluatingRecord/editSection";
  }
  
  /**
   * 编辑
   * @param model
   * @param id
   * @return
   */
  @RequestMapping(value = "/edit", method = RequestMethod.GET)
  public String edit(ModelMap model, Long id) {
    ElderlyEvaluatingRecord elderlyEvaluatingRecord = elderlyEvaluatingRecordService.find(id);
    boolean isSystemForm = false;//标记是否系统默认的评估表
    String returnPage = "";
    if (elderlyEvaluatingRecord.getEvaluatingForm().getEvaluatingSections().get(0).getSystemSection()) {
      isSystemForm = true;
      returnPage = "elderlyEvaluatingRecord/edit";
    }else{
      isSystemForm = false;
      returnPage = "elderlyEvaluatingRecord/customEdit";
    }
    //EvaluatingForm evaluatingForm = evaluatingFormService.find(new Long(1));
    EvaluatingForm evaluatingForm = elderlyEvaluatingRecord.getEvaluatingForm();
    if (evaluatingForm != null) {
      model.addAttribute("evaluatingForm", evaluatingForm);
      if (isSystemForm) {
        //每个模块对应评分规则
        Map<String,String> sectionScoreRuleMap = elderlyEvaluatingRecordService.getSectionScoreRuleMap();
        model.addAttribute("sectionScoreRuleMap", sectionScoreRuleMap);
        model.addAttribute("formScoreRule",elderlyEvaluatingRecordService.getFormScoreRule());
      }else {
        model.addAttribute("formScoreRule",elderlyEvaluatingRecordService.getCustomFormScoreRule(elderlyEvaluatingRecord.getEvaluatingForm().getEvaluatingRule()));
      }
   }
    
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
            if (sectionNameScoreLevel[1].contains(",")) {
              String[] sectionScoreLevel = sectionNameScoreLevel[1].split(",");
              sectionScoreMap.put(sectionNameScoreLevel[0], Integer.parseInt(sectionScoreLevel[0]));
              sectionLevelMap.put(sectionNameScoreLevel[0], Integer.parseInt(sectionScoreLevel[1]));
            }else{//自定义评估试卷，没有模块等级，只有模块总分
              sectionScoreMap.put(sectionNameScoreLevel[0], Integer.parseInt(sectionNameScoreLevel[1]));
            }
        }
      }
    }
//    else {
//      //List<EvaluatingSection> evaluatingSections = evaluatingSectionService.findAll();
//      List<EvaluatingSection> evaluatingSections = evaluatingForm.getEvaluatingSections();
//      if (evaluatingSections != null && evaluatingSections.size() > 0) {
//        sectionScoreMap = elderlyEvaluatingRecordService.getSectionScoreMap(evaluatingSections, elderlyEvaluatingRecord);
//        sectionLevelMap = elderlyEvaluatingRecordService.getSectionLevelMap(evaluatingSections, elderlyEvaluatingRecord, sectionScoreMap);
//      }
//    }
    model.addAttribute("sectionScoreMap", sectionScoreMap);
    model.addAttribute("sectionLevelMap", sectionLevelMap);
    model.addAttribute("elderlyEvaluatingRecord", elderlyEvaluatingRecord);
    model.addAttribute("elderlyInfo", elderlyEvaluatingRecord.getElderlyInfo());
    if (elderlyEvaluatingRecord.getEvaluatingResult() != null) {
      model.addAttribute("formPrimaryLevel", elderlyEvaluatingRecord.getEvaluatingResult());
    }else if(isSystemForm){
      model.addAttribute("formPrimaryLevel", elderlyEvaluatingRecordService.getFormPrimaryLevel(sectionLevelMap));
    }
    return returnPage;
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
      boolean isSystemForm = false;//标记是否系统默认的评估表
      String returnPage = "";
      if (elderlyEvaluatingRecord.getEvaluatingForm().getEvaluatingSections().get(0).getSystemSection()) {
        isSystemForm = true;
        returnPage = "elderlyEvaluatingRecord/view";
      }else{
        isSystemForm = false;
        returnPage = "elderlyEvaluatingRecord/customView";
      }
      if (elderlyEvaluatingRecord != null) {

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
                 if (sectionNameScoreLevel[1].contains(",") && isSystemForm) {
                   String[] sectionScoreLevel = sectionNameScoreLevel[1].split(",");
                   sectionScoreMap.put(sectionNameScoreLevel[0], Integer.parseInt(sectionScoreLevel[0]));
                   sectionLevelMap.put(sectionNameScoreLevel[0], Integer.parseInt(sectionScoreLevel[1]));
                }else{//用户自定义评估表的每个模块没有评分规则
                  sectionScoreMap.put(sectionNameScoreLevel[0], Integer.parseInt(sectionNameScoreLevel[1]));
                }

            }
          }
        }
//        else {
//          //List<EvaluatingSection> evaluatingSections = evaluatingSectionService.findAll();
//          List<EvaluatingSection> evaluatingSections = elderlyEvaluatingRecord.getEvaluatingForm().getEvaluatingSections();
//          if (evaluatingSections != null && evaluatingSections.size() > 0) {
//            sectionScoreMap = elderlyEvaluatingRecordService.getSectionScoreMap(evaluatingSections, elderlyEvaluatingRecord);
//            sectionLevelMap = elderlyEvaluatingRecordService.getSectionLevelMap(evaluatingSections, elderlyEvaluatingRecord, sectionScoreMap);
//          }
//        }
        if (isSystemForm) {//针对系统默认评估表，每个模块有评分规则和等级
          //每个模块对应评分规则
          Map<String,String> sectionScoreRuleMap = elderlyEvaluatingRecordService.getSectionScoreRuleMap();
          model.addAttribute("sectionScoreRuleMap", sectionScoreRuleMap);
          model.addAttribute("sectionLevelMap", sectionLevelMap);
        }
        
        model.addAttribute("sectionScoreMap", sectionScoreMap);
        if (isSystemForm) {
            model.addAttribute("formScoreRule",elderlyEvaluatingRecordService.getFormScoreRule());
        }else{
             model.addAttribute("formScoreRule",elderlyEvaluatingRecordService.getCustomFormScoreRule(elderlyEvaluatingRecord.getEvaluatingForm().getEvaluatingRule()));
        }
        
        if (elderlyEvaluatingRecord.getEvaluatingResult() != null) {
          model.addAttribute("formPrimaryLevel", elderlyEvaluatingRecord.getEvaluatingResult());
        }else if(isSystemForm){
          model.addAttribute("formPrimaryLevel", elderlyEvaluatingRecordService.getFormPrimaryLevel(sectionLevelMap));
        }
        
        model.addAttribute("elderlyEvaluatingRecord", elderlyEvaluatingRecord);
      }
      return returnPage;
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
    boolean isExistingItem = false;//标记是否已经存在的模块
    EvaluatingItems evaluatingItemsDB = null;
    if (evaluatingItems != null && evaluatingItems.getItemName() != null) {
      if (evaluatingItems.getId() != null) {//
        evaluatingItemsDB =  evaluatingItemsService.find(evaluatingItems.getId());  
        if (evaluatingItemsDB.getItemName().equals(evaluatingItems.getItemName())) {
          isExistingItem = true;
        }
      }
    }
      if (isExistingItem) {//已经存在的题目
        List<EvaluatingItems> evaluatingItemsList = evaluatingSection.getEvaluatingItems();
        boolean existing = false;
        if (evaluatingItemsList == null) {
          evaluatingItemsList = new ArrayList<EvaluatingItems>();
        }else{
          for (EvaluatingItems evaluatingItems2 : evaluatingItemsList) {
            if (evaluatingItems2.getId() == evaluatingItems.getId()) {
              existing = true;
              break;
            }
          }
        }
        if (!existing && evaluatingItemsDB != null) {//如果当前模块没有包含模块evaluatingItemsDB
          if (evaluatingItems.getEvaluatingItemsOptions() != null && evaluatingItems.getEvaluatingItemsOptions().size() > 0) {
            List<EvaluatingItemsOptions> evaluatingItemsOptionsList = evaluatingItems.getEvaluatingItemsOptions();
            for (int i = 0; i < evaluatingItemsOptionsList.size(); i ++) {
              EvaluatingItemsOptions evaluatingItemsOptions = evaluatingItemsOptionsList.get(i);
              EvaluatingItemOptions evaluatingItemOptions = evaluatingItemsOptions.getEvaluatingItemOptions();
              if (evaluatingItemOptions.getId() != null) {//已经存在的选项
                EvaluatingItemOptions evaluatingItemOptionsDB = evaluatingItemOptionsService.find(evaluatingItemOptions.getId());
                if (!evaluatingItemOptionsDB.getOptionName().equals(evaluatingItemOptions.getOptionName())) {//已经存在的选项，但是修改过，作为新建的选项处理，另外保存
                  evaluatingItemOptions.setTenantID(currnetTenantId);
                  evaluatingItemOptionsService.save(evaluatingItemOptions);//依次保存选项
                  evaluatingItemsOptions.setEvaluatingItemOptions(evaluatingItemOptions);
                  evaluatingItemsOptionsService.save(evaluatingItemsOptions);//依次保存选项关系（含得分）
                }
              }else{//新添加的选项
                evaluatingItemOptions.setTenantID(currnetTenantId);
                evaluatingItemOptionsService.save(evaluatingItemOptions);//依次保存选项
                evaluatingItemsOptions.setEvaluatingItemOptions(evaluatingItemOptions);
                evaluatingItemsOptionsService.save(evaluatingItemsOptions);//依次保存选项关系（含得分）
              }              
            }
          }
          evaluatingItemsList.add(evaluatingItemsDB);
          evaluatingSectionService.update(evaluatingSection);//更新模块下面的题目
        }else {
          return ERROR_MESSAGE;
        }
      }else{//新添加的题目
        evaluatingItems.setId(null);
        evaluatingItems.setTenantID(currnetTenantId);
        evaluatingItems.setAllowMutipleAnswers(false);
        evaluatingItems.setAnswerRequired(true);
        List<EvaluatingSection> evaluatingSections = new ArrayList<EvaluatingSection>();
        evaluatingSections.add(evaluatingSection);
        evaluatingItems.setEvaluatingSections(evaluatingSections);
        evaluatingItemsService.save(evaluatingItems);//保存题目
        
        if (evaluatingItems.getEvaluatingItemsOptions() != null && evaluatingItems.getEvaluatingItemsOptions().size() > 0) {
          List<EvaluatingItemsOptions> evaluatingItemsOptionsList = evaluatingItems.getEvaluatingItemsOptions();
          for (int i = 0; i < evaluatingItemsOptionsList.size(); i ++) {
            EvaluatingItemsOptions evaluatingItemsOptions = evaluatingItemsOptionsList.get(i);
            EvaluatingItemOptions evaluatingItemOptions = evaluatingItemsOptions.getEvaluatingItemOptions();
            if (evaluatingItemOptions.getId() != null) {//已经存在的选项
              EvaluatingItemOptions evaluatingItemOptionsDB = evaluatingItemOptionsService.find(evaluatingItemOptions.getId());
              if (evaluatingItemOptionsDB.getOptionName().equals(evaluatingItemOptions.getOptionName())) {//已经存在的选项，并且没有修改过
                evaluatingItemsOptions.setEvaluatingItemOptions(evaluatingItemOptionsDB);
                
              }else {//已经存在的选项，但是修改过，作为新建的选项处理，另外保存
                evaluatingItemOptions.setTenantID(currnetTenantId);
                evaluatingItemOptions.setId(null);
                evaluatingItemOptionsService.save(evaluatingItemOptions);//依次保存选项
                evaluatingItemsOptions.setEvaluatingItemOptions(evaluatingItemOptions);
              }
              
            }else{//新添加的选项
              evaluatingItemOptions.setTenantID(currnetTenantId);
              evaluatingItemOptionsService.save(evaluatingItemOptions);//依次保存选项
              evaluatingItemsOptions.setEvaluatingItemOptions(evaluatingItemOptions);
            }     
            evaluatingItemsOptions.setEvaluatingItems(evaluatingItems);
            evaluatingItemsOptionsService.save(evaluatingItemsOptions);//依次保存选项关系（含得分）
          }
        }
        List<EvaluatingItems> evaluatingItemsList = evaluatingSection.getEvaluatingItems();
        if (evaluatingItemsList == null) {
          evaluatingItemsList = new ArrayList<EvaluatingItems>();
        }
        evaluatingItemsList.add(evaluatingItems);
        evaluatingSectionService.update(evaluatingSection);//更新模块下面的题目
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
//      String evaluatingRule = elderlyEvaluatingRecordService.getEvaluatingRule(evaluatingScoreRequest);
//      if (StringUtils.isNotBlank(evaluatingRule)) {
//        evaluatingSection.setEvaluatingRule(evaluatingRule.toString());
//      }
      evaluatingSectionService.save(evaluatingSection);
    }
    
    return SUCCESS_MESSAGE;
  }
  
  /**
   * 添加默认系统定义入院评估 （数据准备）
   * 
   * @param model
   * @param id
   * @return
   */
  @RequestMapping(value = "/addEvaluating", method = RequestMethod.GET)
  public String addEvaluating(ModelMap model, Long formId) {
     EvaluatingForm evaluatingForm = evaluatingFormService.find(formId);
//     List<EvaluatingSection>  evaluatingSections = evaluatingForm.getEvaluatingSections();
//     if (evaluatingSections != null) {
//      for (EvaluatingSection evaluatingSection : evaluatingSections) {
//        List<EvaluatingItems>  evaluatingItems = evaluatingSection.getEvaluatingItems();
//        if (evaluatingItems != null) {
//          for (EvaluatingItems evaluatingItems2 : evaluatingItems) {
//            String itmeName = evaluatingItems2.getItemName();
//          }
//        }
//      }
//    }
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
   * 添加用户自定义入院评估 （数据准备）
   * 
   * @param model
   * @param id
   * @return
   */
  @RequestMapping(value = "/addCustomEvaluating", method = RequestMethod.GET)
  public String addCustomEvaluating(ModelMap model, Long formId) {
     EvaluatingForm evaluatingForm = evaluatingFormService.find(formId);

     if (evaluatingForm != null) {
       model.addAttribute("evaluatingForm", evaluatingForm);
       //每个模块对应评分规则
       //Map<String,String> sectionScoreRuleMap = elderlyEvaluatingRecordService.getSectionScoreRuleMap();
       //model.addAttribute("sectionScoreRuleMap", sectionScoreRuleMap);
       model.addAttribute("formScoreRule",elderlyEvaluatingRecordService.getCustomFormScoreRule(evaluatingForm.getEvaluatingRule()));
    }
      return "elderlyEvaluatingRecord/addCustomEvaluating";
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
      Long evaluatingResultId ,boolean customFormFlag, Long evaluatintFormID, String formLevel, String sectionsResult,
      ElderlyEvaluatingRecord elderlyEvaluatingRecord, ElderlyInfo elderlyInfo) {
    
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
      if (elderlyInfo.getId() != null) {
        elderlyInfoService.update(elderlyInfo,"tenantID","createDate");
      }else{
        elderlyInfoService.save(elderlyInfo);
      }
      
      /**
       * 保存入院评估记录
       */      
      elderlyEvaluatingRecord.setTenantID(currnetTenantId);//设置租户ID
      elderlyEvaluatingRecord.setElderlyInfo(elderlyInfoService.find(elderlyInfo.getId()));//设置
      EvaluatingForm evaluatingForm = evaluatingFormService.find(evaluatintFormID);
      elderlyEvaluatingRecord.setEvaluatingForm(evaluatingForm);//设置评估记录用的评估表
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
      if (customFormFlag) {//处理使用自定义评估表的情况
        if (StringUtils.isNotBlank(formLevel) && StringUtils.isNotBlank(sectionsResult)) {
          sectionsResult = sectionsResult.replace(";;;;", ";").replace("::::", ":");
          //设置模块总分和等级(字符串)
          elderlyEvaluatingRecord.setSectionsResult(sectionsResult);
          //设置评估表最终结果等级        
          elderlyEvaluatingRecord.setEvaluatingResult(formLevel);
          elderlyEvaluatingRecordService.update(elderlyEvaluatingRecord);
        }        
      }else {//处理使用系统默认的评估表的情况
      //获取该评估表下的所有评估模块
        List<EvaluatingSection> evaluatingSections = evaluatingForm.getEvaluatingSections();
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
      }
      
      
      return SUCCESS_MESSAGE;
  }
  
  /**
   * 更新模块
   * 
   * @param elderlyEvaluatingRecord
   * @param elderlyInfoID
   * @return
   */
  @RequestMapping(value = "/updateSection", method = RequestMethod.POST)
  //@Transactional(readOnly = true)
  public @ResponseBody Message updateSection(EvaluatingSection evaluatingSection){
    if (evaluatingSection == null || evaluatingSection.getId() == null) {
      return ERROR_MESSAGE;
    }else {    
      EvaluatingSection evaluatingSectionDB = evaluatingSectionService.find(evaluatingSection.getId());
      evaluatingSectionDB.setSectionName(evaluatingSection.getSectionName());
      evaluatingSectionDB.setSectionDescription(evaluatingSection.getSectionDescription());
      evaluatingSectionService.update(evaluatingSectionDB);
    }        
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
      Long evaluatingResultId, boolean customFormFlag, Long evaluatintFormID, ElderlyEvaluatingRecord elderlyEvaluatingRecord, ElderlyInfo elderlyInfo,
      Long elderlyInfoId, Long elderlyEvaluatingRecordId,String formLevel, String sectionsResult) {
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
    EvaluatingForm evaluatingForm = evaluatingFormService.find(evaluatintFormID);
    elderlyEvaluatingRecord.setEvaluatingForm(evaluatingForm);//设置评估记录用的评估表
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
    if (customFormFlag) {//处理使用自定义评估表的情况
      if (StringUtils.isNotBlank(formLevel) && StringUtils.isNotBlank(sectionsResult)) {
        sectionsResult = sectionsResult.replace(";;;;", ";").replace("::::", ":");
        //设置模块总分和等级(字符串)
        elderlyEvaluatingRecord.setSectionsResult(sectionsResult);
        //设置评估表最终结果等级        
        elderlyEvaluatingRecord.setEvaluatingResult(formLevel);
        elderlyEvaluatingRecordService.update(elderlyEvaluatingRecord,"tenantID","createDate");
      }        
    }else {//处理使用系统默认的评估表的情况
    /**
     * 更新入院评估记录的评估结果
     */
    //获取该评估表下的所有评估模块
    List<EvaluatingSection> evaluatingSections = evaluatingForm.getEvaluatingSections();
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
    }
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
   * 保存自定义评估表
   * @param ids
   * @return
   */
  @RequestMapping(value = "/createForm", method = RequestMethod.POST)
  public @ResponseBody Message createForm(String formName,Long[] ids, String evaluatingRule) {
    if (formName != null && ids != null) {
      Long currnetTenantId = tenantAccountService.getCurrentTenantID();//获取租户ID
      EvaluatingForm evaluatingForm = new EvaluatingForm();
      evaluatingForm.setTenantID(currnetTenantId);
      evaluatingForm.setFormName(formName);
      evaluatingForm.setEvaluatingFormStatus(EvaluatingFormStatus.ENABLE);
      if (StringUtils.isNotBlank(evaluatingRule)) {
        evaluatingForm.setEvaluatingRule(evaluatingRule);
      }
      evaluatingFormService.save(evaluatingForm);//保存自定义评估表
      
      for (Long id : ids) {
        EvaluatingSection evaluatingSection = evaluatingSectionService.find(id);
        if (evaluatingSection != null && evaluatingSection.getId() != null) {
          evaluatingSection.setEvaluatingForm(evaluatingForm);//设置自定义评估表关联的模块
          evaluatingSectionService.update(evaluatingSection);
        }
      }
    }else {
      return ERROR_MESSAGE;
    }
    return SUCCESS_MESSAGE;
  }
  
  /**
   * 计算并返回单个模块的等级
   * @return
   */
  @RequestMapping(value = "/getSectionLevel", method = RequestMethod.POST)
  public @ResponseBody Map<String, Integer> getSectionLevel(ModelMap model, String itemsScoreJSON) {
    int level=-1;
    try {
      level = elderlyEvaluatingRecordService.getSectionLevel(itemsScoreJSON);
    } catch (Exception e) {
      
    }
    Map<String, Integer> levelMap = new HashMap<String, Integer>();   
    levelMap.put("level", level);
    return levelMap;
  }
  /**
   * 计算并返回自定义评估表的等级
   * @return
   */
  @RequestMapping(value = "/getCustomFormLevel", method = RequestMethod.GET)
  public @ResponseBody Map<String, String> getCustomFormLevel(ModelMap model, Long formId, String sectionLevels) {
    EvaluatingForm evaluatingForm = evaluatingFormService.find(formId);
    if (evaluatingForm != null && StringUtils.isNotBlank(evaluatingForm.getEvaluatingRule())) {
      String level= "";
      level = elderlyEvaluatingRecordService.getCustomFormLevel(evaluatingForm.getEvaluatingRule(), sectionLevels);
      Map<String, String> levelMap = new HashMap<String,String>();   
      levelMap.put("level", level);
      return levelMap;
    }
    return null;
  }
  /**
   * 计算并返回系统默认评估表的等级
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
  /**
   * 题库公共搜索页面
   * 
   * @param model
   * @return
   */
  @RequestMapping(value = "/searchAllItems", method = RequestMethod.GET)
  public String searchAllItems(ModelMap model) {
    return "/elderlyEvaluatingRecord/searchAllItems";
  }
  /**
   * 所有题目查询list
   * 
   * @param pageable
   * @return
   */
  @RequestMapping(value = "/getAllItems", method = RequestMethod.POST)
  public @ResponseBody Page<EvaluatingItems> getAllItems(String keysOfItemName, Pageable pageable) {
    Page<EvaluatingItems> evaluatingItemsPage = null;
    if (keysOfItemName == null) {
      evaluatingItemsPage = evaluatingItemsService.findPage(pageable, true);
    } else {
      evaluatingItemsPage = evaluatingItemsService.searchPageByFilter(keysOfItemName, pageable);
    }

    return evaluatingItemsPage;
  }
  /**
   * 返回当前所有空闲模块是否已经包含了此题
   * @return
   */
  @RequestMapping(value = "/sectionContainItem", method = RequestMethod.GET)
  public @ResponseBody Map<String, Boolean> sectionContainItem(Long sectionId, String itemName) {
    boolean sectionContainItem = false;
    Map<String, Boolean> resultMap = new HashMap<String, Boolean>();  
    
    List<EvaluatingSection> evaluatingSectionList = evaluatingSectionService.findAll();
    int count = 1;
    while (count <= evaluatingSectionList.size()) {
      EvaluatingSection evaluatingSection = evaluatingSectionList.get(count - 1);
      if (evaluatingSection.getSystemSection() || evaluatingSection.getEvaluatingForm() != null){//移除系统定义的评估模块
          evaluatingSectionList.remove(count - 1);    // 从List中移除         
     }else{
           count ++;
     }  
    }
    if (StringUtils.isNotBlank(itemName) && evaluatingSectionList != null && evaluatingSectionList.size() > 0) {
      for (EvaluatingSection evaluatingSection : evaluatingSectionList) {
        List<EvaluatingItems> evaluatingItemsList = evaluatingSection.getEvaluatingItems();
        for (EvaluatingItems evaluatingItems2 : evaluatingItemsList) {
          if (evaluatingItems2.getItemName().equals(itemName)) {
            resultMap.put("sectionContainItem", true);
            return resultMap;
          }
        }
      }
    }    
    resultMap.put("sectionContainItem", sectionContainItem);
    return resultMap;
  }
  /**
   * 删除未分配模块下的题目，
   * 1.如果是已经分配过的题目就逻辑删（解除题目与模块之间的关系），
   * 2.如果是新的未分配过的题目，题目肯定直接删，但是题目下面的选项包括两种情况：
   *  2.1 如果题目下面的选项是已经分配过的就逻辑删（解除题目和选项之间的关系）
   *  2.2 如果题目下面的选项是新的未分配过，就直接删除
   * @return
   */
  @RequestMapping(value = "/deleteItem", method = RequestMethod.GET)
  public @ResponseBody Message deleteItem(Long itemId){
    EvaluatingItems evaluatingItems = evaluatingItemsService.find(itemId);
    List<EvaluatingSection>  evaluatingSectionList = evaluatingItems.getEvaluatingSections();
    boolean isNewItem = true;//标记这个题目是否是新的未被分配
    for (EvaluatingSection evaluatingSection : evaluatingSectionList) {
      if (evaluatingSection.getEvaluatingForm() != null) {//表示这个题目所属的这个模块已经被分配过
        isNewItem = false;
        break;
      }
    }
    if (!isNewItem) {//1.如果是已经分配过的题目就逻辑删（解除题目与模块之间的关系），
      for (EvaluatingSection evaluatingSection : evaluatingSectionList) {
        if (evaluatingSection.getEvaluatingForm() == null) {
          List<EvaluatingItems> evaluatingItemsList = evaluatingSection.getEvaluatingItems();
          evaluatingItemsList.remove(evaluatingItems);
          evaluatingSectionService.update(evaluatingSection);
        }
      }
    }else {
      //2.如果是新的未分配过的题目，题目肯定直接删，但是题目下面的选项包括两种情况：
      //2.1 如果题目下面的选项是已经分配过的就逻辑删（解除题目和选项之间的关系）
      //2.2 如果题目下面的选项是新的未分配过，就直接删除
      List<EvaluatingItemsOptions> evaluatingItemsOptionsList_item = evaluatingItems.getEvaluatingItemsOptions();
      for (EvaluatingItemsOptions evaluatingItemsOptions : evaluatingItemsOptionsList_item) {
        List<EvaluatingItemsOptions> evaluatingItemsOptionsList_option = evaluatingItemsOptions.getEvaluatingItemOptions().getEvaluatingItemsOptions();
        boolean isNewOption = true;//标记这个选项是否是新的未被分配
        loop_option_items : for (EvaluatingItemsOptions evaluatingItemsOptions_option : evaluatingItemsOptionsList_option) {
          EvaluatingItems evaluatingItems_option = evaluatingItemsOptions_option.getEvaluatingItems();
          List<EvaluatingSection>  evaluatingSectionList_option = evaluatingItems_option.getEvaluatingSections();
          for (EvaluatingSection evaluatingSection : evaluatingSectionList_option) {
            if (evaluatingSection.getEvaluatingForm() != null) {//表示这个题目所属的这个模块已经被分配过
              isNewOption = false;
              break loop_option_items;
            }
          }
        }
        if (!isNewOption) {//分配过，逻辑删
          evaluatingItemsOptionsService.delete(evaluatingItemsOptions);//逻辑删，解除关系
        }else {//新的未分配，直接删
          evaluatingItemsOptionsService.delete(evaluatingItemsOptions);//先解除关系
          evaluatingItemOptionsService.delete(evaluatingItemsOptions.getEvaluatingItemOptions());//直接删
        }
      }  
      //最终删除题目
      for (EvaluatingSection evaluatingSection : evaluatingSectionList) {
        if (evaluatingSection.getEvaluatingForm() == null) {
          List<EvaluatingItems> evaluatingItemsList = evaluatingSection.getEvaluatingItems();
          evaluatingItemsList.remove(evaluatingItems);
          evaluatingSectionService.update(evaluatingSection);
        }
      }
      evaluatingItemsService.delete(evaluatingItems);
    }
    
    return SUCCESS_MESSAGE;
  }
  
}
