package com.logistics.loginregister.service;

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
	 * 登陆接口
	 * 
	 * @param userinfo
	 * @return
	 */
	userinfo loginByUser(userinfo userinfo);

}
