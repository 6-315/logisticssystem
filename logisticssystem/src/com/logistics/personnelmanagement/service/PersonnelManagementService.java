package com.logistics.personnelmanagement.service;

import java.util.List;

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
	StaffManagerVO getStaffManagerVO(StaffManagerVO staffManagerVO);

	List<unit> getLowerUnit(staff_basicinfo staffBasicinfo);

	List<position> getLowerPosition(staff_basicinfo staffBasicinfo);

	String deleteListStaff(String staffListIdS);

	String updateStaffUnit(staff_basicinfo staffBasicinfo);

	String updateStaffPosition(staff_basicinfo staffBasicinfo);

	String updateStaffState(staff_basicinfo staffBasicinfo);

	String addStaff(staff_basicinfo staffBasicinfo);


	StaffManagerVO getStaffManagerVOByTransfer(StaffManagerVO staffManagerVO, staff_basicinfo staffBasicinfo);

	StaffManagerVO getStaffManagerVOByDistribution(StaffManagerVO staffManagerVO, staff_basicinfo staffBasicinfo);


}
