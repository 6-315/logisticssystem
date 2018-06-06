package com.logistics.loginregister.service.impl;

import com.logistics.loginregister.dao.LoginRegisterDao;
import com.logistics.loginregister.service.LoginRegisterService;

/**
 * 注册登陆的业务实现层
 * 
 * @author LW
 *
 */
public class LoginRegisterServiceImpl implements LoginRegisterService {
	private LoginRegisterDao loginRegisterDao;

	public void setLoginRegisterDao(LoginRegisterDao loginRegisterDao) {
		this.loginRegisterDao = loginRegisterDao;
	}

}
