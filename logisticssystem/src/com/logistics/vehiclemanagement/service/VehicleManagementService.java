package com.logistics.vehiclemanagement.service;

import com.logistics.domain.team;
import com.logistics.domain.vehicle;
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
	public int addVehicle(vehicle vehicleInfo);

	/**
	 * 查询车辆
	 * @return
	 */
	public VehicleVO queryVehicle(VehicleVO vehicleInfoVO);

	/**
	 * 更新车辆
	 * @param vehicleinfo
	 * @return
	 */
	public int updateVehicle(vehicle vehicleInfo);

	/**
	 * 根据id批量删除车辆
	 * @param ids
	 */
	public String deleteVehicle(VehicleVO vehicleInfoVO);

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
	 * @param teamInfoVO
	 */
	public String deleteTeam(TeamVO teamInfoVO);

	/**
	 * 查询车队信息
	 * @param teamInfoVO
	 * @return
	 */
	public TeamVO queryTeam(TeamVO teamInfoVO);

	

	
	

}
