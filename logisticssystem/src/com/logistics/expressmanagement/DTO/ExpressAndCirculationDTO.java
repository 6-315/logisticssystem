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

	@Override
	public String toString() {
		return "ExpressAndCirculationDTO [expressInfo=" + expressInfo + ", expressCirculation=" + expressCirculation
				+ "]";
	}

}
