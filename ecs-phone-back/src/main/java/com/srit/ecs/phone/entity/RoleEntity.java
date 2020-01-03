package com.srit.ecs.phone.entity;

import java.util.Set;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleEntity {
	
	public RoleEntity(String id, String roleName, Set<PermissionsEntity> permissionsSet) {
		this.id = id;
		this.roleName = roleName;
		this.permissionSet = permissionsSet;
	}

	private String id;
    private String roleName;
    
    private Set<PermissionsEntity> permissionSet;

}
