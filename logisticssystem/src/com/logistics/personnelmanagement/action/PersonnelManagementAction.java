package com.logistics.personnelmanagement.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.logistics.personnelmanagement.service.PersonnelManagementService;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 人事管理的Action层
 * 
 * @author LW
 *
 */
public class PersonnelManagementAction extends ActionSupport implements ServletResponseAware, ServletRequestAware {

	private PersonnelManagementService personnelManagementService;

	public void setPersonnelManagementService(PersonnelManagementService personnelManagementService) {
		this.personnelManagementService = personnelManagementService;
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
}
