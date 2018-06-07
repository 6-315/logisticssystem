package com.logistics.domain;

/**
 * 快件信息表
 * 
 * @author XTY
 *
 */

public class expressinfo {
	/**
	 * 快件信息id
	 */
	private String expressinfo_id;
	/**
	 * 保价
	 */
	private String expressinfo_protectprice;
	/**
	 * 内件品名
	 */
	private String expressinfo_productname;
	/**
	 * 物品重量
	 */
	private String expressinfo_productweight;
	/**
	 * 收件人真实姓名
	 */
	private String expressinfo_addresseerealname;
	/**
	 * 收件人地址
	 */
	private String expressinfo_addresseeaddress;
	/**
	 * 收件人详细地址
	 */
	private String expressinfo_adderdetailaddress;
	/**
	 * 收件人手机号码
	 */
	private String expressinfo_addresseephonenumber;
	/**
	 * 收件人邮箱
	 */
	private String expressinfo_addresseeemail;
	/**
	 * 发件人真实姓名
	 */
	private String expressinfo_senderrealname;
	/**
	 * 发件人地址
	 */
	private String expressinfo_senderaddress;
	/**
	 * 发件人详细地址
	 */
	private String expressinfo_senderdetailaddress;
	/**
	 * 发件人手机号码
	 */
	private String expressinfo_senderphonenumber;
	/**
	 * 发件人邮箱
	 */
	private String expressinfo_senderemail;
	/**
	 * 备注
	 */
	private String expressinfo_mark;
	/**
	 * 创建时间
	 */
	private String expressinfo_createtime;
	/**
	 * 修改时间
	 */
	private String expressinfo_modifytime;

	public String getExpressinfo_id() {
		return expressinfo_id;
	}

	public void setExpressinfo_id(String expressinfo_id) {
		this.expressinfo_id = expressinfo_id;
	}

	public String getExpressinfo_protectprice() {
		return expressinfo_protectprice;
	}

	public void setExpressinfo_protectprice(String expressinfo_protectprice) {
		this.expressinfo_protectprice = expressinfo_protectprice;
	}

	public String getExpressinfo_productname() {
		return expressinfo_productname;
	}

	public void setExpressinfo_productname(String expressinfo_productname) {
		this.expressinfo_productname = expressinfo_productname;
	}

	public String getExpressinfo_productweight() {
		return expressinfo_productweight;
	}

	public void setExpressinfo_productweight(String expressinfo_productweight) {
		this.expressinfo_productweight = expressinfo_productweight;
	}

	public String getExpressinfo_addresseerealname() {
		return expressinfo_addresseerealname;
	}

	public void setExpressinfo_addresseerealname(String expressinfo_addresseerealname) {
		this.expressinfo_addresseerealname = expressinfo_addresseerealname;
	}

	public String getExpressinfo_addresseeaddress() {
		return expressinfo_addresseeaddress;
	}

	public void setExpressinfo_addresseeaddress(String expressinfo_addresseeaddress) {
		this.expressinfo_addresseeaddress = expressinfo_addresseeaddress;
	}

	public String getExpressinfo_adderdetailaddress() {
		return expressinfo_adderdetailaddress;
	}

	public void setExpressinfo_adderdetailaddress(String expressinfo_adderdetailaddress) {
		this.expressinfo_adderdetailaddress = expressinfo_adderdetailaddress;
	}

	public String getExpressinfo_addresseephonenumber() {
		return expressinfo_addresseephonenumber;
	}

	public void setExpressinfo_addresseephonenumber(String expressinfo_addresseephonenumber) {
		this.expressinfo_addresseephonenumber = expressinfo_addresseephonenumber;
	}

	public String getExpressinfo_addresseeemail() {
		return expressinfo_addresseeemail;
	}

	public void setExpressinfo_addresseeemail(String expressinfo_addresseeemail) {
		this.expressinfo_addresseeemail = expressinfo_addresseeemail;
	}

	public String getExpressinfo_senderrealname() {
		return expressinfo_senderrealname;
	}

	public void setExpressinfo_senderrealname(String expressinfo_senderrealname) {
		this.expressinfo_senderrealname = expressinfo_senderrealname;
	}

	public String getExpressinfo_senderaddress() {
		return expressinfo_senderaddress;
	}

	public void setExpressinfo_senderaddress(String expressinfo_senderaddress) {
		this.expressinfo_senderaddress = expressinfo_senderaddress;
	}

	public String getExpressinfo_senderdetailaddress() {
		return expressinfo_senderdetailaddress;
	}

	public void setExpressinfo_senderdetailaddress(String expressinfo_senderdetailaddress) {
		this.expressinfo_senderdetailaddress = expressinfo_senderdetailaddress;
	}

	public String getExpressinfo_senderphonenumber() {
		return expressinfo_senderphonenumber;
	}

	public void setExpressinfo_senderphonenumber(String expressinfo_senderphonenumber) {
		this.expressinfo_senderphonenumber = expressinfo_senderphonenumber;
	}

	public String getExpressinfo_senderemail() {
		return expressinfo_senderemail;
	}

	public void setExpressinfo_senderemail(String expressinfo_senderemail) {
		this.expressinfo_senderemail = expressinfo_senderemail;
	}

	public String getExpressinfo_mark() {
		return expressinfo_mark;
	}

	public void setExpressinfo_mark(String expressinfo_mark) {
		this.expressinfo_mark = expressinfo_mark;
	}

	public String getExpressinfo_createtime() {
		return expressinfo_createtime;
	}

	public void setExpressinfo_createtime(String expressinfo_createtime) {
		this.expressinfo_createtime = expressinfo_createtime;
	}

	public String getExpressinfo_modifytime() {
		return expressinfo_modifytime;
	}

	public void setExpressinfo_modifytime(String expressinfo_modifytime) {
		this.expressinfo_modifytime = expressinfo_modifytime;
	}

	@Override
	public String toString() {
		return "expressinfo [expressinfo_id=" + expressinfo_id + ", expressinfo_protectprice="
				+ expressinfo_protectprice + ", expressinfo_productname=" + expressinfo_productname
				+ ", expressinfo_productweight=" + expressinfo_productweight + ", expressinfo_addresseerealname="
				+ expressinfo_addresseerealname + ", expressinfo_addresseeaddress=" + expressinfo_addresseeaddress
				+ ", expressinfo_adderdetailaddress=" + expressinfo_adderdetailaddress
				+ ", expressinfo_addresseephonenumber=" + expressinfo_addresseephonenumber
				+ ", expressinfo_addresseeemail=" + expressinfo_addresseeemail + ", expressinfo_senderrealname="
				+ expressinfo_senderrealname + ", expressinfo_senderaddress=" + expressinfo_senderaddress
				+ ", expressinfo_senderdetailaddress=" + expressinfo_senderdetailaddress
				+ ", expressinfo_senderphonenumber=" + expressinfo_senderphonenumber + ", expressinfo_senderemail="
				+ expressinfo_senderemail + ", expressinfo_mark=" + expressinfo_mark + ", expressinfo_createtime="
				+ expressinfo_createtime + ", expressinfo_modifytime=" + expressinfo_modifytime + "]";
	}

}
