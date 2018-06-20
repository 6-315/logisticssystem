package com.logistics.transferstation.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.logistics.domain.driver;
import com.logistics.domain.staff_basicinfo;
import com.logistics.domain.team;
import com.logistics.domain.unit;
import com.logistics.domain.vehicle;
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
	 * 添加中转站并自动生成编号
	 */

	@Override
	public String addTransferStation(unit transferStation) {
		System.out.println("fdfdfd");
		unit unit = new unit();
		String maxNum = transferStationDao.getTransferStationByNum(unit.getUnit_num());
		System.out.println("iiiii" + maxNum);

		if (maxNum != null) {
			maxNum = maxNum.replaceAll("[A]", "");
			int nextNum = Integer.parseInt(maxNum);
			nextNum = nextNum + 1;
			String num = String.format("A%02d", nextNum);
			transferStation.setUnit_num(num);
			System.out.println("sandanand" + num);
		} else {
			int nextNum = 1;
			String num = String.format("A%02d", nextNum);
			transferStation.setUnit_num(num);
			System.out.println("lalalalala" + num);
		}

		transferStation.setUnit_id(BuildUuid.getUuid());
		transferStation.setUnit_createtime(TimeUtil.getStringSecond());
		transferStation.setUnit_modifytime(TimeUtil.getStringSecond());
		transferStationDao.saveOrUpdateObject(transferStation);
		return "success";
	}

	/**
	 * 删除中转站
	 */

	@Override
	public String deleteTransferStation(String idList) {
		if (idList != null && idList.trim().length() > 0) {
			/*
			 * 将获得的对象转化为数组
			 */
			String[] deleteTransferStationById = idList.split(",");
			/**
			 * 遍历需要删除的中转站数组
			 */
			for (String id : deleteTransferStationById) {
				/**
				 * 如果数据库存在需要删除的中转站的id
				 */
				if (transferStationDao.getTransferStationInfoById(id) != null) {
					transferStationDao.removeObject(transferStationDao.getTransferStationInfoById(id));
					System.out.println("shanchuchenggong111111");
					return "deleteSuccess";
				}
				/**
				 * 如果数据库不存在需要删除的中转站的id
				 */
				else {
					System.out.println("删除失败");
					return "deleteFailed";
				}
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
	public UnitManagerVO queryTransferStation(UnitManagerVO transferStationVO, staff_basicinfo staffBasicinfo) {
		// 实例化List<UnitManagerDTO>
		List<UnitManagerDTO> listUnitManagerDTO = new ArrayList<>();
		// 创建一个UnitManagerDTO对象
		UnitManagerDTO unitManagerDTO = null;
		// 实例化List<unit>
		List<unit> listUnit = new ArrayList<>();
		// sql语句 查询unit表中有多少条数据
		String transferStationCountHql = "select count(*) from unit where 1=1 ";
		// sql语句 查询unit表中每一条数据
		String listTransferStationHql = "from unit where 1=1 ";
		// 查询管理员信息

		/**
		 * 根据单位名字和单位类型模糊查询
		 */
		if (transferStationVO.getSearch() != null && transferStationVO.getSearch().trim().length() > 0) {
			String search = "%" + transferStationVO.getSearch().trim() + "%";
			transferStationCountHql = transferStationCountHql + " and (unit_name like '" + search + "' ";
			listTransferStationHql = listTransferStationHql + " and (unit_name like '" + search + "'";
			transferStationCountHql = transferStationCountHql + "or unit_type like ' " + search + "')";
			listTransferStationHql = listTransferStationHql + "or unit_type like '" + search + "')";
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
		/**
		 * 分页获取单位列表
		 */
		listUnit = (List<unit>) transferStationDao.queryForPage(listTransferStationHql,
				transferStationVO.getPageIndex(), transferStationVO.getPageSize());

		// 遍历unit表
		for (unit unit : listUnit) {
			staff_basicinfo unit_Creator = transferStationDao.getBasicinfoById(unit.getUnit_creator());
			staff_basicinfo unit_Admin = transferStationDao.getBasicinfoById(unit.getUnit_admin());

			// 模糊查询显示高亮
			if (transferStationVO.getSearch() != null && transferStationVO.getSearch().trim().length() > 0) {
				unit.setUnit_name(unit.getUnit_name().replaceAll(transferStationVO.getSearch(),
						"<mark>" + transferStationVO.getSearch() + "</mark>"));
				System.out.println("987654321");
			}
			// 实例化unitManagerDTO
			unitManagerDTO = new UnitManagerDTO();
			// 把unit_Creator和unit_Admin set进unitManagerDTO
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
	 * 车辆批量分配
	 * 
	 * vehicleList是一个数组 我需要得到每一辆车是不是没有所属车队（错误） 但是车辆又在一个集合里面 能不能把数组转化成对象进行操作
	 * 
	 * 
	 */
	public String vehicleDistribution(String vehicleList, String teamNum) {
		team team = transferStationDao.getTeamById(teamNum);
		System.out.println("888888"+team);
		if (team != null) {
			String[] vehicleListDistribute = vehicleList.split(",");
			for (String eachVehicleId : vehicleListDistribute) {
				/**
				 * 如果每辆车不为空
				 */
				System.out.println("进入循环");
				if (eachVehicleId != null && eachVehicleId.trim().length() > 0) {
					vehicle vehicle = transferStationDao.getVehicleById(eachVehicleId);
					System.out.println("ghghghg" + vehicle);
					if (vehicle != null) {
						
						System.out.println("qwqwqw");
						vehicle.setVehicle_team(teamNum);
						vehicle.setVehicle_createtime(TimeUtil.getStringSecond());
						vehicle.setVehicle_modifytime(TimeUtil.getStringSecond());
						System.out.println("分配成功");
					} else {
						System.out.println("分配失败");
						return "fail";
					}
				} else {
					System.out.println("分配失败");
					return "fail";
				}

			}
		}else {
			System.out.println("meinjin");
			return "fail";
		}
		
		return "fail";
	}
	/**
	 * 招募司机
	 */
	public String driverRecruit(staff_basicinfo driver) {
		driver.setStaff_id(BuildUuid.getUuid());
		driver.setStaff_createtime(TimeUtil.getStringSecond());
		driver.setStaff_modifytime(TimeUtil.getStringSecond());
		transferStationDao.saveOrUpdateObject(driver);
		return "success";
	}
	/**
	 * 分配司机到车队
	 */

	@Override
	public String driverDistribution(String driverList, String teamNum) {
		/**
		 * 如果车队编号不为空
		 */
		team team = transferStationDao.getTeamById(teamNum);
		System.out.println("6666666"+team);
		if (team != null) {
			/**
			 * 实例化一个司机集合
			 */
			String[] driverListDistribute = driverList.split(",");
			/**
			 * 遍历这个集合
			 */
			for (String eachDriverId : driverListDistribute) {
				/**
				 * 如果每个司机不为空
				 */
				System.out.println("进入循环");
				if (eachDriverId != null && eachDriverId.trim().length() > 0) {
					driver driver = transferStationDao.getDriverById(eachDriverId);
					System.out.println("ghghghg" + driver);
					if (driver != null) {
						
						System.out.println("qwqwqw");
						driver.setDriver_belong_team(teamNum);
						driver.setDriver_createtime(TimeUtil.getStringSecond());
						driver.setDriver_modifytime(TimeUtil.getStringSecond());
						System.out.println("分配成功");
						return "success";
					} else {
						System.out.println("分配失败");
						return "fail";
					}
				} else {
					System.out.println("分配失败");
					return "fail";
				}

			}
		}else {
			
			System.out.println("meinjin");
			return "fail";
		}
		
		return "fail";
	}
	
	
}
