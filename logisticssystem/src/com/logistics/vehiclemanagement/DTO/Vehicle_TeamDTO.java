package com.logistics.vehiclemanagement.DTO;

import com.logistics.domain.*;

/**
 * 车辆信息中的车队信息
 * 
 * @author LMJ
 *
 */
public class Vehicle_TeamDTO {
	/**
	 * 车队表
	 */
	private team team;
	/**
	 * 员工表
	 */
	private staff_basicinfo staff_BasicInfoLeader;

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

	@Override
	public String toString() {
		return "Vehicle_TeamDTO [team=" + team + ", staff_BasicInfoLeader=" + staff_BasicInfoLeader + "]";
	}

}
