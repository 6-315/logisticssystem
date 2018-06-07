package vehiclemanagement;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.logistics.domain.vehicle;
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

	@Test
	public void addTest() {
		System.out.println(vehicleManagementService);
		vehicle vehicleinfo = new vehicle();
		vehicleinfo.setVehicle_platenum("渝G-A9568");
		vehicleinfo.setVehicle_num("1");
		vehicleinfo.setVehicle_state("待分配");
		vehicleinfo.setVehicle_team("Note3");
		vehicleinfo.setVehicle_unit("Note4");
		vehicleinfo.setVehicle_acquisitionpeople("Admin");
		vehicleManagementService.addVehicle(vehicleinfo);
		System.out.println("+++test:" + vehicleinfo);
	}

}
