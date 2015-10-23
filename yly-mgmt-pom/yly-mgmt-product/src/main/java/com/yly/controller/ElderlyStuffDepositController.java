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
import com.yly.entity.ElderlyStuffDeposit;
import com.yly.entity.ElderlyInfo;
import com.yly.framework.paging.Page;
import com.yly.framework.paging.Pageable;
import com.yly.json.request.StuffDepositSearchRequest;
import com.yly.service.ElderlyStuffDepositService;
import com.yly.service.ElderlyInfoService;
import com.yly.utils.FieldFilterUtils;
import com.yly.utils.ToolsUtils;

/**
 * 物品寄存controller
 * 
 * @author luzhang
 *
 */
@Controller("elderlyStuffDepositController")
@RequestMapping("/console/elderlyStuffDeposit")
public class ElderlyStuffDepositController extends BaseController {

  @Resource(name = "elderlyStuffDepositServiceImpl")
  private ElderlyStuffDepositService elderlyStuffDepositService;

  @Resource(name = "elderlyInfoServiceImpl")
  private ElderlyInfoService elderlyInfoService;

  /**
   * 列表页面
   * 
   * @param model
   * @return
   */
  @RequestMapping(value = "/elderlyStuffDeposit", method = RequestMethod.GET)
  public String elderlyStuffDeposit(ModelMap model) {
    return "/elderlyStuffDeposit/elderlyStuffDeposit";
  }

  /**
   * 
   * @param pageable
   * @return
   */
  @RequestMapping(value = "/list", method = RequestMethod.POST)
  public @ResponseBody Page<ElderlyStuffDeposit> list(StuffDepositSearchRequest searchParameter,
      Pageable pageable) {
    if (searchParameter == null) {
      searchParameter = new StuffDepositSearchRequest();
    }
    if (ToolsUtils.checkObjAllFieldNull(searchParameter)) {
      return elderlyStuffDepositService.findPage(pageable, false);
    } else {
      return elderlyStuffDepositService.searchPageByFilter(searchParameter, pageable);
    }
  }

  @RequestMapping(value = "/findAll", method = RequestMethod.POST)
  public @ResponseBody List<Map<String, Object>> findAll() {
    String[] propertys = {"id", "buildingName"};
    return FieldFilterUtils
        .filterCollectionMap(propertys, elderlyStuffDepositService.findAll(true));
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
      model.addAttribute("elderlyStuffDeposit", elderlyStuffDepositService.find(id));
      return "elderlyStuffDeposit/" + handle;
    }
    return "";
  }

  /**
   * 添加
   * 
   * @param elderlyStuffDeposit
   * @return
   */
  @RequestMapping(value = "/add", method = RequestMethod.POST)
  public @ResponseBody Message save(ElderlyStuffDeposit elderlyStuffDeposit, Long elderlyInfoID) {
    ElderlyInfo elderlyInfo = elderlyInfoService.find(elderlyInfoID);
    if (elderlyInfo != null && elderlyStuffDeposit != null) {
      Date putinDate = elderlyStuffDeposit.getPutinDate();
      if (putinDate != null) {
        elderlyStuffDeposit.setPutinDate(ToolsUtils.addTime(putinDate, Calendar.HOUR, 8));// 加8个小时
      }
      Date takeAwayDate = elderlyStuffDeposit.getTakeAlwayDate();
      if (takeAwayDate != null) {
        elderlyStuffDeposit.setTakeAlwayDate(ToolsUtils.addTime(takeAwayDate, Calendar.HOUR, 8));// 加8个小时
      }
      elderlyStuffDeposit.setElderlyInfo(elderlyInfo);
      if (elderlyStuffDeposit.getName() != null) {
        elderlyStuffDepositService.save(elderlyStuffDeposit);
        return SUCCESS_MESSAGE;
      }
    }
    return ERROR_MESSAGE;
  }

  /**
   * 更新
   * 
   * @param elderlyStuffDeposit
   * @param elderlyInfoID
   * @return
   */
  @RequestMapping(value = "/update", method = RequestMethod.POST)
  public @ResponseBody Message update(ElderlyStuffDeposit elderlyStuffDeposit, Long elderlyInfoID) {
    ElderlyInfo elderlyInfo = elderlyInfoService.find(elderlyInfoID);
    if (elderlyInfo != null) {
      elderlyStuffDeposit.setElderlyInfo(elderlyInfo);
      elderlyStuffDepositService.update(elderlyStuffDeposit, "createDate");
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
        ElderlyStuffDeposit elderlyStuffDeposit = elderlyStuffDepositService.find(id);
        if (elderlyStuffDeposit != null) {
          elderlyStuffDepositService.delete(ids);
        }
      }
    }
    return SUCCESS_MESSAGE;
  }


}
