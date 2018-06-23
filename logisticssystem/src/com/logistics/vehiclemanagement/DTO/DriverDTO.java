package com.logistics.vehiclemanagement.DTO;

import com.logistics.domain.*;

public class DriverDTO {
	/**
	 * 驾驶员表
	 */
	private driver driverInfo;
	/**
	 * 员工信息表
	 */
	private staff_basicinfo staffBasicInfo;

	public driver getDriverInfo() {
		return driverInfo;
	}

	public void setDriverInfo(driver driverInfo) {
		this.driverInfo = driverInfo;
	}

	public staff_basicinfo getStaffBasicInfo() {
		return staffBasicInfo;
	}

	public void setStaffBasicInfo(staff_basicinfo staffBasicInfo) {
		this.staffBasicInfo = staffBasicInfo;
	}

	@Override
	public String toString() {
		return "DriverDTO [driverInfo=" + driverInfo + ", staffBasicInfo=" + staffBasicInfo + "]";
	}

}
