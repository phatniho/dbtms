package com.company.db.entity;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString
@EqualsAndHashCode(callSuper = false)
public class SysUser extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 6013750416832448172L;
	private Integer id;
	private String username;
	private String password;
	private String salt;
	private String email;
	private String mobile;
	private Integer valid;
	private Integer deptId;
}
