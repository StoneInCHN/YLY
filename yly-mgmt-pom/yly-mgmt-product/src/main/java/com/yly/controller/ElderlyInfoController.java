package com.yly.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yly.controller.base.BaseController;
import com.yly.entity.ElderlyInfo;
import com.yly.entity.commonenum.CommonEnum.DeleteStatus;
import com.yly.framework.filter.Filter;
import com.yly.framework.filter.Filter.Operator;
import com.yly.framework.paging.Page;
import com.yly.framework.paging.Pageable;
import com.yly.json.request.ElderlyInfoSearchRequest;
import com.yly.service.ElderlyInfoService;

/**
 * 老人信息
 * 
 * @author shijun
 *
 */
@Controller("elderlyInfoController")
@RequestMapping("console/elderlyInfo")
public class ElderlyInfoController extends BaseController {

  @Resource(name = "elderlyInfoServiceImpl")
  private ElderlyInfoService elderlyInfoService;

  /**
   * 列表页面
   * 
   * @param model
   * @return
   */
  @RequestMapping(value = "/checkedInElderly", method = RequestMethod.GET)
  public String list(ModelMap model) {
    return "/checkedInElderly/checkedInElderly";
  }
  

  /**
   * 老人信息公共搜索页面
   * 
   * @param model
   * @return
   */
  @RequestMapping(value = "/commonElderlySearch", method = RequestMethod.GET)
  public String elderlyInfoSearch(ModelMap model) {
    return "/common/commonElderlySearch";
  }

  /**
   * 查询在院老人记录
   * 
   * @param beginDate
   * @param endDate
   * @param pageable
   * @param model
   * @return
   */
  @RequestMapping(value = "/list", method = RequestMethod.POST)
  public @ResponseBody Page<ElderlyInfo> list(Date beHospitalizedBeginDate,
      Date beHospitalizedEndDate, ElderlyInfo elderlyInfo, Pageable pageable, ModelMap model) {

    if (elderlyInfo.getIdentifier() != null || elderlyInfo.getName() != null
        || elderlyInfo.getElderlyStatus() != null || beHospitalizedBeginDate != null
        || beHospitalizedEndDate != null) {
      return elderlyInfoService.searchElderlyInfo(beHospitalizedBeginDate, beHospitalizedEndDate,
          elderlyInfo, pageable);
    }

    List<Filter> filters = new ArrayList<Filter>();
    Filter delFilter = new Filter("deleteStatus", Operator.eq, DeleteStatus.NOT_DELETED);
    filters.add(delFilter);
    pageable.setFilters(filters);

    return elderlyInfoService.findPage(pageable, true);
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
    ElderlyInfo elderlyInfo = elderlyInfoService.find(id);
    model.addAttribute("elderlyInfo", elderlyInfo);
    return "checkedInElderly/details";
  }
  /**
   * 导出数据前，计算当前呈现给用户的有多少条数据
   * @return
   */
  @RequestMapping(value = "/count", method = RequestMethod.POST)
  public @ResponseBody Map<String, Long> count(ElderlyInfoSearchRequest elderlyInfoSearch) {
    Long count = new Long(0);
    elderlyInfoSearch.setDeleteStatus(DeleteStatus.NOT_DELETED.toString());//没有删除
    count = new Long(elderlyInfoService.countByFilter(elderlyInfoSearch));
    Map<String, Long> countMap = new HashMap<String, Long>(); 
    countMap.put("count", count);
    return countMap;
  }
  /**
   * 导出列表数据，即用户已经查询出来的数据
   * @param withDays
   */
  @RequestMapping(value = "/exportData", method = {RequestMethod.GET,RequestMethod.POST})
  public void exportData(HttpServletResponse response,  ElderlyInfoSearchRequest elderlyInfoSearch) {
    elderlyInfoSearch.setDeleteStatus(DeleteStatus.NOT_DELETED.toString());//没有删除
    List<ElderlyInfo> elderlyInfoList = elderlyInfoService.searchListByFilter(elderlyInfoSearch);
    if (elderlyInfoList != null && elderlyInfoList.size() > 0) {
      String title = "Elderly Information"; // 工作簿标题，同时也是excel文件名前缀
      String[] headers = {"identifier", "name", "age", "elderlyPhoneNumber", "beHospitalizedDate", "gender", "IDCard", "bedLocation", "elderlyConsigner.consignerPhoneNumber", "elderlyStatus"}; // 需要导出的字段
      String[] headersName = {"编号", "老人姓名", "年龄", "老人电话", "入院时间", "性别", "身份证号码", "房间", "委托人电话", "老人状态"}; // 字段对应列的列名
      // 导出数据到Excel
      List<Map<String, String>> elderlyInfoMapList = elderlyInfoService.prepareMap(elderlyInfoList);
      if (elderlyInfoMapList.size() > 0) {
        exportListToExcel(response, elderlyInfoMapList, title, headers, headersName);
      }
    }
  } 
   
}
