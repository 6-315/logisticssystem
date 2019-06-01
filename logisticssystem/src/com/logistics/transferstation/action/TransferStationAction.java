package com.logistics.transferstation.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.logistics.domain.driver;
import com.logistics.domain.staff_basicinfo;
import com.logistics.domain.unit;
import com.logistics.domain.vehicle;
import com.logistics.transferstation.DTO.DriverManagerDTO;
import com.logistics.transferstation.VO.UnitManagerVO;
import com.logistics.transferstation.service.TransferStationService;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 中转站管理的Action
 * 
 * @author LW
 *
 */
@SuppressWarnings("serial")
public class TransferStationAction extends ActionSupport implements ServletResponseAware, ServletRequestAware {

	/**
	 * 使用域模型将unit放到Struts中
	 */
	private unit transferStation;
	/**
	 * 使用域模型listunit
	 */
	private List<unit> listunit;
	/**
	 * 使用域模型vo分页
	 * 
	 */
	private UnitManagerVO unitManagerVO;
	/**
	 * 使用域模型将staff_basicinfo放到Struts中
	 */
	private staff_basicinfo staff_BasicInfo;
	/**
	 * 根据Id删除中转站信息
	 */
	private String idList;
	/**
	 * 根据Id查询管理员字段
	 */
	private String admin;
	/**
	 * 分配车辆的集合
	 */
	private String vehicleList;
	/**
	 * 车队编号
	 */
	private String teamNum;
	/**
	 * 司机
	 * 
	 */
	private driver driver;
	/**
	 * 司机的集合
	 * 
	 * @return
	 */
	private String driverList;
	/**
	 * 需要分配司机的车辆
	 */
	private vehicle vehicle;

	private DriverManagerDTO DriverManagerDTO;
	
	private List<driver> listDriver; 
	

	public vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public List<driver> getListDriver() {
		return listDriver;
	}

	public void setListDriver(List<driver> listDriver) {
		this.listDriver = listDriver;
	}

	public DriverManagerDTO getDriverManagerDTO() {
		return DriverManagerDTO;
	}

	public void setDriverManagerDTO(DriverManagerDTO driverManagerDTO) {
		DriverManagerDTO = driverManagerDTO;
	}

	public String getDriverList() {
		return driverList;
	}

	public void setDriverList(String driverList) {
		this.driverList = driverList;
	}

	public driver getDriver() {
		return driver;
	}

	public void setDriver(driver driver) {
		this.driver = driver;
	}

	public String getTeamNum() {
		return teamNum;
	}

	public void setTeamNum(String teamNum) {
		this.teamNum = teamNum;
	}

	/**
	 * 分页查询的字段
	 */
	private String state;
	private String address;
	private String num;
	private String type;
	private String search;
	private int page = 1;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public UnitManagerVO getUnitManagerVO() {
		return unitManagerVO;
	}

	public void setUnitManagerVO(UnitManagerVO unitManagerVO) {
		this.unitManagerVO = unitManagerVO;
	}

	public staff_basicinfo getStaff_basicinfo() {
		return staff_BasicInfo;
	}

	public void setStaff_basicinfo(staff_basicinfo staff_basicinfo) {
		this.staff_BasicInfo = staff_basicinfo;
	}

	public staff_basicinfo getBasicinfo() {
		return staff_BasicInfo;
	}

	public void setBasicinfo(staff_basicinfo basicinfo) {
		this.staff_BasicInfo = basicinfo;
	}

	public staff_basicinfo getStaff_BasicInfo() {
		return staff_BasicInfo;
	}

	public void setStaff_BasicInfo(staff_basicinfo staff_BasicInfo) {
		this.staff_BasicInfo = staff_BasicInfo;
	}

	public String getIdList() {
		return idList;
	}

	public void setIdList(String idList) {
		this.idList = idList;
	}

	public String getVehicleList() {
		return vehicleList;
	}

	public void setVehicleList(String vehicleList) {
		this.vehicleList = vehicleList;
	}

	public String getAdmin() {
		return admin;
	}

