package com.logistics.loginregister.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.logistics.domain.position;
import com.logistics.domain.staff_basicinfo;
import com.logistics.domain.unit;
import com.logistics.domain.userinfo;
import com.logistics.loginregister.dao.LoginRegisterDao;
import com.logistics.loginregister.service.LoginRegisterService;
import com.logistics.personnelmanagement.DTO.StaffManagerDTO;
import com.logistics.personnelmanagement.VO.StaffManagerVO;

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
	public String addUserifo(userinfo userInfo) {
		List<userinfo> listUser = new ArrayList<>();
		listUser = (List<com.logistics.domain.userinfo>) loginRegisterDao
				.listObject("from userinfo where userinfo_phonenumber = '" + userInfo.getUserinfo_phonenumber() + "'");
		System.out.println("在哪：" + userInfo.getUserinfo_phonenumber());
		System.out.println("什么" + listUser);
		System.out.println("size:" + listUser.size());
		if (listUser.size() == 0) {
			userInfo.setUserinfo_id(BuildUuid.getUuid());
			userInfo.setUserinfo_createtime(TimeUtil.getStringSecond());
			userInfo.setUserinfo_modify(TimeUtil.getStringSecond());
			loginRegisterDao.saveOrUpdateObject(userInfo);
			System.out.println("成功");
			return "成功";
		} else {
			System.out.println("重复");
			return "重复";
		}
	}

	/**
	 * 用户登陆方法判断
	 */
	@Override
	public userinfo loginByUser(String username, String password) {
		return loginRegisterDao.loginByUser(username, password);
	}
	@Override
	public staff_basicinfo loginByStaff(String username, String password) {

		return loginRegisterDao.loginByStaff(username, password);
	}
	/**
	 * 判断username是否在用户表
	 * 
	 */
	@Override
	public List<userinfo> getSize(String username) {
		List<userinfo> listUser = new ArrayList<>();
		listUser = (List<userinfo>) loginRegisterDao
				.listObject("from userinfo where userinfo_phonenumber = '" + username + "'");
		return listUser;
	}
	/**
	 * 判断username是否在员工表
	 */
	@Override
	public List<staff_basicinfo> getSizeBySat(String username) {
		List<staff_basicinfo> listSta = new ArrayList<>();
		listSta = (List<staff_basicinfo>) loginRegisterDao
				.listObject("from staff_basicinfo where staff_num = '" + username + "'");
		return listSta;
	}


	

}
