package com.logistics.loginregister.DTO;
/**
 * 员工信息DTO 
 * @author JXX
 *
 */

import com.logistics.domain.position;
import com.logistics.domain.staff_basicinfo;

public class StaffInfomationDTO {
	/**
	 * 基础信息
	 */
	private staff_basicinfo staffBasicInfo;
	private position staffPosition;

	public staff_basicinfo getStaffBasicInfo() {
		return staffBasicInfo;
	}

	public void setStaffBasicInfo(staff_basicinfo staffBasicInfo) {
		this.staffBasicInfo = staffBasicInfo;
	}

	public position getStaffPosition() {
		return staffPosition;
	}

	public void setStaffPosition(position staffPosition) {
		this.staffPosition = staffPosition;
	}

	@Override
	public String toString() {
		return "StaffInfomationDTO [staffBasicInfo=" + staffBasicInfo + ", staffPosition=" + staffPosition + "]";
	}

}
