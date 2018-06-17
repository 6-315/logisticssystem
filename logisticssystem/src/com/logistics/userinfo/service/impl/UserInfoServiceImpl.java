package com.logistics.userinfo.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.logistics.domain.address;
import com.logistics.domain.reservation;
import com.logistics.domain.userinfo;
import com.logistics.loginregister.DTO.UserInfoSessionDTO;
import com.logistics.userinfo.dao.UserinfoDao;
import com.logistics.userinfo.service.UserInfoService;

import util.BuildUuid;
import util.TimeUtil;

/**
 * 用户信息业务实现层
 * 
 * @author LW
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
	@SuppressWarnings("unchecked")
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
	public String judgePassword(String oldPassword, UserInfoSessionDTO userInfoSessionDTO) {
		if (oldPassword != null && oldPassword.trim().length() > 0 && userInfoSessionDTO != null) {
			if (oldPassword.equals(userInfoSessionDTO.getUserInfoSession().getUserinfo_password())) {
				System.out.println("正确");
				return "Success";

			}
			System.out.println("错误");
			return "Error";
		}
		return null;
	}

	/**
	 * 更新密码
	 */
	@Override
	public String updatePassword(userinfo userInfo) {
		if (userInfo != null) {
			System.out.println("成功");
			userInfoDao.saveOrUpdateObject(userInfo);
			return "Success";
		}
		return "Error";
	}

	/**
	 * 添加一条地址
	 */
	@Override
	public String addAddress(address addressNew, UserInfoSessionDTO userInfoSessionDTO) {
		System.out.println("ppppppppppppp");
		if (addressNew != null && userInfoSessionDTO != null) {
			addressNew.setAddress_id(BuildUuid.getUuid());
			addressNew.setAddress_userinfo_id(userInfoSessionDTO.getUserInfoSession().getUserinfo_id());
			addressNew.setAddress_createtime(TimeUtil.getStringSecond());
			addressNew.setAddress_modifytime(TimeUtil.getStringSecond());
			System.out.println("iiiiiiiiiiii" + addressNew);
			userInfoDao.saveOrUpdateObject(addressNew);
			return "Success";
		}
		return "Error";
	}

}
