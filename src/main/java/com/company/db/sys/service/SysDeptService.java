package com.company.db.sys.service;

import java.util.List;
import java.util.Map;

import com.company.db.common.vo.Node;
import com.company.db.entity.SysDept;

public interface SysDeptService {
	int saveObject(SysDept entity);

	int updateObject(SysDept entity);

	List<Node> findZTreeNodes();

	List<Map<String, Object>> findObjects();

	int deleteObject(Integer id);
}
