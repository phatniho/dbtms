package com.company.db.sys.dao;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.company.db.entity.SysLog;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class SysLogDaoTests {

	@Autowired
	private SysLogsDao sysLogsDao;

	@Test
	public void testGetRowCount() {
		int count = sysLogsDao.getRowCount("admin");
		log.info("查到记录数:" + count);
	}

	@Test
	public void testFindPageObjects() {
		List<SysLog> fpos = sysLogsDao.findPageObjects("admin", 1, 5);
		for (SysLog sys : fpos) {
			log.info("" + sys);
		}
	}
}
