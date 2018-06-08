package com.logistics.distribution.DTO;

import com.logistics.domain.staff_basicinfo;
import com.logistics.domain.unit;

/**
 * 这是单位列表的DTO
 * 
 * @author YX
 *
 */
public class UnitManagerDTO {

	private unit unitInfo;
	private staff_basicinfo unitCreator;
	private staff_basicinfo unitAdmin;

	public unit getUnitInfo() {
		return unitInfo;
	}

	public void setUnitInfo(unit unitInfo) {
		this.unitInfo = unitInfo;
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
		return "UnitManagerDTO [unitInfo=" + unitInfo + ", unitCreator=" + unitCreator + ", unitAdmin=" + unitAdmin
				+ "]";
	}

}
