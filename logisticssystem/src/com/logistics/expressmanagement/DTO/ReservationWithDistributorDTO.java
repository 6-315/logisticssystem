package com.logistics.expressmanagement.DTO;

import com.logistics.domain.*;

public class ReservationWithDistributorDTO {
	/**
	 * 预约表
	 */
	private reservation reservationInfo;
	/**
	 * 派送员表
	 */
	private distributiontor distributor;

	public reservation getReservationInfo() {
		return reservationInfo;
	}

	public void setReservationInfo(reservation reservationInfo) {
		this.reservationInfo = reservationInfo;
	}

	public distributiontor getDistributor() {
		return distributor;
	}

	public void setDistributor(distributiontor distributor) {
		this.distributor = distributor;
	}

	@Override
	public String toString() {
		return "ReservationWithDistributorDTO [reservationInfo=" + reservationInfo + ", distributor=" + distributor
				+ "]";
	}

}
