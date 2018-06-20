package com.logistics.expressmanagementW.DTO;

import com.logistics.domain.*;

/**
 * 配送员表和对应的员工基础信息表
 * 
 * @author LW
 *
 */
public class DistributiontorAndStaffBasicinfoDTO {
	/**
	 * 员工基础信息表
	 */
	private staff_basicinfo staffBasicinfo;
	/**
	 * 配送员表
	 */
	private distributiontor distributiontorNew;

	public staff_basicinfo getStaffBasicinfo() {
		return staffBasicinfo;
	}

	public void setStaffBasicinfo(staff_basicinfo staffBasicinfo) {
		this.staffBasicinfo = staffBasicinfo;
	}

	public distributiontor getDistributiontorNew() {
		return distributiontorNew;
	}

	public void setDistributiontorNew(distributiontor distributiontorNew) {
		this.distributiontorNew = distributiontorNew;
	}

	@Override
	public String toString() {
		return "DistributiontorAndStaffBasicinfoDTO [staffBasicinfo=" + staffBasicinfo + ", distributiontorNew="
				+ distributiontorNew + "]";
	}

}
