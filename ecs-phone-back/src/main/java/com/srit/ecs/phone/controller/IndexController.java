package com.srit.ecs.phone.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class IndexController{

	@RequestMapping("/toIndex")
    public void login(HttpServletRequest request,HttpServletResponse response) throws Exception {
		response.sendRedirect("index.html");
	}
	
    
}
