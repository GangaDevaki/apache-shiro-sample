package com.bny.esg.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.bny.esg.model.User;

public class CustomRealm extends AuthorizingRealm { 
	    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException { 
	        try { 
	            // My custom logic here 

	        } catch(Throwable t) { 
	            System.out.println(t.getMessage()); 
	        } 
	        SimpleAuthenticationInfo authn = new SimpleAuthenticationInfo();
	        return authn;
	    } 

	    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) { 
	        try { 
	            // My custom logic here 
	        } catch(Throwable t) { 
	            System.out.println(t.getMessage()); 
	        }
	        return new SimpleAuthorizationInfo();
	    } 
	}
