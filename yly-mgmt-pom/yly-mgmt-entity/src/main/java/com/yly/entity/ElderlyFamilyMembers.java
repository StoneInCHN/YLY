package com.yly.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.hibernate.annotations.Index;

import com.yly.entity.commonenum.CommonEnum.Relation;

@Embeddable
public class ElderlyFamilyMembers implements Serializable{

  /**
   * 
   */
  private static final long serialVersionUID = 7445603213049873537L;

  /**
   * 租户ID
   */
  private Long tenantID;
  
  /**
   * 家庭成员名称
   */
  private String memberName;

  /**
   * 电话号码
   */
  private String memberPhoneNumber;

  /**
   * 关系
   */
  private Relation  memberRelation;

  /**
   * 居住地址
   */
  private String  memberResidentialAddress;
  

  @Column(length = 15)
  public String getMemberName() {
    return memberName;
  }

  public void setMemberName(String memberName) {
    this.memberName = memberName;
  }

  @Column(length = 15)
  public String getMemberPhoneNumber() {
    return memberPhoneNumber;
  }

  public void setMemberPhoneNumber(String memberPhoneNumber) {
    this.memberPhoneNumber = memberPhoneNumber;
  }

  public Relation getMemberRelation() {
    return memberRelation;
  }

  public void setMemberRelation(Relation memberRelation) {
    this.memberRelation = memberRelation;
  }

  @Column(length = 150)
  public String getMemberResidentialAddress() {
    return memberResidentialAddress;
  }

  public void setMemberResidentialAddress(String memberResidentialAddress) {
    this.memberResidentialAddress = memberResidentialAddress;
  }

  @Index(name="elderly_family_members_tenantid")
  public Long getTenantID() {
    return tenantID;
  }

  public void setTenantID(Long tenantID) {
    this.tenantID = tenantID;
  }
}
