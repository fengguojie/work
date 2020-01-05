package com.srit.ecs.phone.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.srit.ecs.phone.entity.UserEntity;
import com.srit.ecs.phone.mapper.UserMapper;
import com.srit.ecs.phone.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService{
	
	 @Autowired
	 UserMapper mapper;
	
	 @Override
     public UserEntity getUserByName(String name) {
		EntityWrapper<UserEntity> queryWrapper = new EntityWrapper<UserEntity>();
        queryWrapper.where(" state = 1 ")
        			.eq("user_name", name);
        List<UserEntity> users = mapper.selectList(queryWrapper);
        if (users != null && users.size() >= 1) {
			return users.get(0);
		}
        return null;
     }

	 @Override
	 public  UserEntity getCurUser() {
		String name = (String)SecurityUtils.getSubject().getPrincipal();
		if (name == null) {
			return null;
		}
		UserEntity user = getUserByName(name);
		return user;
	 }

	@Override
	public Integer save(UserEntity user) {
		return mapper.insert(user);
	}

	@Override
	public void update(UserEntity user) {
		mapper.updateById(user);
	}

	@Override
	public void delete(Long id) {
		mapper.deleteById(id);
	}
	
	@Override
	public Map<String,Object> queryForPage(Page<UserEntity> page, Map<String, String> params) {
		Wrapper<UserEntity> wrapper = new EntityWrapper<UserEntity>();
		String name = params.get("userName");
		if (name != null && !name.isEmpty()) {
			wrapper.like("name", name);
		}
		List<UserEntity> list = mapper.selectPage(page,wrapper);
		Integer selectCount = mapper.selectCount(wrapper);
		Map<String, Object> result = new HashMap<>();
		result.put("list", list);
		result.put("count", selectCount);
		return result;
	}

}
