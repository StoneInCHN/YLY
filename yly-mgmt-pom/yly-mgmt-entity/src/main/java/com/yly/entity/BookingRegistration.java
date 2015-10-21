package com.yly.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Index;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.DateBridge;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.search.annotations.Resolution;
import org.hibernate.search.annotations.Store;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yly.entity.base.BaseEntity;
import com.yly.entity.commonenum.CommonEnum.Gender;

/**
 * 预约登记
 * 
 * @author shijun
 *
 */

@Entity
@Table(name = "yly_booking_registration")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "yly_booking_registration_sequence")
@Indexed(index="bookingRegistration")
public class BookingRegistration extends BaseEntity {

  /**
   * 
   */
  private static final long serialVersionUID = 3663051798910809315L;


  /**
   * 租户ID
   */
  private Long tenantID;

  /**
   * 入住老人姓名
   */
  private String elderlyName;

  /**
   * 性别
   */
  private Gender gender;

  /**
   * 身份证号码
   */
  private String IDCard;

  /**
   * 意向房型
   */
  private SystemConfig intentRoomType;

  /**
   * 预订人
   */
  private String peopleWhoBooked;

  /**
   * 预订人手机
   */
  private String phoneNumber;

  /**
   * 预约时间
   */
  private Date bookingCheckInDate;

  /**
   * 备注
   */
  private String remark;

  @JsonProperty
  @Column(length = 15)
  @Field(index = org.hibernate.search.annotations.Index.TOKENIZED, store = Store.NO, analyzer = @Analyzer(impl = IKAnalyzer.class))
  public String getElderlyName() {
    return elderlyName;
  }

  public void setElderlyName(String elderlyName) {
    this.elderlyName = elderlyName;
  }

  @JsonProperty
  public Gender getGender() {
    return gender;
  }

  public void setGender(Gender gender) {
    this.gender = gender;
  }

  @JsonProperty
  @Column(length = 25)
  public String getIDCard() {
    return IDCard;
  }

  public void setIDCard(String iDCard) {
    IDCard = iDCard;
  }

  @JsonProperty
  @ManyToOne
  @IndexedEmbedded
  public SystemConfig getIntentRoomType() {
    return intentRoomType;
  }

  public void setIntentRoomType(SystemConfig intentRoomType) {
    this.intentRoomType = intentRoomType;
  }

  @JsonProperty
  @Column(length = 15)
  @Field(index = org.hibernate.search.annotations.Index.TOKENIZED, store = Store.NO, analyzer = @Analyzer(impl = IKAnalyzer.class))
  public String getPeopleWhoBooked() {
    return peopleWhoBooked;
  }

  public void setPeopleWhoBooked(String peopleWhoBooked) {
    this.peopleWhoBooked = peopleWhoBooked;
  }
  
  @JsonProperty
  @Column(length = 15)
  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  @JsonProperty
  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  @JsonProperty
  @Index(name = "booking_registration_tenantid")
  @Field(index = org.hibernate.search.annotations.Index.UN_TOKENIZED, store = Store.NO)
  public Long getTenantID() {
    return tenantID;
  }

  public void setTenantID(Long tenantID) {
    this.tenantID = tenantID;
  }

  @JsonProperty
  @Field(index = org.hibernate.search.annotations.Index.UN_TOKENIZED, store = Store.NO)
  @DateBridge(resolution = Resolution.DAY)
  public Date getBookingCheckInDate() {
    return bookingCheckInDate;
  }

  public void setBookingCheckInDate(Date bookingCheckInDate) {
    this.bookingCheckInDate = bookingCheckInDate;
  }
}
