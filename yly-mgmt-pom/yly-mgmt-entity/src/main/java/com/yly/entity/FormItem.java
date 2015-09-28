package com.yly.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.yly.entity.base.BaseEntity;
import com.yly.entity.commonenum.CommonEnum.ComponentType;

/**
 * 租户自定义表单字段
 * 
 * @author sujinxuan
 *
 */
@Entity
@Table(name = "yly_form_detail")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "yly_form_detail_sequence")
public class FormItem extends BaseEntity {

  private static final long serialVersionUID = -8153295181195884090L;

  /**
   * 表单template
   */
  private FormTemplate formTemplate;

  /**
   * 表单控件
   */
  private ComponentType componentType;

  /**
   * 控件字段名
   */
  private String name;

  /**
   * 控件长度
   */
  private String length;

  /**
   * 控件所在行
   */
  private Integer rowNo;

  /**
   * 控件所在列
   */
  private Integer columnNo;

  /**
   * 控件默认值
   */
  private String defaultValue;

  /**
   * 表单实例详情
   */
  private Set<FormInstanceItem> formInstanceItems = new HashSet<FormInstanceItem>();


  @OneToMany(mappedBy = "formItem")
  public Set<FormInstanceItem> getFormInstanceItems() {
    return formInstanceItems;
  }

  public void setFormInstanceItems(Set<FormInstanceItem> formInstanceItems) {
    this.formInstanceItems = formInstanceItems;
  }

  @ManyToOne
  public FormTemplate getFormTemplate() {
    return formTemplate;
  }


  public void setFormTemplate(FormTemplate formTemplate) {
    this.formTemplate = formTemplate;
  }

  public ComponentType getComponentType() {
    return componentType;
  }

  public void setComponentType(ComponentType componentType) {
    this.componentType = componentType;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getLength() {
    return length;
  }

  public void setLength(String length) {
    this.length = length;
  }

  public Integer getColumnNo() {
    return columnNo;
  }

  public void setColumnNo(Integer columnNo) {
    this.columnNo = columnNo;
  }

  public Integer getRowNo() {
    return rowNo;
  }

  public void setRowNo(Integer rowNo) {
    this.rowNo = rowNo;
  }

  public String getDefaultValue() {
    return defaultValue;
  }

  public void setDefaultValue(String defaultValue) {
    this.defaultValue = defaultValue;
  }

}
