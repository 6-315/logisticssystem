package com.logistics.loginregister.DTO;

import com.logistics.domain.staff_basicinfo;

public class TeamStaffDTO {
	private staff_basicinfo teamStaff;

	public staff_basicinfo getTeamStaff() {
		return teamStaff;
	}

	public void setTeamStaff(staff_basicinfo teamStaff) {
		this.teamStaff = teamStaff;
	}

	@Override
	public String toString() {
		return "TeamStaffDTO [teamStaff=" + teamStaff + "]";
	}

}
