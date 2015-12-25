package com.yly.controller;

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

import com.yly.beans.Message;
import com.yly.controller.base.BaseController;
import com.yly.entity.BookingRegistration;
import com.yly.entity.ConsultationRecord;
import com.yly.entity.SystemConfig;
import com.yly.entity.commonenum.CommonEnum.ConfigKey;
import com.yly.framework.paging.Page;
import com.yly.framework.paging.Pageable;
import com.yly.json.request.BookingRegistrationSearchRequest;
import com.yly.json.request.ConsultationRecordSearchRequest;
import com.yly.service.BookingRegistrationService;
import com.yly.service.SystemConfigService;
import com.yly.service.TenantAccountService;

/**
 * 预约登记
 * 
 * @author shijun
 *
 */
@Controller("bookingRegistrationController")
@RequestMapping("/console/bookingRegistration")
public class BookingRegistrationController extends BaseController {



  @Resource(name = "bookingRegistrationServiceImpl")
  private BookingRegistrationService bookingRegistrationService;

  @Resource(name = "tenantAccountServiceImpl")
  private TenantAccountService tenantAccountService;

  @Resource(name = "systemConfigServiceImpl")
  private SystemConfigService systemConfigService;


  /**
   * 列表页面
   * 
   * @param model
   * @return
   */
  @RequestMapping(value = "/bookingRegistration", method = RequestMethod.GET)
  public String list(ModelMap model) {
    return "/bookingRegistration/bookingRegistration";
  }

  /**
   * 查询探望记录
   * 
   * @param beginDate
   * @param endDate
   * @param pageable
   * @param model
   * @return
   */
  @RequestMapping(value = "/list", method = RequestMethod.POST)
  public @ResponseBody Page<BookingRegistration> list(Date bookingCheckInDateBeginDate,
      Date bookingCheckInDateEndDate, BookingRegistration bookingRegistration,
      Long searchRoomTypeId, Pageable pageable, ModelMap model) {
    
    SystemConfig intentRoomType = systemConfigService.find(searchRoomTypeId);
    
    bookingRegistration.setIntentRoomType(intentRoomType);
    if (bookingCheckInDateBeginDate != null || bookingCheckInDateEndDate != null
        || bookingRegistration.getPeopleWhoBooked() != null
        || bookingRegistration.getElderlyName() != null || intentRoomType != null) {
      
      return bookingRegistrationService.searchBookingRegistration(bookingCheckInDateBeginDate,
          bookingCheckInDateEndDate, bookingRegistration, searchRoomTypeId, pageable);
    }
    return bookingRegistrationService.findPage(pageable, true);
  }

  /**
   * 添加
   * 
   * @param bookingRegistration
   * @return
   */
  @RequestMapping(value = "/add", method = RequestMethod.POST)
  public @ResponseBody Message add(Long roomTypeId, BookingRegistration bookingRegistration) {

    SystemConfig roomType = systemConfigService.find(roomTypeId);

    if (bookingRegistration != null) {
      bookingRegistration.setTenantID(tenantAccountService.getCurrentTenantID());
      bookingRegistration.setIntentRoomType(roomType);
      bookingRegistrationService.save(bookingRegistration);
    }
    return SUCCESS_MESSAGE;
  }

  /**
   * 获取数据进入编辑页面
   * 
   * @param model
   * @param id
   * @return
   */
  @RequestMapping(value = "/edit", method = RequestMethod.GET)
  public String edit(ModelMap model, Long id) {
    BookingRegistration bookingRegistration = bookingRegistrationService.find(id);
    model.addAttribute("systemConfigs",
        systemConfigService.findByConfigKey(ConfigKey.ROOMTYPE, null));
    model.addAttribute("bookingRegistration", bookingRegistration);
    return "bookingRegistration/edit";
  }

  /**
   * 更新
   * 
   * @param bookingRegistration
   * @return
   */
  @RequestMapping(value = "/update", method = RequestMethod.POST)
  public @ResponseBody Message update(Long roomTypeId, BookingRegistration bookingRegistration) {
    SystemConfig roomType = systemConfigService.find(roomTypeId);
    bookingRegistration.setIntentRoomType(roomType);
    bookingRegistrationService.update(bookingRegistration);
    return SUCCESS_MESSAGE;
  }

  /**
   * 删除
   */
  @RequestMapping(value = "/delete", method = RequestMethod.POST)
  public @ResponseBody Message delete(Long[] ids) {
    if (ids != null) {
      bookingRegistrationService.delete(ids);
    }
    return SUCCESS_MESSAGE;
  }
  /**
   * 导出数据前，计算当前呈现给用户的有多少条数据
   * @return
   */
  @RequestMapping(value = "/count", method = RequestMethod.POST)
  public @ResponseBody Map<String, Long> count(BookingRegistrationSearchRequest bookingRegSearch) {
    Long count = new Long(0);
    count = new Long(bookingRegistrationService.countByFilter(bookingRegSearch));
    Map<String, Long> countMap = new HashMap<String, Long>(); 
    countMap.put("count", count);
    return countMap;
  }
  /**
   * 导出列表数据，即用户已经查询出来的数据
   * @param withDays
   */
  @RequestMapping(value = "/exportData", method = {RequestMethod.GET,RequestMethod.POST})
  public void exportData(HttpServletResponse response,  BookingRegistrationSearchRequest bookingRegSearch) {
    List<BookingRegistration> bookingRegistrationList = bookingRegistrationService.searchListByFilter(bookingRegSearch);
    if (bookingRegistrationList != null && bookingRegistrationList.size() > 0) {
      
      String title = "Booking Registration"; // 工作簿标题，同时也是excel文件名前缀
      
      String[] headers = {"peopleWhoBooked", "elderlyName", "phoneNumber", "bookingCheckInDate", "IDCard", "intentRoomType", "gender", "remark"}; // 需要导出的字段
      String[] headersName = {"预约人", "老人姓名", "电话号码", "预约入住时间", "身份证号码", "意向房型", "性别", "备注"}; // 字段对应列的列名
      // 导出数据到Excel
      List<Map<String, String>> bookingRegMapList = bookingRegistrationService.prepareMap(bookingRegistrationList);
      if (bookingRegMapList.size() > 0) {
        exportListToExcel(response, bookingRegMapList, title, headers, headersName);
      }
    }
  } 
}
