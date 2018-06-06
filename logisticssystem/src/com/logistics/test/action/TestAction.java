package com.logistics.test.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.logistics.domain.Testdomain;
import com.logistics.test.service.TestService;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 
 * @author LW
 * zh
 *
 */
/**
 * 
 * @author XTY
 * 这个歌按公司规定个撒旦
 *
 */
public class TestAction extends ActionSupport implements ServletResponseAware, ServletRequestAware {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * service 层注入
	 */
	private TestService testService;
	

	public void setTestService(TestService testService) {
		this.testService = testService;
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

	/**
	 * 使用域模型将News_CarouselInfo对象放置到struts中
	 */
private Testdomain w;
private List<Testdomain> listw;


	public List<Testdomain> getListw() {
	return listw;
}

public void setListw(List<Testdomain> listw) {
	this.listw = listw;
}

	public Testdomain getW() {
		return w;
	}

	public void setW(Testdomain w) {
		this.w = w;
	}
	public String add() {
		System.out.println("______:"+w);
		testService.Add(w);
		
		return "ok";
	}
	public String select() {
		listw = testService.select();
		
		return"";
	}
}
