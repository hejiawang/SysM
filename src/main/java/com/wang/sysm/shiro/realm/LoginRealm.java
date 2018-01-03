package com.wang.sysm.shiro.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.HashSet;
import java.util.Set;

/**
 * @auther HeJiawang
 * @date 2017/12/28
 */
public class LoginRealm extends AuthorizingRealm {

    /**
     * 权限
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        Set<String> roles = new HashSet<String>(){
            {
                add("admin");
                add("common");
            }
        };
        authorizationInfo.setRoles(roles);

        Set<String> permissions = new HashSet<String>(){
            {
                add("user:create");
                add("user:update");
            }
        };
        authorizationInfo.setStringPermissions(permissions);
        return authorizationInfo;
    }

    /**
     * 验证用户
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String)token.getPrincipal();
        String password = new String((char[]) token.getCredentials());

        if (!username.equals("admin"))  throw new UnknownAccountException(); //如果用户名错误
        if (!password.equals("123456")) throw new IncorrectCredentialsException(); //如果密码错误

        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(username, password, this.getName());
        return simpleAuthenticationInfo;
    }
}
