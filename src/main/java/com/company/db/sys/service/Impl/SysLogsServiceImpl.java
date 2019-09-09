package com.company.db.sys.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.db.common.exception.ServiceException;
import com.company.db.common.vo.PageObject;
import com.company.db.common.util.PageUtil;
import com.company.db.entity.SysLog;
import com.company.db.sys.dao.SysLogsDao;
import com.company.db.sys.service.SysLogsService;

@Service
public class SysLogsServiceImpl implements SysLogsService {

	@Autowired
	SysLogsDao sysLogsDao;

	@Override
	public int deleteObjects(Integer... ids) {
		int rows = sysLogsDao.deleteObjects(ids);
		return rows;
	}

	@Override
	public PageObject<SysLog> findPageObjects(String username, Integer pageCurrent) {
		if (pageCurrent == 0 || pageCurrent < 1) {
			throw new IllegalArgumentException("页码不正确");
		}
		int rowCount = sysLogsDao.getRowCount(username);
		if (rowCount == 0) {
			throw new ServiceException("记录不存在");
		}
		int pageSize = PageUtil.getPageSize();
		int startIndex = PageUtil.getStartIndex(pageCurrent);
		List<SysLog> records = sysLogsDao.findPageObjects(username, startIndex, pageSize);

		return PageUtil.setPageObject(pageCurrent, rowCount, pageSize, records);
	}
}
