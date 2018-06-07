package distribution;

import javax.annotation.Resource;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.logistics.distribution.service.DistributionService;
import com.logistics.domain.unit;
/**
 * 配送点管理测试方法
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
	public void addDistributionAction() {
		unit distribution=new unit();
		distribution.setUnit_num("A1");
		distribution.setUnit_admin("张三");
		distribution.setUnit_name("萍乡申通");
		distribution.setUnit_creator("萍乡中转站管理员");
		distributionService.addDistributionAction(distribution);
	}
	
}
