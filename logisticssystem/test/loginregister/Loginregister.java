package loginregister;

import javax.annotation.Resource;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.logistics.loginregister.service.LoginRegisterService;
/**
 * 注册登陆测试方法
 * @author LW
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext*.xml" })

public class Loginregister {
	@Resource
	private LoginRegisterService loginRegisterService;

	public LoginRegisterService getLoginRegisterService() {
		return loginRegisterService;
	}

	public void setLoginRegisterService(LoginRegisterService loginRegisterService) {
		this.loginRegisterService = loginRegisterService;
	}
	

}
