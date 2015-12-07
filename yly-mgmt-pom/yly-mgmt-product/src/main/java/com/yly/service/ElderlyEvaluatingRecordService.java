package com.yly.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yly.entity.ElderlyEvaluatingRecord;
import com.yly.entity.EvaluatingSection;
import com.yly.framework.paging.Page;
import com.yly.framework.paging.Pageable;
import com.yly.framework.service.BaseService;

/**
 * 入院评估记录Service
 * 
 * @author luzhang
 *
 */
public interface ElderlyEvaluatingRecordService extends BaseService<ElderlyEvaluatingRecord, Long> {
  Page<ElderlyEvaluatingRecord> searchPageByFilter(String keysOfElderlyName, Date beginDate,
      Date endDate, Pageable pageable);
  /**
   * @param evaluatingSections
   * @param elderlyEvaluatingRecord
   * @return 返回每个模块对应总得分
   */
  Map<String,Integer> getSectionScoreMap(List<EvaluatingSection> evaluatingSections, ElderlyEvaluatingRecord elderlyEvaluatingRecord);
  /**
   * @param evaluatingSections
   * @param sectionScoreMap
   * @return 返回每个模块对应级别
   */
  Map<String, Integer> getSectionLevelMap(List<EvaluatingSection> evaluatingSections,ElderlyEvaluatingRecord elderlyEvaluatingRecord, Map<String, Integer> sectionScoreMap);
  /**
   * @param evaluatingSections
   * @return 返回每个模块对应评分规则(字符串形式)
   */
  Map<String,String> getSectionScoreRuleMap();
  /**
   * 返回默认评估表的评分规则
   */
  String getFormScoreRule();
  /**
   * 返回自定义评估表的评分规则
   */
  String getCustomFormScoreRule(String evaluatingRule);
  
  /**
   * @param sectionLevelMap
   * @return 根据每个模块对应等级，返回整个评估表的等级
   */
  String getFormPrimaryLevel(Map<String,Integer> sectionLevelMap);
  /**
   * @param sectionName
   * @param answerScores
   * @return 返回单个模块等级
   */
  Integer getSectionLevel(Long sectionId, String answerScores);
  /**
   * @param sectionLevels
   * @return 返回默认评估表的等级
   */
  String getFormLevel(String sectionLevels);
  /**
   * @param sectionLevels
   * @return 返回自定义评估表的等级
   */
  String getCustomFormLevel(String evaluatingRule, String sectionLevels);
  /**
   * @param evaluatingScoreRequest
   * @return 返回评估模块规则字符串
   */
  //String getEvaluatingRule(EvaluatingScoreRequest evaluatingScoreRequest);
  
}
