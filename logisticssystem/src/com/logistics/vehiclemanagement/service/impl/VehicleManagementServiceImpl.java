package com.logistics.vehiclemanagement.service.impl;

import com.logistics.domain.vehicle;
import com.logistics.vehiclemanagement.dao.VehicleManagementDao;
import com.logistics.vehiclemanagement.service.VehicleManagementService;

import util.BuildUuid;
import util.TimeUtil;
/**
 * 车辆管理业务实现层
 * @author LW
 *
 */
public class VehicleManagementServiceImpl implements VehicleManagementService{
private VehicleManagementDao vehicleManagementDao;

public void setVehicleManagementDao(VehicleManagementDao vehicleManagementDao) {
	this.vehicleManagementDao = vehicleManagementDao;
}

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

}
