package com.logistics.domain;

/**
 * 这是快件表
 * 
 * @author YX
 *
 */
public class express {
	/**
	 * 快件id
	 */
	private String express_id;
	/**
	 * 信息表id
	 */
	private String express_expressinfoid;
	/**
	 * 快件单号
	 */
	private String express_number;
	/**
	 * 发件用户
	 */
	private String express_belong;
	/**
	 * 快件所在单位
	 */
	private String express_belongunit;
	/**
	 * 状态
	 */
	private String express_state;
	/**
	 * 快件始发站
	 */
	private String express_originating;
	/**
	 * 快件终点站
	 */
	private String express_end;
	/**
	 * 创建时间
	 */
	private String express_createtime;
	/**
	 * 修改时间
	 */
	private String express_modifytime;

	public String getExpress_id() {
		return express_id;
	}

	public void setExpress_id(String express_id) {
		this.express_id = express_id;
	}

	public String getExpress_expressinfoid() {
		return express_expressinfoid;
	}

	public void setExpress_expressinfoid(String express_expressinfoid) {
		this.express_expressinfoid = express_expressinfoid;
	}

	public String getExpress_number() {
		return express_number;
	}

	public void setExpress_number(String express_number) {
		this.express_number = express_number;
	}

	public String getExpress_belong() {
		return express_belong;
	}

	public void setExpress_belong(String express_belong) {
		this.express_belong = express_belong;
	}

	public String getExpress_belongunit() {
		return express_belongunit;
	}

	public void setExpress_belongunit(String express_belongunit) {
		this.express_belongunit = express_belongunit;
	}

	public String getExpress_state() {
		return express_state;
	}

	public void setExpress_state(String express_state) {
		this.express_state = express_state;
	}

	public String getExpress_originating() {
		return express_originating;
	}

	public void setExpress_originating(String express_originating) {
		this.express_originating = express_originating;
	}

	public String getExpress_end() {
		return express_end;
	}

	public void setExpress_end(String express_end) {
		this.express_end = express_end;
	}

	public String getExpress_createtime() {
		return express_createtime;
	}

	public void setExpress_createtime(String express_createtime) {
		this.express_createtime = express_createtime;
	}

	public String getExpress_modifytime() {
		return express_modifytime;
	}

	public void setExpress_modifytime(String express_modifytime) {
		this.express_modifytime = express_modifytime;
	}

	@Override
	public String toString() {
		return "express [express_id=" + express_id + ", express_expressinfoid=" + express_expressinfoid
				+ ", express_number=" + express_number + ", express_belong=" + express_belong + ", express_belongunit="
				+ express_belongunit + ", express_state=" + express_state + ", express_originating="
				+ express_originating + ", express_end=" + express_end + ", express_createtime=" + express_createtime
				+ ", express_modifytime=" + express_modifytime + "]";
	}

}
