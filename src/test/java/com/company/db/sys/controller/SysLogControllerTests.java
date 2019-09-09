package com.company.db.sys.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.company.db.common.vo.PageObject;
import com.company.db.entity.SysLog;
import com.company.db.sys.service.SysLogsService;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class SysLogControllerTests {

	@Autowired
	SysLogsService sysLogsService;

	@Test
	public void testSysLogController() {
		PageObject<SysLog> obj = sysLogsService.findPageObjects("admin", 1);
		log.info(obj.toString());
	}
}
