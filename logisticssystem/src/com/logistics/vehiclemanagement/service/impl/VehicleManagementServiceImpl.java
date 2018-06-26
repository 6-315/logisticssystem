package com.logistics.vehiclemanagement.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.logistics.domain.*;
import com.logistics.expressmanagement.DTO.RouteDTO;
import com.logistics.vehiclemanagement.DTO.*;
import com.logistics.vehiclemanagement.VO.*;
import com.logistics.vehiclemanagement.dao.VehicleManagementDao;
import com.logistics.vehiclemanagement.service.VehicleManagementService;

import util.BuildUuid;
import util.TimeUtil;

/**
 * 车辆管理业务实现层
 * 
 * @author LW
 *
 */
public class VehicleManagementServiceImpl implements VehicleManagementService {
	private VehicleManagementDao vehicleManagementDao;

	public void setVehicleManagementDao(VehicleManagementDao vehicleManagementDao) {
		this.vehicleManagementDao = vehicleManagementDao;
	}

	/**
	 * 添加车辆（车辆ID，车辆编号，车辆车牌号，车辆状态，车辆所属单位，车辆购置时间，车辆购置人，车辆所属车队，车辆备注，创建时间，修改时间）
	 * 
	 * @return 1 代表添加成功
	 */
	@Override
	public String addVehicle(vehicle vehicleInfo) {
		if (vehicleInfo.getVehicle_platenum() != null && vehicleInfo.getVehicle_platenum().trim().length() > 0) {
			vehicle queryVehicle = vehicleManagementDao.getVehicleInfoByPlateNumber(vehicleInfo.getVehicle_platenum());
			if (queryVehicle != null) {
				System.out.println("该车牌号已经存在");
				return "error";
			} else {
				/**
				 * 添加信息
				 */
				vehicleInfo.setVehicle_id(BuildUuid.getUuid());
				vehicleInfo.setVehicle_acquisitiontime(TimeUtil.getStringSecond());
				vehicleInfo.setVehicle_createtime(TimeUtil.getStringSecond());
				vehicleInfo.setVehicle_modifytime(TimeUtil.getStringSecond());
				vehicleInfo.setVehicle_mark("无");
				/**
				 * 完成添加功能
				 */
				vehicleManagementDao.saveOrUpdateObject(vehicleInfo);
				System.out.println("添加成功");
				return "success";
			}
		} else {
			System.out.println("未获得车牌号");
			return "error";
		}

	}

