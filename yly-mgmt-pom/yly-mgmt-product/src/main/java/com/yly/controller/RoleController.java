package com.yly.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yly.beans.Message;
import com.yly.common.log.LogUtil;
import com.yly.controller.base.BaseController;
import com.yly.entity.AuthorityResource;
import com.yly.entity.Role;
import com.yly.framework.paging.Page;
import com.yly.framework.paging.Pageable;
import com.yly.json.response.TreeNodeResponse;
import com.yly.service.AuthorityService;
import com.yly.service.RoleService;
import com.yly.service.TenantAccountService;

/**
 * Controller - 角色
 * 
 * @author pengyanan
 *
 */
@Controller("roleController")
@RequestMapping("console/role")
public class RoleController extends BaseController {
  @Resource(name = "roleServiceImpl")
  private RoleService roleService;

  @Resource(name = "tenantAccountServiceImpl")
  private TenantAccountService tenantAccountService;

  @Resource(name = "authorityServiceImpl")
  private AuthorityService authorityService;

  /**
   * 界面展示
   * 
   * @param model
   * @return
   */
  @RequestMapping(value = "/role", method = RequestMethod.GET)
  public String list(ModelMap model) {
    return "/role/role";
  }

  /**
   * 列表
   * 
   * @param model
   * @param pageable
   * @return
   */
  @RequestMapping(value = "/list", method = RequestMethod.POST)
  public @ResponseBody Page<Role> list(Model model, Pageable pageable) {
    return roleService.findPage(pageable);
  }

  /**
   * 添加
   * 
   * @param role
   * @return
   */
  @RequestMapping(value = "/add", method = RequestMethod.POST)
  public @ResponseBody Message add(Role role) {
    if (role != null) {
      role.setTenantID(tenantAccountService.getCurrentTenantID());
      roleService.save(role);
    }
    return SUCCESS_MESSAGE;
  }

  /**
   * 权限树列表
   * 
   * @param pageable
   * @param id
   * @return
   */
  @RequestMapping(value = "/listAuth", method = RequestMethod.POST)
  public @ResponseBody List<TreeNodeResponse> listAuth(Pageable pageable, Long id) {
    List<TreeNodeResponse> treeNodeResponses = new ArrayList<TreeNodeResponse>();
    Map<Long, TreeNodeResponse> parentMap = new HashMap<Long, TreeNodeResponse>();
    Role role = roleService.find(id);
    Set<AuthorityResource> currentAuthList = null;// 当前角色的权限
    if (role != null && role.getAuthorityResources() != null) {
      currentAuthList = role.getAuthorityResources();
    }

    // 所有的权限
    List<AuthorityResource> authAll = authorityService.findAll();

    /*
     * 创建树结构，定义树结构只有2级，子节点必须有父节点Id,没有parentId的肯定是父节点
     */
    // 创建父节点
    for (AuthorityResource authorityResource : authAll) {
      TreeNodeResponse treeNodeResponse = new TreeNodeResponse();
      if (authorityResource.getParentId() == null) {
        treeNodeResponse.setId(authorityResource.getId());
        treeNodeResponse.setText(authorityResource.getAuthCnName());
        if (currentAuthList.contains(authorityResource)) {
          treeNodeResponse.setChecked(true);
        } else {
          treeNodeResponse.setChecked(false);
        }
        parentMap.put(treeNodeResponse.getId(), treeNodeResponse);
        treeNodeResponses.add(treeNodeResponse);
      }
    }
    // 创建子节点
    for (AuthorityResource authorityResource : authAll) {
      TreeNodeResponse treeNodeResponseFather = new TreeNodeResponse();
      List<TreeNodeResponse> childList = new ArrayList<TreeNodeResponse>();
      if (authorityResource != null && authorityResource.getParentId() != null) {
        TreeNodeResponse treeNodeResponseChild = new TreeNodeResponse();
        treeNodeResponseFather = parentMap.get(authorityResource.getParentId());
        treeNodeResponseChild.setId(authorityResource.getId());
        treeNodeResponseChild.setText(authorityResource.getAuthCnName());
        if (currentAuthList.contains(authorityResource)) {
          treeNodeResponseChild.setChecked(true);
        } else {
          treeNodeResponseChild.setChecked(false);
        }
        if (treeNodeResponseFather != null && treeNodeResponseFather.getChildren() != null) {
          childList = treeNodeResponseFather.getChildren();
        }
        childList.add(treeNodeResponseChild);
        treeNodeResponseFather.setChildren(childList);
      }
    }
    
    return treeNodeResponses;
  }


  /**
   * 授权
   * 
   * @param role
   * @return
   */
  @RequestMapping(value = "/addAuth", method = RequestMethod.POST)
  public @ResponseBody Message addAuth(Long[] authId, Long id) {
    Role role = roleService.find(id);
    Set<AuthorityResource> authorityResources = new HashSet<AuthorityResource>();
    if (role != null && role.getAuthorityResources() != null) {
      authorityResources = role.getAuthorityResources();
    }
    if (authId == null || id == null) {
      LogUtil.debug(RoleController.class, "RoleController.addAuth()",
          "authId=null or id=null, tenant ID=%s", tenantAccountService.getCurrentTenantID());
      return ERROR_MESSAGE;
    }
    for (Long auth_id : authId) {
      AuthorityResource authorityResource = authorityService.find(auth_id);
      authorityResources.add(authorityResource);
    }
    role.setAuthorityResources(authorityResources);
    roleService.save(role);

    return SUCCESS_MESSAGE;
  }
}
