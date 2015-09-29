package com.yly.beans;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.pam.UnsupportedTokenException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.yly.beans.Setting.CaptchaType;
import com.yly.entity.TenantAccount;
import com.yly.entity.TenantInfo;
import com.yly.entity.commonenum.CommonEnum.AccountStatus;
import com.yly.service.CaptchaService;
import com.yly.service.TenantAccountService;
import com.yly.service.TenantInfoService;

/**
 * 权限认证
 * 
 */
public class AuthenticationRealm extends AuthorizingRealm {

	@Resource(name = "tenantAccountServiceImpl")
	private TenantAccountService tenantAccountService;
	@Resource(name = "tenantInfoServiceImpl")
	private TenantInfoService tenantInfoService;
	@Resource(name = "captchaServiceImpl")
	private CaptchaService captchaService;

	/**
	 * 获取认证信息
	 * 
	 * @param token
	 *            令牌
	 * @return 认证信息
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(org.apache.shiro.authc.AuthenticationToken token) {
		AuthenticationToken authenticationToken = (AuthenticationToken) token;
		String username = authenticationToken.getUsername();
		String password = new String(authenticationToken.getPassword());
		String orgCode = authenticationToken.getOrgCode();
		String captchaId = authenticationToken.getCaptchaId();
		String captcha = authenticationToken.getCaptcha();
		String ip = authenticationToken.getHost();
		
		if (!captchaService.isValid(CaptchaType.adminLogin, captchaId, captcha)) {
          throw new UnsupportedTokenException();
		}
		
		if (username != null && password != null && orgCode != null) {
		    
		    TenantInfo tenantInfo = tenantInfoService.findTenantWithOrgCode(orgCode);
		    TenantAccount tenantAccount = null;
		    if(tenantInfo == null){
		      throw new UnknownAccountException();
		    }else {
              if(tenantInfo.getAccountStatus().equals(AccountStatus.LOCKED)){
                throw new DisabledAccountException();
              }else{
                tenantAccount = tenantAccountService.findByNameAndOrgCode(username, orgCode);
                if (tenantAccount == null) {
                    throw new UnknownAccountException();
                }
                if (tenantAccount.getAccoutStatus().equals(AccountStatus.LOCKED)) {
                    throw new DisabledAccountException();
                }
                if (!DigestUtils.md5Hex(password).equals(tenantAccount.getPassword())) {
                    throw new IncorrectCredentialsException();
                }
              }
            }
			tenantAccount.setLoginIp(ip);
			tenantAccount.setLoginDate(new Date());
			tenantAccountService.update(tenantAccount);
			return new SimpleAuthenticationInfo(new Principal(tenantAccount.getId(), username,tenantInfo.getId()), password, getName());
		}
		throw new UnknownAccountException();
	}

	/**
	 * 获取授权信息
	 * 
	 * @param principals
	 *            principals
	 * @return 授权信息
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		Principal principal = (Principal) principals.fromRealm(getName()).iterator().next();
		if (principal != null) {
			List<String> authorities = tenantAccountService.findAuthorities(principal.getId());
			if (authorities != null) {
				SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
				authorizationInfo.addStringPermissions(authorities);
				return authorizationInfo;
			}
		}
		return null;
	}

}