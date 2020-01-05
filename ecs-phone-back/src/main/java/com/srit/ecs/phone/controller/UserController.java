package com.srit.ecs.phone.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.omg.PortableServer.ServantManagerOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.plugins.Page;
import com.srit.ecs.phone.entity.CashEntity;
import com.srit.ecs.phone.entity.UserEntity;
import com.srit.ecs.phone.service.CashService;
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
	@Autowired
	CashService cashService;
	
    @RequiresRoles("admin")
    @RequestMapping("/list")
    public Result list(HttpServletRequest request) {
        Map<String, String> params = new HashMap<>();
        params.put("userName", request.getParameter("userName"));
        params.put("date", request.getParameter("date"));
        PageDetails pageDetails = PageDetailsUtils.getpPageDetails(request);
        Page<UserEntity> page = new Page<>(pageDetails.getPageNumber(),pageDetails.getPageSize());
        JSONObject result = loginService.queryForPage(page,params);
        return Result.success(result);
    }
    
    @RequiresRoles("admin")
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
    
    @RequiresRoles("admin")
    @RequestMapping("/close")
    public Result close(Integer userId) {
    	UserEntity user = loginService.getUserById(userId);
        user.setState(2);
        loginService.update(user);
        return Result.success();
    }
    
    @RequiresRoles("admin")
    @RequestMapping("/cash")
    public Result cash(Integer type,String money,Integer userId) {
        UserEntity user = loginService.getUserById(userId);
        BigDecimal all = user.getMoney();
        if (type == null) {
			return Result.error("type 不能为空");
		}
        if (type == 1) {
			all = all.add(new BigDecimal(money));
		}else {
			if (all.doubleValue() < new BigDecimal(money).doubleValue()) {
				return Result.error("总金额小于支出金额");
			}
			all = all.subtract(new BigDecimal(money));
		}
        user.setMoney(all);
        loginService.update(user);
        CashEntity cashEntity = new CashEntity();
        cashEntity.setUserId(user.getId());
        cashEntity.setUserName(user.getUserName());
        cashEntity.setState(1);
        cashEntity.setType(type);
        cashEntity.setMoney(new BigDecimal(money));
        cashEntity.setCreatetime(new Date());
        cashService.save(cashEntity);
        return Result.success();
    }
    
	
}
