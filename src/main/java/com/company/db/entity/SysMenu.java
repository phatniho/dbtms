package com.company.db.entity;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString
@EqualsAndHashCode(callSuper = false)
public class SysMenu extends BaseEntity implements Serializable {
	private static final long serialVersionUID = -3708672438133479094L;
	private Integer id;
	private String name;
	private String url;
	private Integer type;
	private Integer sort;
	private String note;
	private Integer parentId;
	private String permission;

}
