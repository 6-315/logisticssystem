package com.logistics.transferstation.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

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
	public String deleteTransferStation(UnitManagerVO unitManagerVO) {
		/*
		 * 将获得的对象转化为数组
		 */
		String[] deleteTransferStationById = unitManagerVO.getIdList().split(",");
		/**
		 * 遍历需要删除的中转站数组
		 */
		for (String idList : deleteTransferStationById) {
			/**
			 * 如果数据库存在需要删除的中转站的id
			 */
			if (transferStationDao.getTransferStationInfoById(idList) != null) {
				transferStationDao.removeObject(transferStationDao.getTransferStationInfoById(idList));
				System.out.println("shanchuchenggong111111");
				return "success";
			}
			/**
			 * 如果数据库不存在需要删除的中转站的id
			 */
			else {
				System.out.println("删除失败");
				return "fail";
			}

		}

		return null;

	}

	/**
	 * 修改中转站信息
	 */
	@Override
	public String updateTransferStation(unit transferStation) {
		// 实例化一个更改信息的对象
		unit update = new unit();
		// 调用DAO层里根据得到
		update = transferStationDao.getTransferStationInfoById(transferStation.getUnit_id());
		update.setUnit_address(transferStation.getUnit_address());
		update.setUnit_state(transferStation.getUnit_state());
		update.setUnit_admin(transferStation.getUnit_admin());
		transferStationDao.saveOrUpdateObject(update);
		return "success";
	}

	/**
	 * 总公司能所有查询中转站
	 */
	@Override
	public UnitManagerVO queryTransferStation(UnitManagerVO transferStationVO,staff_basicinfo staffBasicinfo) {
		// 实例化List<UnitManagerDTO>
		List<UnitManagerDTO> listUnitManagerDTO = new ArrayList<>();
		// 创建一个UnitManagerDTO对象
		UnitManagerDTO unitManagerDTO;
		// 实例化List<unit>
		List<unit> listUnit = new ArrayList<>();
		// sql语句 查询unit表中有多少条数据
		String transferStationCountHql = "select count(*) from unit where 1=1 ";
		// sql语句 查询unit表中每一条数据
		String listTransferStationHql = "from unit where 1=1 ";
		// 查询管理员信息
		/**
		 * 如果unit表中的Unit_id不为空 并且unit表中的Unit_id等于员工信息表中的管理员
		 */
		/**
		 * 模糊查询
		 */
		if (transferStationVO.getSearch() != null && transferStationVO.getSearch().trim().length() > 0) {
			String search = "%" + transferStationVO.getSearch().trim() + "%";
			transferStationCountHql = transferStationCountHql + " and unit_name like '" + search + "' ";
			listTransferStationHql = listTransferStationHql + " and unit_name like '" + search + "'";
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

		// 这里如果不加desc表示正序，如果加**/上desc表示倒序
		transferStationCountHql = transferStationCountHql + "order by unit_createtime desc";
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
		UnitManagerVO unitManagerVO = new UnitManagerVO();
			System.out.println("0.0.0.0.0.0");
			listUnit = (List<unit>) transferStationDao.queryForPage(listTransferStationHql,
					transferStationVO.getPageIndex(), transferStationVO.getPageSize());
			// 遍历unit表
			for (unit unit : listUnit) {
				//根据id获取职位
				staff_basicinfo staff_basicinfo =new staff_basicinfo();
				staff_basicinfo = transferStationDao.getBasicinfoById(staffBasicinfo.getStaff_id());
				//判断职位
				if(staff_basicinfo.getStaff_position()!= null ) {}
				// 查询创建者的信息
				staff_basicinfo unit_Creator = transferStationDao.getBasicinfoById(unit.getUnit_creator());
				// 查询管理员信息
				staff_basicinfo unit_Admin = transferStationDao.getBasicinfoById(unit.getUnit_admin());
				// 模糊查询显示高亮
				if (transferStationVO.getSearch() != null && transferStationVO.getSearch().trim().length() > 0) {
					unit.setUnit_name(unit.getUnit_name().replaceAll(transferStationVO.getSearch(),
							"<mark>" + transferStationVO.getSearch() + "</mark>"));
					System.out.println("987654321");
				}
				// 实例化unitManagerDTO
				unitManagerDTO = new UnitManagerDTO();
				// 把Admin和Creator set进unitManagerDTO
				unitManagerDTO.setUnit_Admin(unit_Admin);
				unitManagerDTO.setUnit_Creator(unit_Creator);
				// 把unit set进unitManagerDTO
				unitManagerDTO.setUnit(unit);
				// 将DTO放在listDTO
				listUnitManagerDTO.add(unitManagerDTO);
			}
			// 将listDTO放在VO里面
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
			 * return null; }
			 * 
			 */
}
