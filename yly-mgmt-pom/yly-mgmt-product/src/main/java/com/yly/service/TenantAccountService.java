package com.yly.service;

import java.util.List;

import com.yly.entity.TenantAccount;
import com.yly.framework.service.BaseService;

/**
 * Service - 租户用户
 * 
 */
public interface TenantAccountService extends BaseService<TenantAccount, Long> {

	/**
	 * 判断用户名是否存在
	 * 
	 * @param username
	 *            用户名(忽略大小写)
	 * @return 用户名是否存在
	 */
	boolean usernameExists(String username);
	
	/**
     * 判断E-mail是否存在
     * 
     * @param email
     *            E-mail(忽略大小写)
     * @return E-mail是否存在
     */
    boolean emailExists(String email);

	/**
	 * 根据用户名查找
	 * 
	 * @param username
	 *            用户名(忽略大小写)
	 * @return 若不存在则返回null
	 */
    TenantAccount findByUsername(String username);
	
	
  /**
   * 根据名字查询用户
   * @param name
   * @return
   */
	List<TenantAccount> findByName(String username);
	
	/**
	 * 根据名字和机构代码查询用户
	 * @param name
	 * @param orgCode
	 * @return
	 */
	TenantAccount findByNameAndOrgCode(String name,String orgCode);
	
	/**
	 * 通过名字精确查找
	 * @param name
	 * @return
	 */
	TenantAccount findByNameAccurate (String name);
  
  
	/**
	 * 根据ID查找权限
	 * 
	 * @param id
	 *            ID
	 * @return 权限,若不存在则返回null
	 */
	List<String> findAuthorities(Long id);

	/**
	 * 判断是否登录
	 * 
	 * @return 是否登录
	 */
	boolean isAuthenticated();

	/**
	 * 获取当前登录用户
	 * 
	 * @return 当前登录用户,若不存在则返回null
	 */
	TenantAccount getCurrent();

	/**
	 * 获取当前登录用户名
	 * 
	 * @return 当前登录用户名,若不存在则返回null
	 */
	String getCurrentUsername();
	
	/**
     * 获取当前登录租户ID
     * 
     * @return 当前登录租户ID,若不存在则返回null
     */
    Long getCurrentTenantID();
	
	/**
	 * 判断用户是否为内置
	 * 
	 */
	boolean isSystemAdmin();
	
}