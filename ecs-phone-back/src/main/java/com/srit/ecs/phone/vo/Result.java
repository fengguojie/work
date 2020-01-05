package com.srit.ecs.phone.vo;

import com.alibaba.fastjson.JSONObject;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Result {
	
	private int code;
	private String msg;
	private JSONObject data;
	
	public Result() {}
	
	public Result(int code,String msg,JSONObject data) {
		this.code = code;
		this.msg = msg;
		this.data = data;
	}
	public static Result success() {
		return new Result(0,"success",null);
	}
	
	public static Result success(String msg) {
		return new Result(0,msg,null);
	}
	
	public static Result success(JSONObject data) {
		return new Result(0,"success",data);
	}
	
	public static Result error(String msg) {
		return new Result(1,msg,null);
	}

	public static Result error(JSONObject data) {
		return new Result(1,"error",data);
	}
	
	
	
	
	
	
	

}
