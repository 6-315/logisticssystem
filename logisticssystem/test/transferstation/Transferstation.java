package transferstation;

import javax.annotation.Resource;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.logistics.transferstation.service.TransferStationService;

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

}
