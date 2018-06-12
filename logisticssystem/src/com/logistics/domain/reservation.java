package com.logistics.domain;

/**
 * 预约表实体类
 * 
 * @author LL
 *
 */
public class reservation {
	/**
	 * 预约单id
	 */
	private String reservation_id;
	/**
	 * 预约单编号
	 */
	private String reservation_num;
	/**
	 * 快件信息ID
	 */
	private String reservation_expressinfo;
	/**
	 * 配送员ID
	 */
	private String reservation_distributiontor;
	/**
	 * 预约用户
	 */
	private String reservation_user;
	/**
	 * 预约单状态
	 */
	private String reservation_state;
	/**
	 * 预约单创建时间
	 */
	private String reservation_createtime;
	/**
	 * 预约单修改时间
	 */
	private String reservation_modifytime;

	public String getReservation_id() {
		return reservation_id;
	}

	public void setReservation_id(String reservation_id) {
		this.reservation_id = reservation_id;
	}

	public String getReservation_num() {
		return reservation_num;
	}

	public void setReservation_num(String reservation_num) {
		this.reservation_num = reservation_num;
	}

	public String getReservation_expressinfo() {
		return reservation_expressinfo;
	}

	public void setReservation_expressinfo(String reservation_expressinfo) {
		this.reservation_expressinfo = reservation_expressinfo;
	}

	public String getReservation_distributiontor() {
		return reservation_distributiontor;
	}

	public void setReservation_distributiontor(String reservation_distributiontor) {
		this.reservation_distributiontor = reservation_distributiontor;
	}

	public String getReservation_user() {
		return reservation_user;
	}

	public void setReservation_user(String reservation_user) {
		this.reservation_user = reservation_user;
	}

	public String getReservation_state() {
		return reservation_state;
	}

	public void setReservation_state(String reservation_state) {
		this.reservation_state = reservation_state;
	}

	public String getReservation_createtime() {
		return reservation_createtime;
	}

	public void setReservation_createtime(String reservation_createtime) {
		this.reservation_createtime = reservation_createtime;
	}

	public String getReservation_modifytime() {
		return reservation_modifytime;
	}

	public void setReservation_modifytime(String reservation_modifytime) {
		this.reservation_modifytime = reservation_modifytime;
	}

	@Override
	public String toString() {
		return "reservation [reservation_id=" + reservation_id + ", reservation_num=" + reservation_num
				+ ", reservation_expressinfo=" + reservation_expressinfo + ", reservation_distributiontor="
				+ reservation_distributiontor + ", reservation_user=" + reservation_user + ", reservation_state="
				+ reservation_state + ", reservation_createtime=" + reservation_createtime + ", reservation_modifytime="
				+ reservation_modifytime + "]";
	}

}
