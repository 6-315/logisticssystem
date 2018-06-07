package com.logistics.transferstation.service;


import java.util.List;

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

	

	String addTransferStation(unit transferStation);

	String deleteTransferStation(unit transferStation);

	UnitManagerVO queryTransferStation(UnitManagerVO transferStationVO);

	String updateTransferStation(unit transferStation);

	List<UnitManagerDTO> getListUnitManagerDTO();






}
