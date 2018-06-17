package expressmanagement;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.logistics.domain.*;
import com.logistics.expressmanagement.DTO.ExpressAndCirculationDTO;
import com.logistics.expressmanagement.DTO.ReservationExpressInfoDTO;
import com.logistics.expressmanagement.DTO.ReservationWithDistributorDTO;
import com.logistics.expressmanagement.VO.*;
import com.logistics.expressmanagement.service.ExpressManagementService;
/**
 * 快件管理的测试方法
 * @author LW
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext*.xml" })
public class Expressmanagement {
	@Resource
	private ExpressManagementService expressManagementService;

	public ExpressManagementService getExpressManagementService() {
		return expressManagementService;
	}

	public void setExpressManagementService(ExpressManagementService expressManagementService) {
		this.expressManagementService = expressManagementService;
	}
	
	
	/**
	 * 预约表VO测试
	 */
	@Test
	public void queryReservationInfoTest() {
		ReservationVO reservationVO = new ReservationVO();
		staff_basicinfo staffInfo = new staff_basicinfo();
		staffInfo.setStaff_id("1234");
		staffInfo.setStaff_unit("123");
		reservationVO = expressManagementService.queryReservationInfo(reservationVO, staffInfo);
		System.out.println("000000000"+reservationVO);
	}

	/**
	 * 添加预约单测试
	 */
	@Test
	public void addReservationAndExpressInfoTest() {
		userinfo userInfo = new userinfo();
		userInfo.setUserinfo_id("123");
		expressinfo expressInfo = new expressinfo();
		expressInfo.setExpressinfo_addresseerealname("阿迪达斯");
		reservation reservationInfo = new reservation();
		ReservationExpressInfoDTO reservationExpressInfoDTO = new ReservationExpressInfoDTO();
		reservationExpressInfoDTO.setExpressInfo(expressInfo);
		reservationExpressInfoDTO.setReservationInfo(reservationInfo);
		reservationExpressInfoDTO = expressManagementService.addReservationAndExpressInfo(reservationExpressInfoDTO, userInfo);
		System.out.println(reservationExpressInfoDTO);
	}
	
	/**
	 * 受理客户预约
	 */
	@Test
	public void updateReservationTest() {
		reservation reservationInfo = new reservation();
		reservationInfo.setReservation_id("2176f29b-f0f4-42db-a91f-7e86fa3ed2db");
		reservationInfo.setReservation_state("已受理");
		expressManagementService.updateReservation(reservationInfo);
		
	}
	
	/**
	 * 分配配送员
	 */
	@Test
	public void updateReservationWithDistributorTest() {
		ReservationWithDistributorDTO reservationWithDistributorDTO = new ReservationWithDistributorDTO();
		distributiontor distributor = new distributiontor();
		distributor.setDistributiontor_id("123");
		reservation reservationInfo = new reservation();
		reservationInfo.setReservation_id("2176f29b-f0f4-42db-a91f-7e86fa3ed2db");
		reservationWithDistributorDTO.setDistributor(distributor);
		reservationWithDistributorDTO.setReservationInfo(reservationInfo);
		expressManagementService.updateReservationWithDistributor(reservationWithDistributorDTO);
	}
	
	/**
	 * 上门取件
	 */
	@Test
	public void completePickExpressTest() {
		ExpressAndCirculationDTO expressAndCirculationDTO = new ExpressAndCirculationDTO();
		staff_basicinfo staffInfo = new staff_basicinfo();
		staffInfo.setStaff_id("1234");
		staffInfo.setStaff_unit("234");
		expressManagementService.completePickExpress(expressAndCirculationDTO, staffInfo);
		
	}
	
	/**
	 *  到达中转站
	 */
	@Test
	public void updateExpressStateTest() {
		express expressInfo = new express();
		expressInfo.setExpress_id("01f4fc19-537c-4fb0-a3c7-e11659e29ac3");
		expressInfo.setExpress_state("待扫描");
		expressManagementService.updateExpressState(expressInfo);
		
	}
	
	/**
	 * 查询所有经过该站点的路线
	 */
	@Test
	public void queryAllRouteWithUnitTest() {
		unit unitInfo = new unit();
		unitInfo.setUnit_id("1");
		System.out.println(expressManagementService.queryAllRouteWithUnit(unitInfo));
		
		
		
	}
	
	
	
	
}
