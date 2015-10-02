package com.yly.controller;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yly.beans.Message;
import com.yly.controller.base.BaseController;
import com.yly.entity.BookingRegistration;
import com.yly.entity.SystemConfig;
import com.yly.entity.commonenum.CommonEnum.ConfigKey;
import com.yly.framework.paging.Page;
import com.yly.framework.paging.Pageable;
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
  public @ResponseBody Page<BookingRegistration> list(Date beginDate, Date endDate,
      Pageable pageable, ModelMap model) {

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
    model.addAttribute("systemConfigs", systemConfigService.findByConfigKey(ConfigKey.ROOMTYPE, null));
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

}
