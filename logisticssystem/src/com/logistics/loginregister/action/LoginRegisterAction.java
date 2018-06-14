package com.logistics.loginregister.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.logistics.domain.userinfo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.logistics.domain.position;
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
	private staff_basicinfo staffBasicInfo;
	private userinfo userInfo;
	private String username;
	private String password;
	private StaffManagerVO staffManagerVO;
	private String type;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public staff_basicinfo getStaff_basicinfo() {
		return staffBasicInfo;
	}

	public void setStaff_basicinfo(staff_basicinfo staff_basicinfo) {
		this.staffBasicInfo = staff_basicinfo;
	}

	public StaffManagerVO getStaffManagerVO() {
		return staffManagerVO;
	}

	public void setStaffManagerVO(StaffManagerVO staffManagerVO) {
		this.staffManagerVO = staffManagerVO;
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
		return userInfo;
	}

	public void setUserinfo(userinfo userinfo) {
		this.userInfo = userinfo;
	}

	/**
	 * 注册方法
	 * 
	 * @throws IOException
	 */
	public void register() throws IOException {
		if (userInfo.getUserinfo_phonenumber() != null && userInfo.getUserinfo_password() != null
				&& userInfo.getUserinfo_phonenumber().trim().length() > 0
				&& userInfo.getUserinfo_password().trim().length() > 0) {
			GsonBuilder gsonBuilder = new GsonBuilder();
			gsonBuilder.setPrettyPrinting();// 格式化json数据
			Gson gson = gsonBuilder.create();
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().write("" + loginRegisterService.addUserifo(userInfo));
		}
	}
	/**
	 * 登陆方法
	 * 
	 * @throws IOException
	 */
	public void login() throws IOException {
		System.out.println("88888888888888888888888888");
		// 判断username是那一张表
		if (username != null && username.trim().length() > 0 && password != null && password.trim().length() > 0) {
			GsonBuilder gsonBuilder = new GsonBuilder();
			gsonBuilder.setPrettyPrinting();// 格式化json数据
			Gson gson = gsonBuilder.create();
			response.setContentType("text/html;charset=utf-8");
			List<userinfo> listUserInfo = new ArrayList<>();
			List<staff_basicinfo> listStaffBasicInfo = new ArrayList<>();
			listUserInfo = loginRegisterService.getSize(username);
			listStaffBasicInfo = loginRegisterService.getSizeBySat(username);
			if (listUserInfo.size() > 0) {
				userinfo userInfoSession = loginRegisterService.loginByUser(username, password);
				if (userInfoSession != null) {
					type = "用户";
					response.getWriter().write(""+"success");
					request.getSession().setAttribute("type", type);
					request.getSession().setAttribute("userInfoSession", userInfoSession);
					response.getWriter().write(gson.toJson(userInfoSession));
					System.out.println("成功！");
				}
				response.getWriter().write(""+"error");
			}
			if (listStaffBasicInfo.size() > 0) {
				staff_basicinfo staffSession = loginRegisterService.loginByStaff(username, password);
				if (staffSession != null) {
					response.getWriter().write(""+"success");
					type = "员工";
					request.getSession().setAttribute("type", type);
					request.getSession().setAttribute("staff_session", staffSession);
					response.getWriter().write(gson.toJson(staffSession));
				}
			}
			response.getWriter().write(""+"error");
			System.out.println("失败");
		}

	}

	/**
	 * 注销方法
	 * 
	 * @throws IOException
	 */
	public void logoff() throws IOException {
		HttpSession session = ServletActionContext.getRequest().getSession();
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.create();
		response.setContentType("text/html;charset=utf-8");
		staff_basicinfo staffBasicinfo = (staff_basicinfo) session.getAttribute("staff_session");
		userinfo userInfoSession = (userinfo) session.getAttribute("userInfoSession");
		request.getSession().setAttribute("type", "");
		request.getSession().setAttribute("staff_session", "");
		request.getSession().setAttribute("userInfoSession", "");
		response.getWriter().write("" + "注销成功");

	}
}
