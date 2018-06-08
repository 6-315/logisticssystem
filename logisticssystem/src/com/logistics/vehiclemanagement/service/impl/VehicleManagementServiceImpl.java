package com.logistics.vehiclemanagement.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.logistics.domain.vehicle;
import com.logistics.vehiclemanagement.VO.vehicleVO;

import com.logistics.vehiclemanagement.dao.VehicleManagementDao;
import com.logistics.vehiclemanagement.service.VehicleManagementService;

import util.BuildUuid;
import util.TimeUtil;

/**
 * 车辆管理业务实现层
 * 
 * @author LW
 *
 */
public class VehicleManagementServiceImpl implements VehicleManagementService {
	private VehicleManagementDao vehicleManagementDao;

	public void setVehicleManagementDao(VehicleManagementDao vehicleManagementDao) {
		this.vehicleManagementDao = vehicleManagementDao;
	}

	/**
	 * 添加车辆
	 */
	@Override
	public int addVehicle(vehicle vehicleinfo) {
		vehicleinfo.setVehicle_id(BuildUuid.getUuid());
		vehicleinfo.setVehicle_acquisitiontime(TimeUtil.getStringSecond());
		vehicleinfo.setVehicle_createtime(TimeUtil.getStringSecond());
		vehicleinfo.setVehicle_modifytime(TimeUtil.getStringSecond());
		vehicleinfo.setVehicle_mark("None");
		vehicleManagementDao.saveOrUpdateObject(vehicleinfo);
		return 1;
	}

	/**
	 * 分页查询车辆
	 * 
	 */
	@SuppressWarnings({ "unchecked" })
	@Override
	public vehicleVO queryVehicle(vehicleVO vehicleinfoVO) {
		List<vehicle> listvehicle = new ArrayList<>();
		/**
		 * 获取数量
		 */

		String vehicleCountHql = "select count(*) from vehicle ";
		/**
		 * 链接数量的hql以及遍历的hql
		 */
		String listvehicleCountHql = "from vehicle ";

		/**
		 * 根据关键词进行模糊查询
		 */
		if (vehicleinfoVO.getSearch() != null && vehicleinfoVO.getSearch().trim().length() > 0) {
			String search = "%" + vehicleinfoVO.getSearch().trim() + "%";
			vehicleCountHql = vehicleCountHql + "where vehicle_num like '" + search + "' ";
			listvehicleCountHql = listvehicleCountHql + "where vehicle_num like '" + search + "'";
			vehicleCountHql = vehicleCountHql + "and vehicle_platenum like '" + search + "' ";
			listvehicleCountHql = listvehicleCountHql + "and vehicle_platenum like '" + search + "'";
		}
		
		/**
		 * 按状态(state)分类查询
		 */
		if(vehicleinfoVO.getState() != null && vehicleinfoVO.getState().trim().length() > 0) {
			String state = vehicleinfoVO.getState().trim();
			vehicleCountHql = vehicleCountHql + "and vehicle_state = '" + state + "' ";
			listvehicleCountHql = listvehicleCountHql + "and vehicle_state = '" + state + "'";
		}
		System.out.println("1111111111"+vehicleCountHql);
		System.out.println("222222222"+listvehicleCountHql);
		/**
		 * 按所属单位(unit)分类查询
		 */
		if(vehicleinfoVO.getUnit() != null && vehicleinfoVO.getUnit().trim().length() > 0) {
			String unit = vehicleinfoVO.getUnit().trim();
			vehicleCountHql = vehicleCountHql + "and vehicle_unit = '" + unit + "' ";
			listvehicleCountHql = listvehicleCountHql + "and vehicle_unit = '" + unit + "'";
		}
		
		/**
		 * 按所属队伍(team)分类查询
		 */
		if(vehicleinfoVO.getTeam() != null && vehicleinfoVO.getTeam().trim().length() > 0) {
			String team = vehicleinfoVO.getTeam().trim();
			vehicleCountHql = vehicleCountHql + "and vehicle_team = '" + team + "' ";
			listvehicleCountHql = listvehicleCountHql + "and vehicle_team = '" + team + "'";
		}
		
		
		/**
		 * 这里如果不加desc表示正序，如果加上desc表示倒序
		 */
		listvehicleCountHql = listvehicleCountHql + "order by vehicle_modifytime desc ";
		int vehicleCount = vehicleManagementDao.getCount(vehicleCountHql);
		/**
		 * 设置总数量
		 */
		vehicleinfoVO.setTotalRecords(vehicleCount);
		/**
		 * 设置总页数
		 */
		vehicleinfoVO.setTotalPages(((vehicleCount - 1) / vehicleinfoVO.getPageSize()) + 1);
		/**
		 * 判断是否拥有上一页
		 */
		if (vehicleinfoVO.getPageIndex() <= 1) {
			vehicleinfoVO.setHavePrePage(false);
		} else {
			vehicleinfoVO.setHavePrePage(true);
		}
		/**
		 * 判断是否拥有下一页
		 */
		if (vehicleinfoVO.getPageIndex() >= vehicleinfoVO.getTotalPages()) {
			vehicleinfoVO.setHaveNextPage(false);
		} else {
			vehicleinfoVO.setHaveNextPage(true);
		}

		/**
		 * 分页查询
		 */
		listvehicle = (List<vehicle>) vehicleManagementDao.queryForPage(listvehicleCountHql,
				vehicleinfoVO.getPageIndex(), vehicleinfoVO.getPageSize());
		
		for (vehicle vehicle : listvehicle) {
			if (vehicleinfoVO.getSearch() != null && vehicleinfoVO.getSearch().trim().length() > 0) {
				vehicle.setVehicle_num(vehicle.getVehicle_num().replaceAll(vehicleinfoVO.getSearch(),
						"<mark>" + vehicleinfoVO.getSearch() + "</mark>"));
				vehicle.setVehicle_platenum(vehicle.getVehicle_platenum().replaceAll(vehicleinfoVO.getSearch(),
						"<mark>" + vehicleinfoVO.getSearch() + "</mark>"));
			}
		}
		
		vehicleinfoVO.setListvehicle(listvehicle);

		return vehicleinfoVO;
	}

	/**
	 * 更新车辆信息
	 */

	@Override
	public int updateVehicle(vehicle vehicleinfo) {
		vehicle updateVehicleinfo = new vehicle();
		updateVehicleinfo = (vehicle) vehicleManagementDao.getVehicleInfoById(vehicleinfo.getVehicle_id());
		updateVehicleinfo.setVehicle_num(vehicleinfo.getVehicle_num());
		vehicleManagementDao.saveOrUpdateObject(updateVehicleinfo);
		System.out.println("SSSSSSSSSSS" + updateVehicleinfo);
		return 1;
	}

}
