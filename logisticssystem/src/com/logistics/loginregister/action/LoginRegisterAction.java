package com.logistics.loginregister.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.logistics.domain.userinfo;
import com.logistics.domain.staff_basicinfo;
import com.logistics.loginregister.service.LoginRegisterService;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 注册登陆公用的Action
 * 
 * @author LW
 *
 */
public class LoginRegisterAction extends ActionSupport implements ServletResponseAware, ServletRequestAware {
	private static final long serialVersionUID = 1L;
	private LoginRegisterService lginRegisterService;

	public void setLginRegisterService(LoginRegisterService lginRegisterService) {
		this.lginRegisterService = lginRegisterService;
	}

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
	/**
	 * 使用域模型
	 */
	private staff_basicinfo staff_basicinfo;
	private userinfo userinfo;

	public userinfo getUserinfo() {
		return userinfo;
	}

	public void setUserinfo(userinfo userinfo) {
		this.userinfo = userinfo;
	}

	/**
	 * 注册方法
	 */
	public void register() {
		lginRegisterService.addUserifo(userinfo);
	}

	/**
	 * 登陆方法
	 */
	public void login() {
		if (userinfo.getUserinfo_phonenumber() != null && userinfo.getUserinfo_phonenumber().length() > 0) {
			userinfo userinfo_session = lginRegisterService.loginByUser(userinfo);
			if (userinfo_session != null) {
				request.getSession().setAttribute("userinfo_session", userinfo_session);
			}
		} else {

		}
	}

}
