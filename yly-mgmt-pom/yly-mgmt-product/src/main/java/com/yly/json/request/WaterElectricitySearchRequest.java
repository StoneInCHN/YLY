package com.yly.json.request;

import java.util.Date;

public class WaterElectricitySearchRequest {
  
  /**
   * 老人姓名（隐藏域）
   */
  private String nameHidden;
  /**
   * 老人编号（隐藏域）
   */
  private String identifierHidden;
  /**
   * 状态（隐藏域）
   */
  private String statusHidden;
  /**
   * 收费时间段,开始（隐藏域）
   */
  private Date beginDateHidden;
  /**
   * 收费时间段，结束（隐藏域）
   */
  private Date endDateHidden;
  public String getNameHidden() {
    return nameHidden;
  }
  public void setNameHidden(String nameHidden) {
    this.nameHidden = nameHidden;
  }
  public String getIdentifierHidden() {
    return identifierHidden;
  }
  public void setIdentifierHidden(String identifierHidden) {
    this.identifierHidden = identifierHidden;
  }
  public String getStatusHidden() {
    return statusHidden;
  }
  public void setStatusHidden(String statusHidden) {
    this.statusHidden = statusHidden;
  }
  public Date getBeginDateHidden() {
    return beginDateHidden;
  }
  public void setBeginDateHidden(Date beginDateHidden) {
    this.beginDateHidden = beginDateHidden;
  }
  public Date getEndDateHidden() {
    return endDateHidden;
  }
  public void setEndDateHidden(Date endDateHidden) {
    this.endDateHidden = endDateHidden;
  }
  
  
}
