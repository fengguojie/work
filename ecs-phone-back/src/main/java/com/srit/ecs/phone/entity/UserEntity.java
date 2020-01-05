package com.srit.ecs.phone.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
@TableName("jlm_user")
public class UserEntity {
	
	public UserEntity() {}
	
	public UserEntity(String id, String name, String pw, String roles) {
		this.id = id;
		this.userName = name;
		this.password = pw;
		this.roles = roles;
	}

	@TableId(value = "id",type = IdType.AUTO)
	private String id;
	
	@TableField(value = "user_name")
    private String userName;
	
	@TableField(value = "real_name")
    private String realName;
	
	@TableField(value = "password")
    private String password;

	@TableField(value = "roles")
    private String roles;
    
	@TableField(value = "state")
    private Integer state;
	
	@TableField(value = "type")
    private Integer type;
	
	@TableField(value = "create_time")
    private Date createtime;
	
	@TableField(value = "user_info")
    private String userInfo;

}
