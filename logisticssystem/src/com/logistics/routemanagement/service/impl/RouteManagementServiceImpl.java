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
		for (String routeId : routeIDArray) {
			System.out.println(routeId);
			routeManagementDao.removeObject(routeIds);
		}

	}

	/**
	 * 获取路线列表，分页
	 */
	@Override
	public RouteManagerVO getRouteManagerVO(RouteManagerVO RouteManagerVO) {
		// 先初始化route表集合
		List<route> listroute = new ArrayList<>();
		// 初始化DTO集合
		List<RouteManagerDTO> listDTO = new ArrayList<>();
		String searchFenYe = "select count(*) from route where ";
		String searchForm = "from route where ";
		// 根据路线编号查询：
		if (RouteManagerVO.getSearch() != null && RouteManagerVO.getSearch().trim().length() > 0) {
			String search = "%" + RouteManagerVO.getSearch() + "%";
			searchFenYe = searchFenYe + " rout_num like '" + search + "' ";
			searchForm = searchForm + " rout_num like '" + search + "'";
		}
		// 根据状态查询
		if (RouteManagerVO.getState() != null && RouteManagerVO.getState().trim().length() > 0) {
			String search = "%" + RouteManagerVO.getState() + "%";
			searchFenYe = searchFenYe + "and route_state like '" + search + "'";
			searchForm = searchForm + "and route_state like '" + search + "'";
		}
		// 根据始发中转站查询
		if (RouteManagerVO.getStartUnit() != null && RouteManagerVO.getStartUnit().trim().length() > 0) {
			String search = "%" + RouteManagerVO.getStartUnit() + "%";
			searchFenYe = searchFenYe + " and route_departurestation like '" + search + "'";
			searchForm = searchForm + "and route_departurestation like '" + search + "'";
		}
		// 根据终止中转站查询
		if (RouteManagerVO.getEndUnit() != null && RouteManagerVO.getEndUnit().trim().length() > 0) {
			String search = "%" + RouteManagerVO.getEndUnit() + "%";
			searchFenYe = searchFenYe + " and route_terminalstation like '" + search + "'";
			searchForm = searchForm + "and route_terminalstation like '" + search + "'";
		}
		// 这里如果不加desc表示正序，如果加**/上desc表示倒序
		searchForm = searchForm + "order by route_createtime desc";
		int userInfoCount = routeManagementDao.getCount(searchFenYe);
		// 设置总数量
		RouteManagerVO.setTotalRecords(userInfoCount);
		// 设置总页数
		RouteManagerVO.setTotalPages(((userInfoCount - 1) / RouteManagerVO.getPageSize()) + 1);
		// 判断是否拥有上一页
		if (RouteManagerVO.getPageIndex() <= 1) {
			RouteManagerVO.setHavePrePage(false);
		} else {
			RouteManagerVO.setHavePrePage(true);
		}
		// 判断是否拥有下一页
		if (RouteManagerVO.getPageIndex() >= RouteManagerVO.getTotalPages()) {
			RouteManagerVO.setHaveNextPage(false);
		} else {
			RouteManagerVO.setHaveNextPage(true);
		}
		listroute = (List<route>) routeManagementDao.queryForPage(searchForm, RouteManagerVO.getPageIndex(),
				RouteManagerVO.getPageSize());
		for (route route : listroute) {
			RouteManagerDTO routeMangerDTO = new RouteManagerDTO();
			staff_basicinfo staff_Basicinfo = new staff_basicinfo();
			unit unit1 = new unit();
			unit unit2 = new unit();
			staff_Basicinfo = (staff_basicinfo) routeManagementDao
					.listObject("from staff_basicinfo where staff_id='" + route.getRoute_creater() + "'");
			unit1 = (unit) routeManagementDao
					.listObject("from unit where unit_id='" + route.getRoute_departurestation() + "'");
			unit2 = (unit) routeManagementDao
					.listObject("from unit where unit_id='" + route.getRoute_terminalstation() + "'");
			staff_Basicinfo.getStaff_id();
			unit1.getUnit_id();
			unit2.getUnit_id();
			if (RouteManagerVO.getSearch() != null && RouteManagerVO.getSearch().trim().length() > 0) {
				route.setRoute_num(route.getRoute_num().replaceAll(RouteManagerVO.getSearch(),
						"<mark>" + RouteManagerVO.getSearch() + "</mark>"));
			}
			routeMangerDTO.setRout(route);
			routeMangerDTO.setListstaff_basicinf(staff_Basicinfo);
			routeMangerDTO.setUnit1(unit1);
			routeMangerDTO.setUnit2(unit2);
			listDTO.add(routeMangerDTO);
		}
		RouteManagerVO.setListRouteManagerDTO(listDTO);
		return RouteManagerVO;
	}

	/**
	 * 批量删除路线
	 */

}
