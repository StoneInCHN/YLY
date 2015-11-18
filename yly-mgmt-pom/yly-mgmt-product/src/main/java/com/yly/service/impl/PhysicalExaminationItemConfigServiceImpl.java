package com.yly.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.lucene.index.Term;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TermRangeQuery;
import org.apache.lucene.util.Version;
import org.springframework.stereotype.Service;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.yly.common.log.LogUtil;
import com.yly.controller.FixedAssetsController;
import com.yly.dao.PhysicalExaminationItemConfigDao;
import com.yly.entity.PhysicalExaminationItemConfig;
import com.yly.entity.commonenum.CommonEnum.ConfigKey;
import com.yly.framework.filter.Filter;
import com.yly.framework.filter.Filter.Operator;
import com.yly.framework.ordering.Ordering;
import com.yly.framework.ordering.Ordering.Direction;
import com.yly.framework.paging.Page;
import com.yly.framework.paging.Pageable;
import com.yly.framework.service.impl.BaseServiceImpl;
import com.yly.service.PhysicalExaminationItemConfigService;
import com.yly.utils.DateTimeUtils;
import com.yly.utils.FieldFilterUtils;

@Service("physicalExaminationItemConfigServiceImpl")
public class PhysicalExaminationItemConfigServiceImpl extends BaseServiceImpl<PhysicalExaminationItemConfig, Long> implements PhysicalExaminationItemConfigService {

  @Resource(name = "physicalExaminationItemConfigDaoImpl")
  private PhysicalExaminationItemConfigDao physicalExaminationItemConfigDao;
  
  @Resource
  public void setBaseDao(PhysicalExaminationItemConfigDao physicalExaminationItemConfigDao) {
    super.setBaseDao(physicalExaminationItemConfigDao);
  }

  @Override
  public List<Map<String, Object>> findByConfigKey (ConfigKey configKey,
      Direction direction)
  {
    if(configKey!=null){
      List<Filter> filters = new ArrayList<Filter>();
      List<Ordering> orderings = new ArrayList<Ordering>();
      Filter keyFilter = new Filter("configKey", Operator.eq, configKey);
      Filter enableFilter = new Filter("isEnable",Operator.eq,true);
      filters.add(enableFilter);
      filters.add(keyFilter);
      if (direction!=null) {
        Ordering ordering = new Ordering();
        ordering.setProperty("configOrder");
        ordering.setDirection(direction);
        orderings.add(ordering);
     }
     List<PhysicalExaminationItemConfig> physicalExamtationItemConfigsConfigs = super.findList(null, filters, orderings,true,null);
     if (physicalExamtationItemConfigsConfigs == null || physicalExamtationItemConfigsConfigs.size() == 0) {
       filters.clear();
       filters.add(keyFilter);
       physicalExamtationItemConfigsConfigs = super.findList(null,filters,null,true,null);
       if (physicalExamtationItemConfigsConfigs == null || physicalExamtationItemConfigsConfigs.size() == 0) {
         filters.clear();
         filters.add(keyFilter);
         physicalExamtationItemConfigsConfigs = super.findList(null, filters, orderings);
       }else {
         return null;
       }
     }
     String[] propertys =
       {"id", "configValue","createDate"};
     return FieldFilterUtils.filterCollectionMap(propertys, physicalExamtationItemConfigsConfigs);
   }
   return null;
  }

  @Override
  public Page<PhysicalExaminationItemConfig> searchByConfigKey (
      ConfigKey configKey,Pageable pageable,Date beginDate,Date endDate,String searchItemName)
  {
    String startDateStr = null;
    String endDateStr = null;

    IKAnalyzer analyzer = new IKAnalyzer ();
    analyzer.setMaxWordLength (true);
    BooleanQuery query = new BooleanQuery ();

    QueryParser nameParser = new QueryParser (Version.LUCENE_35, "configValue",
        analyzer);
    Query nameQuery = null;
    
    TermRangeQuery rangeQuery = null;
    
    org.apache.lucene.search.Filter filter = null;
    if (beginDate != null)
    {
      startDateStr = DateTimeUtils.convertDateToString (beginDate, null);
    }
    if (endDate != null)
    {
      endDateStr = DateTimeUtils.convertDateToString (endDate, null);
    }
    if (searchItemName != null)
    {
      String text = QueryParser.escape (searchItemName);
        try
        {
          nameQuery = nameParser.parse (text);
          query.add (nameQuery, Occur.MUST);
          
          if (LogUtil.isDebugEnabled (FixedAssetsController.class))
          {
            LogUtil.debug (FixedAssetsController.class, "search", "Search searchItemName: "
                + searchItemName );
          }
        }
        catch (ParseException e)
        {
          e.printStackTrace();
        }
        
    }
    
    if (startDateStr != null || endDateStr != null)
    {
      rangeQuery = new TermRangeQuery ("createDate", startDateStr, endDateStr, true, true);
      query.add (rangeQuery,Occur.MUST);
      
      if (LogUtil.isDebugEnabled (FixedAssetsController.class))
      {
        LogUtil.debug (FixedAssetsController.class, "search", "Search start date: "+startDateStr
            +" end date: "+endDateStr);
      }
    }
    TermQuery isEnableQuery = new TermQuery (new Term ("isEnable", "true"));
    query.add (isEnableQuery,Occur.MUST);
    if (nameQuery != null || rangeQuery != null)
    {
      return this.search (query, pageable, analyzer,filter);
    }else {
      if(configKey!=null){
        List<Filter> filters = new ArrayList<Filter>();

        Filter keyFilter = new Filter("configKey", Operator.eq, configKey);
        Filter enableFilter = new Filter("isEnable",Operator.eq,true);
        filters.add(enableFilter);
        filters.add(keyFilter);

        pageable.setFilters (filters);
       Page<PhysicalExaminationItemConfig> physicalExamtationItemConfigsPage = super.findPage (pageable, true);
       if (physicalExamtationItemConfigsPage.getRows () == null || physicalExamtationItemConfigsPage.getRows ().size() == 0) {
         filters.clear();
         filters.add(keyFilter);
         physicalExamtationItemConfigsPage = super.findPage (pageable, true);
         if (physicalExamtationItemConfigsPage.getRows () == null || physicalExamtationItemConfigsPage.getRows ().size() == 0) {
           filters.clear();
           filters.add(keyFilter);
           physicalExamtationItemConfigsPage = super.findPage (pageable);
         }else {
           return null;
         }
       }
       return physicalExamtationItemConfigsPage;
     }
      
      return null;
    }
    
  }

}
