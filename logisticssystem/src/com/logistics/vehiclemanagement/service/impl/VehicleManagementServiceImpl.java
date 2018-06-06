package com.logistics.vehiclemanagement.service.impl;

import com.logistics.vehiclemanagement.dao.VehicleManagementDao;
/**
 * 车辆管理业务实现层
 * @author LW
 *
 */
public class VehicleManagementServiceImpl {
private VehicleManagementDao vehicleManagementDao;

public void setVehicleManagementDao(VehicleManagementDao vehicleManagementDao) {
	this.vehicleManagementDao = vehicleManagementDao;
}

}
