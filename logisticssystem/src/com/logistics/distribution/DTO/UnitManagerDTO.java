package com.logistics.distribution.DTO;

import com.logistics.domain.staff_basicinfo;
import com.logistics.domain.unit;

/**
 * 这是单位列表的DTO
 * @author YX
 *
 */
public class UnitManagerDTO {
	
	private unit unit1;
	private staff_basicinfo unitCreator ;
	private staff_basicinfo unitAdmin;
	public unit getUnit1() {
		return unit1;
	}
	public void setUnit1(unit unit1) {
		this.unit1 = unit1;
	}
	public staff_basicinfo getUnitCreator() {
		return unitCreator;
	}
	public void setUnitCreator(staff_basicinfo unitCreator) {
		this.unitCreator = unitCreator;
	}
	public staff_basicinfo getUnitAdmin() {
		return unitAdmin;
	}
	public void setUnitAdmin(staff_basicinfo unitAdmin) {
		this.unitAdmin = unitAdmin;
	}
	@Override
	public String toString() {
		return "UnitManagerDTO [unit1=" + unit1 + ", unitCreator=" + unitCreator + ", unitAdmin=" + unitAdmin + "]";
	}
	
	
}
