package com.logistics.vehiclemanagement.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.logistics.domain.vehicle;
import com.logistics.vehiclemanagement.service.VehicleManagementService;
import com.opensymphony.xwork2.ActionSupport;

import util.BuildUuid;
/**
 * 车辆管理Action
 * @author LW
 *
 */
public class VehicleManagementAction extends ActionSupport implements ServletResponseAware, ServletRequestAware {

	private static final long serialVersionUID = 1L;
	private VehicleManagementService vehicleManagementService;
	public void setVehicleManagementService(VehicleManagementService vehicleManagementService) {
		this.vehicleManagementService = vehicleManagementService;
	}
	/**
	 * 
	 */
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
	private vehicle vehicleinfo;
	
	public vehicle getVehicleinfo() {
		return vehicleinfo;
	}

	public void setVehicleinfo(vehicle vehicleinfo) {
		this.vehicleinfo = vehicleinfo;
	}

	/**
	 * 添加车辆
	 * @throws IOException 
	 */
	public void addVehicle() throws IOException {
		
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.create();

		response.setContentType("text/html;charset=utf-8");
		
		vehicleinfo = new vehicle();
		vehicleinfo.setVehicle_platenum("渝G-A9568");
		vehicleinfo.setVehicle_num("1");
		vehicleinfo.setVehicle_state("待分配");
		vehicleinfo.setVehicle_team("Note3");
		vehicleinfo.setVehicle_unit("Note4");
		vehicleinfo.setVehicle_acquisitionpeople("Admin");
		
		
		response.getWriter().write("" + vehicleManagementService.addVehicle(vehicleinfo));
	}
}
