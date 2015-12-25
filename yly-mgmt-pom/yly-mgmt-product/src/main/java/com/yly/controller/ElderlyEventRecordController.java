package com.yly.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yly.beans.Message;
import com.yly.controller.base.BaseController;
import com.yly.entity.ElderlyEventRecord;
import com.yly.entity.ElderlyInfo;
import com.yly.framework.paging.Page;
import com.yly.framework.paging.Pageable;
import com.yly.service.ElderlyEventRecordService;
import com.yly.service.ElderlyInfoService;
import com.yly.service.TenantAccountService;
import com.yly.utils.FieldFilterUtils;
import com.yly.utils.ToolsUtils;

/**
 * 老人事件controller
 * 
 * @author luzhang
 *
 */
@Controller("elderlyEventRecordController")
@RequestMapping("/console/elderlyEventRecord")
public class ElderlyEventRecordController extends BaseController {
 
  @Resource(name = "elderlyEventRecordServiceImpl")
  private ElderlyEventRecordService elderlyEventRecordService;

  @Resource(name = "elderlyInfoServiceImpl")
  private ElderlyInfoService elderlyInfoService;

  @Resource(name = "tenantAccountServiceImpl")
  private TenantAccountService tenantAccountService;

  /**
   * 列表页面
   * 
   * @param model
   * @return
   */
  @RequestMapping(value = "/elderlyEventRecord", method = RequestMethod.GET)
  public String elderlyEventRecord(ModelMap model) {
    return "/elderlyEventRecord/elderlyEventRecord";
  }

  /**
   * 查询list
   * 
   * @param pageable
   * @return
   */
  @RequestMapping(value = "/list", method = RequestMethod.POST)
  public @ResponseBody Page<ElderlyEventRecord> list(String keysOfElderlyName, String elderlyEventType, Date beginDate,
      Date endDate, Pageable pageable) {
    //返回到页面的隐藏域XXXHidden，表示已经查询出来的结果
    if (keysOfElderlyName == null && elderlyEventType == null && beginDate == null && endDate == null) {
      return elderlyEventRecordService.findPage(pageable, true);
    } else {
      return elderlyEventRecordService.searchPageByFilter(keysOfElderlyName, elderlyEventType, beginDate, endDate,
          pageable);
    }
  }

  @RequestMapping(value = "/findAll", method = RequestMethod.POST)
  public @ResponseBody List<Map<String, Object>> findAll() {
    String[] propertys = {"id", "buildingName"};
    return FieldFilterUtils.filterCollectionMap(propertys, elderlyEventRecordService.findAll(true));
  }


  /**
   * 编辑
   * 
   * @param model
   * @param id
   * @return
   */
  @RequestMapping(value = "/detail", method = RequestMethod.GET)
  public String edit(ModelMap model, Long id, String handle) {
    if (id != null && handle != null) {
      model.addAttribute("elderlyEventRecord", elderlyEventRecordService.find(id));
      return "elderlyEventRecord/" + handle;
    }
    return "";
  }

  /**
   * 添加
   * 
   * @param elderlyEventRecord
   * @return
   */
  @RequestMapping(value = "/add", method = RequestMethod.POST)
  public @ResponseBody Message save(ElderlyEventRecord elderlyEventRecord, Long elderlyInfoID) {
    ElderlyInfo elderlyInfo = elderlyInfoService.find(elderlyInfoID);
    if (elderlyInfo != null && elderlyEventRecord != null) {
      elderlyEventRecord.setEventDate(ToolsUtils.addTime(elderlyEventRecord.getEventDate(),
          Calendar.HOUR, 8));// 加8个小时
      elderlyEventRecord.setElderlyInfo(elderlyInfo);
      elderlyEventRecord.setTenantID(tenantAccountService.getCurrentTenantID());
      if (elderlyEventRecord.getEventContent() != null) {
        elderlyEventRecordService.save(elderlyEventRecord);
        return SUCCESS_MESSAGE;
      }
    }
    return ERROR_MESSAGE;
  }

  /**
   * 更新
   * 
   * @param elderlyEventRecord
   * @param elderlyInfoID
   * @return
   */
  @RequestMapping(value = "/update", method = RequestMethod.POST)
  public @ResponseBody Message update(ElderlyEventRecord elderlyEventRecord, Long elderlyInfoID) {
    ElderlyInfo elderlyInfo = elderlyInfoService.find(elderlyInfoID);
    if (elderlyInfo != null) {
      elderlyEventRecord.setElderlyInfo(elderlyInfo);
      elderlyEventRecord.setTenantID(tenantAccountService.getCurrentTenantID());
      elderlyEventRecordService.update(elderlyEventRecord, "createDate");
      return SUCCESS_MESSAGE;
    }
    return ERROR_MESSAGE;
  }



  /**
   * 删除
   * 
   * @param ids
   * @return
   */
  @RequestMapping(value = "/delete", method = RequestMethod.POST)
  public @ResponseBody Message delete(Long[] ids) {
    if (ids != null) {
      for (Long id : ids) {
        ElderlyEventRecord elderlyEventRecord = elderlyEventRecordService.find(id);
        if (elderlyEventRecord != null) {
          elderlyEventRecordService.delete(ids);
        }
      }
    }
    return SUCCESS_MESSAGE;
  }

}
