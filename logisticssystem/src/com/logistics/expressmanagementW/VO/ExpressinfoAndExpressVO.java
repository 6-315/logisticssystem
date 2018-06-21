package com.logistics.expressmanagementW.VO;

import java.util.List;

import com.logistics.expressmanagementW.DTO.ExpressinfoAndExpressDTO;
import com.logistics.personnelmanagement.DTO.StaffManagerDTO;

public class ExpressinfoAndExpressVO {
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
	 * 状态
	 */
	private String state;
	/**
	 * List快件和快件信息的DTO
	 */
	private List<ExpressinfoAndExpressDTO> listExpressinfoAndExpressDTO;

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

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public List<ExpressinfoAndExpressDTO> getListExpressinfoAndExpressDTO() {
		return listExpressinfoAndExpressDTO;
	}

	public void setListExpressinfoAndExpressDTO(List<ExpressinfoAndExpressDTO> listExpressinfoAndExpressDTO) {
		this.listExpressinfoAndExpressDTO = listExpressinfoAndExpressDTO;
	}

	@Override
	public String toString() {
		return "ExpressinfoAndExpressVO [totalRecords=" + totalRecords + ", pageIndex=" + pageIndex + ", pageSize="
				+ pageSize + ", totalPages=" + totalPages + ", havePrePage=" + havePrePage + ", haveNextPage="
				+ haveNextPage + ", search=" + search + ", state=" + state + ", listExpressinfoAndExpressDTO="
				+ listExpressinfoAndExpressDTO + "]";
	}

}
