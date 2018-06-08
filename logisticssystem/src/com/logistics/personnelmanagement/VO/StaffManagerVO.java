package com.logistics.personnelmanagement.VO;

import java.util.List;

import com.logistics.personnelmanagement.DTO.StaffManagerDTO;

/**
 * 获取人事管理的页表信息
 * 
 * @author LW
 *
 */
public class StaffManagerVO {
	private int totalRecords = 0;
	private int pageIndex = 1;
	// 分页数量
	private int pageSize = 8;

	private int totalPages = 1;

	private boolean havePrePage = false;
	private boolean haveNextPage = false;
	/**
	 * 关键字
	 */
	private String search;
	/**
	 * 所属单位
	 */
	private String belongUnit;
	/**
	 * 职位表
	 */
	private String position;
	

	private List<StaffManagerDTO> listStaDTO;

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

	public String getBelongUnit() {
		return belongUnit;
	}

	public void setBelongUnit(String belongUnit) {
		this.belongUnit = belongUnit;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public List<StaffManagerDTO> getListStaDTO() {
		return listStaDTO;
	}

	public void setListStaDTO(List<StaffManagerDTO> listStaDTO) {
		this.listStaDTO = listStaDTO;
	}

	@Override
	public String toString() {
		return "StaffManagerVO [totalRecords=" + totalRecords + ", pageIndex=" + pageIndex + ", pageSize=" + pageSize
				+ ", totalPages=" + totalPages + ", havePrePage=" + havePrePage + ", haveNextPage=" + haveNextPage
				+ ", search=" + search + ", belongUnit=" + belongUnit + ", position=" + position + ", listStaDTO="
				+ listStaDTO + "]";
	}

}
