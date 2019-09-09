package com.company.db.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.company.db.common.vo.CheckBox;
import com.company.db.entity.SysRole;
import com.company.db.vo.SysRoleMenuVo;

@Mapper
public interface SysRoleDao {

	List<SysRole> findPageObjects(@Param("name") String name, @Param("startIndex") Integer startIndex,
			@Param("pageSize") Integer pageSize);

	int getRowCount(@Param("name") String name);

	int deleteObject(@Param("id") Integer id);

	SysRoleMenuVo findObjectById(@Param("id") Integer id);

	int updateObject(SysRole sysRole);

	int insertObject(SysRole sysRole);

	List<CheckBox> findObjects();
}
