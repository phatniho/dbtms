package com.company.db.sys.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.company.db.common.vo.Node;
import com.company.db.entity.SysMenu;

@Mapper
public interface SysMenuDao {

	List<Map<String, SysMenu>> findObjects();

	int getChildCount(@Param("id") Integer id);

	int deleteObject(@Param("id") Integer id);

	int updateObject(SysMenu sysMenu);

	int insertObject(SysMenu sysMenu);

	List<Node> findZtreeMenuNodes();

	List<String> findPermissions(@Param("menuIds") Integer... menuIds);

}
