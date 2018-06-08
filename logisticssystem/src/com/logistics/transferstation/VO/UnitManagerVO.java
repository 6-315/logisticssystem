package com.logistics.transferstation.VO;
/**
 * 分页显示中转站TransferstationVO
 * @author LL
 *
 */

import java.util.List;

import com.logistics.domain.unit;

public class UnitManagerVO {
	/**
	 * 生成list集合
	 */
	private List<unit> listunit;
	private int totalRecords = 0;

	private String state;
	
	private String address;
	
	private String num;
	
	private String superiorunit; 
	
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
	public String getSuperiorunit() {
		return superiorunit;
	}
	public void setSuperiorunit(String superiorunit) {
		this.superiorunit = superiorunit;
	}
	private int pageIndex = 1;

	private int pageSize = 2;

	private int totalPages = 1;
	
	private String search;

	public String getSearch() {
		return search;
	}
	public void setSearch(String search) {
		this.search = search;
	}
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
		return "UnitManagerVO [listunit=" + listunit + ", totalRecords=" + totalRecords + ", state=" + state
				+ ", address=" + address + ", num=" + num + ", superiorunit=" + superiorunit + ", pageIndex="
				+ pageIndex + ", pageSize=" + pageSize + ", totalPages=" + totalPages + ", search=" + search
				+ ", havePrePage=" + havePrePage + ", haveNextPage=" + haveNextPage + "]";
	}

	

}
