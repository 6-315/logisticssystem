package com.logistics.userinfo.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.logistics.domain.address;
import com.logistics.domain.express;
import com.logistics.domain.expressinfo;
import com.logistics.domain.reservation;
import com.logistics.domain.userinfo;
import com.logistics.expressmanagementW.DTO.ExpressinfoAndExpressDTO;
import com.logistics.expressmanagementW.VO.ExpressinfoAndExpressVO;
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
			userInfoNew.setUserinfo_modifytime(TimeUtil.getStringSecond());
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
					+ userInfoSessionDTO.getUserInfoSession().getUserinfo_id() + "' and address_state = 1");
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
				return "success";

			}
			return "error";
		}
		return null;
	}

	/**
	 * 更新密码
	 */
	@Override
	public String updatePassword(userinfo userInfo) {
		if (userInfo != null) {
			userInfoDao.saveOrUpdateObject(userInfo);
			return "success";
		}
		return "error";
	}

	/**
	 * 添加一条地址
	 */
	@Override
	public String addAddress(address addressNew, UserInfoSessionDTO userInfoSessionDTO) {
		if (addressNew != null && userInfoSessionDTO != null) {
			if ("0".equals(addressNew.getAddress_state())) {
				/**
				 * 删除
				 */
				System.out.println("oooooooooooooo" + addressNew.getAddress_isdefault());
				addressNew.setAddress_modifytime(TimeUtil.getStringSecond());
				userInfoDao.saveOrUpdateObject(addressNew);
				return "success";
			} else {
				/**
				 * 添加或修改
				 */
				address addressByIs = new address();
				addressByIs = userInfoDao.getAddressById(addressNew.getAddress_id());
				if (addressByIs != null) {
					System.out.println("???????????");
					address addressByState = new address();
					addressByState = userInfoDao.getAddressByState();
					addressByState.setAddress_isdefault("否");
					addressByState.setAddress_modifytime(TimeUtil.getStringSecond());

					addressByIs.setAddress_isdefault("是");
					addressByIs.setAddress_modifytime(TimeUtil.getStringSecond());
					userInfoDao.saveOrUpdateObject(addressByState);
					userInfoDao.saveOrUpdateObject(addressByIs);
					return "success";
				}
				if ("是".equals(addressNew.getAddress_isdefault())) {
					address addressByState = new address();
					addressByState = userInfoDao.getAddressByState();
					if (addressByState != null) {
						addressByState.setAddress_modifytime(TimeUtil.getStringSecond());
						addressByState.setAddress_isdefault("否");
						/**
						 * 新地址
						 */
						addressNew.setAddress_id(BuildUuid.getUuid());
						addressNew.setAddress_userinfo_id(userInfoSessionDTO.getUserInfoSession().getUserinfo_id());
						addressNew.setAddress_createtime(TimeUtil.getStringSecond());
						addressNew.setAddress_modifytime(TimeUtil.getStringSecond());
						userInfoDao.saveOrUpdateObject(addressByState);
						userInfoDao.saveOrUpdateObject(addressNew);
						return "success";
					} else {
						addressNew.setAddress_id(BuildUuid.getUuid());
						addressNew.setAddress_userinfo_id(userInfoSessionDTO.getUserInfoSession().getUserinfo_id());
						addressNew.setAddress_createtime(TimeUtil.getStringSecond());
						addressNew.setAddress_modifytime(TimeUtil.getStringSecond());
						userInfoDao.saveOrUpdateObject(addressNew);
						return "success";
					}

				} else {
					addressNew.setAddress_id(BuildUuid.getUuid());
					addressNew.setAddress_userinfo_id(userInfoSessionDTO.getUserInfoSession().getUserinfo_id());
					addressNew.setAddress_createtime(TimeUtil.getStringSecond());
					addressNew.setAddress_modifytime(TimeUtil.getStringSecond());
					userInfoDao.saveOrUpdateObject(addressNew);
					return "success";
				}
			}

		}
		return "error";
	}

	/**
	 * 根据用户的ID查询它的历史订单，默认是显示未完成的
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ExpressinfoAndExpressVO selectExpressInfo(String ID, ExpressinfoAndExpressVO expressinfoAndExpressVO) {
		String number = "";
		String table = "";
		List<express> listExpress = new ArrayList<>();
		List<ExpressinfoAndExpressDTO> listExpressinfoAndExpressDTO = new ArrayList<>();
		if (ID == null) {
			return null;
		}
		if (expressinfoAndExpressVO.getState() != null && expressinfoAndExpressVO.getState().trim().length() > 0) {
			if ("在途中".equals(expressinfoAndExpressVO.getState())) {
				number = "select count(*) from express where express_belong = '" + ID	
						+ "' and (express_state = '待揽件' or express_state='已揽件' or express_state='待派送' or express_state='派送中')";
				table = "from express  where express_belong = '" + ID
						+ "'and (express_state = '待揽件' or express_state='已揽件' or express_state='待派送' or express_state='派送中') ";
			} else if ("已签收".equals(expressinfoAndExpressVO.getState())) {
				number = "select count(*) from express where express_belong = '" + ID
						+ "' and  (express_state ='已签收' or '已完成') ";
				table = "from express  where express_belong = '已签收' or '已完成' ";
			} else {
				number = "select count(*) from express where express_belong ='" + ID + "'and express_state='"
						+ expressinfoAndExpressVO.getState() + "' ";
				table = "from express  where express_belong ='" + ID + "'and express_state= '"
						+ expressinfoAndExpressVO.getState() + "'";
			}
		} else {
			number = "select count(*) from express where express_belong = '" + ID
					+ "' and (express_state != '已完成' or express_state != '已签收' )";
			table = "from express  where express_belong = '" + ID
					+ "'and (express_state != '已完成' or express_state != '已签收' )";
		}
		if (expressinfoAndExpressVO.getSearch() != null && expressinfoAndExpressVO.getSearch().trim().length() > 0) {
			String search = "%" + expressinfoAndExpressVO.getSearch() + "%";
			number = number + " and express_number like '" + search + "'  ";
			table = table + " and express_number like '" + search + "'";
		}
		table = table + " order by express_createtime desc";
		int userInfoCount = userInfoDao.getCount(number);
		// 设置总数量
		expressinfoAndExpressVO.setTotalRecords(userInfoCount);
		// 设置总页数
		expressinfoAndExpressVO.setTotalPages(((userInfoCount - 1) / expressinfoAndExpressVO.getPageSize()) + 1);
		// 判断是否拥有上一页
		if (expressinfoAndExpressVO.getPageIndex() <= 1) {
			expressinfoAndExpressVO.setHavePrePage(false);
		} else {
			expressinfoAndExpressVO.setHavePrePage(true);
		}
		// 判断是否拥有下一页
		if (expressinfoAndExpressVO.getPageIndex() >= expressinfoAndExpressVO.getTotalPages()) {
			expressinfoAndExpressVO.setHaveNextPage(false);
		} else {
			expressinfoAndExpressVO.setHaveNextPage(true);
		}
		listExpress = (List<express>) userInfoDao.queryForPage(table, expressinfoAndExpressVO.getPageIndex(),
				expressinfoAndExpressVO.getPageSize());
		for (express express : listExpress) {
			System.out.println("ppppppp");
			ExpressinfoAndExpressDTO expressinfoAndExpressDTO = new ExpressinfoAndExpressDTO();
			expressinfo expressInfo = new expressinfo();
			expressInfo = userInfoDao.getExpressInfoById(express.getExpress_expressinfoid());
			System.out.println("oooooo" + expressInfo);
			if (expressinfoAndExpressVO.getSearch() != null
					&& expressinfoAndExpressVO.getSearch().trim().length() > 0) {
				express.setExpress_number(express.getExpress_number().replaceAll(expressinfoAndExpressVO.getSearch(),
						"<span style='color: #ff5063;'>" + expressinfoAndExpressVO.getSearch() + "</span>"));
			}
			if (expressInfo != null) {
				System.out.println("jjijijijiji");
				expressinfoAndExpressDTO.setExpressInfo(expressInfo);
				expressinfoAndExpressDTO.setExpressNew(express);
				listExpressinfoAndExpressDTO.add(expressinfoAndExpressDTO);
			}
		}
		expressinfoAndExpressVO.setListExpressinfoAndExpressDTO(listExpressinfoAndExpressDTO);
		return expressinfoAndExpressVO;

	}
}
