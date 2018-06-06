package vehiclemanagement;

import javax.annotation.Resource;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.logistics.vehiclemanagement.service.VehicleManagementService;

/**
 * 车辆管理测试方法
 * 
 * @author LW
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext*.xml" })
public class Vehiclemanagement {
	@Resource
	private VehicleManagementService vehicleManagementService;

	public VehicleManagementService getVehicleManagementService() {
		return vehicleManagementService;
	}

	public void setVehicleManagementService(VehicleManagementService vehicleManagementService) {
		this.vehicleManagementService = vehicleManagementService;
	}

}
