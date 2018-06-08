package com.logistics.transferstation.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.jasper.tagplugins.jstl.core.ForEach;

import com.logistics.domain.staff_basicinfo;
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
	/**
	 * 注入DAO层
	 */
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
		/*
		 * update.setUnit_id(transferStation.getUnit_id());
		 * update.setUnit_num(transferStation.getUnit_num());
		 * update.setUnit_name(transferStation.getUnit_name());
		 * update.setUnit_address(transferStation.getUnit_address());
		 * update.setUnit_detailaddress(transferStation.getUnit_detailaddress());
		 * update.setUnit_type(transferStation.getUnit_type());
		 * update.setUnit_superiorunit(transferStation.getUnit_superiorunit());
		 * update.setUnit_creator(transferStation.getUnit_creator());
		 * update.setUnit_state(transferStation.getUnit_state());
		 * update.setUnit_admin(transferStation.getUnit_admin());
		 * update.setUnit_createtime(transferStation.getUnit_createtime());
		 * update.setUnit_modifytime(transferStation.getUnit_modifytime());
		 * update.setUnit_phonenumber(transferStation.getUnit_phonenumber());
		 */
		transferStationDao.saveOrUpdateObject(update);
		return "success";
	}

	/**
	 * 查询中转站
	 */
	public List<unit> getUnit() {
		List<unit> listunit;
		/**
		 * 调用DAO层listObject方法
		 */
		listunit = (List<unit>) transferStationDao.listObject("from unit ");
		return listunit;

	}

	@Override
	public UnitManagerVO queryTransferStation(UnitManagerVO transferStationVO) {
		List<UnitManagerDTO> listUnitManagerDTO = new ArrayList<>();
		UnitManagerDTO unitManagerDTO;
		List<unit> listUnit = new ArrayList<>();
		String transferStationCountHql = "select count(*) from unit where 1=1 ";
		String listTransferStationHql = "from unit where 1=1 ";
		/**
		 * 模糊查询
		 */
		if (transferStationVO.getSearch() != null && transferStationVO.getSearch().trim().length() > 0) {
			String search = "%" + transferStationVO.getSearch().trim() + "%";
			transferStationCountHql = transferStationCountHql + " and unit_num like '" + search + "' ";
			listTransferStationHql = listTransferStationHql + " and unit_num like '" + search + "'";
		}
		/**
		 * 根据address查询
		 */
		if (transferStationVO.getAddress() != null && transferStationVO.getAddress().trim().length() > 0) {
			transferStationCountHql = transferStationCountHql + " and  unit_address = '"
					+ transferStationVO.getAddress().trim() + "' ";
			listTransferStationHql = listTransferStationHql + " and unit_address = '"
					+ transferStationVO.getAddress().trim() + "'  ";
		}
		/**
		 * 根据State查询
		 */
		if (transferStationVO.getState() != null && transferStationVO.getState().trim().length() > 0) {
			transferStationCountHql = transferStationCountHql + " and unit_state = '"
					+ transferStationVO.getState().trim() + "'";
			listTransferStationHql = listTransferStationHql + " and unit_state = '"
					+ transferStationVO.getState().trim() + "'";
		}
		/**
		 * 根据num查询
		 */
		if (transferStationVO.getNum() != null && transferStationVO.getNum().trim().length() > 0) {
			transferStationCountHql = transferStationCountHql + " and unit_num = '" + transferStationVO.getNum().trim()
					+ "'";
			listTransferStationHql = listTransferStationHql + " and unit_num = '" + transferStationVO.getNum().trim()
					+ "'";
		}
		/**
		 * 根据superiorunit查询
		 */
		if (transferStationVO.getSuperiorunit() != null && transferStationVO.getSuperiorunit().trim().length() > 0) {
			transferStationCountHql = transferStationCountHql + " and unit_superiorunit = '"
					+ transferStationVO.getSuperiorunit().trim() + "'";
			listTransferStationHql = listTransferStationHql + " and unit_superiorunit = '"
					+ transferStationVO.getSuperiorunit().trim() + "'";
		}

		/**
		 * 分页
		 */
		System.out.println("fdfdfd:-----------" + transferStationCountHql);
		int basicinfoCount = transferStationDao.getCount(transferStationCountHql);
		System.out.println(basicinfoCount);
		// 设置总数量
		transferStationVO.setTotalRecords(basicinfoCount);
		// 设置总页数
		transferStationVO.setTotalPages(((basicinfoCount - 1) / transferStationVO.getPageSize()) + 1);
		// 判断是否拥有上一页
		if (transferStationVO.getPageIndex() <= 1) {
			transferStationVO.setHavePrePage(false);
		} else {
			transferStationVO.setHavePrePage(true);
		}
		// 判断是否拥有下一页
		if (transferStationVO.getPageIndex() >= transferStationVO.getTotalPages()) {
			transferStationVO.setHaveNextPage(false);
		} else {
			transferStationVO.setHaveNextPage(true);
		}
		System.out.println("qqqqqq" + transferStationCountHql);
		System.out.println("aaaaa" + listTransferStationHql);
		listUnit = (List<unit>) transferStationDao.queryForPage(listTransferStationHql,
				transferStationVO.getPageIndex(), transferStationVO.getPageSize());
		for (unit unit : listUnit) {
			// 查询创建者的信息
			staff_basicinfo unit_creator = transferStationDao.getBasicinfo(unit.getUnit_creator());
			staff_basicinfo unit_admin = transferStationDao.getBasicinfo(unit.getUnit_admin());
			unitManagerDTO = new UnitManagerDTO();
			unitManagerDTO.setUnit_admin(unit_admin);
			unitManagerDTO.setUnit_creator(unit_creator);
			listUnitManagerDTO.add(unitManagerDTO);
		}
		transferStationVO.setListUnitManagerDTO(listUnitManagerDTO);
		return transferStationVO;
	}

	/**
	 * DTO
	 */

	/*
	 * public UnitManagerDTO getUnitManagerDTO(String unit_id) { if (unit_id == null
	 * || unit_id.trim().length() <= 0) { return null; }
	 *//**
		 * new一个DTO
		 *//*
			 * UnitManagerDTO unitManagerDTO = new UnitManagerDTO(); staff_basicinfo
			 * unit_creator = new staff_basicinfo (); staff_basicinfo news_NewsInfo = new
			 * staff_basicinfo();
			 * 
			 * 
			 * return null;
			 * 
			 * 
			 * 
			 * }
			 * 
			 */
}
