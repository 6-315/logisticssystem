package expressmanagement;

import javax.annotation.Resource;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
	

}
