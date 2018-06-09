package com.logistics.domain;

/**
 * 车队表
 * 
 * @author LMJ
 *
 */
public class team {
	/**
	 * 车队ID
	 */
	private String team_id;
	/**
	 * 车队编号
	 */
	private String team_num;
	/**
	 * 车队队长
	 */
	private String team_leader;
	/**
	 * 车队所属单位
	 */
	private String team_unit;
	/**
	 * 车队创建时间
	 */
	private String team_createtime;
	/**
	 * 车队修改时间
	 */
	private String team_modifytime;
	/**
	 * 车队状态
	 */
	private String team_state;

	public String getTeam_id() {
		return team_id;
	}

	public void setTeam_id(String team_id) {
		this.team_id = team_id;
	}

	public String getTeam_num() {
		return team_num;
	}

	public void setTeam_num(String team_num) {
		this.team_num = team_num;
	}

	public String getTeam_leader() {
		return team_leader;
	}

	public void setTeam_leader(String team_leader) {
		this.team_leader = team_leader;
	}

	public String getTeam_unit() {
		return team_unit;
	}

	public void setTeam_unit(String team_unit) {
		this.team_unit = team_unit;
	}

	public String getTeam_createtime() {
		return team_createtime;
	}

	public void setTeam_createtime(String team_createtime) {
		this.team_createtime = team_createtime;
	}

	public String getTeam_modifytime() {
		return team_modifytime;
	}

	public void setTeam_modifytime(String team_modifytime) {
		this.team_modifytime = team_modifytime;
	}

	public String getTeam_state() {
		return team_state;
	}

	public void setTeam_state(String team_state) {
		this.team_state = team_state;
	}

	@Override
	public String toString() {
		return "team [team_id=" + team_id + ", team_num=" + team_num + ", team_leader=" + team_leader + ", team_unit="
				+ team_unit + ", team_createtime=" + team_createtime + ", team_modifytime=" + team_modifytime
				+ ", team_state=" + team_state + "]";
	}

}
