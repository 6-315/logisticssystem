package com.logistics.userinfo.service.impl;

import com.logistics.userinfo.dao.UserinfoDao;
import com.logistics.userinfo.service.UserInfoService;
/**
 * 用户信息业务实现层
 * @author 哈哈哈哈哈哈
 *
 */
public class UserInfoServiceImpl implements UserInfoService {
	/**
	 * dao层注入
	 */
	private UserinfoDao userinfoDao;

	public UserinfoDao getUserinfoDao() {
		return userinfoDao;
	}

	public void setUserinfoDao(UserinfoDao userinfoDao) {
		this.userinfoDao = userinfoDao;
	}
	

}
