package com.logistics.routemanagement.service.impl;

import java.util.List;

import com.logistics.domain.route;
import com.logistics.routemanagement.dao.RouteManagementDao;
import com.logistics.routemanagement.service.RouteManagementService;

import util.BuildUuid;

/**
 * 路线管理的业务接口层
 * 
 * @author LW
 *
 */
public class RouteManagementServiceImpl implements RouteManagementService {
	private RouteManagementDao routeManagementDao;

	public void setRouteManagementDao(RouteManagementDao routeManagementDao) {
		this.routeManagementDao = routeManagementDao;
	}

	/**
	 * 路线增加
	 */
	@Override
	public void addRout(route rout) {
		rout.setRoute_id(BuildUuid.getUuid());
		routeManagementDao.saveOrUpdateObject(rout);

	}

	/**
	 * 更改路线信息
	 */
	@Override
	public void updateRoutInfo(route rout) {
		routeManagementDao.saveOrUpdateObject(rout);

	}

	/**
	 * 更改路线状态
	 */
	@Override
	public void updateRouteState(route rout) {
		routeManagementDao.saveOrUpdateObject(rout);
		
	}

	@Override
	public List<route> deleteListRoute(List<route> listrout) {
		routeManagementDao.removeObject(listrout);
		return listrout;
	}

	/**
	 * 批量删除路线
	 */


}
