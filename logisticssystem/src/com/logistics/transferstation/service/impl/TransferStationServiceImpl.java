package com.logistics.transferstation.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.logistics.domain.unit;
import com.logistics.transferstation.VO.TransferStationVO;
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

	/**
	 * 添加中转站
	 */

	@Override
	public String addTransferStation(unit transferStation) {
		transferStation.setUnit_id(BuildUuid.getUuid());
		transferStation.setUnit_createtime(TimeUtil.getStringSecond());
		transferStation.setUnit_modifytime(TimeUtil.getStringSecond());
		transferStationDao.saveOrUpdateObject(transferStation);
		System.out.println("2222222");
		return "success";
	}

	/**
	 * 删除中转站
	 */

	@Override
	public String deleteTransferStation(unit transferStation) {
		unit delete = new unit();
		delete.setUnit_id(transferStation.getUnit_id());
		delete.setUnit_num(transferStation.getUnit_num());
		delete.setUnit_name(transferStation.getUnit_name());
		delete.setUnit_address(transferStation.getUnit_address());
		delete.setUnit_detailaddress(transferStation.getUnit_detailaddress());
		delete.setUnit_type(transferStation.getUnit_type());
		delete.setUnit_superiorunit(transferStation.getUnit_superiorunit());
		delete.setUnit_creator(transferStation.getUnit_creator());
		delete.setUnit_state(transferStation.getUnit_state());
		delete.setUnit_admin(transferStation.getUnit_admin());
		delete.setUnit_createtime(transferStation.getUnit_createtime());
		delete.setUnit_modifytime(transferStation.getUnit_modifytime());
		delete.setUnit_phonenumber(transferStation.getUnit_phonenumber());
		transferStationDao.removeObject(delete);

		return "success";
	}


	/**
	 * 修改中转站信息
	 */
	@Override
	public String updataTransferStation(unit transferStation) {
		unit updata = new unit();
		updata.setUnit_id(transferStation.getUnit_id());
		updata.setUnit_num(transferStation.getUnit_num());
		updata.setUnit_name(transferStation.getUnit_name());
		updata.setUnit_address(transferStation.getUnit_address());
		updata.setUnit_detailaddress(transferStation.getUnit_detailaddress());
		updata.setUnit_type(transferStation.getUnit_type());
		updata.setUnit_superiorunit(transferStation.getUnit_superiorunit());
		updata.setUnit_creator(transferStation.getUnit_creator());
		updata.setUnit_state(transferStation.getUnit_state());
		updata.setUnit_admin(transferStation.getUnit_admin());
		updata.setUnit_createtime(transferStation.getUnit_createtime());
		updata.setUnit_modifytime(transferStation.getUnit_modifytime());
		updata.setUnit_phonenumber(transferStation.getUnit_phonenumber());
		transferStationDao.saveOrUpdateObject(updata);
		return "success";
	}

	/**
	 * 查询中转站
	 */
    public List<unit> getUnit(){
    	List<unit> listunit;
    	/**
    	 * 调用DAO层listObject方法
    	 */
    	listunit = (List<unit>) transferStationDao.listObject("from unit ");
		return listunit;
    	
    	
    }
	@Override
	public TransferStationVO queryTransferStation(TransferStationVO transferStationVO) {
		List<unit> listunit = new ArrayList<>();
		String TransferStationCountHql = "select count(*) from unit";
		return null;
	}



	
}
