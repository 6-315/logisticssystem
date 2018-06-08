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
	private staff_basicinfo staffBasicinfo;
	private userinfo userInfo;
	private String username;
	private String password;
	private StaffManagerVO staffManagerVO;
	private int pageIndex = 1;
	private String search = "";
	private String staffListIdS = "";

	public String getStaffListIdS() {
		return staffListIdS;
	}

	public staff_basicinfo getStaff_basicinfo() {
		return staffBasicinfo;
	}

	public void setStaff_basicinfo(staff_basicinfo staff_basicinfo) {
		this.staffBasicinfo = staff_basicinfo;
	}

	public void setStaffListIdS(String staffListIdS) {
		this.staffListIdS = staffListIdS;
	}

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
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.create();
		response.getWriter().write("" + loginRegisterService.addUserifo(userInfo));

	}

	/**
	 * 登陆方法
	 * 
	 * @throws IOException
	 */
	public void login() throws IOException {
		// 判断username是那一张表
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.create();
		response.setContentType("text/html;charset=utf-8");
		List<userinfo> listuserInfo = new ArrayList<>();
		List<staff_basicinfo> liststaff_basicinfo = new ArrayList<>();
		listuserInfo = loginRegisterService.getSize(username);
		liststaff_basicinfo = loginRegisterService.getSizeBySat(username);
		if (listuserInfo.size() > 0) {
			userinfo userinfo_session = loginRegisterService.loginByUser(username, password);
			if (userinfo_session != null) {
				request.getSession().setAttribute("userinfo_session", userinfo_session);
				response.getWriter().write("" + userinfo_session);
				System.out.println("成功！");
			}
		}
		if (liststaff_basicinfo.size() > 0) {
			staff_basicinfo staff_session = loginRegisterService.loginByStaff(username, password);
			if (staff_session != null) {
				request.getSession().setAttribute("staff_session", staff_session);
				response.getWriter().write("" + staff_session);
			}
		}
		System.out.println("失败");
	}

	/**
	 * 人事管理方法，查询1、工号 2、姓名 3、联系方式 4、入职时间 5、职位 6、所属单位7、状态
	 * 
	 * @throws IOException
	 */
	public void staffManager() throws IOException {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.create();
		response.setContentType("text/html;charset=utf-8");
		staffManagerVO = new StaffManagerVO();
		staffManagerVO.setPageIndex(pageIndex);
		staffManagerVO.setSearch(search);
		HttpSession session = ServletActionContext.getRequest().getSession();
		staff_basicinfo staffBasicinfo = (staff_basicinfo) session.getAttribute("staff_session");
		if ("77e07c34-735f-45d4-a870-3e5bebe5ddc1".equals(staffBasicinfo.getStaff_position())) {
			staffManagerVO = loginRegisterService.getStaffManagerVO(staffManagerVO);
		} else {
			staffManagerVO = loginRegisterService.getStaffManagerVOByZ(staffManagerVO, staffBasicinfo);
		}
		response.getWriter().write("" + staffManagerVO);
	}

	/**
	 * 获取自身职位以下的所有单位
	 * 
	 * @throws IOException
	 */
	public void lowerUnit() throws IOException {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.create();
		response.setContentType("text/html;charset=utf-8");
		List<unit> listUnit = new ArrayList<>();
		HttpSession session = ServletActionContext.getRequest().getSession();
		staff_basicinfo staffBasicinfo = (staff_basicinfo) session.getAttribute("staff_session");
		listUnit = loginRegisterService.getLowerUnit(staffBasicinfo);
		response.getWriter().write("" + listUnit);

	}

	/**
	 * 获取自身职位一下的所有职位
	 * 
	 * @throws IOException
	 */
	public void lowerPosition() throws IOException {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.create();
		response.setContentType("text/html;charset=utf-8");
		List<position> listPosition = new ArrayList<>();
		HttpSession session = ServletActionContext.getRequest().getSession();// 获取session
		staff_basicinfo staffBasicinfo = (staff_basicinfo) session.getAttribute("staff_session");
		listPosition = loginRegisterService.getLowerPosition(staffBasicinfo);
		response.getWriter().write("" + listPosition);
	}

	/**
	 * 删除员工信息循环删除，因为可全选
	 * 
	 * @throws IOException
	 */
	public void deleteListStaff() throws IOException {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.create();
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().write("" + loginRegisterService.deleteListStaff(staffListIdS));
	}

	/**
	 * 修改员工单位
	 * 
	 * @throws IOException
	 */
	public void updateStaffUnit() throws IOException {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.create();
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().write("" + loginRegisterService.updateStaffUnit(staffBasicinfo));
	}

	/**
	 * 就该员工职位
	 * 
	 * @throws IOException
	 */
	public void updateStaffPosition() throws IOException {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.create();
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().write("" + loginRegisterService.updateStaffPosition(staffBasicinfo));

	}

	/**
	 * 修改员工状态
	 */
	public void updateStaffState() throws IOException {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.create();
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().write("" + loginRegisterService.updateStaffState(staffBasicinfo));
	}

	/**
	 * 添加员工
	 * 
	 * @throws IOException
	 */
	public void addStaff() throws IOException {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.create();
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().write("" + loginRegisterService.addStaff(staffBasicinfo));

	}

}
