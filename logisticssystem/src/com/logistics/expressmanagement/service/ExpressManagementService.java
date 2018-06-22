package com.logistics.expressmanagement.service;

import java.util.List;

import com.google.gson.JsonElement;
import com.logistics.domain.*;
import com.logistics.expressmanagement.DTO.*;
import com.logistics.expressmanagement.VO.*;
import com.logistics.loginregister.DTO.UserInfoSessionDTO;

/**
 * 快件管理的service层接口
 * @author LW 
 *
 */

public interface ExpressManagementService {

	public ReservationExpressInfoDTO addReservationAndExpressInfo(ReservationExpressInfoDTO reservationExpressInfoDTO, userinfo userInfo);

	public String updateReservation(String idList, String state);

	public String updateReservationWithDistributor(String idList, distributiontor distributor);

	public ExpressAndCirculationDTO completePickExpress(ExpressAndCirculationDTO expressAndCirculationDTO,staff_basicinfo staffInfo);

	/*public String updateExpressState(express expressInfo);*/

	public String judgeExpressType(express expressInfo);

	public String saveExpressRoute(String idList, express expressInfo);

	public List<RouteDTO> queryAllRouteWithUnit(unit unitInfo);

	public String updateVehicleAndExpressCirculationAndExpressInfo(ExpressAndCirculationDTO expressAndCirculationDTO, staff_basicinfo staffInfo);

	public ReservationVO queryReservationInfo(ReservationVO reservationVO, staff_basicinfo staffInfo);

	public ExpressInfoVO queryExpressInfo(ExpressInfoVO expressInfoVO, staff_basicinfo staffInfo);

	public ReservationOrderHistoryVO queryOrderHistory(ReservationOrderHistoryVO reservationOrderHistoryVO, userinfo userInfo);

	public List<ReservationExpressInfoDTO> queryUserReservation(UserInfoSessionDTO userInfo, String state);

	public String cancelReservation(reservation reservationInfo);

	public String updateReservationInfo(ReservationExpressInfoDTO reservationExpressInfoDTO);

	public ReservationExpressInfoDTO queryCurrentReservationInfo(String idList);



}
