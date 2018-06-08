package transferstation;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
		transferStation.setUnit_name("lailiang");
		transferStation.setUnit_address("hubei");
		transferStation.setUnit_detailaddress("hubeiwuhan");
		transferStation.setUnit_type("zhongzhuangzhan");
		transferStation.setUnit_creator("zonggongsi");
		transferStation.setUnit_state("zhengchang");
		transferStation.setUnit_phonenumber("123");
		transferStationService.addTransferStation(transferStation);
		System.out.println("2222222"+transferStation);
		
	}
	/**
	 * 删除测试
	 */
	@Test
	public void deleteTransferStation() {
		unit transferStation = new unit();
		transferStation.setUnit_id("transferStation");
		System.out.println("shanchu");
		transferStationService.deleteTransferStation(transferStation);
		
	}
	/**
	 * 修改测试
	 */
	@Test
	public void updateTransferStation() {
		unit transferStation = new unit();
		transferStation.setUnit_id("e88d299d-c00b-4050-81c6-5037e81ed2f7");
		transferStation.setUnit_address("lalala");
		transferStationService.updateTransferStation(transferStation);
		
		System.out.println("xiugai");
		
}   
	/**
	 * 查询测试类
	 */
	@Test
	public void queryTransferStation() {
		UnitManagerVO transferStationVO = new UnitManagerVO();
		transferStationVO.setAddress("hubei");
		transferStationVO.setState("zhengchang");
		transferStationVO = transferStationService.queryTransferStation(transferStationVO);
		System.out.println("chaxun"+transferStationVO);
		
	}
	
}
