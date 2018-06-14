package com.logistics.expressmanagementW.DTO;

import com.logistics.domain.express;
import com.logistics.domain.staff_basicinfo;
/**
 * 配送员和快件
 * 
 * @author LW 
 *
 */
public class GetExpressAndDispatcher {
	private express expressNew;
	private staff_basicinfo staffBasicInfo;

	public express getExpressNew() {
		return expressNew;
	}

	public void setExpressNew(express expressNew) {
		this.expressNew = expressNew;
	}

	public staff_basicinfo getStaffBasicInfo() {
		return staffBasicInfo;
	}

	public void setStaffBasicInfo(staff_basicinfo staffBasicInfo) {
		this.staffBasicInfo = staffBasicInfo;
	}

	@Override
	public String toString() {
		return "GetExpressAndDispatcher [expressNew=" + expressNew + ", staffBasicInfo=" + staffBasicInfo + "]";
	}

}
