package com.yly.service.impl;

import java.lang.reflect.Field;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermRangeQuery;
import org.apache.lucene.util.Version;
import org.springframework.stereotype.Repository;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.yly.common.log.LogUtil;
import com.yly.dao.RepairRecordDao;
import com.yly.entity.RepairRecord;
import com.yly.framework.paging.Page;
import com.yly.framework.paging.Pageable;
import com.yly.framework.service.impl.BaseServiceImpl;
import com.yly.service.RepairRecordService;
import com.yly.utils.DateTimeUtils;

/**
 * Service 维修记录
 * 
 * @author pengyanan
 *
 */
@Repository("repairRecordServiceImpl")
public class RepairRecordServiceImpl extends BaseServiceImpl<RepairRecord, Long> implements
    RepairRecordService {

  @Resource(name = "repairRecordDaoImpl")
  private RepairRecordDao repairRecordDao;

  @Resource
  private void setBaseDao(RepairRecordDao repairRecordDao) {
    super.setBaseDao(repairRecordDao);
  }

  @Override
  public Page<RepairRecord> searchListByFilter(Pageable pageable, Date beginDate, Date endDate,
      RepairRecord repairRecord) {
    IKAnalyzer analyzer = new IKAnalyzer();
    analyzer.setMaxWordLength(true);
    BooleanQuery query = new BooleanQuery();
    Query keywordQuery = null;

    // 关键字搜索
    try {
      for (Field field : repairRecord.getClass().getDeclaredFields()) {
        field.setAccessible(true);
        if (!field.getName().equals("serialVersionUID") && field.get(repairRecord) != null) {
          String text = QueryParser.escape(field.get(repairRecord).toString());
          QueryParser keywordParser = new QueryParser(Version.LUCENE_35, field.getName(), analyzer);
          keywordQuery = keywordParser.parse(text);
          query.add(keywordQuery, Occur.MUST);
          if (LogUtil.isDebugEnabled(RepairRecordServiceImpl.class)) {
            LogUtil.debug(RepairRecordServiceImpl.class, "RepairRecordSearch",
                "Search repairRecord with params, tenant ID=%s, key attribute=%s, value=%s",
                tenantAccountService.getCurrentTenantID(), field.getName(), field.get(repairRecord)
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
        if (LogUtil.isDebugEnabled(RepairRecordServiceImpl.class)) {
          LogUtil.debug(RepairRecordServiceImpl.class, "RepairRecordSearch",
              "Search repairRecord with params, tenant ID=%s, beginDate=%s, endDate=%s",
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
