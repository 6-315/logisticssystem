package com.logistics.domain;

/**
 * 车辆流转表
 * 
 * @author LMJ
 *
 */
public class vehiclecirculation {
	/**
	 * 流转编号
	 */
	private String vehiclecirculation_id;
	/**
	 * 流转车辆ID
	 */
	private String vehiclecirculation_vehicle_id;
	/**
	 * 流转方
	 */
	private String vehiclecirculation_initiative;
	/**
	 * 接收方
	 */
	private String vehiclecirculation_acceptd;
	/**
	 * 流转时间
	 */
	private String vehiclecirculation_time;
	/**
	 * 流转说明
	 */
	private String vehiclecirculation_instructions;
	/**
	 * 备注
	 */
	private String vehiclecirculation_mark;
	/**
	 * 流转人
	 */
	private String vehiclecirculation_people;
	/**
	 * 创建时间
	 */
	private String vehiclecirculation_createtime;
	/**
	 * 修改时间
	 */
	private String vehiclecirculation_modifytime;

	public String getVehiclecirculation_id() {
		return vehiclecirculation_id;
	}

	public void setVehiclecirculation_id(String vehiclecirculation_id) {
		this.vehiclecirculation_id = vehiclecirculation_id;
	}

	public String getVehiclecirculation_vehicle_id() {
		return vehiclecirculation_vehicle_id;
	}

	public void setVehiclecirculation_vehicle_id(String vehiclecirculation_vehicle_id) {
		this.vehiclecirculation_vehicle_id = vehiclecirculation_vehicle_id;
	}

	public String getVehiclecirculation_initiative() {
		return vehiclecirculation_initiative;
	}

	public void setVehiclecirculation_initiative(String vehiclecirculation_initiative) {
		this.vehiclecirculation_initiative = vehiclecirculation_initiative;
	}

	public String getVehiclecirculation_acceptd() {
		return vehiclecirculation_acceptd;
	}

	public void setVehiclecirculation_acceptd(String vehiclecirculation_acceptd) {
		this.vehiclecirculation_acceptd = vehiclecirculation_acceptd;
	}

	public String getVehiclecirculation_time() {
		return vehiclecirculation_time;
	}

	public void setVehiclecirculation_time(String vehiclecirculation_time) {
		this.vehiclecirculation_time = vehiclecirculation_time;
	}

	public String getVehiclecirculation_instructions() {
		return vehiclecirculation_instructions;
	}

	public void setVehiclecirculation_instructions(String vehiclecirculation_instructions) {
		this.vehiclecirculation_instructions = vehiclecirculation_instructions;
	}

	public String getVehiclecirculation_mark() {
		return vehiclecirculation_mark;
	}

	public void setVehiclecirculation_mark(String vehiclecirculation_mark) {
		this.vehiclecirculation_mark = vehiclecirculation_mark;
	}

	public String getVehiclecirculation_people() {
		return vehiclecirculation_people;
	}

	public void setVehiclecirculation_people(String vehiclecirculation_people) {
		this.vehiclecirculation_people = vehiclecirculation_people;
	}

	public String getVehiclecirculation_createtime() {
		return vehiclecirculation_createtime;
	}

	public void setVehiclecirculation_createtime(String vehiclecirculation_createtime) {
		this.vehiclecirculation_createtime = vehiclecirculation_createtime;
	}

	public String getVehiclecirculation_modifytime() {
		return vehiclecirculation_modifytime;
	}

	public void setVehiclecirculation_modifytime(String vehiclecirculation_modifytime) {
		this.vehiclecirculation_modifytime = vehiclecirculation_modifytime;
	}

	@Override
	public String toString() {
		return "vehiclecirculation [vehiclecirculation_id=" + vehiclecirculation_id + ", vehiclecirculation_vehicle_id="
				+ vehiclecirculation_vehicle_id + ", vehiclecirculation_initiative=" + vehiclecirculation_initiative
				+ ", vehiclecirculation_acceptd=" + vehiclecirculation_acceptd + ", vehiclecirculation_time="
				+ vehiclecirculation_time + ", vehiclecirculation_instructions=" + vehiclecirculation_instructions
				+ ", vehiclecirculation_mark=" + vehiclecirculation_mark + ", vehiclecirculation_people="
				+ vehiclecirculation_people + ", vehiclecirculation_createtime=" + vehiclecirculation_createtime
				+ ", vehiclecirculation_modifytime=" + vehiclecirculation_modifytime + "]";
	}

}
