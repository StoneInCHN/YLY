package com.yly.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Index;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yly.entity.base.BaseEntity;
import com.yly.entity.commonenum.CommonEnum.EducationLevel;
import com.yly.entity.commonenum.CommonEnum.Gender;
import com.yly.entity.commonenum.CommonEnum.HousingInfo;
import com.yly.entity.commonenum.CommonEnum.LivingState;
import com.yly.entity.commonenum.CommonEnum.MarriageState;
import com.yly.entity.commonenum.CommonEnum.MedicalExpPaymentWay;
import com.yly.entity.commonenum.CommonEnum.PaymentWay;
import com.yly.entity.commonenum.CommonEnum.PoliticalOutlook;
import com.yly.entity.commonenum.CommonEnum.Religion;
import com.yly.entity.commonenum.CommonEnum.SourceOfIncome;

/**
 * 老年人信息
 * 
 * @author shijun
 *
 */
@Entity
@Table(name = "yly_elderly_info")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "yly_elderly_info_sequence")
public class ElderlyInfo extends BaseEntity {

  private static final long serialVersionUID = 1L;
  /**
   * 租户ID
   */
  private Long tenantID;

  /**
   * 老人编号
   */
  private String identifier;

  /**
   * 姓名
   */
  private String name;

  /**
   * 性别
   */
  private Gender gender;

  /**
   * 照片
   */
  private String photo;

  /** 文件 */
  private MultipartFile photoFile;

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
   * 社保卡号
   */
  private String socialInsuranceNumber;

  /**
   * 评估结果
   */
  private SystemConfig evaluatingResult;

  /**
   * 评估分数
   */
  private Float evaluatingScore;

  /**
   * 床位
   */
  private Bed bed;

  /**
   * 老人电话号码
   */
  private String elderlyPhoneNumber;

  /**
   * 人员类别
   */
  private SystemConfig personnelCategory;

  /**
   * 户口地址
   */
  private String registeredResidence;

  /**
   * 现居地址
   */
  private String residentialAddress;

  /**
   * 原来工作单位
   */
  private String originalCompany;

  /**
   * 职位
   */
  private String position;

  /**
   * 曾获荣誉
   */
  private String honors;

  /**
   * 爱好
   */
  private String hobbies;

  /**
   * 个人习惯
   */
  private String personalHabits;

  /**
   * 费用支付情况
   */
  private PaymentWay paymentWay;

  /**
   * 护理级别
   */
  private SystemConfig nursingLevel;

  /**
   * 是否伙食费包月
   */
  private Boolean mealFeeMonthlyPayment;

  /**
   * 文化程度
   */
  private EducationLevel educationLevel;

  /**
   * 政治面貌
   */
  private PoliticalOutlook politicalOutlook;

  /**
   * 宗教
   */
  private Religion religion;

  /**
   * 婚姻状况
   */
  private MarriageState marriageState;

  /**
   * 入院时间
   */
  private Date beHospitalizedDate;

  /**
   * 居住情况
   */
  private LivingState livingState;

  /**
   * 住房信息
   */
  private HousingInfo housingInfo;

  /**
   * 医疗费用支付方式
   */
  private MedicalExpPaymentWay medicalExpPaymentWay;

  /**
   * 月收入
   */
  private BigDecimal monthlyIncome;

  /**
   * 收入来源
   */
  private SourceOfIncome sourceOfIncome;

  /**
   * 委托人
   */
  private ElderlyConsigner elderlyConsigner;

  /**
   * 家庭成员
   */
  private List<ElderlyFamilyMembers> elderlyFamilyMembers = new ArrayList<ElderlyFamilyMembers>();

  /**
   * 评估记录
   */
  private Set<ElderlyEvaluatingRecord> elderlyEvaluatingRecords =
      new HashSet<ElderlyEvaluatingRecord>();

  /**
   * 相册
   */
  private Set<ElderlyPhotoAlbum> elderlyPhotoAlbum = new HashSet<ElderlyPhotoAlbum>();

  /**
   * 押金
   */
  private Deposit deposit;

  /**
   * 床位护理费
   */
  private Set<BedNurseCharge> bedNurseCharges = new HashSet<BedNurseCharge>();

  /**
   * 伙食费
   */
  private Set<MealCharge> mealCharges = new HashSet<MealCharge>();

