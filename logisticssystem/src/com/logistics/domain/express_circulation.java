package com.logistics.domain;

/**
 * 快件流转表
 * 
 * @author XTY
 *
 */
public class express_circulation {
	/**
	 * 快件流转ID
	 */
	private String express_circulation_id;
	/**
	 * 快件ID
	 */
	private String express_circulation_express_id;
	/**
	 * 快件流转发起方
	 */
	private String express_circulation_launchpeople;
	/**
	 * 快件流转接收方
	 */
	private String express_circulation_receiver;
	/**
	 * 创建时间
	 */
	private String express_circulation_createtime;
	/**
	 * 修改时间
	 */
	private String express_circulation_modifytime;
	/**
	 * 状态
	 */
	private String express_circulation_state;

	public String getExpress_circulation_id() {
		return express_circulation_id;
	}

	public void setExpress_circulation_id(String express_circulation_id) {
		this.express_circulation_id = express_circulation_id;
	}

	public String getExpress_circulation_express_id() {
		return express_circulation_express_id;
	}

	public void setExpress_circulation_express_id(String express_circulation_express_id) {
		this.express_circulation_express_id = express_circulation_express_id;
	}

	public String getExpress_circulation_launchpeople() {
		return express_circulation_launchpeople;
	}

	public void setExpress_circulation_launchpeople(String express_circulation_launchpeople) {
		this.express_circulation_launchpeople = express_circulation_launchpeople;
	}

	public String getExpress_circulation_receiver() {
		return express_circulation_receiver;
	}

	public void setExpress_circulation_receiver(String express_circulation_receiver) {
		this.express_circulation_receiver = express_circulation_receiver;
	}

	public String getExpress_circulation_createtime() {
		return express_circulation_createtime;
	}

	public void setExpress_circulation_createtime(String express_circulation_createtime) {
		this.express_circulation_createtime = express_circulation_createtime;
	}

	public String getExpress_circulation_modifytime() {
		return express_circulation_modifytime;
	}

	public void setExpress_circulation_modifytime(String express_circulation_modifytime) {
		this.express_circulation_modifytime = express_circulation_modifytime;
	}

	public String getExpress_circulation_state() {
		return express_circulation_state;
	}

	public void setExpress_circulation_state(String express_circulation_state) {
		this.express_circulation_state = express_circulation_state;
	}

	@Override
	public String toString() {
		return "express_circulation [express_circulation_id=" + express_circulation_id
				+ ", express_circulation_express_id=" + express_circulation_express_id
				+ ", express_circulation_launchpeople=" + express_circulation_launchpeople
				+ ", express_circulation_receiver=" + express_circulation_receiver + ", express_circulation_createtime="
				+ express_circulation_createtime + ", express_circulation_modifytime=" + express_circulation_modifytime
				+ ", express_circulation_state=" + express_circulation_state + "]";
	}
}
