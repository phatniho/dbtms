package com.company.db.sys.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.company.db.common.vo.Node;
import com.company.db.entity.SysDept;

@Mapper
public interface SysDeptDao {
	int updateObject(SysDept entity);

	int insertObject(SysDept entity);

	List<Node> findZTreeNodes();

	List<Map<String, Object>> findObjects();

	int getChildCount(Integer id);

	int deleteObject(Integer id);
}
