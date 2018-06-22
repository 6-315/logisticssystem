package com.logistics.expressmanagementW.service;

import java.util.List;

import com.logistics.domain.express;
import com.logistics.domain.express_circulation;
import com.logistics.domain.staff_basicinfo;
import com.logistics.domain.unit;
import com.logistics.domain.vehicle;
import com.logistics.expressmanagementW.DTO.DistributiontorAndStaffBasicinfoDTO;
import com.logistics.expressmanagementW.DTO.ExpressCirculationAndUnitDTO;
import com.logistics.expressmanagementW.DTO.GetExpressAndDispatcherDTO;
import com.logistics.expressmanagementW.DTO.GetWeightDTO;

/**
 * 快件
 * 
 * @author 哈哈哈哈哈哈
 *
 */
public interface ExpressManagementService2 {

	List<vehicle> getVehicleByID(express expressNew);

	String judgeVehicleIsOverWeight(GetWeightDTO getWeightDTO);

	List<unit> getAddressByUnit(String address);

	List<ExpressCirculationAndUnitDTO> getExpressCirculation(express expressNew);

	List<DistributiontorAndStaffBasicinfoDTO> getDispatcher(staff_basicinfo staffBasicinfo);

	String updateExpressState(GetExpressAndDispatcherDTO getExpressAndDispatcherDTO);

	String updateExpressSendState(express expressNew);

	String updateExpressByDistributiontor(staff_basicinfo staffBasicinfo, express expressNew);

	String chooseDistribution(express expressNew, unit unitNew);

}
