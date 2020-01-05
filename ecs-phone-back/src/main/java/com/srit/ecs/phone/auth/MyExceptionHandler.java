package com.srit.ecs.phone.auth;

import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.srit.ecs.phone.vo.Result;

@ControllerAdvice
public class MyExceptionHandler {
	
	@ExceptionHandler
    @ResponseBody
    public Result ErrorHandler(AuthorizationException e) {
		JSONObject jsonObject =  new JSONObject();
		jsonObject.put("errMsg", "没有通过权限验证！");
        return Result.error(jsonObject);
    }

}
