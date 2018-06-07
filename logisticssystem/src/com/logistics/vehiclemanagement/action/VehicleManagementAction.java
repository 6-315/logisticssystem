package com.logistics.vehiclemanagement.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.logistics.domain.vehicle;
import com.logistics.vehiclemanagement.VO.*;
import com.logistics.vehiclemanagement.service.VehicleManagementService;
import com.opensymphony.xwork2.ActionSupport;

import util.BuildUuid;

/**
 * 车辆管理Action
 * 
 * @author LW
 *
 */
@SuppressWarnings("unused")
public class VehicleManagementAction extends ActionSupport implements ServletResponseAware, ServletRequestAware {
	/**
	 * 注入service
	 */
	private static final long serialVersionUID = 1L;
	private VehicleManagementService vehicleManagementService;

	public void setVehicleManagementService(VehicleManagementService vehicleManagementService) {
		this.vehicleManagementService = vehicleManagementService;
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
	 * 车辆信息表
	 */
	private vehicle vehicleinfo;
	/**
	 * 车辆信息VO
	 */
	private vehicleVO vehicleinfoVO;
	/**
	 * 模糊查询关键字
	 */
	private String search = "";
	/**
	 * 根据状态筛选
	 */
	private String state = "";
	/**
	 * 根据所属单位筛选
	 */
	private String unit = "";
	/**
	 * 根据所属车队筛选
	 */
	private String team = "";
	/**
	 * 分页首页
	 */
	private int page = 1;

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

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public vehicle getVehicleinfo() {
		return vehicleinfo;
	}

	public void setVehicleinfo(vehicle vehicleinfo) {
		this.vehicleinfo = vehicleinfo;
	}

	public vehicleVO getVehicleinfoVO() {
		return vehicleinfoVO;
	}

	public void setVehicleinfoVO(vehicleVO vehicleinfoVO) {
		this.vehicleinfoVO = vehicleinfoVO;
	}

	/**
	 * 结束使用域模型
	 */

	/**
	 * 添加车辆
	 * 
	 * @throws IOException
	 */

	public void addVehicle() throws IOException {

		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.create();

		response.setContentType("text/html;charset=utf-8");

		response.getWriter().write("" + vehicleManagementService.addVehicle(vehicleinfo));
	}

	/**
	 * 查询分页车辆
	 * 
	 * @throws IOException
	 */
	public void queryVehicle() throws IOException {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.create();
		response.setContentType("text/html;charset=utf-8");

		vehicleVO vehicleinfoVO = new vehicleVO();
		vehicleinfoVO.setPageIndex(page);
		vehicleinfoVO.setSearch(search);
		vehicleinfoVO.setState(state);
		vehicleinfoVO.setUnit(unit);
		vehicleinfoVO.setTeam(team);
		vehicleinfoVO = vehicleManagementService.queryVehicle(vehicleinfoVO);
		response.getWriter().write("" + vehicleinfoVO);
	}

	/**
	 * 修改车辆信息
	 * 
	 * @throws IOException
	 */
	public void updateVehicle() throws IOException {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.create();
		response.setContentType("text/html;charset=utf-8");

		response.getWriter().write("" + vehicleManagementService.updateVehicle(vehicleinfo));
	}
}
