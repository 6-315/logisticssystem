package com.logistics.expressmanagement.DTO;

import com.logistics.domain.*;

public class ReservationExpressInfoDTO {
	/**
	 * 预约表
	 */
	private reservation reservationInfo;
	/**
	 * 快件详细信息表
	 */
	private expressinfo expressInfo;
	/**
	 * 单位表
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

	public unit getUnitInfo() {
		return unitInfo;
	}

	public void setUnitInfo(unit unitInfo) {
		this.unitInfo = unitInfo;
	}

	@Override
	public String toString() {
		return "ReservationExpressInfoDTO [reservationInfo=" + reservationInfo + ", expressInfo=" + expressInfo
				+ ", unitInfo=" + unitInfo + "]";
	}

}
