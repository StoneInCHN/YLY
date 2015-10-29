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
import com.yly.entity.ElderlyPhotoes;
import com.yly.entity.ElderlyInfo;
import com.yly.framework.paging.Page;
import com.yly.framework.paging.Pageable;
import com.yly.service.ElderlyPhotoesService;
import com.yly.service.ElderlyInfoService;
import com.yly.service.TenantAccountService;
import com.yly.utils.FieldFilterUtils;

/**
 * 老人照片controller
 * 
 * @author luzhang
 *
 */
@Controller("elderlyPhotoesController")
@RequestMapping("/console/elderlyPhotoes")
public class ElderlyPhotoesController extends BaseController {

  @Resource(name = "elderlyPhotoesServiceImpl")
  private ElderlyPhotoesService elderlyPhotoesService;

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
  @RequestMapping(value = "/elderlyPhotoes", method = RequestMethod.GET)
  public String elderlyPhotoes(ModelMap model) {
    return "/elderlyPhotoes/elderlyPhotoes";
  }

  /**
   * 查询list
   * 
   * @param pageable
   * @return
   */
  @RequestMapping(value = "/list", method = RequestMethod.POST)
  public @ResponseBody Page<ElderlyPhotoes> list(String keysOfElderlyName, Date beginDate,
      Date endDate, Pageable pageable) {
    if (keysOfElderlyName == null && beginDate == null && endDate == null) {
      return elderlyPhotoesService.findPage(pageable, true);
    } else {
      return elderlyPhotoesService.SearchPageByFilter(keysOfElderlyName, beginDate, endDate,
          pageable);
    }
  }

  @RequestMapping(value = "/findAll", method = RequestMethod.POST)
  public @ResponseBody List<Map<String, Object>> findAll() {
    String[] propertys = {"id", "buildingName"};
    return FieldFilterUtils.filterCollectionMap(propertys, elderlyPhotoesService.findAll(true));
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
      model.addAttribute("elderlyPhotoes", elderlyPhotoesService.find(id));
      return "elderlyPhotoes/" + handle;
    }
    return "";
  }

  /**
   * 添加
   * 
   * @param elderlyPhotoes
   * @return
   */
  @RequestMapping(value = "/add", method = RequestMethod.POST)
  public @ResponseBody Message save(ElderlyPhotoes elderlyPhotoes, Long elderlyInfoID) {
    ElderlyInfo elderlyInfo = elderlyInfoService.find(elderlyInfoID);
    if (elderlyInfo != null && elderlyPhotoes != null) {
        elderlyPhotoesService.save(elderlyPhotoes);
        return SUCCESS_MESSAGE;
    }
    return ERROR_MESSAGE;
  }

  /**
   * 更新
   * 
   * @param elderlyPhotoes
   * @param elderlyInfoID
   * @return
   */
  @RequestMapping(value = "/update", method = RequestMethod.POST)
  public @ResponseBody Message update(ElderlyPhotoes elderlyPhotoes, Long elderlyInfoID) {
    ElderlyInfo elderlyInfo = elderlyInfoService.find(elderlyInfoID);
    if (elderlyInfo != null) {
      elderlyPhotoesService.update(elderlyPhotoes, "createDate");
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
        ElderlyPhotoes elderlyPhotoes = elderlyPhotoesService.find(id);
        if (elderlyPhotoes != null) {
          elderlyPhotoesService.delete(ids);
        }
      }
    }
    return SUCCESS_MESSAGE;
  }
}
