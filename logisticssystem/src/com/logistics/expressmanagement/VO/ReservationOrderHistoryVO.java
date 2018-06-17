package com.logistics.expressmanagement.VO;

import java.util.List;

import com.logistics.domain.reservation;

public class ReservationOrderHistoryVO {
	/**
	 * 预约表
	 */
	private List<reservation> listReservation;
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

	public List<reservation> getListReservation() {
		return listReservation;
	}

	public void setListReservation(List<reservation> listReservation) {
		this.listReservation = listReservation;
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

	@Override
	public String toString() {
		return "ReservationOrderHistoryVO [listReservation=" + listReservation + ", pageIndex=" + pageIndex
				+ ", totalRecords=" + totalRecords + ", pageSize=" + pageSize + ", totalPages=" + totalPages
				+ ", HavePrePage=" + HavePrePage + ", HaveNextPage=" + HaveNextPage + ", search=" + search + ", state="
				+ state + "]";
	}

}
