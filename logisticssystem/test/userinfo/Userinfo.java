package userinfo;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.logistics.domain.*;
import com.logistics.expressmanagementW.VO.ExpressinfoAndExpressVO;
import com.logistics.loginregister.DTO.UserInfoSessionDTO;
import com.logistics.userinfo.service.UserInfoService;

/**
 * 用户信息管理测试方法
 * 
 * @author LW
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext*.xml" })
public class Userinfo {
	@Resource
	private UserInfoService userInfoService;

	public UserInfoService getUserInfoService() {
		return userInfoService;
	}

	public void setUserInfoService(UserInfoService userInfoService) {
		this.userInfoService = userInfoService;
	}

	/**
	 * 添加
	 */
	@Test
	public void addAddress() {
		address addressNew = new address();
		userinfo userInfo = new userinfo();
		UserInfoSessionDTO userInfoSessionDTO = new UserInfoSessionDTO();
		userInfo.setUserinfo_id("23");
		addressNew.setAddress_address("海南省儋州市2");
		addressNew.setAddress_isdefault("是的");
		userInfoSessionDTO.setUserInfoSession(userInfo);
		userInfoService.addAddress(addressNew, userInfoSessionDTO);

	}

	/**
	 * 查询所有地址
	 */
	@Test
	public void lookAll() {
		UserInfoSessionDTO userInfoSessionDTO = new UserInfoSessionDTO();
		userinfo userInfo = new userinfo();
		userInfo.setUserinfo_id("1");
		userInfoSessionDTO.setUserInfoSession(userInfo);
		List<address> listAddress = userInfoService.getAllAddress(userInfoSessionDTO);
		System.out.println("iiiiiiiiiiiii" + listAddress);
	}

	/**
	 * 判断密码和旧密码
	 */
	@Test
	public void pp() {
		String oldPassword = "8356";
		userinfo userInfo = new userinfo();
		userInfo.setUserinfo_password("83561");
		UserInfoSessionDTO userInfoSessionDTO = new UserInfoSessionDTO();
		userInfoSessionDTO.setUserInfoSession(userInfo);
		userInfoService.judgePassword(oldPassword, userInfoSessionDTO);
	}

	/**
	 * 保存新密码
	 */
	@Test
	public void update() {
		userinfo userInfo = new userinfo();
		userInfo.setUserinfo_password("83561");
		userInfo.setUserinfo_id("1");
		userInfoService.updatePassword(userInfo);
	}

	/**
	 * 查看历史订单
	 */
	@Test
	public void bbb() {
		ExpressinfoAndExpressVO expressinfoAndExpressVO = new ExpressinfoAndExpressVO();
		userinfo userInfo = new userinfo();
		userInfo.setUserinfo_id("1");
		expressinfoAndExpressVO.setState("");
		expressinfoAndExpressVO.setPageSize(2);
		expressinfoAndExpressVO = userInfoService.selectExpressInfo(userInfo.getUserinfo_id(), expressinfoAndExpressVO);
		System.out.println("kkkkkkkkkkkkk:" + expressinfoAndExpressVO);
	}

}
