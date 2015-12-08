package com.yly.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yly.controller.base.BaseController;
import com.yly.service.VersionConfigService;

/**
 * 版本配置
 * @author huyong
 *
 */
@Controller ("versionConfigController")
@RequestMapping ("console/versionConfig")
public class VersionConfigController extends BaseController
{

  @Resource (name = "versionConfigServiceImpl")
  private VersionConfigService versionConfigService;

  @RequestMapping(value = "/retrieve", method = RequestMethod.GET)
  public void retrieve(){
    
  } 
 
}
