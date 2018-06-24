package com.logistics.personnelmanagement.DTO;

import java.util.List;

import com.logistics.domain.*;

/**
 * 员工基础信息和单位
 * 
 * @author LW
 *
 */
public class StaffManagerDTO {
	private unit unit;
	private staff_basicinfo staffBasicInfo;
	private position position;

	public unit getUnit() {
		return unit;
	}

	public void setUnit(unit unit) {
		this.unit = unit;
	}

	public staff_basicinfo getStaffBasicInfo() {
		return staffBasicInfo;
	}

	public void setStaffBasicInfo(staff_basicinfo staffBasicInfo) {
		this.staffBasicInfo = staffBasicInfo;
	}

	public position getPosition() {
		return position;
	}

	public void setPosition(position position) {
		this.position = position;
	}

	@Override
	public String toString() {
		return "StaffManagerDTO [unit=" + unit + ", staffBasicInfo=" + staffBasicInfo + ", position=" + position + "]";
	}

}
