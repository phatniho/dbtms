package com.company.db.sys.service.Impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.db.common.exception.ServiceException;
import com.company.db.common.vo.Node;
import com.company.db.entity.SysMenu;
import com.company.db.sys.dao.SysMenuDao;
import com.company.db.sys.dao.SysRoleMenuDao;
import com.company.db.sys.service.SysMenuService;

@Service
public class SysMenuServiceImpl implements SysMenuService {

	@Autowired
	SysMenuDao sysMenuDao;

	@Autowired
	SysRoleMenuDao sysRoleMenuDao;

	@Override
	public List<Map<String, SysMenu>> findObjects() {
		List<Map<String, SysMenu>> list = sysMenuDao.findObjects();
		if (list.size() == 0 || list == null) {
			throw new ServiceException("没有对应的菜单");
		}
		return list;
	}

	@Override
	public int deleteObject(Integer id) {
		if (id <= 0 || id == null) {
			throw new IllegalArgumentException("请先选择需要删除的菜单");
		}
		int childCount = sysMenuDao.getChildCount(id);
		if (childCount > 0) {
			throw new ServiceException("请先删除子表");
		}
		int rols = sysMenuDao.deleteObject(id);
		if (rols == 0) {
			throw new IllegalArgumentException("该菜单已被删除");
		}
		sysRoleMenuDao.deleteObjectsByMenuId(id);
		return rols;
	}

	@Override
	public int updateObject(SysMenu sysMenu) {
		if (sysMenu == null) {
			throw new ServiceException("不能为空");
		}
		if (sysMenu.getName() == null || sysMenu.getName().length() == 0) {
			throw new ServiceException("菜单名不能为空");
		}
		if (sysMenu.getPermission() == null || sysMenu.getPermission().length() == 0) {
			throw new ServiceException("权限标识不能为空");
		}
		int rols;
		try {
			rols = sysMenuDao.updateObject(sysMenu);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("记录不存在");
		}
		return rols;
	}

	@Override
	public int saveObject(SysMenu sysMenu) {
		if (sysMenu == null) {
			throw new ServiceException("不能为空");
		}
		if (sysMenu.getName() == null || sysMenu.getName().length() == 0) {
			throw new ServiceException("菜单名不能为空");
		}
		if (sysMenu.getPermission() == null || sysMenu.getPermission().length() == 0) {
			throw new ServiceException("权限标识不能为空");
		}
		int rols;
		try {
			rols = sysMenuDao.insertObject(sysMenu);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("保存失败");
		}
		return rols;
	}

	@Override
	public List<Node> findZtreeMenuNodes() {
		List<Node> list = sysMenuDao.findZtreeMenuNodes();
		return list;
	}

}