  /**
   * 个性化服务费
   */
  private Set<PersonalizedCharge> personalizedCharges = new HashSet<PersonalizedCharge>();

  /**
   * 水电费
   */
  private Set<WaterElectricityCharge> waterElectricityCharges =
      new HashSet<WaterElectricityCharge>();

  /**
   * 预存款
   */
  private Set<AdvanceCharge> advanceCharges = new HashSet<AdvanceCharge>();

  /**
   * 老人探望记录
   */
  private Set<VisitElderlyRecord> visitElderlyRecords = new HashSet<VisitElderlyRecord>();

  /**
   * 病历
   */
  private Set<MedicalRecord> medicalRecords = new HashSet<MedicalRecord>();

  /**
   * 处方
   */
  private Set<Prescription> prescriptions = new HashSet<Prescription>();

  /**
   * 床位位置
   */
  private String bedLocation;

  /**
   * 伙食类型
   */
  private SystemConfig mealType;

  
  @ManyToOne
  public SystemConfig getMealType() {
    return mealType;
  }

  public void setMealType(SystemConfig mealType) {
    this.mealType = mealType;
  }


  public void setBedLocation(String bedLocation) {
    this.bedLocation = bedLocation;
  }

  /**
   * 获取床位所在位置（格式：楼宇+床位号+房间类型）
   * 
   * @return
   */
  @Transient
  public String getBedLocation() {
    StringBuffer str = new StringBuffer();
    Room room = bed.getRoom();
    str.append(room.getBuilding().getBuildingName());
    str.append(bed.getBedNumber());
    str.append(room.getRoomType().getConfigValue());
    bedLocation = str.toString();
    return bedLocation;
  }

  @OneToMany(mappedBy = "elderlyInfo")
  public Set<VisitElderlyRecord> getVisitElderlyRecords() {
    return visitElderlyRecords;
  }

  public void setVisitElderlyRecords(Set<VisitElderlyRecord> visitElderlyRecords) {
    this.visitElderlyRecords = visitElderlyRecords;
  }

  @OneToMany(mappedBy = "elderlyInfo")
  public Set<AdvanceCharge> getAdvanceCharges() {
    return advanceCharges;
  }

  public void setAdvanceCharges(Set<AdvanceCharge> advanceCharges) {
    this.advanceCharges = advanceCharges;
  }

  @OneToMany(mappedBy = "elderlyInfo")
  public Set<WaterElectricityCharge> getWaterElectricityCharges() {
    return waterElectricityCharges;
  }

  public void setWaterElectricityCharges(Set<WaterElectricityCharge> waterElectricityCharges) {
    this.waterElectricityCharges = waterElectricityCharges;
  }

  @OneToMany(mappedBy = "elderlyInfo")
  public Set<PersonalizedCharge> getPersonalizedCharges() {
    return personalizedCharges;
  }

  public void setPersonalizedCharges(Set<PersonalizedCharge> personalizedCharges) {
    this.personalizedCharges = personalizedCharges;
  }

  @OneToMany(mappedBy = "elderlyInfo")
  public Set<MealCharge> getMealCharges() {
    return mealCharges;
  }

  public void setMealCharges(Set<MealCharge> mealCharges) {
    this.mealCharges = mealCharges;
  }

  @OneToMany(mappedBy = "elderlyInfo")
  public Set<BedNurseCharge> getBedNurseCharges() {
    return bedNurseCharges;
  }

  public void setBedNurseCharges(Set<BedNurseCharge> bedNurseCharges) {
    this.bedNurseCharges = bedNurseCharges;
  }

  @OneToOne(mappedBy = "elderlyInfo")
  public Deposit getDeposit() {
    return deposit;
  }

  public void setDeposit(Deposit deposit) {
    this.deposit = deposit;
  }

  @Index(name = "elderly_info_tenantid")
  public Long getTenantID() {
    return tenantID;
  }

  public void setTenantID(Long tenantID) {
    this.tenantID = tenantID;
  }

  @OneToMany(mappedBy = "elderlyInfo", fetch = FetchType.LAZY)
  public Set<ElderlyEvaluatingRecord> getElderlyEvaluatingRecords() {
    return elderlyEvaluatingRecords;
  }

  public void setElderlyEvaluatingRecords(Set<ElderlyEvaluatingRecord> elderlyEvaluatingRecords) {
    this.elderlyEvaluatingRecords = elderlyEvaluatingRecords;
  }

