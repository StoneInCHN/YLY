package com.yly.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.Filter;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermRangeQuery;
import org.apache.lucene.util.Version;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.yly.beans.Message;
import com.yly.common.log.LogUtil;
import com.yly.controller.base.BaseController;
import com.yly.entity.PhysicalExaminationItemConfig;
import com.yly.entity.commonenum.CommonEnum.ConfigKey;
import com.yly.framework.ordering.Ordering.Direction;
import com.yly.framework.paging.Page;
import com.yly.framework.paging.Pageable;
import com.yly.service.ElderlyInfoService;
import com.yly.service.PhysicalExaminationItemConfigService;
import com.yly.utils.DateTimeUtils;

/**
 * 体检项
 * @author huyong
 *
 */
@Controller ("PhysicalExaminationItemConfigController")
@RequestMapping ("console/physicalExaminationItemConfig")
public class PhysicalExaminationItemConfigController extends BaseController
{

  @Resource (name = "physicalExaminationItemConfigServiceImpl")
  private PhysicalExaminationItemConfigService physicalExaminationItemConfigService;
  @Resource(name="elderlyInfoServiceImpl")
  private ElderlyInfoService elderlyInfoService;

  @RequestMapping (value = "/physicalExaminationItemConfig", method = RequestMethod.GET)
  public String list (ModelMap model)
  {
    return "physicalExaminationItemConfig/physicalExaminationItemConfig";
  }
  @RequestMapping (value = "/searchItems", method = RequestMethod.GET)
  public String searchItems (ModelMap model)
  {
    return "physicalExaminationItemConfig/examItemsList";
  }

  @RequestMapping (value = "/list", method = RequestMethod.POST)
  public @ResponseBody Page<PhysicalExaminationItemConfig> list (String name,Date beginDate, Date endDate, 
      Pageable pageable, ModelMap model
      )
  {
    String startDateStr = null;
    String endDateStr = null;

    IKAnalyzer analyzer = new IKAnalyzer ();
    analyzer.setMaxWordLength (true);
    BooleanQuery query = new BooleanQuery ();

    QueryParser nameParser = new QueryParser (Version.LUCENE_35, "elderlyInfo.name",
        analyzer);
    Query nameQuery = null;
    TermRangeQuery rangeQuery = null;
    Filter filter = null;
    if (beginDate != null)
    {
      startDateStr = DateTimeUtils.convertDateToString (beginDate, null);
    }
    if (endDate != null)
    {
      endDateStr = DateTimeUtils.convertDateToString (endDate, null);
    }
    if (name != null)
    {
      String text = QueryParser.escape (name);
        try
        {
          nameQuery = nameParser.parse (text);
          query.add (nameQuery, Occur.MUST);
          
          if (LogUtil.isDebugEnabled (FixedAssetsController.class))
          {
            LogUtil.debug (FixedAssetsController.class, "search", "Search assetName: "
                + name );
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
    if (nameQuery != null || rangeQuery != null)
    {
      return physicalExaminationItemConfigService.search (query, pageable, analyzer,filter);
    }
    return physicalExaminationItemConfigService.findPage (pageable);
  }

  /**
   * get data for vendor edit page
   * 
   * @param model
   * @param vendorId
   * @return
   */
  @RequestMapping (value = "/edit", method = RequestMethod.GET)
  public String edit (ModelMap model, Long id)
  {
    model.put ("physicalExaminationItemConfig", physicalExaminationItemConfigService.find (id));
    return "physicalExaminationItemConfig/edit";
  }

  @RequestMapping (value = "/add", method = RequestMethod.POST)
  public @ResponseBody Message add (PhysicalExaminationItemConfig physicalExaminationItemConfig)
  {
    physicalExaminationItemConfig.setConfigKey (ConfigKey.PHYSICALEXAMITEM);
    physicalExaminationItemConfigService.save (physicalExaminationItemConfig,true);
    return SUCCESS_MESSAGE;
  }

  @RequestMapping (value = "/update", method = RequestMethod.POST)
  public @ResponseBody Message update (PhysicalExaminationItemConfig physicalExaminationItemConfig)
  {
    physicalExaminationItemConfigService.update (physicalExaminationItemConfig,"createDate","configKey");
    return SUCCESS_MESSAGE;
  }

 

  /**
   * 删除
   */
  @RequestMapping (value = "/delete", method = RequestMethod.POST)
  public @ResponseBody Message delete (Long[] ids)
  {
    if (ids != null)
    {
      // 检查是否能被删除
      // if()
      physicalExaminationItemConfigService.delete (ids);
    }
    return SUCCESS_MESSAGE;
  }
  /**
   * 获取数据进入详情页面
   * 
   * @param model
   * @param id
   * @return
   */
  @RequestMapping(value = "/details", method = RequestMethod.GET)
  public String details(ModelMap model, Long id) {
    PhysicalExaminationItemConfig physicalExaminationItemConfig = physicalExaminationItemConfigService.find(id);
    model.addAttribute("physicalExaminationItemConfig", physicalExaminationItemConfig);
    return "physicalExaminationItemConfig/details";
  }
  /**
   * 根据configKey查询
   * @param configKey
   * @param direction
   * @return
   */
  @RequestMapping(value = "/findByConfigKey", method = RequestMethod.POST)
  public @ResponseBody List<Map<String, Object>> findByConfigKey(ConfigKey configKey,Direction direction) {
    
    return physicalExaminationItemConfigService.findByConfigKey(configKey, direction);
  }
  
  @RequestMapping (value = "/searchByConfigKey", method = RequestMethod.POST)
  public @ResponseBody Page<PhysicalExaminationItemConfig> searchByConfigKey (ConfigKey configKey,Date beginDate, Date endDate, 
      Pageable pageable, ModelMap model,String searchItemName)
  {
   return physicalExaminationItemConfigService.searchByConfigKey (configKey, pageable, beginDate, endDate, searchItemName);
  }
}
