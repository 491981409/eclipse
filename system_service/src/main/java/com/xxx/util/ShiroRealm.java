package com.xxx.util;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.xxx.domain.auth.User;

public class ShiroRealm extends  AuthorizingRealm{

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
		String username=(String)arg0.fromRealm(getName()).iterator().next();  
        if( username != null ){  
            /*AuthUser user = new AuthUser();
            if( user != null && user.getAuthRoles() != null ){  
                SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();  
                for(AuthRole each: user.getAuthRoles() ){  
                        info.addRole(each.getRolType());  
                        info.addStringPermissions(null);  
                }  
                return info;  
            }  */
        }  
        return null;  
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken arg0) throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) arg0;  
        String userName = token.getUsername();  
        if( userName != null && !"".equals(userName) ){  
          User user = new User();
            if( user != null )   return new SimpleAuthenticationInfo(user,"123", getName());  
        }  
        return null;  
	}

}
