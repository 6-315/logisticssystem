package com.logistics.transferstation.service;

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
	String deleteTransferStation(unit transferStation);

	/**
	 * 分页显示用户信息
	 * 
	 * @param transferStationVO
	 * @return
	 */

	UnitManagerVO queryTransferStation(UnitManagerVO transferStationVO);

	/**
	 * 修改用户信息
	 * 
	 * @param transferStation
	 * @return
	 */

	String updateTransferStation(unit transferStation);


}
