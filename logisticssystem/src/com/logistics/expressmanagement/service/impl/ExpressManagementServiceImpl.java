package com.logistics.expressmanagement.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.logistics.domain.*;
import com.logistics.expressmanagement.DTO.*;
import com.logistics.expressmanagement.VO.ExpressInfoVO;
import com.logistics.expressmanagement.VO.ReservationOrderHistoryVO;
import com.logistics.expressmanagement.VO.ReservationVO;
import com.logistics.expressmanagement.dao.ExpressManagementDao;

import com.logistics.expressmanagement.service.ExpressManagementService;
import com.logistics.loginregister.DTO.UserInfoSessionDTO;

import util.BuildUuid;
import util.CreateNumberUtil;
import util.TimeUtil;

/**
 * 快件管理的业务层
 * 
 * @author LW
 *
 */

public class ExpressManagementServiceImpl implements ExpressManagementService {
	/**
	 * dao层注入
	 */
	private ExpressManagementDao expressManagementDao;

	public void setExpressManagementDao(ExpressManagementDao expressManagementDao) {
		this.expressManagementDao = expressManagementDao;
	}

	/**
	 * 结束
	 */

	/**
	 * 用户预约 添加预约表和快件信息表
	 */
	@Override
	public ReservationExpressInfoDTO addReservationAndExpressInfo(ReservationExpressInfoDTO reservationExpressInfoDTO,
			userinfo userInfo) {
		if (reservationExpressInfoDTO != null) {
			expressinfo expressInfo = reservationExpressInfoDTO.getExpressInfo();
			expressInfo.setExpressinfo_id(BuildUuid.getUuid());
			expressInfo.setExpressinfo_createtime(TimeUtil.getStringSecond());
			expressInfo.setExpressinfo_modifytime(TimeUtil.getStringSecond());
			reservationExpressInfoDTO.setExpressInfo(expressInfo);
			expressManagementDao.saveOrUpdateObject(reservationExpressInfoDTO.getExpressInfo());
			reservation reservationInfo = reservationExpressInfoDTO.getReservationInfo();
			if (reservationInfo.getReservation_unit() != null
					&& reservationInfo.getReservation_unit().trim().length() > 0) {
				reservationInfo.setReservation_id(BuildUuid.getUuid());
				reservationInfo.setReservation_num(CreateNumberUtil.getTimeNumberT());
				reservationInfo.setReservation_user(userInfo.getUserinfo_id());
				reservationInfo.setReservation_expressinfo(expressInfo.getExpressinfo_id());
				reservationInfo.setReservation_state("待受理");
				reservationInfo.setReservation_createtime(TimeUtil.getStringSecond());
				reservationInfo.setReservation_modifytime(TimeUtil.getStringSecond());
				reservationExpressInfoDTO.setReservationInfo(reservationInfo);
				expressManagementDao.saveOrUpdateObject(reservationExpressInfoDTO.getReservationInfo());
			}
			return reservationExpressInfoDTO;
		}
		return null;

	}

	/**
	 * 受理客户预约
	 */
	@Override
	public String updateReservation(String idList, String state) {
		if (idList != null) {
			String[] listId = idList.split(",");
			for (String id : listId) {
				reservation updateReservationInfo = expressManagementDao.getReservationInfoById(id);
				if (updateReservationInfo != null) {
					updateReservationInfo.setReservation_state(state);
					updateReservationInfo.setReservation_modifytime(TimeUtil.getStringSecond());
					expressManagementDao.saveOrUpdateObject(updateReservationInfo);
				}
			}
			return "success";
		}
		return "error";
	}

	/**
	 * 管理员分配配送员给预约单
	 */
	@Override
	public String updateReservationWithDistributor(String idList, distributiontor distributor) {
		if (distributor.getDistributiontor_id() != null && distributor.getDistributiontor_id().trim().length() > 0) {
			distributiontor distributorInfo = expressManagementDao
					.getDistributorInfoById(distributor.getDistributiontor_id());
			if (distributorInfo != null) {
				if (idList != null) {
					String[] listId = idList.split(",");
					for (String id : listId) {
						reservation updateReservation = expressManagementDao.getReservationInfoById(id);
						if (updateReservation != null) {
							updateReservation.setReservation_distributiontor(distributorInfo.getDistributiontor_id());
							updateReservation.setReservation_state("待取件");
							updateReservation.setReservation_modifytime(TimeUtil.getStringSecond());
							expressManagementDao.saveOrUpdateObject(updateReservation);
						}
					}
					return "success";
				}
			}
		}
		return "error";
	}

