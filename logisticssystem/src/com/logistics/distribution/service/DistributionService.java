package com.logistics.distribution.service;

import com.logistics.domain.unit;

/**
 * 配送点管理的service接口
 * 
 * @author LW
 *
 */
public interface DistributionService {
	/**
	 * 添加配送点
	 * 
	 * @param distribution
	 * @return
	 */
	String addDistribution(unit distribution);

	/**
	 * 修改配送点
	 * 
	 * @param distribution
	 * @return
	 */
	String updateDistribution(unit distribution);

	/**
	 * 删除配送点
	 * 
	 * @param idList
	 * @return
	 */
	String deleteDistribution(String idList);

}
