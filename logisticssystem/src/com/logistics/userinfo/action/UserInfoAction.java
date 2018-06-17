package com.logistics.userinfo.action;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.logistics.userinfo.service.UserInfoService;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 用户信息Action层
 * 
 * @author LW
 *
 */
public class UserInfoAction extends ActionSupport implements ServletResponseAware, ServletRequestAware {
	private static final long serialVersionUID = 1L;
	/**
	 * service层注入
	 */
	private UserInfoService userInfoService;

	public void setUserInfoService(UserInfoService userInfoService) {
		this.userInfoService = userInfoService;
	}

	/**
	 * 
	 */
	/**
	 * 实现request以及response
	 */
	private HttpServletResponse response;

	private HttpServletRequest request;

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	/**
	 * 实现结束
	 */
	public String userIndex() {
		return "userIndex";
	}

	/**
	 * 跳转到我要寄件的页面
	 * 
	 * @return
	 */
	public String pageSendExpress() {
		return "pageSendExpress";
	}

	/**
	 * 跳转到我要查件的页面
	 * 
	 * @return
	 */
	public String pageSearchExpress() {
		return "pageSearchExpress";
	}

	/**
	 * 跳转到我的快件的页面
	 * 
	 * @return
	 */
	public String pageMyExpress() {
		return "pageMyExpress";
	}

	/**
	 * 跳转到用户信息页面
	 * 
	 * @return
	 */
	public String pageUserInfo() {
		return "pageUserInfo";
	}

	/**
	 * 地址管理
	 * 
	 * @return
	 */
	public String pageUserAddress() {
		return "pageUserAddress";
	}

	/**
	 * 更改密码
	 * 
	 * @return
	 */
	public String pageUpdatePassword() {
		return "pageUpdatePassword";
	}

	/**
	 * 跳转到我的消息页面
	 * 
	 * @return
	 */
	public String pageUserMessage() {
		return "pageUserMessage";
	}

}
