package com.yly.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.yly.beans.Message;
import com.yly.controller.base.BaseController;

@Controller("fileController")
@RequestMapping("console/file")
public class FileController extends BaseController{
  
  
  @RequestMapping("/uploadHeadPic")
  public @ResponseBody Message uploadHeadPic(@RequestParam("file") MultipartFile file, HttpServletRequest request,
      HttpServletResponse response) {
    try {
     // super.upload(file, "/upload/user/", request);
     // response.getWriter().print(super.getFileName());
    //  response.getWriter().print("xxxxxxxx");
      return Message.success("xxxxx/xxxxxx/xxxxx");
    } catch (Exception e) {
      e.printStackTrace();
      return ERROR_MESSAGE;
    }
    
  }
  
}
