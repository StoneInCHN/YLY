package com.yly.controller;

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
import com.yly.entity.Building;
import com.yly.entity.Room;
import com.yly.entity.SystemConfig;
import com.yly.framework.paging.Page;
import com.yly.framework.paging.Pageable;
import com.yly.service.BuildingService;
import com.yly.service.RoomService;
import com.yly.service.SystemConfigService;
import com.yly.service.TenantAccountService;
import com.yly.utils.FieldFilterUtils;

/**
 * 房间
 * @author tanbiao
 *
 */
@Controller("roomController")
@RequestMapping("console/room")
public class RoomController extends BaseController {

  @Resource(name = "buildingServiceImpl")
  private BuildingService buildingService;

  @Resource(name = "roomServiceImpl")
  private RoomService roomService;
  
  @Resource(name = "tenantAccountServiceImpl")
  private TenantAccountService tenantAccountService;
  
  @Resource(name = "systemConfigServiceImpl")
  private SystemConfigService systemConfigService;

  /**
   * 进入room主页面
   * @param model
   * @return
   */
  @RequestMapping(value = "/room", method = RequestMethod.GET)
  public String list(ModelMap model) {
    return "room/room";
  }

  /**
   * 按条件分页查询
   * @param beginDate
   * @param endDate
   * @param pageable
   * @param model
   * @return
   */
  @RequestMapping(value = "/list", method = RequestMethod.POST)
  public @ResponseBody Page<Room> list(Date beginDate, Date endDate, Pageable pageable,
      ModelMap model) {
    return roomService.findPage(pageable,true);
  }

  /**
   * 查询该租户下所有的房间
   * @return
   */
  @RequestMapping(value = "/findAll", method = RequestMethod.POST)
  public @ResponseBody List<Map<String, Object>> findAll() {
    String[] strArr = {"id","roomNumber"};
    return FieldFilterUtils.filterCollectionMap(strArr,  roomService.findAll(true));
  }
  
  /**
   * 获取数据进入编辑页面
   * @param model
   * @param id
   * @return
   */
  @RequestMapping(value = "/edit", method = RequestMethod.GET)
  public String edit(ModelMap model, Long id) {
    model.addAttribute("room", roomService.find(id));
    model.addAttribute("buildings", buildingService.findAll(true));
    return "room/edit";
  }

  /**
   * 保存
   * @param buildingId
   * @param room
   * @return
   */
  @RequestMapping(value = "/save", method = RequestMethod.POST)
  public @ResponseBody Message save(Long buildingId,Long roomTypeId ,Room room) {
    Building building = buildingService.find(buildingId);
    SystemConfig roomType =systemConfigService.find(roomTypeId);
    if(building!=null && roomType!=null ){
      room.setBuilding(building);
      room.setRoomType(roomType);
      roomService.save(room,true);
      return SUCCESS_MESSAGE;
    }else{
      return ERROR_MESSAGE;
    }
  }

  /**
   * 更新
   * @param buildingId 楼宇ID
   * @param room
   * @return
   */

  @RequestMapping(value = "/update", method = RequestMethod.POST)
  public @ResponseBody Message update(Long buildingId,Room room) {
    Building building = buildingService.find(buildingId);
    if(building!=null){
      room.setBuilding(building);
      roomService.update(room,"tenantID","createDate");
      return SUCCESS_MESSAGE;
    }else{
      return ERROR_MESSAGE;
    }
  }



  /**
   * 删除
   */
  @RequestMapping(value = "/delete", method = RequestMethod.POST)
  public @ResponseBody Message delete(Long[] ids) {
    if (ids != null) {
      // 检查是否能被删除
      // if()
      roomService.delete(ids);;
    }
    return SUCCESS_MESSAGE;
  }



}
