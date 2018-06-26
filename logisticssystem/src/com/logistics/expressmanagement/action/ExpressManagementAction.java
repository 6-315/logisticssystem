package com.logistics.expressmanagement.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.logistics.domain.*;
import com.logistics.expressmanagement.DTO.*;
import com.logistics.expressmanagement.VO.*;
import com.logistics.expressmanagement.service.ExpressManagementService;
import com.logistics.loginregister.DTO.UserInfoSessionDTO;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 快件管理的Action
 * 
 * @author LW
 *
 */
public class ExpressManagementAction extends ActionSupport implements ServletResponseAware, ServletRequestAware {
	private static final long serialVersionUID = 1L;
	/**
	 * service层注入
	 */
	private ExpressManagementService expressManagementService;

	public void setExpressManagementService(ExpressManagementService expressManagementService) {
		this.expressManagementService = expressManagementService;
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
	/**
	 * 预约表与快件详细信息表DTO
	 */
	private ReservationExpressInfoDTO reservationExpressInfoDTO;
	/**
	 * 预约表与派送员表DTO
	 */
	private ReservationWithDistributorDTO reservationWithDistributorDTO;
	/**
	 * 快件表与流转表DTO
	 */
	private ExpressAndCirculationDTO expressAndCirculationDTO;
	/**
	 * 预约表
	 */
	private reservation reservationInfo;
	/**
	 * 快件表
	 */
	private express expressInfo;
	/**
	 * 长字符串（ID带分隔符集合）
	 */
	private String idList;
	/**
	 * 单位表
	 */
	private unit unitInfo;
	/**
	 * 车辆信息表
	 */
	private vehicle vehicleInfo;
	/**
	 * 快件列表
	 */
	private ExpressInfoVO expressInfoVO;
	/**
	 * 预约列表
	 */
	private ReservationVO reservationVO;
	/**
	 * 用户表
	 */
	private userinfo userInfo;
	/**
	 * 历史预约单VO
	 */
	private ReservationOrderHistoryVO reservationOrderHistoryVO;
	/**
	 * 状态
	 */
	private String state = "";
	/**
	 * 单位
	 */
	private String unit = "";
	/**
	 * 关键词
	 */
	private String search = "";
	/**
	 * 当前页
	 */
	private int page = 1;
	/**
	 * 配送员
	 */
	private distributiontor distributor;
	/**
	 * 根据是否分配进行筛选
	 */
	private String isDistributed = "";
	/**
	 * 是否分配配送点
	 */
	private String isDistributedDistribution = "";
	/**
	 * 是否分配配送员
	 */
	private String isDistributedDistributor = "";
	/**
	 * 方向
	 */
	private String id_directionList;

	public String getId_directionList() {
		return id_directionList;
	}

	public void setId_directionList(String id_directionList) {
		this.id_directionList = id_directionList;
	}

	public ExpressInfoVO getExpressInfoVO() {
		return expressInfoVO;
	}

	public void setExpressInfoVO(ExpressInfoVO expressInfoVO) {
		this.expressInfoVO = expressInfoVO;
	}

	public String getIsDistributedDistribution() {
		return isDistributedDistribution;
	}

	public void setIsDistributedDistribution(String isDistributedDistribution) {
		this.isDistributedDistribution = isDistributedDistribution;
	}

	public String getIsDistributedDistributor() {
		return isDistributedDistributor;
	}

	public void setIsDistributedDistributor(String isDistributedDistributor) {
		this.isDistributedDistributor = isDistributedDistributor;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public String getIsDistributed() {
		return isDistributed;
	}

	public void setIsDistributed(String isDistributed) {
		this.isDistributed = isDistributed;
	}

	public distributiontor getDistributor() {
		return distributor;
	}

	public void setDistributor(distributiontor distributor) {
		this.distributor = distributor;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public ReservationOrderHistoryVO getReservationOrderHistoryVO() {
		return reservationOrderHistoryVO;
	}

	public void setReservationOrderHistoryVO(ReservationOrderHistoryVO reservationOrderHistoryVO) {
		this.reservationOrderHistoryVO = reservationOrderHistoryVO;
	}

	public userinfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(userinfo userInfo) {
		this.userInfo = userInfo;
	}

	public ReservationVO getReservationVO() {
		return reservationVO;
	}

	public void setReservationVO(ReservationVO reservationVO) {
		this.reservationVO = reservationVO;
	}

	public vehicle getVehicleInfo() {
		return vehicleInfo;
	}

	public void setVehicleInfo(vehicle vehicleInfo) {
		this.vehicleInfo = vehicleInfo;
	}

	public unit getUnitInfo() {
		return unitInfo;
	}

	public void setUnitInfo(unit unitInfo) {
		this.unitInfo = unitInfo;
	}

	public String getIdList() {
		return idList;
	}

	public void setIdList(String idList) {
		this.idList = idList;
	}

	public express getExpressInfo() {
		return expressInfo;
	}

	public void setExpressInfo(express expressInfo) {
		this.expressInfo = expressInfo;
	}

	public ExpressAndCirculationDTO getExpressAndCirculationDTO() {
		return expressAndCirculationDTO;
	}

	public void setExpressAndCirculationDTO(ExpressAndCirculationDTO expressAndCirculationDTO) {
		this.expressAndCirculationDTO = expressAndCirculationDTO;
	}

	public ReservationWithDistributorDTO getReservationWithDistributorDTO() {
		return reservationWithDistributorDTO;
	}

	public void setReservationWithDistributorDTO(ReservationWithDistributorDTO reservationWithDistributorDTO) {
		this.reservationWithDistributorDTO = reservationWithDistributorDTO;
	}

	public reservation getReservationInfo() {
		return reservationInfo;
	}

	public void setReservationInfo(reservation reservationInfo) {
		this.reservationInfo = reservationInfo;
	}

	public ReservationExpressInfoDTO getReservationExpressInfoDTO() {
		return reservationExpressInfoDTO;
	}

	public void setReservationExpressInfoDTO(ReservationExpressInfoDTO reservationExpressInfoDTO) {
		this.reservationExpressInfoDTO = reservationExpressInfoDTO;
	}

	/**
	 * 结束使用域模型
	 */

	/**
	 * 用户开始预约
	 * 
	 * @throws IOException
	 */
	public void addReservationAndExpressInfo() throws IOException {
		GsonBuilder gsonBuilder = new GsonBuilder();
		/**
		 * 格式化json数据
		 */
		gsonBuilder.setPrettyPrinting();
		Gson gson = gsonBuilder.create();
		response.setContentType("text/html;charset=utf-8");
		HttpSession session = ServletActionContext.getRequest().getSession();// 获取session
		UserInfoSessionDTO userInfo = new UserInfoSessionDTO();
		userInfo = (UserInfoSessionDTO) session.getAttribute("userInfoSession");
		System.out.println("fdfd:" + reservationExpressInfoDTO);
		response.getWriter().write(gson.toJson(expressManagementService
				.addReservationAndExpressInfo(reservationExpressInfoDTO, userInfo.getUserInfoSession())));
	}

	/**
	 * 配送点受理客户预约
	 * 
	 * @throws IOException
	 */
	public void updateReservation() throws IOException {
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().write("" + expressManagementService.updateReservation(idList, state));
	}

	/**
	 * 给预约单分配派送员
	 * 
	 * @throws IOException
	 */
	public void updateReservationWithDistributor() throws IOException {
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().write("" + expressManagementService.updateReservationWithDistributor(idList, distributor));
	}

	/**
	 * 生成快件表和流转表
	 * 
	 * @throws IOException
	 */
	public void completePickExpress() throws IOException {
		GsonBuilder gsonBuilder = new GsonBuilder();
		/**
		 * 格式化json数据
		 */
		gsonBuilder.setPrettyPrinting();
		Gson gson = gsonBuilder.create();
		response.setContentType("text/html;charset=utf-8");
		HttpSession session = ServletActionContext.getRequest().getSession();// 获取session
		staff_basicinfo staffInfo = (staff_basicinfo) session.getAttribute("staff_session");
		response.getWriter()
				.write(gson.toJson(expressManagementService.completePickExpress(expressAndCirculationDTO, staffInfo)));
	}

	/**
	 * 到达中转站（更新快件状态）
	 * 
	 * @throws IOException
	 */
	/*
	 * public void updateExpressState() throws IOException {
	 * response.setContentType("text/html;charset=utf-8");
	 * response.getWriter().write("" +
	 * expressManagementService.updateExpressState(expressInfo)); }
	 */

	/**
	 * 扫描（判断快件类型）
	 * 
	 * @throws IOException
	 */
	@SuppressWarnings("unused")
	public void judgeExpressType() throws IOException {
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().write("" + expressManagementService.judgeExpressType(expressInfo));
	}

	/**
	 * 保存快件路线
	 * 
	 * @throws IOException
	 */
	public void saveExpressRoute() throws IOException {
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().write("" + expressManagementService.saveExpressRoute(id_directionList,expressInfo));
	}

	/**
	 * 查询经过该中转站的所有路线
	 * 
	 * @throws IOException
	 */
	public void queryAllRouteWithUnit() throws IOException {
		GsonBuilder gsonBuilder = new GsonBuilder();
		/**
		 * 格式化json数据
		 */
		gsonBuilder.setPrettyPrinting();
		Gson gson = gsonBuilder.create();
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().write(gson.toJson(expressManagementService.queryAllRouteWithUnit(unitInfo)));
	}

	/**
	 * 完成扫描操作
	 * 
	 * @throws IOException
	 */
	public void updateVehicleAndExpressCirculationAndExpressInfo() throws IOException {
		response.setContentType("text/html;charset=utf-8");
		HttpSession session = ServletActionContext.getRequest().getSession();// 获取session
		staff_basicinfo staffInfo = (staff_basicinfo) session.getAttribute("staff_session");
		response.getWriter().write("" + expressManagementService
				.updateVehicleAndExpressCirculationAndExpressInfo(expressAndCirculationDTO, staffInfo));
	}

	/**
	 * 获得快件信息列表
	 * 
	 * @throws IOException
	 */
	public void queryExpressInfo() throws IOException {
		GsonBuilder gsonBuilder = new GsonBuilder();
		/**
		 * 格式化json数据
		 */
		gsonBuilder.setPrettyPrinting();
		Gson gson = gsonBuilder.create();
		response.setContentType("text/html;charset=utf-8");
		ExpressInfoVO expressInfoVO = new ExpressInfoVO();
		expressInfoVO.setPageIndex(page);
		expressInfoVO.setSearch(search);
		expressInfoVO.setState(state);
		expressInfoVO.setUnit(unit);
		expressInfoVO.setIsDistributedDistribution(isDistributedDistribution);
		expressInfoVO.setIsDistributedDistributor(isDistributedDistributor);
		HttpSession session = ServletActionContext.getRequest().getSession();// 获取session
		staff_basicinfo staffInfo = (staff_basicinfo) session.getAttribute("staff_session");
		response.getWriter().write(gson.toJson(expressManagementService.queryExpressInfo(expressInfoVO, staffInfo)));
	}

	/**
	 * 获得预约列表
	 * 
	 * @throws IOException
	 */
	public void queryReservationInfo() throws IOException {
		GsonBuilder gsonBuilder = new GsonBuilder();
		/**
		 * 格式化json数据
		 */
		gsonBuilder.setPrettyPrinting();
		Gson gson = gsonBuilder.create();
		response.setContentType("text/html;charset=utf-8");
		ReservationVO reservationVO = new ReservationVO();
		reservationVO.setPageIndex(page);
		reservationVO.setSearch(search);
		reservationVO.setState(state);
		reservationVO.setUnit(unit);
		reservationVO.setIsDistributed(isDistributed);
		HttpSession session = ServletActionContext.getRequest().getSession();// 获取session
		staff_basicinfo staffInfo = (staff_basicinfo) session.getAttribute("staff_session");
		response.getWriter()
				.write(gson.toJson(expressManagementService.queryReservationInfo(reservationVO, staffInfo)));

	}

	/**
	 * 查看用户历史订单
	 * 
	 * @throws IOException
	 */
	public void queryOrderHistory() throws IOException {
		GsonBuilder gsonBuilder = new GsonBuilder();
		/**
		 * 格式化json数据
		 */
		gsonBuilder.setPrettyPrinting();
		Gson gson = gsonBuilder.create();
		response.setContentType("text/html;charset=utf-8");
		ReservationOrderHistoryVO reservationOrderHistoryVO = new ReservationOrderHistoryVO();
		reservationOrderHistoryVO.setPageIndex(page);
		reservationOrderHistoryVO.setSearch(search);
		reservationOrderHistoryVO.setState(state);
		if (userInfo.getUserinfo_id() != null && userInfo.getUserinfo_id().trim().length() > 0) {
			response.getWriter()
					.write("" + expressManagementService.queryOrderHistory(reservationOrderHistoryVO, userInfo));
		} else {
			HttpSession session = ServletActionContext.getRequest().getSession();// 获取session
			UserInfoSessionDTO userInfo = new UserInfoSessionDTO();
			userInfo = (UserInfoSessionDTO) session.getAttribute("userInfoSession");
			response.getWriter().write(gson.toJson(expressManagementService.queryOrderHistory(reservationOrderHistoryVO,
					userInfo.getUserInfoSession())));
		}

	}

	/**
	 * 用户查看自己的预约单
	 * 
	 * @throws IOException
	 */
	public void queryUserReservation() throws IOException {
		GsonBuilder gsonBuilder = new GsonBuilder();
		/**
		 * 格式化json数据
		 */
		gsonBuilder.setPrettyPrinting();
		Gson gson = gsonBuilder.create();
		response.setContentType("text/html;charset=utf-8");
		HttpSession session = ServletActionContext.getRequest().getSession();// 获取session
		UserInfoSessionDTO userInfo = new UserInfoSessionDTO();
		userInfo = (UserInfoSessionDTO) session.getAttribute("userInfoSession");
		response.getWriter().write(gson.toJson(expressManagementService.queryUserReservation(userInfo.getUserInfoSession(), state)));
	}

	/**
	 * 取消预约单
	 * 
	 * @throws IOException
	 */
	public void cancelReservation() throws IOException {
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().write("" + expressManagementService.cancelReservation(reservationInfo));
	}

	/**
	 * 修改预约单
	 * 
	 * @throws IOException
	 */
	public void updateReservationInfo() throws IOException {
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().write("" + expressManagementService.updateReservationInfo(reservationExpressInfoDTO));

	}

	/**
	 * 重定向到action
	 */
	public String skipPage() {
		return "add";
	}

	/*
	 * 跳转到添加快件的页面
	 */
	public String pageAddExpress() {
		return "pageAddExpress";
	}

	/**
	 * 重定向到action
	 */
	public String addUnitPage() {
		return "addUnit";
	}
	/**
	 * 跳转到添加单位页面
	 */
	public String pageAddUnit(){
		return "pageAddUnit";
	}
	/**
	 * 重定向到action
	 */
	public String addStaffPage() {
		return "addStaff";
	}
	/**
	 * 跳转到添加员工页面
	 */
	public String pageAddStaff(){
		return "pageAddStaff";
	}
	/**
	 * 重定向到action
	 */
	public String addRoutePage() {
		return "addRoute";
	}
	/**
	 * 跳转到添加路线页面
	 */
	public String pageAddRoute(){
		return "pageAddRoute";
	}
	/**
	 * 重定向到action
	 */
	public String addVehiclePage() {
		return "addVehcile";
	}
	/**
	 * 跳转到添加路线页面
	 */
	public String pageAddVehcile(){
		return "pageAddVehcile";
	}
	
	/**
	 * 查看当前预约单信息
	 * 
	 * @throws IOException
	 */
	public void queryCurrentReservationInfo() throws IOException {
		GsonBuilder gsonBuilder = new GsonBuilder();
		/**
		 * 格式化json数据
		 */
		gsonBuilder.setPrettyPrinting();
		Gson gson = gsonBuilder.create();
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().write(gson.toJson(expressManagementService.queryCurrentReservationInfo(idList)));
	}

	/**
	 * 获得路线信息
	 * @throws IOException 
	 */
	public void getRouteInfo() throws IOException {
		GsonBuilder gsonBuilder = new GsonBuilder();
		/**
		 * 格式化json数据
		 */
		gsonBuilder.setPrettyPrinting();
		Gson gson = gsonBuilder.create();
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().write(gson.toJson(expressManagementService.getRouteInfo(idList)));
		
	}
	
}
