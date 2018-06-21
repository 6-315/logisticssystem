package com.logistics.userinfo.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.logistics.domain.*;
import com.logistics.expressmanagementW.VO.ExpressinfoAndExpressVO;
import com.logistics.loginregister.DTO.UserInfoSessionDTO;
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
	/**
	 * 使用域模型
	 * 
	 */
	private userinfo userInfo;
	private String oldPassword;
	private address addressNew;
	private ExpressinfoAndExpressVO expressinfoAndExpressVO;
	private String search = "";
	private String state = "";
	private int page = 1;

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public ExpressinfoAndExpressVO getExpressinfoAndExpressVO() {
		return expressinfoAndExpressVO;
	}

	public void setExpressinfoAndExpressVO(ExpressinfoAndExpressVO expressinfoAndExpressVO) {
		this.expressinfoAndExpressVO = expressinfoAndExpressVO;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public address getAddressNew() {
		return addressNew;
	}

	public void setAddressNew(address addressNew) {
		this.addressNew = addressNew;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public userinfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(userinfo userInfo) {
		this.userInfo = userInfo;
	}

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
	 * 
	 * @throws IOException
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

	/**
	 * 更改用户信息
	 * 
	 * @throws IOException
	 */
	public void updateUserInfo() throws IOException {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.create();
		response.setContentType("text/html;charset=utf-8");
		UserInfoSessionDTO userInfoSessionDTO = new UserInfoSessionDTO();
		userInfoSessionDTO = userInfoService.updateUserInfo(userInfo);
		request.getSession().setAttribute("userInfoSession", userInfoSessionDTO);
		if (userInfoSessionDTO != null) {
			response.getWriter().write("success");
		} else {
			response.getWriter().write("error");
		}
	}

	/**
	 * 获取一个人的所有地址
	 * 
	 * @throws IOException
	 */
	public void allAddressByUserInfo() throws IOException {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.create();
		response.setContentType("text/html;charset=utf-8");
		HttpSession session = ServletActionContext.getRequest().getSession();
		UserInfoSessionDTO userInfoSessionDTO = (UserInfoSessionDTO) session.getAttribute("userInfoSession");
		List<address> listAddress = new ArrayList<>();
		listAddress = userInfoService.getAllAddress(userInfoSessionDTO);
		response.getWriter().write(gson.toJson(listAddress));
	}

	/**
	 * 修改密码时候，判断旧密码是否一致
	 * 
	 * @throws IOException
	 */
	public void judgePassword() throws IOException {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.create();
		response.setContentType("text/html;charset=utf-8");
		HttpSession session = ServletActionContext.getRequest().getSession();
		UserInfoSessionDTO userInfoSessionDTO = (UserInfoSessionDTO) session.getAttribute("userInfoSession");
		response.getWriter().write("" + userInfoService.judgePassword(oldPassword, userInfoSessionDTO));
	}

	/**
	 * 更新密码
	 * 
	 * @throws IOException
	 */
	public void updatePassword() throws IOException {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.create();
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().write("" + userInfoService.updatePassword(userInfo));
	}

	/**
	 * 添加一条地址
	 * 
	 * @throws IOException
	 */
	public void addAddress() throws IOException { 
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.create();	
		response.setContentType("text/html;charset=utf-8");
		HttpSession session = ServletActionContext.getRequest().getSession();
		UserInfoSessionDTO userInfoSessionDTO = (UserInfoSessionDTO) session.getAttribute("userInfoSession");
		response.getWriter().write("" + userInfoService.addAddress(addressNew, userInfoSessionDTO));

	}

	/**
	 * 根据传回来的域模型userInfo查询用户的历史订单
	 * 
	 * @throws IOException
	 */
	public void selectExpressInfo() throws IOException {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.create();
		response.setContentType("text/html;charset=utf-8");
		expressinfoAndExpressVO = new ExpressinfoAndExpressVO();
		expressinfoAndExpressVO.setPageIndex(page);
		expressinfoAndExpressVO.setSearch(search);
		expressinfoAndExpressVO.setState(state);
		if (userInfo.getUserinfo_id() != null && userInfo.getUserinfo_id().trim().length() > 0) {
			expressinfoAndExpressVO = userInfoService.selectExpressInfo(userInfo.getUserinfo_id(),
					expressinfoAndExpressVO);
		}

		response.getWriter().write(gson.toJson(expressinfoAndExpressVO));

	}
	
}
