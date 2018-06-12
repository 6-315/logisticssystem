package com.logistics.domain;

/**
 * 快件路线
 * 
 * @author XTY
 * update LMJ
 *
 */
public class express_route {
	/**
	 * 快件路线id
	 */
	private String express_route_id;
	/**
	 * 快件id
	 */
	private String express_route_belongexpress;
	/**
	 * 路线ID
	 */
	private String express_route_route_id;
	/**
	 * 路线方向
	 */
	private String express_route_route_away;
	/**
	 * 路线状态
	 */
	private String eexpress_route_state;
	/**
	 * 上一级路线
	 */
	private String express_route_superior;
	/**
	 * 创建时间
	 */
	private String express_route_createtime;
	/**
	 * 修改时间
	 */
	private String express_route_modifytime;

	public String getExpress_route_id() {
		return express_route_id;
	}

	public void setExpress_route_id(String express_route_id) {
		this.express_route_id = express_route_id;
	}

	public String getExpress_route_belongexpress() {
		return express_route_belongexpress;
	}

	public void setExpress_route_belongexpress(String express_route_belongexpress) {
		this.express_route_belongexpress = express_route_belongexpress;
	}

	public String getExpress_route_route_id() {
		return express_route_route_id;
	}

	public void setExpress_route_route_id(String express_route_route_id) {
		this.express_route_route_id = express_route_route_id;
	}

	public String getExpress_route_route_away() {
		return express_route_route_away;
	}

	public void setExpress_route_route_away(String express_route_route_away) {
		this.express_route_route_away = express_route_route_away;
	}

	public String getEexpress_route_state() {
		return eexpress_route_state;
	}

	public void setEexpress_route_state(String eexpress_route_state) {
		this.eexpress_route_state = eexpress_route_state;
	}

	public String getExpress_route_superior() {
		return express_route_superior;
	}

	public void setExpress_route_superior(String express_route_superior) {
		this.express_route_superior = express_route_superior;
	}

	public String getExpress_route_createtime() {
		return express_route_createtime;
	}

	public void setExpress_route_createtime(String express_route_createtime) {
		this.express_route_createtime = express_route_createtime;
	}

	public String getExpress_route_modifytime() {
		return express_route_modifytime;
	}

	public void setExpress_route_modifytime(String express_route_modifytime) {
		this.express_route_modifytime = express_route_modifytime;
	}

	@Override
	public String toString() {
		return "express_route [express_route_id=" + express_route_id + ", express_route_belongexpress="
				+ express_route_belongexpress + ", express_route_route_id=" + express_route_route_id
				+ ", express_route_route_away=" + express_route_route_away + ", eexpress_route_state="
				+ eexpress_route_state + ", express_route_superior=" + express_route_superior
				+ ", express_route_createtime=" + express_route_createtime + ", express_route_modifytime="
				+ express_route_modifytime + "]";
	}

}
