package com.srit.ecs.phone.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.srit.ecs.phone.entity.UserEntity;
import com.srit.ecs.phone.service.LoginService;
import com.srit.ecs.phone.vo.Result;

import lombok.extern.slf4j.Slf4j;


@RestController
@Slf4j
public class LoginController{
	
	@Autowired
	LoginService loginService;

	@RequestMapping("/login")
	@ResponseBody
    public Result login(HttpServletRequest request) {
		String userName = request.getParameter("name");
		String password = request.getParameter("pw");
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(
                userName,password
        );
        Result result =  new Result();
        try {
            subject.login(usernamePasswordToken);//进行验证，这里可以捕获异常，然后返回对应信息
            result = Result.success("login success");
            //subject.checkRole("admin");
            //subject.checkPermissions("query", "add");
        } catch (AuthenticationException e) {
            log.error(e.getMessage());
            result = Result.error("账号密码错误");
        }  catch (Exception e) {
        	log.error(e.getMessage());
        	result = Result.error("系统异常");
		}
        return result;
    }
    
    @RequiresRoles("common")
    @RequestMapping("/getUserInfo")
    @ResponseBody
    public Result getUserInfo() {
    	UserEntity user = loginService.getCurUser();
    	JSONObject jsonObject =  new JSONObject();
    	jsonObject.put("user", user);
        return Result.success(jsonObject);
    }
   
	
}
