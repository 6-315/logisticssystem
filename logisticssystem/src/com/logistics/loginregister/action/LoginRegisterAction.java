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
import com.logistics.loginregister.DTO.StaffInfomationDTO;
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

	public void setLoginRegisterService(LoginRegisterService loginRegisterService) {
		this.loginRegisterService = loginRegisterService;
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
	private StaffInfomationDTO staffInfomationDTO;

	public StaffInfomationDTO getStaffInfomationDTO() {
		return staffInfomationDTO;
	}

	public void setStaffInfomationDTO(StaffInfomationDTO staffInfomationDTO) {
		this.staffInfomationDTO = staffInfomationDTO;
	}

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
	 * 跳转到用户首页
	 */
	public String pageUser() {
		return "SuccessByUser";
	}

	/**
	 * 跳转到员工首页
	 */
	public String pageStaff() {
		return "SuccessByStaff";
	}

	/**
	 * 登陆方法
	 * 
	 * @throws IOException
	 */
	public void login() throws IOException {
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
					request.getSession().setAttribute("userInfoSession", userInfoSession);
					response.getWriter().write("用户登录成功");
				} else {
					response.getWriter().write("error");
				}
			} else if (listStaffBasicInfo.size() > 0) {
				staff_basicinfo staffSession = loginRegisterService.loginByStaff(username, password);
				if (staffSession != null) {
					position positionNew = new position();
					if (staffSession.getStaff_position() != null
							&& staffSession.getStaff_position().trim().length() > 0) {
						positionNew = loginRegisterService.getPosition(staffSession.getStaff_position());
					}
					staffInfomationDTO.setStaffBasicInfo(staffSession);
					staffInfomationDTO.setStaffPosition(positionNew);
					request.getSession().setAttribute("staffInfomationDTO", staffInfomationDTO);
					response.getWriter().write("员工登录成功");
				} else {
					response.getWriter().write("error");
				}
			} else {
				response.getWriter().write("error");
			}
		}
		response.getWriter().flush();
		response.getWriter().close();
	}

	/**
	 * 获取session中的数据
	 * 
	 * @throws IOException
	 */
	public void getSessionData() throws IOException {
		response.setContentType("text/html;charset=utf-8");
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();
		Gson gson = gsonBuilder.create();
		if (request.getSession().getAttribute("userInfoSession") != null) {
			response.getWriter().write(gson.toJson(request.getSession().getAttribute("userInfoSession")));
		} else if (request.getSession().getAttribute("staffInfomationDTO") != null) {
			response.getWriter().write(gson.toJson(request.getSession().getAttribute("staffInfomationDTO")));
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
