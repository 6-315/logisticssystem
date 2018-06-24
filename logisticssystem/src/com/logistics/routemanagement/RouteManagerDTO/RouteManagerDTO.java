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
	/**。
	 * 员工信息表
	 */
	private staff_basicinfo staff_Id;
	/**
	 * 始发中转站
	 */
	private unit route_Departurestation;
	/**
	 * 终止中转站
	 */
	private unit route_Terminalstation;
	public route getRout() {
		return rout;
	}
	public void setRout(route rout) {
		this.rout = rout;
	}
	public staff_basicinfo getStaff_Id() {
		return staff_Id;
	}
	public void setStaff_Id(staff_basicinfo staff_Id) {
		this.staff_Id = staff_Id;
	}
	public unit getRoute_Departurestation() {
		return route_Departurestation;
	}
	public void setRoute_Departurestation(unit route_Departurestation) {
		this.route_Departurestation = route_Departurestation;
	}
	public unit getRoute_Terminalstation() {
		return route_Terminalstation;
	}
	public void setRoute_Terminalstation(unit route_Terminalstation) {
		this.route_Terminalstation = route_Terminalstation;
	}
	@Override
	public String toString() {
		return "RouteManagerDTO [rout=" + rout + ", staff_Id=" + staff_Id + ", route_Departurestation="
				+ route_Departurestation + ", route_Terminalstation=" + route_Terminalstation + "]";
	}
	
}