  @OneToOne(mappedBy = "elderlyInfo", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
  public ElderlyConsigner getElderlyConsigner() {
    return elderlyConsigner;
  }

  public void setElderlyConsigner(ElderlyConsigner elderlyConsigner) {
    this.elderlyConsigner = elderlyConsigner;
  }

  @ElementCollection
  @LazyCollection(LazyCollectionOption.FALSE)
  @CollectionTable(name = "yly_elderly_family_members")
  public List<ElderlyFamilyMembers> getElderlyFamilyMembers() {
    return elderlyFamilyMembers;
  }

  public void setElderlyFamilyMembers(List<ElderlyFamilyMembers> elderlyFamilyMembers) {
    this.elderlyFamilyMembers = elderlyFamilyMembers;
  }

  @JsonProperty
  @Column(length = 15)
  public String getIdentifier() {
    return identifier;
  }

  public void setIdentifier(String identifier) {
    this.identifier = identifier;
  }

  @JsonProperty
  @Column(length = 15)
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @JsonProperty
  public Gender getGender() {
    return gender;
  }

  public void setGender(Gender gender) {
    this.gender = gender;
  }

  @Column(length = 50)
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

  @JsonProperty
  @Column(length = 25)
  public String getIDCard() {
    return IDCard;
  }

  public void setIDCard(String iDCard) {
    IDCard = iDCard;
  }

  @JsonProperty
  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  @JsonProperty
  public Date getBirthday() {
    return birthday;
  }

  public void setBirthday(Date birthday) {
    this.birthday = birthday;
  }

  @Column(length = 25)
  public String getSocialInsuranceNumber() {
    return socialInsuranceNumber;
  }

  public void setSocialInsuranceNumber(String socialInsuranceNumber) {
    this.socialInsuranceNumber = socialInsuranceNumber;
  }

  @ManyToOne
  public SystemConfig getEvaluatingResult() {
    return evaluatingResult;
  }

  public void setEvaluatingResult(SystemConfig evaluatingResult) {
    this.evaluatingResult = evaluatingResult;
  }

  public Float getEvaluatingScore() {
    return evaluatingScore;
  }

  public void setEvaluatingScore(Float evaluatingScore) {
    this.evaluatingScore = evaluatingScore;
  }

  @JsonProperty
  @OneToOne(mappedBy = "elderlyInfo")
  public Bed getBed() {
    return bed;
  }


  public void setBed(Bed bed) {
    this.bed = bed;
  }

  @JsonProperty
  @Column(length = 15)
  public String getElderlyPhoneNumber() {
    return elderlyPhoneNumber;
  }

  public void setElderlyPhoneNumber(String elderlyPhoneNumber) {
    this.elderlyPhoneNumber = elderlyPhoneNumber;
  }

  @ManyToOne
  public SystemConfig getPersonnelCategory() {
    return personnelCategory;
  }

  public void setPersonnelCategory(SystemConfig personnelCategory) {
    this.personnelCategory = personnelCategory;
  }

  @Column(length = 150)
  public String getRegisteredResidence() {
    return registeredResidence;
  }

  public void setRegisteredResidence(String registeredResidence) {
    this.registeredResidence = registeredResidence;
  }

  @Column(length = 150)
  public String getResidentialAddress() {
    return residentialAddress;
  }

  public void setResidentialAddress(String residentialAddress) {
    this.residentialAddress = residentialAddress;
  }

  @Column(length = 120)
  public String getOriginalCompany() {
    return originalCompany;
  }

  public void setOriginalCompany(String originalCompany) {
    this.originalCompany = originalCompany;
  }

  @Column(length = 20)
  public String getPosition() {
    return position;
  }

  public void setPosition(String position) {
    this.position = position;
  }

  @Column(length = 150)
  public String getHonors() {
    return honors;
  }

  public void setHonors(String honors) {
    this.honors = honors;
  }

  @Column(length = 150)
  public String getHobbies() {
    return hobbies;
  }

  public void setHobbies(String hobbies) {
    this.hobbies = hobbies;
  }

  @Column(length = 150)
  public String getPersonalHabits() {
    return personalHabits;
  }

  public void setPersonalHabits(String personalHabits) {
    this.personalHabits = personalHabits;
  }

  public PaymentWay getPaymentWay() {
    return paymentWay;
  }

  public void setPaymentWay(PaymentWay paymentWay) {
    this.paymentWay = paymentWay;
  }

  @ManyToOne
  public SystemConfig getNursingLevel() {
    return nursingLevel;
  }

  public void setNursingLevel(SystemConfig nursingLevel) {
    this.nursingLevel = nursingLevel;
  }

  public Boolean getMealFeeMonthlyPayment() {
    return mealFeeMonthlyPayment;
  }

  public void setMealFeeMonthlyPayment(Boolean mealFeeMonthlyPayment) {
    this.mealFeeMonthlyPayment = mealFeeMonthlyPayment;
  }

  public EducationLevel getEducationLevel() {
    return educationLevel;
  }

  public void setEducationLevel(EducationLevel educationLevel) {
    this.educationLevel = educationLevel;
  }

  public PoliticalOutlook getPoliticalOutlook() {
    return politicalOutlook;
  }

  public void setPoliticalOutlook(PoliticalOutlook politicalOutlook) {
    this.politicalOutlook = politicalOutlook;
  }

  public Religion getReligion() {
    return religion;
  }

  public void setReligion(Religion religion) {
    this.religion = religion;
  }

  public MarriageState getMarriageState() {
    return marriageState;
  }

  public void setMarriageState(MarriageState marriageState) {
    this.marriageState = marriageState;
  }

  public Date getBeHospitalizedDate() {
    return beHospitalizedDate;
  }

  public void setBeHospitalizedDate(Date beHospitalizedDate) {
    this.beHospitalizedDate = beHospitalizedDate;
  }

  public LivingState getLivingState() {
    return livingState;
  }

  public void setLivingState(LivingState livingState) {
    this.livingState = livingState;
  }

  public MedicalExpPaymentWay getMedicalExpPaymentWay() {
    return medicalExpPaymentWay;
  }

  public void setMedicalExpPaymentWay(MedicalExpPaymentWay medicalExpPaymentWay) {
    this.medicalExpPaymentWay = medicalExpPaymentWay;
  }

  public BigDecimal getMonthlyIncome() {
    return monthlyIncome;
  }

  public void setMonthlyIncome(BigDecimal monthlyIncome) {
    this.monthlyIncome = monthlyIncome;
  }

  public SourceOfIncome getSourceOfIncome() {
    return sourceOfIncome;
  }

  public void setSourceOfIncome(SourceOfIncome sourceOfIncome) {
    this.sourceOfIncome = sourceOfIncome;
  }

  @Column(length = 150)
  public String getPhoto() {
    return photo;
  }

  public void setPhoto(String photo) {
    this.photo = photo;
  }

  @Transient
  public MultipartFile getPhotoFile() {
    return photoFile;
  }

  public void setPhotoFile(MultipartFile photoFile) {
    this.photoFile = photoFile;
  }


  // public MultipartFile getFile() {
  // return file;
  // }
  //
  //
  // public void setFile(MultipartFile file) {
  // this.file = file;
  // }

  @OneToMany(mappedBy = "elderlyInfo", fetch = FetchType.LAZY)
  public Set<ElderlyPhotoAlbum> getElderlyPhotoAlbum() {
    return elderlyPhotoAlbum;
  }

  public void setElderlyPhotoAlbum(Set<ElderlyPhotoAlbum> elderlyPhotoAlbum) {
    this.elderlyPhotoAlbum = elderlyPhotoAlbum;
  }

  @OneToMany(mappedBy = "elderlyInfo", fetch = FetchType.LAZY)
  public Set<MedicalRecord> getMedicalRecords() {
    return medicalRecords;
  }

  public void setMedicalRecords(Set<MedicalRecord> medicalRecords) {
    this.medicalRecords = medicalRecords;
  }

  @OneToMany(mappedBy = "elderlyInfo", fetch = FetchType.LAZY)
  public Set<Prescription> getPrescriptions() {
    return prescriptions;
  }

  public void setPrescriptions(Set<Prescription> prescriptions) {
    this.prescriptions = prescriptions;
  }

  public HousingInfo getHousingInfo() {
    return housingInfo;
  }

  public void setHousingInfo(HousingInfo housingInfo) {
    this.housingInfo = housingInfo;
  }
}
