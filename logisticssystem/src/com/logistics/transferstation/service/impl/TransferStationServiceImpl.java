package com.logistics.transferstation.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.logistics.domain.unit;
import com.logistics.transferstation.DTO.UnitManagerDTO;
import com.logistics.transferstation.VO.UnitManagerVO;
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
	
		transferStationDao.removeObject(transferStation);

		return "success";
	}


	/**
	 * 修改中转站信息
	 */
	@Override
	public String updateTransferStation(unit transferStation) {
		unit update = new unit();
		update = transferStationDao.getTransferStationInfoById(transferStation.getUnit_id());
		update.setUnit_address(transferStation.getUnit_address());
		update.setUnit_state(transferStation.getUnit_state());
		update.setUnit_admin(transferStation.getUnit_admin());
		/*update.setUnit_id(transferStation.getUnit_id());
		update.setUnit_num(transferStation.getUnit_num());
		update.setUnit_name(transferStation.getUnit_name());
		update.setUnit_address(transferStation.getUnit_address());
		update.setUnit_detailaddress(transferStation.getUnit_detailaddress());
		update.setUnit_type(transferStation.getUnit_type());
		update.setUnit_superiorunit(transferStation.getUnit_superiorunit());
		update.setUnit_creator(transferStation.getUnit_creator());
		update.setUnit_state(transferStation.getUnit_state());
		update.setUnit_admin(transferStation.getUnit_admin());
		update.setUnit_createtime(transferStation.getUnit_createtime());
		update.setUnit_modifytime(transferStation.getUnit_modifytime());
		update.setUnit_phonenumber(transferStation.getUnit_phonenumber());*/
		transferStationDao.saveOrUpdateObject(update);
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
	public UnitManagerVO queryTransferStation(UnitManagerVO transferStationVO) {
		List<unit> listunit = new ArrayList<>();
		String TransferStationCountHql = "select count(*) from unit";
		return null;
	}

	@Override
	public List<UnitManagerDTO> getListUnitManagerDTO() {
		return null;
	}



	
}
