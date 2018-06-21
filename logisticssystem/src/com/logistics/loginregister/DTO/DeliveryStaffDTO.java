package com.logistics.loginregister.DTO;

import com.logistics.domain.staff_basicinfo;

public class DeliveryStaffDTO {
	private staff_basicinfo deliveryStaff;

	public staff_basicinfo getDeliveryStaff() {
		return deliveryStaff;
	}

	public void setDeliveryStaff(staff_basicinfo deliveryStaff) {
		this.deliveryStaff = deliveryStaff;
	}

	@Override
	public String toString() {
		return "DeliveryStaffDTO [deliveryStaff=" + deliveryStaff + "]";
	}

}
