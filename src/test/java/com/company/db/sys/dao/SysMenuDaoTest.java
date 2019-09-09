package com.company.db.sys.dao;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.company.db.entity.SysMenu;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class SysMenuDaoTest {

	@Autowired
	SysMenuDao sysMenuDao;

	@Test
	public void testFindObject() {
		List<Map<String, SysMenu>> list = sysMenuDao.findObjects();
		for (Map<String, SysMenu> sys : list) {
			log.info("sys:" + sys);
		}
	}

	@Test
	public void testDeleteObject() {
		int rols = sysMenuDao.deleteObject(48);
		log.info("影响的行数:" + rols);
	}
}
