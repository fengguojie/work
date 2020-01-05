package com.srit.ecs.phone.auth;

import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.srit.ecs.phone.vo.Result;

@ControllerAdvice
public class MyExceptionHandler {
	
	@ExceptionHandler
    @ResponseBody
    public Result ErrorHandler(AuthorizationException e) {
        return Result.error("没有通过权限验证！");
    }

}
