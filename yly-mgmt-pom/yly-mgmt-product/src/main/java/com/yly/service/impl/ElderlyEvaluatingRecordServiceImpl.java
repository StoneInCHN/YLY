package com.yly.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import com.yly.entity.EvaluatingItemsAnswer;
import com.yly.entity.EvaluatingItemsOptions;
import com.yly.entity.EvaluatingSection;
import com.yly.framework.paging.Page;
import com.yly.framework.paging.Pageable;
import com.yly.framework.service.impl.BaseServiceImpl;
import com.yly.service.ElderlyEvaluatingRecordService;
import com.yly.utils.DateTimeUtils;

/**
 * 入院评估记录 Service Implement
 * 
 * @author luzhang
 *
 */
@Service("elderlyEvaluatingRecordServiceImpl")
public class ElderlyEvaluatingRecordServiceImpl extends BaseServiceImpl<ElderlyEvaluatingRecord, Long>
    implements ElderlyEvaluatingRecordService {

  @Resource(name = "elderlyEvaluatingRecordDaoImpl")
  private ElderlyEvaluatingRecordDao elderlyEvaluatingRecordDao;

  @Resource
  public void setBaseDao(ElderlyEvaluatingRecordDao elderlyEvaluatingRecordDao) {
    super.setBaseDao(elderlyEvaluatingRecordDao);
  }

  @Override
  public Page<ElderlyEvaluatingRecord> searchPageByFilter(String keysOfElderlyName, Date beginDate,
      Date endDate, Pageable pageable) {
    IKAnalyzer analyzer = new IKAnalyzer();
    analyzer.setMaxWordLength(true);

    try {
      BooleanQuery query = new BooleanQuery();
      if (keysOfElderlyName != null) {
        String text = QueryParser.escape(keysOfElderlyName);
        QueryParser filterParser = new QueryParser(Version.LUCENE_35, "elderlyInfo.name", analyzer);
        Query filterQuery = filterParser.parse(text);
        query.add(filterQuery, Occur.MUST);
      }
      if (beginDate != null || endDate != null) {
        TermRangeQuery tQuery =
            new TermRangeQuery("creatDate", DateTimeUtils.convertDateToString(beginDate, null),
                DateTimeUtils.convertDateToString(endDate, null), beginDate != null, endDate != null);
        query.add(tQuery, Occur.MUST);
      }

      return elderlyEvaluatingRecordDao.search(query, pageable, analyzer, null);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }
  
  /**
   * @return 返回每个模块对应总得分
   */
  @Override
  public Map<String, Integer> getSectionScoreMap(List<EvaluatingSection> evaluatingSections, ElderlyEvaluatingRecord elderlyEvaluatingRecord) {
    Map<String,Integer> sectionScoreMap = new HashMap<String,Integer>();
    for (EvaluatingSection evaluatingSection : evaluatingSections) {
      sectionScoreMap.put(evaluatingSection.getSectionName(), 0);
    }
    Set<EvaluatingItemsAnswer> evaluatingItemsAnswers = elderlyEvaluatingRecord.getEvaluatingItemsAnswers();
    for (EvaluatingItemsAnswer evaluatingItemsAnswer : evaluatingItemsAnswers) {
      EvaluatingItemsOptions evaluatingItemsOptions = evaluatingItemsAnswer.getEvaluatingItemsOptions();
      if (evaluatingItemsOptions != null && evaluatingItemsOptions.getEvaluatingItems() != null
          && evaluatingItemsOptions.getEvaluatingItems().getEvaluatingSection() != null) {
            String sectionName = evaluatingItemsOptions.getEvaluatingItems().getEvaluatingSection().getSectionName();
            sectionScoreMap.replace(sectionName, sectionScoreMap.get(sectionName)+evaluatingItemsOptions.getOptionScore());//累积得分
      }
    }
    return sectionScoreMap;
  }
  
  /**
   * @return 返回每个模块对应级别  
   * 备注 该方法判定每个模块的级别的规则是hardcode，并不适应于每个租户个性胡自定义的评估表(后期有待讨论)
   */
  @Override
  public Map<String, Integer> getSectionLevelMap(List<EvaluatingSection> evaluatingSections,ElderlyEvaluatingRecord elderlyEvaluatingRecord, Map<String, Integer> sectionScoreMap) {
    Map<String,Integer> sectionLevelMap = new HashMap<String,Integer>();
    for (EvaluatingSection evaluatingSection : evaluatingSections) {
      sectionLevelMap.put(evaluatingSection.getSectionName(), -1);
    }
    Set<EvaluatingItemsAnswer> evaluatingItemsAnswers = elderlyEvaluatingRecord.getEvaluatingItemsAnswers();
    Iterator entries = sectionScoreMap.entrySet().iterator();          
    while (entries.hasNext()) {  
        Map.Entry entry = (Map.Entry) entries.next(); 
      /*
       * 感知觉与沟通分级
       * 0能力完好：意识清醒，且视力和听力评为0或1，沟通评为0 
       * 1轻度受损：意识清醒，但视力或听力中至少一项评为2，或沟通评为1
       * 2中度受损：意识清醒，但视力或听力中至少一项评为3，或沟通评为2；或嗜睡，视力或听力评定为3及以下，沟通评定为2及以下
       * 3重度受损：意识清醒或嗜睡，但视力或听力中至少一项评为4，或沟通评为3；或昏睡/昏迷
       */
        
      if (entry.getKey().toString().equals("感知觉与沟通")) {
        //获取 感知与沟通 模块下面的所有answers (Perception and communication) 缩小遍历范围(长度为4,感知与沟通模块下面只有四个小评估项目)
        Set<EvaluatingItemsAnswer> answersForPerCom = new HashSet<EvaluatingItemsAnswer>();//用于存放 感知与沟通 模块下面的所有answers(长度为4)
        for (EvaluatingItemsAnswer evaluatingItemsAnswer : evaluatingItemsAnswers) {
          EvaluatingItemsOptions evaluatingItemsOptions = evaluatingItemsAnswer.getEvaluatingItemsOptions();
          if (evaluatingItemsOptions != null && evaluatingItemsOptions.getEvaluatingItems() != null
              && evaluatingItemsOptions.getEvaluatingItems().getEvaluatingSection() != null
              && evaluatingItemsOptions.getEvaluatingItems().getEvaluatingSection().getSectionName().equals("感知觉与沟通")) {
               answersForPerCom.add(evaluatingItemsAnswer);
          }
        }
        
        int consciousness = -1, eyesight = -1, hearing = -1, communication = -1;//分别记录 意识 视力 听力 沟通 四个小项目的得分  
        for (EvaluatingItemsAnswer evaluatingItemsAnswer : answersForPerCom) {//遍历感知与沟通模块下面的所有answers(长度为4)
          EvaluatingItemsOptions evaluatingItemsOptions = evaluatingItemsAnswer.getEvaluatingItemsOptions();
          if (evaluatingItemsOptions != null&& evaluatingItemsOptions.getEvaluatingItems() != null
              && evaluatingItemsOptions.getEvaluatingItems().getItemName() != null) {
            String itemName = evaluatingItemsOptions.getEvaluatingItems().getItemName();
            if (itemName.contains("意识")) consciousness = evaluatingItemsOptions.getOptionScore();
            if (itemName.contains("听力")) eyesight = evaluatingItemsOptions.getOptionScore();
            if (itemName.contains("视力")) hearing = evaluatingItemsOptions.getOptionScore();
            if (itemName.contains("沟通")) communication = evaluatingItemsOptions.getOptionScore();
          }
        }
        if (consciousness >= 0 && eyesight >= 0 && hearing >=0 && communication>= 0) {
          /*
           * 意识清醒，且视力和听力评为0或1，沟通评为0
           */
          if (consciousness == 0 && (eyesight == 0 || eyesight == 1) && (hearing == 0 || hearing == 1) && communication == 0) {
            sectionLevelMap.replace(entry.getKey().toString(), 0);
          }
          /*
           * 意识清醒，但视力或听力中至少一项评为2，或沟通评为1
           */
          if (consciousness == 0 && (eyesight == 2 || hearing == 2 || communication == 1)) {
            sectionLevelMap.replace(entry.getKey().toString(), 1);
          }
          /*
           * 意识清醒，但视力或听力中至少一项评为3，或沟通评为2；
           * 或嗜睡，视力或听力评定为3及以下，沟通评定为2及以下
           */
          if ((consciousness == 0 && (eyesight == 3 || hearing == 3 || communication == 2)) 
              || (consciousness == 1 && (eyesight <= 3 || hearing <= 3) && communication <= 2)) {
            sectionLevelMap.replace(entry.getKey().toString(), 2);
          }
          /*
           * 意识清醒或嗜睡，但视力或听力中至少一项评为4，或沟通评为3；
           * 或昏睡/昏迷
           */
          if (((consciousness == 0 || consciousness == 1) && (eyesight == 4 || hearing == 4 || communication == 3))
              || consciousness <= 2) {
            sectionLevelMap.replace(entry.getKey().toString(), 3);
          }
        }
      }          
      /*
       * 日常生活活动分级 
       * 0能力完好：总分100分 
       * 1轻度受损：总分65-95分 
       * 2中度受损：总分45-60分 
       * 3重度受损：总分≤40分
       */
        if (entry.getKey().toString().equals("日常生活活动")) {
          if ((Integer)entry.getValue() == 100) {
            sectionLevelMap.replace(entry.getKey().toString(), 0);
          }
          if ((Integer)entry.getValue() >= 65 && (Integer)entry.getValue() <= 95) {
            sectionLevelMap.replace(entry.getKey().toString(), 1);
          }
          if ((Integer)entry.getValue() >= 45 && (Integer)entry.getValue() <= 60) {
            sectionLevelMap.replace(entry.getKey().toString(), 2);
          }
          if ((Integer)entry.getValue() <= 40) {
            sectionLevelMap.replace(entry.getKey().toString(), 3);
          }
        }
      /*
       * 精神状态分级
       *  0能力完好：总分为0分 
       *  1轻度受损：总分为1分 
       *  2中度受损：总分2-3分 
       *  3重度受损：总分4-6分
       */           
        if (entry.getKey().toString().equals("精神状态")) {
          if ((Integer)entry.getValue() == 0) {
            sectionLevelMap.replace(entry.getKey().toString(), 0);
          }
          if ((Integer)entry.getValue() == 1) {
            sectionLevelMap.replace(entry.getKey().toString(), 1);
          }
          if ((Integer)entry.getValue() == 2 || (Integer)entry.getValue() == 3) {
            sectionLevelMap.replace(entry.getKey().toString(), 2);
          }
          if ((Integer)entry.getValue() >= 4 && (Integer)entry.getValue() <= 6) {
            sectionLevelMap.replace(entry.getKey().toString(), 3);
          }
        }
      /*
       * 社会参与分级 
       * 0能力完好：总分为0-2分 
       * 1轻度受损：总分为3-7分 
       * 2中度受损：总分8-13分 
       * 3重度受损：总分14-20分
       */           
        if (entry.getKey().toString().equals("社会参与")) {
          if ((Integer)entry.getValue() >= 0 && (Integer)entry.getValue() <= 2) {
            sectionLevelMap.replace(entry.getKey().toString(), 0);
          }
          if ((Integer)entry.getValue() >= 3 && (Integer)entry.getValue() <= 7) {
            sectionLevelMap.replace(entry.getKey().toString(), 1);
          }
          if ((Integer)entry.getValue() >= 8 && (Integer)entry.getValue() <= 13) {
            sectionLevelMap.replace(entry.getKey().toString(), 2);
          }
          if ((Integer)entry.getValue() >= 14 && (Integer)entry.getValue() <= 20) {
            sectionLevelMap.replace(entry.getKey().toString(), 3);
          }
        }  
    }
    return sectionLevelMap;
  }
  /**
   * 返回每个模块对应评分规则(字符串形式) 目前为hardcode (后期改成动态的获取规则字符串)
   */
  @Override
  public Map<String, String> getSectionScoreRuleMap() {
    Map<String,String> sectionScoreRuleMap = new HashMap<String,String>();
    sectionScoreRuleMap.put("日常生活活动", "0 能力完好：总分100分<p/>1 轻度受损：总分65-95分<p/>2 中度受损：总分45-60分<p/>3 重度受损：总分≤40分");
    sectionScoreRuleMap.put("精神状态", "0能力完好：总分为0分<p/>1轻度受损：总分为1分<p/>2中度受损：总分2-3分<p/>3重度受损：总分4-6分");
    sectionScoreRuleMap.put("社会参与", "0能力完好：总分为0-2分 <p/>1轻度受损：总分为3-7分<p/>2中度受损：总分8-13分 <p/>3重度受损：总分14-20分");
    sectionScoreRuleMap.put("感知觉与沟通", "0能力完好：意识清醒，且视力和听力评为0或1，沟通评为0<p/>1轻度受损：意识清醒，但视力或听力中至少一项评为2，或沟通评为1<p/>"
                  + "2中度受损：意识清醒，但视力或听力中至少一项评为3，或沟通评为2；或嗜睡，视力或听力评定为3及以下，沟通评定为2及以下<p/>"
                  + "3重度受损：意识清醒或嗜睡，但视力或听力中至少一项评为4，或沟通评为3；或昏睡/昏迷");
    return sectionScoreRuleMap;
  }
  

  /**
   * 返回整个评估表的评分规则
   */
  @Override
  public String getFormScoreRule(){
    StringBuffer formScoreRule = new StringBuffer();
    formScoreRule.append("&nbsp;&nbsp;&nbsp;&nbsp;注：老年人能力初步等级划分标准<p/>");
    formScoreRule.append("0 能力完好：<p/>");
    formScoreRule.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日常生活活动、精神状态、感知觉与沟通分级均为0，社会参与的分级为0或1。<p/>");
    formScoreRule.append("1 轻度失能：<p/>");
    formScoreRule.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日常生活活动分级为0，但精神状态、感知觉与沟通中至少一项分级为1以上，或社会参与的分级为2；<p/>");
    formScoreRule.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;或日常生活活动分级为1，精神状态、感知觉与沟通、社会参与中至少有一项的分级为0或1。<p/>");
    formScoreRule.append("2 中度失能：<p/>");
    formScoreRule.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日常生活活动分级为1，但精神状态、感知觉与沟通、社会参与均为2，或有一项为3；<p/>");
    formScoreRule.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;或日常生活活动分级为2，且精神状态、感知觉与沟通、社会参与中有1-2项的分级为1或2。<p/>");
    formScoreRule.append("3 重度失能：<p/>");
    formScoreRule.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日常生活活动的分级为3；<p/>");
    formScoreRule.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;或日常生活活动、精神状态、感知觉与沟通、社会参与分级均为2；<p/>");
    formScoreRule.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;或日常生活活动分级为2，且精神状态、感知觉与沟通、社会参与中至少有一项分级为3。<p/>");
    return formScoreRule.toString();
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
        if ((dailyLife == 0 && (mentalState <=1 || perceptionCom <=1 || socialPart == 2))
               ||(dailyLife == 1 &&(mentalState <=1 || perceptionCom <=1 || socialPart <=1))) {
            formPrimaryLevel = "1 轻度失能";
         }
          /**
           * 2 中度失能：
           *   日常生活活动分级为1，但精神状态、感知觉与沟通、社会参与均为2，或有一项为3；
           *   或日常生活活动分级为2，且精神状态、感知觉与沟通、社会参与中有1-2项的分级为1或2。
           */
        if ((dailyLife == 1 && (
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
        if (dailyLife == 3
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
}
