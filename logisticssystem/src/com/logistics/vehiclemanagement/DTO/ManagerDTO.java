package com.logistics.vehiclemanagement.DTO;

import com.logistics.domain.staff_basicinfo;

public class ManagerDTO {
	private staff_basicinfo managerInfo;

	public staff_basicinfo getManagerInfo() {
		return managerInfo;
	}

	public void setManagerInfo(staff_basicinfo managerInfo) {
		this.managerInfo = managerInfo;
	}

	@Override
	public String toString() {
		return "ManagerDTO [managerInfo=" + managerInfo + "]";
	}

}
