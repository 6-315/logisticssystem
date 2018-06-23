package com.logistics.transferstation.VO;
/**
 * 分页显示中转站TransferstationVO
 * @author LL
 *
 */

import java.util.List;

import com.logistics.transferstation.DTO.UnitManagerDTO;

/**
 * 分页显示中转站的UnitManagerVO
 * 
 * @author LL
 *
 */
public class UnitManagerVO {
	/**
	 * 生成list集合
	 */
	private List<UnitManagerDTO> listUnitManagerDTO;

	private int totalRecords = 0;

	private String type;

	private String state;
	
	private int pageIndex = 1;

	private int pageSize = 2;

	private int totalPages = 1;
	/**
	 * 模糊查询
	 */
	private String search;

	private boolean havePrePage = false;
	private boolean haveNextPage = false;
	
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}


	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}


	public List<UnitManagerDTO> getListUnitManagerDTO() {
		return listUnitManagerDTO;
	}

	public void setListUnitManagerDTO(List<UnitManagerDTO> listUnitManagerDTO) {
		this.listUnitManagerDTO = listUnitManagerDTO;
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
		return "UnitManagerVO [listUnitManagerDTO=" + listUnitManagerDTO + ", totalRecords=" + totalRecords + ", type="
				+ type + ", state=" + state + ", pageIndex=" + pageIndex + ", pageSize=" + pageSize + ", totalPages="
				+ totalPages + ", search=" + search + ", havePrePage=" + havePrePage + ", haveNextPage=" + haveNextPage
				+ "]";
	}

	

}
