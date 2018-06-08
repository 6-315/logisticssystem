package distribution;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.logistics.distribution.VO.UnitManagerVO;
import com.logistics.distribution.service.DistributionService;
import com.logistics.domain.unit;

/**
 * 配送点管理测试方法
 * 
 * @author LW
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext*.xml" })
public class Distribution {
	@Resource
	private DistributionService distributionService;

	public DistributionService getDistributionService() {
		return distributionService;
	}

	public void setDistributionService(DistributionService distributionService) {
		this.distributionService = distributionService;
	}

	/**
	 * 添加配送点的测试方法
	 */
	@Test
	public void addDistributionAction() {
		unit distribution = new unit();
		distribution.setUnit_num("A3");
		distribution.setUnit_admin("创建人id");
		distribution.setUnit_name("萍乡申通");
		distribution.setUnit_creator("创建人id");
		distribution.setUnit_address("江西省/萍乡市/安源区");
		distribution.setUnit_detailaddress("江西省/萍乡市/安源区/东大街");
		distribution.setUnit_phonenumber("110");
		distribution.setUnit_type("配送点");
		distribution.setUnit_superiorunit("萍乡中转站");
		distributionService.addDistributionAction(distribution);
	}
	/**
	 * 查询单位的的测试方法
	 */

	@Test
	public void getUnitManagerVO() {
		UnitManagerVO unitManagerVO = new UnitManagerVO();
		int page = 1;
		unitManagerVO.setPageIndex(page);

		unitManagerVO = distributionService.getUnitManagerVO(unitManagerVO);
		System.out.println("ceshi :"+unitManagerVO);
	}

}
