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

import com.srit.ecs.phone.entity.RoleEntity;
import com.srit.ecs.phone.entity.UserEntity;
import com.srit.ecs.phone.service.LoginService;
import com.srit.ecs.phone.service.RoleService;

public class CustomRealm extends AuthorizingRealm{
	
	@Autowired
    LoginService loginService;
	@Autowired
	RoleService roleService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String name = (String) principalCollection.getPrimaryPrincipal();
        UserEntity user = loginService.getUserByName(name);
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        String[] roleArray = user.getRoles().split(",");
        for (int i = 0; i < roleArray.length; i++) {
        	long roleId = Long.parseLong(roleArray[i]);
        	RoleEntity role = roleService.queryById(roleId);
			simpleAuthorizationInfo.addRole(role.getRoleName());
		}
//        for (RoleEntity role : user.getRoles()) {
//            simpleAuthorizationInfo.addRole(role.getRoleName());
//            for (PermissionsEntity permissions : role.getPermissionSet()) {
//                simpleAuthorizationInfo.addStringPermission(permissions.getPermissionsName());
//            }
//        }
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
            SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(name, user.getPassword(), getName());
            return simpleAuthenticationInfo;
        }
    }

}
