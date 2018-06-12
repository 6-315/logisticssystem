package com.logistics.routemanagement.service;


import com.logistics.domain.route;
import com.logistics.routemanagement.RouteManagerVO.RouteManagerVO;

/**
 * 路线管理service层接口
 * @author LW
 *
 */
public interface RouteManagementService {

	String addRout(route rout);

	String updateRoutInfo(route rout);

	String updateRouteState(route rout);

	String deleteListRoute(String routeId);

	RouteManagerVO getRouteManagerVO(RouteManagerVO routManagerVO);




}
