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
public class DistributionServiceImpl implements DistributionService {
	private DistributionDao distributionDao;

	public void setDistributionDao(DistributionDao distributionDao) {
		this.distributionDao = distributionDao;
	}

	/**
	 * 添加配送点
	 */
	@Override
	public String addDistribution(unit distribution) {
		unit unit = new unit();
		String maxNum = distributionDao.getDistributionByNum(unit.getUnit_num());
		unit superiorunit = distributionDao.getDistributionInfoById(distribution.getUnit_superiorunit());
		String beforNum = superiorunit.getUnit_num();
		if(beforNum!=null&&superiorunit!=null) {
		if (maxNum != null) {
			maxNum = maxNum.substring(5);
			int nextNum = Integer.parseInt(maxNum);
			nextNum = nextNum + 1;
			String num = String.format("%02d", nextNum);
			distribution.setUnit_num(beforNum+'B'+num);
		} else {
			int nextNum = 1;
			String num = String.format("%02d", nextNum);
			distribution.setUnit_num(beforNum+'B'+num);
		}
		}
		distribution.setUnit_id(BuildUuid.getUuid());
		distribution.setUnit_createtime(TimeUtil.getStringSecond());
		distribution.setUnit_modifytime(TimeUtil.getStringSecond());
		distributionDao.saveOrUpdateObject(distribution);
		return "success";
	}

	/**
	 * 修改配送点
	 */
	@Override
	public String updateDistribution(unit distribution) {
		unit update = new unit();
		update = distributionDao.getDistributionInfoById(distribution.getUnit_id());
		update.setUnit_address(distribution.getUnit_address());
		update.setUnit_state(distribution.getUnit_state());
		update.setUnit_admin(distribution.getUnit_admin());
		distributionDao.saveOrUpdateObject(update);
		return "success";
	}

	/**
	 * 批量删除配送点
	 */
	@Override
	public String deleteDistribution(String idList) {
		if (idList != null && idList.trim().length() > 0) {
			/*
			 * 将获得的对象转化为数组
			 */
			String[] deleteDistributionById = idList.split(",");
			/**
			 * 遍历需要删除的配送点数组
			 */
			for (String id : deleteDistributionById) {
				/**
				 * 如果数据库存在需要删除的配送点的id
				 */
				if (distributionDao.getDistributionInfoById(id) != null) {
					distributionDao.removeObject(distributionDao.getDistributionInfoById(id));
					return "deleteSuccess";
				}
				/**
				 * 如果数据库不存在需要删除的配送点的id
				 */
				else {
					return "deleteFailed";
				}
			}
		}
		return "deleteFailed";

	}
}

/**
 * 中转站管理员可以查看所有配送点信息
 */
