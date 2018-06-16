package com.logistics.expressmanagement.VO;

import java.util.List;

import com.logistics.expressmanagement.DTO.*;

public class ExpressInfoVO {
	/**
	 * 快件信息列表
	 */
	private List<ExpressInfoDTO> listExpressInfoDTO;
	/**
	 * 当前页码
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
	private String search = "";
	/**
	 * 根据状态筛选
	 */
	private String state = "";
	/**
	 * 根据单位筛选
	 */
	private String unit = "";

	public List<ExpressInfoDTO> getListExpressInfoDTO() {
		return listExpressInfoDTO;
	}

	public void setListExpressInfoDTO(List<ExpressInfoDTO> listExpressInfoDTO) {
		this.listExpressInfoDTO = listExpressInfoDTO;
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

	@Override
	public String toString() {
		return "ExpressInfoVO [listExpressInfoDTO=" + listExpressInfoDTO + ", pageIndex=" + pageIndex
				+ ", totalRecords=" + totalRecords + ", pageSize=" + pageSize + ", totalPages=" + totalPages
				+ ", HavePrePage=" + HavePrePage + ", HaveNextPage=" + HaveNextPage + ", search=" + search + ", state="
				+ state + ", unit=" + unit + "]";
	}

}
