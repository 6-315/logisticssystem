package com.logistics.expressmanagementW.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.tomcat.jni.Time;

import com.logistics.domain.distributiontor;
import com.logistics.domain.express;
import com.logistics.domain.express_circulation;
import com.logistics.domain.express_route;
import com.logistics.domain.express_send;
import com.logistics.domain.expressinfo;
import com.logistics.domain.reservation;
import com.logistics.domain.route;
import com.logistics.domain.staff_basicinfo;
import com.logistics.domain.team;
import com.logistics.domain.unit;
import com.logistics.domain.vehicle;
import com.logistics.domain.vehicle_express_relevance;
import com.logistics.expressmanagementW.DTO.DistributiontorAndStaffBasicinfoDTO;
import com.logistics.expressmanagementW.DTO.ExpressCirculationAndUnitDTO;
import com.logistics.expressmanagementW.DTO.GetExpressAndDispatcherDTO;
import com.logistics.expressmanagementW.DTO.GetWeightDTO;
import com.logistics.expressmanagementW.dao.ExpressManagementDao2;
import com.logistics.expressmanagementW.service.ExpressManagementService2;

import util.BuildUuid;
import util.TimeUtil;

public class ExpressManagementServiceImpl2 implements ExpressManagementService2 {
	/**
	 * dao层注入W
	 */
	private ExpressManagementDao2 expressManagementDao2;

	public void setExpressManagementDao2(ExpressManagementDao2 expressManagementDao2) {
		this.expressManagementDao2 = expressManagementDao2;
	}

