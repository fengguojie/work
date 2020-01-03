package com.srit.ecs.phone.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.srit.ecs.phone.entity.DictionaryEntity;
import com.srit.ecs.phone.mapper.DictionaryMapper;
import com.srit.ecs.phone.service.DictionaryService;
import com.srit.ecs.phone.util.Constants;

@Service
public class DictionaryServiceImpl implements DictionaryService {

	@Autowired
	private DictionaryMapper dictionaryMapper;
	
	@Override
	public Map<String, Object> queryByPidAndDescription(DictionaryEntity template) {
		// TODO Auto-generated method stub
		Map<String, Object> result = new HashMap<String, Object>();
        EntityWrapper<DictionaryEntity> queryWrapper = new EntityWrapper<DictionaryEntity>();
        queryWrapper.where(" state = true ")
        			.eq("parent_id", template.getParentId())
        			.eq("module", template.getModule())
        			.orderBy("sorting", true);
        List<DictionaryEntity> articleCategoryList = dictionaryMapper.selectList(queryWrapper);
        result.put(Constants.BOOTSTRAP_FORMAT_DATA, articleCategoryList);
		return result;
	}
	
	@Override
	public DictionaryEntity queryById(Long id){
		// TODO Auto-generated method stub
		return dictionaryMapper.selectById(id);
	}

	@Override
	public Map<String,Object> queryForPage(Page<DictionaryEntity> page,String name) {
		Wrapper<DictionaryEntity> wrapper = 
				new EntityWrapper<DictionaryEntity>().like("name", name);
		List<DictionaryEntity> list = dictionaryMapper.selectPage(page,wrapper);
		Integer selectCount = dictionaryMapper.selectCount(wrapper);
		Map<String, Object> result = new HashMap<>();
		result.put("list", list);
		result.put("count", selectCount);
		return result;
	}
	
	@Override
	public List<Map<String, Object>> queryForMapPage(Page<DictionaryEntity> page,String name) {
		return dictionaryMapper.selectMapsPage(page, 
				new EntityWrapper<DictionaryEntity>().like("name", name));
	}
	
}
