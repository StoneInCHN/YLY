package com.yly.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.yly.beans.FileInfo;
import com.yly.beans.FileInfo.FileType;
import com.yly.beans.FileInfo.OrderType;

public interface FileService {

  /**
   * 文件验证
   * 
   * @param fileType 文件类型
   * @param multipartFile 上传文件
   * @return 文件验证是否通过
   */
  boolean isValid(FileType fileType, MultipartFile multipartFile);

  /**
   * 文件上传
   * 
   * @param fileType 文件类型
   * @param multipartFile 上传文件
   * @param async 是否异步
   * @return 访问URL
   */
  //String upload(FileType fileType, MultipartFile multipartFile, String identifier, boolean async);
  String upload(FileType fileType, MultipartFile multipartFile, Map<String, String> paramMap, boolean async);

  /**
   * 文件上传(异步)
   * 
   * @param fileType 文件类型
   * @param multipartFile 上传文件
   * @return 访问URL
   */
  //String upload(FileType fileType, MultipartFile multipartFile, String identifier);
  String upload(FileType fileType, MultipartFile multipartFile, Map<String, String> paramMap);
  /**
   * 文件上传至本地
   * 
   * @param fileType 文件类型
   * @param multipartFile 上传文件
   * @return 路径
   */
  String uploadLocal(FileType fileType, MultipartFile multipartFile , String identifier);

  /**
   * 文件浏览
   * 
   * @param path 浏览路径
   * @param fileType 文件类型
   * @param orderType 排序类型
   * @return 文件信息
   */
  List<FileInfo> browser(String path, FileType fileType, OrderType orderType);
  
  /** 
   * 删除某个文件夹下的所有子文件夹和子文件 
   * 
   * @param realpath 绝对路径
   * @return boolean 
   */ 
  boolean deletefile(String realpath) throws Exception;
  /**
   * 根据相对路径获取 应用服务器上的绝对路径
   * @param relativepath 相对路径
   * @return
   */
  String getRealPath(String relativepath);

}
