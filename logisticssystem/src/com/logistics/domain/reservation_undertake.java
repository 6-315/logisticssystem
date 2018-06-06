package com.logistics.domain;
/**
 * 预约单承接表实体类
 * @author LL
 *
 */
public class reservation_undertake {
	/**
	 * 预约承接单id
	 */
	private String reservation_undertake_id;
	/**
	 * 预约单号id
	 */
	private String reservation_undertake_reservation_id;
	/**
	 * 预约单承接配送员
	 */
	private String reservation_undertake_distributiontor;
	/**
	 * 预约单状态
	 */
	private String reservation_undertake_state;
	/**
	 * 创建时间
	 */
	private String reservation_undertake_createtime;
	/**
	 * 修改时间
	 */
	private String reservation_undertake_modifytime;
	public String getReservation_undertake_id() {
		return reservation_undertake_id;
	}
	public void setReservation_undertake_id(String reservation_undertake_id) {
		this.reservation_undertake_id = reservation_undertake_id;
	}
	public String getReservation_undertake_reservation_id() {
		return reservation_undertake_reservation_id;
	}
	public void setReservation_undertake_reservation_id(String reservation_undertake_reservation_id) {
		this.reservation_undertake_reservation_id = reservation_undertake_reservation_id;
	}
	public String getReservation_undertake_distributiontor() {
		return reservation_undertake_distributiontor;
	}
	public void setReservation_undertake_distributiontor(String reservation_undertake_distributiontor) {
		this.reservation_undertake_distributiontor = reservation_undertake_distributiontor;
	}
	public String getReservation_undertake_state() {
		return reservation_undertake_state;
	}
	public void setReservation_undertake_state(String reservation_undertake_state) {
		this.reservation_undertake_state = reservation_undertake_state;
	}
	public String getReservation_undertake_createtime() {
		return reservation_undertake_createtime;
	}
	public void setReservation_undertake_createtime(String reservation_undertake_createtime) {
		this.reservation_undertake_createtime = reservation_undertake_createtime;
	}
	public String getReservation_undertake_modifytime() {
		return reservation_undertake_modifytime;
	}
	public void setReservation_undertake_modifytime(String reservation_undertake_modifytime) {
		this.reservation_undertake_modifytime = reservation_undertake_modifytime;
	}
	@Override
	public String toString() {
		return "reservation_undertake [reservation_undertake_id=" + reservation_undertake_id
				+ ", reservation_undertake_reservation_id=" + reservation_undertake_reservation_id
				+ ", reservation_undertake_distributiontor=" + reservation_undertake_distributiontor
				+ ", reservation_undertake_state=" + reservation_undertake_state + ", reservation_undertake_createtime="
				+ reservation_undertake_createtime + ", reservation_undertake_modifytime="
				+ reservation_undertake_modifytime + "]";
	}
	
}
