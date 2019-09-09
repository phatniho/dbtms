package com.company.db.sys.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.company.db.common.exception.ServiceException;
import com.company.db.common.util.PageUtil;
import com.company.db.common.vo.CheckBox;
import com.company.db.common.vo.PageObject;
import com.company.db.entity.SysRole;
import com.company.db.sys.dao.SysRoleDao;
import com.company.db.sys.dao.SysRoleMenuDao;
import com.company.db.sys.dao.SysUserRoleDao;
import com.company.db.sys.service.SysRoleService;
import com.company.db.vo.SysRoleMenuVo;

@Service
public class SysRoleServiceImpl implements SysRoleService {

	@Autowired
	SysRoleDao sysRoleDao;

	@Autowired
	SysRoleMenuDao sysRoleMenuDao;

	@Autowired
	SysUserRoleDao sysUserRoleDao;

	@Override
	public PageObject<SysRole> findPageObjects(String name, Integer pageCurrent) {

		// 1.判定pageCurrent参数合法性
		if (pageCurrent == null || pageCurrent < 1) {
			throw new IllegalArgumentException("当前页码值不正确");
		}
		// 2.查询日志总记录数,并进行判定
		int rowCount = sysRoleDao.getRowCount(name);
		if (rowCount == 0) {
			throw new ServiceException("没有数据");
		}
		// 3.查询当前页日志记录
		int startIndex = PageUtil.getStartIndex(pageCurrent);
		List<SysRole> records = sysRoleDao.findPageObjects(name, startIndex, PageUtil.getPageSize());
		// 4.对查询结果进行封装并返回
		PageObject<SysRole> po = PageUtil.setPageObject(pageCurrent, rowCount, PageUtil.getPageSize(), records);
		return po;
	}

	@Override
	public int deleteObject(Integer id) {
		if (id == null || id <= 0) {
			throw new IllegalArgumentException("ID值不正确");
		}
		int rols = sysRoleDao.deleteObject(id);
		if (rols == 0) {
			throw new ServiceException("记录不存在");
		}
		sysRoleMenuDao.deleteObjectsByMenuId(id);
		sysUserRoleDao.deleteObjectsByRoleId(id);
		return rols;
	}

	@Override
	public SysRoleMenuVo findObjectById(Integer id) {
		if (id == null || id <= 0) {
			throw new IllegalArgumentException("ID值不正确");
		}
		SysRoleMenuVo bean = sysRoleDao.findObjectById(id);
		if (bean == null) {
			throw new ServiceException("记录不存在");
		}
		return bean;
	}

	@Override
	public int updateObject(SysRole sysRole, Integer... menuIds) {
		if (sysRole == null) {
			throw new IllegalArgumentException("对象不能为空");
		}
		if (sysRole.getName() == null || sysRole.getName().length() == 0) {
			throw new IllegalArgumentException("用户名不能为空");
		}
		if (menuIds == null || menuIds.length == 0) {
			throw new IllegalArgumentException("必须为角色分配资源");
		}
		int cols = sysRoleDao.updateObject(sysRole);
		sysRoleMenuDao.deleteObjectsByRoleId(sysRole.getId());
		sysRoleMenuDao.insertObjects(sysRole.getId(), menuIds);

		return cols;
	}

	@Override
	public int insertObject(SysRole sysRole, Integer... menuIds) {
		if (sysRole == null) {
			throw new IllegalArgumentException("对象不能为空");
		}
		if (StringUtils.isEmpty(sysRole.getName())) {
			throw new IllegalArgumentException("用户名不能为空");
		}
		if (menuIds == null || menuIds.length == 0) {
			throw new IllegalArgumentException("必须为角色分配资源");
		}
		int cols = sysRoleDao.insertObject(sysRole);
		sysRoleMenuDao.insertObjects(sysRole.getId(), menuIds);

		return cols;
	}

	@Override
	public List<CheckBox> findObjects() {
		List<CheckBox> list = sysRoleDao.findObjects();
		return list;
	}

}
