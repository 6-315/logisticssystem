package vehiclemanagement;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.logistics.domain.vehicle;
import com.logistics.vehiclemanagement.VO.VehicleVO;
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

	/**
	 * 添加功能-测试
	 */
	@Test
	public void addTest() {
		vehicle vehicleInfo = new vehicle();
		vehicleInfo.setVehicle_platenum("渝G-A9568");
		vehicleInfo.setVehicle_num("1");
		vehicleInfo.setVehicle_state("待分配");
		vehicleInfo.setVehicle_team("Note3");
		vehicleInfo.setVehicle_unit("Note4");
		vehicleInfo.setVehicle_acquisitionpeople("Admin");
		vehicleManagementService.addVehicle(vehicleInfo);
		System.out.println("+++test:" + vehicleInfo);
	}

	/**
	 * 查询功能-测试
	 */
	@Test
	public void queryTest() {
		VehicleVO vehicleInfoVO = new VehicleVO();
		vehicleInfoVO = vehicleManagementService.queryVehicle(vehicleInfoVO);
		System.out.println(vehicleInfoVO);
	}

	/**
	 * 更新功能-测试
	 */
	@Test
	public void updateTest() {
		vehicle vehicleinfo = new vehicle();
		vehicleinfo.setVehicle_id("af5b73b2-78e0-4471-a39b-9ab23c2b9d68");
		vehicleinfo.setVehicle_num("998");
		vehicleManagementService.updateVehicle(vehicleinfo);
	}
	
	/**
	 * 删除功能-测试
	 */
	@Test
	public void deleteTest() {
		String ids = "ea0d6851-0349-4326-9609-2e3b63eb2bab";
		VehicleVO vehicleInfoVO = new VehicleVO();
		vehicleInfoVO.setIdList(ids);
		vehicleManagementService.deleteVehicle(vehicleInfoVO);
	}
}
