package com.yly.json.request;

import java.util.Date;

public class ConsultationRecordSearchRequest {
  /**
   * 咨询人（隐藏域）
   */
  private String visitorHidden;
  /**
   * 老人姓名（隐藏域）
   */
  private String elderlyNameHidden;
  /**
   * 入住意向（隐藏域）
   */
  private String checkinIntentionHidden;
  /**
   * 信息来源（隐藏域）
   */
  private String infoChannelHidden;
  /**
   * 回访时间，开始（隐藏域）
   */
  private Date returnVisitDateBeginDateHidden;
  /**
   * 回访时间，结束（隐藏域）
   */
  private Date returnVisitDateEndDateHidden;
  public String getVisitorHidden() {
    return visitorHidden;
  }
  public void setVisitorHidden(String visitorHidden) {
    this.visitorHidden = visitorHidden;
  }
  public String getElderlyNameHidden() {
    return elderlyNameHidden;
  }
  public void setElderlyNameHidden(String elderlyNameHidden) {
    this.elderlyNameHidden = elderlyNameHidden;
  }
  public String getCheckinIntentionHidden() {
    return checkinIntentionHidden;
  }
  public void setCheckinIntentionHidden(String checkinIntentionHidden) {
    this.checkinIntentionHidden = checkinIntentionHidden;
  }
  public String getInfoChannelHidden() {
    return infoChannelHidden;
  }
  public void setInfoChannelHidden(String infoChannelHidden) {
    this.infoChannelHidden = infoChannelHidden;
  }
  public Date getReturnVisitDateBeginDateHidden() {
    return returnVisitDateBeginDateHidden;
  }
  public void setReturnVisitDateBeginDateHidden(Date returnVisitDateBeginDateHidden) {
    this.returnVisitDateBeginDateHidden = returnVisitDateBeginDateHidden;
  }
  public Date getReturnVisitDateEndDateHidden() {
    return returnVisitDateEndDateHidden;
  }
  public void setReturnVisitDateEndDateHidden(Date returnVisitDateEndDateHidden) {
    this.returnVisitDateEndDateHidden = returnVisitDateEndDateHidden;
  }
  
  

}
