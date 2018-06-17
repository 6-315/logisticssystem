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
	private ExpressInfoVO expressVO;
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

	public ExpressInfoVO getExpressVO() {
		return expressVO;
	}

	public void setExpressVO(ExpressInfoVO expressVO) {
		this.expressVO = expressVO;
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
		response.getWriter().write("" + expressManagementService.addReservationAndExpressInfo(reservationExpressInfoDTO,
				userInfo.getUserInfoSession()));
	}

	/**
	 * 配送点受理客户预约
	 * 
	 * @throws IOException
	 */
	public void updateReservation() throws IOException {
		GsonBuilder gsonBuilder = new GsonBuilder();
		/**
		 * 格式化json数据
		 */
		gsonBuilder.setPrettyPrinting();
		Gson gson = gsonBuilder.create();
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().write("" + expressManagementService.updateReservation(reservationInfo));
	}

	/**
	 * 给预约单分配派送员
	 * 
	 * @throws IOException
	 */
	public void updateReservationWithDistributor() throws IOException {
		GsonBuilder gsonBuilder = new GsonBuilder();
		/**
		 * 格式化json数据
		 */
		gsonBuilder.setPrettyPrinting();
		Gson gson = gsonBuilder.create();
		response.setContentType("text/html;charset=utf-8");
		response.getWriter()
				.write("" + expressManagementService.updateReservationWithDistributor(reservationWithDistributorDTO));
	}

	/**
	 * 上门取件
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
		response.getWriter().write("" + expressManagementService.completePickExpress(staffInfo));
	}

	/**
	 * 到达中转站（更新快件状态）
	 * 
	 * @throws IOException
	 */
	public void updateExpressState() throws IOException {
		GsonBuilder gsonBuilder = new GsonBuilder();
		/**
		 * 格式化json数据
		 */
		gsonBuilder.setPrettyPrinting();
		Gson gson = gsonBuilder.create();
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().write("" + expressManagementService.updateExpressState(expressInfo));
	}

	/**
	 * 扫描（判断快件类型）
	 * 
	 * @throws IOException
	 */
	@SuppressWarnings("unused")
	private void judgeExpressType() throws IOException {
		GsonBuilder gsonBuilder = new GsonBuilder();
		/**
		 * 格式化json数据
		 */
		gsonBuilder.setPrettyPrinting();
		Gson gson = gsonBuilder.create();
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().write("" + expressManagementService.judgeExpressType(expressInfo));
	}

	/**
	 * 保存快件路线
	 * 
	 * @throws IOException
	 */
	public void saveExpressRoute() throws IOException {
		GsonBuilder gsonBuilder = new GsonBuilder();
		/**
		 * 格式化json数据
		 */
		gsonBuilder.setPrettyPrinting();
		Gson gson = gsonBuilder.create();
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().write("" + expressManagementService.saveExpressRoute(idList, expressInfo));
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
		response.getWriter().write("" + expressManagementService.queryAllRouteWithUnit(unitInfo));
	}

	/**
	 * 完成扫描操作
	 * 
	 * @throws IOException
	 */
	public void updateVehicleAndExpressCirculationAndExpressInfo() throws IOException {
		GsonBuilder gsonBuilder = new GsonBuilder();
		/**
		 * 格式化json数据
		 */
		gsonBuilder.setPrettyPrinting();
		Gson gson = gsonBuilder.create();
		response.setContentType("text/html;charset=utf-8");
		HttpSession session = ServletActionContext.getRequest().getSession();// 获取session
		staff_basicinfo staffInfo = (staff_basicinfo) session.getAttribute("staff_session");
		response.getWriter().write("" + expressManagementService
				.updateVehicleAndExpressCirculationAndExpressInfo(expressInfo, vehicleInfo, staffInfo));
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
		HttpSession session = ServletActionContext.getRequest().getSession();// 获取session
		staff_basicinfo staffInfo = (staff_basicinfo) session.getAttribute("staff_session");
		response.getWriter().write("" + expressManagementService.queryExpressInfo(expressVO, staffInfo));
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
		HttpSession session = ServletActionContext.getRequest().getSession();// 获取session
		staff_basicinfo staffInfo = (staff_basicinfo) session.getAttribute("staff_session");
		response.getWriter().write("" + expressManagementService.queryReservationInfo(reservationVO, staffInfo));

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
		if (userInfo.getUserinfo_id() != null && userInfo.getUserinfo_id().trim().length() > 0) {
			response.getWriter()
					.write("" + expressManagementService.queryOrderHistory(reservationOrderHistoryVO, userInfo));
		} else {
			HttpSession session = ServletActionContext.getRequest().getSession();// 获取session
			UserInfoSessionDTO userInfo = new UserInfoSessionDTO();
			userInfo = (UserInfoSessionDTO) session.getAttribute("userInfoSession");
			response.getWriter().write("" + expressManagementService.queryOrderHistory(reservationOrderHistoryVO,
					userInfo.getUserInfoSession()));
		}

	}
}
