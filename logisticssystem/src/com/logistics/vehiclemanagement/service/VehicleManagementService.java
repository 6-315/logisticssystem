package com.logistics.vehiclemanagement.service;

import java.util.List;

import com.google.gson.JsonElement;
import com.logistics.domain.staff_basicinfo;
import com.logistics.domain.team;
import com.logistics.domain.vehicle;
import com.logistics.domain.vehiclecirculation;
import com.logistics.vehiclemanagement.DTO.ManagerDTO;
import com.logistics.vehiclemanagement.VO.TeamVO;
import com.logistics.vehiclemanagement.VO.VehicleVO;

/**
 * 车辆管理Service层接口
 * @author LW
 *
 */
public interface VehicleManagementService {

	/**
	 * 添加车辆
	 * @param vehicleinfo
	 * @return
	 */
	public String addVehicle(vehicle vehicleInfo);

	/**
	 * 查询车辆
	 * @param staffInfo 
	 * @return
	 */
	public VehicleVO queryVehicle(VehicleVO vehicleInfoVO, staff_basicinfo staffInfo);

	/**
	 * 更新车辆
	 * @param vehicleinfo
	 * @return
	 */
	public String updateVehicle(vehicle vehicleInfo);

	/**
	 * 根据id批量删除车辆
	 * @param ids
	 */
	public String deleteVehicle(String idList);

	/**
	 * 添加车队
	 * @param teamInfo
	 * @return
	 */
	public String addTeam(team teamInfo);

	/**
	 * 更新车队信息
	 * @param teamInfo
	 * @return
	 */
	public String updateTeam(team teamInfo);

	/**
	 * 批量删除车队
	 * @param idList
	 */
	public String deleteTeam(String idList);

	/**
	 * 查询车队信息
	 * @param teamInfoVO
	 * @param staffInfo 
	 * @return
	 */
	public TeamVO queryTeam(TeamVO teamInfoVO, staff_basicinfo staffInfo);

	public String exchangeVehicle(vehiclecirculation vehicleCirculation);

	public List<ManagerDTO> getAllManager(String position);

}
