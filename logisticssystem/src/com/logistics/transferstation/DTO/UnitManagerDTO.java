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
	 * 这个DTO里面有unit 和staff_basicinfo两个表
	 */
	private unit unit;
	private staff_basicinfo staff_basicinfo;
	public staff_basicinfo getStaff_basicinfo() {
		return staff_basicinfo;
	}

	public void setStaff_basicinfo(staff_basicinfo staff_basicinfo) {
		this.staff_basicinfo = staff_basicinfo;
	}
/**
 * staff_basicinfo中的unit_Creator和unit_Admin
 */
	private staff_basicinfo unit_Creator;
	private staff_basicinfo unit_Admin;

	public unit getUnit() {
		return unit;
	}

	public void setUnit(unit unit) {
		this.unit = unit;
	}

	public staff_basicinfo getUnit_Creator() {
		return unit_Creator;
	}

	public void setUnit_Creator(staff_basicinfo unit_Creator) {
		this.unit_Creator = unit_Creator;
	}

	public staff_basicinfo getUnit_Admin() {
		return unit_Admin;
	}

	public void setUnit_Admin(staff_basicinfo unit_Admin) {
		this.unit_Admin = unit_Admin;
	}

	@Override
	public String toString() {
		return "UnitManagerDTO [unit=" + unit + ", unit_Creator=" + unit_Creator + ", unit_Admin=" + unit_Admin + "]";
	}

}
