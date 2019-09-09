package com.company.db.sys.service;

import java.util.List;

import com.company.db.common.vo.CheckBox;
import com.company.db.common.vo.PageObject;
import com.company.db.entity.SysRole;
import com.company.db.vo.SysRoleMenuVo;

public interface SysRoleService {

	PageObject<SysRole> findPageObjects(String name, Integer pageCurrent);

	int deleteObject(Integer id);

	SysRoleMenuVo findObjectById(Integer id);

	int updateObject(SysRole sysRole, Integer... menuIds);

	int insertObject(SysRole sysRole, Integer... menuIds);

	List<CheckBox> findObjects();
}
