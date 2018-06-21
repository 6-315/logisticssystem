package com.logistics.expressmanagement.DTO;

import com.logistics.domain.*;

public class ExpressAndCirculationDTO {
	/**
	 * 快件表
	 */
	private express expressInfo;
	/**
	 * 快件流转表
	 */
	private express_circulation expressCirculation;
	/**
	 * 车辆表
	 */
	private vehicle vehicleInfo;
	/**
	 * 用户表
	 */
	private userinfo userInfo;
	/**
	 * 快件详情表
	 */
	private expressinfo expressDetailInfo;
	/**
	 * 配送员
	 */
	private distributiontor distributor;


	public express getExpressInfo() {
		return expressInfo;
	}

	public void setExpressInfo(express expressInfo) {
		this.expressInfo = expressInfo;
	}

	public express_circulation getExpressCirculation() {
		return expressCirculation;
	}

	public void setExpressCirculation(express_circulation expressCirculation) {
		this.expressCirculation = expressCirculation;
	}

	public vehicle getVehicleInfo() {
		return vehicleInfo;
	}

	public void setVehicleInfo(vehicle vehicleInfo) {
		this.vehicleInfo = vehicleInfo;
	}

	public userinfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(userinfo userInfo) {
		this.userInfo = userInfo;
	}

	public expressinfo getExpressDetailInfo() {
		return expressDetailInfo;
	}

	public void setExpressDetailInfo(expressinfo expressDetailInfo) {
		this.expressDetailInfo = expressDetailInfo;
	}

	public distributiontor getDistributor() {
		return distributor;
	}

	public void setDistributor(distributiontor distributor) {
		this.distributor = distributor;
	}

	@Override
	public String toString() {
		return "ExpressAndCirculationDTO [expressInfo=" + expressInfo + ", expressCirculation=" + expressCirculation
				+ ", vehicleInfo=" + vehicleInfo + ", userInfo=" + userInfo + ", expressDetailInfo=" + expressDetailInfo
				+ ", distributor=" + distributor + "]";
	}

}
