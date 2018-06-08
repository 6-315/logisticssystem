package com.logistics.routemanagement.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.swing.plaf.synth.SynthSeparatorUI;

import com.logistics.domain.route;
import com.logistics.domain.staff_basicinfo;
import com.logistics.domain.unit;
import com.logistics.routemanagement.RouteManagerDTO.RouteManagerDTO;
import com.logistics.routemanagement.RouteManagerVO.RouteManagerVO;
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
	/**
	 * 批量删除路线
	 */
	@Override
	public void deleteListRoute(String routeIds) {
		String[] routeIDArray = routeIds.split(",");
		route deleteRoute = null;
		for (String routeId : routeIDArray) {
			deleteRoute = new route();
			deleteRoute = routeManagementDao.getRouteById(routeId);
			if(deleteRoute!=null) {
				routeManagementDao.removeObject(deleteRoute);
			}
		}
	}
	/**
	 * 获取路线列表，分页
	 */
	@Override
	public RouteManagerVO getRouteManagerVO(RouteManagerVO routeManagerVO) {
		// 先初始化route表集合
		List<route> listRoute = new ArrayList<>();
		// 初始化DTO集合
		List<RouteManagerDTO> listRouteManagerDTO = new ArrayList<>();
		//查询分页，表
		String searchPaging = "select count(*) from route  where 1=1 ";
		String searchForm = "from route where 1=1 ";
		// 根据路线编号查询：
		if (routeManagerVO.getSearch() != null && routeManagerVO.getSearch().trim().length() > 0) {
			String search = "%" + routeManagerVO.getSearch() + "%";
			searchPaging = searchPaging + " and rout_num like '" + search + "' ";
			searchForm = searchForm + " and rout_num like '" + search + "'";
		}
		// 根据状态查询
		if (routeManagerVO.getState() != null && routeManagerVO.getState().trim().length() > 0) {
			String search = "%" + routeManagerVO.getState() + "%";
			searchPaging = searchPaging + "and route_state like '" + search + "'";
			searchForm = searchForm + "and route_state like '" + search + "'";
		}
		// 根据始发中转站查询
		if (routeManagerVO.getStartUnit() != null && routeManagerVO.getStartUnit().trim().length() > 0) {
			String search = "%" + routeManagerVO.getStartUnit() + "%";
			searchPaging = searchPaging + " and route_departurestation like '" + search + "'";
			searchForm = searchForm + "and route_departurestation like '" + search + "'";
		}
		// 根据终止中转站查询
		if (routeManagerVO.getEndUnit() != null && routeManagerVO.getEndUnit().trim().length() > 0) {
			String search = "%" + routeManagerVO.getEndUnit() + "%";
			searchPaging = searchPaging + " and route_terminalstation like '" + search + "'";
			searchForm = searchForm + "and route_terminalstation like '" + search + "'";
		}
		// 这里如果不加desc表示正序，如果加**/上desc表示倒序
		searchForm = searchForm + "order by route_createtime desc";
		System.out.println("fdfdfdfd:---------"+searchPaging);
		int userInfoCount = routeManagementDao.getCount(searchPaging);
		// 设置总数量
		routeManagerVO.setTotalRecords(userInfoCount);
		// 设置总页数
		routeManagerVO.setTotalPages(((userInfoCount - 1) / routeManagerVO.getPageSize()) + 1);
		// 判断是否拥有上一页
		if (routeManagerVO.getPageIndex() <= 1) {
			routeManagerVO.setHavePrePage(false);
		} else {
			routeManagerVO.setHavePrePage(true);
		}
		// 判断是否拥有下一页
		if (routeManagerVO.getPageIndex() >= routeManagerVO.getTotalPages()) {
			routeManagerVO.setHaveNextPage(false);
		} else {
			routeManagerVO.setHaveNextPage(true);
		}
		listRoute = (List<route>) routeManagementDao.queryForPage(searchForm, routeManagerVO.getPageIndex(),
				routeManagerVO.getPageSize());
		
		for (route route : listRoute) {
			RouteManagerDTO routeMangerDTO = new RouteManagerDTO();
			staff_basicinfo staffBasicinfo = new staff_basicinfo();
			//始发站，终止站
			unit routeDeparturestation = new unit();
			unit routeTerminalstation = new unit();
			String sql = "from staff_basicinfo where staff_id='" + route.getRoute_creater() + "'";
			staffBasicinfo = routeManagementDao.getStaff_Basicinfo(sql);
			String sql1 = "from unit where unit_id='" + route.getRoute_departurestation() + "'";
			routeDeparturestation = routeManagementDao.getRoute_Departurestation(sql1);
			String sql2 = "from unit where unit_id='" + route.getRoute_terminalstation() + "'";
			routeTerminalstation = routeManagementDao.getRoute_Terminalstation(sql2);

			staffBasicinfo.getStaff_id();
			routeDeparturestation.getUnit_id();
			routeTerminalstation.getUnit_id();
			if (routeManagerVO.getSearch() != null && routeManagerVO.getSearch().trim().length() > 0) {
				route.setRoute_num(route.getRoute_num().replaceAll(routeManagerVO.getSearch(),
						"<mark>" + routeManagerVO.getSearch() + "</mark>"));
			}
			routeMangerDTO.setRout(route);
			routeMangerDTO.setStaff_Id(staffBasicinfo);
			routeMangerDTO.setRoute_Departurestation(routeDeparturestation);
			routeMangerDTO.setRoute_Terminalstation(routeTerminalstation);
			listRouteManagerDTO.add(routeMangerDTO);
		}
		routeManagerVO.setListRouteManagerDTO(listRouteManagerDTO);
		return routeManagerVO;
	}

}
