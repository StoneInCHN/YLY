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
 * 租户自定义表单
 * 
 * @author sujinxuan
 *
 */
@Entity
@Table(name = "yly_form_info")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "yly_form_info_sequence")
public class FormTemplate extends BaseEntity {

  private static final long serialVersionUID = 2342688410044202569L;

  /**
   * 租户ID
   */
  private Long tenantID;

  /**
   * 表单名称
   */
  private String formName;

  /**
   * 表单创建人
   */
  private String formCreator;

  /**
   * 租户自定义流程
   */
  private WorkProcess workProcess;

  /**
   * 表单实例
   */
  private Set<FormInstance> formInstances = new HashSet<FormInstance>();

  /**
   * 表单项
   */
  private Set<FormItem> formItems = new HashSet<FormItem>();
  /**
   * 表单类型
   */
  private SystemConfig formType;



  @OneToMany(mappedBy = "formTemplate")
  public Set<FormItem> getFormItems() {
    return formItems;
  }

  public void setFormItems(Set<FormItem> formItems) {
    this.formItems = formItems;
  }

  @ManyToOne
  public SystemConfig getFormType() {
    return formType;
  }

  public void setFormType(SystemConfig formType) {
    this.formType = formType;
  }

  @OneToMany(mappedBy = "formTemplate")
  public Set<FormInstance> getFormInstances() {
    return formInstances;
  }

  public void setFormInstances(Set<FormInstance> formInstances) {
    this.formInstances = formInstances;
  }

  @OneToOne(mappedBy = "formTemplate")
  public WorkProcess getWorkProcess() {
    return workProcess;
  }

  public String getFormName() {
    return formName;
  }

  public void setWorkProcess(WorkProcess workProcess) {
    this.workProcess = workProcess;
  }

  public void setFormName(String formName) {
    this.formName = formName;
  }

  public String getFormCreator() {
    return formCreator;
  }

  public void setFormCreator(String formCreator) {
    this.formCreator = formCreator;
  }

  @Index(name = "form_template_tenantid")
  public Long getTenantID() {
    return tenantID;
  }

  public void setTenantID(Long tenantID) {
    this.tenantID = tenantID;
  }
}
