package transferstation;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.logistics.domain.unit;
import com.logistics.transferstation.service.TransferStationService;

import util.BuildUuid;
import util.TimeUtil;

/**
 * 中转站管理测试方法
 * 
 * @author LW
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
	@Test
	public void deleteTransferStation() {
		unit transferStation = new unit();
		transferStation.setUnit_id("9dba8b9d-ae80-458b-80b8-c5fe440f6486");
		System.out.println("shanchu");
		transferStationService.deleteTransferStation(transferStation);
		
	}
	/*@Test
	public void updataTransferStation() {
		unit transferStation = new unit();
		System.out.println("xiugai");
		transferStationService.(transferStation);
}*/
	/*@Test
	public void queryTransferStation() {
		unit transferStation = new unit();
		List<unit> listunit = new ArrayList<>();
		System.out.println("chaxun");
		listunit =	transferStationService.queryTransferStation(transferStation);
}*/
}
