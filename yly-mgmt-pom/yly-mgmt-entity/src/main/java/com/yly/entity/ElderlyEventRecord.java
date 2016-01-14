package com.yly.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Index;
import org.hibernate.search.annotations.DateBridge;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.FieldBridge;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.search.annotations.Resolution;
import org.hibernate.search.annotations.Store;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yly.entity.base.BaseEntity;
import com.yly.entity.commonenum.CommonEnum.ElderlyEventType;
import com.yly.lucene.DateBridgeImpl;

/**
 * 老人事件记录表
 * 
 * @author shijun
 *
 */
@Entity
@Table(name = "yly_elderly_event_record")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "yly_elderly_event_record_sequence")
@Indexed(index = "elderlyEventRecord/elderlyEventRecord")
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
   * 事件类型
   */
  private ElderlyEventType elderlyEventType;
  
  @JsonProperty
  @Field(index = org.hibernate.search.annotations.Index.UN_TOKENIZED, store = Store.NO)
  public ElderlyEventType getElderlyEventType() {
    return elderlyEventType;
  }

  public void setElderlyEventType(ElderlyEventType elderlyEventType) {
    this.elderlyEventType = elderlyEventType;
  }

  /**
   * 事件老人
   */
  private ElderlyInfo elderlyInfo;

  /**
   * 记录人
   */
  private String operator;

  @JsonProperty
  @Temporal(TemporalType.DATE)
  @Field(index = org.hibernate.search.annotations.Index.UN_TOKENIZED, store = Store.NO)
  @FieldBridge(impl = DateBridgeImpl.class)
  public Date getEventDate() {
    return eventDate;
  }

  public void setEventDate(Date eventDate) {
    this.eventDate = eventDate;
  }

  @JsonProperty
  @NotEmpty
  @Length(max = 300)
  @Column(length = 300)
  public String getEventContent() {
    return eventContent;
  }

  public void setEventContent(String eventContent) {
    this.eventContent = eventContent;
  }

  @JsonProperty
  @ManyToOne(fetch = FetchType.LAZY)
  @IndexedEmbedded
  public ElderlyInfo getElderlyInfo() {
    return elderlyInfo;
  }

  public void setElderlyInfo(ElderlyInfo elderlyInfo) {
    this.elderlyInfo = elderlyInfo;
  }

  @JsonProperty
  @NotEmpty
  @Length(max = 15)
  @Column(length = 15, nullable = false)
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
