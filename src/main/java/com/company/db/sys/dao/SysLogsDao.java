package com.company.db.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.company.db.entity.SysLog;

@Mapper
public interface SysLogsDao {

	/**
	 * 按条件查询日志总记录数
	 * 
	 * @param username 查询条件
	 */
	int getRowCount(@Param("username") String username);

	/**
	 * 基于条件执行分页查询,获取当前页记录
	 * 
	 * @param username   查询条件
	 * @param startIndex 起始位置
	 * @param pageSize   页面大小(每个页面显示多少条数据)
	 * @return
	 */
	List<SysLog> findPageObjects(@Param("username") String username, @Param("startIndex") Integer startIndex,
			@Param("pageSize") Integer pageSize);

	/**
	 * 
	 * @param ids
	 * @return
	 */
	int deleteObjects(@Param("ids") Integer... ids);

	int insertObject(SysLog log);

}
