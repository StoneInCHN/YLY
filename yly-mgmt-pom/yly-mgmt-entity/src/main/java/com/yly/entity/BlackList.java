package com.yly.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.yly.entity.base.BaseEntity;
import com.yly.entity.commonenum.CommonEnum.Gender;

/**
 * 黑名单
 * 
 * @author sujinxuan
 *
 */
@Entity
@Table(name = "yly_blacklist")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "yly_blacklist_sequence")
public class BlackList extends BaseEntity {

  
  private static final long serialVersionUID = 3525183712762376969L;

  /**
   * 老人姓名
   */
  private String name;
  
  /**
   * 所在养老院
   */
  private String geracomium;
  
  /**
   * 性别
   */
  private Gender gender;
  
  /**
   * 照片
   */
  private String photo;
  
  /**
   * 籍贯
   */
  private String placeOfOrigin;

  /**
   * 民族
   */
  private String nation;

  /**
   * 身份证号
   */
  private String IDCard;

  /**
   * 年龄
   */
  private Integer age;

  /**
   * 出生日期
   */
  private Date birthday;

  /**
   * 联系电话
   */
  private String contactPhone;

  /**
   * 户口地址
   */
  private String registeredResidence;

  /**
   * 现居地址
   */
  private String residentialAddress;
  
  /**
   * 加入黑名单原因
   */
  private String cause;
  
  /**
   * 备注
   */
  private String remark;
  
  
  /**
   * 租户ID
   */
  private Long tenantID;
  
  public Long getTenantID() {
    return tenantID;
  }

  public void setTenantID(Long tenantID) {
    this.tenantID = tenantID;
  }
  
  public String getGeracomium() {
    return geracomium;
  }

  public void setGeracomium(String geracomium) {
    this.geracomium = geracomium;
  }

  @Column(length = 200)
  public String getCause() {
    return cause;
  }

  public void setCause(String cause) {
    this.cause = cause;
  }

  @Column(length = 50)
  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  @Column(length = 15)
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Gender getGender() {
    return gender;
  }

  public void setGender(Gender gender) {
    this.gender = gender;
  }

  @Column(length = 20)
  public String getPlaceOfOrigin() {
    return placeOfOrigin;
  }

  public void setPlaceOfOrigin(String placeOfOrigin) {
    this.placeOfOrigin = placeOfOrigin;
  }
  
  @Column(length = 10)
  public String getNation() {
    return nation;
  }

  public void setNation(String nation) {
    this.nation = nation;
  }

  @Column(length = 25)
  public String getIDCard() {
    return IDCard;
  }

  public void setIDCard(String iDCard) {
    IDCard = iDCard;
  }

  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  public Date getBirthday() {
    return birthday;
  }

  public void setBirthday(Date birthday) {
    this.birthday = birthday;
  }

  @Column(length = 100)
  public String getRegisteredResidence() {
    return registeredResidence;
  }

  public void setRegisteredResidence(String registeredResidence) {
    this.registeredResidence = registeredResidence;
  }

  @Column(length = 100)
  public String getResidentialAddress() {
    return residentialAddress;
  }

  public void setResidentialAddress(String residentialAddress) {
    this.residentialAddress = residentialAddress;
  }

  @Column(length=300)
  public String getPhoto() {
    return photo;
  }

  public void setPhoto(String photo) {
    this.photo = photo;
  }

  @Column(length = 30)
  public String getContactPhone() {
    return contactPhone;
  }

  public void setContactPhone(String contactPhone) {
    this.contactPhone = contactPhone;
  }
  
}
