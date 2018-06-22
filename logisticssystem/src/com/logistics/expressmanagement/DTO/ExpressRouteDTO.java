package com.logistics.expressmanagement.DTO;

import java.util.List;

import com.logistics.domain.*;

public class ExpressRouteDTO {

	/**
	 * 路线DTO
	 */
	private List<RouteDTO> listRouteDTO;
	/**
	 * 当前单位
	 */
	private unit currentUnit;

	public List<RouteDTO> getListRouteDTO() {
		return listRouteDTO;
	}

	public void setListRouteDTO(List<RouteDTO> listRouteDTO) {
		this.listRouteDTO = listRouteDTO;
	}

	public unit getCurrentUnit() {
		return currentUnit;
	}

	public void setCurrentUnit(unit currentUnit) {
		this.currentUnit = currentUnit;
	}

	@Override
	public String toString() {
		return "ExpressRouteDTO [listRouteDTO=" + listRouteDTO + ", currentUnit=" + currentUnit + "]";
	}

}
