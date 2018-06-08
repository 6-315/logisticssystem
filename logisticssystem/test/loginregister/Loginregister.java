package loginregister;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.logistics.domain.position;
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

	/**
	 * 测试登陆
	 */
	@Test
	public void reg() {
		userinfo userinfo = new userinfo();
		userinfo.setUserinfo_phonenumber("1101");
		System.out.println("测试：" + userinfo);
		loginRegisterService.addUserifo(userinfo);

	}

	/**
	 * 测试登陆
	 */
	@Test
	public void login() {
		String username = "110";
		String password = "155";
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
		System.out.println("成功！" + staffManagerVO);

	}

	/**
	 * 查看自身以下的所有职位
	 */
	@Test
	public void getLowerPosition() {
		staff_basicinfo staffBasicinfo = new staff_basicinfo();
		staffBasicinfo.setStaff_position("77e07c34-735f-45d4-a870-3e5bebe5ddc1");
		List<position> listPosition = new ArrayList<>();
		listPosition = loginRegisterService.getLowerPosition(staffBasicinfo);
		System.out.println("你是什么鬼！" + listPosition);

	}

	/**
	 * 批量删除员工
	 */
	@Test
	public void deleteListStaff() {
		String staffListIdS = "1,2,3,4,5";
		loginRegisterService.deleteListStaff(staffListIdS);

	}
	/**
	 * 测试修改单位
	 */
	@Test
	public void updateStaffUnit() {
		staff_basicinfo staffBasicinfo = new staff_basicinfo();
		staffBasicinfo.setStaff_id("6");
		staffBasicinfo.setStaff_unit("9999999");
		loginRegisterService.updateStaffUnit(staffBasicinfo);
	}
	
/**
 * 测试修改职位
 */
	@Test
	public void updateStaffPosition() {
		staff_basicinfo staffBasicinfo = new staff_basicinfo();
		staffBasicinfo.setStaff_id("6");
		staffBasicinfo.setStaff_position("9999999");
		loginRegisterService.updateStaffPosition(staffBasicinfo);
		
	}
	/**
	 * 中转站管理员查看自己往下的所有人信息
	 */
	@Test
	public void getPeopleByZ() {
		StaffManagerVO staffManagerVO = new StaffManagerVO();
		staff_basicinfo staffBasicinfo = new staff_basicinfo();
		staffBasicinfo.setStaff_id("2");
		staffManagerVO = loginRegisterService.getStaffManagerVOByZ(staffManagerVO, staffBasicinfo);
		System.out.println("这是什么？："+staffManagerVO);
	}
	
}
