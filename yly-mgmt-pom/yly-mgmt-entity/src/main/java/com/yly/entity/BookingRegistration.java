package com.yly.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Index;

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
  private String phoenNumber;

  /**
   * 备注
   */
  private String remark;

  @Column(length = 15)
  public String getElderlyName() {
    return elderlyName;
  }

  public void setElderlyName(String elderlyName) {
    this.elderlyName = elderlyName;
  }

  public Gender getGender() {
    return gender;
  }

  public void setGender(Gender gender) {
    this.gender = gender;
  }

  @Column(length = 25)
  public String getIDCard() {
    return IDCard;
  }

  public void setIDCard(String iDCard) {
    IDCard = iDCard;
  }

  @ManyToOne
  public SystemConfig getIntentRoomType() {
    return intentRoomType;
  }

  public void setIntentRoomType(SystemConfig intentRoomType) {
    this.intentRoomType = intentRoomType;
  }

  @Column(length = 15)
  public String getPeopleWhoBooked() {
    return peopleWhoBooked;
  }

  public void setPeopleWhoBooked(String peopleWhoBooked) {
    this.peopleWhoBooked = peopleWhoBooked;
  }
  
  @Column(length = 15)
  public String getPhoenNumber() {
    return phoenNumber;
  }

  public void setPhoenNumber(String phoenNumber) {
    this.phoenNumber = phoenNumber;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

@Index(name = "booking_registration_tenantid")
public Long getTenantID() {
	return tenantID;
}

public void setTenantID(Long tenantID) {
	this.tenantID = tenantID;
}
  
  
}
