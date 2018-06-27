package com.logistics.transferstation.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.logistics.domain.driver;
import com.logistics.domain.position;
import com.logistics.domain.staff_basicinfo;
import com.logistics.domain.team;
import com.logistics.domain.unit;
import com.logistics.domain.vehicle;
import com.logistics.transferstation.DTO.DriverManagerDTO;
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
	 * 添加单位并自动生成编号
	 */

	@Override
	public unit addTransferStation(unit transferStation, staff_basicinfo staffBasicInfo) {
		if (transferStation.getUnit_id() == null || transferStation.getUnit_id() == "") {
			unit unit = new unit();
			position position = new position();
			if (staffBasicInfo != null) {
				position = transferStationDao.getPositionById(staffBasicInfo.getStaff_position());
				if (position != null && position.getPosition_name().equals("中转站管理员")) {
					String maxNum = transferStationDao.getDistributionByNum(unit.getUnit_num());
					unit belongUnit = transferStationDao.getTransferStationInfoById(staffBasicInfo.getStaff_unit());
					String beforeNum = belongUnit.getUnit_num();
					if (maxNum != null) {
						int nextNum = Integer.parseInt(maxNum);
						nextNum = nextNum + 1;
						String num = String.format("%02d", nextNum);
						transferStation.setUnit_num(beforeNum + "B" + num);

					} else {
						int nextNum = 1;
						String num = String.format("%02d", nextNum);
						transferStation.setUnit_num(beforeNum + "B" + num);
					}
				} else if (position != null && position.getPosition_name().equals("总公司管理员")) {
					String maxNum = transferStationDao.getTransferStationByNum(unit.getUnit_num());
					if (maxNum != null) {
						maxNum = maxNum.replaceAll("[A]", "");
						int nextNum = Integer.parseInt(maxNum);
						nextNum = nextNum + 1;
						String num = String.format("%02d", nextNum);
						transferStation.setUnit_num("A" + num);
					} else {
						int nextNum = 1;
						String num = String.format("%02d", nextNum);
						transferStation.setUnit_num("A" + num);
					}
				}
			}
			transferStation.setUnit_id(BuildUuid.getUuid());
			transferStation.setUnit_state("未启用");
			transferStation.setUnit_creator(staffBasicInfo.getStaff_id());
			transferStation.setUnit_superiorunit(staffBasicInfo.getStaff_unit());
			transferStation.setUnit_createtime(TimeUtil.getStringSecond());
			transferStation.setUnit_modifytime(TimeUtil.getStringSecond());
			transferStationDao.saveOrUpdateObject(transferStation);
		} else {

			transferStationDao.saveOrUpdateObject(transferStation);
		}
		return transferStation;
	}

	/**
	 * 删除单位
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
					return "deleteSuccess";
				}
				/**
				 * 如果数据库不存在需要删除的中转站的id
				 */
				else {
					return "deleteFailed";
				}
			}
		}
		return null;

	}

	/**
	 * 修改单位信息
	 */
	@Override
	public String updateTransferStation(unit transferStation) {
		// 实例化一个更改信息的对象
		unit updateUnit = transferStation;
		updateUnit.setUnit_modifytime(TimeUtil.getStringSecond());
		transferStationDao.saveOrUpdateObject(updateUnit);

		return "success";
	}

	/**
	 * 总公司能所有查询单位
	 */
	@Override
	public UnitManagerVO queryTransferStation(UnitManagerVO transferStationVO, staff_basicinfo staffBasicInfo) {
		// 实例化List<UnitManagerDTO>
		List<UnitManagerDTO> listUnitManagerDTO = new ArrayList<>();
		// 创建一个UnitManagerDTO对象
		UnitManagerDTO unitManagerDTO = null;
		// 实例化List<unit>
		List<unit> listUnit = new ArrayList<>();
		/*
		 * // sql语句 查询unit表中有多少条数据 String transferStationCountHql =
		 * "select count(*) from unit where 1=1 "; // sql语句 查询unit表中每一条数据 String
		 * listTransferStationHql = "from unit where 1=1 "; // 查询管理员信息
		 *//**
			 * 根据单位名字和单位编号单位地址模糊查询
			 */
		/*
		 * if (transferStationVO.getSearch() != null &&
		 * transferStationVO.getSearch().trim().length() > 0) { String search = "%" +
		 * transferStationVO.getSearch().trim() + "%"; transferStationCountHql =
		 * transferStationCountHql + " and (unit_name like '" + search + "'";
		 * listTransferStationHql = listTransferStationHql + " and (unit_name like '" +
		 * search + "'"; transferStationCountHql = transferStationCountHql +
		 * "or unit_num like ' " + search + "'"; listTransferStationHql =
		 * listTransferStationHql + "or unit_num like '" + search + "'";
		 * transferStationCountHql = transferStationCountHql + "or unit_address like'" +
		 * search + "')"; listTransferStationHql = listTransferStationHql +
		 * "or unit_address like'" + search + "')"; }
		 *//**
			 * 根据unit_type查询
			 */
		/*
		 * if (transferStationVO.getType() != null &&
		 * transferStationVO.getType().trim().length() > 0) { transferStationCountHql =
		 * transferStationCountHql + " and  unit_type = '" +
		 * transferStationVO.getType().trim() + "' "; listTransferStationHql =
		 * listTransferStationHql + " and unit_type = '" +
		 * transferStationVO.getType().trim() + "'  "; }
		 *//**
			 * 根据State查询
			 *//*
				 * if (transferStationVO.getState() != null &&
				 * transferStationVO.getState().trim().length() > 0) { transferStationCountHql =
				 * transferStationCountHql + " and unit_state = '" +
				 * transferStationVO.getState().trim() + "'"; listTransferStationHql =
				 * listTransferStationHql + " and unit_state = '" +
				 * transferStationVO.getState().trim() + "'"; }
				 */
		/**
		 * 分页获取自身单位，以及自身以下单位信息
		 */
		String listTransferStationHql = "";
		String transferStationCountHql = "";
		if (staffBasicInfo != null) {
			unit staff_unit = transferStationDao.getTransferStationInfoById(staffBasicInfo.getStaff_unit());
			position positionNew = new position();
			positionNew = transferStationDao.getPositionById(staffBasicInfo.getStaff_position());
			if ("总公司管理员".equals(positionNew.getPosition_name())) {
				transferStationCountHql = "select count(*) from unit where 1=1 ";

				listTransferStationHql = "from unit where 1=1 ";
			} else if ("中转站管理员".equals(positionNew.getPosition_name())) {
				System.out.println("????");
				transferStationCountHql = "select count(*) from unit where 1=1 and (unit_superiorunit = '"
						+ staffBasicInfo.getStaff_unit() + "' or unit_id='" + staffBasicInfo.getStaff_unit() + "') ";

				listTransferStationHql = "from unit where 1=1 and (unit_superiorunit = '"
						+ staffBasicInfo.getStaff_unit() + "' or  unit_id='" + staffBasicInfo.getStaff_unit() + "')";
			} else if ("配送点管理员".equals(positionNew.getPosition_name())) {
				transferStationCountHql = "select count(*) from unit where unit_id='" + staffBasicInfo.getStaff_unit()
						+ "'";
				listTransferStationHql = "from unit where unit_id='" + staffBasicInfo.getStaff_unit() + "'";
			}

			/**
			 * 根据单位名字和单位编号单位地址模糊查询
			 */
			if (transferStationVO.getSearch() != null && transferStationVO.getSearch().trim().length() > 0) {
				String search = "%" + transferStationVO.getSearch().trim() + "%";
				transferStationCountHql = transferStationCountHql + " and (unit_name like '" + search + "'";
				listTransferStationHql = listTransferStationHql + " and (unit_name like '" + search + "'";
				transferStationCountHql = transferStationCountHql + "or unit_num like ' " + search + "'";
				listTransferStationHql = listTransferStationHql + "or unit_num like '" + search + "'";
				transferStationCountHql = transferStationCountHql + "or unit_address like'" + search + "')";
				listTransferStationHql = listTransferStationHql + "or unit_address like'" + search + "')";
			}
			/**
			 * 根据unit_type查询
			 */
			if (transferStationVO.getType() != null && transferStationVO.getType().trim().length() > 0) {
				transferStationCountHql = transferStationCountHql + " and  unit_type ='"
						+ transferStationVO.getType().trim() + "' ";
				listTransferStationHql = listTransferStationHql + " and unit_type ='"
						+ transferStationVO.getType().trim() + "'  ";
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
			 * 分页获取自身单位，以及自身以下单位信息
			 */

			/*
			 * transferStationCountHql = transferStationCountHql + " and (unit_id ='" +
			 * staff_unit.getUnit_id() + "' or unit_superiorunit='" +
			 * staff_unit.getUnit_id() + "' )"; listTransferStationHql =
			 * listTransferStationHql + " and (unit_id ='" + staff_unit.getUnit_id() +
			 * "' or unit_superiorunit='" + staff_unit.getUnit_id() + "' )";
			 * System.out.println("wsawedasegfg" + staff_unit);
			 */

		}
		/**
		 * 分页
		 */

		// 这里如果不加desc表示正序，如果加**/上desc表示倒序
		transferStationCountHql = transferStationCountHql + " order by unit_createtime desc";
		int basicinfoCount = transferStationDao.getCount(transferStationCountHql);
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
		UnitManagerVO unitManagerVO = new UnitManagerVO();
		/**
		 * 分页获取单位列表
		 */
		listUnit = (List<unit>) transferStationDao.queryForPage(listTransferStationHql,
				transferStationVO.getPageIndex(), transferStationVO.getPageSize());

		// 遍历unit表
		for (unit unit : listUnit) {
			// 实例化unitManagerDTO
			unitManagerDTO = new UnitManagerDTO();

			// unit unitAll =
			// transferStationDao.getTransferStationInfoById(unit.getUnit_id());

			/**
			 * 获取单位创建者的信息
			 */
			staff_basicinfo unit_Creator = transferStationDao.getBasicinfoById(unit.getUnit_creator());
			// 把unit_Creator set进unitManagerDTO
			if (unit_Creator != null) {
				unitManagerDTO.setUnit_Creator(unit_Creator);
			}
			/**
			 * 获取单位管理员信息
			 */
			staff_basicinfo unit_Admin = transferStationDao.getBasicinfoById(unit.getUnit_admin());
			if (unit_Admin != null) {
				unitManagerDTO.setUnit_Admin(unit_Admin);
			}
			// 模糊查询显示高亮
			if (transferStationVO.getSearch() != null && transferStationVO.getSearch().trim().length() > 0) {
				unit.setUnit_name(unit.getUnit_name().replaceAll(transferStationVO.getSearch(),
						"<span style='color: #ff5063;'> " + transferStationVO.getSearch() + "</span>"));

				unit.setUnit_address(unit.getUnit_address().replaceAll(transferStationVO.getSearch(),
						"<span style='color: #ff5063;'>" + transferStationVO.getSearch() + "</span>"));

				unit.setUnit_num(unit.getUnit_num().replaceAll(transferStationVO.getSearch(),
						"<span style='color: #ff5063;'>" + transferStationVO.getSearch() + "</span>"));
			}

			// 将DTO放在listDTO
			unitManagerDTO.setUnit(unit);
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
		if (team != null) {
			String[] vehicleListDistribute = vehicleList.split(",");
			for (String eachVehicleId : vehicleListDistribute) {
				/**
				 * 如果每辆车不为空
				 */
				vehicle vehicle = transferStationDao.getVehicleById(eachVehicleId);
				driver driver = transferStationDao.getDriverByVehicle_id(eachVehicleId);
				if (driver != null) {
					driver.setDriver_vehicle("");
					}
					if (vehicle != null) {
						vehicle.setVehicle_team(teamNum);
						vehicle.setVehicle_createtime(TimeUtil.getStringSecond());
						vehicle.setVehicle_modifytime(TimeUtil.getStringSecond());
						transferStationDao.saveOrUpdateObject(vehicle);
						
					}
				

			}
			return "success";
		} else {
			return "fail";
		}
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
					driver driver = transferStationDao.getDriverById(eachDriverId);
					System.out.println("ghghghg" + driver);
					staff_basicinfo driverNew = transferStationDao.getBasicinfoById(eachDriverId);
					if (driver != null && driverNew != null) {
						driverNew.setStaff_superiorleader(team.getTeam_leader());
						driver.setDriver_belong_team(teamNum);
						driver.setDriver_createtime(TimeUtil.getStringSecond());
						driver.setDriver_modifytime(TimeUtil.getStringSecond());
						transferStationDao.saveOrUpdateObject(driver);
					} 
					return "success";
			}
		} else {
			return "fail";
		}
		return "fail";
	}

	/**
	 * 获取自身单位及以下单位信息
	 */
	@Override
	public List<unit> getUnitInfo(staff_basicinfo staffBasicInfo) {
		unit unitNew = new unit();
		position positionNew = new position();
		List<unit> listunit = new ArrayList<>();
		if (staffBasicInfo != null && staffBasicInfo.getStaff_unit() != null) {
			positionNew = transferStationDao.getPositionById(staffBasicInfo.getStaff_position());
			System.out.println("hyhyhy" + positionNew);
			if (positionNew != null && positionNew.getPosition_name().equals("总公司管理员")) {
				listunit = (List<unit>) transferStationDao.listObject("from unit ");
				return listunit;
			}
			if (positionNew != null && unitNew != null && positionNew.getPosition_name().equals("中转站管理员")) {

				System.out.println("sdsdsdsd");
				listunit = (List<unit>) transferStationDao
						.listObject("from unit where (unit_id ='" + staffBasicInfo.getStaff_unit()
								+ "' or unit_superiorunit='" + staffBasicInfo.getStaff_unit() + "')");
				return listunit;
			}
			if (positionNew != null && positionNew.getPosition_name().equals("配送点管理员")) {
				listunit = (List<unit>) transferStationDao
						.listObject("from unit where (unit_id ='" + staffBasicInfo.getStaff_unit()
								+ "' or unit_superiorunit='" + staffBasicInfo.getStaff_unit() + "')");
				return listunit;
			}
		}
		return null;
	}

	/**
	 * 获取所有未分配车辆的司机
	 */

	@Override
	public List<DriverManagerDTO> getDiverUnDistributed(DriverManagerDTO driverManagerDTO,
			staff_basicinfo staffBasicInfo) {
		/**
		 * list一个DTO
		 */
		List<DriverManagerDTO> listDriverManagerDTO = new ArrayList<>();
		if (staffBasicInfo != null) {
			/**
			 * 根据session中车队队长id获取车队
			 */
			team team = new team();
			team = transferStationDao.getTeamByLeader(staffBasicInfo.getStaff_id());
			if (team != null) {
				/**
				 * 根据司机Id在员工信息表里面查询司机详细信息
				 */

				List<driver> listDriver = new ArrayList<>();
				listDriver = (List<driver>) transferStationDao.listObject(
						"from driver where (driver_vehicle = ''or driver_vehicle=null ) and driver_belong_team ='"
								+ team.getTeam_id() + "'");
				/**
				 * 遍历司机表
				 */
				for (driver driver : listDriver) {

					staff_basicinfo driverUnDistributed = transferStationDao
							.getBasicinfoById(driver.getDriver_basicinfoid());
					driverManagerDTO = new DriverManagerDTO();
					driverManagerDTO.setDriver(driver);

					driverManagerDTO.setDriverUnDistributed(driverUnDistributed);
					listDriverManagerDTO.add(driverManagerDTO);
				}
			}
		}
		return listDriverManagerDTO;
	}

	/**
	 * 根据未分配的司机分配车辆
	 */

	@Override
	public String distributeDiver(vehicle vehicle, driver driver) {
		vehicle vehicleNew = new vehicle();
		vehicleNew = transferStationDao.getVehicleById(vehicle.getVehicle_id());
		driver driverNew = new driver();
		driverNew = transferStationDao.getDriverById(driver.getDriver_id());
		if (driverNew!=null
				&& vehicleNew!=null) {
			driverNew.setDriver_vehicle(vehicleNew.getVehicle_id());
        transferStationDao.saveOrUpdateObject(driverNew);
			return "success";
		}
		return "error";
	}

	/**
	 * 查询单位，管理员，上级单位信息
	 */
	@Override
	public UnitManagerDTO getUnitAdmin(unit transferStation) {
		UnitManagerDTO unitManagerDTO = new UnitManagerDTO();
		if (transferStation.getUnit_id() != null && transferStation.getUnit_id().trim().length() > 0) {
			unit unitNew = transferStationDao.getTransferStationInfoById(transferStation.getUnit_id());
			if (unitNew != null) {
				unitManagerDTO.setUnit(unitNew);
			}
			staff_basicinfo unit_Admin = transferStationDao.getBasicinfoById(unitNew.getUnit_admin());
			if (unit_Admin != null) {
				unitManagerDTO.setUnit_Admin(unit_Admin);
			}
			unit unit_superiorunit = transferStationDao.getTransferStationInfoById(unitNew.getUnit_superiorunit());
			if (unit_superiorunit != null) {
				unitManagerDTO.setUnit_superiorunit(unit_superiorunit);
			}
			return unitManagerDTO;
		}
		return null;

	}
}
