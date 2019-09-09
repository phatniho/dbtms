package com.company.db.sys.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.company.db.common.vo.PageObject;
import com.company.db.entity.SysLog;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SysLogServiceTests {
	@Autowired
	private SysLogsService sysLogsService;

	@Test
	public void findPageObjectsTest() {
		PageObject<SysLog> pages = sysLogsService.findPageObjects("admin", 3);
		System.out.println(pages);
	}
}
