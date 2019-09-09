package com.company.db.sys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.company.db.common.vo.JsonResult;
import com.company.db.sys.service.SysLogsService;

@Controller
@RequestMapping("/log/")
public class SysLogsController {
	@Autowired
	private SysLogsService sysLogsService;

	@RequestMapping("doDeleteObjects")
	@ResponseBody
	public JsonResult doDeleteObjects(Integer... ids) {
		int rows = sysLogsService.deleteObjects(ids);
		return new JsonResult("成功删除行数:" + rows);
	}

	@RequestMapping("doLogListUI")
	public String doLogListUI() {
//		return "ajax/logtest";
		return "sys/Log_list";
	}

	@RequestMapping("doLogTest")
	public String doLogTest() {
		return "ajax/logtest";
	}

	@RequestMapping("doFindPageObjects")
	@ResponseBody
	public JsonResult doFindPageObjects(Integer pageCurrent, String username) {
		return new JsonResult(sysLogsService.findPageObjects(username, pageCurrent));
	}
}
