package com.yly.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Index;

import com.yly.entity.base.BaseEntity;

/**
 * 员工提交的表单
 * 
 * @author sujinxuan
 *
 */
@Entity
@Table(name = "yly_form_instance")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "yly_form_instance_sequence")
public class FormInstance extends BaseEntity {

  private static final long serialVersionUID = 9114793424588879204L;

  /**
   * 租户ID
   */
  private Long tenantID;

  /**
   * 提交表单员工
   */
  private TenantUser tenantUser;

  /**
   * 员工提交的表单详情
   */
  private Set<FormInstanceItem> formInstanceItems = new HashSet<FormInstanceItem>();

  /**
   * 表单实例对应的流程任务实例
   */
  private WorkProcessInstance workProcessInstance;

  /**
   * 自定义表单
   */
  private FormTemplate formTemplate;

  /**
   * 表单类型
   */
  private SystemConfig formType;


  @OneToOne
  public WorkProcessInstance getWorkProcessInstance() {
    return workProcessInstance;
  }

  public void setWorkProcessInstance(WorkProcessInstance workProcessInstance) {
    this.workProcessInstance = workProcessInstance;
  }

  @ManyToOne
  public SystemConfig getFormType() {
    return formType;
  }

  public void setFormType(SystemConfig formType) {
    this.formType = formType;
  }

  @ManyToOne
  public FormTemplate getFormTemplate() {
    return formTemplate;
  }

  public void setFormTemplate(FormTemplate formTemplate) {
    this.formTemplate = formTemplate;
  }

  @OneToMany(mappedBy = "formInstance")
  public Set<FormInstanceItem> getFormInstanceItems() {
    return formInstanceItems;
  }

  public void setFormInstanceItems(Set<FormInstanceItem> formInstanceItems) {
    this.formInstanceItems = formInstanceItems;
  }

  @ManyToOne
  public TenantUser getTenantUser() {
    return tenantUser;
  }

  public void setTenantUser(TenantUser tenantUser) {
    this.tenantUser = tenantUser;
  }

  @Index(name = "form_instance_tenantid")
  public Long getTenantID() {
    return tenantID;
  }

  public void setTenantID(Long tenantID) {
    this.tenantID = tenantID;
  }
}
