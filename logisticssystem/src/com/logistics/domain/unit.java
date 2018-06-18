package com.logistics.domain;

/**
 * 单位信息表
 * 
 * @author LMJ
 *
 */
public class unit {
	/**
	 * 单位ID
	 */
	private String unit_id;
	/**
	 * 单位编号
	 */
	private String unit_num;
	/**
	 * 单位名称
	 */
	private String unit_name;
	/**
	 * 单位地址
	 */
	private String unit_address;
	/**
	 * 单位详细地址
	 */
	private String unit_detailaddress;
	/**
	 * 单位类型
	 */
	private String unit_type;
	/**
	 * 上级单位
	 */
	private String unit_superiorunit;
	/**
	 * 创建者
	 */
	private String unit_creator;
	/**
	 * 状态
	 */
	private String unit_state;
	/**
	 * 管理员
	 */
	private String unit_admin;
	/**
	 * 创建时间
	 */
	private String unit_createtime;
	/**
	 * 修改时间
	 */
	private String unit_modifytime;
	/**
	 * 联系方式
	 */
	private String unit_phonenumber;

	public String getUnit_id() {
		return unit_id;
	}

	public void setUnit_id(String unit_id) {
		this.unit_id = unit_id;
	}

	public String getUnit_num() {
		return unit_num;
	}

	public void setUnit_num(String unit_num) {
		this.unit_num = unit_num;
	}

	public String getUnit_name() {
		return unit_name;
	}

	public void setUnit_name(String unit_name) {
		this.unit_name = unit_name;
	}

	public String getUnit_address() {
		return unit_address;
	}

	public void setUnit_address(String unit_address) {
		this.unit_address = unit_address;
	}

	public String getUnit_detailaddress() {
		return unit_detailaddress;
	}

	public void setUnit_detailaddress(String unit_detailaddress) {
		this.unit_detailaddress = unit_detailaddress;
	}

	public String getUnit_type() {
		return unit_type;
	}

	public void setUnit_type(String unit_type) {
		this.unit_type = unit_type;
	}

	public String getUnit_superiorunit() {
		return unit_superiorunit;
	}

	public void setUnit_superiorunit(String unit_superiorunit) {
		this.unit_superiorunit = unit_superiorunit;
	}

	public String getUnit_creator() {
		return unit_creator;
	}

	public void setUnit_creator(String unit_creator) {
		this.unit_creator = unit_creator;
	}

	public String getUnit_state() {
		return unit_state;
	}

	public void setUnit_state(String unit_state) {
		this.unit_state = unit_state;
	}

	public String getUnit_admin() {
		return unit_admin;
	}

	public void setUnit_admin(String unit_admin) {
		this.unit_admin = unit_admin;
	}

	public String getUnit_createtime() {
		return unit_createtime;
	}

	public void setUnit_createtime(String unit_createtime) {
		this.unit_createtime = unit_createtime;
	}

	public String getUnit_modifytime() {
		return unit_modifytime;
	}

	public void setUnit_modifytime(String unit_modifytime) {
		this.unit_modifytime = unit_modifytime;
	}

	public String getUnit_phonenumber() {
		return unit_phonenumber;
	}

	public void setUnit_phonenumber(String unit_phonenumber) {
		this.unit_phonenumber = unit_phonenumber;
	}

	@Override
	public String toString() {
		return "unit [unit_id=" + unit_id + ", unit_num=" + unit_num + ", unit_name=" + unit_name + ", unit_address="
				+ unit_address + ", unit_detailaddress=" + unit_detailaddress + ", unit_type=" + unit_type
				+ ", unit_superiorunit=" + unit_superiorunit + ", unit_creator=" + unit_creator + ", unit_state="
				+ unit_state + ", unit_admin=" + unit_admin + ", unit_createtime=" + unit_createtime
				+ ", unit_modifytime=" + unit_modifytime + ", unit_phonenumber=" + unit_phonenumber + "]";
	}

}
