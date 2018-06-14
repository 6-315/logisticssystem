package com.logistics.expressmanagementW.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.logistics.domain.express;
import com.logistics.domain.express_circulation;
import com.logistics.domain.express_route;
import com.logistics.domain.express_send;
import com.logistics.domain.expressinfo;
import com.logistics.domain.route;
import com.logistics.domain.staff_basicinfo;
import com.logistics.domain.unit;
import com.logistics.domain.vehicle;
import com.logistics.domain.vehicle_express_relevance;
import com.logistics.expressmanagementW.DTO.GetExpressAndDispatcher;
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
	@Override
	public List<vehicle> getVehicleByID(express expressNew) {
		if (expressNew.getExpress_id() != null && expressNew.getExpress_id().trim().length() > 0) {
			List<vehicle> listVehicle = new ArrayList<>();
			express getExpress = new express();
			getExpress = expressManagementDao2.getExpress(expressNew.getExpress_id());
			listVehicle = (List<vehicle>) expressManagementDao2.listObject("from vehicle where express_belongunit = '"
					+ getExpress.getExpress_belongunit() + "' and " + "vehicle_drivingdirection = '正向' ");
			return listVehicle;
		}
		return null;
	}

	/**
	 * 
	 */
	@Override
	public String getVehicleIsOverWeight(GetWeightDTO getWeightDTO) {
		express_circulation expressCirculation = new express_circulation();
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
			 */
			route routeNew = new route();// 车辆要发往哪一条路线
			express_route expressRoute = new express_route();// 获取车辆要发往哪一条路线的ID
			expressRoute = expressManagementDao2.getexpressRoute(getExpress.getExpress_id());
			routeNew = expressManagementDao2.getRoute(expressRoute.getExpress_route_id());
			int weightByNow = Integer.parseInt(vehicleNew.getVehicle_current_weight());// 车的当前重量
			int weighByExpressInfo = Integer.parseInt(expressInfo.getExpressinfo_productweight());// 快件重量
			int weighByCarAll = Integer.parseInt(vehicleNew.getVehicle_current_weight());// 车的总重量
			int calculation = weightByNow + weighByExpressInfo;
			if (("空闲".equals(vehicleNew.getVehicle_express_state())
					|| "可载货".equals(vehicleNew.getVehicle_express_state())) && calculation <= weighByCarAll) {
				expressCirculation = new express_circulation();
				expressCirculation.setExpress_circulation_id(BuildUuid.getUuid());
				expressCirculation.setExpress_circulation_express_id(getExpress.getExpress_id());
				expressCirculation.setExpress_circulation_launchpeople(routeNew.getRoute_departurestation());
				expressCirculation.setExpress_circulation_receiver(routeNew.getRoute_terminalstation());
				expressManagementDao2.saveOrUpdateObject(expressCirculation);
				getExpress.setExpress_state("已装车");
				getExpress.setExpress_belongunit(routeNew.getRoute_departurestation());
				expressManagementDao2.saveOrUpdateObject(getExpress);
				if (vehicleNew.getVehicle_unit().equals(routeNew.getRoute_departurestation())) {
					vehicleNew.setVehicle_drivingdirection("正向");
				} else {
					vehicleNew.setVehicle_drivingdirection("反向");
				}
				if ("空闲".equals(vehicleNew.getVehicle_express_state())) {
					vehicleNew.setVehicle_express_state("可载货");
				}
				expressManagementDao2.saveOrUpdateObject(vehicleNew);
				vehicleExpressRelevance = new vehicle_express_relevance();
				vehicleExpressRelevance.setVehicle_express_relevance_id(BuildUuid.getUuid());
				vehicleExpressRelevance.setVehicle_express_relevance_expressinfo(getExpress.getExpress_id());
				vehicleExpressRelevance.setVehicle_express_relevance_modifytime(TimeUtil.getStringSecond());
				vehicleExpressRelevance.setVehicle_express_relevance_createtime(TimeUtil.getStringSecond());
				expressManagementDao2.saveOrUpdateObject(vehicleExpressRelevance);
				return "Success";
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
	 */
	@Override
	public List<unit> getAddressByUnit(express expressNew) {
		if (expressNew.getExpress_id() != null && expressNew.getExpress_id().trim().length() > 0) {
			List<unit> listUint = new ArrayList<>();
			expressinfo expressInfo = new expressinfo();
			expressInfo = expressManagementDao2.getExpressInfo(expressNew.getExpress_id());
			listUint = (List<unit>) expressManagementDao2.listObject(
					"from unit where unit_address = '" + expressInfo.getExpressinfo_addresseeaddress() + "'");
			return listUint;
		}
		return null;
	}

	/**
	 * 快件详情
	 */
	@Override
	public List<express_circulation> getExpressCirculation(express expressNew) {
		if (expressNew.getExpress_id() != null && expressNew.getExpress_id().trim().length() > 0) {
			List<express_circulation> ListExpressCirculation = new ArrayList<>();
			ListExpressCirculation = (List<express_circulation>) expressManagementDao2
					.listObject("from express_circulation where express_circulation_express_id ='"
							+ expressNew.getExpress_id() + "'");
			return ListExpressCirculation;
		}
		return null;
	}

	/**
	 * 根据自身职位获取配送员
	 */
	@Override
	public List<staff_basicinfo> getDispatcher(staff_basicinfo staffBasicinfo) {
		if (staffBasicinfo.getStaff_id() != null && staffBasicinfo.getStaff_id().trim().length() > 0
				&& staffBasicinfo.getStaff_unit() != null && staffBasicinfo.getStaff_unit().trim().length() > 0) {
			List<staff_basicinfo> listStaffBasicInfo = new ArrayList<>();
			listStaffBasicInfo = (List<staff_basicinfo>) expressManagementDao2
					.listObject("from staff_basicinfo where staff_unit ='" + staffBasicinfo.getStaff_unit()
							+ "'and staff_id !='" + staffBasicinfo.getStaff_id() + "'");
			return listStaffBasicInfo;
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
	public String updateExpressState(GetExpressAndDispatcher getExpressAndDispatcher) {
		if (getExpressAndDispatcher.getExpressNew().getExpress_id() != null
				&& getExpressAndDispatcher.getExpressNew().getExpress_id().trim().length() > 0
				&& getExpressAndDispatcher.getStaffBasicInfo().getStaff_id() != null
				&& getExpressAndDispatcher.getStaffBasicInfo().getStaff_id().trim().length() > 0) {
			List<staff_basicinfo> ListStaff = new ArrayList<>();
			ListStaff = (List<staff_basicinfo>) expressManagementDao2
					.listObject("from staff_basicinfo where staff_id ='"
							+ getExpressAndDispatcher.getStaffBasicInfo().getStaff_id() + "'");
			express_send expressSend = new express_send();
			expressSend.setExpress_send_id(BuildUuid.getUuid());
			expressSend.setExpress_send_express_id(getExpressAndDispatcher.getExpressNew().getExpress_id());
			expressSend.setExpress_send_distributiontor(getExpressAndDispatcher.getStaffBasicInfo().getStaff_id());
			expressSend.setExpress_send_state("揽件中");
			expressSend.setExpress_send_createtime(TimeUtil.getStringSecond());
			expressSend.setExpress_send_modifytime(TimeUtil.getStringSecond());
			expressManagementDao2.saveOrUpdateObject(expressSend);
			express expressNew = new express();
			expressNew = expressManagementDao2.getExpress(getExpressAndDispatcher.getExpressNew().getExpress_id());
			expressNew.setExpress_state("派送中");
			express_circulation expressCirculation = new express_circulation();
			expressCirculation.setExpress_circulation_id(BuildUuid.getUuid());
			expressCirculation.setExpress_circulation_express_id(expressNew.getExpress_id());
			expressCirculation.setExpress_circulation_launchpeople(expressNew.getExpress_end());
			expressCirculation.setExpress_circulation_receiver(ListStaff.get(0).getStaff_unit());
			expressManagementDao2.saveOrUpdateObject(expressCirculation);
			return "Success";

		}

		return null;
	}
}
