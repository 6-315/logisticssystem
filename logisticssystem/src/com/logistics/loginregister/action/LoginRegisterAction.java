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
import com.logistics.domain.hat_area;
import com.logistics.domain.hat_city;
import com.logistics.domain.hat_province;
import com.logistics.domain.position;
import com.logistics.domain.staff_basicinfo;
import com.logistics.loginregister.DTO.DeliveryAdminStaffDTO;
import com.logistics.loginregister.DTO.DeliveryStaffDTO;
import com.logistics.loginregister.DTO.DriverStaffDTO;
import com.logistics.loginregister.DTO.SuperAdminStaffDTO;
import com.logistics.loginregister.DTO.TeamStaffDTO;
import com.logistics.loginregister.DTO.TransAdminStaffDTO;
import com.logistics.loginregister.DTO.UserInfoSessionDTO;
import com.logistics.loginregister.service.LoginRegisterService;
import com.logistics.personnelmanagement.VO.StaffManagerVO;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 注册登陆公用的Action
 *
 * @author LW
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
	private String cityFatherId;
	// 预约单信息
	private String reversationNum;

	public userinfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(userinfo userInfo) {
		this.userInfo = userInfo;
	}

	public String getReversationNum() {
		return reversationNum;
	}

	public void setReversationNum(String reversationNum) {
		this.reversationNum = reversationNum;
	}

	public String getCityFatherId() {
		return cityFatherId;
	}

	public void setCityFatherId(String cityFatherId) {
		this.cityFatherId = cityFatherId;
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

	/**
	 * 注册方法
	 *
	 * @throws IOException
	 */
	public void register() throws IOException {
		System.out.println("kkkkkkkkkkkkk");
		System.out.println("oooooooo" + userInfo.getUserinfo_password());
		if (userInfo.getUserinfo_phonenumber() != null && userInfo.getUserinfo_password() != null
				&& userInfo.getUserinfo_phonenumber().trim().length() > 0
				&& userInfo.getUserinfo_password().trim().length() > 0) {
			GsonBuilder gsonBuilder = new GsonBuilder();
			gsonBuilder.setPrettyPrinting();// 格式化json数据
			Gson gson = gsonBuilder.create();
			System.out.println("ppppppppppppppp");
			response.setContentType("text/html;charset=utf-8");
			System.out.println("oooooooooooooooooo" + userInfo);
			response.getWriter().write("" + loginRegisterService.addUserifo(userInfo));
		}
	}

	/**
	 * 获取所有省份
	 * 
	 * @author JXX
	 * @throws IOException
	 */
	public void getAllProvince() throws IOException {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.create();
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().write(gson.toJson(loginRegisterService.getAllProvince()));
	}

	/**
	 * 根据省份provinceID获取相应的市
	 * 
	 * @author JXX
	 * @throws IOException
	 */
	public void getAllCityByProvinceID() throws IOException {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.create();
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().write(gson.toJson(loginRegisterService.getAllCityByProvinceID(cityFatherId)));
	}

	/**
	 * 根据市id获取区id
	 * 
	 * @throws IOException
	 */
	public void getAllCountryByCityID() throws IOException {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.create();
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().write(gson.toJson(loginRegisterService.getAllCountryByCityID(cityFatherId)));
	}

	/**
	 * 跳转到用户首页
	 */
	public String pageUser() {
		return "SuccessByUser";
	}

	/**
	 * 跳转到成功页面
	 */
	public String pageReservationSuccess() {
		System.out.println(reversationNum);
		return "pageReservationSuccess";
	}

	/**
	 * 跳转到员工首页
	 */
	public String pageStaff() {
		return "SuccessByStaff";
	}

	/**
	 * 跳转到预约管理页面
	 */
	public String pageReservationManager() {
		return "pageReservationManager";
	}

	/**
	 * 跳转到单位列表
	 * 
	 * @return
	 */
	public String pageUnitList() {
		return "pageUnitList";
	}

	/**
	 * 跳转到路线列表
	 * 
	 * @return
	 */
	public String pageRouteList() {
		return "pageUnitList";
	}

	/**
	 * 跳转到添加单位的页面
	 * 
	 * @return
	 */
	public String pageUnitAdd() {
		return "pageUnitAdd";
	}

	/**
	 * 跳转到员工列表
	 * 
	 * @return
	 */
	public String pageStaffList() {
		return "pageStaffList";
	}

	/**
	 * 跳转到添加员工的界面
	 */
	public String pageStaffAdd() {
		return "pageStaffAdd";
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
					UserInfoSessionDTO userInfoSessionDTO = new UserInfoSessionDTO();
					userInfoSessionDTO.setUserInfoSession(userInfoSession);
					request.getSession().setAttribute("userInfoSession", userInfoSessionDTO);
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
					if (positionNew != null) {
						if (positionNew.getPosition_name() != null
								&& positionNew.getPosition_name().trim().length() > 0) {
							request.getSession().setAttribute("staff_session", staffSession);
							switch (positionNew.getPosition_name().trim()) {
							case "总公司管理员":
								SuperAdminStaffDTO superAdminStaff = new SuperAdminStaffDTO();
								superAdminStaff.setSuperAdminStaff(staffSession);
								request.getSession().setAttribute("superAdminStaff", superAdminStaff);
								response.getWriter().write("总公司管理员登录成功");
								break;
							case "中转站管理员":
								TransAdminStaffDTO transAdminStaff = new TransAdminStaffDTO();
								transAdminStaff.setTransAdminStaff(staffSession);
								request.getSession().setAttribute("transAdminStaff", transAdminStaff);
								response.getWriter().write("中转站管理员登录成功");
								break;
							case "配送点管理员":
								DeliveryAdminStaffDTO deliveryAdminStaff = new DeliveryAdminStaffDTO();
								deliveryAdminStaff.setDeliveryAdminStaff(staffSession);
								request.getSession().setAttribute("deliveryAdminStaff", deliveryAdminStaff);
								response.getWriter().write("配送点管理员登录成功");
								break;
							case "配送员":
								DeliveryStaffDTO deliveryStaff = new DeliveryStaffDTO();
								deliveryStaff.setDeliveryStaff(staffSession);
								request.getSession().setAttribute("deliveryStaff", deliveryStaff);
								response.getWriter().write("配送员登录成功");
								break;
							case "车队队长":
								TeamStaffDTO teamStaff = new TeamStaffDTO();
								teamStaff.setTeamStaff(staffSession);
								request.getSession().setAttribute("teamStaff", teamStaff);
								response.getWriter().write("车队队长登录成功");
								break;
							case "驾驶员":
								DriverStaffDTO driverStaff = new DriverStaffDTO();
								driverStaff.setDriverStaff(staffSession);
								request.getSession().setAttribute("driverStaff", driverStaff);
								response.getWriter().write("驾驶员登录成功");
								break;
							}
						}
					}
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
		} else if (request.getSession().getAttribute("superAdminStaff") != null) {
			response.getWriter().write(gson.toJson(request.getSession().getAttribute("superAdminStaff")));
		} else if (request.getSession().getAttribute("transAdminStaff") != null) {
			response.getWriter().write(gson.toJson(request.getSession().getAttribute("transAdminStaff")));
		} else if (request.getSession().getAttribute("deliveryAdminStaff") != null) {
			response.getWriter().write(gson.toJson(request.getSession().getAttribute("deliveryAdminStaff")));
		} else if (request.getSession().getAttribute("deliveryStaff") != null) {
			response.getWriter().write(gson.toJson(request.getSession().getAttribute("deliveryStaff")));
		} else if (request.getSession().getAttribute("teamStaff") != null) {
			response.getWriter().write(gson.toJson(request.getSession().getAttribute("teamStaff")));
		} else if (request.getSession().getAttribute("driverStaff") != null) {
			response.getWriter().write(gson.toJson(request.getSession().getAttribute("driverStaff")));
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
		request.getSession().setAttribute("superAdminStaff", "");
		request.getSession().setAttribute("transAdminStaff", "");
		request.getSession().setAttribute("deliveryAdminStaff", "");
		request.getSession().setAttribute("teamStaff", "");
		request.getSession().setAttribute("driverStaff", "");
		response.getWriter().write("" + "注销成功");

	}
}
