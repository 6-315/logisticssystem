package com.logistics.domain;
/**
 * 快件派送表
 * @author LMJ
 *
 */
public class express_send {
	/**
	 * 快件派送ID
	 */
	private String express_send_id;
	/**
	 * 快件ID
	 */
	private String express_send_express_id;
	/**
	 * 派送员
	 */
	private String express_send_distributiontor;
	/**
	 * 派送状态
	 */
	private String express_send_state;
	/**
	 * 创建时间
	 */
	private String express_send_createtime;
	/**
	 * 修改时间
	 */
	private String express_send_modifytime;

	public String getExpress_send_id() {
		return express_send_id;
	}

	public void setExpress_send_id(String express_send_id) {
		this.express_send_id = express_send_id;
	}

	public String getExpress_send_express_id() {
		return express_send_express_id;
	}

	public void setExpress_send_express_id(String express_send_express_id) {
		this.express_send_express_id = express_send_express_id;
	}

	public String getExpress_send_distributiontor() {
		return express_send_distributiontor;
	}

	public void setExpress_send_distributiontor(String express_send_distributiontor) {
		this.express_send_distributiontor = express_send_distributiontor;
	}

	public String getExpress_send_state() {
		return express_send_state;
	}

	public void setExpress_send_state(String express_send_state) {
		this.express_send_state = express_send_state;
	}

	public String getExpress_send_createtime() {
		return express_send_createtime;
	}

	public void setExpress_send_createtime(String express_send_createtime) {
		this.express_send_createtime = express_send_createtime;
	}

	public String getExpress_send_modifytime() {
		return express_send_modifytime;
	}

	public void setExpress_send_modifytime(String express_send_modifytime) {
		this.express_send_modifytime = express_send_modifytime;
	}

	@Override
	public String toString() {
		return "express_send [express_send_id=" + express_send_id + ", express_send_express_id="
				+ express_send_express_id + ", express_send_distributiontor=" + express_send_distributiontor
				+ ", express_send_state=" + express_send_state + ", express_send_createtime=" + express_send_createtime
				+ ", express_send_modifytime=" + express_send_modifytime + "]";
	}

}
