package com.yly.entity;

import java.util.Date;

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
@Table (name = "yly_report_repair_record_result")
@SequenceGenerator (name = "sequenceGenerator", sequenceName = "yly_report_repair_record_sequence")
public class ReportRepairRecord extends BaseEntity
{

  private static final long serialVersionUID = 4063994934255971594L;

  /**
   * 租户ID
   */
  private Long tenantID;
 
  /**
   * 维修次数
   */
  private int repairedCount;
  
  /**
   * 统计周期
   */
  private Date repairedStatiticsCycle;

  @Index (name = "report_repair_record_tenantid")
  public Long getTenantID ()
  {
    return tenantID;
  }

  public void setTenantID (Long tenantID)
  {
    this.tenantID = tenantID;
  }

  public int getRepairedCount ()
  {
    return repairedCount;
  }

  public void setRepairedCount (int repairedCount)
  {
    this.repairedCount = repairedCount;
  }

  public Date getRepairedStatiticsCycle ()
  {
    return repairedStatiticsCycle;
  }

  public void setRepairedStatiticsCycle (Date repairedStatiticsCycle)
  {
    this.repairedStatiticsCycle = repairedStatiticsCycle;
  }

}
