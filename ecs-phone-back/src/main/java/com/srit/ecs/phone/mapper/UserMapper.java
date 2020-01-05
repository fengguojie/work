package com.srit.ecs.phone.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.srit.ecs.phone.entity.UserEntity;

@Mapper
public interface UserMapper extends BaseMapper<UserEntity> {
	
	
}
