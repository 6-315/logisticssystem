package com.logistics.vehiclemanagement.VO;

import java.util.List;

import com.logistics.vehiclemanagement.DTO.Vehicle_TeamDTO;

public class TeamVO {
	/**
	 * 车队信息ListDTO
	 */
	private List<Vehicle_TeamDTO> listTeamDTO;
	/**
	 * 需要显示的页码
	 */
	private int pageIndex = 1;
	/**
	 * 总记录数
	 */
	private int totalRecords = 0;
	/**
	 * 每页记录数
	 */
	private int pageSize = 10;
	/**
	 * 总页数
	 */
	private int totalPages = 1;
	/**
	 * 是否有上一页
	 */
	private boolean HavePrePage = false;
	/**
	 * 是否有下一页
	 */
	private boolean HaveNextPage = false;

	/**
	 * 模糊查询关键字
	 */
	private String search;
	/**
	 * 根据所属单位筛选
	 */
	private String unit;
	/**
	 * 根据队长筛选
	 */
	private String teamLeader;
	/**
	 * 根据状态筛选
	 */
	private String state;
	/**
	 * 根据ID批量删除
	 */
	private String idList;

	public List<Vehicle_TeamDTO> getListTeamDTO() {
		return listTeamDTO;
	}

	public void setListTeamDTO(List<Vehicle_TeamDTO> listTeamDTO) {
		this.listTeamDTO = listTeamDTO;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
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
		return HavePrePage;
	}

	public void setHavePrePage(boolean havePrePage) {
		HavePrePage = havePrePage;
	}

	public boolean isHaveNextPage() {
		return HaveNextPage;
	}

	public void setHaveNextPage(boolean haveNextPage) {
		HaveNextPage = haveNextPage;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getTeamLeader() {
		return teamLeader;
	}

	public void setTeamLeader(String teamLeader) {
		this.teamLeader = teamLeader;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getIdList() {
		return idList;
	}

	public void setIdList(String idList) {
		this.idList = idList;
	}

	@Override
	public String toString() {
		return "TeamVO [listTeamDTO=" + listTeamDTO + ", pageIndex=" + pageIndex + ", totalRecords=" + totalRecords
				+ ", pageSize=" + pageSize + ", totalPages=" + totalPages + ", HavePrePage=" + HavePrePage
				+ ", HaveNextPage=" + HaveNextPage + ", search=" + search + ", unit=" + unit + ", teamLeader="
				+ teamLeader + ", state=" + state + ", idList=" + idList + "]";
	}

}
