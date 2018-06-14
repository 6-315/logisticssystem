package com.logistics.expressmanagementW.service;

import java.util.List;

import com.logistics.domain.express;
import com.logistics.domain.express_circulation;
import com.logistics.domain.staff_basicinfo;
import com.logistics.domain.unit;
import com.logistics.domain.vehicle;
import com.logistics.expressmanagementW.DTO.GetExpressAndDispatcher;
import com.logistics.expressmanagementW.DTO.GetWeightDTO;

/**
 * 快件
 * @author 哈哈哈哈哈哈
 *
 */
public interface ExpressManagementService2 {

	List<vehicle> getVehicleByID(express expressNew);

	String getVehicleIsOverWeight(GetWeightDTO getWeightDTO);

	List<unit> getAddressByUnit(express expressNew);

	List<express_circulation> getExpressCirculation(express expressNew);

	List<staff_basicinfo> getDispatcher(staff_basicinfo staffBasicinfo);

	String updateExpressState(GetExpressAndDispatcher getExpressAndDispatcher);


}
