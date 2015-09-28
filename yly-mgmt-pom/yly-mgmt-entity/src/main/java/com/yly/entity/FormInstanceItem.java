package com.yly.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.yly.entity.base.BaseEntity;

/**
 * 提交的表单实例详情
 * 
 * @author sujinxuan
 *
 */
@Entity
@Table(name = "yly_form_instance_detail")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "yly_form_instance_detail_sequence")
public class FormInstanceItem extends BaseEntity {

  private static final long serialVersionUID = 7695921109630786162L;

  /**
   * 提交的表单实例
   */
  private FormInstance formInstance;

  /**
   * 表单详情
   */
  private FormItem formItem;

  /**
   * 字段值
   */
  private String fieldValue;

  @ManyToOne
  public FormItem getFormItem() {
    return formItem;
  }

  public void setFormItem(FormItem formItem) {
    this.formItem = formItem;
  }

  @Column(length = 100)
  public String getFieldValue() {
    return fieldValue;
  }

  public void setFieldValue(String fieldValue) {
    this.fieldValue = fieldValue;
  }

  @ManyToOne
  public FormInstance getFormInstance() {
    return formInstance;
  }

  public void setFormInstance(FormInstance formInstance) {
    this.formInstance = formInstance;
  }



}
