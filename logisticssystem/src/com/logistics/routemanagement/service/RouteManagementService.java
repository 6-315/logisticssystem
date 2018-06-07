package com.logistics.routemanagement.service;


import com.logistics.domain.route;
import com.logistics.routemanagement.RouteManagerVO.RouteManagerVO;

/**
 * 路线管理service层接口
 * @author LW
 *
 */
public interface RouteManagementService {

	void addRout(route rout);

	void updateRoutInfo(route rout);

	void updateRouteState(route rout);

	void deleteListRoute(String routeId);

	RouteManagerVO getRouteManagerVO(RouteManagerVO routManagerVO);




}
