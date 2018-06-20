package com.logistics.loginregister.service;

import java.util.List;

import com.google.gson.JsonElement;
import com.logistics.domain.hat_area;
import com.logistics.domain.hat_city;
import com.logistics.domain.hat_province;
import com.logistics.domain.position;
import com.logistics.domain.staff_basicinfo;
import com.logistics.domain.unit;
import com.logistics.domain.userinfo;
import com.logistics.personnelmanagement.VO.StaffManagerVO;

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
	String addUserifo(userinfo userinfo);

	/**
	 * 用户登陆接口
	 * 
	 * @param userinfo
	 * @return
	 */
	// userinfo loginByUser(userinfo userinfo);

	/**
	 * 员工登陆接口
	 * 
	 * @param staff_basicinfo
	 * @return
	 */
	staff_basicinfo loginByStaff(String username, String password);

	List<userinfo> getSize(String username);

	userinfo loginByUser(String username, String password);

	List<staff_basicinfo> getSizeBySat(String username);

	position getPosition(String staff_position);

	/**
	 * 获取所有省份
	 * 
	 * @author JXX
	 * @return
	 */
	List<hat_province> getAllProvince();

	/**
	 * 根据省份id获取市区
	 * 
	 * @param cityFatherId
	 * @return
	 */
	List<hat_city> getAllCityByProvinceID(String cityFatherId);

	/**
	 * 根据市id获取所有的区
	 * 
	 * @param cityFatherId
	 * @return
	 */
	List<hat_area> getAllCountryByCityID(String cityFatherId);

}
