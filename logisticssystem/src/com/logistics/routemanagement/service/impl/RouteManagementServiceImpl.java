package com.logistics.routemanagement.service.impl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.plaf.synth.SynthSeparatorUI;

import com.logistics.domain.*;
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
	 * 
	 * @return
	 */
	@Override
	public route addRout(route rout) {
		route route = new route();
	String	routeMax = routeManagementDao.getMaxRouteNum(route.getRoute_num());
		if(routeMax!=null) {
			routeMax=routeMax.replaceAll("[A]", "");
			int nextNum=Integer.parseInt(routeMax);
			nextNum=nextNum+1;
			String num=String.format("%03d", nextNum);
			rout.setRoute_num("A"+num);
			System.out.println("eeee");
		}else {
			int nextNum=1;
			String num=String.format("%03d", nextNum);
			rout.setRoute_num("A"+num);
		}
		rout.setRoute_id(BuildUuid.getUuid());
		routeManagementDao.saveOrUpdateObject(rout);
		return rout;
	}

	/**
	 * 更改路线信息
	 */
	@Override
	public route updateRoutInfo(route rout) {
		routeManagementDao.saveOrUpdateObject(rout);
		return rout;
	}

	/**
	 * 更改路线状态
	 */
	@Override
	public route updateRouteState(route rout) {
		routeManagementDao.saveOrUpdateObject(rout);
		return rout;

	}

	/**
	 * 批量删除路线
	 * 
	 * @return
	 */
	@Override
	public String deleteListRoute(String routeIds) {
		String[] routeIDArray = routeIds.split(",");
		route deleteRoute = null;
		for (String routeId : routeIDArray) {
			deleteRoute = new route();
			deleteRoute = routeManagementDao.getRouteById(routeId);
			if (deleteRoute != null) {
				routeManagementDao.removeObject(deleteRoute);
			}
		}
		return "Success";
	}

	/**
	 * 获取路线列表，分页
	 */
	@SuppressWarnings("unchecked")
	@Override
	public RouteManagerVO getRouteManagerVO(RouteManagerVO routeManagerVO) {
		// 先初始化route表集合
		List<route> listRoute = new ArrayList<>();
		// 初始化DTO集合
		List<RouteManagerDTO> listRouteManagerDTO = new ArrayList<>();
		RouteManagerDTO routeManagerDTO;
		// 查询分页，表
		String searchPaging = " select count(*) from route where 1=1 ";
		String searchForm = " from route where 1=1 ";
		// 根据路线编号查询：
		if (routeManagerVO.getSearch() != null && routeManagerVO.getSearch().trim().length() > 0) {
			String search = "%" + routeManagerVO.getSearch() + "%";
			searchPaging = searchPaging + " and route_num like '" + search + "' ";
			searchForm = searchForm + " and route_num like '" + search + "'";
		}
		// 根据状态查询
		if (routeManagerVO.getState() != null && routeManagerVO.getState().trim().length() > 0) {
			String search = "%" + routeManagerVO.getState() + "%";
			searchPaging = searchPaging + " and route_state = '" + search + "'";
			searchForm = searchForm + " and route_state = '" + search + "'";
		}
		// 根据始发中转站查询
		if (routeManagerVO.getStartUnit() != null && routeManagerVO.getStartUnit().trim().length() > 0) {
			String search = "%" + routeManagerVO.getStartUnit() + "%";
			searchPaging = searchPaging + " and route_departurestation = '" + search + "'";
			searchForm = searchForm + " and route_departurestation = '" + search + "'";
		}
		// 根据终止中转站查询
		if (routeManagerVO.getEndUnit() != null && routeManagerVO.getEndUnit().trim().length() > 0) {
			String search = "%" + routeManagerVO.getEndUnit() + "%";
			searchPaging = searchPaging + " and route_terminalstation = '" + search + "'";
			searchForm = searchForm + " and route_terminalstation = '" + search + "'";
		}

		// 这里如果不加desc表示正序，如果加**/上desc表示倒序
		searchForm = searchForm + " order by route_createtime desc";
		// 获取对象的总数量
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
		System.out.println("888888" + searchForm);
		// 获取rout表的内容---当前页，每页的记录条数
		listRoute = (List<route>) routeManagementDao.queryForPage(searchForm, routeManagerVO.getPageIndex(),
				routeManagerVO.getPageSize());
		System.out.println("--------------------a a a a a " + listRoute.size());
		for (route route : listRoute) {
			System.out.println("这是什么" + route.getRoute_id());
			RouteManagerDTO routeMangerDTO = new RouteManagerDTO();
			List<staff_basicinfo> liststaffBasicinfo = new ArrayList<>();
			liststaffBasicinfo = (List<staff_basicinfo>) routeManagementDao
					.listObject("from staff_basicinfo where staff_id='" + route.getRoute_creater() + "'");

			List<unit> listUnitD = new ArrayList<>();
			listUnitD = (List<unit>) routeManagementDao
					.listObject("from unit where unit_id = '" + route.getRoute_departurestation() + "'");
			System.out.println("________:" + listUnitD.get(0).getUnit_id());
			List<unit> listUnitT = new ArrayList<>();
			listUnitT = (List<unit>) routeManagementDao
					.listObject("from unit where unit_id = '" + route.getRoute_terminalstation() + "'");
			System.out.println("gggggggggg:" + listUnitT.size());
			if (listUnitT.size() != 0 && listUnitD.size() != 0 && liststaffBasicinfo.size() != 0) {
				routeMangerDTO.setStaff_Id(liststaffBasicinfo.get(0));
				routeMangerDTO.setRoute_Departurestation(listUnitD.get(0));
				System.out.println("MMMMMMMMMMM" + routeMangerDTO);
				routeMangerDTO.setRoute_Terminalstation(listUnitT.get(0));
				System.out.println("PPPPPP:" + routeMangerDTO);
				listRouteManagerDTO.add(routeMangerDTO);
				System.out.println();
			}
			// 高亮
			// 根据路线编号模糊查询高亮
			if (routeManagerVO.getSearch() != null && routeManagerVO.getSearch().trim().length() > 0) {
				route.setRoute_num(route.getRoute_num().replaceAll(routeManagerVO.getSearch(),
						"<mark>" + routeManagerVO.getSearch() + "</mark>"));
			}

		}

		/*
		 * for (route route : listRoute) { RouteManagerDTO routeMangerDTO = new
		 * RouteManagerDTO(); staff_basicinfo staffBasicinfo = new staff_basicinfo(); //
		 * 始发站，终止站 unit routeDeparturestation = new unit(); unit routeTerminalstation =
		 * new unit(); String sql = "from staff_basicinfo where staff_id='" +
		 * route.getRoute_creater() + "'"; staffBasicinfo =
		 * routeManagementDao.getStaff_Basicinfo(sql); String sql1 =
		 * "from unit where unit_id='" + route.getRoute_departurestation() + "'";
		 * routeDeparturestation = routeManagementDao.getRoute_Departurestation(sql1);
		 * String sql2 = "from unit where unit_id='" + route.getRoute_terminalstation()
		 * + "'"; routeTerminalstation =
		 * routeManagementDao.getRoute_Terminalstation(sql2);
		 * 
		 * staffBasicinfo.getStaff_id(); routeDeparturestation.getUnit_id();
		 * routeTerminalstation.getUnit_id(); System.out.println("5555" + route);
		 * System.out.println("5555" + staffBasicinfo); System.out.println("5555" +
		 * routeDeparturestation); if (routeManagerVO.getSearch() != null &&
		 * routeManagerVO.getSearch().trim().length() > 0) {
		 * route.setRoute_num(route.getRoute_num().replaceAll(routeManagerVO.getSearch()
		 * , "<mark>" + routeManagerVO.getSearch() + "</mark>")); }
		 * routeMangerDTO.setRout(route); routeMangerDTO.setStaff_Id(staffBasicinfo);
		 * routeMangerDTO.setRoute_Departurestation(routeDeparturestation);
		 * routeMangerDTO.setRoute_Terminalstation(routeTerminalstation);
		 * listRouteManagerDTO.add(routeMangerDTO); }
		 */
		System.out.println("3333333" + listRouteManagerDTO);
		routeManagerVO.setListRouteManagerDTO(listRouteManagerDTO);
		System.out.println("hsahdioashdoihasodh" + routeManagerVO);
		return routeManagerVO;
	}

}
