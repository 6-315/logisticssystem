package com.logistics.expressmanagement.service;

import java.util.List;

import com.logistics.domain.*;
import com.logistics.expressmanagement.DTO.*;
import com.logistics.expressmanagement.VO.*;

/**
 * 快件管理的service层接口
 * @author LW 
 *
 */

public interface ExpressManagementService {

	public ReservationExpressInfoDTO addReservationAndExpressInfo(ReservationExpressInfoDTO reservationExpressInfoDTO, userinfo userInfo);

	public String updateReservation(reservation reservationInfo);

	public String updateReservationWithDistributor(ReservationWithDistributorDTO reservationWithDistributorDTO);

	public ExpressAndCirculationDTO completePickExpress(ExpressAndCirculationDTO expressAndCirculationDTO, staff_basicinfo staffInfo);

	public String updateExpressState(express expressInfo);

	public String judgeExpressType(express expressInfo);

	public String saveExpressRoute(String idList, express expressInfo);

	public List<route> queryAllRouteWithUnit(unit unitInfo);

	public String updateVehicleAndExpressCirculationAndExpressInfo(express expressInfo, vehicle vehicleInfo, staff_basicinfo staffInfo);

	public ReservationVO queryReservationInfo(ReservationVO reservationVO, staff_basicinfo staffInfo);

	public ExpressInfoVO queryExpressInfo(ExpressInfoVO expressVO, staff_basicinfo staffInfo);

	public ReservationOrderHistoryVO queryOrderHistory(ReservationOrderHistoryVO reservationOrderHistoryVO, userinfo userInfo);


}
