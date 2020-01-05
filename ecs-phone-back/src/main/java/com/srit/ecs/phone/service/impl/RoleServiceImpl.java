package com.srit.ecs.phone.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.srit.ecs.phone.entity.RoleEntity;
import com.srit.ecs.phone.mapper.RoleMapper;
import com.srit.ecs.phone.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleMapper mapper;
	
	@Override
	public RoleEntity queryById(Long id){
		RoleEntity entity = mapper.selectById(id);
		return entity;
	}
	
}
