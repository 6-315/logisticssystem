package com.logistics.transferstation.DTO;

import java.util.List;

import com.logistics.domain.staff_basicinfo;
import com.logistics.domain.unit;

/**
 * 中转站DTO
 * 
 * @author LL
 *
 */
public class UnitManagerDTO {
	/**
	 * 这个DTO里面有
	 */
	private unit unit;
	private staff_basicinfo unit_creator;
	private staff_basicinfo unit_admin;

	public unit getUnit() {
		return unit;
	}

	public void setUnit(unit unit) {
		this.unit = unit;
	}

	public staff_basicinfo getUnit_creator() {
		return unit_creator;
	}

	public void setUnit_creator(staff_basicinfo unit_creator) {
		this.unit_creator = unit_creator;
	}

	public staff_basicinfo getUnit_admin() {
		return unit_admin;
	}

	public void setUnit_admin(staff_basicinfo unit_admin) {
		this.unit_admin = unit_admin;
	}

	@Override
	public String toString() {
		return "UnitManagerDTO [unit=" + unit + ", unit_creator=" + unit_creator + ", unit_admin=" + unit_admin + "]";
	}

}
