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

import com.logistics.expressmanagementW.DTO.GetExpressAndDispatcher;
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
	private GetExpressAndDispatcher getExpressAndDispatcher;

	public GetExpressAndDispatcher getGetExpressAndDispatcher() {
		return getExpressAndDispatcher;
	}

	public void setGetExpressAndDispatcher(GetExpressAndDispatcher getExpressAndDispatcher) {
		this.getExpressAndDispatcher = getExpressAndDispatcher;
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
		response.getWriter().write("" + expressManagementService2.getVehicleIsOverWeight(getWeightDTO));

	}

	/**
	 * 根据地址获取所有配送点
	 */
	public void getAddressByUnit() throws IOException {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.create();
		response.setContentType("text/html;charset=utf-8");
		List<unit> listUnit = new ArrayList<>();
		listUnit = expressManagementService2.getAddressByUnit(expressNew);
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
		List<express_circulation> listExpressCirculation = new ArrayList<>();
		listExpressCirculation = expressManagementService2.getExpressCirculation(expressNew);
		response.getWriter().write(gson.toJson(listExpressCirculation));
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
		List<staff_basicinfo> liststaffBasicInfo = new ArrayList<>();
		HttpSession session = ServletActionContext.getRequest().getSession();
		staff_basicinfo staffBasicinfo = (staff_basicinfo) session.getAttribute("staff_session");
		liststaffBasicInfo = expressManagementService2.getDispatcher(staffBasicinfo);
		response.getWriter().write(gson.toJson(liststaffBasicInfo));

	}

	/**
	 * 分配给生成快件配送表记录 更改快件状态，完成流转记录
	 * 
	 * @throws IOException
	 */
	public void updateExpressState() throws IOException {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.create();
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().write("" + expressManagementService2.updateExpressState(getExpressAndDispatcher));

	}
}
