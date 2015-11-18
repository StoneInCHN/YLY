package com.yly.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yly.beans.Message;
import com.yly.controller.base.BaseController;
import com.yly.service.PhysicalExaminationItemsService;

/**
 * 体检项配置map
 * @author huyong
 *
 */
@Controller ("physicalExaminationItemsController")
@RequestMapping ("console/physicalExaminationItems")
public class PhysicalExaminationItemsController extends BaseController
{

  @Resource (name = "physicalExaminationItemsServiceImpl")
  private PhysicalExaminationItemsService physicalExaminationItemsService;

  /**
   * 删除
   */
  @RequestMapping (value = "/delete", method = RequestMethod.POST)
  public @ResponseBody Message delete (Long id)
  {
    
      physicalExaminationItemsService.delete (id);
    
    return SUCCESS_MESSAGE;
  }
  
}
