package com.logistics.expressmanagement.DTO;

import com.logistics.domain.*;

public class RouteDTO {
	/**
	 * 路线
	 */
	private route routeInfo;
	/**
	 * 起点站
	 */
	private unit beginUnit;
	/**
	 * 终点站
	 */
	private unit endUnit;
	/**
	 * 方向
	 */
	private String direction;

	public route getRouteInfo() {
		return routeInfo;
	}

	public void setRouteInfo(route routeInfo) {
		this.routeInfo = routeInfo;
	}

	public unit getBeginUnit() {
		return beginUnit;
	}

	public void setBeginUnit(unit beginUnit) {
		this.beginUnit = beginUnit;
	}

	public unit getEndUnit() {
		return endUnit;
	}

	public void setEndUnit(unit endUnit) {
		this.endUnit = endUnit;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	@Override
	public String toString() {
		return "RouteDTO [routeInfo=" + routeInfo + ", beginUnit=" + beginUnit + ", endUnit=" + endUnit + ", direction="
				+ direction + "]";
	}

}
