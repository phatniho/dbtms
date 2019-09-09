package com.company.db.sys.service;

import java.util.List;
import java.util.Map;

import com.company.db.common.vo.Node;
import com.company.db.entity.SysMenu;

public interface SysMenuService {
	List<Map<String, SysMenu>> findObjects();

	int deleteObject(Integer id);

	int updateObject(SysMenu sysMenu);

	int saveObject(SysMenu sysMenu);

	List<Node> findZtreeMenuNodes();
}
