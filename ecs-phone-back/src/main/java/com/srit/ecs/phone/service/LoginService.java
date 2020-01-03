package com.srit.ecs.phone.service;

import com.srit.ecs.phone.entity.UserEntity;

public interface LoginService {

	UserEntity getUserByName(String name);

}
