package com.yly.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.lucene.index.Term;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.Filter;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TermRangeQuery;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.util.Version;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.yly.beans.Message;
import com.yly.beans.FileInfo.FileType;
import com.yly.common.log.LogUtil;
import com.yly.controller.base.BaseController;
import com.yly.entity.Department;
import com.yly.entity.ElderlyInfo;
import com.yly.entity.Position;
import com.yly.entity.TenantUser;
import com.yly.framework.paging.Page;
import com.yly.framework.paging.Pageable;
import com.yly.service.DepartmentService;
import com.yly.service.FileService;
import com.yly.service.PositionService;
import com.yly.service.TenantUserService;
import com.yly.utils.DateTimeUtils;

/**
 * 租户用户
 * @author huyong
 *
 */
@Controller ("tenantUserController")
@RequestMapping ("console/tenantUser")
public class TenantUserController extends BaseController
{

  @Resource (name = "tenantUserServiceImpl")
  private TenantUserService tenantUserService;
  @Resource(name = "departmentServiceImpl")
  private DepartmentService departmentService;
  @Resource(name = "positionServiceImpl")
  private PositionService positionService;
  
  @Resource(name = "fileServiceImpl")
  private FileService fileService;
  
  @RequestMapping (value = "/tenantUser", method = RequestMethod.GET)
  public String list (ModelMap model)
  {
    return "tenantUser/tenantUser";
  }
  @RequestMapping (value = "/commonTenantUserSearch", method = RequestMethod.GET)
  public String commonTenantUserSearch (ModelMap model)
  {
    return "common/commonTenantUserSearch";
  }
  @RequestMapping (value = "/list", method = RequestMethod.POST)
  public @ResponseBody Page<TenantUser> list (Pageable pageable, ModelMap model,
      Date beginDate, Date endDate, String realNameSearch, String staffStatusSearch,
      String departmentSearchId, String positionSearchId)
  {
    String startDateStr = null;
    String endDateStr = null;

    IKAnalyzer analyzer = new IKAnalyzer ();
    analyzer.setMaxWordLength (true);
    BooleanQuery query = new BooleanQuery ();

    QueryParser nameParser = new QueryParser (Version.LUCENE_35, "realName",
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
    if (realNameSearch != null)
    {
      String text = QueryParser.escape (realNameSearch);
        try
        {
          nameQuery = nameParser.parse (text);
          query.add (nameQuery, Occur.MUST);
          
          if (LogUtil.isDebugEnabled (FixedAssetsController.class))
          {
            LogUtil.debug (FixedAssetsController.class, "search", "Search real name: "
                + realNameSearch );
          }
        }
        catch (ParseException e)
        {
          e.printStackTrace();
        }
        
    }
    //过滤部门
    if (departmentSearchId != null)
    {
      TermQuery departmentQuery = new TermQuery(new Term("department.id", departmentSearchId));  
      query.add (departmentQuery,Occur.MUST);
    }
    //过滤职位
    if (positionSearchId != null)
    {
      TermQuery positionQuery = new TermQuery(new Term("position.id", positionSearchId));  
      query.add (positionQuery,Occur.MUST);
    }
    //过滤状态
    if (staffStatusSearch != null)
    {
      TermQuery statusQuery = new TermQuery(new Term("staffStatus", staffStatusSearch));  
      query.add (statusQuery,Occur.MUST);
    }
    if (startDateStr != null || endDateStr != null)
    {
      rangeQuery = new TermRangeQuery ("hireDate", startDateStr, endDateStr, true, true);
      query.add (rangeQuery,Occur.MUST);
      
      if (LogUtil.isDebugEnabled (FixedAssetsController.class))
      {
        LogUtil.debug (FixedAssetsController.class, "search", "Search start date: "+startDateStr
            +" end date: "+endDateStr);
      }
    }
    if (nameQuery != null || rangeQuery != null || 
        departmentSearchId != null || positionSearchId != null || staffStatusSearch != null)
    {
      return tenantUserService.search (query, pageable, analyzer,filter);
    }
      return tenantUserService.findPage (pageable);
    
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
    model.put ("tenantUser", tenantUserService.find (id));
    return "tenantUser/edit";
  }

  @RequestMapping (value = "/add", method = RequestMethod.POST)
  public @ResponseBody Message add (TenantUser tenantUser,Long departmentId, Long positionId)
  {
    Department department = departmentService.find (departmentId);
    Position position = positionService.find (positionId);
    tenantUser.setDepartment (department);
    tenantUser.setPosition (position);
    tenantUserService.save (tenantUser,true);
    return SUCCESS_MESSAGE;
  }

  @RequestMapping (value = "/update", method = RequestMethod.POST)
  public @ResponseBody Message update (TenantUser tenantUser,Long departmentId, Long positionId)
  {
    Department department = departmentService.find (departmentId);
    Position position = positionService.find (positionId);
    tenantUser.setDepartment (department);
    tenantUser.setPosition (position);
    tenantUserService.update (tenantUser,"photo");
    return SUCCESS_MESSAGE;
  }
  @RequestMapping(value = "/uploadPhoto", method = RequestMethod.POST)
  public @ResponseBody Message uploadPhoto(@RequestParam("file") MultipartFile file,
      String staffID, Long tenantUserId) {
    Map<String, String> paramMap = new HashMap<String, String>();
    paramMap.put("staffID", staffID);
//    String filePath = fileService.upload(FileType.PROFILE_PICTURE, file, identifier);
    String filePath = fileService.upload(FileType.PROFILE_PICTURE, file, paramMap);
    if (filePath != null && tenantUserId != null) {
      TenantUser tenantUser = tenantUserService.find(tenantUserId);
      tenantUser.setPhoto (filePath);
      tenantUserService.update(tenantUser);
      return Message.success(filePath);
    } else {
      return ERROR_MESSAGE;
    }

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
      tenantUserService.delete (ids);
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
    TenantUser tenantUser = tenantUserService.find(id);
    model.addAttribute("tenantUser", tenantUser);
    return "tenantUser/details";
  }
}
