package com.logistics.loginregister.DTO;

import com.logistics.domain.staff_basicinfo;

public class TransAdminStaffDTO {
	private staff_basicinfo transAdminStaff;

	public staff_basicinfo getTransAdminStaff() {
		return transAdminStaff;
	}

	public void setTransAdminStaff(staff_basicinfo transAdminStaff) {
		this.transAdminStaff = transAdminStaff;
	}

	@Override
	public String toString() {
		return "TransAdminStaffDTO [transAdminStaff=" + transAdminStaff + "]";
	}
	
}