	public void setAdmin(String admin) {
		this.admin = admin;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
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

	public List<unit> getListunit() {
		return listunit;
	}

	public void setListunit(List<unit> listunit) {
		this.listunit = listunit;
	}

	/**
	 * service层注入
	 */
	private TransferStationService transferStationService;

	public void setTransferStationService(TransferStationService transferStationService) {
		this.transferStationService = transferStationService;
	}

	/**
	 * 实现request以及response
	 */
	private HttpServletResponse response;

	private HttpServletRequest request;

	/**
	 * get和set
	 * 
	 * @return
	 */

	public unit getTransferStation() {
		return transferStation;
	}

	public void setTransferStation(unit transferStation) {
		this.transferStation = transferStation;
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
	 */
	/**
	 * 中转站管理
	 */
	public void transferStationManager() {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.create();
		response.setContentType("text/html;charset=utf-8");

	}

	/**
	 * 查询中转站
	 * 
	 * @throws IOException
	 */
	public void queryTransferStation() throws IOException {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.serializeNulls().create();
		response.setContentType("text/html;charset=utf-8");
		UnitManagerVO unitManagerVO = new UnitManagerVO();
		unitManagerVO.setSearch(search);
		unitManagerVO.setType(type);
		unitManagerVO.setState(state);
		unitManagerVO.setPageIndex(page);
		HttpSession session = ServletActionContext.getRequest().getSession();
		staff_basicinfo staffBasicInfo = (staff_basicinfo) session.getAttribute("staff_session");
		unitManagerVO = transferStationService.queryTransferStation(unitManagerVO, staffBasicInfo);
		response.getWriter().write(gson.toJson(unitManagerVO));
	}

	/**
	 * 添加中转站
	 * 
	 * @throws IOException
	 */

	public void addTransferStation() throws IOException {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.create();
		response.setContentType("text/html;charset=utf-8");
		HttpSession session = ServletActionContext.getRequest().getSession();
		staff_basicinfo staffBasicInfo = (staff_basicinfo) session.getAttribute("staff_session");
		response.getWriter().write(gson.toJson(transferStationService.addTransferStation(transferStation, staffBasicInfo)));
	}

	/**
	 * 删除中转站
	 * 
	 * @throws IOException
	 */

	public void deleteTransferStation() throws IOException {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.create();
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().write("" + transferStationService.deleteTransferStation(idList));
	}

	/**
	 * 修改中转站信息
	 * 
	 * @throws IOException
	 */

	public void updateTransferStation() throws IOException {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.create();
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().write("" + transferStationService.updateTransferStation(transferStation));
	}

	/**
	 * 分配车辆
	 * 
	 * @throws IOException
	 */
	public void vehicleDistribution() throws IOException {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.create();
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().write("" + transferStationService.vehicleDistribution(vehicleList, teamNum));
	}

	/**
	 * 司机招募
	 * 
	 * @throws IOException
	 */
	public void driverRecruit() throws IOException {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.create();
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().write("" + transferStationService.driverRecruit(null));
	}

	/**
	 * 司机分配
	 * 
	 * @throws IOException
	 */
	public void driverDistribution() throws IOException {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.create();
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().write("" + transferStationService.driverDistribution(driverList, teamNum));
	}

	/**
	 * 得到自身单位以及以下单位信息
	 * 
	 * @throws IOException
	 */
	public void getUnitInfo() throws IOException {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.create();
		response.setContentType("text/html;charset=utf-8");
		HttpSession session = ServletActionContext.getRequest().getSession();
		staff_basicinfo staffBasicInfo = (staff_basicinfo) session.getAttribute("staff_session");
		response.getWriter().write("" + transferStationService.getUnitInfo(staffBasicInfo));

	}
	/**
	 * 得到未分配车辆的司机
	 * @throws IOException
	 */
	public void getDiverUnDistributed() throws IOException {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.create();
		response.setContentType("text/html;charset=utf-8");
		HttpSession session = ServletActionContext.getRequest().getSession();
		staff_basicinfo staffBasicInfo = (staff_basicinfo) session.getAttribute("staff_session");
		response.getWriter().write(gson.toJson(transferStationService.getDiverUnDistributed(DriverManagerDTO,staffBasicInfo)));
		
	}
	/**
	 * 分配司机
	 * @throws IOException
	 */
	public void distributeDiver() throws IOException {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.create();
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().write("" + transferStationService.distributeDiver(vehicle,driver));
	}
	/**
	 * 得到单位管理员
	 * @throws IOException
	 */
	public void getUnitAdmin() throws IOException {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.serializeNulls().create();
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().write(gson.toJson(transferStationService.getUnitAdmin(transferStation)));
	}
	
}
