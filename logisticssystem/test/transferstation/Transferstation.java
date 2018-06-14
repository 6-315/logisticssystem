package transferstation;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
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
		transferStation.setUnit_name("lailiang");
		transferStation.setUnit_address("hubei");
		transferStation.setUnit_detailaddress("hubeiwuhan");
		transferStation.setUnit_type("zhongzhuangzhan");
		transferStation.setUnit_creator("zonggongsi");
		transferStation.setUnit_state("zhengchang");
		transferStation.setUnit_phonenumber("123");
		staff_basicinfo staffBasicinfo = new staff_basicinfo();
		staffBasicinfo.setStaff_id("dsdsd");
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
		transferStationService.updateTransferStation(transferStation);
		
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
		transferStationVO.setSuperiorunit("");
		transferStationVO = transferStationService.queryTransferStation(transferStationVO,staffBasicInfo);
		System.out.println("chaxun"+transferStationVO);
		
	}
	
}
