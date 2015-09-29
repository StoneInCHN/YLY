package com.yly.dao;

import java.util.List;

import com.yly.entity.TenantAccount;
import com.yly.framework.dao.BaseDao;

/**
 * Dao - 租户用户
 * 
 */
public interface TenantAccountDao extends BaseDao<TenantAccount, Long> {

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
	 * 根据用户名查找管理员
	 * 
	 * @param username
	 *            用户名(忽略大小写)
	 * @return 管理员，若不存在则返回null
	 */
    TenantAccount findByUsername(String username);
    
    /**
     * 根据名字和机构代码查询用户
     * @param name
     * @param orgCode
     * @return
     */
    TenantAccount findByNameAndOrgCode(String username,String orgCode);
	
	/**
	 * 根据名字查询
	 * @param name
	 * @return
	 */
	List<TenantAccount> findByName(String name);
	
	/**
	 * 通过名字精确查找
	 * @param name
	 * @return
	 */
	TenantAccount findByNameAccurate (String name);
	
	

}