	/**
	 * 生成快件单和流转单
	 */
	@Override
	public ExpressAndCirculationDTO completePickExpress(ExpressAndCirculationDTO expressAndCirculationDTO,
			staff_basicinfo staffInfo) {
		if (staffInfo != null) {
			if (expressAndCirculationDTO.getExpressDetailInfo() != null) {
				expressinfo expressDetailInfo = expressAndCirculationDTO.getExpressDetailInfo();
				expressDetailInfo.setExpressinfo_id(BuildUuid.getUuid());
				expressDetailInfo.setExpressinfo_createtime(TimeUtil.getStringSecond());
				expressDetailInfo.setExpressinfo_modifytime(TimeUtil.getStringSecond());
				expressManagementDao.saveOrUpdateObject(expressDetailInfo);
				expressAndCirculationDTO.setExpressDetailInfo(expressDetailInfo);
				// 生成快件单
				express expressInfo = new express();
				expressInfo.setExpress_belongunit(staffInfo.getStaff_unit());
				if (expressAndCirculationDTO.getUserInfo().getUserinfo_id() != null
						&& expressAndCirculationDTO.getUserInfo().getUserinfo_id().trim().length() > 0) {
					expressInfo.setExpress_belong(expressAndCirculationDTO.getUserInfo().getUserinfo_id());
				}
				expressInfo.setExpress_expressinfoid(expressDetailInfo.getExpressinfo_id());
				expressInfo.setExpress_id(BuildUuid.getUuid());
				expressInfo.setExpress_number("315" + CreateNumberUtil.getExpressNumber());
				expressInfo.setExpress_state("已揽件");
				expressInfo.setExpress_createtime(TimeUtil.getStringSecond());
				expressInfo.setExpress_modifytime(TimeUtil.getStringSecond());
				expressManagementDao.saveOrUpdateObject(expressInfo);
				expressAndCirculationDTO.setExpressInfo(expressInfo);
				// 生成快件派送表
				express_send expressSendInfo = new express_send();
				expressSendInfo.setExpress_send_id(BuildUuid.getUuid());
				expressSendInfo.setExpress_send_express_id(expressInfo.getExpress_id());
				if (expressAndCirculationDTO != null) {
					if (expressAndCirculationDTO.getDistributor() != null) {
						if (expressAndCirculationDTO.getDistributor().getDistributiontor_id() != null
								&& expressAndCirculationDTO.getDistributor().getDistributiontor_id().trim()
										.length() > 0) {
							expressSendInfo.setExpress_send_distributiontor(
									expressAndCirculationDTO.getDistributor().getDistributiontor_id());
						}
					}
				}
				expressSendInfo.setExpress_send_type("揽件");
				expressSendInfo.setExpress_send_state("未完成");
				expressSendInfo.setExpress_send_createtime(TimeUtil.getStringSecond());
				expressSendInfo.setExpress_send_modifytime(TimeUtil.getStringSecond());
				expressManagementDao.saveOrUpdateObject(expressSendInfo);
				// 生成流转单
				express_circulation expressCirculation = new express_circulation();
				expressCirculation.setExpress_circulation_id(BuildUuid.getUuid());
				expressCirculation.setExpress_circulation_express_id(expressInfo.getExpress_id());
				expressCirculation.setExpress_circulation_state("流转中");
				if (staffInfo.getStaff_unit() != null && staffInfo.getStaff_unit().trim().length() > 0) {
					expressCirculation.setExpress_circulation_launchpeople(staffInfo.getStaff_unit());
					unit unitInfo = expressManagementDao.getUnitInfoById(staffInfo.getStaff_unit());
					if (unitInfo != null) {
						if (unitInfo.getUnit_superiorunit() != null
								&& unitInfo.getUnit_superiorunit().trim().length() > 0) {
							unit superiorUnit = expressManagementDao.getUnitInfoById(unitInfo.getUnit_superiorunit());
							if (superiorUnit != null) {
								expressCirculation.setExpress_circulation_receiver(unitInfo.getUnit_superiorunit());
								expressCirculation.setExpress_circulation_createtime(TimeUtil.getStringSecond());
								expressCirculation.setExpress_circulation_modifytime(TimeUtil.getStringSecond());
								expressManagementDao.saveOrUpdateObject(expressCirculation);
								return expressAndCirculationDTO;
							}
						}
					}
				}
			}
		}
		return null;
	}

	/**
	 * 到达中转站（更新快件状态）
	 *//*
		 * @Override public String updateExpressState(express expressInfo) { if
		 * (expressInfo != null) { if (expressInfo.getExpress_id() != null &&
		 * expressInfo.getExpress_id().trim().length() > 0) { express updateExpress =
		 * expressManagementDao.getExpressById(expressInfo.getExpress_id()); if
		 * (updateExpress != null) { if (expressInfo.getExpress_state() != null &&
		 * expressInfo.getExpress_state().trim().length() > 0) {
		 * updateExpress.setExpress_state("待扫描");
		 * updateExpress.setExpress_modifytime(TimeUtil.getStringSecond());
		 * expressManagementDao.saveOrUpdateObject(updateExpress); return "success"; } }
		 * } } return "error"; }
		 */

	/**
	 * 扫描（判断快件类型）
	 */
	@Override
	public String judgeExpressType(express expressInfo) {
		if (expressInfo != null) {
			if (expressInfo.getExpress_id() != null && expressInfo.getExpress_id().trim().length() > 0) {
				express judgeExpress = expressManagementDao.getExpressById(expressInfo.getExpress_id());
				if (judgeExpress != null) {
					String expressRouteInfo = expressManagementDao
							.getExpressRouteInfoByExpressId(expressInfo.getExpress_id());
					if (expressRouteInfo != null) {
						if ("0001".equals(expressRouteInfo)) {
							System.out.println("始发站");
							return "始发站";
						} else if (!"0001".equals(expressRouteInfo)) {
							System.out.println("中转站");
							return "中转站";
						}
					} else {
						System.out.println("终点站");
						return "终点站";
					}
				}
			}
		}
		return null;
	}

	/**
	 * 保存路线
	 */
	@Override
	public String saveExpressRoute(String idList, express expressInfo) {
		if (idList != null && idList.trim().length() > 0) {
			String[] listId = idList.split(",");
			if (expressInfo.getExpress_id() != null && expressInfo.getExpress_id().trim().length() > 0) {
				if (listId != null) {
					for (String id : listId) {
						express_route expressRoute = new express_route();
						expressRoute.setExpress_route_id(BuildUuid.getUuid());
						expressRoute.setExpress_route_route_id(id);
						expressRoute.setExpress_route_belongexpress(expressInfo.getExpress_id());
						expressRoute.setExpress_route_state("未完成");

						String hql = "select express_route_superior from express_route where express_route_belongexpress='"
								+ expressInfo.getExpress_id() + "' order by --express_route_superior desc limit 1 ";
						String number = expressManagementDao.getMaxNumber(hql);
						if (number != null && number.trim().length() > 0) {
							int num = Integer.parseInt(number);
							num = num + 1;
							String nextNumber = String.format("%04d", num);
							expressRoute.setExpress_route_superior(nextNumber);
						} else {
							expressRoute.setExpress_route_superior("0001");
						}
						expressRoute.setExpress_route_createtime(TimeUtil.getStringSecond());
						expressRoute.setExpress_route_modifytime(TimeUtil.getStringSecond());
						expressManagementDao.saveOrUpdateObject(expressRoute);
					}
					return "success";
				}
			}
		}
		return "error";
	}

