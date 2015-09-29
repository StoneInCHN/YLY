package com.yly.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Index;

import com.yly.entity.base.BaseEntity;

/**
 * 老人事件记录表
 * 
 * @author shijun
 *
 */
@Entity
@Table(name = "yly_elderly_event_record")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "yly_elderly_event_record_sequence")
public class ElderlyEventRecord extends BaseEntity {

  /**
   * 
   */
  private static final long serialVersionUID = 4369912329850780267L;

  
  /**
   * 租户ID
   */
  private Long tenantID;
  
  /**
   * 事件发生时间
   */
  private Date eventDate;

  /**
   * 事件内容描述
   */
  private String eventContent;

  /**
   * 事件老人
   */
  private ElderlyInfo elderlyInfo;
  
  /**
   * 记录人
   */
  private String operator;

  public Date getEventDate() {
    return eventDate;
  }

  public void setEventDate(Date eventDate) {
    this.eventDate = eventDate;
  }

  public String getEventContent() {
    return eventContent;
  }

  public void setEventContent(String eventContent) {
    this.eventContent = eventContent;
  }

  public ElderlyInfo getElderlyInfo() {
    return elderlyInfo;
  }

  public void setElderlyInfo(ElderlyInfo elderlyInfo) {
    this.elderlyInfo = elderlyInfo;
  }

  @Column(length = 15)
  public String getOperator() {
    return operator;
  }

  public void setOperator(String operator) {
    this.operator = operator;
  }

  @Index(name = "elderly_event_record_tenantid")
public Long getTenantID() {
	return tenantID;
}

public void setTenantID(Long tenantID) {
	this.tenantID = tenantID;
}
  
  
}
