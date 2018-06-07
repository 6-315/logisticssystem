package com.logistics.routemanagement.RouteManagerDTO;

import java.util.List;

import com.logistics.domain.route;
import com.logistics.domain.staff_basicinfo;
import com.logistics.domain.unit;

/**
 * 路线DTO
 * @author XTY
 *
 */
public class RouteManagerDTO {
	/**
	 * 路线表
	 */
	private route rout;
	/**
	 * 员工信息表
	 */
	private staff_basicinfo liststaff_basicinf;
	/**
	 * 始发中转站
	 */
	private unit unit1;
	/**
	 * 终止中转站
	 */
	private unit unit2;
	public route getRout() {
		return rout;
	}
	public void setRout(route rout) {
		this.rout = rout;
	}
	public staff_basicinfo getListstaff_basicinf() {
		return liststaff_basicinf;
	}
	public void setListstaff_basicinf(staff_basicinfo liststaff_basicinf) {
		this.liststaff_basicinf = liststaff_basicinf;
	}
	public unit getUnit1() {
		return unit1;
	}
	public void setUnit1(unit unit1) {
		this.unit1 = unit1;
	}
	public unit getUnit2() {
		return unit2;
	}
	public void setUnit2(unit unit2) {
		this.unit2 = unit2;
	}
	@Override
	public String toString() {
		return "RouteManagerDTO [rout=" + rout + ", liststaff_basicinf=" + liststaff_basicinf + ", unit1=" + unit1
				+ ", unit2=" + unit2 + "]";
	}

}
