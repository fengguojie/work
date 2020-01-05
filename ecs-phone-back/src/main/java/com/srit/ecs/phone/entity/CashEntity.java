package com.srit.ecs.phone.entity;

import java.math.BigDecimal;
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
@TableName("jlm_cash")
public class CashEntity {
	
	@TableId(value = "id",type = IdType.AUTO)
	private Integer id;
	
	@TableField(value = "user_name")
    private String userName;
	
	@TableField(value = "user_id")
    private Integer userId;

	@TableField(value = "state")
    private Integer state;
	
	@TableField(value = "type")
    private Integer type;
	
	@TableField(value = "money")
    private BigDecimal money;
	
	@TableField(value = "create_time")
    private Date createtime;

}
