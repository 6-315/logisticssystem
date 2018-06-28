package com.logistics.routemanagement.RouteManagerVO;

import java.util.List;

import com.logistics.routemanagement.RouteManagerDTO.RouteManagerDTO;

/**
 * 路线分页
 * @author XTY
 *
 */

public class RouteManagerVO {
	// 分页使用的东西
	private int totalRecords = 0;
	//当前页
	private int pageIndex = 1;
	// 分页数量
	private int pageSize = 10;
    //总页数
	private int totalPages = 1;

	private boolean havePrePage = false;
	private boolean haveNextPage = false;
	/**
	 * 根据路线编号进行搜索
	 */
	private String search;
	/**
	 * 根据创建时间进行搜索
	 */
	private String startTime;
	/**
	 * 根据结束时间进行搜索
	 */
	private String endTime;
	/**
	 * 根据状态进行筛选
	 */
	private String state;
	/**
	 * 根据开始单位进行筛选
	 */
	private String startUnit;
	/**
	 * 根据结束单位进行筛选
	 */
	private String endUnit;
	private List<RouteManagerDTO> listRouteManagerDTO;
	public int getTotalRecords() {
		return totalRecords;
	}
	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}
	public int getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	public boolean isHavePrePage() {
		return havePrePage;
	}
	public void setHavePrePage(boolean havePrePage) {
		this.havePrePage = havePrePage;
	}
	public boolean isHaveNextPage() {
		return haveNextPage;
	}
	public void setHaveNextPage(boolean haveNextPage) {
		this.haveNextPage = haveNextPage;
	}
	public String getSearch() {
		return search;
	}
	public void setSearch(String search) {
		this.search = search;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
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
	public List<RouteManagerDTO> getListRouteManagerDTO() {
		return listRouteManagerDTO;
	}
	public void setListRouteManagerDTO(List<RouteManagerDTO> listRouteManagerDTO) {
		this.listRouteManagerDTO = listRouteManagerDTO;
	}
	@Override
	public String toString() {
		return "RouteManagerVO [totalRecords=" + totalRecords + ", pageIndex=" + pageIndex + ", pageSize=" + pageSize
				+ ", totalPages=" + totalPages + ", havePrePage=" + havePrePage + ", haveNextPage=" + haveNextPage
				+ ", search=" + search + ", startTime=" + startTime + ", endTime=" + endTime + ", state=" + state
				+ ", startUnit=" + startUnit + ", endUnit=" + endUnit + ", listRouteManagerDTO=" + listRouteManagerDTO
				+ "]";
	}
	
	

}
