package com.company.db.sys.service;

import java.util.Map;

import com.company.db.common.vo.PageObject;
import com.company.db.entity.SysUser;
import com.company.db.vo.SysUserDeptVo;

public interface SysUserService {

	int updatePassword(String pwd, String newPwd, String cfgPwd);

	PageObject<SysUserDeptVo> findPageObjects(String username, Integer pageCurrent);

	Map<String, Object> findObjectById(Integer id);

	int validById(Integer id, Integer valid, String modifiedUser);

	int insertObject(SysUser sysUser, Integer[] roleIds);

	int updateObject(SysUser sysUser, Integer[] roleIds);
}
