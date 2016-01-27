package com.yly.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import com.yly.entity.commonenum.CommonEnum.ConfigKey;
import com.yly.entity.commonenum.CommonEnum.TreeNodeState;
import com.yly.framework.filter.Filter;
import com.yly.framework.filter.Filter.Operator;
import com.yly.framework.paging.Page;
import com.yly.framework.paging.Pageable;
import com.yly.json.response.TreeNodeResponse;
import com.yly.service.BuildingService;
import com.yly.service.RoomService;
import com.yly.service.SystemConfigService;
import com.yly.service.TenantAccountService;

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
  public @ResponseBody Page<Room> list(Long buildingId, Pageable pageable,
      ModelMap model) {
    if (buildingId !=null) {
      Building building = buildingService.find(buildingId);
      List<Filter> filters = new ArrayList<Filter>();
      Filter delFilter = new Filter("building", Operator.eq ,building);
      filters.add(delFilter);
      pageable.setFilters(filters);
    }
    return roomService.findPage(pageable,true);
  }
  
  /**
   * 查询该租户下所有的房间
   * roomId 被选中的房间id
   * isSelect 是否用于下拉标签
   * @return
   */
  @RequestMapping(value = "/findAll", method = RequestMethod.POST)
  public @ResponseBody List<TreeNodeResponse> findAll(Long roomId, Boolean isSelect) {
    List<TreeNodeResponse> treeNodeResponses = null;
    List<Building> buildings = buildingService.findAll(true);
    if (buildings != null && buildings.size() > 0) {
      treeNodeResponses = new ArrayList<TreeNodeResponse>();
      for (Building building : buildings) {
        Boolean isExpand = false;// 是否展开
        TreeNodeResponse treeNodeResponse = new TreeNodeResponse();
        Map<String, Object> attributes = new HashMap<String, Object>();
        attributes.put("isBuilding", true);
        treeNodeResponse.setAttributes(attributes);
        treeNodeResponse.setChecked(false);
        treeNodeResponse.setId(building.getId());
        treeNodeResponse.setText(building.getBuildingName());
        Set<Room> rooms = building.getRooms();
        List<TreeNodeResponse> children = new ArrayList<TreeNodeResponse>();
        if (isSelect) {
          if (rooms != null && rooms.size() > 0) {
            for (Room room : rooms) {
              TreeNodeResponse child = new TreeNodeResponse();
              child.setId(room.getId());
              child.setChecked(false);
              child.setText(room.getRoomNumber());
              if (roomId != null && roomId.equals(room.getId())) {
                isExpand = true;
              }
              children.add(child);
            }
            treeNodeResponse.setChildren(children);
            if (isExpand) {
              treeNodeResponse.setState(TreeNodeState.open);
            } else {
              treeNodeResponse.setState(TreeNodeState.closed);
            }
            treeNodeResponses.add(treeNodeResponse);
          }
        } else {
          if (rooms.size()>0) {
            for (Room room : rooms) {
              TreeNodeResponse child = new TreeNodeResponse();
              child.setId(room.getId());
              child.setChecked(false);
              child.setText(room.getRoomNumber());
              Map<String, Object> nodeAttributes = new HashMap<String, Object>();
              nodeAttributes.put("roomNode", true);
              child.setAttributes(nodeAttributes);
              children.add(child);
            }
          }else{
            //设定树节点上显示的图标
            treeNodeResponse.setIconCls("icon-folder");
          }
          treeNodeResponse.setChildren(children);
          treeNodeResponse.setState(TreeNodeState.open);
          treeNodeResponses.add(treeNodeResponse);
        }

      }
    }
    if (!isSelect) {
      List<TreeNodeResponse> roots = new ArrayList<TreeNodeResponse>();
      TreeNodeResponse rootNodeResponse = new TreeNodeResponse();
      Map<String, Object> attributes = new HashMap<String, Object>();
      attributes.put("rootNode", true);
      rootNodeResponse.setAttributes(attributes);
      rootNodeResponse.setChildren(treeNodeResponses);
      rootNodeResponse.setText("全部");
      rootNodeResponse.setChecked(true);
      rootNodeResponse.setState(TreeNodeState.open);
      roots.add(rootNodeResponse);
      return roots;
    } else {
      return treeNodeResponses;
    }

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
    model.addAttribute("systemConfigs", systemConfigService.findByConfigKey(ConfigKey.ROOMTYPE, null));
    return "room/edit";
  }

  /**
   * 保存
   * @param buildingId
   * @param room
   * @return
   */
  @RequestMapping(value = "/save", method = RequestMethod.POST)
  public @ResponseBody Message add(Long buildingId,Long roomTypeId ,Room room) {
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
  public @ResponseBody Message update(Long buildingId,Room room,Long roomTypeId) {
    Building building = buildingService.find(buildingId);
    SystemConfig roomType =systemConfigService.find(roomTypeId);
    if(building!=null && roomType!=null){
      room.setBuilding(building);
      room.setRoomType(roomType);
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