	/**
	 * 分页查询车辆(车辆ID，车辆编号，车辆车牌号，车辆状态，车辆所属单位，车辆购置时间，车辆购置人，车辆所属车队，车辆备注，创建时间，修改时间)
	 * 
	 * @return 返回vehicleVO数据对象
	 */
	@SuppressWarnings({ "unchecked" })
	@Override
	public VehicleVO queryVehicle(VehicleVO vehicleInfoVO, staff_basicinfo staffInfo) {
		/**
		 * 车辆信息DTO
		 */
		List<VehicleDTOManager> listVehicleDTO = new ArrayList<>();
		/**
		 * 车队DTO
		 */
		VehicleTeamManagerDTO vehicleBelongTeamDTO = null;
		/**
		 * 车队信息
		 */
		team vehicleBelongTeam = null;
		/**
		 * 车辆信息
		 */
		List<vehicle> listVehicleDTOInfo = new ArrayList<>();
		/**
		 * 车辆DTO
		 */
		VehicleDTOManager vehicleDTO;
		/**
		 * 车队队长
		 */
		staff_basicinfo teamLeaderInfo = null;
		/**
		 * 驾驶员
		 */
		DriverDTO driverDTO;
		/**
		 * 获取数量
		 */
		String vehicleCountHql = "select count(*) from vehicle where 1=1 ";
		/**
		 * 链接数量的hql以及遍历的hql
		 */
		String listVehicleDTOCountHql = "from vehicle where 1=1 ";

		if (staffInfo != null) {
			vehicleCountHql = vehicleCountHql + " and ( ";
			listVehicleDTOCountHql = listVehicleDTOCountHql + " and ( ";

			if (staffInfo.getStaff_position() != null && staffInfo.getStaff_position().trim().length() > 0) {
				position postionInfo = vehicleManagementDao.getPostionById(staffInfo.getStaff_position());
				if (postionInfo != null) {
					if ("总公司管理员".equals(postionInfo.getPosition_name())) {
						vehicleCountHql = vehicleCountHql + " 1=1 ";
						listVehicleDTOCountHql = listVehicleDTOCountHql + " 1=1 ";
						/**
						 * 按所属单位(unit)分类查询
						 */
						if (vehicleInfoVO.getUnit() != null && vehicleInfoVO.getUnit().trim().length() > 0) {
							String unit = vehicleInfoVO.getUnit().trim();
							vehicleCountHql = vehicleCountHql + " and vehicle_unit ='" + unit + "' ";
							listVehicleDTOCountHql = listVehicleDTOCountHql + " and vehicle_unit ='" + unit + "'";
						}
					} else if ("中转站管理员".equals(postionInfo.getPosition_name())) {
						vehicleCountHql = vehicleCountHql + " vehicle_unit='" + staffInfo.getStaff_unit() + "' ";
						listVehicleDTOCountHql = listVehicleDTOCountHql + " vehicle_unit='" + staffInfo.getStaff_unit()
								+ "' ";
						/**
						 * 按所属队伍(team)分类查询
						 */
						if (vehicleInfoVO.getTeam() != null && vehicleInfoVO.getTeam().trim().length() > 0) {
							String team = vehicleInfoVO.getTeam().trim();
							vehicleCountHql = vehicleCountHql + " and vehicle_team = '" + team + "' ";
							listVehicleDTOCountHql = listVehicleDTOCountHql + " and vehicle_team = '" + team + "'";
						}
					} else if ("车队队长".equals(postionInfo.getPosition_name())) {
						List<team> listTeam = (List<team>) vehicleManagementDao
								.listObject("from team where team_leader ='" + staffInfo.getStaff_id() + "' ");
						if (listTeam.size() > 0) {
							for (int i = 0; i < listTeam.size(); i++) {
								if (listTeam.get(i).getTeam_id() != null
										&& listTeam.get(i).getTeam_id().trim().length() > 0) {
									vehicleCountHql = vehicleCountHql + " vehicle_team ='"
											+ listTeam.get(i).getTeam_id() + "' ";
									listVehicleDTOCountHql = listVehicleDTOCountHql + " vehicle_team ='"
											+ listTeam.get(i).getTeam_id() + "' ";
								}
								if (i < listTeam.size() - 1) {
									vehicleCountHql = vehicleCountHql + " or ";
									listVehicleDTOCountHql = listVehicleDTOCountHql + " or ";
								}
							}
						} else {
							vehicleCountHql = vehicleCountHql + " 1！=1 ";
							listVehicleDTOCountHql = listVehicleDTOCountHql + " 1！=1 ";
						}
					} else if ("驾驶员".equals(postionInfo.getPosition_name())) {
						if (staffInfo.getStaff_id() != null && staffInfo.getStaff_id().trim().length() > 0) {
							driver driverInfo = vehicleManagementDao.getDriverInfoByStaffId(staffInfo.getStaff_id());
							if (driverInfo != null) {
								if (driverInfo.getDriver_vehicle() != null
										&& driverInfo.getDriver_vehicle().trim().length() > 0) {
									vehicleCountHql = vehicleCountHql + " vehicle_id='" + driverInfo.getDriver_vehicle()
											+ "' ";
									listVehicleDTOCountHql = listVehicleDTOCountHql + " vehicle_id='"
											+ driverInfo.getDriver_vehicle() + "' ";
								}
							} else {
								vehicleCountHql = vehicleCountHql + " 1！=1 ";
								listVehicleDTOCountHql = listVehicleDTOCountHql + " 1！=1 ";
							}
						}
					}
				}
			}
			vehicleCountHql = vehicleCountHql + " ) ";
			listVehicleDTOCountHql = listVehicleDTOCountHql + " ) ";

			/**
			 * 根据关键词进行模糊查询
			 */
			if (vehicleInfoVO.getSearch() != null && vehicleInfoVO.getSearch().trim().length() > 0) {
				String search = "%" + vehicleInfoVO.getSearch().trim() + "%";
				vehicleCountHql = vehicleCountHql + " and ( vehicle_num like '" + search + "' ";
				listVehicleDTOCountHql = listVehicleDTOCountHql + " and ( vehicle_num like '" + search + "'";
				vehicleCountHql = vehicleCountHql + " or vehicle_platenum like '" + search + "' ) ";
				listVehicleDTOCountHql = listVehicleDTOCountHql + " or vehicle_platenum like '" + search + "' ) ";
			}

			/**
			 * 按状态(state)分类查询
			 */
			if (vehicleInfoVO.getState() != null && vehicleInfoVO.getState().trim().length() > 0) {
				String state = vehicleInfoVO.getState().trim();
				vehicleCountHql = vehicleCountHql + " and vehicle_state = '" + state + "' ";
				listVehicleDTOCountHql = listVehicleDTOCountHql + " and vehicle_state = '" + state + "'";
			}

			/**
			 * 根据分配状态筛选
			 */
			if (vehicleInfoVO.getDistributionState() != null
					&& vehicleInfoVO.getDistributionState().trim().length() > 0) {
				vehicleCountHql = vehicleCountHql + " and vehicle_distribution_state='"
						+ vehicleInfoVO.getDistributionState() + "' ";
				listVehicleDTOCountHql = listVehicleDTOCountHql + " and vehicle_distribution_state='"
						+ vehicleInfoVO.getDistributionState() + "' ";
			}

			/**
			 * 根据载货状态筛选
			 */
			if (vehicleInfoVO.getExpressState() != null && vehicleInfoVO.getExpressState().trim().length() > 0) {
				vehicleCountHql = vehicleCountHql + " and vehicle_express_state='" + vehicleInfoVO.getExpressState()
						+ "' ";
				listVehicleDTOCountHql = listVehicleDTOCountHql + " and vehicle_express_state='"
						+ vehicleInfoVO.getExpressState() + "' ";
			}

			/**
			 * 这里如果不加desc表示正序，如果加上desc表示倒序
			 */
			listVehicleDTOCountHql = listVehicleDTOCountHql + " order by vehicle_modifytime desc ";
			int vehicleCount = vehicleManagementDao.getCount(vehicleCountHql);
			/**
			 * 设置总数量
			 */
			vehicleInfoVO.setTotalRecords(vehicleCount);
			/**
			 * 设置总页数
			 */
			vehicleInfoVO.setTotalPages(((vehicleCount - 1) / vehicleInfoVO.getPageSize()) + 1);
			/**
			 * 判断是否拥有上一页
			 */
			if (vehicleInfoVO.getPageIndex() <= 1) {
				vehicleInfoVO.setHavePrePage(false);
			} else {
				vehicleInfoVO.setHavePrePage(true);
			}
			/**
			 * 判断是否拥有下一页
			 */
			if (vehicleInfoVO.getPageIndex() >= vehicleInfoVO.getTotalPages()) {
				vehicleInfoVO.setHaveNextPage(false);
			} else {
				vehicleInfoVO.setHaveNextPage(true);
			}

			/**
			 * 分页查询
			 */
			listVehicleDTOInfo = (List<vehicle>) vehicleManagementDao.queryForPage(listVehicleDTOCountHql,
					vehicleInfoVO.getPageIndex(), vehicleInfoVO.getPageSize());
			for (vehicle vehicle : listVehicleDTOInfo) {
				/**
				 * 车队队长
				 */
				teamLeaderInfo = new staff_basicinfo();
				/**
				 * 车队
				 */
				vehicleBelongTeam = new team();
				/**
				 * 车队DTO
				 */
				vehicleBelongTeamDTO = new VehicleTeamManagerDTO();
				/**
				 * 车辆DTO
				 */
				vehicleDTO = new VehicleDTOManager();
				/**
				 * 驾驶员
				 */
				driverDTO = new DriverDTO();
				/**
				 * 查询车辆购置人
				 */
				staff_basicinfo staff_BasicInfo = vehicleManagementDao
						.getStaffInfoById(vehicle.getVehicle_acquisitionpeople());
				if (staff_BasicInfo != null) {
					/**
					 * 将查询到的信息放入车辆DTO
					 */
					vehicleDTO.setStaff_BasicInfoAcquisition(staff_BasicInfo);
				}
				/**
				 * 查询车辆所属单位
				 */
				unit unitInfo = vehicleManagementDao.getUnitInfoById(vehicle.getVehicle_unit());
				if (unitInfo != null) {
					/**
					 * 将查询到的信息放入车辆DTO
					 */
					vehicleDTO.setUnit(unitInfo);
				}
				/**
				 * 查询车辆所属车队
				 */
				vehicleBelongTeam = vehicleManagementDao.getTeamInfoById(vehicle.getVehicle_team());
				/**
				 * 根据队长id获取队长、驾驶员信息
				 */
				if (vehicleBelongTeam != null) {
					if (vehicleBelongTeam.getTeam_leader() != null
							&& vehicleBelongTeam.getTeam_leader().trim().length() > 0) {
						teamLeaderInfo = vehicleManagementDao
								.getStaffInfoById(vehicleBelongTeam.getTeam_leader().trim());
						if (teamLeaderInfo != null) {
							vehicleBelongTeamDTO.setStaff_BasicInfoLeader(teamLeaderInfo);
						}
					}
					if (vehicleBelongTeam.getTeam_id() != null && vehicleBelongTeam.getTeam_id().trim().length() > 0) {
						List<driver> listDriverInfo = (List<driver>) vehicleManagementDao.listObject(
								"from driver where driver_belong_team='" + vehicleBelongTeam.getTeam_id() + "' ");
						if (listDriverInfo.size() > 0) {
							for (driver driverInfo : listDriverInfo) {
								if (driverInfo != null) {
									if (driverInfo.getDriver_basicinfoid() != null
											&& driverInfo.getDriver_basicinfoid().trim().length() > 0) {
										staff_basicinfo driverBasicInfo = vehicleManagementDao
												.getStaffInfoById(driverInfo.getDriver_basicinfoid());
										if (driverBasicInfo != null) {
											if (driverInfo.getDriver_vehicle() != null
													&& driverInfo.getDriver_vehicle().trim().length() > 0) {
												if (driverInfo.getDriver_vehicle().equals(vehicle.getVehicle_id())) {
													driverDTO.setStaffBasicInfo(driverBasicInfo);
													driverDTO.setDriverInfo(driverInfo);
												}
											}
										}
									}
								}
							}
						}
					}
					/**
					 * 将查询到的信息放入DTO
					 */
					vehicleDTO.setDriverDTO(driverDTO);
					vehicleBelongTeamDTO.setTeam(vehicleBelongTeam);
				}

				/**
				 * 将关键字高亮
				 */
				if (vehicleInfoVO.getSearch() != null && vehicleInfoVO.getSearch().trim().length() > 0) {
					vehicle.setVehicle_num(vehicle.getVehicle_num().replaceAll(vehicleInfoVO.getSearch(),
							"<span style='color: #ff5063;'>" + vehicleInfoVO.getSearch() + "</span>"));
					vehicle.setVehicle_platenum(vehicle.getVehicle_platenum().replaceAll(vehicleInfoVO.getSearch(),
							"<span style='color: #ff5063;'>" + vehicleInfoVO.getSearch() + "</span>"));
				}

				/**
				 * 将查询到的信息放入车辆DTO中
				 */
				vehicleDTO.setVehicle_TeamDTO(vehicleBelongTeamDTO);
				vehicleDTO.setVehicleInfo(vehicle);
				listVehicleDTO.add(vehicleDTO);

			}
			/**
			 * 将DTO放入VO中
			 */
			vehicleInfoVO.setListVehicleDTO(listVehicleDTO);

			return vehicleInfoVO;
		}
		return null;
	}

