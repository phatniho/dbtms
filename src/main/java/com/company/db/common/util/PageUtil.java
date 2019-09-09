package com.company.db.common.util;

import java.util.List;

import com.company.db.common.vo.PageObject;

public class PageUtil {
	private static int pageSize = 10;

	public static int getPageSize() {
		return pageSize;
	}

	public static int getStartIndex(Integer pageCurrent) {
		return (pageCurrent - 1) * pageSize;
	}

	public static <T> PageObject<T> setPageObject(Integer pageCurrent, int rowCount, int pageSize, List<T> records) {
		PageObject<T> po = new PageObject<>();
		po.setRowCount(rowCount);
		po.setRecords(records);
		po.setPageSize(pageSize);
		po.setPageCount((rowCount - 1) / pageSize + 1);
		po.setPageCurrent(pageCurrent);
		return po;
	}
}
