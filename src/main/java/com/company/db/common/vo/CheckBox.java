package com.company.db.common.vo;

import java.io.Serializable;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CheckBox implements Serializable {
	private static final long serialVersionUID = -2745776148231040606L;
	private Integer id;
	private String name;
}
