package com.logistics.transferstation.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.logistics.domain.staff_basicinfo;
import com.logistics.domain.unit;
import com.logistics.transferstation.DTO.UnitManagerDTO;
import com.logistics.transferstation.VO.UnitManagerVO;
import com.logistics.transferstation.service.TransferStationService;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 中转站管理的Action
 * 
 * @author LW
 *
 */
@SuppressWarnings("serial")
public class TransferStationAction extends ActionSupport implements ServletResponseAware, ServletRequestAware {

	/**
	 * 使用域模型将unit放到Struts中
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
	private UnitManagerVO unitManagerVO;
	/**
	 * 使用域模型将staff_basicinfo放到Struts中
	 */
	private staff_basicinfo staff_BasicInfo;

	public UnitManagerVO getUnitManagerVO() {
		return unitManagerVO;
	}

	public void setUnitManagerVO(UnitManagerVO unitManagerVO) {
		this.unitManagerVO = unitManagerVO;
	}

	public staff_basicinfo getStaff_basicinfo() {
		return staff_BasicInfo;
	}

	public void setStaff_basicinfo(staff_basicinfo staff_basicinfo) {
		this.staff_BasicInfo = staff_basicinfo;
	}


	public staff_basicinfo getBasicinfo() {
		return staff_BasicInfo;
	}

	public void setBasicinfo(staff_basicinfo basicinfo) {
		this.staff_BasicInfo = basicinfo;
	}
	
	
	
	
public staff_basicinfo getStaff_BasicInfo() {
		return staff_BasicInfo;
	}

	public void setStaff_BasicInfo(staff_basicinfo staff_BasicInfo) {
		this.staff_BasicInfo = staff_BasicInfo;
	}
	/**
	 * 根据id进行删除或者批量删除的字段
	 */
	private String idList;
	/**
	 * 根据Id查询管理员字段
	 */
	private String admin;

public String getIdList() {
		return idList;
	}

	public void setIdList(String idList) {
		this.idList = idList;
	}

	public String getAdmin() {
		return admin;
	}

	public void setAdmin(String admin) {
		this.admin = admin;
	}
/**
 * 分页查询的字段
 */
	private String state;
	private String address;
	private String num;
	private String superiorunit;
	private String search;
	private int page;

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

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
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
	 * 
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
	 * 中转站管理
	 */
	public void transferStationManager() {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.create();
		response.setContentType("text/html;charset=utf-8");
		
		
	}
	
	
	/**
	 * 查询中转站
	 */
	public void queryTransferStation() {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.create();
		response.setContentType("text/html;charset=utf-8");
		unitManagerVO = new UnitManagerVO();
		unitManagerVO.setSearch(search);
		unitManagerVO.setNum(num);
		unitManagerVO.setAddress(address);
		unitManagerVO.setSuperiorunit(superiorunit);
		unitManagerVO.setState(state);
		unitManagerVO.setPageIndex(page);
		unitManagerVO.setAdmin(admin);
		HttpSession session = ServletActionContext.getRequest().getSession();
		staff_basicinfo staffBasicinfo = (staff_basicinfo) session.getAttribute("staff_session");
		unitManagerVO = transferStationService.queryTransferStation(unitManagerVO,staffBasicinfo);
		//listunit = transferStationService.queryTransferStation();
		System.out.println("chaxun action");
		
	}

	/**
	 * 添加中转站
	 * 
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
		response.getWriter().write("" + transferStationService.addTransferStation(transferStation));

		System.out.println("qqqqq");

	}

	/**
	 * 删除中转站
	 * 
	 * @throws IOException
	 */

	public void deleteTransferStation() throws IOException {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.create();
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().write("" + transferStationService.deleteTransferStation(unitManagerVO));
	}

	/**
	 * 修改中转站信息
	 * 
	 * @throws IOException
	 */

	public void TransferStation() throws IOException {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.create();
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().write("" + transferStationService.updateTransferStation(transferStation));
	}

}
