package com.logistics.transferstation.service;

import com.logistics.domain.driver;
import com.logistics.domain.staff_basicinfo;
import com.logistics.domain.team;
import com.logistics.domain.unit;
import com.logistics.transferstation.DTO.UnitManagerDTO;
import com.logistics.transferstation.VO.UnitManagerVO;

/**
 * 中转站管理的service接口层
 * 
 * @author LW
 *
 */
public interface TransferStationService {
	/**
	 * 添加中转站
	 * 
	 * @param transferStation
	 * @param staffBasicinfo
	 * @return
	 */

	String addTransferStation(unit transferStation);

	/**
	 * 删除中转站
	 * 
	 * @param transferStation
	 * @return
	 */
	
	String deleteTransferStation(String idList);

	/**
	 * 分页显示中转站信息
	 * 
	 * @param transferStationVO
	 * @return
	 */

	UnitManagerVO queryTransferStation(UnitManagerVO transferStationVO, staff_basicinfo staffBasicnfo);

	/**
	 * 修改中转站信息
	 * 
	 * @param transferStation
	 * @return
	 */

	String updateTransferStation(unit transferStation);

	/**
	 * 车辆分配
	 * 
	 * @param vehicleList
	 * @param teamNum
	 * @return
	 */
	
	String vehicleDistribution(String vehicleList, String teamNum);

	/**
	 * 司机招募
	 * 
	 * @param driver
	 * @return
	 */
	String driverRecruit(staff_basicinfo driver);

	/**
	 * 司机分配到车队
	 * 
	 * @param driverList
	 * @param teamNum
	 * @return
	 */
	
	String driverDistribution(String driverList, String teamNum);

}
