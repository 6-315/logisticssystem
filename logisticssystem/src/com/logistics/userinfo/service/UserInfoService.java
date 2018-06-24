package com.logistics.userinfo.service;

import java.util.List;

import com.logistics.domain.address;
import com.logistics.domain.userinfo;
import com.logistics.expressmanagementW.VO.ExpressinfoAndExpressVO;
import com.logistics.loginregister.DTO.UserInfoSessionDTO;

/**
 * 用户信息service层接口
 * @author LW
 *
 */

public interface UserInfoService {

	UserInfoSessionDTO updateUserInfo(userinfo userInfo);

	List<address> getAllAddress(UserInfoSessionDTO userInfoSessionDTO);

	String judgePassword(String oldPassword, UserInfoSessionDTO userInfoSessionDTO);

	String updatePassword(userinfo userInfo);

	String addAddress(address addressNew, UserInfoSessionDTO userInfoSessionDTO);


	ExpressinfoAndExpressVO selectExpressInfo(String ID, ExpressinfoAndExpressVO expressinfoAndExpressVO);

}
