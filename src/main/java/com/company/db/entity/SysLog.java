package com.company.db.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.ToString;

/** 借助此对象封装用户的行为日志信息 */
@Data
@ToString
public class SysLog implements Serializable {
	/** 序列化ID */
	private static final long serialVersionUID = -5296723236738233216L;
	/** ID */
	private Integer id;
	/** 用户名 */
	private String username;
	/** 用户操作 */
	private String operation;
	/** 执行方法 */
	private String method;
	/** 请求参数 */
	private String params;
	/** 持续时长 */
	private Long time;
	/** IP地址 */
	private String ip;
	/** 创建时间 */
	private Date createdTime;
}
