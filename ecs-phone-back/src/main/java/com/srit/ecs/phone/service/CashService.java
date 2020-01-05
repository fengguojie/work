package com.srit.ecs.phone.service;

import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.plugins.Page;
import com.srit.ecs.phone.entity.CashEntity;
import com.srit.ecs.phone.entity.UserEntity;

public interface CashService {

	
	Integer save(CashEntity user);
	
	void update(CashEntity user);
	
	void delete(Long id);

	JSONObject queryForPage(Page<CashEntity> page, Map<String, String> params);
	

}
