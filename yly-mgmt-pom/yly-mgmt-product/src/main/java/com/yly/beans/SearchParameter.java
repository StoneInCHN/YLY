package com.yly.beans;

import java.util.Date;

public class SearchParameter {
  /**
   *  寄存物品名称关键字 
   */
  private String keysOfStuffName;
  /**
   *  寄存物编号称关键字 
   */
  private String keysOfStuffNumber;
  /**
   *  寄存物品所属老人名字关键字 
   */
  private String keysOfElderlyName;
  /**
   *  存入物品的开始时间 查询
   */
  private Date beginPutInDate;
  /**
   *  存入物品的截止时间 查询
   */
  private Date endPutInDate;
  /**
   *  取走物品的开始时间 查询
   */
  private Date beginTakeAwayDate;
  /**
   *  取走物品的截止时间 查询
   */
  private Date endTakeAwayDate;
  public String getKeysOfStuffName() {
    return keysOfStuffName;
  }
  public void setKeysOfStuffName(String keysOfStuffName) {
    this.keysOfStuffName = keysOfStuffName;
  }
  public String getKeysOfStuffNumber() {
    return keysOfStuffNumber;
  }
  public void setKeysOfStuffNumber(String keysOfStuffNumber) {
    this.keysOfStuffNumber = keysOfStuffNumber;
  }
  public String getKeysOfElderlyName() {
    return keysOfElderlyName;
  }
  public void setKeysOfElderlyName(String keysOfElderlyName) {
    this.keysOfElderlyName = keysOfElderlyName;
  }
  public Date getBeginPutInDate() {
    return beginPutInDate;
  }
  public void setBeginPutInDate(Date beginPutInDate) {
    this.beginPutInDate = beginPutInDate;
  }
  public Date getEndPutInDate() {
    return endPutInDate;
  }
  public void setEndPutInDate(Date endPutInDate) {
    this.endPutInDate = endPutInDate;
  }
  public Date getBeginTakeAwayDate() {
    return beginTakeAwayDate;
  }
  public void setBeginTakeAwayDate(Date beginTakeAwayDate) {
    this.beginTakeAwayDate = beginTakeAwayDate;
  }
  public Date getEndTakeAwayDate() {
    return endTakeAwayDate;
  }
  public void setEndTakeAwayDate(Date endTakeAwayDate) {
    this.endTakeAwayDate = endTakeAwayDate;
  }
  
}
