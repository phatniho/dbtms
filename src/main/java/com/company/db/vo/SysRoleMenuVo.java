package com.company.db.vo;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class SysRoleMenuVo implements Serializable {
	private static final long serialVersionUID = -2980513887549823980L;
	private Integer id;
	private String name;
	private String note;
	private List<Integer> menuIds;

}
