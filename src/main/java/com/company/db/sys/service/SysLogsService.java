package com.company.db.sys.service;

import com.company.db.common.vo.PageObject;
import com.company.db.entity.SysLog;

public interface SysLogsService {

	int deleteObjects(Integer... ids);

	PageObject<SysLog> findPageObjects(String username, Integer pageCurrent);
}
