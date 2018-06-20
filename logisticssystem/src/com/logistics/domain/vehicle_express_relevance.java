package com.logistics.domain;

public class vehicle_express_relevance {
	/**
	 * 关联表ID
	 */
	private String vehicle_express_relevance_id;
	/**
	 * 快件ID
	 */
	private String vehicle_express_relevance_expressinfo;
	/**
	 * 快件开始时间
	 */
	private String vehicle_express_relevance_expressinfo_begintime;
	/**
	 * 快件结束时间
	 */
	private String vehicle_express_relevance_expressinfo_endtime;
	/**
	 * 车辆ID
	 */
	private String vehicle_express_relevance_vehicleinfo;
	/**
	 * 创建时间
	 */
	private String vehicle_express_relevance_createtime;
	/**
	 * 修改时间
	 */
	private String vehicle_express_relevance_modifytime;

	public String getVehicle_express_relevance_id() {
		return vehicle_express_relevance_id;
	}

	public void setVehicle_express_relevance_id(String vehicle_express_relevance_id) {
		this.vehicle_express_relevance_id = vehicle_express_relevance_id;
	}

	public String getVehicle_express_relevance_expressinfo() {
		return vehicle_express_relevance_expressinfo;
	}

	public void setVehicle_express_relevance_expressinfo(String vehicle_express_relevance_expressinfo) {
		this.vehicle_express_relevance_expressinfo = vehicle_express_relevance_expressinfo;
	}

	public String getVehicle_express_relevance_expressinfo_begintime() {
		return vehicle_express_relevance_expressinfo_begintime;
	}

	public void setVehicle_express_relevance_expressinfo_begintime(
			String vehicle_express_relevance_expressinfo_begintime) {
		this.vehicle_express_relevance_expressinfo_begintime = vehicle_express_relevance_expressinfo_begintime;
	}

	public String getVehicle_express_relevance_expressinfo_endtime() {
		return vehicle_express_relevance_expressinfo_endtime;
	}

	public void setVehicle_express_relevance_expressinfo_endtime(String vehicle_express_relevance_expressinfo_endtime) {
		this.vehicle_express_relevance_expressinfo_endtime = vehicle_express_relevance_expressinfo_endtime;
	}

	public String getVehicle_express_relevance_vehicleinfo() {
		return vehicle_express_relevance_vehicleinfo;
	}

	public void setVehicle_express_relevance_vehicleinfo(String vehicle_express_relevance_vehicleinfo) {
		this.vehicle_express_relevance_vehicleinfo = vehicle_express_relevance_vehicleinfo;
	}

	public String getVehicle_express_relevance_createtime() {
		return vehicle_express_relevance_createtime;
	}

	public void setVehicle_express_relevance_createtime(String vehicle_express_relevance_createtime) {
		this.vehicle_express_relevance_createtime = vehicle_express_relevance_createtime;
	}

	public String getVehicle_express_relevance_modifytime() {
		return vehicle_express_relevance_modifytime;
	}

	public void setVehicle_express_relevance_modifytime(String vehicle_express_relevance_modifytime) {
		this.vehicle_express_relevance_modifytime = vehicle_express_relevance_modifytime;
	}

	@Override
	public String toString() {
		return "vehicle_express_relevance [vehicle_express_relevance_id=" + vehicle_express_relevance_id
				+ ", vehicle_express_relevance_expressinfo=" + vehicle_express_relevance_expressinfo
				+ ", vehicle_express_relevance_expressinfo_begintime=" + vehicle_express_relevance_expressinfo_begintime
				+ ", vehicle_express_relevance_expressinfo_endtime=" + vehicle_express_relevance_expressinfo_endtime
				+ ", vehicle_express_relevance_vehicleinfo=" + vehicle_express_relevance_vehicleinfo
				+ ", vehicle_express_relevance_createtime=" + vehicle_express_relevance_createtime
				+ ", vehicle_express_relevance_modifytime=" + vehicle_express_relevance_modifytime + "]";
	}

}
