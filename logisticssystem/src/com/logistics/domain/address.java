package com.logistics.domain;

/**
 * 这是地址簿表
 * 
 * @author YX
 * update LMJ
 *
 */
public class address {
	/**
	 * 地址id
	 */
	private String address_id;
	/**
	 * 收件人地址
	 */
	private String address_address;
	/**
	 * 收件人详细地址
	 */
	private String address_detailaddress;
	/**
	 * 邮政编号
	 */
	private String address_postalnumber;
	/**
	 * 联系人姓名
	 */
	private String address_realname;
	/**
	 * 收件人手机
	 */
	private String address_phonenumber;
	/**
	 * 是否是默认地址
	 */
	private String address_isdefault;
	/**
	 * 所属用户
	 */
	private String address_userinfo_id;
	/**
	 * 创建时间
	 */
	private String address_createtime;
	/**
	 * 修改时间
	 */
	private String address_modifytime;
	/**
	 * 状态
	 */
	private String address_state;

	public String getAddress_id() {
		return address_id;
	}

	public void setAddress_id(String address_id) {
		this.address_id = address_id;
	}

	public String getAddress_address() {
		return address_address;
	}

	public void setAddress_address(String address_address) {
		this.address_address = address_address;
	}

	public String getAddress_detailaddress() {
		return address_detailaddress;
	}

	public void setAddress_detailaddress(String address_detailaddress) {
		this.address_detailaddress = address_detailaddress;
	}

	public String getAddress_postalnumber() {
		return address_postalnumber;
	}

	public void setAddress_postalnumber(String address_postalnumber) {
		this.address_postalnumber = address_postalnumber;
	}

	public String getAddress_realname() {
		return address_realname;
	}

	public void setAddress_realname(String address_realname) {
		this.address_realname = address_realname;
	}

	public String getAddress_phonenumber() {
		return address_phonenumber;
	}

	public void setAddress_phonenumber(String address_phonenumber) {
		this.address_phonenumber = address_phonenumber;
	}

	public String getAddress_isdefault() {
		return address_isdefault;
	}

	public void setAddress_isdefault(String address_isdefault) {
		this.address_isdefault = address_isdefault;
	}

	public String getAddress_userinfo_id() {
		return address_userinfo_id;
	}

	public void setAddress_userinfo_id(String address_userinfo_id) {
		this.address_userinfo_id = address_userinfo_id;
	}

	public String getAddress_createtime() {
		return address_createtime;
	}

	public void setAddress_createtime(String address_createtime) {
		this.address_createtime = address_createtime;
	}

	public String getAddress_modifytime() {
		return address_modifytime;
	}

	public void setAddress_modifytime(String address_modifytime) {
		this.address_modifytime = address_modifytime;
	}

	public String getAddress_state() {
		return address_state;
	}

	public void setAddress_state(String address_state) {
		this.address_state = address_state;
	}

	@Override
	public String toString() {
		return "address [address_id=" + address_id + ", address_address=" + address_address + ", address_detailaddress="
				+ address_detailaddress + ", address_postalnumber=" + address_postalnumber + ", address_realname="
				+ address_realname + ", address_phonenumber=" + address_phonenumber + ", address_isdefault="
				+ address_isdefault + ", address_userinfo_id=" + address_userinfo_id + ", address_createtime="
				+ address_createtime + ", address_modifytime=" + address_modifytime + ", address_state=" + address_state
				+ "]";
	}

}
