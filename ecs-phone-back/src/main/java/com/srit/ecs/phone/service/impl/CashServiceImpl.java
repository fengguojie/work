package com.srit.ecs.phone.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.srit.ecs.phone.entity.CashEntity;
import com.srit.ecs.phone.mapper.CashMapper;
import com.srit.ecs.phone.service.CashService;

@Service
public class CashServiceImpl implements CashService{
	
	@Autowired
	CashMapper mapper;
	
	@Override
	public Integer save(CashEntity cash) {
		return mapper.insert(cash);
	}

	@Override
	public void update(CashEntity cash) {
		mapper.updateById(cash);
	}

	@Override
	public void delete(Long id) {
		mapper.deleteById(id);
	}
	
	@Override
	public JSONObject queryForPage(Page<CashEntity> page, Map<String, String> params) {
		Wrapper<CashEntity> wrapper = new EntityWrapper<CashEntity>();
		String name = params.get("userName");
		if (name != null && !name.isEmpty()) {
			wrapper.like("user_name", name);
		}
		String state = params.get("state");
		if (state != null && !state.isEmpty()) {
			wrapper.like("state", state);
		}
		String start = params.get("start");
		if (start != null && !start.isEmpty()) {
			wrapper.gt("create_time", start+" 00:00:00");
		}
		String end = params.get("end");
		if (end != null && !end.isEmpty()) {
			wrapper.lt("create_time", end+" 23:59:59");
		}
		wrapper.orderBy("id");
		List<CashEntity> list = mapper.selectPage(page,wrapper);
		Integer selectCount = mapper.selectCount(wrapper);
		JSONObject result = new JSONObject();
		result.put("list", list);
		result.put("count", selectCount);
		return result;
	}


}
