package com.logistics.routemanagement.service;


import com.logistics.domain.route;
import com.logistics.routemanagement.RouteManagerVO.RouteManagerVO;

/**
 * 路线管理service层接口
 * @author LW
 *
 */
public interface RouteManagementService {

	route addRout(route rout);

	String updateRoutInfo(route rout);

	String updateRouteState(route rout);

	String removeListRoute(String routeId);

	RouteManagerVO getRouteManagerVO(RouteManagerVO routManagerVO);




}
