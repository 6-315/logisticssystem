package com.logistics.expressmanagement.service.impl;

import com.logistics.expressmanagement.dao.ExpressManagementDao;

import com.logistics.expressmanagement.service.ExpressManagementService;
/**
 * 快件管理的业务层
 * @author LW
 *
 */

public class ExpressManagementServiceImpl implements ExpressManagementService {
	/**
	 * dao层注入
	 */
	private ExpressManagementDao expressManagementDao;

	public void setExpressManagementDao(ExpressManagementDao expressManagementDao) {
		this.expressManagementDao = expressManagementDao;
	}
	/**
	 * 结束
	 */

}
