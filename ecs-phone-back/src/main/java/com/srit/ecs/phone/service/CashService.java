package com.srit.ecs.phone.service;

import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.plugins.Page;
import com.srit.ecs.phone.entity.CashEntity;

public interface CashService {

	
	Integer save(CashEntity cash);
	
	void update(CashEntity user);
	
	void delete(Long id);

	JSONObject queryForPage(Page<CashEntity> page, Map<String, String> params);
	
	CashEntity getCashById(Integer id);
	

}
