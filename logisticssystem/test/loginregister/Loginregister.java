package loginregister;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.logistics.domain.staff_basicinfo;
import com.logistics.domain.userinfo;
import com.logistics.loginregister.service.LoginRegisterService;
import com.logistics.personnelmanagement.VO.StaffManagerVO;

/**
 * 注册登陆测试方法
 * 
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

	@Test
	public void reg() {
		userinfo userinfo = new userinfo();
		userinfo.setUserinfo_phonenumber("1101");
		System.out.println("测试：" + userinfo);
		loginRegisterService.addUserifo(userinfo);

	}

	@Test
	public void login() {
		String username = "110";
		String password = "11";
		List<userinfo> listuserInfo = new ArrayList<>();
		List<staff_basicinfo> liststaff_basicinfo = new ArrayList<>();
		listuserInfo = loginRegisterService.getSize(username);
		liststaff_basicinfo = loginRegisterService.getSizeBySat(username);
		if (listuserInfo.size() > 0) {
			userinfo userinfo_session = loginRegisterService.loginByUser(username, password);
			if (userinfo_session != null) {
				// request.getSession().setAttribute("userinfo_session", userinfo_session);
				System.out.println("成功！");
			}
		}
		if (liststaff_basicinfo.size() > 0) {
			staff_basicinfo staff_session = loginRegisterService.loginByStaff(username, password);
			if (staff_session != null) {
				// request.getSession().setAttribute("staff_session", staff_session);
			}
		}
	}
	/**
	 * 测试查看所有单位下的所有员工
	 */
@Test
	public void getStaffManagerVO() {
		StaffManagerVO staffManagerVO = new StaffManagerVO();
		// staffManagerVO.setPageIndex(pageIndex);
		// staffManagerVO.setSearch(search);
		staffManagerVO = loginRegisterService.getStaffManagerVO(staffManagerVO);
		System.out.println("成功！"+staffManagerVO);

	}

}
