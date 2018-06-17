package com.logistics.expressmanagementW.DTO;

import com.logistics.domain.*;

public class ExpressinfoAndExpressDTO {
	/**
	 * 快件表
	 */
	private express expressNew;
	/**
	 * 快件信息表
	 */
	private expressinfo expressInfo;

	public express getExpressNew() {
		return expressNew;
	}

	public void setExpressNew(express expressNew) {
		this.expressNew = expressNew;
	}

	public expressinfo getExpressInfo() {
		return expressInfo;
	}

	public void setExpressInfo(expressinfo expressInfo) {
		this.expressInfo = expressInfo;
	}

	@Override
	public String toString() {
		return "ExpressinfoAndExpressDTO [expressNew=" + expressNew + ", expressInfo=" + expressInfo + "]";
	}

}
