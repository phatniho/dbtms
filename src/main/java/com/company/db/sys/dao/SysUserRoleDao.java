package com.company.db.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SysUserRoleDao {

	int deleteObjectsByRoleId(@Param("roleId") Integer roleId);

	int insertObjects(@Param("userId") Integer userId, @Param("roleIds") Integer... roleIds);

	List<Integer> findRoleIdsByUserId(@Param("id") Integer id);

	int deleteObjectsByUserId(@Param("userId") Integer userId);

}
