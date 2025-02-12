package com.yly.service.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.CompareToBuilder;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;

import com.yly.beans.FileInfo;
import com.yly.beans.FileInfo.FileType;
import com.yly.beans.FileInfo.OrderType;
import com.yly.beans.Setting;
import com.yly.plugin.StoragePlugin;
import com.yly.service.FileService;
import com.yly.service.PluginService;
import com.yly.service.TenantAccountService;
import com.yly.utils.FreemarkerUtils;
import com.yly.utils.SettingUtils;

/**
 * 文件服务
 * 
 * @author shijun
 *
 */
@Service("fileServiceImpl")
public class FileServiceImpl  implements FileService, ServletContextAware{

  /** servletContext */
  private ServletContext servletContext;

  @Resource(name = "taskExecutor")
  private TaskExecutor taskExecutor;
  @Resource(name = "pluginServiceImpl")
  private PluginService pluginService;
  @Resource(name = "tenantAccountServiceImpl")
  private TenantAccountService tenantAccountService;

  public void setServletContext(ServletContext servletContext) {
    this.servletContext = servletContext;
  }

  /**
   * 添加上传任务
   * 
   * @param storagePlugin 存储插件
   * @param path 上传路径
   * @param tempFile 临时文件
   * @param contentType 文件类型
   */
  private void addTask(final StoragePlugin storagePlugin, final String path, final File tempFile,
      final String contentType) {
    taskExecutor.execute(new Runnable() {
      public void run() {
        try {
          storagePlugin.upload(path, tempFile, contentType);
        } finally {
          FileUtils.deleteQuietly(tempFile);
        }
      }
    });
  }

  public boolean isValid(FileType fileType, MultipartFile multipartFile) {
    if (multipartFile == null) {
      return false;
    }
    Setting setting = SettingUtils.get();
    if (setting.getUploadMaxSize() != null && setting.getUploadMaxSize() != 0
        && multipartFile.getSize() > setting.getUploadMaxSize() * 1024L * 1024L) {
      return false;
    }
    String[] uploadExtensions;
    if (fileType == FileType.FLASH) {
      uploadExtensions = setting.getUploadFlashExtensions();
    } else if (fileType == FileType.MEDIA) {
      uploadExtensions = setting.getUploadMediaExtensions();
    } else if (fileType == FileType.FILE) {
      uploadExtensions = setting.getUploadFileExtensions();
    } else {
      uploadExtensions = setting.getUploadImageExtensions();
    }
    if (!ArrayUtils.isEmpty(uploadExtensions)) {
      return FilenameUtils.isExtension(multipartFile.getOriginalFilename(), uploadExtensions);
    }
    return false;
  }

