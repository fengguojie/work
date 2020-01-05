package com.srit.ecs.phone.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.plugins.Page;
import com.srit.ecs.phone.entity.UserEntity;
import com.srit.ecs.phone.service.LoginService;
import com.srit.ecs.phone.util.PageDetails;
import com.srit.ecs.phone.util.PageDetailsUtils;
import com.srit.ecs.phone.util.UserUtil;
import com.srit.ecs.phone.vo.Result;


@RestController
@RequestMapping("/user")
public class UserController{
	
	@Autowired
	LoginService loginService;
	
    @RequiresRoles("admin")
    @RequestMapping("/list")
    public Map<String, Object> list(HttpServletRequest request) {
    	Map<String, Object> result = new HashMap<>();
        Map<String, String> params = new HashMap<>();
        params.put("userName", request.getParameter("userName"));
        PageDetails pageDetails = PageDetailsUtils.getpPageDetails(request);
        Page<UserEntity> page = new Page<>(pageDetails.getPageNumber(),pageDetails.getPageSize());
        result = loginService.queryForPage(page,params);
        return result;
    }
    
    @RequiresRoles("normal")
    @RequestMapping("/register")
    public Result register() {
    	String userName = UserUtil.getStringRandom(6);
    	String password = UserUtil.getStringRandom(8);
    	UserEntity user = new UserEntity();
    	user.setState(1);
    	user.setType(2);
    	user.setRoles("2,3");
    	user.setCreatetime(new Date());
    	user.setUserName(userName);
    	user.setPassword(password);
    	while(true) {
    		UserEntity user2 = loginService.getUserByName(userName);
    		if (user2 == null) {
				loginService.save(user);
				break;
			}else {
				userName = UserUtil.getStringRandom(6);
			}
    	}
    	JSONObject jsonObject = new JSONObject();
    	jsonObject.put("userName", user.getUserName());
    	jsonObject.put("password", user.getPassword());
        return Result.success(jsonObject);
    }
    
    
	
}
