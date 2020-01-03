package com.srit.ecs.phone.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.plugins.Page;
import com.srit.ecs.phone.entity.DictionaryEntity;
import com.srit.ecs.phone.util.PageDetails;

public interface DictionaryService {

	Map<String, Object> queryByPidAndDescription(DictionaryEntity template);
	
	DictionaryEntity queryById(Long id);

	Map<String, Object> queryForPage(Page<DictionaryEntity> page,String name);

	List<Map<String, Object>> queryForMapPage(Page<DictionaryEntity> page, String name);

	
}
