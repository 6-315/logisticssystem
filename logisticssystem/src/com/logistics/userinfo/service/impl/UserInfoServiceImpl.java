package com.logistics.userinfo.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.logistics.domain.address;
import com.logistics.domain.userinfo;
import com.logistics.loginregister.DTO.UserInfoSessionDTO;
import com.logistics.userinfo.dao.UserinfoDao;
import com.logistics.userinfo.service.UserInfoService;

/**
 * 用户信息业务实现层
 * 
 * @author 哈哈哈哈哈哈
 *
 */
public class UserInfoServiceImpl implements UserInfoService {
	/**
	 * dao层注入
	 */
	private UserinfoDao userInfoDao;

	public void setUserInfoDao(UserinfoDao userInfoDao) {
		this.userInfoDao = userInfoDao;
	}

	/**
	 * 更新用户信息跟新session
	 */
	@Override
	public UserInfoSessionDTO updateUserInfo(userinfo userInfo) {
		if (userInfo != null) {
			UserInfoSessionDTO userInfoSessionDTO = new UserInfoSessionDTO();
			userInfoDao.saveOrUpdateObject(userInfo);
			userinfo userInfoNew = new userinfo();
			userInfoNew = userInfoDao.getUserInfo(userInfo.getUserinfo_id());
			if (userInfoNew != null) {
				userInfoSessionDTO.setUserInfoSession(userInfoNew);
			}
			return userInfoSessionDTO;

		}
		return null;
	}

	/**
	 * 根据个人ID获取它的所有地址
	 */
	@Override
	public List<address> getAllAddress(UserInfoSessionDTO userInfoSessionDTO) {
		if (userInfoSessionDTO != null) {
			List<address> listaddress = new ArrayList<>();
			listaddress = (List<address>) userInfoDao.listObject("from address where address_userinfo_id = '"
					+ userInfoSessionDTO.getUserInfoSession().getUserinfo_id() + "'");
			if (listaddress.size() > 0) {
				return listaddress;
			}
		}
		return null;
	}

	/**
	 * 判断旧密码是否一致
	 */
	@Override
	public String judgePassword(String oldPassword) {
		if (oldPassword != null && oldPassword.trim().length() > 0) {

		}
		return null;
	}

}
