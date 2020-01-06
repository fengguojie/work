package com.srit.ecs.phone.enums;

import lombok.Getter;

@Getter
public enum CashTypeEnum {
	
	Down(-1,"下分"),
	up(1,"上分");
	
	private int code;
	private String desc;
	
	private CashTypeEnum(int code,String desc) {
		this.code = code;
		this.desc = desc;
	}
	

}
