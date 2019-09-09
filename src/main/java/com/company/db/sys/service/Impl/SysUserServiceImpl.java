package com.company.db.sys.service.Impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.company.db.common.annotation.RequiredLog;
import com.company.db.common.exception.ServiceException;
import com.company.db.common.util.PageUtil;
import com.company.db.common.vo.PageObject;
import com.company.db.entity.SysUser;
import com.company.db.sys.dao.SysUserDao;
import com.company.db.sys.dao.SysUserRoleDao;
import com.company.db.sys.service.SysUserService;
import com.company.db.vo.SysUserDeptVo;

@Transactional(timeout = 30, readOnly = false, isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class, propagation = Propagation.REQUIRED)
@Service
public class SysUserServiceImpl implements SysUserService {

	@Autowired
	SysUserDao sysUserDao;

	@Autowired
	SysUserRoleDao sysUserRoleDao;

	@Override
	public PageObject<SysUserDeptVo> findPageObjects(String username, Integer pageCurrent) {
		if (pageCurrent < 0 || pageCurrent == null) {
			throw new IllegalArgumentException("页码错误");
		}
		int rowCount = sysUserDao.findPageCount(username);
		if (rowCount == 0) {
			throw new ServiceException("没有记录");
		}
		List<SysUserDeptVo> records = sysUserDao.findPageObjects(username, PageUtil.getStartIndex(pageCurrent),
				PageUtil.getPageSize());
		PageObject<SysUserDeptVo> po = PageUtil.setPageObject(pageCurrent, rowCount, PageUtil.getPageSize(), records);
		return po;
	}

	@Override
	public Map<String, Object> findObjectById(Integer id) {
		SysUserDeptVo user = sysUserDao.findObjectById(id);
		if (user == null) {
			throw new ServiceException("用户可能已经不存在");
		}
		List<Integer> roleIds = sysUserRoleDao.findRoleIdsByUserId(id);
		// 4.对如上两次查询结果进行封装
		Map<String, Object> map = new HashMap<>();
		map.put("user", user);
		map.put("roleIds", roleIds);
		return map;
	}

	@RequiresPermissions("sys:user:valid")
	@Override
	public int validById(Integer id, Integer valid, String modifiedUser) {
		int rols = sysUserDao.validById(id, valid, modifiedUser);
		if (rols == 0) {
			throw new ServiceException("记录可能已经不存在");
		}
		return rols;
	}

	@Override
	public int insertObject(SysUser sysUser, Integer[] roleIds) {
		if (sysUser == null) {
			throw new IllegalArgumentException("对象不能为空");
		}
		if (StringUtils.isEmpty(sysUser.getUsername())) {
			throw new IllegalArgumentException("用户名不能为空");
		}
		if (StringUtils.isEmpty(sysUser.getPassword())) {
			throw new IllegalArgumentException("密码不能为空");
		}
		if (roleIds == null || roleIds.length == 0) {
			throw new IllegalArgumentException("所属部门不能为空");
		}
		String salt = UUID.randomUUID().toString();
		SimpleHash sh = new SimpleHash("MD5", sysUser.getPassword(), salt, 1);
		sysUser.setSalt(salt);
		sysUser.setPassword(sh.toHex());
		int rols = sysUserDao.insertObject(sysUser);
		sysUserRoleDao.insertObjects(sysUser.getId(), roleIds);
		return rols;
	}

	@RequiredLog("update user")
	@Override
	public int updateObject(SysUser sysUser, Integer[] roleIds) {
		if (sysUser == null) {
			throw new IllegalArgumentException("对象不能为空");
		}
		if (StringUtils.isEmpty(sysUser.getUsername())) {
			throw new IllegalArgumentException("用户名不能为空");
		}
		if (roleIds == null || roleIds.length == 0) {
			throw new IllegalArgumentException("所属部门不能为空");
		}
		int rols = sysUserDao.updateObject(sysUser);
		if (rols == 0) {
			throw new ServiceException("数据可能已经不存在");
		}
		sysUserRoleDao.deleteObjectsByRoleId(sysUser.getId());
		sysUserRoleDao.insertObjects(sysUser.getId(), roleIds);
		return rols;
	}

	@Override
	public int updatePassword(String pwd, String newPwd, String cfgPwd) {
		if (StringUtils.isEmpty(newPwd))
			throw new IllegalArgumentException("新密码不能为空");
		if (StringUtils.isEmpty(cfgPwd))
			throw new IllegalArgumentException("确认密码不能为空");
		if (!newPwd.equals(cfgPwd))
			throw new IllegalArgumentException("两次输入的密码不相等");
		if (StringUtils.isEmpty(pwd))
			throw new IllegalArgumentException("原密码不能为空");
		SysUser user = (SysUser) SecurityUtils.getSubject().getPrincipal();
		Integer id = user.getId();
		SimpleHash sh = new SimpleHash("MD5", pwd, user.getSalt(), 1);
		if (!user.getPassword().equals(sh.toHex()))
			throw new IllegalArgumentException("原密码不正确");
		String salt = UUID.randomUUID().toString();
		sh = new SimpleHash("MD5", newPwd, salt, 1);
		int rows = sysUserDao.updatePassword(sh.toHex(), salt, id);
		if (rows == 0)
			throw new ServiceException("修改失败");
		return rows;
	}

}
