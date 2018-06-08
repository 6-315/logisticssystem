package com.logistics.vehiclemanagement.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.logistics.domain.staff_basicinfo;
import com.logistics.domain.team;
import com.logistics.domain.unit;
import com.logistics.domain.vehicle;
import com.logistics.vehiclemanagement.DTO.VehicleDTO;
import com.logistics.vehiclemanagement.DTO.Vehicle_TeamDTO;
import com.logistics.vehiclemanagement.VO.VehicleVO;
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
	 * 添加车辆（车辆ID，车辆编号，车辆车牌号，车辆状态，车辆所属单位，车辆购置时间，车辆购置人，车辆所属车队，车辆备注，创建时间，修改时间）
	 * 
	 * @return 1 代表添加成功
	 */
	@Override
	public int addVehicle(vehicle vehicleInfo) {
		/**
		 * 添加信息
		 */
		vehicleInfo.setVehicle_id(BuildUuid.getUuid());
		vehicleInfo.setVehicle_acquisitiontime(TimeUtil.getStringSecond());
		vehicleInfo.setVehicle_createtime(TimeUtil.getStringSecond());
		vehicleInfo.setVehicle_modifytime(TimeUtil.getStringSecond());
		vehicleInfo.setVehicle_mark("None");
		/**
		 * 完成添加功能
		 */
		vehicleManagementDao.saveOrUpdateObject(vehicleInfo);
		return 1;
	}

	/**
	 * 分页查询车辆(车辆ID，车辆编号，车辆车牌号，车辆状态，车辆所属单位，车辆购置时间，车辆购置人，车辆所属车队，车辆备注，创建时间，修改时间)
	 * 
	 * @return 返回vehicleVO数据对象
	 */
	@SuppressWarnings({ "unchecked" })
	@Override
	public VehicleVO queryVehicle(VehicleVO vehicleInfoVO) {
		/**
		 * 车辆信息DTO
		 */
		List<VehicleDTO> listVehicleDTO = new ArrayList<>();
		/**
		 * 车队信息DTO
		 */
		Vehicle_TeamDTO vehicleBelongTeamDTO = null;
		/**
		 * 车队信息
		 */
		team vehicleBelongTeam = null;
		/**
		 * 车辆信息
		 */
		List<vehicle> listVehicleDTOInfo = new ArrayList<>();
		/**
		 * 车辆DTO
		 */
		VehicleDTO vehicleDTO;
		/**
		 * 车队队长
		 */
		staff_basicinfo teamLeaderInfo = null;
		/**
		 * 获取数量
		 */
		String vehicleCountHql = "select count(*) from vehicle ";
		/**
		 * 链接数量的hql以及遍历的hql
		 */
		String listVehicleDTOCountHql = "from vehicle ";

		/**
		 * 根据关键词进行模糊查询
		 */
		if (vehicleInfoVO.getSearch() != null && vehicleInfoVO.getSearch().trim().length() > 0) {
			String search = "%" + vehicleInfoVO.getSearch().trim() + "%";
			vehicleCountHql = vehicleCountHql + "where vehicle_num like '" + search + "' ";
			listVehicleDTOCountHql = listVehicleDTOCountHql + "where vehicle_num like '" + search + "'";
			vehicleCountHql = vehicleCountHql + "and vehicle_platenum like '" + search + "' ";
			listVehicleDTOCountHql = listVehicleDTOCountHql + "and vehicle_platenum like '" + search + "'";
		}

		/**
		 * 按状态(state)分类查询
		 */
		if (vehicleInfoVO.getState() != null && vehicleInfoVO.getState().trim().length() > 0) {
			String state = vehicleInfoVO.getState().trim();
			vehicleCountHql = vehicleCountHql + "and vehicle_state = '" + state + "' ";
			listVehicleDTOCountHql = listVehicleDTOCountHql + "and vehicle_state = '" + state + "'";
		}

		/**
		 * 按所属单位(unit)分类查询
		 */
		if (vehicleInfoVO.getUnit() != null && vehicleInfoVO.getUnit().trim().length() > 0) {
			String unit = vehicleInfoVO.getUnit().trim();
			vehicleCountHql = vehicleCountHql + "and vehicle_unit = '" + unit + "' ";
			listVehicleDTOCountHql = listVehicleDTOCountHql + "and vehicle_unit = '" + unit + "'";
		}

		/**
		 * 按所属队伍(team)分类查询
		 */
		if (vehicleInfoVO.getTeam() != null && vehicleInfoVO.getTeam().trim().length() > 0) {
			String team = vehicleInfoVO.getTeam().trim();
			vehicleCountHql = vehicleCountHql + "and vehicle_team = '" + team + "' ";
			listVehicleDTOCountHql = listVehicleDTOCountHql + "and vehicle_team = '" + team + "'";
		}

		/**
		 * 这里如果不加desc表示正序，如果加上desc表示倒序
		 */
		listVehicleDTOCountHql = listVehicleDTOCountHql + "order by vehicle_modifytime desc ";
		int vehicleCount = vehicleManagementDao.getCount(vehicleCountHql);
		/**
		 * 设置总数量
		 */
		vehicleInfoVO.setTotalRecords(vehicleCount);
		/**
		 * 设置总页数
		 */
		vehicleInfoVO.setTotalPages(((vehicleCount - 1) / vehicleInfoVO.getPageSize()) + 1);
		/**
		 * 判断是否拥有上一页
		 */
		if (vehicleInfoVO.getPageIndex() <= 1) {
			vehicleInfoVO.setHavePrePage(false);
		} else {
			vehicleInfoVO.setHavePrePage(true);
		}
		/**
		 * 判断是否拥有下一页
		 */
		if (vehicleInfoVO.getPageIndex() >= vehicleInfoVO.getTotalPages()) {
			vehicleInfoVO.setHaveNextPage(false);
		} else {
			vehicleInfoVO.setHaveNextPage(true);
		}

		/**
		 * 分页查询
		 */
		listVehicleDTOInfo = (List<vehicle>) vehicleManagementDao.queryForPage(listVehicleDTOCountHql,
				vehicleInfoVO.getPageIndex(), vehicleInfoVO.getPageSize());
		for (vehicle vehicle : listVehicleDTOInfo) {
			/**
			 * 车队队长
			 */
			teamLeaderInfo = new staff_basicinfo();
			/**
			 * 车队
			 */
			vehicleBelongTeam = new team();
			/**
			 * 车队DTO
			 */
			vehicleBelongTeamDTO = new Vehicle_TeamDTO();
			/**
			 * 车辆DTO
			 */
			vehicleDTO = new VehicleDTO();
			/**
			 * 查询车辆购置人
			 */
			staff_basicinfo staff_BasicInfo = vehicleManagementDao
					.getStaffInfoById(vehicle.getVehicle_acquisitionpeople());
			if (staff_BasicInfo != null) {
				/**
				 * 将查询到的信息放入车辆DTO
				 */
				vehicleDTO.setStaff_BasicInfoAcquisition(staff_BasicInfo);
			}
			/**
			 * 查询车辆所属单位
			 */
			unit unitInfo = vehicleManagementDao.getUnitInfoById(vehicle.getVehicle_unit());
			if (unitInfo != null) {
				/**
				 * 将查询到的信息放入车辆DTO
				 */
				vehicleDTO.setUnit(unitInfo);
			}
			/**
			 * 查询车辆所属车队
			 */
			vehicleBelongTeam = vehicleManagementDao.getTeamInfoById(vehicle.getVehicle_team());
			/**
			 * 根据队长id获取队长信息
			 */
			if (vehicleBelongTeam != null) {
				if (vehicleBelongTeam.getTeam_leader() != null
						&& vehicleBelongTeam.getTeam_leader().trim().length() > 0) {
					teamLeaderInfo = vehicleManagementDao.getStaffInfoById(vehicleBelongTeam.getTeam_leader().trim());
				}
				/**
				 * 将查询到的信息放入车队DTO
				 */
				vehicleBelongTeamDTO.setStaff_BasicInfoLeader(teamLeaderInfo);
				vehicleBelongTeamDTO.setTeam(vehicleBelongTeam);
			}
			
			if (vehicleInfoVO.getSearch() != null && vehicleInfoVO.getSearch().trim().length() > 0) {
				vehicle.setVehicle_num(vehicle.getVehicle_num().replaceAll(vehicleInfoVO.getSearch(),
						"<mark>" + vehicleInfoVO.getSearch() + "</mark>"));
				vehicle.setVehicle_platenum(vehicle.getVehicle_platenum().replaceAll(vehicleInfoVO.getSearch(),
						"<mark>" + vehicleInfoVO.getSearch() + "</mark>"));
			}
			/**
			 * 将查询到的信息放入车辆DTO中
			 */
			vehicleDTO.setVehicle_TeamDTO(vehicleBelongTeamDTO);
			vehicleDTO.setVehicleInfo(vehicle);
			listVehicleDTO.add(vehicleDTO);

		}
		/**
		 * 将DTO放入VO中
		 */
		vehicleInfoVO.setListVehicleDTO(listVehicleDTO);

		return vehicleInfoVO;
	}

	/**
	 * 更新车辆信息（车辆编号，车辆车牌号，车辆状态，车辆所属单位，车辆所属车队，车辆备注，修改时间）
	 * 
	 * @return 1 代表更新成功
	 */

	@Override
	public int updateVehicle(vehicle vehicleInfo) {
		/**
		 * 更新车辆
		 */
		vehicle updateVehicleInfo = new vehicle();
		/**
		 * 根据ID查询车辆信息
		 */
		updateVehicleInfo = (vehicle) vehicleManagementDao.getVehicleInfoById(vehicleInfo.getVehicle_id());
		/**
		 * 更新所需要更新的属性
		 */
		updateVehicleInfo.setVehicle_num(vehicleInfo.getVehicle_num());
		/**
		 * 保存更新
		 */
		vehicleManagementDao.saveOrUpdateObject(updateVehicleInfo);
		return 1;
	}

	/**
	 * 批量删除车辆
	 */
	@Override
	public void deleteVehicle(VehicleVO vehicleInfoVO) {
		/**
		 * 将获得的idList转换成数组
		 */
		String[] deleteVehicleById = vehicleInfoVO.getIdList().split(",");
		/**
		 * 遍历数组
		 */
		for (String id : deleteVehicleById) {
			/**
			 * 根据获得的ID查询信息，再删除
			 */
			vehicleManagementDao.removeObject(vehicleManagementDao.getVehicleInfoById(id));
		}
	}

}
