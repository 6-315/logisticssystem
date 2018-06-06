package com.logistics.domain;
/**
 * 这是驾驶员信息表
 * @author YX
 *
 */
public class driver {
	/**
	 * 驾驶员ID
	 */
	private String driver_id;
	/**
	 * 驾驶员基础信息ID
	 */
	private String driver_basicinfoid;
	/**
	 * 车辆
	 */
	private String driver_vehicle;
	/**
	 * 所属车队
	 */
	private String driver_belong_team;
	/**
	 * 创建时间
	 */
	private String driver_createtime;
	/**
	 * 修改时间
	 */
	private String driver_modifytime;
	/**
	 * 状态
	 */
	private String driver_state;
	
	public String getDriver_id() {
		return driver_id;
	}
	public void setDriver_id(String driver_id) {
		this.driver_id = driver_id;
	}
	public String getDriver_basicinfoid() {
		return driver_basicinfoid;
	}
	public void setDriver_basicinfoid(String driver_basicinfoid) {
		this.driver_basicinfoid = driver_basicinfoid;
	}
	public String getDriver_vehicle() {
		return driver_vehicle;
	}
	public void setDriver_vehicle(String driver_vehicle) {
		this.driver_vehicle = driver_vehicle;
	}
	public String getDriver_belong_team() {
		return driver_belong_team;
	}
	public void setDriver_belong_team(String driver_belong_team) {
		this.driver_belong_team = driver_belong_team;
	}
	public String getDriver_createtime() {
		return driver_createtime;
	}
	public void setDriver_createtime(String driver_createtime) {
		this.driver_createtime = driver_createtime;
	}
	public String getDriver_modifytime() {
		return driver_modifytime;
	}
	public void setDriver_modifytime(String driver_modifytime) {
		this.driver_modifytime = driver_modifytime;
	}
	public String getDriver_state() {
		return driver_state;
	}
	public void setDriver_state(String driver_state) {
		this.driver_state = driver_state;
	}
	@Override
	public String toString() {
		return "driver [driver_id=" + driver_id + ", driver_basicinfoid=" + driver_basicinfoid + ", driver_vehicle="
				+ driver_vehicle + ", driver_belong_team=" + driver_belong_team + ", driver_createtime="
				+ driver_createtime + ", driver_modifytime=" + driver_modifytime + ", driver_state=" + driver_state
				+ "]";
	}
	
}
