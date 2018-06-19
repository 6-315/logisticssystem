package com.logistics.loginregister.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.logistics.domain.hat_area;
import com.logistics.domain.hat_city;
import com.logistics.domain.hat_province;
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
		if (listUser.size() == 0) {
			userInfo.setUserinfo_id(BuildUuid.getUuid());
			userInfo.setUserinfo_createtime(TimeUtil.getStringSecond());
			userInfo.setUserinfo_modifytime(TimeUtil.getStringSecond());
			loginRegisterDao.saveOrUpdateObject(userInfo);
			return "success";
		} else {
			return "error";
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

	@Override
	public position getPosition(String staff_position) {
		List<position> listPosition = new ArrayList<>();
		listPosition = (List<position>) loginRegisterDao
				.listObject("from position where position_id = '" + staff_position + "'");
		return listPosition.get(0);
	}

	@Override
	public List<hat_province> getAllProvince() {
		List<hat_province> listProvince = (List<hat_province>) loginRegisterDao.listObject("from hat_province");
		return listProvince;
	}

	@Override
	public List<hat_city> getAllCityByProvinceID(String cityFatherId) {
		if (cityFatherId != null && cityFatherId.trim().length() > 0) {
			List<hat_city> listCity = (List<hat_city>) loginRegisterDao
					.listObject("from hat_city where father = '" + cityFatherId + "'");
			return listCity;
		}
		return null;
	}

	@Override
	public List<hat_area> getAllCountryByCityID(String cityFatherId) {
		if (cityFatherId != null && cityFatherId.trim().length() > 0) {
			List<hat_area> listCity = (List<hat_area>) loginRegisterDao
					.listObject("from hat_area where father = '" + cityFatherId + "'");
			return listCity;
		}
		return null;
	}

}
