package com.yly.entity;

import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Index;

import com.yly.entity.base.BaseEntity;

/**
 * 
 * 
 * @author yohu
 *
 */
@Entity
@Table (name = "yly_report_charge_record")
@SequenceGenerator (name = "sequenceGenerator", sequenceName = "yly_report_charge_record_sequence")
public class ReportChargeRecord extends BaseEntity
{

  private static final long serialVersionUID = 4063994934255971594L;

  /**
   * 租户ID
   */
  private Long tenantID;
 
  /**
   * 预存缴费
   */
  private int advanceCharge;
  
  /**
   * 床位护理费缴费
   */
  private int BedNurseCharge;
  
  /**
   * 伙食费缴费
   */
  private int mealCharge;
  
  /**
   * 个性化服务费缴费
   */
  private int personalizedCharge;
  
  /**
   * 水电费
   */
  private int waterElectricityCharge;
  
  /**
   * 日期
   */
  private int Date;
  
  @Index (name = "report_charge_record_tenantid")
  public Long getTenantID ()
  {
    return tenantID;
  }

  public void setTenantID (Long tenantID)
  {
    this.tenantID = tenantID;
  }

  public int getAdvanceCharge ()
  {
    return advanceCharge;
  }

  public void setAdvanceCharge (int advanceCharge)
  {
    this.advanceCharge = advanceCharge;
  }

  public int getBedNurseCharge ()
  {
    return BedNurseCharge;
  }

  public void setBedNurseCharge (int bedNurseCharge)
  {
    BedNurseCharge = bedNurseCharge;
  }

  public int getMealCharge ()
  {
    return mealCharge;
  }

  public void setMealCharge (int mealCharge)
  {
    this.mealCharge = mealCharge;
  }

  public int getPersonalizedCharge ()
  {
    return personalizedCharge;
  }

  public void setPersonalizedCharge (int personalizedCharge)
  {
    this.personalizedCharge = personalizedCharge;
  }

  public int getWaterElectricityCharge ()
  {
    return waterElectricityCharge;
  }

  public void setWaterElectricityCharge (int waterElectricityCharge)
  {
    this.waterElectricityCharge = waterElectricityCharge;
  }

  public int getDate ()
  {
    return Date;
  }

  public void setDate (int date)
  {
    Date = date;
  }

}
