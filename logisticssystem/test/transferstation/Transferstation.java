package transferstation;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.logistics.domain.staff_basicinfo;
import com.logistics.domain.unit;
import com.logistics.transferstation.VO.UnitManagerVO;
import com.logistics.transferstation.service.TransferStationService;

/**
 * 中转站管理测试方法
 * 
 * @author LL
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext*.xml" })
public class Transferstation {
	@Resource
	private TransferStationService transferStationService;

	public TransferStationService getTransferStationService() {
		return transferStationService;
	}

	public void setTransferStationService(TransferStationService transferStationService) {
		this.transferStationService = transferStationService;
	}
/**
 * 添加测试
 */
	@Test
	public void addTransferStation() {
		unit transferStation = new unit();
		staff_basicinfo staffBasicinfo = new staff_basicinfo();
		/*transferStation.setUnit_name("lailiang");
		transferStation.setUnit_address("hubei");
		transferStation.setUnit_detailaddress("hubeiwuhan");
		transferStation.setUnit_type("配送点");
		transferStation.setUnit_creator("zonggongsi");
		transferStation.setUnit_state("zhengchang");
		transferStation.setUnit_phonenumber("123");*/
		
		
		/*staffBasicinfo.setStaff_id("78e07c34-735f-45d4-a870-3e5bebe5ddc1");
		staffBasicinfo.setStaff_position("71e07c34-735f-45d4-a870-3e5bebe5ddc1");
		staffBasicinfo.setStaff_unit("77e07c34-735f-45d4-a870-3e5bebe5ddc1");
		transferStation.setUnit_address("hubei");
		transferStation.setUnit_detailaddress("hubeiwuhan");
		transferStation.setUnit_type("中转站");
		transferStation.setUnit_creator("zonggongsi");*/
		staffBasicinfo.setStaff_id("78e07c34-735f-45d4-a870-3e5bebe5ddc3");
		staffBasicinfo.setStaff_position("71e07c34-735f-45d4-a870-3e5bebe5ddc2");
		staffBasicinfo.setStaff_unit("77e07c34-735f-45d4-a870-3e5bebe5ddc2");
		transferStation.setUnit_address("hubei");
		transferStation.setUnit_detailaddress("hubeiwuhan");
		transferStation.setUnit_type("配送点");
		transferStation.setUnit_creator("zonggongsi");
		// staff_basicinfo staffBasicinfo = new staff_basicinfo();
		// staffBasicinfo.setStaff_id("dsdsd");
		transferStationService.addTransferStation(transferStation,staffBasicinfo);
		System.out.println("2222222"+transferStation);
		
	}
	/**
	 * 删除测试
	 */
	@Test
	public void deleteTransferStation() {
		String  idList ="";
		System.out.println("1234567"+idList);
		transferStationService.deleteTransferStation(idList);
		
	}
	/**
	 * 修改测试
	 */
	@Test
	public void updateTransferStation() {
		unit transferStation = new unit();
		transferStation.setUnit_id("e88d299d-c00b-4050-81c6-5037e81ed2f7");
		transferStation.setUnit_address("lalala");
		transferStationService.updateTransferStation(transferStation, null);
		
		System.out.println("xiugai");
		
}   
	/**
	 * 查询测试类
	 */
	@Test
	public void queryTransferStation() {
		UnitManagerVO transferStationVO = new UnitManagerVO();
		staff_basicinfo staffBasicInfo = new staff_basicinfo();
		/*String search = "l";
		transferStationVO.setSearch(search);*/
		staffBasicInfo.setStaff_id("78e07c34-735f-45d4-a870-3e5bebe5ddc1");
		staffBasicInfo.setStaff_unit("77e07c34-735f-45d4-a870-3e5bebe5ddc2");
		transferStationVO = transferStationService.queryTransferStation(transferStationVO,staffBasicInfo);
		System.out.println("chaxun"+transferStationVO);
		
	}
	/**
	 * 分配车辆测试类
	 */
	@Test
	public void vehicleDistribution() {
		String vehicleList = "1,2";
		System.out.println("66658217"+vehicleList);
		String teamNum=" ";
		transferStationService.vehicleDistribution(vehicleList, teamNum);
		System.out.println("aaaaaaddd"+vehicleList+teamNum);
	}
	/**
	 * 招募司机
	 */
	@Test
	public void driverRecruit() {
		staff_basicinfo driver = new staff_basicinfo();
				driver.setStaff_birthday("19970723");
				transferStationService.driverRecruit(driver);
				System.out.println("招募成功"+driver);
	}
	/**
	 * 分配车辆测试类
	 */
	@Test
	public void driverDistribution() {
		String driverList = "1,2";
		System.out.println("66658217"+driverList);
		String teamNum="1";
		transferStationService.driverDistribution(driverList, teamNum);
		System.out.println("aaaaaaddd"+driverList+teamNum);
	}
	/**
	 * 查询自身单位以及自身以下单位
	 */
	@Test
	public void getUnitInfo() {
		staff_basicinfo staffBasicInfo = new staff_basicinfo();
		staffBasicInfo.setStaff_id("78e07c34-735f-45d4-a870-3e5bebe5ddc3");
		staffBasicInfo.setStaff_position("71e07c34-735f-45d4-a870-3e5bebe5ddc2");
		staffBasicInfo.setStaff_unit("77e07c34-735f-45d4-a870-3e5bebe5ddc2");
		transferStationService.getUnitInfo(staffBasicInfo);
	}
}