 // public String upload(FileType fileType, MultipartFile multipartFile, String identifier, boolean async) {
  public String upload(FileType fileType, MultipartFile multipartFile, Map<String, String> paramMap, boolean async){
    if (multipartFile == null) {
      return null;
    }
    Setting setting = SettingUtils.get();
    String uploadPath = null;
    if (fileType == FileType.FLASH) {
      uploadPath = setting.getFlashUploadPath();
    } else if (fileType == FileType.MEDIA) {
      uploadPath = setting.getMediaUploadPath();
    } else if (fileType == FileType.FILE) {
      uploadPath = setting.getFileUploadPath();
    } else if(fileType == FileType.PROFILE_PICTURE){
      uploadPath = setting.getProfilePictureUploadPath();
    } else if(fileType == FileType.ALBUM){
      uploadPath = setting.getAlbumUploadPath();
    } else if (fileType == FileType.NOTIFY_PICTURE){
      uploadPath = setting.getNotifyPictureUploadPath ();
    }      
    try {
      Map<String, Object> model = new HashMap<String, Object>();
//      model.put("uuid", UUID.randomUUID().toString());
      model.put("orgCode", tenantAccountService.getCurrentTenantOrgCode());
      //model.put("identifier", identifier);
      model.put("identifier", paramMap.get("identifier"));
      model.put("albumName", paramMap.get("albumName"));
      model.put("tenantUserName", paramMap.get("tenantUserName")); 
      String path = FreemarkerUtils.process(uploadPath, model);
      String destPath =
          path + UUID.randomUUID() + "."
              + FilenameUtils.getExtension(multipartFile.getOriginalFilename());

      for (StoragePlugin storagePlugin : pluginService.getStoragePlugins(true)) {
        File tempFile =
            new File(System.getProperty("java.io.tmpdir") + "/upload_" + UUID.randomUUID() + ".tmp");
        if (!tempFile.getParentFile().exists()) {
          tempFile.getParentFile().mkdirs();
        }
        multipartFile.transferTo(tempFile);
        if (async) {
          addTask(storagePlugin, destPath, tempFile, multipartFile.getContentType());
        } else {
          try {
            storagePlugin.upload(destPath, tempFile, multipartFile.getContentType());
          } finally {
            FileUtils.deleteQuietly(tempFile);
          }
        }
        return storagePlugin.getUrl(destPath);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  //public String upload(FileType fileType, MultipartFile multipartFile , String identifier) {
  public String upload(FileType fileType, MultipartFile multipartFile , Map<String, String> paramMap) { 
    return upload(fileType, multipartFile, paramMap, false);
  }
  public String uploadLocal(FileType fileType, MultipartFile multipartFile , String identifier) {
    if (multipartFile == null) {
      return null;
    }
    Setting setting = SettingUtils.get();
    String uploadPath = null;
    if (fileType == FileType.FLASH) {
      uploadPath = setting.getFlashUploadPath();
    } else if (fileType == FileType.MEDIA) {
      uploadPath = setting.getMediaUploadPath();
    } else if (fileType == FileType.FILE) {
      uploadPath = setting.getFileUploadPath();
    } else if(fileType == FileType.PROFILE_PICTURE){
      uploadPath = setting.getProfilePictureUploadPath();
    } else if(fileType == FileType.ALBUM){
      uploadPath = setting.getAlbumUploadPath();
    }else if (fileType == FileType.NOTIFY_PICTURE){
      uploadPath = setting.getImageUploadPath ();
    }
    try {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("uuid", UUID.randomUUID().toString());
      String path = FreemarkerUtils.process(uploadPath, model);
      String destPath =
          path + UUID.randomUUID() + "."
              + FilenameUtils.getExtension(multipartFile.getOriginalFilename());
      File destFile = new File(servletContext.getRealPath(destPath));
      if (!destFile.getParentFile().exists()) {
        destFile.getParentFile().mkdirs();
      }
      multipartFile.transferTo(destFile);
      return destPath;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  public List<FileInfo> browser(String path, FileType fileType, OrderType orderType) {
    if (path != null) {
      if (!path.startsWith("/")) {
        path = "/" + path;
      }
      if (!path.endsWith("/")) {
        path += "/";
      }
    } else {
      path = "/";
    }
    Setting setting = SettingUtils.get();
    String uploadPath;
    if (fileType == FileType.FLASH) {
      uploadPath = setting.getFlashUploadPath();
    } else if (fileType == FileType.MEDIA) {
      uploadPath = setting.getMediaUploadPath();
    } else if (fileType == FileType.FILE) {
      uploadPath = setting.getFileUploadPath();
    } else {
      uploadPath = setting.getImageUploadPath();
    }
    String browsePath = StringUtils.substringBefore(uploadPath, "${");
    browsePath = StringUtils.substringBeforeLast(browsePath, "/") + path;

    List<FileInfo> fileInfos = new ArrayList<FileInfo>();
    if (browsePath.indexOf("..") >= 0) {
      return fileInfos;
    }
    for (StoragePlugin storagePlugin : pluginService.getStoragePlugins(true)) {
      fileInfos = storagePlugin.browser(browsePath);
      break;
    }
    if (orderType == OrderType.SIZE) {
      Collections.sort(fileInfos, new SizeComparator());
    } else if (orderType == OrderType.TYPE) {
      Collections.sort(fileInfos, new TypeComparator());
    } else {
      Collections.sort(fileInfos, new NameComparator());
    }
    return fileInfos;
  }

  private class NameComparator implements Comparator<FileInfo> {
    public int compare(FileInfo fileInfos1, FileInfo fileInfos2) {
      return new CompareToBuilder()
          .append(!fileInfos1.getIsDirectory(), !fileInfos2.getIsDirectory())
          .append(fileInfos1.getName(), fileInfos2.getName()).toComparison();
    }
  }

  private class SizeComparator implements Comparator<FileInfo> {
    public int compare(FileInfo fileInfos1, FileInfo fileInfos2) {
      return new CompareToBuilder()
          .append(!fileInfos1.getIsDirectory(), !fileInfos2.getIsDirectory())
          .append(fileInfos1.getSize(), fileInfos2.getSize()).toComparison();
    }
  }

  private class TypeComparator implements Comparator<FileInfo> {
    public int compare(FileInfo fileInfos1, FileInfo fileInfos2) {
      return new CompareToBuilder()
          .append(!fileInfos1.getIsDirectory(), !fileInfos2.getIsDirectory())
          .append(FilenameUtils.getExtension(fileInfos1.getName()),
              FilenameUtils.getExtension(fileInfos2.getName())).toComparison();
    }
  }
  /** 
   * 删除某个文件夹下的所有子文件夹和子文件 
   * 
   * @param realPath 绝对路径
   * @return boolean 
   */  
  public boolean deletefile(String realPath) throws Exception {
    if (StringUtils.isBlank(realPath)) return false;
    boolean isDeleted = false;
   try {       
    File file = new File(realPath);  
    if (!file.isDirectory()) {  
      isDeleted = file.delete();  
    } else if (file.isDirectory()) {  
     String[] filelist = file.list();  
     for (int i = 0; i < filelist.length; i++) {  
      File delfile = new File(realPath + "\\" + filelist[i]);  
      if (!delfile.isDirectory()) {  
          delfile.delete();  
      } else if (delfile.isDirectory()) {  
         deletefile(realPath + "\\" + filelist[i]);  
      }  
     }  
     isDeleted = file.delete();  
    }     
   } catch (FileNotFoundException e) {  
       e.printStackTrace();
       return isDeleted;
   }  
   return isDeleted;  
  }
  
  /**
   * 根据相对路径获取 应用服务器上的绝对路径
   * @param relativepath 相对路径
   * @return
   */
  @Override
  public String getRealPath(String relativepath) {  
    if (StringUtils.isBlank(relativepath)){
      return "";
    }
    return servletContext.getRealPath(relativepath);
  }  
  
}
