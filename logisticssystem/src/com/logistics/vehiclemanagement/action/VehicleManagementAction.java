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
	private vehicle vehicleInfo;
	/**
	 * 车辆信息VO
	 */
	private VehicleVO vehicleInfoVO;
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
	/**
	 * 根据id批量删除
	 */
	private String idList = "";
	
	public vehicle getVehicleInfo() {
		return vehicleInfo;
	}

	public void setVehicleInfo(vehicle vehicleInfo) {
		this.vehicleInfo = vehicleInfo;
	}

	public VehicleVO getVehicleInfoVO() {
		return vehicleInfoVO;
	}

	public void setVehicleInfoVO(VehicleVO vehicleInfoVO) {
		this.vehicleInfoVO = vehicleInfoVO;
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

	public String getIdList() {
		return idList;
	}

	public void setIdList(String idList) {
		this.idList = idList;
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
		/**
		 *  格式化json数据
		 */
		gsonBuilder.setPrettyPrinting();
		Gson gson = gsonBuilder.create();

		response.setContentType("text/html;charset=utf-8");

		response.getWriter().write("" + vehicleManagementService.addVehicle(vehicleInfo));
	}

	/**
	 * 查询分页车辆
	 * 
	 * @throws IOException
	 */
	public void queryVehicle() throws IOException {
		GsonBuilder gsonBuilder = new GsonBuilder();
		/**
		 *  格式化json数据
		 */
		gsonBuilder.setPrettyPrinting();
		Gson gson = gsonBuilder.create();
		response.setContentType("text/html;charset=utf-8");

		/**
		 * 将从前台传回来的数据放入VO中进行传输
		 */
		VehicleVO vehicleInfoVO = new VehicleVO();
		vehicleInfoVO.setPageIndex(page);
		vehicleInfoVO.setSearch(search);
		vehicleInfoVO.setState(state);
		vehicleInfoVO.setUnit(unit);
		vehicleInfoVO.setTeam(team);
		vehicleInfoVO = vehicleManagementService.queryVehicle(vehicleInfoVO);
		response.getWriter().write("" + vehicleInfoVO);
	}

	/**
	 * 修改车辆信息
	 * 
	 * @throws IOException
	 */
	public void updateVehicle() throws IOException {
		GsonBuilder gsonBuilder = new GsonBuilder();
		/**
		 *  格式化json数据
		 */
		gsonBuilder.setPrettyPrinting();
		Gson gson = gsonBuilder.create();
		response.setContentType("text/html;charset=utf-8");

		response.getWriter().write("" + vehicleManagementService.updateVehicle(vehicleInfo));
	}
	
	/**
	 * 批量删除车辆
	 */
	public void deleteVehicle(){
		/**
		 * 将从前台传回来的数据放入VO中传输
		 */
		VehicleVO vehicleInfoVO = new VehicleVO();
		vehicleInfoVO.setIdList(idList);
		vehicleManagementService.deleteVehicle(vehicleInfoVO);
	}
}
