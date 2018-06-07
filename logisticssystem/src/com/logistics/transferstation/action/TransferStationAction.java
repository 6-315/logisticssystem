package com.logistics.transferstation.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.logistics.domain.unit;
import com.logistics.transferstation.VO.TransferStationVO;
import com.logistics.transferstation.service.TransferStationService;
import com.opensymphony.xwork2.ActionSupport;
/**
 * 中转站管理的Action
 * @author LW
 *
 */
public class TransferStationAction   extends ActionSupport implements ServletResponseAware, ServletRequestAware{
	
	/**
	 * 使用域模型将单位表放到Struts中
	 */
	private unit transferStation;
	/**
	 * 使用域模型listunit
	 */
	private List<unit> listunit;
	/**
	 * 使用域模型vo分页
	 * 
	 */
	private TransferStationVO transferStationVO;
	
	private int page;
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public TransferStationVO getTransferStationVO() {
		return transferStationVO;
	}
	public void setTransferStationVO(TransferStationVO transferStationVO) {
		this.transferStationVO = transferStationVO;
	}
	public List<unit> getListunit() {
		return listunit;
	}
	public void setListunit(List<unit> listunit) {
		this.listunit = listunit;
	}

	/**
	 * service层注入
	 */
	private TransferStationService transferStationService;

	public void setTransferStationService(TransferStationService transferStationService) {
		this.transferStationService = transferStationService;
	}
	
	/**
	 * 实现request以及response
	 */
	private HttpServletResponse response;

	private HttpServletRequest request;
	
	/**
	 * get和set
	 * @return
	 */

	public unit getTransferStation() {
		return transferStation;
	}
	public void setTransferStation(unit transferStation) {
		this.transferStation = transferStation;
	}
	
	
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

	/**
	 * 实现结束
	 */
	/**
	 * 查询中转站
	 */
	public void queryTransferStation() {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.create();

		response.setContentType("text/html;charset=utf-8");
		 transferStationVO = new TransferStationVO();
		 transferStationVO.setPageIndex(page);
		 transferStationVO = transferStationService.queryTransferStation(transferStationVO);
		//listunit = transferStationService.queryTransferStation();
		System.out.println("chaxun action");
		
	}
	/**
	 * 添加中转站
	 * @throws IOException 
	 */
	
	public void addTransferStation() throws IOException {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.create();

		response.setContentType("text/html;charset=utf-8");

		unit transferStation = new unit();
		transferStation.setUnit_name("lailiang");
		transferStation.setUnit_address("hubei");
		transferStation.setUnit_detailaddress("hubeiwuhan");
		transferStation.setUnit_type("zhongzhuangzhan");
		transferStation.setUnit_creator("zonggongsi");
		transferStation.setUnit_state("zhengchang");
		transferStation.setUnit_phonenumber("123");
       response.getWriter().write(""+transferStationService.addTransferStation(transferStation));
		
		
		System.out.println("qqqqq");
		
	}
	/**
	 * 删除中转站
	 * @throws IOException 
	 */
	
	public void deleteTransferStation() throws IOException {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.create();
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().write(""+transferStationService.deleteTransferStation(transferStation));
	}
	/**
	 * 修改中转站信息
	 * @throws IOException 
	 */
	
	public void updataTransferStation() throws IOException {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.create();
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().write(""+transferStationService.updataTransferStation(transferStation));
	}
	
	
	
	
}
