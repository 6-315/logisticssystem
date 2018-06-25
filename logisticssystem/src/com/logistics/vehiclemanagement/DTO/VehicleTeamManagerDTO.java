package com.logistics.vehiclemanagement.DTO;

import java.util.List;

import com.logistics.domain.*;
import com.logistics.expressmanagement.DTO.RouteDTO;

/**
 * 车辆信息中的车队信息
 * 
 * @author LMJ
 *
 */
public class VehicleTeamManagerDTO {
	/**
	 * 车队表
	 */
	private team team;
	/**
	 * 员工表-队长信息
	 */
	private staff_basicinfo staff_BasicInfoLeader;
	/**
	 * 员工表-队员信息
	 */
	private List<DriverDTO> listDriverInfoDTO;
	/**
	 * 单位表
	 */
	private unit teamBelongUnit;
	/**
	 * 路线
	 */
	private RouteDTO routeDTO;

	public team getTeam() {
		return team;
	}

	public void setTeam(team team) {
		this.team = team;
	}

	public staff_basicinfo getStaff_BasicInfoLeader() {
		return staff_BasicInfoLeader;
	}

	public void setStaff_BasicInfoLeader(staff_basicinfo staff_BasicInfoLeader) {
		this.staff_BasicInfoLeader = staff_BasicInfoLeader;
	}

	public List<DriverDTO> getListDriverInfoDTO() {
		return listDriverInfoDTO;
	}

	public void setListDriverInfoDTO(List<DriverDTO> listDriverInfoDTO) {
		this.listDriverInfoDTO = listDriverInfoDTO;
	}

	public unit getTeamBelongUnit() {
		return teamBelongUnit;
	}

	public void setTeamBelongUnit(unit teamBelongUnit) {
		this.teamBelongUnit = teamBelongUnit;
	}

	public RouteDTO getRouteDTO() {
		return routeDTO;
	}

	public void setRouteDTO(RouteDTO routeDTO) {
		this.routeDTO = routeDTO;
	}

	@Override
	public String toString() {
		return "VehicleTeamManagerDTO [team=" + team + ", staff_BasicInfoLeader=" + staff_BasicInfoLeader
				+ ", listDriverInfoDTO=" + listDriverInfoDTO + ", teamBelongUnit=" + teamBelongUnit + ", routeDTO="
				+ routeDTO + "]";
	}

}
