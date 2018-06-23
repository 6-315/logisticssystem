package com.logistics.expressmanagement.DTO;

import com.logistics.domain.*;

public class ReservationDTO {
	/**
	 * 预约表
	 */
	private reservation reservationInfo;
	/**
	 * 快件信息表
	 */
	private expressinfo expressInfo;
	/**
	 * 用户表
	 */
	private userinfo userInfo;
	/**
	 * 配送点信息
	 */
	private unit unitInfo;

	public reservation getReservationInfo() {
		return reservationInfo;
	}

	public void setReservationInfo(reservation reservationInfo) {
		this.reservationInfo = reservationInfo;
	}

	public expressinfo getExpressInfo() {
		return expressInfo;
	}

	public void setExpressInfo(expressinfo expressInfo) {
		this.expressInfo = expressInfo;
	}

	public userinfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(userinfo userInfo) {
		this.userInfo = userInfo;
	}

	public unit getUnitInfo() {
		return unitInfo;
	}

	public void setUnitInfo(unit unitInfo) {
		this.unitInfo = unitInfo;
	}

	@Override
	public String toString() {
		return "ReservationDTO [reservationInfo=" + reservationInfo + ", expressInfo=" + expressInfo + ", userInfo="
				+ userInfo + ", unitInfo=" + unitInfo + "]";
	}

}
