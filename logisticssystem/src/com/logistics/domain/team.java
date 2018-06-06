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
	private String tean_num;
	/**
	 * 车队队长
	 */
	private String team_leader;
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
	public String getTean_num() {
		return tean_num;
	}
	public void setTean_num(String tean_num) {
		this.tean_num = tean_num;
	}
	public String getTeam_leader() {
		return team_leader;
	}
	public void setTeam_leader(String team_leader) {
		this.team_leader = team_leader;
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
		return "team [team_id=" + team_id + ", tean_num=" + tean_num + ", team_leader=" + team_leader
				+ ", team_createtime=" + team_createtime + ", team_modifytime=" + team_modifytime + ", team_state="
				+ team_state + "]";
	}

	
}
