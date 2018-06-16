package com.logistics.loginregister.DTO;

import com.logistics.domain.staff_basicinfo;

public class DriverStaffDTO {
	private staff_basicinfo driverStaff;

	public staff_basicinfo getDriverStaff() {
		return driverStaff;
	}

	public void setDriverStaff(staff_basicinfo driverStaff) {
		this.driverStaff = driverStaff;
	}

	@Override
	public String toString() {
		return "DriverStaffDTO [driverStaff=" + driverStaff + "]";
	}

}
