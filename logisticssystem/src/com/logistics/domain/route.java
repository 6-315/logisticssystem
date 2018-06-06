package com.logistics.domain;
/**
 * 路线实体类
 * @author LL
 *
 */
public class route {
	/**
	 * 路线_id
	 */
	private String route_id;
	/**
	 * 路线编号
	 */
	private String route_num;
	/**
	 * 路线创建者
	 */
	private String route_creater;
	/**
	 * 路线始发中转站_id
	 */
	private String route_departurestation;
	/**
	 * 路线终点中转站_id
	 */
	private String route_terminalstation;
	/**
	 * 路线状态
	 */
	private String route_state;
	/**
	 * 路线创建时间
	 */
	private String route_createtime;
	/**
	 * 路线修改时间
	 */
	private String route_modifytime;
	public String getRoute_id() {
		return route_id;
	}
	public void setRoute_id(String route_id) {
		this.route_id = route_id;
	}
	public String getRoute_num() {
		return route_num;
	}
	public void setRoute_num(String route_num) {
		this.route_num = route_num;
	}
	public String getRoute_creater() {
		return route_creater;
	}
	public void setRoute_creater(String route_creater) {
		this.route_creater = route_creater;
	}
	public String getRoute_departurestation() {
		return route_departurestation;
	}
	public void setRoute_departurestation(String route_departurestation) {
		this.route_departurestation = route_departurestation;
	}
	public String getRoute_terminalstation() {
		return route_terminalstation;
	}
	public void setRoute_terminalstation(String route_terminalstation) {
		this.route_terminalstation = route_terminalstation;
	}
	public String getRoute_state() {
		return route_state;
	}
	public void setRoute_state(String route_state) {
		this.route_state = route_state;
	}
	public String getRoute_createtime() {
		return route_createtime;
	}
	public void setRoute_createtime(String route_createtime) {
		this.route_createtime = route_createtime;
	}
	public String getRoute_modifytime() {
		return route_modifytime;
	}
	public void setRoute_modifytime(String route_modifytime) {
		this.route_modifytime = route_modifytime;
	}
	@Override
	public String toString() {
		return "route [route_id=" + route_id + ", route_num=" + route_num + ", route_creater=" + route_creater
				+ ", route_departurestation=" + route_departurestation + ", route_terminalstation="
				+ route_terminalstation + ", route_state=" + route_state + ", route_createtime=" + route_createtime
				+ ", route_modifytime=" + route_modifytime + "]";
	}
	
}
