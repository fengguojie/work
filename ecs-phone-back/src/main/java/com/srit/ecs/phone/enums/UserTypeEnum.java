package com.srit.ecs.phone.enums;

import lombok.Getter;

@Getter
public enum UserTypeEnum {
	
	Admin(1,"管理员"),
	NORMAL(2,"C端用户");
	
	private int code;
	private String desc;
	
	private UserTypeEnum(int code,String desc) {
		this.code = code;
		this.desc = desc;
	}
	

}
