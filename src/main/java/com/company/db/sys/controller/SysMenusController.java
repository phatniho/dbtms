package com.company.db.sys.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.company.db.common.vo.JsonResult;
import com.company.db.common.vo.Node;
import com.company.db.entity.SysMenu;
import com.company.db.sys.service.SysMenuService;

@Controller
@RequestMapping("/menu/")
public class SysMenusController {

	@Autowired
	SysMenuService sysMenuService;

	@RequestMapping("doMenuListUI")
	public String doMenuListUI() {
		return "sys/menu_list";
	}

	@RequestMapping("doFindObjects")
	@ResponseBody
	public JsonResult doFindObjects() {
		List<Map<String, SysMenu>> list = sysMenuService.findObjects();
		return new JsonResult(list);
	}

	@RequestMapping("doDeleteObject")
	@ResponseBody
	public JsonResult doDeleteObject(Integer id) {
		int rols = sysMenuService.deleteObject(id);
		return new JsonResult("成功删除行数:" + rols);
	}

	@RequestMapping("doMenuEditUI")
	public String doMenuEditUI() {
		return "sys/menu_edit";
	}

	@RequestMapping("doUpdateObject")
	@ResponseBody
	public JsonResult doUpdateObject(SysMenu sysMenu) {
		int rols = sysMenuService.updateObject(sysMenu);
		return new JsonResult("修改成功,修改记录数:" + rols);
	}

	@RequestMapping("doSaveObject")
	@ResponseBody
	public JsonResult doSaveObject(SysMenu sysMenu) {
		int rols = sysMenuService.saveObject(sysMenu);
		return new JsonResult("保存成功!记录+" + rols);
	}

	@RequestMapping("doFindZtreeMenuNodes")
	@ResponseBody
	public JsonResult doFindZtreeMenuNodes() {
		List<Node> list = sysMenuService.findZtreeMenuNodes();
		return new JsonResult(list);
	}
}
