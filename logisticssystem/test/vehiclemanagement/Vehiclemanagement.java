package vehiclemanagement;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.logistics.domain.*;
import com.logistics.vehiclemanagement.VO.*;
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
	 * 车辆添加功能-测试
	 */
	@Test
	public void addVehicleTest() {
		vehicle vehicleInfo = new vehicle();
		vehicleInfo.setVehicle_platenum("渝F-A3D68");
		vehicleInfo.setVehicle_num("139");
		vehicleInfo.setVehicle_state("保养中");
		vehicleInfo.setVehicle_team("Note3");
		vehicleInfo.setVehicle_unit("Note4");
		vehicleInfo.setVehicle_acquisitionpeople("Admin");
		vehicleManagementService.addVehicle(vehicleInfo);
		System.out.println("+++++++add:" + vehicleInfo);
	}

	/**
	 * 车辆查询功能-测试
	 */
/*	@Test
	public void queryVehicleTest() {
		VehicleVO vehicleInfoVO = new VehicleVO();
		String search = "7";
		vehicleInfoVO.setSearch(search);
		vehicleInfoVO = vehicleManagementService.queryVehicle(vehicleInfoVO);
		System.out.println(vehicleInfoVO);
	}*/

	/**
	 * 车辆更新功能-测试
	 */
	@Test
	public void updateVehcileTest() {
		vehicle vehicleinfo = new vehicle();
		vehicleinfo.setVehicle_id("");
		vehicleinfo.setVehicle_num("9998");
		vehicleinfo.setVehicle_mark("引擎故障，无法工作");
		vehicleManagementService.updateVehicle(vehicleinfo);
		System.out.println("++++++update:" + vehicleinfo);
	}

	/**
	 * 车辆删除功能-测试
	 */
	@Test
	public void deleteVehicleTest() {
		String idList = "ea0d6851-0349-4326-9609-2e3b63eb2bab,asdjiow12312sda";
		vehicleManagementService.deleteVehicle(idList);
	}
	
	/**
	 * 车队添加功能-测试
	 */
	@Test
	public void addTeamTest() {
		team teamInfo = new team();
		teamInfo.setTeam_leader("123");
		teamInfo.setTeam_unit("Note4");
		teamInfo.setTeam_state("Perfect");
		vehicleManagementService.addTeam(teamInfo);
	}
	/**
	 * 车队查询功能-测试
	 */
	@Test
	public void queryTeamTest() {
		TeamVO teamInfoVO = new TeamVO();
		String search = "9";
		teamInfoVO.setSearch(search);
		vehicleManagementService.queryTeam(teamInfoVO);
		System.out.println(teamInfoVO);
	}
	/**
	 * 车队更新功能-测试
	 */
	@Test
	public void updateTeamTest() {
		team teamInfo = new team();
		teamInfo.setTeam_id("a50c6460-7938-4984-a862-1cb598301615");
		teamInfo.setTeam_state("空闲");
		vehicleManagementService.updateTeam(teamInfo);
	}
	/**
	 * 车队批量删除功能-测试
	 */
	@Test
	public void deleteTeamTest() {
		String idList = "";
		vehicleManagementService.deleteTeam(idList);
	}
	/**
	 * 车辆流转测试
	 */
	@Test
	public void exchangeVehicleTest() {
		vehiclecirculation vehicleCirculation = new vehiclecirculation();
		vehicleCirculation.setVehiclecirculation_vehicle_id("2");
		vehicleCirculation.setVehiclecirculation_initiative("22");
		vehicleCirculation.setVehiclecirculation_acceptd("33");
		vehicleManagementService.exchangeVehicle(vehicleCirculation);
		
	}
}
