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
		String routeMax = routeManagementDao.getMaxRouteNum(route.getRoute_num());
		if (routeMax != null) {
			routeMax = routeMax.replaceAll("[A]", "");
			int nextNum = Integer.parseInt(routeMax);
			nextNum = nextNum + 1;
			String num = String.format("%03d", nextNum);
			rout.setRoute_num("A" + num);
			System.out.println("eeee");
		} else {
			int nextNum = 1;
			String num = String.format("%03d", nextNum);
			rout.setRoute_num("A" + num);
		}
		rout.setRoute_id(BuildUuid.getUuid());
		if (rout != null) {
			routeManagementDao.saveOrUpdateObject(rout);
		}
		return rout;
	}

	/**
	 * 更改路线信息
	 */
	@Override
	public String updateRoutInfo(route rout) {
		if (rout.getRoute_id().trim().length() > 0) {
			routeManagementDao.saveOrUpdateObject(rout);
		}
		return "success";
	}

	/**
	 * 更改路线状态
	 */
	@Override
	public String updateRouteState(route rout) {
		if (rout.getRoute_id().trim().length() > 0 && rout.getRoute_state().trim().length() > 0) {
			routeManagementDao.saveOrUpdateObject(rout);
		}
		return "success";

	}

	/**
	 * 批量删除路线
	 * 
	 * @return
	 */
	@Override
	public String removeListRoute(String routeIds) {
		String[] routeIDArray = routeIds.split(",");
		route deleteRoute = null;
		for (String routeId : routeIDArray) {
			deleteRoute = new route();
			deleteRoute = routeManagementDao.getRouteById(routeId);
			if (deleteRoute != null) {
				routeManagementDao.removeObject(deleteRoute);
			}
		}
		return "sccess";
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
			// String search = "%" + routeManagerVO.getState() + "%";
			searchPaging = searchPaging + " and route_state = '" + routeManagerVO.getState() + "'";
			searchForm = searchForm + " and route_state = '" + routeManagerVO.getState() + "'";
		}
		// 根据始发中转站查询
		if (routeManagerVO.getStartUnit() != null && routeManagerVO.getStartUnit().trim().length() > 0) {
			searchPaging = searchPaging + " and route_departurestation = '" + routeManagerVO.getStartUnit() + "'";
			searchForm = searchForm + " and route_departurestation = '" + routeManagerVO.getStartUnit() + "'";
		}
		// 根据终止中转站查询
		if (routeManagerVO.getEndUnit() != null && routeManagerVO.getEndUnit().trim().length() > 0) {
			searchPaging = searchPaging + " and route_terminalstation = '" + routeManagerVO.getEndUnit() + "'";
			searchForm = searchForm + " and route_terminalstation = '" + routeManagerVO.getEndUnit() + "'";
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

			RouteManagerDTO routeMangerDTO = new RouteManagerDTO();
			staff_basicinfo creator = routeManagementDao.getStaff_Basicinfo(" from staff_basicinfo where staff_id ='"+route.getRoute_creater()+"' ");
			routeMangerDTO.setStaff_Id(creator);
			unit beginUnit = routeManagementDao.getUnitInfoById(route.getRoute_departurestation());
			routeMangerDTO.setRoute_Departurestation(beginUnit);
			unit endUnit = routeManagementDao.getUnitInfoById(route.getRoute_terminalstation());
			routeMangerDTO.setRoute_Terminalstation(endUnit);
			routeMangerDTO.setRout(route);
			listRouteManagerDTO.add(routeMangerDTO);
/*			List<staff_basicinfo> liststaffBasicinfo = new ArrayList<>();
			liststaffBasicinfo = (List<staff_basicinfo>) routeManagementDao
					.listObject("from staff_basicinfo where staff_id='" + route.getRoute_creater() + "'");
			List<unit> listUnitD = new ArrayList<>();
			listUnitD = (List<unit>) routeManagementDao
					.listObject("from unit where unit_id = '" + route.getRoute_departurestation() + "'");
			List<unit> listUnitT = new ArrayList<>();
			listUnitT = (List<unit>) routeManagementDao
					.listObject("from unit where unit_id = '" + route.getRoute_terminalstation() + "'");
			if (listUnitT.size() != 0 && listUnitD.size() != 0 && liststaffBasicinfo.size() != 0) {
				routeMangerDTO.setStaff_Id(liststaffBasicinfo.get(0));
				routeMangerDTO.setRoute_Departurestation(listUnitD.get(0));
				routeMangerDTO.setRoute_Terminalstation(listUnitT.get(0));
				routeMangerDTO.setRout(route);
				listRouteManagerDTO.add(routeMangerDTO);
			}*/
			// 高亮
			// 根据路线编号模糊查询高亮
			if (routeManagerVO.getSearch() != null && routeManagerVO.getSearch().trim().length() > 0) {
				route.setRoute_num(route.getRoute_num().replaceAll(routeManagerVO.getSearch(),
						"<span style='color: #ff5063;'>" + routeManagerVO.getSearch() + "</span>"));
			}

		}
		System.out.println("size:" + listRouteManagerDTO.size());
		routeManagerVO.setListRouteManagerDTO(listRouteManagerDTO);
		return routeManagerVO;
	}

}
