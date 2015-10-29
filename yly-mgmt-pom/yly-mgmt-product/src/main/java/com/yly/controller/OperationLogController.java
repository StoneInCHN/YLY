package com.yly.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yly.beans.Message;
import com.yly.controller.base.BaseController;
import com.yly.entity.OperationLog;
import com.yly.framework.filter.Filter;
import com.yly.framework.filter.Filter.Operator;
import com.yly.framework.paging.Page;
import com.yly.framework.paging.Pageable;
import com.yly.service.OperationLogService;



@Controller
@RequestMapping("/console/operationLog")
public class OperationLogController extends BaseController {

  @Resource(name = "operationLogServiceImpl")
  private OperationLogService operationLogService;

  /**
   * 列表
   */
  @RequestMapping (value = "/operationLog", method = RequestMethod.GET)
  public String list (ModelMap model)
  {
    return "operationLog/operationLog";
  }
  @RequestMapping(value = "/list", method = RequestMethod.POST)
  public @ResponseBody Page<OperationLog> list(Pageable pageable, Date beginDate, Date endDate,ModelMap model) {
    
    List<Filter> filters = new ArrayList<Filter>();
    
    if(beginDate != null){
      Filter dateGeFilter = new Filter("createDate",Operator.ge,beginDate);
      filters.add(dateGeFilter);
    }
    if(endDate != null){
      Filter dateLeFilter = new Filter("createDate",Operator.le,endDate);
      filters.add(dateLeFilter);
    }
    pageable.setFilters(filters);
    
    return operationLogService.findPage(pageable);
   
  }

  /**
   * 查看
   */
  @RequestMapping(value = "/view", method = RequestMethod.GET)
  public String view(Long id, ModelMap model) {
    model.addAttribute("log", operationLogService.find(id));
    return "/operationLog/view";
  }

  /**
   * 删除
   */
  @RequestMapping(value = "/delete", method = RequestMethod.POST)
  public @ResponseBody Message delete(Long[] ids) {
    operationLogService.delete(ids);
    return SUCCESS_MESSAGE;
  }
}
