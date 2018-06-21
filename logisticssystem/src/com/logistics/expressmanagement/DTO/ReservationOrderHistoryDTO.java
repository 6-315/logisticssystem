package com.logistics.expressmanagement.DTO;

import com.logistics.domain.*;

public class ReservationOrderHistoryDTO {
	/**
	 * 预约表
	 */
	private reservation reservationInfo;
	/**
	 * 快件详情表
	 */
	private expressinfo expressDetailInfo;

	public reservation getReservationInfo() {
		return reservationInfo;
	}

	public void setReservationInfo(reservation reservationInfo) {
		this.reservationInfo = reservationInfo;
	}

	public expressinfo getExpressDetailInfo() {
		return expressDetailInfo;
	}

	public void setExpressDetailInfo(expressinfo expressDetailInfo) {
		this.expressDetailInfo = expressDetailInfo;
	}

	@Override
	public String toString() {
		return "ReservationOrderHistoryDTO [reservationInfo=" + reservationInfo + ", expressDetailInfo="
				+ expressDetailInfo + "]";
	}

}