	/**
	 * 更新车辆信息（车辆编号，车辆车牌号，车辆状态，车辆所属单位，车辆所属车队，车辆备注，修改时间）
	 */
	@Override
	public String updateVehicle(vehicle vehicleInfo) {
		/**
		 * 更新车辆
		 */
		vehicle updateVehicleInfo = new vehicle();
		/**
		 * 根据ID查询车辆信息
		 */
		if (vehicleInfo.getVehicle_id() != null && vehicleInfo.getVehicle_id().trim().length() > 0) {
			updateVehicleInfo = (vehicle) vehicleManagementDao.getVehicleInfoById(vehicleInfo.getVehicle_id());
			if (updateVehicleInfo != null) {
				/**
				 * 更新所需要更新的属性
				 */
				/**
				 * 如果车辆信息的状态不为空并且长度大于零
				 */
				if (vehicleInfo.getVehicle_state() != null && vehicleInfo.getVehicle_state().trim().length() > 0) {
					/**
					 * 根据得到车辆状态更新车辆状态
					 */
					updateVehicleInfo.setVehicle_state(vehicleInfo.getVehicle_state());
				}
				/**
				 * 如果车辆信息的所属单位不为空并且长度大于零
				 */
				if (vehicleInfo.getVehicle_unit() != null && vehicleInfo.getVehicle_unit().trim().length() > 0) {
					/**
					 * 根据得到车辆新单位更新车辆状态
					 */
					updateVehicleInfo.setVehicle_unit(vehicleInfo.getVehicle_unit());
				}
				/**
				 * 如果车辆信息的所属队伍不为空并且长度大于零
				 */
				if (vehicleInfo.getVehicle_team() != null && vehicleInfo.getVehicle_team().trim().length() > 0) {
					/**
					 * 根据得到车辆新队伍更新车辆状态
					 */
					updateVehicleInfo.setVehicle_team(vehicleInfo.getVehicle_team());
				}
				/**
				 * 如果车辆信息的备注不为空并且长度大于零
				 */
				if (vehicleInfo.getVehicle_mark() != null && vehicleInfo.getVehicle_mark().trim().length() > 0) {
					/**
					 * 根据得到车辆新备注更新车辆状态
					 */
					updateVehicleInfo.setVehicle_mark(vehicleInfo.getVehicle_mark());
				}
				updateVehicleInfo.setVehicle_modifytime(TimeUtil.getStringSecond());
				/**
				 * 保存更新
				 */
				vehicleManagementDao.saveOrUpdateObject(updateVehicleInfo);
				System.out.println("更新成功");
			} else {
				System.out.println("更新失败");
			}
			return "success";
		}
		System.out.println("未获得ID");
		return "error";
	}