	/**
	 * 查询经过该中转站的所有路线
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<RouteDTO> queryAllRouteWithUnit(unit unitInfo) {
		List<RouteDTO> listRouteDTO = new ArrayList<>();
		RouteDTO routeDTO = null;
		if (unitInfo != null && unitInfo.getUnit_id() != null && unitInfo.getUnit_id().trim().length() > 0) {
			List<route> listRoute = (List<route>) expressManagementDao
					.listObject(" from route where route_state = '正常使用' and (route_departurestation = '"
							+ unitInfo.getUnit_id() + "' or route_terminalstation = '" + unitInfo.getUnit_id() + "')");
			if (listRoute != null) {
				routeDTO = new RouteDTO();
				for (route routeInfo : listRoute) {
					if (routeInfo.getRoute_departurestation().equals(unitInfo.getUnit_id())) {
						String direction = "正向";
						routeDTO.setDirection(direction);
						unit beginUnit = expressManagementDao.getUnitInfoById(routeInfo.getRoute_departurestation());
						unit endUnit = expressManagementDao.getUnitInfoById(routeInfo.getRoute_terminalstation());
						if (beginUnit != null && endUnit != null) {
							routeDTO.setBeginUnit(beginUnit);
							routeDTO.setEndUnit(endUnit);
						}
					} else {
						String direction = "反向";
						routeDTO.setDirection(direction);
						unit beginUnit = expressManagementDao.getUnitInfoById(routeInfo.getRoute_terminalstation());
						unit endUnit = expressManagementDao.getUnitInfoById(routeInfo.getRoute_departurestation());
						if (beginUnit != null && endUnit != null) {
							routeDTO.setBeginUnit(beginUnit);
							routeDTO.setEndUnit(endUnit);
						}
					}
					routeDTO.setRouteInfo(routeInfo);
					listRouteDTO.add(routeDTO);
				}
				return listRouteDTO;
			}
		}
		return null;
	}

	/**
	 * 完成扫描
	 */
	@Override
	public String updateVehicleAndExpressCirculationAndExpressInfo(ExpressAndCirculationDTO expressAndCirculationDTO,
			staff_basicinfo staffInfo) {
		if (expressAndCirculationDTO != null) {
			express expressInfo = expressAndCirculationDTO.getExpressInfo();
			vehicle vehicleInfo = expressAndCirculationDTO.getVehicleInfo();
			if (expressInfo != null && vehicleInfo != null && staffInfo != null) {
				if (expressInfo.getExpress_id() != null && vehicleInfo.getVehicle_id() != null
						&& expressInfo.getExpress_id().trim().length() > 0
						&& vehicleInfo.getVehicle_id().trim().length() > 0) {
					vehicle updateVehicle = expressManagementDao.getVehicleInfoById(vehicleInfo.getVehicle_id());
					if (updateVehicle != null) {
						if (staffInfo.getStaff_unit() != null && staffInfo.getStaff_unit().trim().length() > 0) {
							updateVehicle.setVehicle_drivingdirection(staffInfo.getStaff_unit());
							updateVehicle.setVehicle_express_state("空闲");
							updateVehicle.setVehicle_current_weight("");
							updateVehicle.setVehicle_modifytime(TimeUtil.getStringSecond());
							expressManagementDao.saveOrUpdateObject(updateVehicle);
							express_circulation expressCirculationInfo = expressManagementDao
									.getExpressCirculationInfoByExpressIdAndReceiver(expressInfo.getExpress_id(),
											staffInfo.getStaff_unit());
							if (expressCirculationInfo != null) {
								expressCirculationInfo.setExpress_circulation_state("已完成");
								expressCirculationInfo.setExpress_circulation_modifytime(TimeUtil.getStringSecond());
								expressManagementDao.saveOrUpdateObject(expressCirculationInfo);

								express updateExpress = expressManagementDao
										.getExpressById(expressInfo.getExpress_id());
								if (updateExpress != null) {
									updateExpress.setExpress_state("已扫描");
									updateExpress.setExpress_modifytime(TimeUtil.getStringSecond());
									expressManagementDao.saveOrUpdateObject(updateExpress);
									return "success";
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
	 * 获得预约列表VO
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ReservationVO queryReservationInfo(ReservationVO reservationVO, staff_basicinfo staffInfo) {
		List<ReservationDTO> listReservationDTO = new ArrayList<>();
		ReservationDTO reservationDTO;
		List<reservation> listReservation = new ArrayList<>();

		String reservationCountHql = "select count(*) from reservation where 1=1 and ( ";
		String listReservationInfoHql = " from reservation where 1=1 and ( ";

		if (staffInfo != null) {
			/**
			 * 根据角色单位筛选
			 */
			position staffPosition = expressManagementDao.getPositionById(staffInfo.getStaff_position());
			if (staffPosition != null && staffPosition.getPosition_name() != null
					&& staffPosition.getPosition_name().trim().length() > 0) {
				if ("配送员".equals(staffPosition.getPosition_name())) {
					distributiontor distributor = expressManagementDao
							.getDistributorInfoByBasicInfo(staffInfo.getStaff_id());
					if (distributor != null && distributor.getDistributiontor_id() != null
							&& distributor.getDistributiontor_id().trim().length() > 0) {
						reservationCountHql = reservationCountHql + " reservation_distributiontor='"
								+ distributor.getDistributiontor_id() + "' ";
						listReservationInfoHql = listReservationInfoHql + " reservation_distributiontor='"
								+ distributor.getDistributiontor_id() + "' ";
					}
				} else if ("总公司管理员".equals(staffPosition.getPosition_name())) {
					reservationCountHql = reservationCountHql + "1=1";
					listReservationInfoHql = listReservationInfoHql + "1=1";
					if (reservationVO.getUnit() != null && reservationVO.getUnit().trim().length() > 0) {
						reservationCountHql = reservationCountHql + " and reservation_unit ='" + reservationVO.getUnit()
								+ "' ";
						listReservationInfoHql = listReservationInfoHql + " and reservation_unit ='"
								+ reservationVO.getUnit() + "' ";
					}
				} else {
					if (reservationVO.getUnit() != null && reservationVO.getUnit().trim().length() > 0) {
						reservationCountHql = reservationCountHql + " reservation_unit ='" + reservationVO.getUnit()
								+ "' ";
						listReservationInfoHql = listReservationInfoHql + " reservation_unit ='"
								+ reservationVO.getUnit() + "' ";
					} else {
						List<unit> listUnit = (List<unit>) expressManagementDao
								.listObject(" from unit where 1=1 and ( unit_id ='" + staffInfo.getStaff_unit()
										+ " ' or unit_superiorunit ='" + staffInfo.getStaff_unit() + "' ) ");
						if (listUnit != null) {
							for (int i = 0; i < listUnit.size(); i++) {
								if (listUnit.get(i) != null && listUnit.get(i).getUnit_id() != null
										&& listUnit.get(i).getUnit_id().trim().length() > 0) {
									reservationCountHql = reservationCountHql + " reservation_unit ='"
											+ listUnit.get(i).getUnit_id().trim() + "'";
									listReservationInfoHql = listReservationInfoHql + "  reservation_unit ='"
											+ listUnit.get(i).getUnit_id().trim() + "' ";
								}
								if (i < listUnit.size() - 1) {
									reservationCountHql = reservationCountHql + " or  ";
									listReservationInfoHql = listReservationInfoHql + " or  ";
								}
							}

						}
					}
				}
			}
			reservationCountHql = reservationCountHql + " )  ";
			listReservationInfoHql = listReservationInfoHql + " )  ";

			/**
			 * 模糊查询
			 */
			if (reservationVO.getSearch() != null && reservationVO.getSearch().trim().length() > 0) {
				String search = "%" + reservationVO.getSearch().trim() + "%";
				reservationCountHql = reservationCountHql + " and reservation_num like '" + search + "' ";
				listReservationInfoHql = listReservationInfoHql + " and reservation_num like '" + search + "' ";
			}
			/**
			 * 根据是否分配进行筛选
			 */
			if (reservationVO.getIsDistributed() != null && reservationVO.getIsDistributed().trim().length() > 0) {
				if ("是".equals(reservationVO.getIsDistributed())) {
					reservationCountHql = reservationCountHql
							+ " and ( reservation_distributiontor !='' or reservation_distributiontor !=null )  ";
					listReservationInfoHql = listReservationInfoHql
							+ " and  ( reservation_distributiontor !='' or reservation_distributiontor !=null )  ";
				}
				if ("否".equals(reservationVO.getIsDistributed())) {
					reservationCountHql = reservationCountHql
							+ " and ( reservation_distributiontor ='' or reservation_distributiontor =null )  ";
					listReservationInfoHql = listReservationInfoHql
							+ " and  ( reservation_distributiontor ='' or reservation_distributiontor =null )  ";
				}
			}
			/**
			 * 根据状态分类查询
			 */
			if (reservationVO.getState() != null && reservationVO.getState().trim().length() > 0) {
				reservationCountHql = reservationCountHql + " and reservation_state ='" + reservationVO.getState()
						+ "' ";
				listReservationInfoHql = listReservationInfoHql + " and reservation_state ='" + reservationVO.getState()
						+ "' ";
			}

			listReservationInfoHql = listReservationInfoHql + " order by reservation_modifytime desc ";
			int reservationCount = expressManagementDao.getCount(reservationCountHql);
			/**
			 * 设置总数量
			 */
			reservationVO.setTotalRecords(reservationCount);
			/**
			 * 设置总页数
			 */
			reservationVO.setTotalPages(((reservationCount - 1) / reservationVO.getPageSize()) + 1);
			/**
			 * 判断是否拥有上一页
			 */
			if (reservationVO.getPageIndex() <= 1) {
				reservationVO.setHavePrePage(false);
			} else {
				reservationVO.setHavePrePage(true);
			}
			/**
			 * 判断是否拥有下一页
			 */
			if (reservationVO.getPageIndex() >= reservationVO.getTotalPages()) {
				reservationVO.setHaveNextPage(false);
			} else {
				reservationVO.setHaveNextPage(true);
			}

			/**
			 * 分页查询
			 */
			listReservation = (List<reservation>) expressManagementDao.queryForPage(listReservationInfoHql,
					reservationVO.getPageIndex(), reservationVO.getPageSize());
			for (reservation reservationInfo : listReservation) {
				reservationDTO = new ReservationDTO();
				if (reservationInfo != null) {
					if (reservationInfo.getReservation_expressinfo() != null
							&& reservationInfo.getReservation_expressinfo().trim().length() > 0
							&& reservationInfo.getReservation_user() != null
							&& reservationInfo.getReservation_user().trim().length() > 0) {
						expressinfo expressInfo = expressManagementDao
								.getExpressInfoById(reservationInfo.getReservation_expressinfo());
						if (expressInfo != null) {
							reservationDTO.setExpressInfo(expressInfo);
						}
						userinfo userInfo = expressManagementDao.getUserInfoById(reservationInfo.getReservation_user());
						if (userInfo != null) {
							reservationDTO.setUserInfo(userInfo);
						}
						if (reservationInfo.getReservation_unit() != null
								&& reservationInfo.getReservation_unit().trim().length() > 0) {
							unit unitInfo = expressManagementDao.getUnitInfoById(reservationInfo.getReservation_unit());
							if (unitInfo != null) {
								reservationDTO.setUnitInfo(unitInfo);
							}
						}
					}
					/**
					 * 将关键字高亮
					 */
					if (reservationVO.getSearch() != null && reservationVO.getSearch().trim().length() > 0) {
						reservationInfo.setReservation_num(reservationInfo.getReservation_num().replaceAll(
								reservationVO.getSearch(), "<mark>" + reservationVO.getSearch() + "</mark>"));
					}

					reservationDTO.setReservationInfo(reservationInfo);
					listReservationDTO.add(reservationDTO);
				}
			}
			reservationVO.setListReservationInfoDTO(listReservationDTO);
			return reservationVO;
		}
		return null;
	}

	/**
	 * 获得快件列表VO
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ExpressInfoVO queryExpressInfo(ExpressInfoVO expressInfoVO, staff_basicinfo staffInfo) {
		List<ExpressInfoDTO> listExpressInfoDTO = new ArrayList<>();
		List<express> listExpress = new ArrayList<>();
		ExpressInfoDTO expressInfoDTO;

		String expressCountHql = "select count(*) from express where 1=1  ";
		String listExpressInfoHql = " from express where 1=1  ";

		if (staffInfo != null) {
			expressCountHql = expressCountHql + " and ( ";
			listExpressInfoHql = listExpressInfoHql + " and ( ";
			/**
			 * 根据角色筛选
			 */
			position staffPosition = expressManagementDao.getPositionById(staffInfo.getStaff_position());
			if (staffPosition != null && staffPosition.getPosition_name() != null
					&& staffPosition.getPosition_name().trim().length() > 0) {
				if ("驾驶员".equals(staffPosition.getPosition_name())) {
					driver driverInfo = expressManagementDao.getDriverInfoByBasicInfo(staffInfo.getStaff_id());
					if (driverInfo != null) {
						if (driverInfo.getDriver_vehicle() != null
								&& driverInfo.getDriver_vehicle().trim().length() > 0) {
							List<vehicle_express_relevance> listVehicleExpressRelevance = (List<vehicle_express_relevance>) expressManagementDao
									.listObject(
											" from vehicle_express_relevance where vehicle_express_relevance_vehicleinfo ='"
													+ driverInfo.getDriver_vehicle() + "' ");
							if (listVehicleExpressRelevance != null) {
								for (int i = 0; i < listVehicleExpressRelevance.size(); i++) {
									if (listVehicleExpressRelevance.get(i) != null) {
										if (listVehicleExpressRelevance.get(i)
												.getVehicle_express_relevance_expressinfo() != null
												&& listVehicleExpressRelevance.get(i)
														.getVehicle_express_relevance_expressinfo().trim()
														.length() > 0) {
											expressCountHql = expressCountHql + " express_id ='"
													+ listVehicleExpressRelevance.get(i)
															.getVehicle_express_relevance_expressinfo()
													+ "' ";
											listExpressInfoHql = listExpressInfoHql + " express_id ='"
													+ listVehicleExpressRelevance.get(i)
															.getVehicle_express_relevance_expressinfo()
													+ "' ";
										}
										if (i < listVehicleExpressRelevance.size() - 1) {
											expressCountHql = expressCountHql + " or  ";
											listExpressInfoHql = listExpressInfoHql + " or  ";
										}
									}
								}
							}
						}
					}
				} else if ("配送员".equals(staffPosition.getPosition_name())) {
					distributiontor distributor = expressManagementDao
							.getDistributorInfoByBasicInfo(staffInfo.getStaff_id());
					if (distributor != null) {
						if (distributor.getDistributiontor_id() != null
								&& distributor.getDistributiontor_id().trim().length() > 0) {
							expressCountHql = expressCountHql + " express_isdistributeddistributor ='"
									+ distributor.getDistributiontor_id() + "' ";
							listExpressInfoHql = listExpressInfoHql + " express_isdistributeddistributor ='"
									+ distributor.getDistributiontor_id() + "' ";
						}
					}
				} else if ("总公司管理员".equals(staffPosition.getPosition_name())) {
					expressCountHql = expressCountHql + " 1=1 ";
					listExpressInfoHql = listExpressInfoHql + " 1=1 ";
					if (expressInfoVO.getUnit() != null && expressInfoVO.getUnit().trim().length() > 0) {
						expressCountHql = expressCountHql + " and express_belongunit ='" + expressInfoVO.getUnit()
								+ "' ";
						listExpressInfoHql = listExpressInfoHql + " and express_belongunit ='" + expressInfoVO.getUnit()
								+ "' ";
					}
				} else {
					if (staffInfo.getStaff_unit() != null && staffInfo.getStaff_unit().trim().length() > 0) {
						List<unit> listUnit = (List<unit>) expressManagementDao
								.listObject(" from unit where 1=1 and ( unit_id ='" + staffInfo.getStaff_unit()
										+ " ' or unit_superiorunit ='" + staffInfo.getStaff_unit() + "' ) ");
						System.out.println("fdfdfdfdfd:" + listUnit);
						if (listUnit != null) {
							for (int i = 0; i < listUnit.size(); i++) {
								if (listUnit.get(i) != null && listUnit.get(i).getUnit_id() != null
										&& listUnit.get(i).getUnit_id().trim().length() > 0) {
									expressCountHql = expressCountHql + " express_belongunit ='"
											+ listUnit.get(i).getUnit_id().trim() + "'";
									listExpressInfoHql = listExpressInfoHql + "  express_belongunit ='"
											+ listUnit.get(i).getUnit_id().trim() + "' ";
								}
								if (i < listUnit.size() - 1) {
									expressCountHql = expressCountHql + " or  ";
									listExpressInfoHql = listExpressInfoHql + " or  ";
								}
							}
						}
					}
				}
			}
			expressCountHql = expressCountHql + " )  ";
			listExpressInfoHql = listExpressInfoHql + " )  ";
		}

		/**
		 * 模糊查询
		 */
		if (expressInfoVO.getSearch() != null && expressInfoVO.getSearch().trim().length() > 0) {
			String search = "%" + expressInfoVO.getSearch().trim() + "%";
			expressCountHql = expressCountHql + " and express_number like '" + search + "' ";
			listExpressInfoHql = listExpressInfoHql + " and express_number like '" + search + "' ";
		}
		/**
		 * 根据状态筛选
		 */
		if (expressInfoVO.getState() != null && expressInfoVO.getState().trim().length() > 0) {
			expressCountHql = expressCountHql + " and express_state ='" + expressInfoVO.getState() + "' ";
			listExpressInfoHql = listExpressInfoHql + " and express_state ='" + expressInfoVO.getState() + "' ";
		}
		/**
		 * 根据是否分配配送点或分配配送员进行筛选
		 */
		if (expressInfoVO.getIsDistributedDistribution() != null
				&& expressInfoVO.getIsDistributedDistribution().trim().length() > 0) {
			// 判断是否已分配配送点
			if ("是".equals(expressInfoVO.getIsDistributedDistribution())) {
				expressCountHql = expressCountHql
						+ " and ( express_isdistributeddistribution !='' or express_isdistributeddistribution !=null ) ";
				listExpressInfoHql = listExpressInfoHql
						+ " and ( express_isdistributeddistribution !='' or express_isdistributeddistribution !=null ) ";
				// 判断是否已分配配送员
				if ("是".equals(expressInfoVO.getIsDistributedDistributor())) {
					expressCountHql = expressCountHql
							+ " and ( express_isdistributeddistributor !='' or express_isdistributeddistributor !=null ) ";
					listExpressInfoHql = listExpressInfoHql
							+ " and ( express_isdistributeddistributor !='' or express_isdistributeddistributor !=null ) ";
				}
				else if ("否".equals(expressInfoVO.getIsDistributedDistributor())) {
					expressCountHql = expressCountHql
							+ " and ( express_isdistributeddistributor ='' or express_isdistributeddistributor =null ) ";
					listExpressInfoHql = listExpressInfoHql
							+ " and ( express_isdistributeddistributor ='' or express_isdistributeddistributor =null ) ";
				}
			}
			else if ("否".equals(expressInfoVO.getIsDistributedDistribution())) {
				expressCountHql = expressCountHql
						+ " and ( express_isdistributeddistribution ='' or express_isdistributeddistribution =null ) ";
				listExpressInfoHql = listExpressInfoHql
						+ " and ( express_isdistributeddistribution ='' or express_isdistributeddistribution =null ) ";
			}
		}

		listExpressInfoHql = listExpressInfoHql + " order by express_modifytime desc ";
		int expressCount = expressManagementDao.getCount(expressCountHql);
		/**
		 * 设置总数量
		 */
		expressInfoVO.setTotalRecords(expressCount);
		/**
		 * 设置总页数
		 */
		expressInfoVO.setTotalPages(((expressCount - 1) / expressInfoVO.getPageSize()) + 1);
		/**
		 * 判断是否拥有上一页
		 */
		if (expressInfoVO.getPageIndex() <= 1) {
			expressInfoVO.setHavePrePage(false);
		} else {
			expressInfoVO.setHavePrePage(true);
		}
		/**
		 * 判断是否拥有下一页
		 */
		if (expressInfoVO.getPageIndex() >= expressInfoVO.getTotalPages()) {
			expressInfoVO.setHaveNextPage(false);
		} else {
			expressInfoVO.setHaveNextPage(true);
		}
		
		/**
		 * 分页查询
		 */
		listExpress = (List<express>) expressManagementDao.queryForPage(listExpressInfoHql,
				expressInfoVO.getPageIndex(), expressInfoVO.getPageSize());
		if (listExpress != null) {
			for (express expressInfo : listExpress) {
				expressInfoDTO = new ExpressInfoDTO();
				if (expressInfo != null) {
					if (expressInfo.getExpress_expressinfoid() != null
							&& expressInfo.getExpress_expressinfoid().trim().length() > 0) {
						expressinfo expressDetail = expressManagementDao
								.getExpressInfoById(expressInfo.getExpress_expressinfoid());
						if (expressDetail != null) {
							expressInfoDTO.setExpressDetailInfo(expressDetail);
						}
					}
					if (expressInfo.getExpress_belong() != null
							&& expressInfo.getExpress_belong().trim().length() > 0) {
						userinfo userInfo = expressManagementDao.getUserInfoById(expressInfo.getExpress_belong());
						if (userInfo != null) {
							expressInfoDTO.setUserInfo(userInfo);
						}
					}
					if (expressInfo.getExpress_belongunit() != null
							&& expressInfo.getExpress_belongunit().trim().length() > 0) {
						unit unitInfo = expressManagementDao.getUnitInfoById(expressInfo.getExpress_belongunit());
						if (unitInfo != null) {
							expressInfoDTO.setUnitInfo(unitInfo);
						}
					}
					if (expressInfo.getExpress_originating() != null
							&& expressInfo.getExpress_originating().trim().length() > 0) {
						unit beginUnit = expressManagementDao.getUnitInfoById(expressInfo.getExpress_originating());
						if (beginUnit != null) {
							expressInfoDTO.setBeginUnit(beginUnit);
						}
					}
					if (expressInfo.getExpress_end() != null && expressInfo.getExpress_end().trim().length() > 0) {
						unit endUnit = expressManagementDao.getUnitInfoById(expressInfo.getExpress_end());
						if (endUnit != null) {
							expressInfoDTO.setEndUnit(endUnit);
						}
					}
					/**
					 * 将关键字高亮
					 */
					if (expressInfoVO.getSearch() != null && expressInfoVO.getSearch().trim().length() > 0) {
						expressInfo.setExpress_number(expressInfo.getExpress_number().replaceAll(
								expressInfoVO.getSearch(), "<mark>" + expressInfoVO.getSearch() + "</mark>"));
					}

					expressInfoDTO.setExpressInfo(expressInfo);
					listExpressInfoDTO.add(expressInfoDTO);
				}
				expressInfoVO.setListExpressInfoDTO(listExpressInfoDTO);
			}
			return expressInfoVO;
		}
		return null;
	}

	/**
	 * 历史订单
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ReservationOrderHistoryVO queryOrderHistory(ReservationOrderHistoryVO reservationOrderHistoryVO,
			userinfo userInfo) {
		List<ReservationOrderHistoryDTO> listReservationOrderHistoryDTO = new ArrayList<>();
		List<reservation> listReservation = new ArrayList<>();
		ReservationOrderHistoryDTO reservationOrderHistoryDTO;
		expressinfo expressDetailInfo;

		if (userInfo != null) {
			String reservationOrderHistoryCountHql = " select count(*) from reservation where 1=1 ";
			String listReservationOrderHistoryInfoHql = " from reservation where 1=1  ";
			reservationOrderHistoryCountHql = reservationOrderHistoryCountHql + " and reservation_user ='"
					+ userInfo.getUserinfo_id().trim() + "' ";
			listReservationOrderHistoryInfoHql = listReservationOrderHistoryInfoHql + " and reservation_user ='"
					+ userInfo.getUserinfo_id().trim() + "' ";

			/**
			 * 根据关键字进行模糊查询
			 */
			if (reservationOrderHistoryVO.getSearch() != null
					&& reservationOrderHistoryVO.getSearch().trim().length() > 0) {
				String search = "%" + reservationOrderHistoryVO.getSearch().trim() + "%";
				reservationOrderHistoryCountHql = reservationOrderHistoryCountHql + " and reservation_num like '"
						+ search + "' ";
				listReservationOrderHistoryInfoHql = listReservationOrderHistoryInfoHql + " and reservation_num like '"
						+ search + "' ";
			}
			/**
			 * 根据状态筛选(默认显示未完成的预约单)
			 */
			if (reservationOrderHistoryVO.getState() != null
					&& reservationOrderHistoryVO.getState().trim().length() > 0) {
				reservationOrderHistoryCountHql = reservationOrderHistoryCountHql + " and reservation_state ='"
						+ reservationOrderHistoryVO.getState().trim() + "' ";
				listReservationOrderHistoryInfoHql = listReservationOrderHistoryInfoHql + " and reservation_state ='"
						+ reservationOrderHistoryVO.getState().trim() + "' ";
			} else {
				reservationOrderHistoryCountHql = reservationOrderHistoryCountHql + " and reservation_state ='未完成' ";
				listReservationOrderHistoryInfoHql = listReservationOrderHistoryInfoHql
						+ " and reservation_state ='未完成' ";
			}

			listReservationOrderHistoryInfoHql = listReservationOrderHistoryInfoHql
					+ " order by reservation_createtime desc ";
			int reservationOrderHistoryCount = expressManagementDao.getCount(reservationOrderHistoryCountHql);
			// 设置总数量
			reservationOrderHistoryVO.setTotalRecords(reservationOrderHistoryCount);
			// 设置总页数
			reservationOrderHistoryVO
					.setTotalPages(((reservationOrderHistoryCount - 1) / reservationOrderHistoryVO.getPageSize()) + 1);
			// 判断是否拥有上一页
			if (reservationOrderHistoryVO.getPageIndex() <= 1) {
				reservationOrderHistoryVO.setHavePrePage(false);
			} else {
				reservationOrderHistoryVO.setHavePrePage(true);
			}
			// 判断是否拥有下一页
			if (reservationOrderHistoryVO.getPageIndex() >= reservationOrderHistoryVO.getTotalPages()) {
				reservationOrderHistoryVO.setHaveNextPage(false);
			} else {
				reservationOrderHistoryVO.setHaveNextPage(true);
			}

			listReservation = (List<reservation>) expressManagementDao.queryForPage(listReservationOrderHistoryInfoHql,
					reservationOrderHistoryVO.getPageIndex(), reservationOrderHistoryVO.getPageSize());
			if (listReservation != null) {
				for (reservation reservationInfo : listReservation) {
					reservationOrderHistoryDTO = new ReservationOrderHistoryDTO();
					expressDetailInfo = expressManagementDao
							.getExpressInfoById(reservationInfo.getReservation_expressinfo());
					if (expressDetailInfo != null) {
						reservationOrderHistoryDTO.setExpressDetailInfo(expressDetailInfo);
					}
					// 将关键字高亮
					if (reservationOrderHistoryVO.getSearch() != null
							&& reservationOrderHistoryVO.getSearch().trim().length() > 0) {
						reservationInfo.setReservation_num(
								reservationInfo.getReservation_num().replaceAll(reservationOrderHistoryVO.getSearch(),
										"<mark>" + reservationOrderHistoryVO.getSearch() + "</mark>"));
					}
					reservationOrderHistoryDTO.setReservationInfo(reservationInfo);
					listReservationOrderHistoryDTO.add(reservationOrderHistoryDTO);
				}

				reservationOrderHistoryVO.setListReservationOrderHistoryDTO(listReservationOrderHistoryDTO);
			}
			return reservationOrderHistoryVO;
		}
		return null;
	}

	/**
	 * 用户查看自己的预约单
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<ReservationExpressInfoDTO> queryUserReservation(UserInfoSessionDTO userInfo, String state) {
		List<ReservationExpressInfoDTO> listReservationExpressInfoDTO = new ArrayList<>();
		ReservationExpressInfoDTO reservationExpressInfoDTO = null;
		List<reservation> listUserReservation = new ArrayList<>();

		if (userInfo.getUserInfoSession() != null) {
			String hql = "from reservation where reservation_user='" + userInfo.getUserInfoSession().getUserinfo_id()
					+ "' ";
			if (state != null && state.trim().length() > 0) {
				hql = hql + " and reservation_state='" + state + "' ";
			}
			hql = hql + " order by reservation_createtime desc ";
			listUserReservation = (List<reservation>) expressManagementDao.listObject(hql);
			if (listUserReservation != null) {
				for (reservation reservationInfo : listUserReservation) {
					reservationExpressInfoDTO = new ReservationExpressInfoDTO();
					if (reservationInfo.getReservation_expressinfo() != null
							&& reservationInfo.getReservation_expressinfo().trim().length() > 0) {
						expressinfo expressDetailInfo = expressManagementDao
								.getExpressInfoById(reservationInfo.getReservation_expressinfo());
						if (expressDetailInfo != null) {
							reservationExpressInfoDTO.setExpressInfo(expressDetailInfo);
						}
					}
					if (reservationInfo.getReservation_unit() != null
							&& reservationInfo.getReservation_unit().trim().length() > 0) {
						unit unitInfo = expressManagementDao.getUnitInfoById(reservationInfo.getReservation_unit());
						if (unitInfo != null) {
							reservationExpressInfoDTO.setUnitInfo(unitInfo);
						}
					}
					reservationExpressInfoDTO.setReservationInfo(reservationInfo);
					listReservationExpressInfoDTO.add(reservationExpressInfoDTO);
				}
				return listReservationExpressInfoDTO;
			}
		}
		return null;
	}

	/**
	 * 取消订单
	 */
	@Override
	public String cancelReservation(reservation reservationInfo) {
		if (reservationInfo.getReservation_id() != null && reservationInfo.getReservation_id().trim().length() > 0) {
			reservation updateReservation = expressManagementDao
					.getReservationInfoById(reservationInfo.getReservation_id());
			if (updateReservation != null) {
				if (updateReservation.getReservation_state() != null
						&& updateReservation.getReservation_state().trim().length() > 0) {
					if ("已完成".equals(updateReservation.getReservation_state())) {
						return "completed";
					} else {
						updateReservation.setReservation_state("已取消");
						updateReservation.setReservation_modifytime(TimeUtil.getStringSecond());
						expressManagementDao.saveOrUpdateObject(updateReservation);
						return "success";
					}

				}
			}
		}
		return "error";
	}

	/**
	 * 修改预约单信息
	 */
	@Override
	public String updateReservationInfo(ReservationExpressInfoDTO reservationExpressInfoDTO) {
		if (reservationExpressInfoDTO != null) {
			if ("已完成".equals(reservationExpressInfoDTO.getReservationInfo().getReservation_state())) {
				return "complete";
			}
			if (reservationExpressInfoDTO.getExpressInfo() != null) {
				expressinfo updateExpressDetailInfo = reservationExpressInfoDTO.getExpressInfo();
				updateExpressDetailInfo.setExpressinfo_modifytime(TimeUtil.getStringSecond());
				expressManagementDao.saveOrUpdateObject(updateExpressDetailInfo);
				if (reservationExpressInfoDTO.getReservationInfo() != null) {
					reservation updateReservation = reservationExpressInfoDTO.getReservationInfo();
					updateReservation.setReservation_modifytime(TimeUtil.getStringSecond());
					expressManagementDao.saveOrUpdateObject(updateReservation);
					return "success";
				}
			}
		}
		return "error";
	}

	/**
	 * 查看当前预约信息
	 */
	@Override
	public ReservationExpressInfoDTO queryCurrentReservationInfo(String idList) {
		ReservationExpressInfoDTO reservationExpressInfoDTO = new ReservationExpressInfoDTO();
		if (idList != null && idList.trim().length() > 0) {
			reservation reservationInfo = expressManagementDao.getReservationInfoById(idList);
			if (reservationInfo != null) {
				reservationExpressInfoDTO.setReservationInfo(reservationInfo);
				if (reservationInfo.getReservation_expressinfo() != null
						&& reservationInfo.getReservation_expressinfo().trim().length() > 0) {
					expressinfo expressDetailInfo = expressManagementDao
							.getExpressInfoById(reservationInfo.getReservation_expressinfo());
					if (expressDetailInfo != null) {
						reservationExpressInfoDTO.setExpressInfo(expressDetailInfo);
						return reservationExpressInfoDTO;
					}
				}
			}
		}
		return null;
	}

}
