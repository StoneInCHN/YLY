package com.yly.controller;


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
import com.yly.framework.paging.Page;
import com.yly.framework.paging.Pageable;
import com.yly.service.BuildingService;
import com.yly.service.TenantAccountService;
import com.yly.utils.FieldFilterUtils;

@Controller
@RequestMapping("console/building")
public class BuildingController extends BaseController {

  @Resource(name = "buildingServiceImpl")
  private BuildingService buildingService;
  
  @Resource(name = "tenantAccountServiceImpl")
  private TenantAccountService tenantAccountService;

  @RequestMapping(value = "/building", method = RequestMethod.GET)
  public String list(ModelMap model) {
    return "building/building";
  }

  @RequestMapping(value = "/list", method = RequestMethod.POST)
  public @ResponseBody Page<Building> list(Pageable pageable) {
    return buildingService.findPage(pageable,true);
  }

  @RequestMapping(value = "/findAll", method = RequestMethod.POST)
  public @ResponseBody List<Map<String, Object>> findAll() {
    String[] strArr = {"id","buildingName"};
    return FieldFilterUtils.filterCollectionMap(strArr,  buildingService.findAll(true));
  }

  
  /**
   * 编辑
   * @param model
   * @param id
   * @return
   */
  @RequestMapping(value = "/edit", method = RequestMethod.GET)
  public String edit(ModelMap model, Long id) {
    model.addAttribute("building", buildingService.find(id));
    return "building/edit";
  }

  /**
   * 保存
   * @param building
   * @return
   */
  @RequestMapping(value = "/save", method = RequestMethod.POST)
  public @ResponseBody Message save(Building building) {
    if(building !=null){
      building.setTenantID(tenantAccountService.getCurrentTenantID());
      buildingService.save(building);
      return SUCCESS_MESSAGE;
    }else{
      return ERROR_MESSAGE;
    }
    
  }
  
  
  @RequestMapping(value = "/update", method = RequestMethod.POST)
  public @ResponseBody Message update(Building building) {
    buildingService.update(building,"tenantID","createDate");
    return SUCCESS_MESSAGE;
  }
  


  /**
   * 删除
   * @param ids
   * @return
   */
  @RequestMapping(value = "/delete", method = RequestMethod.POST)
  public @ResponseBody Message delete(Long[] ids) {
    if (ids != null) {
      // 检查是否能被删除
      for (Long id : ids) {
       Building building = buildingService.find(id);
       if(building.getRooms()!=null && building.getRooms().size() >0){
         continue;
       }else{
         buildingService.delete(ids);
       }
      }
    }
    return SUCCESS_MESSAGE;
  }

}
