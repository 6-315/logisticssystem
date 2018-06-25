package com.logistics.distribution.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.logistics.distribution.service.DistributionService;
import com.logistics.domain.unit;
import com.logistics.transferstation.DTO.UnitManagerDTO;
import com.logistics.transferstation.VO.UnitManagerVO;
import com.opensymphony.xwork2.ActionSupport;

/**
 *  配送点管理 的action
 * 
 * @author LW
 *
 */

public class DistributionAction extends ActionSupport implements ServletResponseAware, ServletRequestAware {
	private static final long serialVersionUID = 1L;

	private DistributionService distributionService;
	/**
	 * 使用域模型
	 */
	private unit distribution;
	private List<UnitManagerDTO> unitManagerDTO;
	private UnitManagerVO unitManagerVO;
	private String idList;
	

	public String getIdList() {
		return idList;
	}

	public void setIdList(String idList) {
		this.idList = idList;
	}

	private int page = 1;

	public List<UnitManagerDTO> getUnitManagerDTO() {
		return unitManagerDTO;
	}

	public void setUnitManagerDTO(List<UnitManagerDTO> unitManagerDTO) {
		this.unitManagerDTO = unitManagerDTO;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public void setUnitManagerVO(UnitManagerVO unitManagerVO) {
		this.unitManagerVO = unitManagerVO;
	}

	public void setDistributionService(DistributionService distributionService) {
		this.distributionService = distributionService;
	}

	/**
	 * 实现request以及response
	 */
	private HttpServletResponse response;

	private HttpServletRequest request;

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	public unit getDistribution() {
		return distribution;
	}

	public void setDistribution(unit distribution) {
		this.distribution = distribution;
	}

	/**
	 * 实现结束
	 */

	/**
	 * 配送点的增加方法
	 * 
	 * @throws IOException
	 */
	public void addDistribution() throws IOException {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.create();
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().write("" + distributionService.addDistribution(distribution));
	}

	/**
	 * 修改配送点信息
	 * 
	 * @throws IOException
	 */
	public void updateDistribution() throws IOException {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.create();
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().write("" + distributionService.updateDistribution(distribution));

	}

	/**
	 * 批量删除配送点
	 * @throws IOException 
	 */
	public void deleteDistribution() throws IOException {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.create();
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().write("" + distributionService.deleteDistribution(idList) );

	}

	/**
	 * 获取配送点列表信息方法
	 * 
	 * @throws IOException
	 */
	public void getUnitManagerVO() throws IOException {
		unitManagerVO = new UnitManagerVO();
		unitManagerVO.setPageIndex(page);
		GsonBuilder gsonBuilder = new GsonBuilder();
		// 格式化json数据
		gsonBuilder.setPrettyPrinting();
		Gson gson = gsonBuilder.create();
		response.setContentType("text/html;charset=utf-8");

	}
}
