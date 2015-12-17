package com.yly.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.json.JSONException;

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
  Integer getSectionLevel(String itemsScoreJSON)  throws JSONException;
  /**
   * @param sectionLevels
   * @return 返回默认评估表的等级
   */
  String getFormLevel(String sectionsLevelJSON);
  /**
   * @param sectionLevels
   * @return 返回自定义评估表的等级
   */
  String getCustomFormLevel(String sectionsScoreJSON);

  List<Map<String, String>> prepareMap(List<ElderlyEvaluatingRecord> evaluatingRecordList);

  int countByFilter(String elderlyNameHidden, Date beginDateHidden, Date endDateHidden);

  List<ElderlyEvaluatingRecord> searchListByFilter(String elderlyNameHidden, Date beginDateHidden,
      Date endDateHidden);
}
