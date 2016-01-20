package com.yly.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Index;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yly.entity.base.BaseEntity;

/**
 * 通知公告
 * @author sujinxuan
 *
 */
@Entity
@Table(name = "yly_notification")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "yly_notification_sequence")
@Indexed(index="notification")
public class Notification extends BaseEntity{

  
  private static final long serialVersionUID = 7085075064841785833L;
  
  /**
   * 租户ID
   */
  private Long tenantID;
  /**
   * 发布时间
   */
  private Date publishTime;
  /**
   * 发布人
   */
  private String operator;
  /**
   * 标题
   */
  private String title;
  
  /**
   * 内容
   */
  private String content;
  

  @JsonProperty
  public Date getPublishTime() {
    return publishTime;
  }

  public void setPublishTime(Date publishTime) {
    this.publishTime = publishTime;
  }

  @Column(length = 15)
  @JsonProperty
  @Field(store= Store.NO,index = org.hibernate.search.annotations.Index.TOKENIZED,analyzer = @Analyzer(impl = IKAnalyzer.class) )
  public String getOperator() {
    return operator;
  }

  public void setOperator(String operator) {
    this.operator = operator;
  }

  @Column(length = 30)
  @JsonProperty
  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  @Column(length = 1000)
  @JsonProperty
  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  @Index(name="notification_tenantid")
  public Long getTenantID() {
    return tenantID;
  }

  public void setTenantID(Long tenantID) {
    this.tenantID = tenantID;
  }
  
}

