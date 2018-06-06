package com.logistics.routemanagement.service.impl;

import com.logistics.routemanagement.dao.RouteManagementDao;
import com.logistics.routemanagement.service.RouteManagementService;

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

}
