package com.company.db.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.company.db.entity.SysUser;
import com.company.db.vo.SysUserDeptVo;

@Mapper
public interface SysUserDao {

	int updatePassword(@Param("password") String password, @Param("salt") String salt, @Param("id") Integer id);

	List<SysUserDeptVo> findPageObjects(@Param("username") String username, @Param("startIndex") Integer startIndex,
			@Param("pageSize") Integer pageSize);

	int findPageCount(@Param("username") String username);

	SysUserDeptVo findObjectById(@Param("id") Integer id);

	int validById(@Param("id") Integer id, @Param("valid") Integer valid, @Param("modifiedUser") String modifiedUser);

	int insertObject(SysUser sysUser);

	int updateObject(SysUser sysUser);

	SysUser findByName(@Param("username") String username);
}
