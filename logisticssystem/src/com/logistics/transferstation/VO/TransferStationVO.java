package com.logistics.transferstation.VO;
/**
 * 分页显示中转站TransferstationVO
 * @author LL
 *
 */

import java.util.List;

import com.logistics.domain.unit;

public class TransferStationVO {
	/**
	 * 生成list集合
	 */
	private List<unit> listunit;
	private int totalRecords = 0;

	private int pageIndex = 1;

	private int pageSize = 2;

	private int totalPages = 1;

	private boolean havePrePage = false;
	private boolean haveNextPage = false;
	public List<unit> getListunit() {
		return listunit;
	}
	public void setListunit(List<unit> listunit) {
		this.listunit = listunit;
	}
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
	@Override
	public String toString() {
		return "TransferstationVO [listunit=" + listunit + ", totalRecords=" + totalRecords + ", pageIndex=" + pageIndex
				+ ", pageSize=" + pageSize + ", totalPages=" + totalPages + ", havePrePage=" + havePrePage
				+ ", haveNextPage=" + haveNextPage + "]";
	}


}
