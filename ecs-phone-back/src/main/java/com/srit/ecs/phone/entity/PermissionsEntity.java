package com.srit.ecs.phone.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PermissionsEntity {
	
	public PermissionsEntity(String id, String permissionsName) {
		this.id = id;
		this.permissionsName = permissionsName;
	}
	private String id;
    private String permissionsName;
    
}

