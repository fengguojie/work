package com.srit.ecs.phone.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.plugins.Page;
import com.srit.ecs.phone.entity.DictionaryEntity;
import com.srit.ecs.phone.service.DictionaryService;
import com.srit.ecs.phone.util.PageDetails;
import com.srit.ecs.phone.util.PageDetailsUtils;


@RestController
@RequestMapping("/dictionary")
public class DictionaryController {

	@Autowired
	private DictionaryService dictionaryService;
	
    @RequestMapping(value = "/queryByPidAndModule", method = RequestMethod.POST)
    public Map<String, Object> queryByPidAndDescription(HttpServletRequest request, DictionaryEntity dictionary) {
        Map<String, Object> result = new HashMap<>();
        result = dictionaryService.queryByPidAndDescription(dictionary);
        return result;
    }
    
    @RequestMapping(value = "/queryForPage", method = RequestMethod.GET)
    public Map<String, Object> queryForPage(HttpServletRequest request) {
        Map<String, Object> result = new HashMap<>();
        String name = request.getParameter("name");
        PageDetails pageDetails = PageDetailsUtils.getpPageDetails(request);
        Page<DictionaryEntity> page = new Page<>(pageDetails.getPageNumber(),pageDetails.getPageSize());
        result = dictionaryService.queryForPage(page,name);
        return result;
    }
	
}
