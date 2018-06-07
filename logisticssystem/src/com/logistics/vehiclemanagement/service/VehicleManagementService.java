package com.logistics.vehiclemanagement.service;

import com.logistics.domain.vehicle;
import com.logistics.vehiclemanagement.VO.vehicleVO;

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
	public int addVehicle(vehicle vehicleinfo);

	/**
	 * 查询车辆
	 * @return
	 */
	public vehicleVO queryVehicle(vehicleVO vehicleinfoVO);

	/**
	 * 更新车辆
	 * @param vehicleinfo
	 * @return
	 */
	public int updateVehicle(vehicle vehicleinfo);

	
	

}
