package com.logistics.loginregister.DTO;

import com.logistics.domain.staff_basicinfo;

public class DeliveryAdminStaffDTO {
	private staff_basicinfo deliveryAdminStaff;

	public staff_basicinfo getDeliveryAdminStaff() {
		return deliveryAdminStaff;
	}

	public void setDeliveryAdminStaff(staff_basicinfo deliveryAdminStaff) {
		this.deliveryAdminStaff = deliveryAdminStaff;
	}

	@Override
	public String toString() {
		return "DeliveryAdminStaffDTO [deliveryAdminStaff=" + deliveryAdminStaff + "]";
	}

}
