package com.company.db.sys.dao;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.company.db.entity.SysRole;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class SysRoleDaoTests {

	@Autowired
	SysRoleDao sysRoleDao;

	@Test
	public void testSysRoleDao() {
		List<SysRole> list = sysRoleDao.findPageObjects(null, 0, 5);
		log.info("list:" + list);
	}
}
