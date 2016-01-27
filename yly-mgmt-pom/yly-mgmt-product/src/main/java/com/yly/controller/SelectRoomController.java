package com.yly.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yly.controller.base.BaseController;
import com.yly.entity.Building;
import com.yly.entity.Room;
import com.yly.service.BuildingService;
import com.yly.service.RoomService;

@Controller("selectRoomController")
@RequestMapping("console/selectRoom")
public class SelectRoomController extends BaseController {
  
  @Resource(name = "buildingServiceImpl")
  public BuildingService buildingService;
  
  @Resource(name = "roomServiceImpl")
  public RoomService roomService;
  
  @RequestMapping(value = "/selectRoom", method = RequestMethod.GET)
  public String changeRoom() {

    return "selectRoom/selectRoom";
  }

  @RequestMapping(value = "/bedImgShow", method = RequestMethod.GET)
  public String bedImgShow(Long roomId, Long buildingId, ModelMap model) {
    List<Building> buildings = new ArrayList<Building>();
    if (buildingId != null) {
      Building building = buildingService.find(buildingId);
      buildings.add(building);
    } else if (roomId != null) {
      Room room = roomService.find(roomId);
      Set<Room> rooms = new HashSet<Room>();
      rooms.add(room);
      Building building = room.getBuilding();
      building.setRooms(rooms);
      buildings.add(building);
    } else {
      buildings = buildingService.findAll(true);
    }
    model.addAttribute("buildings", buildings);

    return "selectRoom/bedImgShow";
  }

}
