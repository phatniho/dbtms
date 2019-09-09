package com.company.db.entity;

import java.io.Serializable;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class SysRoleMenu implements Serializable {
	private static final long serialVersionUID = -3664421621955810809L;
	private Integer id;
	private Integer roleId;
	private Integer menuId;

}
