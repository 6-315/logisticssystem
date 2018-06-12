package com.logistics.transferstation.service;

import com.logistics.domain.staff_basicinfo;
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
	 * @return
	 */

	String addTransferStation(unit transferStation);

	/**
	 * 删除中转站
	 * 
	 * @param transferStation
	 * @return
	 */
	public String deleteTransferStation(UnitManagerVO unitManagerVO);

	/**
	 * 分页显示中转站信息
	 * 
	 * @param transferStationVO
	 * @return
	 */

	UnitManagerVO queryTransferStation(UnitManagerVO transferStationVO,staff_basicinfo staffBasicnfo);

	/**
	 * 修改中转站信息
	 * 
	 * @param transferStation
	 * @return
	 */

	String updateTransferStation(unit transferStation);


}
