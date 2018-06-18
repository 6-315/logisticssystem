package com.logistics.vehiclemanagement.DTO;


import com.logistics.domain.*;

/**
 * 车辆信息DTO
 * 
 * @author LMJ
 *
 */
public class VehicleDTO {
	/**
	 * 车辆信息表
	 */
	private vehicle vehicleInfo;
	/**
	 * 员工信息表
	 */
	private staff_basicinfo staff_BasicInfoAcquisition;
	/**
	 * 单位信息表
	 */
	private unit unit;
	/**
	 * 车队信息
	 */
	private Vehicle_TeamDTO vehicle_TeamDTO;

	public vehicle getVehicleInfo() {
		return vehicleInfo;
	}

	public void setVehicleInfo(vehicle vehicleInfo) {
		this.vehicleInfo = vehicleInfo;
	}

	public staff_basicinfo getStaff_BasicInfoAcquisition() {
		return staff_BasicInfoAcquisition;
	}

	public void setStaff_BasicInfoAcquisition(staff_basicinfo staff_BasicInfoAcquisition) {
		this.staff_BasicInfoAcquisition = staff_BasicInfoAcquisition;
	}

	public unit getUnit() {
		return unit;
	}

	public void setUnit(unit unit) {
		this.unit = unit;
	}

	public Vehicle_TeamDTO getVehicle_TeamDTO() {
		return vehicle_TeamDTO;
	}

	public void setVehicle_TeamDTO(Vehicle_TeamDTO vehicle_TeamDTO) {
		this.vehicle_TeamDTO = vehicle_TeamDTO;
	}

	@Override
	public String toString() {
		return "VehicleDTO [vehicleInfo=" + vehicleInfo + ", staff_BasicInfoAcquisition=" + staff_BasicInfoAcquisition
				+ ", unit=" + unit + ", vehicle_TeamDTO=" + vehicle_TeamDTO + "]";
	}

}
