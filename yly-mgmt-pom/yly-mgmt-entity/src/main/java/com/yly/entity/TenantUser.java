package com.yly.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Index;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.FieldBridge;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.search.annotations.Store;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yly.entity.base.BaseEntity;
import com.yly.entity.commonenum.CommonEnum.Gender;
import com.yly.entity.commonenum.CommonEnum.StaffStatus;
import com.yly.lucene.DateBridgeImpl;

/**
 * 租户用户
 * 
 * @author shijun
 *
 */
@Entity
@Table(name = "yly_tenant_user")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "yly_tenant_user_sequence")
@Indexed(index= "tenantUser")
public class TenantUser extends BaseEntity {

  private static final long serialVersionUID = -665961639617388534L;

  /**
   * 租户ID
   */
  private Long tenantID;

  /** E-mail*/
  private String email;

  /** 姓名 */
  private String realName;
  
  /**性别*/
  private Gender gender;
  
  /**年龄*/
  private Integer age;
  
  /**相片*/
  private String photo;
  
  /**身份证号码*/
  private String IDCard;
  
  /**员工编号*/
  private String staffID;
  
  /**工作年限*/
  private Integer workingYear;
  
  /**员工状态*/
  private StaffStatus staffStatus;
  
  /** 出生日期 */
  private Date birthDay;

  /** 地址 */
  private String address;

  /** 邮编 */
  private String zipCode;

  /** 电话 */
  private String telephone;

  /** 手机 */
  private String mobile;
  
  /**所在部门*/
  private Department department;
  
  /**担任职务*/
  private Position position;
  
  /**入职时间*/
  private Date hireDate; 
  
  /**
   * 表单实例
   */
  private Set<FormInstance> formInstances = new HashSet<FormInstance>();
  /**
   * 用户登录账号
   */
  private Set<TenantAccount> tenantAccounts = new HashSet<TenantAccount>();
  
  @OneToMany(mappedBy="tenantUser")
  public Set<TenantAccount> getTenantAccounts() {
    return tenantAccounts;
  }

  public void setTenantAccounts(Set<TenantAccount> tenantAccounts) {
    this.tenantAccounts = tenantAccounts;
  }

  @OneToMany(mappedBy="tenantUser")
  public Set<FormInstance> getFormInstances() {
    return formInstances;
  }

  public void setFormInstances(Set<FormInstance> formInstances) {
    this.formInstances = formInstances;
  }
  
  @JsonProperty
  public Gender getGender() {
    return gender;
  }

  public void setGender(Gender gender) {
    this.gender = gender;
  }

  @JsonProperty
  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  @Column(length=300)
  public String getPhoto() {
    return photo;
  }

  public void setPhoto(String photo) {
    this.photo = photo;
  }

  @Column(length=30)
  public String getIDCard() {
    return IDCard;
  }

  public void setIDCard(String iDCard) {
    IDCard = iDCard;
  }

  @Column(length=30)
  @JsonProperty
  public String getStaffID() {
    return staffID;
  }

  public void setStaffID(String staffID) {
    this.staffID = staffID;
  }

  public Integer getWorkingYear() {
    return workingYear;
  }

  public void setWorkingYear(Integer workingYear) {
    this.workingYear = workingYear;
  }

  @JsonProperty
  @Field(store = Store.NO, index = org.hibernate.search.annotations.Index.UN_TOKENIZED, analyzer = @Analyzer(impl = IKAnalyzer.class))
  public StaffStatus getStaffStatus() {
    return staffStatus;
  }

  public void setStaffStatus(StaffStatus staffStatus) {
    this.staffStatus = staffStatus;
  }

  public Date getBirthDay() {
    return birthDay;
  }

  public void setBirthDay(Date birthDay) {
    this.birthDay = birthDay;
  }

  @Column(length=200)
  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  @Column(length=20)
  public String getZipCode() {
    return zipCode;
  }

  public void setZipCode(String zipCode) {
    this.zipCode = zipCode;
  }

  @Column(length=30)
  public String getTelephone() {
    return telephone;
  }

  public void setTelephone(String telephone) {
    this.telephone = telephone;
  }

  @Column(length=30)
  public String getMobile() {
    return mobile;
  }

  public void setMobile(String mobile) {
    this.mobile = mobile;
  }

  @ManyToOne
  @JsonProperty
  @IndexedEmbedded
  public Department getDepartment() {
    return department;
  }

  public void setDepartment(Department department) {
    this.department = department;
  }

  @ManyToOne
  @JsonProperty
  @IndexedEmbedded
  public Position getPosition() {
    return position;
  }

  public void setPosition(Position position) {
    this.position = position;
  }

  @JsonProperty
  @Field(index = org.hibernate.search.annotations.Index.UN_TOKENIZED, store = Store.NO)
  @FieldBridge(impl = DateBridgeImpl.class)
  public Date getHireDate() {
    return hireDate;
  }

  public void setHireDate(Date hireDate) {
    this.hireDate = hireDate;
  }

  @Column(length=60)
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  @Column(length=20)
  @JsonProperty
  @Field(store = Store.NO, index = org.hibernate.search.annotations.Index.TOKENIZED, analyzer = @Analyzer(impl = IKAnalyzer.class))
  public String getRealName() {
    return realName;
  }

  public void setRealName(String realName) {
    this.realName = realName;
  }

  @Index(name="tenant_user_tenantid")
  public Long getTenantID() {
    return tenantID;
  }

  public void setTenantID(Long tenantID) {
    this.tenantID = tenantID;
  }

}
