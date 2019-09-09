package com.company.db.entity;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString
@EqualsAndHashCode(callSuper = false)
public class SysRole extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 3121020168321537556L;
	private Integer id;
	private String name;
	private String note;

}
