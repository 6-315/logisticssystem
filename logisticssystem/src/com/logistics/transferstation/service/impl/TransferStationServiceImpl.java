package com.logistics.transferstation.service.impl;

import com.logistics.domain.unit;
import com.logistics.transferstation.dao.TransferStationDao;
import com.logistics.transferstation.service.TransferStationService;

import util.BuildUuid;
import util.TimeUtil;


/**
 * 中转站管理的业务实现层
 * 
 * @author LW
 *
 */
public class TransferStationServiceImpl implements TransferStationService {
	private TransferStationDao transferStationDao;

	public void setTransferStationDao(TransferStationDao transferStationDao) {
		this.transferStationDao = transferStationDao;
	}

	

	@Override
	public String addTransferStation(unit transferStation) {
		transferStation.setUnit_id(BuildUuid.getUuid());
		transferStation.setUnit_createtime(TimeUtil.getStringSecond());
		return null;
	}

	
}
