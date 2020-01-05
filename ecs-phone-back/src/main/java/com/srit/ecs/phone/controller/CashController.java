package com.srit.ecs.phone.controller;

import java.math.BigDecimal;
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
import com.srit.ecs.phone.entity.CashEntity;
import com.srit.ecs.phone.entity.UserEntity;
import com.srit.ecs.phone.service.CashService;
import com.srit.ecs.phone.service.LoginService;
import com.srit.ecs.phone.util.PageDetails;
import com.srit.ecs.phone.util.PageDetailsUtils;
import com.srit.ecs.phone.vo.Result;


@RestController
@RequestMapping("/cash")
public class CashController{
	
	@Autowired
	LoginService loginService;
	@Autowired
	CashService cashService;
	
    @RequiresRoles("admin")
    @RequestMapping("/list")
    public Result list(HttpServletRequest request,String userName,String start,String end,String state) {
        Map<String, String> params = new HashMap<>();
        params.put("userName", userName);
        params.put("start", start);
        params.put("end", end);
        params.put("state", state);
        PageDetails pageDetails = PageDetailsUtils.getpPageDetails(request);
        Page<CashEntity> page = new Page<>(pageDetails.getPageNumber(),pageDetails.getPageSize());
        JSONObject result = cashService.queryForPage(page,params);
        return Result.success(result);
    }
    
    @RequiresRoles("normal")
    @RequestMapping("/add")
    public Result cash(Integer type,String money,String userNama,String start,String end) {
        UserEntity user = loginService.getCurUser();
        BigDecimal all = user.getMoney();
        if (type != null) {
			return Result.error("type 不能为空");
		}
        if (type == 1) {
			all.add(new BigDecimal(money));
		}else {
			if (all.doubleValue() < new BigDecimal(money).doubleValue()) {
				return Result.error("总金额小于支出金额");
			}
			all.subtract(new BigDecimal(money));
		}
        loginService.update(user);
        CashEntity cashEntity = new CashEntity();
        cashEntity.setUserId(user.getId());
        cashEntity.setUserName(user.getUserName());
        cashEntity.setState(1);
        cashEntity.setType(type);
        cashEntity.setMoney(new BigDecimal(money));
        cashEntity.setCreatetime(new Date());
        return Result.success();
    }
    
	
}
