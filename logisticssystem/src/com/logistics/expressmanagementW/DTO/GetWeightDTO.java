package com.logistics.expressmanagementW.DTO;

import com.logistics.domain.*;

/**
 * 快件和车辆的重量
 * 
 * @author LW
 *
 */
public class GetWeightDTO {
	/**
	 * 快件表
	 */
	private express expressNew;
	/**
	 * 车辆表
	 */
	private vehicle vehicleNew;

	public express getExpressNew() {
		return expressNew;
	}

	public void setExpressNew(express expressNew) {
		this.expressNew = expressNew;
	}

	public vehicle getVehicleNew() {
		return vehicleNew;
	}

	public void setVehicleNew(vehicle vehicleNew) {
		this.vehicleNew = vehicleNew;
	}

	@Override
	public String toString() {
		return "GetWeight [expressNew=" + expressNew + ", vehicleNew=" + vehicleNew + "]";
	}

}
