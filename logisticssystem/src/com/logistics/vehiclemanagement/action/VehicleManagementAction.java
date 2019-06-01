package com.logistics.vehiclemanagement.action;

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
import com.logistics.domain.*;
import com.logistics.vehiclemanagement.DTO.ManagerDTO;
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
	 * 车队信息
	 */
	private team teamInfo;
	/**
	 * 车辆信息VO
	 */
	private VehicleVO vehicleInfoVO;
	/**
	 * 车队信息VO
	 */
	private TeamVO teamInfoVO;
	/**
	 * 模糊查询关键字
	 */
	private String search = "";
	/**
	 * 根据状态筛选车辆
	 */
	private String state = "";
	/**
	 * 根据所属单位筛选车辆、车队
	 */
	private String unit = "";
	/**
	 * 根据所属车队筛选车辆
	 */
	private String team = "";
	/**
	 * 根据队长筛选车队
	 */
	private String teamLeader = "";
	/**
	 * 分页首页
	 */
	private int page = 1;
	/**
	 * 根据id批量删除
	 */
	private String idList = "";
	/**
	 * 车辆流转表
	 */
	private vehiclecirculation vehicleCirculation;
	/**
	 * 职位名称
	 */
	private String position = "";
	/**
	 * 根据分配状态筛选
	 */
	private String distributionState = "";
	/**
	 * 根据载货状态筛选
	 */
	private String expressState = "";
	/**
	 * 管理员信息
	 */
	private List<ManagerDTO> listManagerDTO;

	public List<ManagerDTO> getListManagerDTO() {
		return listManagerDTO;
	}

	public void setListManagerDTO(List<ManagerDTO> listManagerDTO) {
		this.listManagerDTO = listManagerDTO;
	}

	public String getDistributionState() {
		return distributionState;
	}

	public void setDistributionState(String distributionState) {
		this.distributionState = distributionState;
	}

	public String getExpressState() {
		return expressState;
	}

	public void setExpressState(String expressState) {
		this.expressState = expressState;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public team getTeamInfo() {
		return teamInfo;
	}

	public void setTeamInfo(team teamInfo) {
		this.teamInfo = teamInfo;
	}

	public vehiclecirculation getVehicleCirculation() {
		return vehicleCirculation;
	}

	public void setVehicleCirculation(vehiclecirculation vehicleCirculation) {
		this.vehicleCirculation = vehicleCirculation;
	}

	public TeamVO getTeamInfoVO() {
		return teamInfoVO;
	}

	public void setTeamInfoVO(TeamVO teamInfoVO) {
		this.teamInfoVO = teamInfoVO;
	}

	public String getTeamLeader() {
		return teamLeader;
	}

	public void setTeamLeader(String teamLeader) {
		this.teamLeader = teamLeader;
	}

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
		 * 格式化json数据
		 */
		gsonBuilder.setPrettyPrinting();
		Gson gson = gsonBuilder.serializeNulls().create();
		HttpSession session = ServletActionContext.getRequest().getSession();// 获取session
		staff_basicinfo staffInfo = (staff_basicinfo) session.getAttribute("staff_session");
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().write(gson.toJson(vehicleManagementService.addVehicle(vehicleInfo, staffInfo)));
	}

	/**
	 * 查询分页车辆
	 * 
	 * @throws IOException
	 */
	public void queryVehicle() throws IOException {
		GsonBuilder gsonBuilder = new GsonBuilder();
		/**
		 * 格式化json数据
		 */
		gsonBuilder.setPrettyPrinting();
		Gson gson = gsonBuilder.serializeNulls().create();
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
		vehicleInfoVO.setDistributionState(distributionState);
		vehicleInfoVO.setExpressState(expressState);
		HttpSession session = ServletActionContext.getRequest().getSession();// 获取session
		staff_basicinfo staffInfo = (staff_basicinfo) session.getAttribute("staff_session");
		vehicleInfoVO = vehicleManagementService.queryVehicle(vehicleInfoVO, staffInfo);
		response.getWriter().write(gson.toJson(vehicleInfoVO));
	}

	/**
	 * 修改车辆信息
	 * 
	 * @throws IOException
	 */
	public void updateVehicle() throws IOException {
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().write("" + vehicleManagementService.updateVehicle(vehicleInfo));
	}

	/**
	 * 批量删除车辆
	 * 
	 * @throws IOException
	 */
	public void deleteVehicle() throws IOException {
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().write("" + vehicleManagementService.deleteVehicle(idList));
	}

	/**
	 * 添加车队
	 * 
	 * @throws IOException
	 */
	public void addTeam() throws IOException {
		GsonBuilder gsonBuilder = new GsonBuilder();
		/**
		 * 格式化json数据
		 */
		gsonBuilder.setPrettyPrinting();
		Gson gson = gsonBuilder.serializeNulls().create();
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().write(gson.toJson(vehicleManagementService.addTeam(teamInfo)) );
	}

	/**
	 * 更新车队信息
	 * 
	 * @throws IOException
	 */
	public void updateTeam() throws IOException {
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().write("" + vehicleManagementService.updateTeam(teamInfo));
	}

	/**
	 * 批量删除车队
	 * 
	 * @throws IOException
	 */
	public void deleteTeam() throws IOException {
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().write("" + vehicleManagementService.deleteTeam(idList));
	}

	/**
	 * 分页查询、模糊查询、筛选查询车队信息
	 * 
	 * @throws IOException
	 */
	public void queryTeam() throws IOException {
		GsonBuilder gsonBuilder = new GsonBuilder();
		/**
		 * 格式化json数据
		 */
		gsonBuilder.setPrettyPrinting();
		Gson gson = gsonBuilder.serializeNulls().create();

		response.setContentType("text/html;charset=utf-8");
		/**
		 * 将从前台传回来的数据放入VO中进行传输
		 */
		TeamVO teamInfoVO = new TeamVO();
		teamInfoVO.setSearch(search);
		teamInfoVO.setState(state);
		teamInfoVO.setUnit(unit);
		teamInfoVO.setTeamLeader(teamLeader);
		HttpSession session = ServletActionContext.getRequest().getSession();// 获取session
		staff_basicinfo staffInfo = (staff_basicinfo) session.getAttribute("staff_session");
		response.getWriter().write(gson.toJson(vehicleManagementService.queryTeam(teamInfoVO, staffInfo)));
	}

	/**
	 * 车辆流转
	 * 
	 * @throws IOException
	 */
	public void exchangeVehicle() throws IOException {
		HttpSession session = ServletActionContext.getRequest().getSession();// 获取session
		staff_basicinfo staffInfo = (staff_basicinfo) session.getAttribute("staff_session");
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().write("" + vehicleManagementService.exchangeVehicle(idList, unit, staffInfo));
	}

	/**
	 * 获取所有管理员
	 * 
	 * @throws IOException
	 */
	public void getAllManager() throws IOException {
		GsonBuilder gsonBuilder = new GsonBuilder();
		/**
		 * 格式化json数据
		 */
		gsonBuilder.setPrettyPrinting();
		Gson gson = gsonBuilder.serializeNulls().create();
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().write(gson.toJson(vehicleManagementService.getAllManager(position)));
	}

	/**
	 * 获得所有车队
	 * 
	 * @throws IOException
	 */
	public void getAllTeam() throws IOException {
		GsonBuilder gsonBuilder = new GsonBuilder();
		/**
		 * 格式化json数据
		 */
		gsonBuilder.setPrettyPrinting();
		Gson gson = gsonBuilder.serializeNulls().create();
		HttpSession session = ServletActionContext.getRequest().getSession();// 获取session
		staff_basicinfo staffInfo = (staff_basicinfo) session.getAttribute("staff_session");
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().write(gson.toJson(vehicleManagementService.getAllTeam(staffInfo)));
	}

	
	/**
	 * 根据Id获得车辆信息
	 * @throws IOException 
	 */
	public void getVehicleInfoById() throws IOException {
		GsonBuilder gsonBuilder = new GsonBuilder();
		/**
		 * 格式化json数据
		 */
		gsonBuilder.setPrettyPrinting();
		Gson gson = gsonBuilder.serializeNulls().create();
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().write(gson.toJson(vehicleManagementService.getVehicleInfoById(idList)));
	}
	

}
