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

}
