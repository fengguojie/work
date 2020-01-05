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
@TableName("jlm_role")
public class RoleEntity {
	
	public RoleEntity() {}
	
	public RoleEntity(String id, String roleName) {
		this.id = id;
		this.roleName = roleName;
	}
	
	@TableId(value = "id",type = IdType.AUTO)
	private String id;
	
	@TableField(value = "role_name")
    private String roleName;
	
	@TableField(value = "create_time")
    private Date createtime;
	
	@TableField(value = "role_info")
    private String userInfo;


}
