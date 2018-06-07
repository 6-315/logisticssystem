package com.logistics.domain;

/**
 * 员工信息实体类
 * 
 * @author LL
 *
 */
public class staff_basicinfo {
	/**
	 * 员工基础信息id
	 */
	private String staff_id;
	/**
	 * 员工工号
	 */
	private String staff_num;
	/**
	 * 员工姓名
	 */
	private String staff_name;
	/**
	 * 手机号码
	 */
	private String staff_phonenumber;
	/**
	 * 出生年月
	 */
	private String staff_birthday;
	/**
	 * 入职时间
	 */
	private String staff_entrytime;
	/**
	 * 性别
	 */
	private String staff_sex;
	/**
	 * 职位
	 */
	private String staff_position;
	/**
	 * 所属单位
	 */
	private String staff_unit;
	/**
	 * 创建时间
	 */
	private String staff_createtime;
	/**
	 * 修改时间
	 */
	private String staff_modifytime;
	public String getStaff_id() {
		return staff_id;
	}
	public void setStaff_id(String staff_id) {
		this.staff_id = staff_id;
	}
	public String getStaff_num() {
		return staff_num;
	}
	public void setStaff_num(String staff_num) {
		this.staff_num = staff_num;
	}
	public String getStaff_name() {
		return staff_name;
	}
	public void setStaff_name(String staff_name) {
		this.staff_name = staff_name;
	}
	public String getStaff_phonenumber() {
		return staff_phonenumber;
	}
	public void setStaff_phonenumber(String staff_phonenumber) {
		this.staff_phonenumber = staff_phonenumber;
	}
	public String getStaff_birthday() {
		return staff_birthday;
	}
	public void setStaff_birthday(String staff_birthday) {
		this.staff_birthday = staff_birthday;
	}
	public String getStaff_entrytime() {
		return staff_entrytime;
	}
	public void setStaff_entrytime(String staff_entrytime) {
		this.staff_entrytime = staff_entrytime;
	}
	public String getStaff_sex() {
		return staff_sex;
	}
	public void setStaff_sex(String staff_sex) {
		this.staff_sex = staff_sex;
	}
	public String getStaff_position() {
		return staff_position;
	}
	public void setStaff_position(String staff_position) {
		this.staff_position = staff_position;
	}
	public String getStaff_unit() {
		return staff_unit;
	}
	public void setStaff_unit(String staff_unit) {
		this.staff_unit = staff_unit;
	}
	public String getStaff_createtime() {
		return staff_createtime;
	}
	public void setStaff_createtime(String staff_createtime) {
		this.staff_createtime = staff_createtime;
	}
	public String getStaff_modifytime() {
		return staff_modifytime;
	}
	public void setStaff_modifytime(String staff_modifytime) {
		this.staff_modifytime = staff_modifytime;
	}
	@Override
	public String toString() {
		return "staff_basicinfo [staff_id=" + staff_id + ", staff_num=" + staff_num + ", staff_name=" + staff_name
				+ ", staff_phonenumber=" + staff_phonenumber + ", staff_birthday=" + staff_birthday
				+ ", staff_entrytime=" + staff_entrytime + ", staff_sex=" + staff_sex + ", staff_position="
				+ staff_position + ", staff_unit=" + staff_unit + ", staff_createtime=" + staff_createtime
				+ ", staff_modifytime=" + staff_modifytime + "]";
	}
	

}
