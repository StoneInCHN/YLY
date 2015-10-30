package com.yly.controller;

import java.math.BigDecimal;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yly.beans.Message;
import com.yly.controller.base.BaseController;
import com.yly.entity.WaterElectricityRecord;
import com.yly.framework.paging.Page;
import com.yly.framework.paging.Pageable;
import com.yly.service.RoomService;
import com.yly.service.TenantAccountService;
import com.yly.service.WaterElectricityRecordService;

/**
 * Controller - 水电用量记录
 * 
 * @author pengyanan
 *
 */
@Controller("waterElectricityRecord")
@RequestMapping("console/waterElectricityRecord")
public class WaterElectricityRecordController extends BaseController {
  @Resource(name = "waterElectricityRecordServiceImpl")
  private WaterElectricityRecordService waterElectricityRecordService;

  @Resource(name = "tenantAccountServiceImpl")
  private TenantAccountService tenantAccountService;

  @Resource(name = "roomServiceImpl")
  private RoomService roomService;

  /**
   * 页面展示
   * 
   * @param model
   * @return
   */
  @RequestMapping(value = "/waterElectricityRecord", method = RequestMethod.GET)
  public String list(Model model) {
    return "/waterElectricityRecord/waterElectricityRecord";
  }

  /**
   * 获取列表
   * 
   * @param pageable
   * @return
   */
  @RequestMapping(value = "/list", method = RequestMethod.POST)
  public @ResponseBody Page<WaterElectricityRecord> list(Pageable pageable, Date beginDate,
      Date endDate, WaterElectricityRecord waterElectricityRecord) {
    if (waterElectricityRecord != null
        && waterElectricityRecord.getRoom() != null
        && (waterElectricityRecord.getRoom().getRoomName() != null
            || waterElectricityRecord.getRoom().getRoomNumber() != null
            || waterElectricityRecord.getOperator() != null || beginDate != null || endDate != null)) {
      return waterElectricityRecordService.searchListByFilter(pageable, beginDate, endDate,
          waterElectricityRecord);
    }
    return waterElectricityRecordService.findPage(pageable);
  }

  /**
   * 添加
   * 
   * @param waterElectricityRecord
   * @return
   */
  @RequestMapping(value = "/add", method = RequestMethod.POST)
  public @ResponseBody Message add(WaterElectricityRecord waterElectricityRecord, Long roomId) {
    if (waterElectricityRecord != null && roomId != null) {
      // 计算计费用水量，用电量 -START
      BigDecimal waterAcutal =
          waterElectricityRecord.getWaterCount().subtract(waterElectricityRecord.getWaterDerate());
      BigDecimal electricityActual =
          waterElectricityRecord.getElectricityCount().subtract(
              waterElectricityRecord.getElectricityDerate());
      waterAcutal = waterAcutal.compareTo(BigDecimal.ZERO) > 0 ? waterAcutal : BigDecimal.ZERO;
      electricityActual =
          electricityActual.compareTo(BigDecimal.ZERO) > 0 ? electricityActual : BigDecimal.ZERO;
      // 计算计费用水量，用电量 -END
      waterElectricityRecord.setWaterActual(waterAcutal);
      waterElectricityRecord.setElectricityActual(electricityActual);
      waterElectricityRecord.setTenantID(tenantAccountService.getCurrentTenantID());
      waterElectricityRecord.setRoom(roomService.find(roomId));
      waterElectricityRecordService.save(waterElectricityRecord);
      return SUCCESS_MESSAGE;
    } else {
      return ERROR_MESSAGE;
    }
  }

  /**
   * 详情
   * 
   * @param model
   * @param id
   * @return
   */
  @RequestMapping(value = "/details", method = RequestMethod.GET)
  public String details(Model model, Long id) {
    if (id != null) {
      model.addAttribute("waterElectricityRecord", waterElectricityRecordService.find(id));
    }
    return "/waterElectricityRecord/details";
  }

  /**
   * 编辑
   * 
   * @param model
   * @param id
   * @return
   */
  @RequestMapping(value = "/edit", method = RequestMethod.GET)
  public String edit(Model model, Long id) {
    if (id != null) {
      model.addAttribute("waterElectricityRecord", waterElectricityRecordService.find(id));
    }
    return "/waterElectricityRecord/edit";
  }

  /**
   * 更新
   * 
   * @param waterElectricityRecord
   * @param roomId
   * @return
   */
  @RequestMapping(value = "/update", method = RequestMethod.POST)
  public @ResponseBody Message update(WaterElectricityRecord waterElectricityRecord, Long roomId) {
    if (waterElectricityRecord != null && roomId != null) {
      // 计算计费用水量，用电量 -START
      BigDecimal waterAcutal =
          waterElectricityRecord.getWaterCount().subtract(waterElectricityRecord.getWaterDerate());
      BigDecimal electricityActual =
          waterElectricityRecord.getElectricityCount().subtract(
              waterElectricityRecord.getElectricityDerate());
      waterAcutal = waterAcutal.compareTo(BigDecimal.ZERO) > 0 ? waterAcutal : BigDecimal.ZERO;
      electricityActual =
          electricityActual.compareTo(BigDecimal.ZERO) > 0 ? electricityActual : BigDecimal.ZERO;
      // 计算计费用水量，用电量 -END
      waterElectricityRecord.setWaterActual(waterAcutal);
      waterElectricityRecord.setElectricityActual(electricityActual);
      waterElectricityRecord.setRoom(roomService.find(roomId));
      waterElectricityRecordService.update(waterElectricityRecord);
      return SUCCESS_MESSAGE;
    } else {
      return ERROR_MESSAGE;
    }
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
      waterElectricityRecordService.delete(ids);
      return SUCCESS_MESSAGE;
    } else {
      return ERROR_MESSAGE;
    }

  }
}
