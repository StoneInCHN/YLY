package com.yly.json.response;

/**
 * 用于ztree树加载
 * 
 * @author tanbiao
 *
 */
public class TreeResponse {

  /**
   * 节点ID
   */
  private Long id;
  /**
   * 父节点Id
   */
  private Long parentId;
  /**
   * 显示的文本名称
   */
  private String name;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getParentId() {
    return parentId;
  }

  public void setParentId(Long parentId) {
    this.parentId = parentId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

}
