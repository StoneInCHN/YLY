package com.yly.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yly.beans.Message;
import com.yly.controller.base.BaseController;
import com.yly.entity.Bed;
import com.yly.entity.Room;
import com.yly.framework.paging.Page;
import com.yly.framework.paging.Pageable;
import com.yly.service.BedService;
import com.yly.service.RoomService;

@Controller("bedController")
@RequestMapping("console/bed")
public class BedController extends BaseController {

  @Resource(name = "bedServiceImpl")
  private BedService bedService;
  
  @Resource(name="roomServiceImpl")
  private RoomService roomService;

  @RequestMapping(value = "/bed", method = RequestMethod.GET)
  public String bed(ModelMap model) {
    return "bed/bed";
  }

  @RequestMapping(value = "/list", method = RequestMethod.POST)
  public @ResponseBody Page<Bed> list(Pageable pageable) {
    return bedService.findPage(pageable, true);
  }

  /**
   * 编辑
   * 
   * @param model
   * @param id
   * @return
   */
  @RequestMapping(value = "/edit", method = RequestMethod.GET)
  public String edit(ModelMap model, Long id) {
    model.addAttribute("building", bedService.find(id));
    return "bed/edit";
  }

  /**
   * 保存
   * 
   * @param building
   * @return
   */
  @RequestMapping(value = "/add", method = RequestMethod.POST)
  public @ResponseBody Message add(Bed bed,Long roomId) {
    Room room=null;
    if (roomId!=null) {
       room = roomService.find(roomId);
    }
    if (bed != null && room!=null) {
      bed.setRoom(room);
      bedService.save(bed, true);
      return SUCCESS_MESSAGE;
    } else {
      return ERROR_MESSAGE;
    }

  }


  @RequestMapping(value = "/update", method = RequestMethod.POST)
  public @ResponseBody Message update(Bed bed) {
    bedService.update(bed, "tenantID", "createDate","room");
    return SUCCESS_MESSAGE;
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
      bedService.delete(ids);
    }
    return SUCCESS_MESSAGE;
  }

}
