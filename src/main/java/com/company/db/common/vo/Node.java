package com.company.db.common.vo;

import java.io.Serializable;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Node implements Serializable {
	private static final long serialVersionUID = -5811891055462247794L;
	private Integer id;
	private String name;
	private Integer parentId;
}
