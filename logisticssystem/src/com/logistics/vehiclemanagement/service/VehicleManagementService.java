package com.logistics.vehiclemanagement.service;

import com.logistics.domain.vehicle;
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
	public void deleteVehicle(VehicleVO vehicleInfoVO);

	

	
	

}