	/**
	 * 结束
	 */
	/**
	 * 根据快件ID获取当前路线的所有车辆
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<vehicle> getVehicleByID(express expressNew) {
		if (expressNew.getExpress_id() != null && expressNew.getExpress_id().trim().length() > 0) {
			List<vehicle> listVehicle = new ArrayList<>();
			route getRoute = new route();
			express_route expressRoute = new express_route();
			expressRoute = expressManagementDao2.getexpressRoute(expressNew.getExpress_id());// 得到要跑哪一条路线
			getRoute = expressManagementDao2.getRoute(expressRoute.getExpress_route_route_id()); // 得到路线
			team teamNew = new team();
			teamNew = expressManagementDao2.getTeam(getRoute.getRoute_id());
			if (teamNew != null) {
				/**
				 * 正向所在的车辆
				 */
				if ("1".equals(expressRoute.getExpress_route_state())) {
					listVehicle = (List<vehicle>) expressManagementDao2.listObject("from vehicle where vehicle_team = '"
							+ teamNew.getTeam_id()
							+ "' and vehicle_state = '空闲' or vehicle_state = '可载货' and vehicle_drivingdirection = '"
							+ getRoute.getRoute_departurestation() + "'");
					return listVehicle;

				}
				/**
				 * 反向所在的车辆
				 */
				if ("2".equals(expressRoute.getExpress_route_state())) {
					listVehicle = (List<vehicle>) expressManagementDao2.listObject("from vehicle where vehicle_team = '"
							+ teamNew.getTeam_id()
							+ "' and vehicle_state = '空闲' or vehicle_state = '可载货' and vehicle_drivingdirection ='"
							+ getRoute.getRoute_terminalstation() + "'");
					return listVehicle;
				}

			}
		}
		return null;
	}

	/**
	 * 判断车的状态来更改车的状态判断是否超重生成流转单更改快件状态生成车和快件信息表记录
	 * 
	 */
	@Override
	public String judgeVehicleIsOverWeight(GetWeightDTO getWeightDTO) {
		// express_circulation expressCirculation = new express_circulation();
		vehicle_express_relevance vehicleExpressRelevance = new vehicle_express_relevance();
		if (getWeightDTO.getExpressNew().getExpress_id() != null
				&& getWeightDTO.getExpressNew().getExpress_id().trim().length() > 0
				&& getWeightDTO.getVehicleNew().getVehicle_id() != null
				&& getWeightDTO.getVehicleNew().getVehicle_id().trim().length() > 0) {
			vehicle vehicleNew = new vehicle();// 查车辆信息表
			express getExpress = new express();// 查快件表
			expressinfo expressInfo = new expressinfo();// 查快件信息表
			getExpress = expressManagementDao2.getExpress(getWeightDTO.getExpressNew().getExpress_id());
			vehicleNew = expressManagementDao2.getVehicle(getWeightDTO.getVehicleNew().getVehicle_id());
			expressInfo = expressManagementDao2.getExpressInfo(getExpress.getExpress_expressinfoid());
			/**
			 * 查路线
			 * 
			 */
			route routeNew = new route();// 车辆要发往哪一条路线
			express_route expressRoute = new express_route();// 获取车辆要发往哪一条路线的ID
			expressRoute = expressManagementDao2.getexpressRoute(getExpress.getExpress_id());
			routeNew = expressManagementDao2.getRoute(expressRoute.getExpress_route_id());
			int weightByNow = Integer.parseInt(vehicleNew.getVehicle_current_weight());// 车的当前重量
			int weighByExpressInfo = Integer.parseInt(expressInfo.getExpressinfo_productweight());// 快件重量
			int weighByCarAll = Integer.parseInt(vehicleNew.getVehicle_standard());// 车的总重量
			int calculation = weightByNow + weighByExpressInfo;
			if (("空闲".equals(vehicleNew.getVehicle_state()) || "可载货".equals(vehicleNew.getVehicle_state()))
					&& calculation <= weighByCarAll) {
				express_circulation expressCirculation = new express_circulation();
				expressCirculation.setExpress_circulation_id(BuildUuid.getUuid());
				expressCirculation.setExpress_circulation_express_id(getExpress.getExpress_id());
				expressCirculation.setExpress_circulation_launchpeople(getExpress.getExpress_belongunit());
				/**
				 * 如果快件路线是正向，快件流转的接收方就是路线的终点单位
				 */
				if ("1".equals(expressRoute.getExpress_route_state())) {
					expressCirculation.setExpress_circulation_receiver(routeNew.getRoute_terminalstation());
				}
				/**
				 * 如果快件路线是反向，快件流转的接收方就是路线的起始单位
				 */
				if ("2".equals(expressRoute.getExpress_route_state())) {
					expressCirculation.setExpress_circulation_receiver(routeNew.getRoute_departurestation());
				}
				expressManagementDao2.saveOrUpdateObject(expressCirculation);
				getExpress.setExpress_state("已装车");
				// getExpress.setExpress_belongunit(routeNew.getRoute_departurestation());
				expressManagementDao2.saveOrUpdateObject(getExpress);
				if ("空闲".equals(vehicleNew.getVehicle_state())) {
					vehicleNew.setVehicle_state("可载货");
				}
				vehicleNew.setVehicle_current_weight("" + calculation);
				expressManagementDao2.saveOrUpdateObject(vehicleNew);
				vehicleExpressRelevance = new vehicle_express_relevance();
				vehicleExpressRelevance.setVehicle_express_relevance_id(BuildUuid.getUuid());
				vehicleExpressRelevance.setVehicle_express_relevance_expressinfo(getExpress.getExpress_id());
				vehicleExpressRelevance.setVehicle_express_relevance_modifytime(TimeUtil.getStringSecond());
				vehicleExpressRelevance.setVehicle_express_relevance_createtime(TimeUtil.getStringSecond());
				vehicleExpressRelevance.setVehicle_express_relevance_expressinfo_begintime(TimeUtil.getStringSecond());
				expressManagementDao2.saveOrUpdateObject(vehicleExpressRelevance);
				return "success";
			} else if (calculation > weighByCarAll) {
				return "Overweight";
			} else if (calculation == weighByCarAll) {
				return "Pack full";
			}

		}
		return null;
	}

	/**
	 * 
	 * 根据快件的ID查询匹配的所有配送点
	 * 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<unit> getAddressByUnit(String address) {
		if (address != null && address.trim().length() > 0) {
			List<unit> listUint = new ArrayList<>();
			listUint = (List<unit>) expressManagementDao2
					.listObject("from unit where unit_address = '" + address + "' and unit_type = '配送点'");
			if (listUint.size() > 0) {
				return listUint;
			} else {
				return null;
			}
		}
		return null;
	}

	/**
	 * 快件详情
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<ExpressCirculationAndUnitDTO> getExpressCirculation(express expressNew) {
		List<ExpressCirculationAndUnitDTO> listExpressCirculationAndUnitDTO = new ArrayList<>();
		ExpressCirculationAndUnitDTO expressCirculationAndUnitDTO = new ExpressCirculationAndUnitDTO();
		if (expressNew.getExpress_id() != null && expressNew.getExpress_id().trim().length() > 0) {
			List<express_circulation> ListExpressCirculation = new ArrayList<>();
			ListExpressCirculation = (List<express_circulation>) expressManagementDao2
					.listObject("from express_circulation where express_circulation_express_id ='"
							+ expressNew.getExpress_id() + "' order by express_circulation_createtime");
			for (express_circulation expressCirculation : ListExpressCirculation) {
				expressCirculationAndUnitDTO = new ExpressCirculationAndUnitDTO();
				unit unitByLaunchpeople = new unit();
				unit unitByReceiver = new unit();
				unitByLaunchpeople = expressManagementDao2
						.getUnitById(expressCirculation.getExpress_circulation_launchpeople());
				unitByReceiver = expressManagementDao2
						.getUnitById(expressCirculation.getExpress_circulation_receiver());
				if (expressCirculation != null && unitByLaunchpeople != null && unitByReceiver != null) {
					expressCirculationAndUnitDTO.setExpressCirculation(expressCirculation);
					expressCirculationAndUnitDTO.setUnitByLaunchpeople(unitByLaunchpeople);
					expressCirculationAndUnitDTO.setUnitByReceiver(unitByReceiver);
					listExpressCirculationAndUnitDTO.add(expressCirculationAndUnitDTO);
				}
			}
			return listExpressCirculationAndUnitDTO;
		}
		return null;
	}

	/**
	 * 根据自身职位获取配送员
	 * 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<DistributiontorAndStaffBasicinfoDTO> getDispatcher(staff_basicinfo staffBasicinfo) {
		List<DistributiontorAndStaffBasicinfoDTO> listDistributiontorAndStaffBasicinfoDTO = new ArrayList<>();
		DistributiontorAndStaffBasicinfoDTO distributiontorAndStaffBasicinfoDTO = new DistributiontorAndStaffBasicinfoDTO();
		if (staffBasicinfo.getStaff_id() != null && staffBasicinfo.getStaff_id().trim().length() > 0
				&& staffBasicinfo.getStaff_unit() != null && staffBasicinfo.getStaff_unit().trim().length() > 0) {
			List<distributiontor> listDistributiontor = new ArrayList<>();
			listDistributiontor = (List<distributiontor>) expressManagementDao2
					.listObject("from distributiontor where distributiontor_belongdistribution ='"
							+ staffBasicinfo.getStaff_unit() + "' ");
			if (listDistributiontor.size() > 0) {
				for (distributiontor distributiontor : listDistributiontor) {
					System.out.println("ooooooooooooooooo");
					distributiontorAndStaffBasicinfoDTO = new DistributiontorAndStaffBasicinfoDTO();
					staff_basicinfo staff_Basicinfo = new staff_basicinfo();
					staff_Basicinfo = expressManagementDao2
							.getStaffBasicinfo(distributiontor.getDistributiontor_basicinfo());
					if (staff_Basicinfo != null) {
						distributiontorAndStaffBasicinfoDTO.setDistributiontorNew(distributiontor);
						distributiontorAndStaffBasicinfoDTO.setStaffBasicinfo(staff_Basicinfo);
						listDistributiontorAndStaffBasicinfoDTO.add(distributiontorAndStaffBasicinfoDTO);

					}

				}
				return listDistributiontorAndStaffBasicinfoDTO;
			}

		}
		return null;
	}

	/**
	 * 分配给生成快件配送表记录
	 * 
	 * 更改快件状态，完成流转记录
	 */
	@SuppressWarnings("unchecked")
	@Override
	public String updateExpressState(GetExpressAndDispatcherDTO getExpressAndDispatcherDTO) {
		if (getExpressAndDispatcherDTO.getExpressNew().getExpress_id() != null
				&& getExpressAndDispatcherDTO.getExpressNew().getExpress_id().trim().length() > 0
				&& getExpressAndDispatcherDTO.getStaffBasicInfo().getStaff_id() != null
				&& getExpressAndDispatcherDTO.getStaffBasicInfo().getStaff_id().trim().length() > 0) {
			List<staff_basicinfo> ListStaff = new ArrayList<>();
			ListStaff = (List<staff_basicinfo>) expressManagementDao2
					.listObject("from staff_basicinfo where staff_id ='"
							+ getExpressAndDispatcherDTO.getStaffBasicInfo().getStaff_id() + "'");
			express expressNew = new express();
			expressNew = expressManagementDao2.getExpress(getExpressAndDispatcherDTO.getExpressNew().getExpress_id());

			express_send expressSend = new express_send();
			distributiontor distributiontorNew = new distributiontor();// 配送员信息
			distributiontorNew = expressManagementDao2
					.getDistributiontor(getExpressAndDispatcherDTO.getStaffBasicInfo().getStaff_id());
			if (distributiontorNew != null) {
				expressSend.setExpress_send_id(BuildUuid.getUuid());
				expressSend.setExpress_send_express_id(getExpressAndDispatcherDTO.getExpressNew().getExpress_id());
				expressSend.setExpress_send_distributiontor(distributiontorNew.getDistributiontor_id());
				expressSend.setExpress_send_state("等待揽件");
				expressSend.setExpress_send_type("派送");
				expressSend.setExpress_send_createtime(TimeUtil.getStringSecond());
				expressSend.setExpress_send_modifytime(TimeUtil.getStringSecond());
				expressNew.setExpress_isdistributeddistributor(distributiontorNew.getDistributiontor_id());
				if (expressSend != null && expressNew != null) {
					expressManagementDao2.saveOrUpdateObject(expressNew);
					expressManagementDao2.saveOrUpdateObject(expressSend);
					return "success";
				}
			}
		}
		return "error";
	}

	/**
	 * 完成派送记录，更改快件记录
	 */
	@Override
	public String updateExpressSendState(express expressNew) {
		if (expressNew.getExpress_id() != null && expressNew.getExpress_id().trim().length() > 0) {
			express expressUpdate = new express();
			expressUpdate = expressManagementDao2.getExpress(expressNew.getExpress_id());
			express_send expressSend = new express_send();
			expressSend = expressManagementDao2.getExpressSend(expressNew.getExpress_id());
			if (expressUpdate != null && expressSend != null) {
				expressUpdate.setExpress_state("已完成");
				expressUpdate.setExpress_belongunit("");
				expressUpdate.setExpress_modifytime(TimeUtil.getStringSecond());
				expressSend.setExpress_send_state("已签收");
				expressSend.setExpress_send_modifytime(TimeUtil.getStringSecond());
				expressManagementDao2.saveOrUpdateObject(expressSend);
				expressManagementDao2.saveOrUpdateObject(expressUpdate);
				return "success";
			}
			return "error";
		}

		return "error";
	}

	/**
	 * 配送员点击揽件
	 */
	@Override
	public String updateExpressByDistributiontor(staff_basicinfo staffBasicinfo, express expressNew) {
		if (expressNew.getExpress_id() != null && expressNew.getExpress_id().trim().length() > 0) {
			express_send expressSend = new express_send();
			express getExpress = new express();
			getExpress = expressManagementDao2.getExpress(expressNew.getExpress_id());
			expressSend = expressManagementDao2.getExpressSend(expressNew.getExpress_id());
			if (getExpress != null && expressSend != null) {
				getExpress.setExpress_state("已揽件");
				getExpress.setExpress_createtime(TimeUtil.getStringSecond());
				expressSend.setExpress_send_state("已揽件正在派送");
				expressSend.setExpress_send_createtime(TimeUtil.getStringSecond());
				expressManagementDao2.saveOrUpdateObject(getExpress);
				expressManagementDao2.saveOrUpdateObject(expressSend);
				System.out.println("成功！");
				return "success";
			}

		}
		return null;
	}

	/**
	 * 中转站选择配送点分配快件
	 */
	@Override
	public String chooseDistribution(express expressNew, unit unitNew) {
		if (expressNew == null && unitNew == null) {
			return "error";
		}
		expressNew.setExpress_state("待揽件");
		expressNew.setExpress_isdistributeddistribution(unitNew.getUnit_id());
		express_circulation expressCirculation = new express_circulation();
		expressCirculation.setExpress_circulation_id(BuildUuid.getUuid());
		expressCirculation.setExpress_circulation_express_id(expressNew.getExpress_id());
		expressCirculation.setExpress_circulation_launchpeople(expressNew.getExpress_belongunit());
		expressCirculation.setExpress_circulation_receiver(unitNew.getUnit_id());
		if (expressNew != null && expressCirculation != null) {
			expressManagementDao2.saveOrUpdateObject(expressCirculation);
			expressManagementDao2.saveOrUpdateObject(expressNew);
			return "success";
		}
		return "error";
	}
}
