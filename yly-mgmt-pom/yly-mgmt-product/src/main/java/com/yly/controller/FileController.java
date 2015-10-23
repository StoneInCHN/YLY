package com.yly.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.yly.beans.FileInfo.FileType;
import com.yly.beans.Message;
import com.yly.controller.base.BaseController;
import com.yly.service.FileService;

/**
 * 文件上传
 * 
 * @author shijun
 *
 */
@Controller("fileController")
@RequestMapping("console/file")
public class FileController extends BaseController {

  @Resource(name = "fileServiceImpl")
  private FileService fileService;
  

  @RequestMapping(value = "/uploadProfilePhoto", method = RequestMethod.POST)
  public @ResponseBody Message uploadProfilePhoto(@RequestParam("file") MultipartFile file,String identifier) {
    
    String filePath = fileService.upload(FileType.PROFILE_PICTURE, file, identifier);
    if(filePath ==null){
      filePath ="";
    }
    return Message.success(filePath);
  }

}
