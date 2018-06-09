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
		vehicleInfo.setVehicle_platenum("渝G-A99A5");
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
	@Test
	public void queryVehicleTest() {
		VehicleVO vehicleInfoVO = new VehicleVO();
		String search = "7";
		vehicleInfoVO.setSearch(search);
		vehicleInfoVO = vehicleManagementService.queryVehicle(vehicleInfoVO);
		System.out.println(vehicleInfoVO);
	}

	/**
	 * 车辆更新功能-测试
	 */
	@Test
	public void updateVehcileTest() {
		vehicle vehicleinfo = new vehicle();
		vehicleinfo.setVehicle_id("857b5726-0fe1-4683-8ede-01b4d41b3fd1");
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
		String idList = "ea0d6851-0349-4326-9609-2e3b63eb2bab";
		VehicleVO vehicleInfoVO = new VehicleVO();
		vehicleInfoVO.setIdList(idList);
		vehicleManagementService.deleteVehicle(vehicleInfoVO);
	}
	
	/**
	 * 车队添加功能-测试
	 */
	@Test
	public void addTeamTest() {
		team teamInfo = new team();
		teamInfo.setTeam_leader("123");
		teamInfo.setTeam_num("929");
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
		teamInfo.setTeam_id("b0f41b73-d4c8-4e06-a48a-958f056dfc83");
		teamInfo.setTeam_num("51849");
		vehicleManagementService.updateTeam(teamInfo);
	}
	/**
	 * 车队批量删除功能-测试
	 */
	@Test
	public void deleteTeamTest() {
		String idList = "509435ae-5991-431f-b5e7-b72d41eb3d92,b0f41b73-d4c8-4e06-a48a-958f056dfc83";
		TeamVO teamInfoVO = new TeamVO();
		teamInfoVO.setIdList(idList);
		vehicleManagementService.deleteTeam(teamInfoVO);
	}
}
