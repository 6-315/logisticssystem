package com.logistics.loginregister.DTO;

import com.logistics.domain.staff_basicinfo;

public class SuperAdminStaffDTO {
	private staff_basicinfo superAdminStaff;

	public staff_basicinfo getSuperAdminStaff() {
		return superAdminStaff;
	}

	public void setSuperAdminStaff(staff_basicinfo superAdminStaff) {
		this.superAdminStaff = superAdminStaff;
	}

	@Override
	public String toString() {
		return "SuperAdminStaffDTO [superAdminStaff=" + superAdminStaff + "]";
	}

}
