package com.logistics.distribution.service.impl;

import com.logistics.distribution.dao.DistributionDao;

/**
 * 配送点管理业务实现层
 * 
 * @author LW
 *
 */
public class DistributionServiceImpl {
	private DistributionDao distributionDao;

	public void setDistributionDao(DistributionDao distributionDao) {
		this.distributionDao = distributionDao;
	}

}
