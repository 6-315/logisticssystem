package com.logistics.distribution.action;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.logistics.distribution.service.DistributionService;
import com.opensymphony.xwork2.ActionSupport;
 
/**
 * 配送点管理 的action
 * 
 * @author LW
 *
 */

public class DistributionAction extends ActionSupport implements ServletResponseAware, ServletRequestAware {
	private static final long serialVersionUID = 1L;
	private DistributionService distributionService;

	public void setDistributionService(DistributionService distributionService) {
		this.distributionService = distributionService;
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

}
