package com.logistics.domain;
/**
 * 职位表
 * @author XTY
 *
 */
public class position {
	/**
	 * 职位表ID
	 */
	private String position_id;
	/**
	 * 职位名称
	 */
	private String position_name;
	/**
	 * 直属领导
	 */
	private String position_leader;
	/**
	 * 创建时间
	 */
	private String position_createtime;
	/**
	 * 修改时间
	 */
	private String position_modifytime;
	public String getPosition_id() {
		return position_id;
	}
	public void setPosition_id(String position_id) {
		this.position_id = position_id;
	}
	public String getPosition_name() {
		return position_name;
	}
	public void setPosition_name(String position_name) {
		this.position_name = position_name;
	}
	public String getPosition_leader() {
		return position_leader;
	}
	public void setPosition_leader(String position_leader) {
		this.position_leader = position_leader;
	}
	public String getPosition_createtime() {
		return position_createtime;
	}
	public void setPosition_createtime(String position_createtime) {
		this.position_createtime = position_createtime;
	}
	public String getPosition_modifytime() {
		return position_modifytime;
	}
	public void setPosition_modifytime(String position_modifytime) {
		this.position_modifytime = position_modifytime;
	}
	@Override
	public String toString() {
		return "position [position_id=" + position_id + ", position_name=" + position_name + ", position_leader="
				+ position_leader + ", position_createtime=" + position_createtime + ", position_modifytime="
				+ position_modifytime + "]";
	}
	

}
