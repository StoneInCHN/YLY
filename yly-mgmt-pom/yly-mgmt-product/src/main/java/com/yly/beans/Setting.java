package com.yly.beans;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;
import org.hibernate.validator.constraints.Length;

/**
 * 系统设置
 * 
 */
public class Setting implements Serializable {

  private static final long serialVersionUID = -1478999889661796840L;

  /**
   * 验证码类型
   */
  public enum CaptchaType {

    /** 后台登录 */
    adminLogin,

    /** 后台注册 */
    adminReg,

    /** 找回密码 */
    findPassword,

    /** 重置密码 */
    resetPassword,

    /** 其它 */
    other
  }

  /** 缓存名称 */
  public static final String CACHE_NAME = "setting";

  /** 缓存Key */
  public static final Integer CACHE_KEY = 0;

  /** 分隔符 */
  private static final String SEPARATOR = ",";

  /** 用户名手机号正则表达式 */
  private String mobilePattern;
  
  /** 验证码类型 */
  private CaptchaType[] captchaTypes;
  
  /**
   * 默认角色Id
   */
  private Long defaultRoleId;
  
  private String fileUploadPath;
   
  /** 允许上传文件扩展名 */
  private String uploadFileExtension;
  

  public String getMobilePattern() {
    return mobilePattern;
  }

  public void setMobilePattern(String mobilePattern) {
    this.mobilePattern = mobilePattern;
  }

  public CaptchaType[] getCaptchaTypes() {
    return captchaTypes;
  }

  public void setCaptchaTypes(CaptchaType[] captchaTypes) {
    this.captchaTypes = captchaTypes;
  }

  public Long getDefaultRoleId() {
    return defaultRoleId;
  }

  public void setDefaultRoleId(Long defaultRoleId) {
    this.defaultRoleId = defaultRoleId;
  }

  public String getFileUploadPath() {
    return fileUploadPath;
  }

  public void setFileUploadPath(String fileUploadPath) {
    this.fileUploadPath = fileUploadPath;
  }

  /**
   * 获取允许上传文件扩展名
   * 
   * @return 允许上传文件扩展名
   */
  @Length(max = 200)
  public String getUploadFileExtension() {
      return uploadFileExtension;
  }

  /**
   * 设置允许上传文件扩展名
   * 
   * @param uploadFileExtension
   *            允许上传文件扩展名
   */
  public void setUploadFileExtension(String uploadFileExtension) {
      if (uploadFileExtension != null) {
          uploadFileExtension = uploadFileExtension.replaceAll("[,\\s]*,[,\\s]*", ",").replaceAll("^,|,$", "").toLowerCase();
      }
      this.uploadFileExtension = uploadFileExtension;
  }
  
  /**
   * 获取允许上传文件扩展名
   * 
   * @return 允许上传文件扩展名
   */
  public String[] getUploadFileExtensions() {
      return StringUtils.split(uploadFileExtension, SEPARATOR);
  }
}

