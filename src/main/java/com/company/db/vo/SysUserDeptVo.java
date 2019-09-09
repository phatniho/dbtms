package com.company.db.vo;

import com.company.db.entity.BaseEntity;
import com.company.db.entity.SysDept;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString
@EqualsAndHashCode(callSuper = false)
public class SysUserDeptVo extends BaseEntity {
	private static final long serialVersionUID = -2752331961435583421L;
	private Integer id;
	private String username;
	private String password;
	private String salt;
	private String email;
	private String mobile;
	private Integer valid = 1;
	private SysDept sysDept;
}
