package com.logistics.loginregister.service;

import com.logistics.domain.staff_basicinfo;
import com.logistics.domain.userinfo;

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
	void addUserifo(userinfo userinfo);

	/**
	 * 用户登陆接口
	 * 
	 * @param userinfo
	 * @return
	 */
	userinfo loginByUser(userinfo userinfo);

	/**
	 * 员工登陆接口
	 * 
	 * @param staff_basicinfo
	 * @return
	 */
	staff_basicinfo loginByStaff(staff_basicinfo staff_basicinfo);

}
