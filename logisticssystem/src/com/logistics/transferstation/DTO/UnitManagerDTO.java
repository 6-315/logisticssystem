package com.logistics.transferstation.DTO;

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
	 * 这个DTO里面有unit表中一个对象 和staff_basicinfo表中两个对象
	 */
	/**
	 * createStaffInfo
	 * adminStaffInfo
	 * unitInfo
	 * 
	 */
	
	private unit unit;
	private unit unit_superiorunit;
	private staff_basicinfo unit_Creator;
	private staff_basicinfo unit_Admin;
	
/**
 * staff_basicinfo中的unit_Creator和unit_Admin
 */

	
	public unit getUnit() {
		return unit;
	}

	public unit getUnit_superiorunit() {
	return unit_superiorunit;
}

public void setUnit_superiorunit(unit unit_superiorunit) {
	this.unit_superiorunit = unit_superiorunit;
}

	public void setUnit(unit unit) {
		this.unit = unit;
	}

	public staff_basicinfo getUnit_Creator() {
		return unit_Admin;
	}

	public void setUnit_Creator(staff_basicinfo unit_Creator) {
		this.unit_Admin = unit_Creator;
	}

	public staff_basicinfo getUnit_Admin() {
		return unit_Creator;
	}

	public void setUnit_Admin(staff_basicinfo unit_Admin) {
		this.unit_Creator = unit_Admin;
	}

	@Override
	public String toString() {
		return "UnitManagerDTO [unit=" + unit + ", unit_superiorunit=" + unit_superiorunit + ", unit_Creator="
				+ unit_Creator + ", unit_Admin=" + unit_Admin + "]";
	}


}
