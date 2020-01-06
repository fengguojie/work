package com.srit.ecs.phone.enums;

import lombok.Getter;

@Getter
public enum UserStateEnum {
	
	OPEN(1,"正常"),
	CLOSE(2,"封号"),
	DELETE(3,"删除");
	
	private int code;
	private String desc;
	
	private UserStateEnum(int code,String desc) {
		this.code = code;
		this.desc = desc;
	}
	

}
