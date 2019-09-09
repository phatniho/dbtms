package com.company.db.sys.controller;

import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.company.db.common.vo.JsonResult;
import com.company.db.common.vo.PageObject;
import com.company.db.entity.SysUser;
import com.company.db.sys.service.SysUserService;
import com.company.db.vo.SysUserDeptVo;

@Controller
@RequestMapping("/user/")
public class SysUserController {

	@Autowired
	SysUserService sysUserService;

	@RequestMapping("doUserListUI")
	public String doUserListUI() {
		return "sys/user_list";
	}

	@RequestMapping("doUserEditUI")
	public String doUserEditUI() {
		return "sys/user_edit";
	}

	@RequestMapping("doPwdEditUI")
	public String doPwdEditUI() {
		return "sys/pwd_edit";
	}

	@RequestMapping("doUpdatePassword")
	@ResponseBody
	public JsonResult doUpdatePassword(String pwd, String newPwd, String cfgPwd) {
		int rols = sysUserService.updatePassword(pwd, newPwd, cfgPwd);
		return new JsonResult("修改成功" + rols);
	}

	@RequestMapping("doLogin")
	@ResponseBody
	public JsonResult doLogin(String username, String password, boolean isRememberMe) {
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		System.out.println(username + "+" + password + "+" + isRememberMe);
		if (isRememberMe) {
			token.setRememberMe(true);
		}
		Subject subject = SecurityUtils.getSubject();
		subject.login(token);
		return new JsonResult("登录成功!");
	}

	@RequestMapping("doValidById")
	@ResponseBody
	public JsonResult doValidById(Integer id, Integer valid, String modifiedUser) {
		int rols = sysUserService.validById(id, valid, modifiedUser);
		return new JsonResult(rols);
	}

	@RequestMapping("doSaveObject")
	@ResponseBody
	public JsonResult doSaveObject(SysUser sysUser, Integer[] roleIds) {
		int rols = sysUserService.insertObject(sysUser, roleIds);
		return new JsonResult("添加成功!" + rols);
	}

	@RequestMapping("doUpdateObject")
	@ResponseBody
	public JsonResult doUpdateObject(SysUser sysUser, Integer[] roleIds) {
		int rols = sysUserService.updateObject(sysUser, roleIds);
		return new JsonResult("修改成功!" + rols);
	}

	@RequestMapping("doFindObjectById")
	@ResponseBody
	public JsonResult doFindObjectById(Integer id) {
		Map<String, Object> bean = sysUserService.findObjectById(id);
		return new JsonResult(bean);
	}

	@RequestMapping("doFindPageObjects")
	@ResponseBody
	public JsonResult doFindPageObjects(String username, Integer pageCurrent) {
		PageObject<SysUserDeptVo> bean = sysUserService.findPageObjects(username, pageCurrent);
		return new JsonResult(bean);
	}
}