	/**
	 * 批量删除车辆
	 */
	@Override
	public String deleteVehicle(String idList) {
		if (idList != null && idList.trim().length() > 0) {
			/**
			 * 将获得的idList转换成数组
			 */
			String[] deleteVehicleById = idList.split(",");
			/**
			 * 遍历数组
			 */
			for (String id : deleteVehicleById) {
				/**
				 * 根据获得的ID查询信息，再删除
				 */
				vehicle vehicleInfo = vehicleManagementDao.getVehicleInfoById(id);
				if (vehicleInfo != null) {
					vehicleManagementDao.removeObject(vehicleInfo);
					System.out.println("删除成功");
				} else {
					System.out.println("该数据不存在！");
				}
			}
			return "success";
		}
		System.out.println("未获得ID");
		return "error";
	}

	/**
	 * 添加车队
	 */
	@Override
	public String addTeam(team teamInfo) {
		teamInfo.setTeam_id(BuildUuid.getUuid());
		teamInfo.setTeam_num("123");
		teamInfo.setTeam_createtime(TimeUtil.getStringSecond());
		teamInfo.setTeam_modifytime(TimeUtil.getStringSecond());
		vehicleManagementDao.saveOrUpdateObject(teamInfo);
		System.out.println("添加成功");
		return "success";
	}

