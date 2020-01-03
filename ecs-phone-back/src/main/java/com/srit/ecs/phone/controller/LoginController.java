package com.srit.ecs.phone.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.srit.ecs.phone.entity.UserEntity;

import lombok.extern.slf4j.Slf4j;


@RestController
@Slf4j
public class LoginController{

	@RequestMapping("/login")
    public String login(UserEntity user) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(
                user.getUserName(),
                user.getPassword()
        );
        try {
            subject.login(usernamePasswordToken);//进行验证，这里可以捕获异常，然后返回对应信息
            //subject.checkRole("admin");
            //subject.checkPermissions("query", "add");
        } catch (AuthenticationException e) {
            log.error(e.getMessage());
            return "账号或密码错误！";
        } catch (AuthorizationException e) {
        	log.error(e.getMessage());
            return "没有权限";
        }
        return "login success";
    }
	
    @RequiresRoles("admin")
    @RequiresPermissions("add")
    @RequestMapping("/index")
    public String index() {
        return "index!";
    }
	
}
