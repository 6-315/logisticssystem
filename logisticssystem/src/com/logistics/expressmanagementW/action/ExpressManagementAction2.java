package com.logistics.expressmanagementW.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.logistics.expressmanagementW.DTO.DistributiontorAndStaffBasicinfoDTO;
import com.logistics.expressmanagementW.DTO.ExpressCirculationAndUnitDTO;
import com.logistics.expressmanagementW.DTO.GetExpressAndDispatcherDTO;
import com.logistics.expressmanagementW.DTO.GetWeightDTO;
import com.logistics.expressmanagementW.service.ExpressManagementService2;
import com.opensymphony.xwork2.ActionSupport;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.logistics.domain.*;

/**
 * 快件管理的Action
 * 
 * @author LW
 *
 */
public class ExpressManagementAction2 extends ActionSupport implements ServletResponseAware, ServletRequestAware {
	private static final long serialVersionUID = 1L;
	/**
	 * service层注入
	 */
	private ExpressManagementService2 expressManagementService2;

	public void setExpressManagementService2(ExpressManagementService2 expressManagementService2) {
		this.expressManagementService2 = expressManagementService2;
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
	 * 域模型加set\get
	 */
	private express expressNew;
	private GetWeightDTO getWeightDTO;
	private GetExpressAndDispatcherDTO getExpressAndDispatcherDTO;
	private String address;
	private unit unitNew;
	private String listExpressId;

	public String getListExpressId() {
		return listExpressId;
	}

	public void setListExpressId(String listExpressId) {
		this.listExpressId = listExpressId;
	}

	public unit getUnitNew() {
		return unitNew;
	}

	public void setUnitNew(unit unitNew) {
		this.unitNew = unitNew;
	}

	public GetExpressAndDispatcherDTO getGetExpressAndDispatcherDTO() {
		return getExpressAndDispatcherDTO;
	}

	public void setGetExpressAndDispatcherDTO(GetExpressAndDispatcherDTO getExpressAndDispatcherDTO) {
		this.getExpressAndDispatcherDTO = getExpressAndDispatcherDTO;
	}

	public GetWeightDTO getGetWeightDTO() {
		return getWeightDTO;
	}

	public void setGetWeightDTO(GetWeightDTO getWeightDTO) {
		this.getWeightDTO = getWeightDTO;
	}

	public express getExpressNew() {
		return expressNew;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setExpressNew(express expressNew) {
		this.expressNew = expressNew;
	}

	/**
	 * 结束
	 */

	/**
	 * 根据快件ID获取当前路线的所有车辆 有快件对象ID
	 * 
	 * @throws IOException
	 */
	public void getVehicleByID() throws IOException {
		List<vehicle> listVehicle = new ArrayList<>();
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.create();
		response.setContentType("text/html;charset=utf-8");
		listVehicle = expressManagementService2.getVehicleByID(expressNew);
		response.getWriter().write(gson.toJson(listVehicle));

	}

	/**
	 * 判断车的状态来更改车的状态判断是否超重生成流转单更改快件状态生成车和快件信息表记录
	 * 
	 * @throws IOException
	 */
	public void getVehicleIsOverWeight() throws IOException {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.create();
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().write("" + expressManagementService2.judgeVehicleIsOverWeight(getWeightDTO));

	}

	/**
	 * 根据地址获取所有配送点
	 * 
	 */
	public void getAddressByUnit() throws IOException {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.create();
		response.setContentType("text/html;charset=utf-8");
		List<unit> listUnit = new ArrayList<>();
		listUnit = expressManagementService2.getAddressByUnit(address);
		response.getWriter().write(gson.toJson(listUnit));
	}

	/**
	 * 快件详情(流转信息)
	 * 
	 * @throws IOException
	 */
	public void getExpressCirculation() throws IOException {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.create();
		response.setContentType("text/html;charset=utf-8");
		List<ExpressCirculationAndUnitDTO> listExpressCirculationAndUnitDTO = new ArrayList<>();
		listExpressCirculationAndUnitDTO = expressManagementService2.getExpressCirculation(expressNew);
		response.getWriter().write(gson.toJson(listExpressCirculationAndUnitDTO));
	}

	/**
	 * 获取配送员根据自身session
	 * 
	 * @throws IOException
	 */
	public void getDispatcher() throws IOException {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.create();
		response.setContentType("text/html;charset=utf-8");
		List<DistributiontorAndStaffBasicinfoDTO> listDistributiontorAndStaffBasicinfoDTO = new ArrayList<>();
		HttpSession session = ServletActionContext.getRequest().getSession();
		staff_basicinfo staffBasicinfo = (staff_basicinfo) session.getAttribute("staff_session");
		listDistributiontorAndStaffBasicinfoDTO = expressManagementService2.getDispatcher(staffBasicinfo);
		response.getWriter().write(gson.toJson(listDistributiontorAndStaffBasicinfoDTO));
	}

	/**
	 * 
	 * 分配快件给配送员，更改快件记录，生成快件派送表
	 * 
	 * @throws IOException
	 */
	public void updateExpressState() throws IOException {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.create();
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().write("" + expressManagementService2.updateExpressState(getExpressAndDispatcherDTO));
	}

	/**
	 * 完成派送记录，更改快件记录
	 * 
	 * @throws IOException
	 */
	public void updateExpressSendState() throws IOException {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.create();
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().write("" + expressManagementService2.updateExpressSendState(expressNew));

	}

	/**
	 * 配送员修改快件状态
	 */
	public void updateExpressByDistributiontor() throws IOException {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.create();
		response.setContentType("text/html;charset=utf-8");
		HttpSession session = ServletActionContext.getRequest().getSession();
		staff_basicinfo staffBasicinfo = (staff_basicinfo) session.getAttribute("staff_session");
		response.getWriter()
				.write("" + expressManagementService2.updateExpressByDistributiontor(staffBasicinfo, expressNew));

	}

	/***
	 * 中转站选择配送点
	 * 
	 * @throws IOException
	 */
	public void chooseDistribution() throws IOException {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.create();
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().write("" + expressManagementService2.chooseDistribution(expressNew, unitNew));

	}

	/**
	 * 更改快件未扫描
	 * 
	 * @throws IOException
	 */
	public void updateNotScan() throws IOException {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.create();
		response.setContentType("text/html;charset=utf-8");
		HttpSession session = ServletActionContext.getRequest().getSession();
		staff_basicinfo staffBasicinfo = (staff_basicinfo) session.getAttribute("staff_session");

		response.getWriter().write("" + expressManagementService2.updateNotScan(listExpressId, staffBasicinfo));

	}

	/**
	 * 根据session查询所有配送点
	 * @throws IOException 
	 */
	public void getDistributionBySession() throws IOException {

		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.create();
		response.setContentType("text/html;charset=utf-8");
		HttpSession session = ServletActionContext.getRequest().getSession();
		staff_basicinfo staffBasicinfo = (staff_basicinfo) session.getAttribute("staff_session");
		List<unit> listDistribution = new ArrayList<>();
		listDistribution = expressManagementService2.getDistributionBySession(staffBasicinfo);

		response.getWriter().write(gson.toJson(listDistribution));

	}
}
