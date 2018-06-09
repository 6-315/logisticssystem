package com.logistics.personnelmanagement.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.logistics.domain.position;
import com.logistics.domain.staff_basicinfo;
import com.logistics.domain.unit;
import com.logistics.domain.userinfo;
import com.logistics.personnelmanagement.VO.StaffManagerVO;
import com.logistics.personnelmanagement.service.PersonnelManagementService;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 人事管理的Action层
 * 
 * @author LW
 *
 */
public class PersonnelManagementAction extends ActionSupport implements ServletResponseAware, ServletRequestAware {

	private PersonnelManagementService personnelManagementService;

	public void setPersonnelManagementService(PersonnelManagementService personnelManagementService) {
		this.personnelManagementService = personnelManagementService;
	}

	/**
	 * 
	 */
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

	/**
	 * 实现结束
	 */
	/**
	 * 使用域模型
	 */
	private staff_basicinfo staffBasicInfo;
	private StaffManagerVO staffManagerVO;
	private int pageIndex = 1;
	private String search = "";
	private String staffListIdS = "";
	private String belongUnit = "";

	public String getBelongUnit() {
		return belongUnit;
	}

	public void setBelongUnit(String belongUnit) {
		this.belongUnit = belongUnit;
	}

	public staff_basicinfo getStaffBasicinfo() {
		return staffBasicInfo;
	}

	public void setStaffBasicinfo(staff_basicinfo staffBasicinfo) {
		this.staffBasicInfo = staffBasicinfo;
	}

	public StaffManagerVO getStaffManagerVO() {
		return staffManagerVO;
	}

	public void setStaffManagerVO(StaffManagerVO staffManagerVO) {
		this.staffManagerVO = staffManagerVO;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public String getStaffListIdS() {
		return staffListIdS;
	}

	public void setStaffListIdS(String staffListIdS) {
		this.staffListIdS = staffListIdS;
	}

	public PersonnelManagementService getPersonnelManagementService() {
		return personnelManagementService;
	}

	/**
	 * 人事管理方法，查询1、工号 2、姓名 3、联系方式 4、入职时间 5、职位 6、所属单位7、状态
	 * @throws IOException
	 */
	public void staffManager() throws IOException {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.create();
		response.setContentType("text/html;charset=utf-8");
		staffManagerVO = new StaffManagerVO();
		staffManagerVO.setPageIndex(pageIndex);
		staffManagerVO.setSearch(search);
		HttpSession session = ServletActionContext.getRequest().getSession();
		
		staff_basicinfo staffBasicinfo = (staff_basicinfo) session.getAttribute("staff_session");
		if(staffBasicinfo.getStaff_id()!=null && staffBasicinfo.getStaff_id().trim().length()>0) {
			
		}
		/**
		 * 总公司
		 * 职位ID
		 * 中转站管理员
		 * 职位ID
		 */
		if ("77e07c34-735f-45d4-a870-3e5bebe5ddc1".equals(staffBasicinfo.getStaff_position())) {
			staffManagerVO = personnelManagementService.getStaffManagerVO(staffManagerVO);
		}
		if ("77e07c34-735f-45d4-a870-3e5bebe5ddc2".equals(staffBasicinfo.getStaff_position())) {
			staffManagerVO = personnelManagementService.getStaffManagerVOByTransfer(staffManagerVO, staffBasicinfo);
		}
		if ("77e07c34-735f-45d4-a870-3e5bebe5ddc3".equals(staffBasicinfo.getStaff_position())) {
			staffManagerVO = personnelManagementService.getStaffManagerVOByDistribution(staffManagerVO, staffBasicinfo);
		}
		response.getWriter().write("" + staffManagerVO);
	}

	/**
	 * 获取自身职位以下的所有单位
	 * 
	 * @throws IOException
	 */
	public void lowerUnit() throws IOException {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.create();
		response.setContentType("text/html;charset=utf-8");
		List<unit> listUnit = new ArrayList<>();
		HttpSession session = ServletActionContext.getRequest().getSession();
		staff_basicinfo staffBasicinfo = (staff_basicinfo) session.getAttribute("staff_session");
		listUnit = personnelManagementService.getLowerUnit(staffBasicinfo);
		response.getWriter().write("" + listUnit);

	}

	/**
	 * 获取自身职位一下的所有职位
	 * 
	 * @throws IOException
	 */
	public void lowerPosition() throws IOException {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.create();
		response.setContentType("text/html;charset=utf-8");
		List<position> listPosition = new ArrayList<>();
		HttpSession session = ServletActionContext.getRequest().getSession();// 获取session
		staff_basicinfo staffBasicinfo = (staff_basicinfo) session.getAttribute("staff_session");
		listPosition = personnelManagementService.getLowerPosition(staffBasicinfo);
		response.getWriter().write("" + listPosition);
	}

	/**
	 * 删除员工信息循环删除，因为可全选
	 * 
	 * @throws IOException
	 */
	public void deleteListStaff() throws IOException {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.create();
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().write("" + personnelManagementService.deleteListStaff(staffListIdS));
	}

	/**
	 * 修改员工单位
	 * 
	 * @throws IOException
	 */
	public void updateStaffUnit() throws IOException {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.create();
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().write("" + personnelManagementService.updateStaffUnit(staffBasicInfo));
	}

	/**
	 * 就该员工职位
	 * 
	 * @throws IOException
	 */
	public void updateStaffPosition() throws IOException {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.create();
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().write("" + personnelManagementService.updateStaffPosition(staffBasicInfo));

	}

	/**
	 * 修改员工状态
	 */
	public void updateStaffState() throws IOException {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.create();
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().write("" + personnelManagementService.updateStaffState(staffBasicInfo));
	}

	/**
	 * 添加员工
	 * 
	 * @throws IOException
	 */
	public void addStaff() throws IOException {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.create();
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().write("" + personnelManagementService.addStaff(staffBasicInfo));
	}

}
