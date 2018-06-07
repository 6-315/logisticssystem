package com.logistics.personnelmanagement.DTO;
import java.util.List;

import com.logistics.domain.*;
/**
 * 员工基础信息和单位
 * @author LW
 *
 */
public class StaffManagerDTO {
	private unit unit;
	private List<staff_basicinfo> listStaff;
	public unit getUnit() {
		return unit;
	}
	public void setUnit(unit unit) {
		this.unit = unit;
	}
	public List<staff_basicinfo> getListStaff() {
		return listStaff;
	}
	public void setListStaff(List<staff_basicinfo> listStaff) {
		this.listStaff = listStaff;
	}
	@Override
	public String toString() {
		return "StaffManagerDTO [unit=" + unit + ", listStaff=" + listStaff + "]";
	}
}
