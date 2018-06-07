package com.logistics.loginregister.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.logistics.domain.userinfo;
import com.logistics.domain.staff_basicinfo;
import com.logistics.domain.unit;
import com.logistics.loginregister.service.LoginRegisterService;
import com.logistics.personnelmanagement.VO.StaffManagerVO;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 注册登陆公用的Action
 * 
 * @author LW
 *
 */
public class LoginRegisterAction extends ActionSupport implements ServletResponseAware, ServletRequestAware {
	private static final long serialVersionUID = 1L;
	private LoginRegisterService loginRegisterService;

	public void setLginRegisterService(LoginRegisterService lginRegisterService) {
		this.loginRegisterService = lginRegisterService;
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
	private String username;
	private String password;
	private StaffManagerVO staffManagerVO;
	private int pageIndex = 1;
	private String search = "";

	public StaffManagerVO getStaffManagerVO() {
		return staffManagerVO;
	}

	public void setStaffManagerVO(StaffManagerVO staffManagerVO) {
		this.staffManagerVO = staffManagerVO;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

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
		loginRegisterService.addUserifo(userinfo);

	}

	/**
	 * 登陆方法
	 */
	public void login() {
		// 判断username是那一张表
		List<userinfo> listuserInfo = new ArrayList<>();
		List<staff_basicinfo> liststaff_basicinfo = new ArrayList<>();
		listuserInfo = loginRegisterService.getSize(username);
		liststaff_basicinfo = loginRegisterService.getSizeBySat(username);
		if (listuserInfo.size() > 0) {
			userinfo userinfo_session = loginRegisterService.loginByUser(username, password);
			if (userinfo_session != null) {
				request.getSession().setAttribute("userinfo_session", userinfo_session);
				System.out.println("成功！");
			}
		}
		if (liststaff_basicinfo.size() > 0) {
			staff_basicinfo staff_session = loginRegisterService.loginByStaff(username, password);
			if (staff_session != null) {
				request.getSession().setAttribute("staff_session", staff_session);
			}
		}
		System.out.println("失败");
	}

	/**
	 * 人事管理方法，查询1、工号 2、姓名 3、联系方式 4、入职时间 5、职位 6、所属单位7、状态
	 */
	public void staffManager() {
		staffManagerVO = new StaffManagerVO();
		staffManagerVO.setPageIndex(pageIndex);
		staffManagerVO.setSearch(search);
		staffManagerVO = loginRegisterService.getStaffManagerVO(staffManagerVO);

	}

	/**
	 * 获取自身职位以下的所有职位
	 */
	public void getLowerPosition() {
		HttpSession session = ServletActionContext.getRequest().getSession();
		List<unit> listunit  = new ArrayList<>();
		staff_basicinfo staffBasicinfo = (staff_basicinfo) session.getAttribute("staff_session");
		listunit = loginRegisterService.getLowerUnit(staffBasicinfo);

	}
}
