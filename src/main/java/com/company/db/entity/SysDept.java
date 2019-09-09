package com.company.db.entity;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString
@EqualsAndHashCode(callSuper = false)
public class SysDept extends BaseEntity implements Serializable {
	private static final long serialVersionUID = -3670847294649370815L;
	private Integer id;
	private String name;
	private Integer parentId;
	private Integer sort;
	private String note;
}
