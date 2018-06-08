package com.logistics.loginregister.service;

import java.util.List;

import com.logistics.domain.position;
import com.logistics.domain.staff_basicinfo;
import com.logistics.domain.unit;
import com.logistics.domain.userinfo;
import com.logistics.personnelmanagement.VO.StaffManagerVO;

/**
 * 注册登陆的service层接口
 *      
 * @author LW
 *
 */
public interface LoginRegisterService {
	/**
	 * 注册接口
	 * 
	 * @param userinfo
	 */
	String addUserifo(userinfo userinfo);

	/**
	 * 用户登陆接口
	 * 
	 * @param userinfo
	 * @return
	 */
	//userinfo loginByUser(userinfo userinfo);

	/**
	 * 员工登陆接口
	 * 
	 * @param staff_basicinfo
	 * @return
	 */
	staff_basicinfo loginByStaff(String username, String password);

	List<userinfo> getSize(String username);

	userinfo loginByUser(String username, String password);

	List<staff_basicinfo> getSizeBySat(String username);




}
