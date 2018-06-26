package com.logistics.routemanagement.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.logistics.domain.route;
import com.logistics.routemanagement.RouteManagerVO.RouteManagerVO;
import com.logistics.routemanagement.service.RouteManagementService;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 路线管理action
 * 
 * @autho r LW
 *
 */
public class RouteManagementAction extends ActionSupport implements ServletResponseAware, ServletRequestAware {
	private static final long serialVersionUID = 1L;
	private RouteManagementService routeManagementService;

	public void setRouteManagementService(RouteManagementService routeManagementService) {
		this.routeManagementService = routeManagementService;
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
	 * 分页
	 */
	
	private RouteManagerVO routManagerVO;
	private int page = 1;
	private String search = "";

	private String startUnit;
	/**
	 * 根据结束时间进行搜索
	 */
	private String endUnit;
	/**
	 * 根据状态进行筛选
	 */
	private String state;
	/**
	 * 使用域模型将route对象放置到struts中
	 */
	private route rout;
	private String routeIds;
	/**
	 * 多选路线string
	 */
	private String routeId;

	public String getStartUnit() {
		return startUnit;
	}

	public void setStartUnit(String startUnit) {
		this.startUnit = startUnit;
	}

	public String getEndUnit() {
		return endUnit;
	}

	public void setEndUnit(String endUnit) {
		this.endUnit = endUnit;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getRouteIds() {
		return routeIds;
	}

	public void setRouteIds(String routeIds) {
		this.routeIds = routeIds;
	}

	public route getRout() {
		return rout;
	}

	public void setRout(route rout) {
		this.rout = rout;
	}

	public String getRouteId() {
		return routeId;
	}

	public void setRouteId(String routeId) {
		this.routeId = routeId;
	}

	public RouteManagerVO getRoutManagerVO() {
		return routManagerVO;
	}

	public void setRoutManagerVO(RouteManagerVO routManagerVO) {
		this.routManagerVO = routManagerVO;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	/**
	 * 增加路线
	 * @throws IOException 
	 */
	public void addRoute() throws IOException {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.create();
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().write(gson.toJson(routeManagementService.addRout(rout)));

		
	}

	/**
	 * 更改路线信息
	 * @throws IOException 
	 */
	public void updateRouteInfo() throws IOException {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.create();
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().write(gson.toJson(routeManagementService.updateRoutInfo(rout)));
	}

	/**
	 * 更改路线状态
	 * @throws IOException 
	 */
	public void updateRouteState() throws IOException {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.create();
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().write(gson.toJson(routeManagementService.updateRouteState(rout)));
		//routeManagementService.updateRouteState(rout);

	}

	/**
	 * 批量删除路线
	 * @throws IOException 
	 */
	public void removeListRoute() throws IOException {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.create();
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().write(routeManagementService.removeListRoute(routeId));


	}

	/**
	 * 获取路线列表
	 */
	public void getRouteManagerVO() throws IOException {
		// 使用VO拿到数据和分页
		
		response.setContentType("text/html;charset=utf-8");
		RouteManagerVO routManagerVO = new RouteManagerVO();
		routManagerVO.setPageIndex(page);
		routManagerVO.setSearch(search);
		routManagerVO.setEndUnit(endUnit);
		routManagerVO.setStartUnit(startUnit);
		routManagerVO.setState(state);
		routManagerVO = routeManagementService.getRouteManagerVO(routManagerVO);
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.serializeNulls().create();
		response.getWriter().write(gson.toJson(routManagerVO));

		

	}
}
