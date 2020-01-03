package com.srit.ecs.phone.entity;

import java.util.Set;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserEntity {
	
	public UserEntity(String id, String name, String pw, Set<RoleEntity> roleSet) {
		this.id = id;
		this.userName = name;
		this.password = pw;
		this.roles = roleSet;
	}

	private String id;
    private String userName;
    private String password;
    
    private Set<RoleEntity> roles;

}
