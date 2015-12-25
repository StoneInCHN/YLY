package com.yly.json.request;

import java.util.Date;

public class ElderlyInfoSearchRequest {

  /**
   * 老人编号(隐藏域)
   */
  private String identifierHidden;
  /**
   * 老人姓名(隐藏域)
   */
  private String nameHidden;
  /**
   * 老人状态(隐藏域)
   */
  private String elderlyStatusHidden;
  /**
   * 删除状态
   */
  private String deleteStatus;
  
  public String getDeleteStatus() {
    return deleteStatus;
  }
  public void setDeleteStatus(String deleteStatus) {
    this.deleteStatus = deleteStatus;
  }
  /**
   * 入院时间，开始(隐藏域)
   */
  private Date beHospitalizedBeginDateHiden;
  /**
   * 入院时间，结束(隐藏域)
   */
  private Date beHospitalizedEndDateHidden;
  public String getIdentifierHidden() {
    return identifierHidden;
  }
  public void setIdentifierHidden(String identifierHidden) {
    this.identifierHidden = identifierHidden;
  }
  public String getNameHidden() {
    return nameHidden;
  }
  public void setNameHidden(String nameHidden) {
    this.nameHidden = nameHidden;
  }
  public String getElderlyStatusHidden() {
    return elderlyStatusHidden;
  }
  public void setElderlyStatusHidden(String elderlyStatusHidden) {
    this.elderlyStatusHidden = elderlyStatusHidden;
  }
  public Date getBeHospitalizedBeginDateHiden() {
    return beHospitalizedBeginDateHiden;
  }
  public void setBeHospitalizedBeginDateHiden(Date beHospitalizedBeginDateHiden) {
    this.beHospitalizedBeginDateHiden = beHospitalizedBeginDateHiden;
  }
  public Date getBeHospitalizedEndDateHidden() {
    return beHospitalizedEndDateHidden;
  }
  public void setBeHospitalizedEndDateHidden(Date beHospitalizedEndDateHidden) {
    this.beHospitalizedEndDateHidden = beHospitalizedEndDateHidden;
  }
  
  
  


}