	/**
	 * 更新车队信息
	 */
	@Override
	public String updateTeam(team teamInfo) {
		team updateTeamInfo = new team();
		if (teamInfo.getTeam_id() != null && teamInfo.getTeam_id().trim().length() > 0) {
			updateTeamInfo = vehicleManagementDao.getTeamInfoById(teamInfo.getTeam_id());
			if (updateTeamInfo != null) {
				if (teamInfo.getTeam_leader() != null && teamInfo.getTeam_leader().trim().length() > 0) {
					updateTeamInfo.setTeam_leader(teamInfo.getTeam_leader());
				}
				if (teamInfo.getTeam_state() != null && teamInfo.getTeam_state().trim().length() > 0) {
					updateTeamInfo.setTeam_state(teamInfo.getTeam_state());
				}
				if (teamInfo.getTeam_unit() != null && teamInfo.getTeam_unit().trim().length() > 0) {
					updateTeamInfo.setTeam_unit(teamInfo.getTeam_unit());
				}
				updateTeamInfo.setTeam_modifytime(TimeUtil.getStringSecond());
				vehicleManagementDao.saveOrUpdateObject(updateTeamInfo);
				System.out.println("更新成功");
			} else {
				System.out.println("更新失败");
			}
			return "success";
		}
		System.out.println("未获得ID");
		return "error";
	}

	/**
	 * 删除车队信息
	 */
	@Override
	public String deleteTeam(String idList) {
		if (idList != null && idList.trim().length() > 0) {
			/**
			 * 将获得的idList转换成数组
			 */
			String[] deleteVehicleById = idList.split(",");
			/**
			 * 遍历数组
			 */
			for (String id : deleteVehicleById) {
				/**
				 * 根据获得的ID查询信息，再删除
				 */
				team teamInfo = vehicleManagementDao.getTeamInfoById(id);
				if (teamInfo != null) {
					vehicleManagementDao.removeObject(teamInfo);
					System.out.println("删除成功");
				} else {
					System.out.println("该数据不存在");
				}
			}
			return "success";
		}
		System.out.println("未获得ID");
		return "error";
	}

