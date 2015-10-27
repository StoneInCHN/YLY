package com.yly.json.response;

import java.util.List;
import java.util.Map;

import com.yly.entity.commonenum.CommonEnum.TreeNodeState;

/**
 * 
 * @author tanbiao
 * 
 * id：节点ID，对加载远程数据很重要。 
 * text：显示节点文本。 
 * state：节点状态，'open' 或E'closed'，默认：'open'。如果为'closed'的时候，将不自动展开该节点。
 * checked：表示该节点是否被选中。 
 * attributes:被添加到节点的自定义属性。 
 * children: 一个节点数组声明了若干节点。
 */
public class TreeNodeResponse {

  /**
   * id：节点ID，对加载远程数据很重要。
   */
  private Long id;
  /**
   * text：显示节点文本。
   */
  private String text;
  /**
   * state：节点状态，'open' 或 'closed'，默认：'open'。如果为'closed'的时候，将不自动展开该节点。
   */
  private TreeNodeState state;
  /**
   * checked：表示该节点是否被选中。
   */
  private Boolean checked;
  
  /**
   * attributes 被添加到节点的自定义属性。 
   */
  private Map<String, Object> attributes;
  
  /**
   * children: 一个节点数组声明了若干节点。
   */
  private List<TreeNodeResponse> children;

  /**
   * 节点要展示的图标
   */
  private String iconCls;
  
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public TreeNodeState getState() {
    return state;
  }

  public void setState(TreeNodeState state) {
    this.state = state;
  }

  public Boolean getChecked() {
    return checked;
  }

  public void setChecked(Boolean checked) {
    this.checked = checked;
  }
  
  public Map<String, Object> getAttributes() {
    return attributes;
  }

  public void setAttributes(Map<String, Object> attributes) {
    this.attributes = attributes;
  }

  public List<TreeNodeResponse> getChildren() {
    return children;
  }

  public void setChildren(List<TreeNodeResponse> children) {
    this.children = children;
  }

  public String getIconCls() {
    return iconCls;
  }

  public void setIconCls(String iconCls) {
    this.iconCls = iconCls;
  }
  
}
