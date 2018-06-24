package com.logistics.personnelmanagement.service;

import java.util.List;

import com.google.gson.JsonElement;
import com.logistics.domain.position;
import com.logistics.domain.staff_basicinfo;
import com.logistics.domain.unit;
import com.logistics.personnelmanagement.VO.StaffManagerVO;

/**
 * 人事管理的service接口层
 * @author LW
 *
 */
public interface PersonnelManagementService {
	StaffManagerVO getStaffManagerVO(StaffManagerVO staffManagerVO,staff_basicinfo staffBasicinfo);

	List<unit> getLowerUnit(staff_basicinfo staffBasicinfo);

	List<position> getLowerPosition(staff_basicinfo staffBasicinfo);

	String removeListStaff(String staffListIdS);





	String updateStaffInfo(staff_basicinfo staffBasicInfo);

	String updateStaffUnit(staff_basicinfo staffBasicinfo);

	String updateStaffState(staff_basicinfo staffBasicinfo);

	List<position> getPositionById(String ID);

	staff_basicinfo addStaff(staff_basicinfo staffBasicInfo, staff_basicinfo staffBasicSession);

	String updatePositionById(String iD, String positionNew);

	String updateUnitById(String iD, String unitNew);


	


}
