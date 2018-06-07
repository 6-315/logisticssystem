package com.logistics.routemanagement.service;

import java.util.List;

import com.logistics.domain.route;

/**
 * 路线管理service层接口
 * @author LW
 *
 */
public interface RouteManagementService {

	void addRout(route rout);

	void updateRoutInfo(route rout);

	void updateRouteState(route rout);

	List<route> deleteListRoute(List<route> listrout);




}
