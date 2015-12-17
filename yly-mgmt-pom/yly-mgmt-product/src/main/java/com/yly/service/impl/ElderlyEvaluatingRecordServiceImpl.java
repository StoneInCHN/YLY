package com.yly.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermRangeQuery;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.util.Version;
import org.springframework.stereotype.Service;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.yly.dao.ElderlyEvaluatingRecordDao;
import com.yly.entity.ElderlyEvaluatingRecord;
import com.yly.entity.EvaluatingForm;
import com.yly.entity.EvaluatingItems;
import com.yly.entity.EvaluatingItemsAnswer;
import com.yly.entity.EvaluatingItemsOptions;
import com.yly.entity.EvaluatingSection;
import com.yly.entity.commonenum.CommonEnum.EvaluatingReason;
import com.yly.framework.paging.Page;
import com.yly.framework.paging.Pageable;
import com.yly.framework.service.impl.BaseServiceImpl;
import com.yly.service.ElderlyEvaluatingRecordService;
import com.yly.service.EvaluatingFormService;
import com.yly.service.EvaluatingItemsService;
import com.yly.service.EvaluatingSectionService;
import com.yly.utils.DateTimeUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * 入院评估记录 Service Implement
 * 
 * @author luzhang
 *
 */
@Service("elderlyEvaluatingRecordServiceImpl")
public class ElderlyEvaluatingRecordServiceImpl extends BaseServiceImpl<ElderlyEvaluatingRecord, Long>
    implements ElderlyEvaluatingRecordService {
  
  @Resource(name = "evaluatingSectionServiceImpl")
  private EvaluatingSectionService evaluatingSectionService;
  @Resource(name = "evaluatingFormServiceImpl")
  private EvaluatingFormService evaluatingFormService;
  
  @Resource(name = "elderlyEvaluatingRecordDaoImpl")
  private ElderlyEvaluatingRecordDao elderlyEvaluatingRecordDao;

  @Resource
  public void setBaseDao(ElderlyEvaluatingRecordDao elderlyEvaluatingRecordDao) {
    super.setBaseDao(elderlyEvaluatingRecordDao);
  }
  @Resource(name = "evaluatingItemsServiceImpl")
  private EvaluatingItemsService evaluatingItemsService;
  

  
  /**
   * @return 返回每个模块对应总得分
   */
  @Override
  public Map<String, Integer> getSectionScoreMap(List<EvaluatingSection> evaluatingSections, ElderlyEvaluatingRecord elderlyEvaluatingRecord) {
    Map<String,Integer> sectionScoreMap = new HashMap<String,Integer>();
    for (EvaluatingSection evaluatingSection : evaluatingSections) {
      sectionScoreMap.put(evaluatingSection.getId().toString(), 0);
    }
    List<EvaluatingItemsAnswer> evaluatingItemsAnswers = elderlyEvaluatingRecord.getEvaluatingItemsAnswers();
    for (EvaluatingItemsAnswer evaluatingItemsAnswer : evaluatingItemsAnswers) {
      EvaluatingItemsOptions evaluatingItemsOptions = evaluatingItemsAnswer.getEvaluatingItemsOptions();
      if (evaluatingItemsOptions != null && evaluatingItemsOptions.getEvaluatingItems() != null
          && evaluatingItemsOptions.getEvaluatingItems().getEvaluatingSections() != null) {
            String sectionId = evaluatingItemsOptions.getEvaluatingItems().getEvaluatingSections().get(0).getId().toString();
            sectionScoreMap.replace(sectionId, sectionScoreMap.get(sectionId)+evaluatingItemsOptions.getOptionScore());//累积得分
      }
    }
    return sectionScoreMap;
  }
  /**
   * 返回单个模块等级
   * @throws JSONException 
   */
  @Override
  public Integer getSectionLevel(String itemsScoreJSON) throws JSONException {
    //{"sectionId":"2","items":[{"id":"11","score":"0"},{"id":"12","score":"1"},{"id":"13","score":"1"}]}
    EvaluatingSection evaluatingSection = null;
    int level = -1;
    JSONObject itemsScore = new JSONObject(itemsScoreJSON);
    Long sectionId = Long.parseLong(itemsScore.getString("sectionId").trim());
    JSONArray itemsArray = itemsScore.getJSONArray("items");

    if (sectionId == null) {
      return level;
    }else {
      evaluatingSection = evaluatingSectionService.find(sectionId);
    }
    String sectionName = "";
    if (evaluatingSection.getSectionName()!=null && evaluatingSection.getSectionName()!=null) {
      sectionName = evaluatingSection.getSectionName();
    }
    int totalScore = 0;
    for(int i=0 ; i < itemsArray.length() ;i++){
      JSONObject itemScore = itemsArray.getJSONObject(i);                                                
      totalScore += Integer.parseInt(itemScore.getString("score").trim());
    }
    /*
     * 日常生活活动分级 
     * 0能力完好：总分100分 
     * 1轻度受损：总分65-95分 
     * 2中度受损：总分45-60分 
     * 3重度受损：总分≤40分
     */

      if (evaluatingSection.getSectionName().equals("日常生活活动")) {
        if (totalScore == 100) level = 0;
        if (totalScore >= 65 && totalScore <= 95) level = 1;
        if (totalScore >= 45 && totalScore <= 60) level = 2;
        if (totalScore <= 40) level = 3;
      }
    /*
     * 精神状态分级
     *  0能力完好：总分为0分 
     *  1轻度受损：总分为1分 
     *  2中度受损：总分2-3分 
     *  3重度受损：总分4-6分
     */           
      if (sectionName.equals("精神状态")) {
        if (totalScore == 0) level = 0;
        if (totalScore == 1) level = 1;
        if (totalScore == 2 || totalScore == 3) level = 2;
        if (totalScore >= 4 && totalScore <= 6) level = 3;
      }
    /*
     * 社会参与分级 
     * 0能力完好：总分为0-2分 
     * 1轻度受损：总分为3-7分 
     * 2中度受损：总分8-13分 
     * 3重度受损：总分14-20分
     */           
      if (sectionName.equals("社会参与")) {
        if (totalScore >= 0 && totalScore <= 2) level = 0;
        if (totalScore >= 3 && totalScore <= 7) level = 1;
        if (totalScore >= 8 && totalScore <= 13) level = 2;
        if (totalScore >= 14 && totalScore <= 20)level = 3;
      }  
      /*
       * 感知觉与沟通分级
       * 0能力完好：意识清醒，且视力和听力评为0或1，沟通评为0 
       * 1轻度受损：意识清醒，但视力或听力中至少一项评为2，或沟通评为1
       * 2中度受损：意识清醒，但视力或听力中至少一项评为3，或沟通评为2；或嗜睡，视力或听力评定为3及以下，沟通评定为2及以下
       * 3重度受损：意识清醒或嗜睡，但视力或听力中至少一项评为4，或沟通评为3；或昏睡/昏迷
       */

    if (sectionName.equals("感知觉与沟通")) {      
      int consciousness = -1, eyesight = -1, hearing = -1, communication = -1;//分别记录 意识 视力 听力 沟通 四个小项目的得分  
      for(int i=0 ; i < itemsArray.length() ;i++){
        JSONObject itemScoreObj = itemsArray.getJSONObject(i);  
        String itemId = itemScoreObj.getString("id").toString();
        String itemName = "";
        EvaluatingItems evaluatingItems = evaluatingItemsService.find(Long.parseLong(itemId));
        if (evaluatingItems != null) {
          itemName = evaluatingItems.getItemName();
        }
        int itemScore = Integer.parseInt(itemScoreObj.getString("score").toString());
        if (itemName.contains("意识")) consciousness = itemScore;
        if (itemName.contains("听力")) eyesight = itemScore;
        if (itemName.contains("视力")) hearing = itemScore;
        if (itemName.contains("沟通")) communication = itemScore;
      }
      if (consciousness >= 0 && eyesight >= 0 && hearing >=0 && communication>= 0) {
        /*
         * 意识清醒，且视力和听力评为0或1，沟通评为0
         */
        if (consciousness == 0 && (eyesight == 0 || eyesight == 1) && (hearing == 0 || hearing == 1) && communication == 0) {
          level = 0;
        }
        /*
         * 意识清醒，但视力或听力中至少一项评为2，或沟通评为1
         */
        if (consciousness == 0 && (eyesight == 2 || hearing == 2 || communication == 1)) {
          level = 1;
        }
        /*
         * 意识清醒，但视力或听力中至少一项评为3，或沟通评为2；
         * 或嗜睡，视力或听力评定为3及以下，沟通评定为2及以下
         */
        if ((consciousness == 0 && (eyesight == 3 || hearing == 3 || communication == 2)) 
            || (consciousness == 1 && (eyesight <= 3 || hearing <= 3) && communication <= 2)) {
          level = 2;
        }
        /*
         * 意识清醒或嗜睡，但视力或听力中至少一项评为4，或沟通评为3；
         * 或昏睡/昏迷
         */
        if (((consciousness == 0 || consciousness == 1) && (eyesight == 4 || hearing == 4 || communication == 3))
            || consciousness >= 2) {
          level = 3;
        }
      }
    }          
    return level;
  }

  /**
   * 解析每个模块对应等级的字符串，并调用getFormPrimaryLevel返回评估表等级
   */
  @Override
  public String getFormLevel(String sectionsLevelJSON) {
    //{"sectionId":"2","items":[{"id":"11","score":"0"},{"id":"12","score":"1"},{"id":"13","score":"1"}]}
    String formLevel = "";
    Map<String,Integer> sectionLevelMap = new HashMap<String, Integer>();
    JSONArray sectionsLevel = new JSONArray(sectionsLevelJSON);
    for(int i=0 ; i < sectionsLevel.length() ;i++){
      JSONObject sectionLevel = sectionsLevel.getJSONObject(i); 
      String sectionName = "";
      if (StringUtils.isNotBlank(sectionLevel.getString("id").trim())) {
        EvaluatingSection evaluatingSection = evaluatingSectionService.find(Long.parseLong(sectionLevel.getString("id").trim()));
        if (evaluatingSection != null && StringUtils.isNotBlank(evaluatingSection.getSectionName())) {
          sectionName = evaluatingSection.getSectionName();
          sectionLevelMap.put(sectionName,Integer.parseInt(sectionLevel.getString("level").trim()));
        }else {
          return formLevel;
        }
      }else {
        return formLevel;
      }
      
    }
    if (sectionLevelMap.size() > 0) {
      formLevel = getFormPrimaryLevel(sectionLevelMap);
    }
    return formLevel;
  }
  /**
   * 根据每个模块对应等级，返回整个评估表的等级
   */
  @Override
  public String getFormPrimaryLevel(Map<String,Integer> sectionLevelMap){

    String formPrimaryLevel = "";
    int dailyLife = -1, mentalState = -1, socialPart = -1, perceptionCom = -1;//分别记录 日常生活活动 精神状态 社会参与 感知觉与沟通 四个大模块的等级
    Iterator entries = sectionLevelMap.entrySet().iterator();          
    while (entries.hasNext()) {  
        Map.Entry entry = (Map.Entry) entries.next(); 
        if (entry.getKey().toString().equals("日常生活活动")) {
          dailyLife = (Integer)entry.getValue();
        }
        if (entry.getKey().toString().equals("精神状态")) {
          mentalState = (Integer)entry.getValue();
        }
        if (entry.getKey().toString().equals("社会参与")) {
          socialPart = (Integer)entry.getValue();
        }
   
        if (entry.getKey().toString().equals("感知觉与沟通")) {
          perceptionCom = (Integer)entry.getValue();
        }
    }
    /*    
     * 0 能力完好： 
     *    日常生活活动、精神状态、感知觉与沟通分级均为0，社会参与的分级为0或1。
     * 1 轻度失能：
     *    日常生活活动分级为0，但精神状态、感知觉与沟通中至少一项分级为1以上，或社会参与的分级为2；
     *    或日常生活活动分级为1，精神状态、感知觉与沟通、社会参与中至少有一项的分级为0或1。
     * 2 中度失能：
     *    日常生活活动分级为1，但精神状态、感知觉与沟通、社会参与均为2，或有一项为3；
     *    或日常生活活动分级为2，且精神状态、感知觉与沟通、社会参与中有1-2项的分级为1或2。
     * 3 重度失能：
     *    日常生活活动的分级为3；
     *    或日常生活活动、精神状态、感知觉与沟通、社会参与分级均为2；
     *    或日常生活活动分级为2，且精神状态、感知觉与沟通、社会参与中至少有一项分级为3。
     */    
    if (dailyLife >= 0  && mentalState >= 0 && socialPart >= 0  && perceptionCom >= 0 ) {
          /**
           * 0 能力完好：
           *   日常生活活动、精神状态、感知觉与沟通分级均为0，社会参与的分级为0或1。
           */
        if (dailyLife == 0 && mentalState == 0 && perceptionCom ==0 && (socialPart == 0 || socialPart == 1)) {
            formPrimaryLevel = "0 能力完好";
          }
          /**
           * 1 轻度失能：
           *   日常生活活动分级为0，但精神状态、感知觉与沟通中至少一项分级为1以上，或社会参与的分级为2；
           *   或日常生活活动分级为1，精神状态、感知觉与沟通、社会参与中至少有一项的分级为0或1。
           */
        else if ((dailyLife == 0 && (mentalState <=1 || perceptionCom <=1 || socialPart == 2))
               ||(dailyLife == 1 &&(mentalState <=1 || perceptionCom <=1 || socialPart <=1))) {
            formPrimaryLevel = "1 轻度失能";
         }
          /**
           * 2 中度失能：
           *   日常生活活动分级为1，但精神状态、感知觉与沟通、社会参与均为2，或有一项为3；
           *   或日常生活活动分级为2，且精神状态、感知觉与沟通、社会参与中有1-2项的分级为1或2。
           */
        else if ((dailyLife == 1 && (
                    (mentalState == 2 && perceptionCom ==2 && socialPart == 2)
                  ||(mentalState == 3 && perceptionCom != 3 && socialPart != 3)
                  ||(mentalState != 3 && perceptionCom == 3 && socialPart != 3)
                  ||(mentalState != 3 && perceptionCom != 3 && socialPart == 3)))
              ||(dailyLife == 2 && (
                    (is1or2(mentalState) && !is1or2(perceptionCom) && !is1or2(socialPart))
                  ||(!is1or2(mentalState) && is1or2(perceptionCom) && !is1or2(socialPart))
                  ||(!is1or2(mentalState) && !is1or2(perceptionCom) && is1or2(socialPart))
                  ||(is1or2(mentalState) && is1or2(perceptionCom) && !is1or2(socialPart))
                  ||(is1or2(mentalState) && !is1or2(perceptionCom) && is1or2(socialPart))
                  ||(!is1or2(mentalState) && is1or2(perceptionCom) && is1or2(socialPart)) )))
                   {
          formPrimaryLevel = "2 中度失能";
         }
        /**
         * 3 重度失能：
         *    日常生活活动的分级为3；
         *    或日常生活活动、精神状态、感知觉与沟通、社会参与分级均为2；
         *    或日常生活活动分级为2，且精神状态、感知觉与沟通、社会参与中至少有一项分级为3。
         */
        else if (dailyLife == 3
             || (dailyLife == 2 && mentalState == 2 && socialPart == 2 && perceptionCom == 2)
             || (dailyLife == 2 &&(mentalState == 3 || socialPart == 3 || perceptionCom == 3))){
          formPrimaryLevel = "3 重度失能";
        }
    }
    return formPrimaryLevel;
  }
  /**
   * @param level
   * @return 判断分级是否1或2
   */
  public boolean is1or2(int level){
    if (level == 1 || level == 2) {
      return true;
    }else {
      return false;
    }
  }

  @Override
  public String getCustomFormScoreRule(String evaluatingRule) {
    //丧失能力:0~20;重度失能:19~40;中度失能:39~60;轻度失能:59~80;能力完好:79~100
    StringBuffer formScoreRule = new StringBuffer();
    formScoreRule.append("&nbsp;&nbsp;&nbsp;&nbsp;自定义评估表等级划分标准<p/>");
    if (StringUtils.isNotBlank(evaluatingRule)) {
      JSONArray jsonArray = new JSONArray(evaluatingRule);
      for (int i = 0; i < jsonArray.length(); i++) {
        JSONObject jsonObject = jsonArray.getJSONObject(i);
        String LevelName = jsonObject.getString("LevelName");
        String ScoreScope = jsonObject.getString("ScoreScope");
        formScoreRule.append(LevelName);
        formScoreRule.append(":");
        formScoreRule.append("&nbsp;&nbsp;&nbsp;&nbsp;总分为");
        formScoreRule.append(ScoreScope);
        formScoreRule.append("分<p/>");
      }
    }
    return formScoreRule.toString();
  }

  @Override
  public String getCustomFormLevel(String sectionsScoreJSON) {
    // {"formId":"12","sections":[{"id":"9","score":"38"},{"id":"10","score":"8"}]}
    String formPrimaryLevel = "";
    JSONObject sectionsScore = new JSONObject(sectionsScoreJSON);
    EvaluatingForm evaluatingForm = null;
    String evaluatingRule = "";
    Long formId = Long.parseLong(sectionsScore.getString("formId").trim());
    if (formId != null) {
      evaluatingForm = evaluatingFormService.find(formId);
      if (evaluatingForm != null && StringUtils.isNotBlank(evaluatingForm.getEvaluatingRule())) {
        evaluatingRule = evaluatingForm.getEvaluatingRule();
      } else {
        return formPrimaryLevel;
      }
    } else {
      return formPrimaryLevel;
    }
    JSONArray sectionsScoreList = sectionsScore.getJSONArray("sections");
    int totalScore = 0;// 整个评估表的总分
    for (int i = 0; i < sectionsScoreList.length(); i++) {
      JSONObject sectionScore = sectionsScoreList.getJSONObject(i);
      totalScore += Integer.parseInt(sectionScore.getString("score").trim());
    }
    // evaluatingRule 例如 --> 丧失能力:0~20;重度失能:19~40;中度失能:39~60;轻度失能:59~80;能力完好:79~100
    JSONArray jsonArray = new JSONArray(evaluatingRule);
    for (int i = 0; i < jsonArray.length(); i++) {
      JSONObject jsonObject = jsonArray.getJSONObject(i);
      String ScoreScopeStr = jsonObject.getString("ScoreScope");
      if (ScoreScopeStr.contains("~")) {
        String[] scores = ScoreScopeStr.split("~");
        int from = Integer.parseInt(scores[0]);
        int to = Integer.parseInt(scores[1]);
        if (totalScore >= from && totalScore <= to) {
          formPrimaryLevel = jsonObject.getString("LevelName");
          break;
        }
      }
    }
    return formPrimaryLevel;
  }

  /**
   * 根据搜索条件，返回查询结果个数
   */
  @Override
  public int countByFilter(String elderlyNameHidden, Date beginDateHidden, Date endDateHidden) {
    IKAnalyzer analyzer = new IKAnalyzer();
    analyzer.setMaxWordLength(true);
    try {
      BooleanQuery booleanQuery = getQuery(analyzer, elderlyNameHidden, beginDateHidden, endDateHidden);
      return elderlyEvaluatingRecordDao.count(booleanQuery, analyzer, null);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return 0;
  }

  /**
   * 根据搜索条件，返回查询结果List
   */
  @Override
  public List<ElderlyEvaluatingRecord> searchListByFilter(String elderlyNameHidden,
      Date beginDateHidden, Date endDateHidden) {
    IKAnalyzer analyzer = new IKAnalyzer();
    analyzer.setMaxWordLength(true);
    try {
      BooleanQuery query = getQuery(analyzer, elderlyNameHidden, beginDateHidden, endDateHidden);
      return elderlyEvaluatingRecordDao.searchList(query, analyzer, null);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }
  /**
   * 根据搜索条件，返回查询结果page
   */ 
  @Override
  public Page<ElderlyEvaluatingRecord> searchPageByFilter(String keysOfElderlyName, Date beginDate,
      Date endDate, Pageable pageable) {
    IKAnalyzer analyzer = new IKAnalyzer();
    analyzer.setMaxWordLength(true);

    try {
      BooleanQuery booleanQuery = new BooleanQuery();
      
      QueryParser queryParser = new QueryParser(Version.LUCENE_35, "tenantID", analyzer);
      Query query = queryParser.parse(tenantAccountService.getCurrentTenantID().toString());
      booleanQuery.add(query, Occur.MUST);
      
      if (keysOfElderlyName != null) {
        String text = QueryParser.escape(keysOfElderlyName);
        QueryParser filterParser = new QueryParser(Version.LUCENE_35, "elderlyInfo.name", analyzer);
        Query filterQuery = filterParser.parse(text);
        booleanQuery.add(filterQuery, Occur.MUST);
      }
      if (beginDate != null || endDate != null) {
        TermRangeQuery tQuery =
            new TermRangeQuery("evaluatingDate", DateTimeUtils.convertDateToString(beginDate, null),
                DateTimeUtils.convertDateToString(endDate, null), beginDate != null, endDate != null);
        booleanQuery.add(tQuery, Occur.MUST);
      }

      return elderlyEvaluatingRecordDao.search(booleanQuery, pageable, analyzer, null);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }
  /**
   * 根据查询条件中的 老人姓名，评估开始时间和结束时间，返回带条件的查询Query
   * @return
   */
  private BooleanQuery getQuery(IKAnalyzer analyzer, String elderlyNameHidden, Date beginDateHidden, Date endDateHidden) {
    BooleanQuery booleanQuery  = new BooleanQuery();
    try {
      Long tennateId = tenantAccountService.getCurrentTenantID();
      if (tennateId != null) {
        QueryParser queryParser = new QueryParser(Version.LUCENE_35, "tenantID", analyzer);
        Query query = queryParser.parse(tennateId.toString());
        booleanQuery.add(query, Occur.MUST);
      }
      
      if (elderlyNameHidden != null) {
        String text = QueryParser.escape(elderlyNameHidden);
        QueryParser filterParser = new QueryParser(Version.LUCENE_35, "elderlyInfo.name", analyzer);
        Query filterQuery = filterParser.parse(text);
        booleanQuery.add(filterQuery, Occur.MUST);
      }  
      if (beginDateHidden != null || endDateHidden != null) {
        TermRangeQuery tQuery =
            new TermRangeQuery("evaluatingDate", DateTimeUtils.convertDateToString(beginDateHidden, null),
                DateTimeUtils.convertDateToString(endDateHidden, null), beginDateHidden != null, endDateHidden != null);
        booleanQuery.add(tQuery, Occur.MUST);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return booleanQuery;
  }
  /**
   * 准备数据，将list转化成HashMap,作为需要导出的数据
   * @return
   */
  @Override
  public List<Map<String, String>> prepareMap(List<ElderlyEvaluatingRecord> evaluatingRecordList) {
    List<Map<String, String>> mapList = new ArrayList<Map<String,String>>();
    for (ElderlyEvaluatingRecord evaluatingRecord : evaluatingRecordList) {
      Map<String, String> evaluatingRecordMap = new HashMap<String, String>();
      evaluatingRecordMap.put("elderlyInfo.name", evaluatingRecord.getElderlyInfo()!=null?evaluatingRecord.getElderlyInfo().getName():"");
      evaluatingRecordMap.put("operator", evaluatingRecord.getOperator());
      if(evaluatingRecord.getEvaluatingReason() == EvaluatingReason.CHECKIN_EVALUATING){
        evaluatingRecordMap.put("evaluatingReason", "入院评估");
      }else if (evaluatingRecord.getEvaluatingReason() == EvaluatingReason.ROUTINE_EVALUATING) {
        evaluatingRecordMap.put("evaluatingReason", "入院后常规评估");
      }else if (evaluatingRecord.getEvaluatingReason() == EvaluatingReason.IMMEDIATE_EVALUATING) {
        evaluatingRecordMap.put("evaluatingReason", "状况发生变化后的即时评估");
      }else if (evaluatingRecord.getEvaluatingReason() == EvaluatingReason.QUESTION_EVALUATING) {
        evaluatingRecordMap.put("evaluatingReason", "有疑问的再次评估");
      }
      evaluatingRecordMap.put("evaluatingResult", evaluatingRecord.getEvaluatingResult());
      evaluatingRecordMap.put("evaluatingForm.formName", evaluatingRecord.getEvaluatingForm()!=null?evaluatingRecord.getEvaluatingForm().getFormName():"");
      if (evaluatingRecord.getEvaluatingDate() != null) {
        evaluatingRecordMap.put("evaluatingDate", DateTimeUtils.getSimpleFormatString(DateTimeUtils.shortDateFormat, evaluatingRecord.getEvaluatingDate()));
      }else {
        evaluatingRecordMap.put("evaluatingDate", "");
      }
      
      mapList.add(evaluatingRecordMap);
    }
    return mapList;
  }
}
