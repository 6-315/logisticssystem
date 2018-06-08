package com.logistics.distribution.VO;
/**
 * 这是单位管理列表的VO
 * @author YX
 *
 */

import java.util.List;

import com.logistics.distribution.DTO.UnitManagerDTO;

public class UnitManagerVO {
	/**
	 * 总记录数
	 */
	private int totalRecords = 0;
	/**
	 * 当前页
	 */
	private int pageIndex = 1;
	/**
	 * 每页的数量
	 */
	private int pageSize = 2;
	/**
	 * 总页数
	 */
	private int totalPages = 1;
	/**
	 * 前一页
	 */
	private boolean havePrePage = false;
	/**
	 * 下一页
	 */
	private boolean haveNextPage = false;
	/**
	 * 搜索
	 */
	private String search;
	/**
	 * 人事管理列表DTO
	 */
	private List<UnitManagerDTO> unitManagerDTO;
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
	public List<UnitManagerDTO> getUnitManagerDTO() {
		return unitManagerDTO;
	}
	public void setUnitManagerDTO(List<UnitManagerDTO> unitManagerDTO) {
		this.unitManagerDTO = unitManagerDTO;
	}
	@Override
	public String toString() {
		return "UnitManagerVO [totalRecords=" + totalRecords + ", pageIndex=" + pageIndex + ", pageSize=" + pageSize
				+ ", totalPages=" + totalPages + ", havePrePage=" + havePrePage + ", haveNextPage=" + haveNextPage
				+ ", search=" + search + ", unitManagerDTO=" + unitManagerDTO + "]";
	}
	
}
