package com.srit.ecs.phone.service;

import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.plugins.Page;
import com.srit.ecs.phone.entity.UserEntity;

public interface LoginService {
	
	UserEntity getUserById(Integer id);

	UserEntity getUserByName(String name);

	UserEntity getCurUser();
	
	Integer save(UserEntity user);
	
	void update(UserEntity user);
	
	void delete(Long id);

	JSONObject queryForPage(Page<UserEntity> page, Map<String, String> params);
	

}
