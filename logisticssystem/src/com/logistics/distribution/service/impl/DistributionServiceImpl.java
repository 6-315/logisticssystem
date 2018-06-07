package com.logistics.distribution.service.impl;

import com.logistics.distribution.dao.DistributionDao;
import com.logistics.distribution.service.DistributionService;
import com.logistics.domain.unit;

import util.BuildUuid;
import util.TimeUtil;

/**
 * 配送点管理业务实现层
 * 
 * @author LW
 *
 */
public class DistributionServiceImpl  implements DistributionService{
	private DistributionDao distributionDao;

	public void setDistributionDao(DistributionDao distributionDao) {
		this.distributionDao = distributionDao;
	}
/**
 * 添加配送点业务逻辑实现层
 */
	@Override
	public void addDistributionAction(unit distribution) {
		// TODO Auto-generated method stub
		distribution.setUnit_id(BuildUuid.getUuid());
		distribution.setUnit_createtime(TimeUtil.getTimeChou());
		distribution.setUnit_modifytime(TimeUtil.getTimeChou());
		System.out.println("woshiyangxin "+distribution);
		distributionDao.saveOrUpdateObject(distribution);
	}

}
