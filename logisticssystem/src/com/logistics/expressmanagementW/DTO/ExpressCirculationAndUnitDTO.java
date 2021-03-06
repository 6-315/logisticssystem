package com.logistics.expressmanagementW.DTO;

import com.logistics.domain.*;

/**
 * 流转信息详情和对应的流转接收单位
 * 
 * @author LW
 *
 */
public class ExpressCirculationAndUnitDTO {
	/**
	 * 快件流转信息
	 */
	private express_circulation expressCirculation;
	/**
	 * 快件流转发起方
	 */
	private unit unitByLaunchpeople;
	/***
	 * 
	 * 快件流转接收方
	 */
	private unit unitByReceiver;
	/**
	 * 
	 * 动作
	 */
	private String motion;
	public express_circulation getExpressCirculation() {
		return expressCirculation;
	}
	public void setExpressCirculation(express_circulation expressCirculation) {
		this.expressCirculation = expressCirculation;
	}
	public unit getUnitByLaunchpeople() {
		return unitByLaunchpeople;
	}
	public void setUnitByLaunchpeople(unit unitByLaunchpeople) {
		this.unitByLaunchpeople = unitByLaunchpeople;
	}
	public unit getUnitByReceiver() {
		return unitByReceiver;
	}
	public void setUnitByReceiver(unit unitByReceiver) {
		this.unitByReceiver = unitByReceiver;
	}
	public String getMotion() {
		return motion;
	}
	public void setMotion(String motion) {
		this.motion = motion;
	}
	@Override
	public String toString() {
		return "ExpressCirculationAndUnitDTO [expressCirculation=" + expressCirculation + ", unitByLaunchpeople="
				+ unitByLaunchpeople + ", unitByReceiver=" + unitByReceiver + ", motion=" + motion + "]";
	}
	

}
