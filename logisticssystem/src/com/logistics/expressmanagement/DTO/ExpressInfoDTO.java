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
	private expressinfo expressInfos;
	/**
	 * 用户表
	 */
	private userinfo userInfo;

	public express getExpressInfo() {
		return expressInfo;
	}

	public void setExpressInfo(express expressInfo) {
		this.expressInfo = expressInfo;
	}

	public expressinfo getExpressInfos() {
		return expressInfos;
	}

	public void setExpressInfos(expressinfo expressInfos) {
		this.expressInfos = expressInfos;
	}

	public userinfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(userinfo userInfo) {
		this.userInfo = userInfo;
	}

	@Override
	public String toString() {
		return "ExpressInfoDTO [expressInfo=" + expressInfo + ", expressInfos=" + expressInfos + ", userInfo="
				+ userInfo + "]";
	}

}
