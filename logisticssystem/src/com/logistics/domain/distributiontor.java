package com.logistics.domain;
/**
 * 这是配送员表
 * @author YX
 *
 */
public class distributiontor {
	/**
	 * 配送员id
	 */
	private String distributiontor_id;
	/**
	 * 配送员基础信息
	 */
	private String distributiontor_basicinfo;
	/**
	 * 配送员所属配送点
	 */
	private String distributiontor_belongdistribution;
	/**
	 * 创建时间
	 */
	private String distributiontor_createtime;
	/**
	 * 修改时间
	 */
	private String distributiontor_modifytime;
	/**
	 * 状态
	 */
	private String distributiontor_state;
	
	public String getDistributiontor_id() {
		return distributiontor_id;
	}
	public void setDistributiontor_id(String distributiontor_id) {
		this.distributiontor_id = distributiontor_id;
	}
	public String getDistributiontor_basicinfo() {
		return distributiontor_basicinfo;
	}
	public void setDistributiontor_basicinfo(String distributiontor_basicinfo) {
		this.distributiontor_basicinfo = distributiontor_basicinfo;
	}
	public String getDistributiontor_belongdistribution() {
		return distributiontor_belongdistribution;
	}
	public void setDistributiontor_belongdistribution(String distributiontor_belongdistribution) {
		this.distributiontor_belongdistribution = distributiontor_belongdistribution;
	}
	public String getDistributiontor_createtime() {
		return distributiontor_createtime;
	}
	public void setDistributiontor_createtime(String distributiontor_createtime) {
		this.distributiontor_createtime = distributiontor_createtime;
	}
	public String getDistributiontor_modifytime() {
		return distributiontor_modifytime;
	}
	public void setDistributiontor_modifytime(String distributiontor_modifytime) {
		this.distributiontor_modifytime = distributiontor_modifytime;
	}
	public String getDistributiontor_state() {
		return distributiontor_state;
	}
	public void setDistributiontor_state(String distributiontor_state) {
		this.distributiontor_state = distributiontor_state;
	}
	@Override
	public String toString() {
		return "distributiontor [distributiontor_id=" + distributiontor_id + ", distributiontor_basicinfo="
				+ distributiontor_basicinfo + ", distributiontor_belongdistribution="
				+ distributiontor_belongdistribution + ", distributiontor_createtime=" + distributiontor_createtime
				+ ", distributiontor_modifytime=" + distributiontor_modifytime + ", distributiontor_state="
				+ distributiontor_state + "]";
	}
	
}
