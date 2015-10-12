package com.yly.controller;


import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yly.controller.base.BaseController;
import com.yly.entity.commonenum.CommonEnum.IdentifierType;
import com.yly.service.IdentifierService;

/**
 * 人员编号
 * 
 * @author shijun
 *
 */
@Controller("identifierController")
@RequestMapping("console/identifier")
public class IdentifierController extends BaseController {

  @Resource(name = "identifierServiceImpl")
  private IdentifierService identifierService;

  /**
   * 生成人员编号
   * 
   * @param model
   * @param idType
   * @return
   */
  @RequestMapping(value = "/generateId", method = RequestMethod.GET)
  public @ResponseBody Map<String, String> generateId(ModelMap model, IdentifierType idType) {
    Map<String, String> idMap = new HashMap<String, String>();
    idMap.put("id", identifierService.generate(idType));
    return idMap;
  }
}
