package com.logistics.expressmanagementW.service.impl;

import com.logistics.expressmanagementW.dao.ExpressManagementDao2;
import com.logistics.expressmanagementW.service.ExpressManagementService2;

public class ExpressManagementServiceImpl2 implements ExpressManagementService2 {
	/**
	 * dao层注入
	 */
	private ExpressManagementDao2 expressManagementDao2;

	public void setExpressManagementDao2(ExpressManagementDao2 expressManagementDao2) {
		this.expressManagementDao2 = expressManagementDao2;
	}
	/**
	 * 结束
	 */

}
