package com.srit.ecs.phone.enums;

import lombok.Getter;

@Getter
public enum CashStateEnum {
	
	UNHANDLE(0,"未处理"),
	HANDLE(1,"已处理");
	
	private int code;
	private String desc;
	
	private CashStateEnum(int code,String desc) {
		this.code = code;
		this.desc = desc;
	}
	

}
