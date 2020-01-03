package com.srit.ecs.phone.entity;

import java.sql.Timestamp;

import com.baomidou.mybatisplus.annotations.TableField;

public class BaseEntity {
    
	@TableField(value = "create_by",exist = true)
    private String createBy;

	@TableField(value = "create_time",exist = true)
    private Timestamp createTime;

	@TableField(value = "update_by",exist = true)
    private String updateBy;

	@TableField(value = "update_time",exist = true)
    private Timestamp updateTime;

	@TableField(value = "state",exist = true)
    private Boolean state;

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	public Boolean getState() {
		return state;
	}

	public void setState(Boolean state) {
		this.state = state;
	}
    
}
