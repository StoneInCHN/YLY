package com.yly.service.impl;

import java.lang.reflect.Field;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermRangeQuery;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.util.Version;
import org.springframework.stereotype.Repository;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.yly.common.log.LogUtil;
import com.yly.dao.WaterElectricityRecordDao;
import com.yly.entity.WaterElectricityRecord;
import com.yly.framework.paging.Page;
import com.yly.framework.paging.Pageable;
import com.yly.framework.service.impl.BaseServiceImpl;
import com.yly.service.WaterElectricityRecordService;
import com.yly.utils.DateTimeUtils;

/**
 * ServiceImpl - 水电抄表记录
 * @author pengyanan
 *
 */
@Repository("waterElectricityRecordServiceImpl")
public class WaterElectricityRecordServiceImpl extends
    BaseServiceImpl<WaterElectricityRecord, Long> implements WaterElectricityRecordService {

  @Resource(name = "waterElectricityRecordDaoImpl")
  private WaterElectricityRecordDao waterElectricityRecordDao;
  
  @Resource
  private void setBaseDao(WaterElectricityRecordDao waterElectricityRecordDao){
    super.setBaseDao(waterElectricityRecordDao);
  }

  
  @Override
  public Page<WaterElectricityRecord> searchListByFilter(Pageable pageable, Date beginDate,
      Date endDate, WaterElectricityRecord waterEletricityRecord) {
    IKAnalyzer analyzer = new IKAnalyzer();
    analyzer.setMaxWordLength(true);
    BooleanQuery query = new BooleanQuery();
    Query keywordQuery = null;

    // 关键字搜索
    try {
      for (Field field : waterEletricityRecord.getClass().getDeclaredFields()) {
        field.setAccessible(true);
        if (!field.getName().equals("serialVersionUID") && field.get(waterEletricityRecord) != null) {
          String text = QueryParser.escape(field.get(waterEletricityRecord).toString());
          QueryParser keywordParser = new QueryParser(Version.LUCENE_35, field.getName(), analyzer);
          keywordQuery = keywordParser.parse(text);
          query.add(keywordQuery, Occur.MUST);
          if (LogUtil.isDebugEnabled(WaterElectricityRecordServiceImpl.class)) {
            LogUtil.debug(WaterElectricityRecordServiceImpl.class, "waterEletricityRecordSearch",
                "Search waterEletricityRecord with params, tenant ID=%s, key attribute=%s, value=%s",
                tenantAccountService.getCurrentTenantID(), field.getName(), field.get(waterEletricityRecord)
                    .toString());
          }
        }
      }
      // 关键字-时间范围
      if (beginDate != null || endDate != null) {
        TermRangeQuery tquery =
            new TermRangeQuery("createDate", DateTimeUtils.convertDateToString(beginDate, null),
                DateTimeUtils.convertDateToString(endDate, null), beginDate != null,
                endDate != null);
        query.add(tquery, Occur.MUST);
        if (LogUtil.isDebugEnabled(WaterElectricityRecordServiceImpl.class)) {
          LogUtil.debug(WaterElectricityRecordServiceImpl.class, "waterEletricityRecordSearch",
              "Search waterEletricityRecord with params, tenant ID=%s, beginDate=%s, endDate=%s",
              tenantAccountService.getCurrentTenantID(),
              DateTimeUtils.convertDateToString(beginDate, null),
              DateTimeUtils.convertDateToString(endDate, null));
        }
      }

    } catch (Exception e) {
      e.printStackTrace();
    }
    return super.search(query, pageable, analyzer, null);
  }

}