	/**
	 * 查询车队信息
	 */
	@SuppressWarnings("unchecked")
	@Override
	public TeamVO queryTeam(TeamVO teamInfoVO, staff_basicinfo staffInfo) {
		/**
		 * 车队信息DTO
		 */
		List<VehicleTeamManagerDTO> listTeamDTO = new ArrayList<>();
		/**
		 * 车队信息
		 */
		List<team> teamInfo = new ArrayList<>();
		/**
		 * 车队DTO
		 */
		VehicleTeamManagerDTO teamDTO;
		/**
		 * 路线DTO
		 */
		RouteDTO routeDTO;
		/**
		 * 驾驶员信息DTO
		 */
		List<DriverDTO> listDriverInfoDTO = new ArrayList<>();
		/**
		 * 获取数量
		 */
		String teamCountHql = "select count(*) from team where 1=1 ";
		/**
		 * 链接数量的hql以及遍历的hql
		 */
		String listTeamDTOCountHql = "from team where 1=1 ";

		if (staffInfo != null) {
			teamCountHql = teamCountHql + " and ( ";
			listTeamDTOCountHql = listTeamDTOCountHql + " and ( ";
			if (staffInfo.getStaff_position() != null && staffInfo.getStaff_position().trim().length() > 0) {
				position positionInfo = vehicleManagementDao.getPostionById(staffInfo.getStaff_position());
				if ("中转站管理员".equals(positionInfo.getPosition_name())) {
					if (staffInfo.getStaff_unit() != null && staffInfo.getStaff_unit().trim().length() > 0) {
						teamCountHql = teamCountHql + " team_unit='" + staffInfo.getStaff_unit() + "' ";
						listTeamDTOCountHql = listTeamDTOCountHql + " team_unit='" + staffInfo.getStaff_unit() + "' ";
					} else {
						teamCountHql = teamCountHql + " 1！=1 ";
						listTeamDTOCountHql = listTeamDTOCountHql + " 1！=1 ";
					}
				} else if ("车队队长".equals(positionInfo.getPosition_name())) {
					if (staffInfo.getStaff_id() != null && staffInfo.getStaff_id().trim().length() > 0) {
						teamCountHql = teamCountHql + " team_leader='" + staffInfo.getStaff_id() + "' ";
						listTeamDTOCountHql = listTeamDTOCountHql + " team_leader='" + staffInfo.getStaff_id() + "' ";
					} else {
						teamCountHql = teamCountHql + " 1！=1 ";
						listTeamDTOCountHql = listTeamDTOCountHql + " 1！=1 ";
					}
				} else if ("驾驶员".equals(positionInfo.getPosition_name())) {
					driver driverInfo = vehicleManagementDao.getDriverInfoByStaffId(staffInfo.getStaff_id());
					if (driverInfo != null) {
						if (driverInfo.getDriver_belong_team() != null
								&& driverInfo.getDriver_belong_team().trim().length() > 0) {
							teamCountHql = teamCountHql + " team_id='" + driverInfo.getDriver_belong_team() + "' ";
							listTeamDTOCountHql = listTeamDTOCountHql + " team_id='"
									+ driverInfo.getDriver_belong_team() + "' ";
						}
					} else {
						teamCountHql = teamCountHql + " 1！=1 ";
						listTeamDTOCountHql = listTeamDTOCountHql + " 1！=1 ";
					}
				}
			}
			teamCountHql = teamCountHql + " ) ";
			listTeamDTOCountHql = listTeamDTOCountHql + " ) ";

			/**
			 * 根据关键词进行模糊查询
			 */
			if (teamInfoVO.getSearch() != null && teamInfoVO.getSearch().trim().length() > 0) {
				String search = "%" + teamInfoVO.getSearch().trim() + "%";
				teamCountHql = teamCountHql + " and team_num like '" + search + "' ";
				listTeamDTOCountHql = listTeamDTOCountHql + " and team_num like '" + search + "'";
			}
			/**
			 * 按状态(state)分类查询
			 */
			if (teamInfoVO.getState() != null && teamInfoVO.getState().trim().length() > 0) {
				String state = teamInfoVO.getState().trim();
				teamCountHql = teamCountHql + " and team_state = '" + state + "' ";
				listTeamDTOCountHql = listTeamDTOCountHql + " and team_state = '" + state + "'";
			}

			/**
			 * 这里如果不加desc表示正序，如果加上desc表示倒序
			 */
			listTeamDTOCountHql = listTeamDTOCountHql + " order by team_modifytime desc ";
			int teamCount = vehicleManagementDao.getCount(teamCountHql);
			/**
			 * 设置总数量
			 */
			teamInfoVO.setTotalRecords(teamCount);
			/**
			 * 设置总页数
			 */
			teamInfoVO.setTotalPages(((teamCount - 1) / teamInfoVO.getPageSize()) + 1);
			/**
			 * 判断是否拥有上一页
			 */
			if (teamInfoVO.getPageIndex() <= 1) {
				teamInfoVO.setHavePrePage(false);
			} else {
				teamInfoVO.setHavePrePage(true);
			}
			/**
			 * 判断是否拥有下一页
			 */
			if (teamInfoVO.getPageIndex() >= teamInfoVO.getTotalPages()) {
				teamInfoVO.setHaveNextPage(false);
			} else {
				teamInfoVO.setHaveNextPage(true);
			}
			/**
			 * 分页查询
			 */
			teamInfo = (List<team>) vehicleManagementDao.queryForPage(listTeamDTOCountHql, teamInfoVO.getPageIndex(),
					teamInfoVO.getPageSize());
			if (teamInfo.size() > 0) {
				for (team team : teamInfo) {
					teamDTO = new VehicleTeamManagerDTO();
					routeDTO = new RouteDTO();
					/**
					 * 查询队长信息
					 */
					if (team.getTeam_leader() != null && team.getTeam_leader().trim().length() > 0) {
						staff_basicinfo teamLeader = vehicleManagementDao.getStaffInfoById(team.getTeam_leader());
						if (teamLeader != null) {
							teamDTO.setStaff_BasicInfoLeader(teamLeader);
						}
					}
					/**
					 * 查询所属单位
					 */
					if (team.getTeam_unit() != null && team.getTeam_unit().trim().length() > 0) {
						unit teamUnit = vehicleManagementDao.getUnitInfoById(team.getTeam_unit());
						if (teamUnit != null) {
							teamDTO.setTeamBelongUnit(teamUnit);
						}
					}
					/**
					 * 查询路线信息
					 */
					if (team.getTeam_route() != null && team.getTeam_route().trim().length() > 0) {
						route routeInfo = vehicleManagementDao.getRouteInfoByTeamId(team.getTeam_route());
						if (routeInfo != null) {
							if (routeInfo.getRoute_departurestation() != null
									&& routeInfo.getRoute_departurestation().trim().length() > 0) {
								unit beginUnit = vehicleManagementDao
										.getUnitInfoById(routeInfo.getRoute_departurestation());
								if (beginUnit != null) {
									routeDTO.setBeginUnit(beginUnit);
								}
							}
							if (routeInfo.getRoute_terminalstation() != null
									&& routeInfo.getRoute_terminalstation().trim().length() > 0) {
								unit endUnit = vehicleManagementDao
										.getUnitInfoById(routeInfo.getRoute_terminalstation());
								if (endUnit != null) {
									routeDTO.setEndUnit(endUnit);
								}
							}
							routeDTO.setRouteInfo(routeInfo);
						}

					}
					/**
					 * 查询所有队员信息
					 */
					if (team.getTeam_id() != null && team.getTeam_id().trim().length() > 0) {
						List<driver> listDriver = (List<driver>) vehicleManagementDao
								.listObject(" from driver where driver_belong_team ='" + team.getTeam_id() + "' ");
						if (listDriver.size() > 0) {
							for (driver driver : listDriver) {
								DriverDTO driverDTO = new DriverDTO();
								if (driver.getDriver_basicinfoid() != null
										&& driver.getDriver_basicinfoid().trim().length() > 0) {
									staff_basicinfo staffBasicInfo = vehicleManagementDao
											.getStaffInfoById(driver.getDriver_basicinfoid());
									if (staffBasicInfo != null) {
										driverDTO.setStaffBasicInfo(staffBasicInfo);
										driverDTO.setDriverInfo(driver);
										listDriverInfoDTO.add(driverDTO);
										teamDTO.setListDriverInfoDTO(listDriverInfoDTO);
									}
								}
							}
						}
						/**
						 * 将关键字高亮
						 */
						if (teamInfoVO.getSearch() != null && teamInfoVO.getSearch().trim().length() > 0) {
							team.setTeam_num(team.getTeam_num().replaceAll(teamInfoVO.getSearch(),
									"<span style='color: #ff5063;'>" + teamInfoVO.getSearch() + "</span>"));
						}
						teamDTO.setTeam(team);
						teamDTO.setRouteDTO(routeDTO);
						listTeamDTO.add(teamDTO);
					}
				}
				teamInfoVO.setListTeamDTO(listTeamDTO);
				return teamInfoVO;
			}
		}
		return null;
	}

