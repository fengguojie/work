package com.srit.ecs.phone.auth;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.srit.ecs.phone.entity.PermissionsEntity;
import com.srit.ecs.phone.entity.RoleEntity;
import com.srit.ecs.phone.entity.UserEntity;
import com.srit.ecs.phone.service.LoginService;

public class CustomRealm extends AuthorizingRealm{
	
	@Autowired
    private LoginService loginService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String name = (String) principalCollection.getPrimaryPrincipal();
        UserEntity user = loginService.getUserByName(name);
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        for (RoleEntity role : user.getRoles()) {
            simpleAuthorizationInfo.addRole(role.getRoleName());
            for (PermissionsEntity permissions : role.getPermissionSet()) {
                simpleAuthorizationInfo.addStringPermission(permissions.getPermissionsName());
            }
        }
        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        if (authenticationToken.getPrincipal() == null) {
            return null;//加这一步的目的是在Post请求的时候会先进认证，然后在到请求
        }
        String name = authenticationToken.getPrincipal().toString();
        UserEntity user = loginService.getUserByName(name);
        if (user == null) {
            return null; //这里返回后会报出对应异常
        } else {
            SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(name, user.getPassword().toString(), getName());
            return simpleAuthenticationInfo;
        }
    }

}
