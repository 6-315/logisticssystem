package com.logistics.vehiclemanagement.DTO;

import java.util.List;

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
	 * 员工表-队长信息
	 */
	private staff_basicinfo staff_BasicInfoLeader;
	/**
	 * 员工表-队员信息
	 */
	private List<staff_basicinfo> staff_TeamMember;
	/**
	 * 单位表
	 */
	private unit teamBelongUnit;

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

	public List<staff_basicinfo> getStaff_TeamMember() {
		return staff_TeamMember;
	}

	public void setStaff_TeamMember(List<staff_basicinfo> staff_TeamMember) {
		this.staff_TeamMember = staff_TeamMember;
	}

	public unit getTeamBelongUnit() {
		return teamBelongUnit;
	}

	public void setTeamBelongUnit(unit teamBelongUnit) {
		this.teamBelongUnit = teamBelongUnit;
	}

	@Override
	public String toString() {
		return "Vehicle_TeamDTO [team=" + team + ", staff_BasicInfoLeader=" + staff_BasicInfoLeader
				+ ", staff_TeamMember=" + staff_TeamMember + ", teamBelongUnit=" + teamBelongUnit + "]";
	}

}
