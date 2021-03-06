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
import com.logistics.personnelmanagement.DTO.StaffManagerDTO;
import com.logistics.personnelmanagement.VO.StaffManagerVO;
import com.logistics.personnelmanagement.dao.PersonnelManagementDao;
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
	private String state = "";
	private String position = "";
	private String ID;
	private String unitNew;
	private String positionNew;

	public String getUnitNew() {
		return unitNew;
	}

	public void setUnitNew(String unitNew) {
		this.unitNew = unitNew;
	}

	public String getPositionNew() {
		return positionNew;
	}

	public void setPositionNew(String positionNew) {
		this.positionNew = positionNew;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

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
	 * 
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
		staffManagerVO.setBelongUnit(belongUnit);
		staffManagerVO.setState(state);
		staffManagerVO.setPosition(position);
		HttpSession session = ServletActionContext.getRequest().getSession();
		staff_basicinfo staffBasicinfo = (staff_basicinfo) session.getAttribute("staff_session");
		if (staffBasicinfo.getStaff_id() != null && staffBasicinfo.getStaff_id().trim().length() > 0
				&& staffBasicinfo.getStaff_position() != null
				&& staffBasicinfo.getStaff_position().trim().length() > 0) {
			staffManagerVO = personnelManagementService.getStaffManagerVO(staffManagerVO, staffBasicinfo);
			response.getWriter().write(gson.toJson(staffManagerVO));
		}

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
		staff_basicinfo staffBasicInfo = (staff_basicinfo) session.getAttribute("staff_session");
		listUnit = personnelManagementService.getLowerUnit(staffBasicInfo);
		response.getWriter().write(gson.toJson(listUnit));

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
		staff_basicinfo staffBasicInfo = (staff_basicinfo) session.getAttribute("staff_session");
		listPosition = personnelManagementService.getLowerPosition(staffBasicInfo);
		response.getWriter().write(gson.toJson(listPosition));
	}

	/**
	 * 删除员工信息循环删除，因为可全选
	 * 
	 * @throws IOException
	 */
	public void removeListStaff() throws IOException {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.create();
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().write("" + personnelManagementService.removeListStaff(staffListIdS));
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
		response.getWriter().write("" + personnelManagementService.updateStaffInfo(staffBasicInfo));
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
		HttpSession session = ServletActionContext.getRequest().getSession();// 获取session
		staff_basicinfo staffBasicSession = (staff_basicinfo) session.getAttribute("staff_session");
		staff_basicinfo staffNew = new staff_basicinfo();
		staffNew = personnelManagementService.addStaff(staffBasicInfo, staffBasicSession);
		response.getWriter().write(gson.toJson(staffNew));
	}

	/**
	 * 根据单位 获取该单位所有职位
	 * 
	 * @throws IOException
	 */
	public void getPositionById() throws IOException {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.create();
		response.setContentType("text/html;charset=utf-8");
		List<position> listPosition = new ArrayList<>();
		HttpSession session = ServletActionContext.getRequest().getSession();// 获取session
		staff_basicinfo staffBasicSession = (staff_basicinfo) session.getAttribute("staff_session");
		listPosition = personnelManagementService.getPositionById(staffBasicSession);
		response.getWriter().write(gson.toJson(listPosition));

	}

	/**
	 * 根据传回来的员工ID修改职位
	 * 
	 * @return
	 * @throws IOException
	 */
	public void updatePositionById() throws IOException {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.create();
		response.setContentType("text/html;charset=utf-8");

		response.getWriter().write("" + personnelManagementService.updatePositionById(ID, positionNew));
	}

	/**
	 * 根据传回来的员工ID修改单位
	 * 
	 * @return
	 * @throws IOException
	 */
	public void updateUnitById() throws IOException {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.create();
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().write("" + personnelManagementService.updateUnitById(ID, unitNew));
	}

	/**
	 * 根据ID查询一个人的StaffManagerDTO
	 * 
	 * @throws IOException
	 */
	public void getStaffManagerDTO() throws IOException {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.create();
		response.setContentType("text/html;charset=utf-8");
		StaffManagerDTO staffManagerDTO = new StaffManagerDTO();
		staffManagerDTO = personnelManagementService.getStaffManagerDTO(ID);
		response.getWriter().write(gson.toJson(staffManagerDTO));
	}
}
