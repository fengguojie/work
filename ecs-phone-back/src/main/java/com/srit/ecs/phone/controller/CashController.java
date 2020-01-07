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
import com.srit.ecs.phone.enums.CashStateEnum;
import com.srit.ecs.phone.enums.CashTypeEnum;
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
    
    @RequiresRoles("admin")
    @RequestMapping("/confirmCash")
    public Result confirmCash(Integer cashId) {
        CashEntity cashEntity = cashService.getCashById(cashId);
        if (cashEntity == null) {
			return Result.error("查不到记录");
		}
        if (cashEntity.getState() != CashStateEnum.UNHANDLE.getCode()) {
			return Result.error("本条数据状态为已处理");
		}
        UserEntity user = loginService.getUserById(cashEntity.getUserId());
        int type = cashEntity.getType();
        if (type == CashTypeEnum.up.getCode()) {
			user.setMoney(user.getMoney().add(cashEntity.getMoney()));
		}else {
			if (user.getMoney().doubleValue() < cashEntity.getMoney().doubleValue()) {
				return Result.error("总金额小于支出金额");
			}
			user.setMoney(user.getMoney().subtract(cashEntity.getMoney()));
		}
        cashEntity.setState(CashStateEnum.HANDLE.getCode());
        cashService.update(cashEntity);
        loginService.update(user);
        return Result.success();
    }
    
    @RequiresRoles("normal")
    @RequestMapping("/apply")
    public Result cash(Integer type,String money) {
        UserEntity user = loginService.getCurUser();
        if (type == null || money == null) {
			return Result.error("type或者money为空");
		}
        if (type == CashTypeEnum.Down.getCode()) {
        	if (new BigDecimal(money).doubleValue() > user.getMoney().doubleValue()) {
    			return Result.error("提现金额不能大于账户总金额");
    		}
		}
        CashEntity cashEntity = new CashEntity();
        cashEntity.setUserId(user.getId());
        cashEntity.setUserName(user.getUserName());
        cashEntity.setState(CashStateEnum.UNHANDLE.getCode());
        cashEntity.setType(type);
        cashEntity.setMoney(new BigDecimal(money));
        cashEntity.setCreatetime(new Date());
        cashService.save(cashEntity);
        return Result.success();
    }
    
	
}
