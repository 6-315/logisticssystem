package com.logistics.expressmanagement.DTO;

import com.logistics.domain.*;

public class ExpressInfoDTO {
	/**
	 * 快件表
	 */
	private express expressInfo;
	/**
	 * 快件信息表
	 */
	private expressinfo expressDetailInfo;
	/**
	 * 用户表
	 */
	private userinfo userInfo;
	/**
	 * 单位表
	 */
	private unit unitInfo;
	/**
	 * 起点站
	 */
	private unit beginUnit;
	/**
	 * 终点站
	 */
	private unit endUnit;

	public express getExpressInfo() {
		return expressInfo;
	}

	public void setExpressInfo(express expressInfo) {
		this.expressInfo = expressInfo;
	}

	public expressinfo getExpressDetailInfo() {
		return expressDetailInfo;
	}

	public void setExpressDetailInfo(expressinfo expressDetailInfo) {
		this.expressDetailInfo = expressDetailInfo;
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

	public unit getBeginUnit() {
		return beginUnit;
	}

	public void setBeginUnit(unit beginUnit) {
		this.beginUnit = beginUnit;
	}

	public unit getEndUnit() {
		return endUnit;
	}

	public void setEndUnit(unit endUnit) {
		this.endUnit = endUnit;
	}

	@Override
	public String toString() {
		return "ExpressInfoDTO [expressInfo=" + expressInfo + ", expressDetailInfo=" + expressDetailInfo + ", userInfo="
				+ userInfo + ", unitInfo=" + unitInfo + ", beginUnit=" + beginUnit + ", endUnit=" + endUnit + "]";
	}

}
