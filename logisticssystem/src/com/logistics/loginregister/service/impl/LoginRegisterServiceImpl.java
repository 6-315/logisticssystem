package com.logistics.loginregister.service.impl;

import com.logistics.domain.userinfo;
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
/**
 * 用户注册方法
 */
	@Override
	public void addUserifo(userinfo userinfo) {
		loginRegisterDao.saveOrUpdateObject(userinfo);
		
	}
/**
 * 用户登陆方法判断
 */
	@Override
	public userinfo loginByUser(userinfo userinfo) {
		return loginRegisterDao.loginByUser(userinfo);
	}

}
