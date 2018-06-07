package com.logistics.loginregister.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.logistics.domain.staff_basicinfo;
import com.logistics.domain.userinfo;
import com.logistics.loginregister.dao.LoginRegisterDao;
import com.logistics.loginregister.service.LoginRegisterService;

import util.BuildUuid;
import util.TimeUtil;

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
		List<userinfo> listuser = new ArrayList<>();
		listuser = (List<com.logistics.domain.userinfo>) loginRegisterDao
				.listObject("from userinfo where userinfo_phonenumber = '" + userinfo.getUserinfo_phonenumber() + "'");
		if (listuser == null) {
			userinfo.setUserinfo_id(BuildUuid.getUuid());
			userinfo.setUserinfo_createtime(TimeUtil.getStringSecond());
			userinfo.setUserinfo_modify(TimeUtil.getStringSecond());
			loginRegisterDao.saveOrUpdateObject(userinfo);
			System.out.println("成功");
		} else {
			System.out.println("重复");
		}
	}
	/**
	 * 用户登陆方法判断
	 */
	@Override
	public userinfo loginByUser(userinfo userinfo) {
		return loginRegisterDao.loginByUser(userinfo);
	}

	@Override
	public staff_basicinfo loginByStaff(staff_basicinfo staff_basicinfo) {

		return loginRegisterDao.loginByStaff(staff_basicinfo);
	}

}
