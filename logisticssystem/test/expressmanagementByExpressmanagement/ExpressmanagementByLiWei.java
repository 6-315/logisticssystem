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
	@Test
	public void allUnit() {
		express expressNew = new express();
		expressNew.setExpress_id("999");
		List<unit> listUnit = expressManagementService2.getAddressByUnit(expressNew);
	}
}
