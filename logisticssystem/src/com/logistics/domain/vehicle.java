package com.logistics.domain;

/**
 * 车辆信息
 * 
 * @author LMJ
 *
 */
public class vehicle {
	/**
	 * 车辆ID
	 */
	private String vehicle_id;
	/**
	 * 车辆编号
	 */
	private String vehicle_num;
	/**
	 * 车辆车牌号
	 */
	private String vehicle_platenum;
	/**
	 * 车辆状态
	 */
	private String vehicle_state;
	/**
	 * 车辆分配状态
	 */
	private String vehicle_distribution_state;
	/**
	 * 车辆装载状态
	 */
	private String vehicle_express_state;
	/**
	 * 车辆行驶方向
	 */
	private String vehicle_drivingdirection;
	/**
	 * 车辆所属单位
	 */
	private String vehicle_unit;
	/**
	 * 车辆购置时间
	 */
	private String vehicle_acquisitiontime;
	/**
	 * 车辆购置人
	 */
	private String vehicle_acquisitionpeople;
	/**
	 * 车辆所属车队
	 */
	private String vehicle_team;
	/**
	 * 备注
	 */
	private String vehicle_mark;
	/**
	 * 创建时间
	 */
	private String vehicle_createtime;
	/**
	 * 修改时间
	 */
	private String vehicle_modifytime;

	public String getVehicle_id() {
		return vehicle_id;
	}

	public void setVehicle_id(String vehicle_id) {
		this.vehicle_id = vehicle_id;
	}

	public String getVehicle_num() {
		return vehicle_num;
	}

	public void setVehicle_num(String vehicle_num) {
		this.vehicle_num = vehicle_num;
	}

	public String getVehicle_platenum() {
		return vehicle_platenum;
	}

	public void setVehicle_platenum(String vehicle_platenum) {
		this.vehicle_platenum = vehicle_platenum;
	}

	public String getVehicle_state() {
		return vehicle_state;
	}

	public void setVehicle_state(String vehicle_state) {
		this.vehicle_state = vehicle_state;
	}

	public String getVehicle_distribution_state() {
		return vehicle_distribution_state;
	}

	public void setVehicle_distribution_state(String vehicle_distribution_state) {
		this.vehicle_distribution_state = vehicle_distribution_state;
	}

	public String getVehicle_express_state() {
		return vehicle_express_state;
	}

	public void setVehicle_express_state(String vehicle_express_state) {
		this.vehicle_express_state = vehicle_express_state;
	}

	public String getVehicle_drivingdirection() {
		return vehicle_drivingdirection;
	}

	public void setVehicle_drivingdirection(String vehicle_drivingdirection) {
		this.vehicle_drivingdirection = vehicle_drivingdirection;
	}

	public String getVehicle_unit() {
		return vehicle_unit;
	}

	public void setVehicle_unit(String vehicle_unit) {
		this.vehicle_unit = vehicle_unit;
	}

	public String getVehicle_acquisitiontime() {
		return vehicle_acquisitiontime;
	}

	public void setVehicle_acquisitiontime(String vehicle_acquisitiontime) {
		this.vehicle_acquisitiontime = vehicle_acquisitiontime;
	}

	public String getVehicle_acquisitionpeople() {
		return vehicle_acquisitionpeople;
	}

	public void setVehicle_acquisitionpeople(String vehicle_acquisitionpeople) {
		this.vehicle_acquisitionpeople = vehicle_acquisitionpeople;
	}

	public String getVehicle_team() {
		return vehicle_team;
	}

	public void setVehicle_team(String vehicle_team) {
		this.vehicle_team = vehicle_team;
	}

	public String getVehicle_mark() {
		return vehicle_mark;
	}

	public void setVehicle_mark(String vehicle_mark) {
		this.vehicle_mark = vehicle_mark;
	}

	public String getVehicle_createtime() {
		return vehicle_createtime;
	}

	public void setVehicle_createtime(String vehicle_createtime) {
		this.vehicle_createtime = vehicle_createtime;
	}

	public String getVehicle_modifytime() {
		return vehicle_modifytime;
	}

	public void setVehicle_modifytime(String vehicle_modifytime) {
		this.vehicle_modifytime = vehicle_modifytime;
	}

	@Override
	public String toString() {
		return "vehicle [vehicle_id=" + vehicle_id + ", vehicle_num=" + vehicle_num + ", vehicle_platenum="
				+ vehicle_platenum + ", vehicle_state=" + vehicle_state + ", vehicle_distribution_state="
				+ vehicle_distribution_state + ", vehicle_express_state=" + vehicle_express_state
				+ ", vehicle_drivingdirection=" + vehicle_drivingdirection + ", vehicle_unit=" + vehicle_unit
				+ ", vehicle_acquisitiontime=" + vehicle_acquisitiontime + ", vehicle_acquisitionpeople="
				+ vehicle_acquisitionpeople + ", vehicle_team=" + vehicle_team + ", vehicle_mark=" + vehicle_mark
				+ ", vehicle_createtime=" + vehicle_createtime + ", vehicle_modifytime=" + vehicle_modifytime + "]";
	}

}
