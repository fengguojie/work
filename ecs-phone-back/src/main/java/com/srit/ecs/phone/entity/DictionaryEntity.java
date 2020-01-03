package com.srit.ecs.phone.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import lombok.Data;

@Data
@TableName("dictionary")
public class DictionaryEntity  extends BaseEntity {

    @TableId(value = "id",type = IdType.AUTO)//指定自增策略
    private Long id;

    @TableField(value = "sorting",exist = true)
    private Integer sorting;

    @TableField(value = "name",exist = true)
    private String name;

    @TableField(value = "code",exist = true)
    private String code;

    @TableField(value = "module",exist = true)
    private String module;

    @TableField(value = "remark",exist = true)
    private String remark;

    @TableField(value = "parent_id",exist = true)
    private Long parentId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getSorting() {
		return sorting;
	}

	public void setSorting(Integer sorting) {
		this.sorting = sorting;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

}
