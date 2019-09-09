package com.company.db.sys.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.company.db.common.vo.CheckBox;
import com.company.db.common.vo.JsonResult;
import com.company.db.common.vo.PageObject;
import com.company.db.entity.SysRole;
import com.company.db.sys.service.SysRoleService;
import com.company.db.vo.SysRoleMenuVo;

@Controller
@RequestMapping("/role/")
public class SysRoleController {

	@Autowired
	SysRoleService sysRoleService;

	@RequestMapping("doRoleListUI")
	public String doRolesListUI() {
		return "sys/role_list";
	}

	@RequestMapping("doRoleEditUI")
	public String doRoleEditUI() {
		return "sys/role_edit";
	}

	@RequestMapping("doUpdateObject")
	@ResponseBody
	public JsonResult doUpdateObject(SysRole sysRole, Integer... menuIds) {
		int rols = sysRoleService.updateObject(sysRole, menuIds);
		return new JsonResult("修改成功,修改行数:" + rols);
	}

	@RequestMapping("doSaveObject")
	@ResponseBody
	public JsonResult doSaveObject(SysRole sysRole, Integer... menuIds) {
		int rols = sysRoleService.insertObject(sysRole, menuIds);
		return new JsonResult("保存成功!保存记录数:" + rols);
	}

	@RequestMapping("doFindPageObjects")
	@ResponseBody
	public JsonResult doFindPageObjects(String name, Integer pageCurrent) {
		PageObject<SysRole> row = sysRoleService.findPageObjects(name, pageCurrent);
		return new JsonResult(row);
	}

	@RequestMapping("doDeleteObject")
	@ResponseBody
	public JsonResult doDeleteObject(Integer id) {
		int rols = sysRoleService.deleteObject(id);
		return new JsonResult("删除成功!删除记录数:" + rols);
	}

	@RequestMapping("doFindObjectById")
	@ResponseBody
	public JsonResult doFindObjectById(Integer id) {
		SysRoleMenuVo bean = sysRoleService.findObjectById(id);
		return new JsonResult(bean);
	}

	@RequestMapping("doFindRoles")
	@ResponseBody
	public JsonResult doFindRoles() {
		List<CheckBox> list = sysRoleService.findObjects();
		return new JsonResult(list);
	}

}
