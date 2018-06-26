package expressmanagementByExpressmanagement;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.logistics.domain.express;
import com.logistics.domain.*;
import com.logistics.expressmanagementW.DTO.DistributiontorAndStaffBasicinfoDTO;
import com.logistics.expressmanagementW.DTO.ExpressCirculationAndUnitDTO;
import com.logistics.expressmanagementW.DTO.GetExpressAndDispatcherDTO;
import com.logistics.expressmanagementW.DTO.GetWeightDTO;
import com.logistics.expressmanagementW.service.ExpressManagementService2;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext*.xml" })
public class ExpressmanagementByLiWei {
	/**
	 * service层注入
	 */
	@Resource
	private ExpressManagementService2 expressManagementService2;

	public void setExpressManagementService2(ExpressManagementService2 expressManagementService2) {
		this.expressManagementService2 = expressManagementService2;
	}

	/**
	 * 根据快件ID获取当前路线的所有车辆 有快件对象ID
	 * 
	 * @throws IOException
	 */
	@Test
	public void getVehicleByID() {
		express expressNew = new express();
		expressNew.setExpress_id("999");
		List<vehicle> listVehicle = expressManagementService2.getVehicleByID(expressNew);
		System.out.println("eeee" + listVehicle);
	}

	/**
	 * 扫描装车操作
	 */
	@Test
	public void zhongliang() {
		GetWeightDTO getWeightDTO = new GetWeightDTO();
		express expressNew = new express();
		expressNew.setExpress_id("999");
		vehicle vehicleNew = new vehicle();
		vehicleNew.setVehicle_id("1");
		getWeightDTO.setExpressNew(expressNew);
		getWeightDTO.setVehicleNew(vehicleNew);
		expressManagementService2.judgeVehicleIsOverWeight(getWeightDTO);
	}

	/**
	 * 根据快件ID获取配送点地址
	 */
	// @Test
	/*
	 * public void allUnit() { express expressNew = new express();
	 * expressNew.setExpress_id("999"); List<unit> listUnit =
	 * expressManagementService2.getAddressByUnit(expressNew);
	 * System.out.println("???????????????" + listUnit); }
	 */

	/**
	 * 查看快件详情
	 */
	@Test
	public void listExpressCirculationAndUnitDTO() {
		express expressNew = new express();
		expressNew.setExpress_id("999");
		List<ExpressCirculationAndUnitDTO> listExpressCirculationAndUnitDTO = expressManagementService2
				.getExpressCirculation(expressNew);
		System.out.println("IIIIIIIIIIIIIIIIIII=:" + listExpressCirculationAndUnitDTO);
	}

	/**
	 * 根据自身session查询自身以下的所有配送员
	 */
	@Test
	public void peisongyuan() {
		staff_basicinfo staffBasicinfo = new staff_basicinfo();
		staffBasicinfo.setStaff_id("1");
		staffBasicinfo.setStaff_unit("1");
		List<DistributiontorAndStaffBasicinfoDTO> listDistributiontorAndStaffBasicinfoDTO = expressManagementService2
				.getDispatcher(staffBasicinfo);
		System.out.println("kkkkkkkkkkkk：" + listDistributiontorAndStaffBasicinfoDTO);

	}

	/**
	 * 
	 * 选择配送员送货上门
	 */
	@Test
	public void qqq() {
		GetExpressAndDispatcherDTO getExpressAndDispatcherDTO = new GetExpressAndDispatcherDTO();
		staff_basicinfo staffBasicinfo = new staff_basicinfo();
		staffBasicinfo.setStaff_id("1");
		express expressNew = new express();
		expressNew.setExpress_id("999");
		getExpressAndDispatcherDTO.setExpressNew(expressNew);
		getExpressAndDispatcherDTO.setStaffBasicInfo(staffBasicinfo);
		expressManagementService2.updateExpressState(getExpressAndDispatcherDTO);
	}

	/**
	 * 完成签收
	 */
	@Test
	public void ii() {
		express expressNew = new express();
		expressNew.setExpress_id("999");
		expressManagementService2.updateExpressSendState(expressNew);

	}

/*	*//**
	 * 配送员对快件的操作
	 *//*
	@Test
	public void qq() {
		express expressNew = new express();
		expressNew.setExpress_id("999");
		staff_basicinfo staffBasicinfo = new staff_basicinfo();
		expressManagementService2.updateExpressByDistributiontor(staffBasicinfo, expressNew);

	}*/
}
