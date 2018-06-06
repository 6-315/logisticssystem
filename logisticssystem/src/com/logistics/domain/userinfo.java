package com.logistics.domain;

/**
 * 用户信息
 * 
 * @author LMJ
 *
 */
public class userinfo {
	/**
	 * 用户ID
	 */
	private String userinfo_id;
	/**
	 * 用户昵称
	 */
	private String userinfo_nickname;
	/**
	 * 用户密码
	 */
	private String userinfo_password;
	/**
	 * 用户手机号码
	 */
	private String userinfo_phonenumber;
	/**
	 * 用户邮箱
	 */
	private String userinfo_email;
	/**
	 * 用户创建时间
	 */
	private String userinfo_createtime;
	/**
	 * 用户修改时间
	 */
	private String userinfo_modify;
	/**
	 * 备注
	 */
	private String userinfo_mark;
	/**
	 * 用户最后一次登录IP
	 */
	private String userinfo_lastlyip;
	/**
	 * 用户最后一次登录时间
	 */
	private String userinfo_lastlytime;

	public String getUserinfo_id() {
		return userinfo_id;
	}

	public void setUserinfo_id(String userinfo_id) {
		this.userinfo_id = userinfo_id;
	}

	public String getUserinfo_nickname() {
		return userinfo_nickname;
	}

	public void setUserinfo_nickname(String userinfo_nickname) {
		this.userinfo_nickname = userinfo_nickname;
	}

	public String getUserinfo_password() {
		return userinfo_password;
	}

	public void setUserinfo_password(String userinfo_password) {
		this.userinfo_password = userinfo_password;
	}

	public String getUserinfo_phonenumber() {
		return userinfo_phonenumber;
	}

	public void setUserinfo_phonenumber(String userinfo_phonenumber) {
		this.userinfo_phonenumber = userinfo_phonenumber;
	}

	public String getUserinfo_email() {
		return userinfo_email;
	}

	public void setUserinfo_email(String userinfo_email) {
		this.userinfo_email = userinfo_email;
	}

	public String getUserinfo_createtime() {
		return userinfo_createtime;
	}

	public void setUserinfo_createtime(String userinfo_createtime) {
		this.userinfo_createtime = userinfo_createtime;
	}

	public String getUserinfo_modify() {
		return userinfo_modify;
	}

	public void setUserinfo_modify(String userinfo_modify) {
		this.userinfo_modify = userinfo_modify;
	}

	public String getUserinfo_mark() {
		return userinfo_mark;
	}

	public void setUserinfo_mark(String userinfo_mark) {
		this.userinfo_mark = userinfo_mark;
	}

	public String getUserinfo_lastlyip() {
		return userinfo_lastlyip;
	}

	public void setUserinfo_lastlyip(String userinfo_lastlyip) {
		this.userinfo_lastlyip = userinfo_lastlyip;
	}

	public String getUserinfo_lastlytime() {
		return userinfo_lastlytime;
	}

	public void setUserinfo_lastlytime(String userinfo_lastlytime) {
		this.userinfo_lastlytime = userinfo_lastlytime;
	}

	@Override
	public String toString() {
		return "userinfo [userinfo_id=" + userinfo_id + ", userinfo_nickname=" + userinfo_nickname
				+ ", userinfo_password=" + userinfo_password + ", userinfo_phonenumber=" + userinfo_phonenumber
				+ ", userinfo_email=" + userinfo_email + ", userinfo_createtime=" + userinfo_createtime
				+ ", userinfo_modify=" + userinfo_modify + ", userinfo_mark=" + userinfo_mark + ", userinfo_lastlyip="
				+ userinfo_lastlyip + ", userinfo_lastlytime=" + userinfo_lastlytime + "]";
	}

}