	/**
	 * 流转车辆
	 */
	@Override
	public String exchangeVehicle(String idList, String unit, staff_basicinfo staffInfo) {
		if (staffInfo != null) {
			if (idList != null) {
				if (unit != null) {
					String[] vehicle_unit = idList.split(",");
					if (vehicle_unit != null) {
						for (String viui : vehicle_unit) {
							if (viui != null) {
								String[] vu = viui.split("&");
								if (vu[0] != null && vu[1] != null) {
									unit initiativeUnitInfo = vehicleManagementDao.getUnitInfoById(vu[1]);
									unit acceptUnitInfo = vehicleManagementDao.getUnitInfoById(unit);
									if (initiativeUnitInfo != null && acceptUnitInfo != null) {
										if (staffInfo.getStaff_id() != null
												&& staffInfo.getStaff_id().trim().length() > 0) {
											vehicle vehicleInfo = vehicleManagementDao.getVehicleInfoById(vu[0]);
											driver driverInfo = vehicleManagementDao.getDriverInfoByVehicleId(vu[0]);
											if (driverInfo != null) {
												driverInfo.setDriver_vehicle("");
												driverInfo.setDriver_modifytime(TimeUtil.getStringSecond());
												vehicleManagementDao.saveOrUpdateObject(driverInfo);
											}
											if (vehicleInfo != null) {
												vehicleInfo.setVehicle_current_weight("0");
												vehicleInfo.setVehicle_express_state("空闲");
												vehicleInfo.setVehicle_distribution_state("已分配到中转站");
												vehicleInfo.setVehicle_team("");
												vehicleInfo.setVehicle_unit(unit);
												vehicleInfo.setVehicle_drivingdirection(unit);
												vehicleInfo.setVehicle_modifytime(TimeUtil.getStringSecond());
												vehicleManagementDao.saveOrUpdateObject(vehicleInfo);

												vehiclecirculation vehicleCirculationInfo = new vehiclecirculation();
												vehicleCirculationInfo.setVehiclecirculation_id(BuildUuid.getUuid());
												vehicleCirculationInfo.setVehiclecirculation_acceptd(unit);
												vehicleCirculationInfo.setVehiclecirculation_initiative(vu[1]);
												vehicleCirculationInfo.setVehiclecirculation_instructions("无");
												vehicleCirculationInfo.setVehiclecirculation_mark("无");
												vehicleCirculationInfo.setVehiclecirculation_vehicle_id(vu[0]);
												vehicleCirculationInfo
														.setVehiclecirculation_people(staffInfo.getStaff_id());
												vehicleCirculationInfo
														.setVehiclecirculation_createtime(TimeUtil.getStringSecond());
												vehicleCirculationInfo
														.setVehiclecirculation_modifytime(TimeUtil.getStringSecond());
												vehicleManagementDao.saveOrUpdateObject(vehicleCirculationInfo);
												return "success";
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		return "error";
	}

	/**
	 * 获得所有管理员
	 */
	@Override
	public List<ManagerDTO> getAllManager(String position) {
		List<ManagerDTO> listManagerDTO = new ArrayList<>();
		ManagerDTO managerDTO;
		if (position != null && position.trim().length() > 0) {
			position positionInfo = vehicleManagementDao.getPostionByName(position);
			if (positionInfo != null) {
				if (positionInfo.getPosition_id() != null && positionInfo.getPosition_id().trim().length() > 0) {
					String hql = "select * from staff_basicinfo AS sta where sta.staff_position ='"
							+ positionInfo.getPosition_id()
							+ "' and sta.staff_id not in ( select u.unit_admin from unit AS u )  ";
					List<staff_basicinfo> listManager = vehicleManagementDao.getListManager(hql);
					if (listManager.size() > 0) {
						for (staff_basicinfo manager : listManager) {
							managerDTO = new ManagerDTO();
							if (manager != null) {
								managerDTO.setManagerInfo(manager);
								listManagerDTO.add(managerDTO);
							}
						}
						return listManagerDTO;
					}
				}
			}
		}
		return null;
	}

	/**
	 * 获得所有车队
	 */
	@Override
	public List<team> getAllTeam(staff_basicinfo staffInfo) {
		if (staffInfo != null) {
			if (staffInfo.getStaff_unit() != null && staffInfo.getStaff_unit().trim().length() > 0) {
				List<team> listTeam = (List<team>) vehicleManagementDao
						.listObject("from team where team_unit='" + staffInfo.getStaff_unit() + "' ");
				if (listTeam.size() > 0) {
					return listTeam;
				}
			}
		}
		return null;
	}

}
