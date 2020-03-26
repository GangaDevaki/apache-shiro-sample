package com.bny.esg.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.bny.esg.entity.User;
import com.bny.esg.service.UserService;

public class CustomRealm extends AuthorizingRealm {
	
    @Autowired
	private UserService userService;

	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String username = (String) token.getPrincipal();
		User user = userService.getUserByName(username);
		if (StringUtils.isEmpty(user)) {
			return null;
		}
		return new SimpleAuthenticationInfo(user, user.getPassword(), getName());
	}

	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
			SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
			User user = (User) principals.getPrimaryPrincipal();
			//String role = user.getRole();
			authorizationInfo.addRole("admin");
/*			for (Permission permission : permissionService.getByRoleId(role)) {
				authorizationInfo.addStringPermission(permission.getPermission());
			}*/
			return authorizationInfo;
	}
}
