package com.company.db.entity;

import java.io.Serializable;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class SysUserRole implements Serializable {
	private static final long serialVersionUID = -1045129268812088279L;
	private Integer id;
	private Integer userId;
	private Integer roleId;
}
