package com.bny.esg.realm;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;

public class Driver { 
    public static void main(String[] args) { 
        Driver d = new Driver(); 
        d.test(); 
    } 

    public void test() { 
        Factory<org.apache.shiro.mgt.SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini"); 
        org.apache.shiro.mgt.SecurityManager securityManager = factory.getInstance(); 
        SecurityUtils.setSecurityManager(securityManager); 

        UsernamePasswordToken token = new UsernamePasswordToken("", ""); 
        token.setRememberMe(true); 

        System.out.println("Shiro props:");

        Subject currentUser = SecurityUtils.getSubject();

        try { 
            currentUser.login(token);

            System.out.println("I think this worked!"); 
        } catch (UnknownAccountException uae) { 
        	System.out.println("Exception: ${uae}");
        } catch (IncorrectCredentialsException ice) { 
        	System.out.println("Exception: ${ice}");
        } catch (LockedAccountException lae) { 
        	System.out.println("Exception: ${lae}"); 
        } catch (ExcessiveAttemptsException eae) { 
        	System.out.println("Exception: ${eae}"); 
        } catch (AuthenticationException ae) { 
        	System.out.println("Exception: ${ae}"); 
        } 
   } 
}