package com.company.db.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SysRoleMenuDao {

	int deleteObjectsByMenuId(@Param("menuId") Integer menuId);

	int insertObjects(@Param("roleId") Integer roleId, @Param("menuIds") Integer... menuIds);

	int deleteObjectsByRoleId(@Param("roleId") Integer roleId);

	List<Integer> findMenuIdsByRoleIds(@Param("roleIds") Integer... roleIds);

	List<Integer> findMenuIdsByRoleId(@Param("roleId") Integer roleId);
}
