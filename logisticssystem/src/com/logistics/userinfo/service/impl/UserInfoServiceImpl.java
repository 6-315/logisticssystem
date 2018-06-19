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

	/**
	 * 根据用户的ID查询它的历史订单，默认是显示未完成的
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ExpressinfoAndExpressVO selectExpressInfo(String userinfo_id,
			ExpressinfoAndExpressVO expressinfoAndExpressVO) {
		if (userinfo_id != null && userinfo_id.trim().length() > 0) {
			String number = "";
			String table = "";
			List<express> listExpress = new ArrayList<>();
			List<ExpressinfoAndExpressDTO> listExpressinfoAndExpressDTO = new ArrayList<>();
			if (expressinfoAndExpressVO.getState() != null && expressinfoAndExpressVO.getState().trim().length() > 0) {

				number = "select count(*) from express where express_belong = '" + userinfo_id
						+ "' and express_state = '" + expressinfoAndExpressVO.getState() + "'  ";
				table = "from express  where express_belong = '" + userinfo_id + "'and express_state = '"
						+ expressinfoAndExpressVO.getState() + "' ";

			} else {
				number = "select count(*) from express where express_belong = '" + userinfo_id
						+ "' and express_state != '已完成'  ";
				table = "from express  where express_belong = '" + userinfo_id + "'and express_state != '已完成' ";

			}
			if (expressinfoAndExpressVO.getSearch() != null
					&& expressinfoAndExpressVO.getSearch().trim().length() > 0) {
				String search = "%" + expressinfoAndExpressVO.getSearch() + "%";
				number = number + " and express_id like '" + search + "' ";
				table = table + " and express_id like '" + search + "'";
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
			/**
			 * 遍历它的订单得到订单的详细信息
			 */
			for (express express : listExpress) {
				System.out.println("ppppppp");
				ExpressinfoAndExpressDTO expressinfoAndExpressDTO = new ExpressinfoAndExpressDTO();
				expressinfo expressInfo = new expressinfo();
				expressInfo = userInfoDao.getExpressInfoById(express.getExpress_id());
				if (expressInfo != null) {
					expressinfoAndExpressDTO.setExpressInfo(expressInfo);
					expressinfoAndExpressDTO.setExpressNew(express);
					listExpressinfoAndExpressDTO.add(expressinfoAndExpressDTO);
				}
			}
			expressinfoAndExpressVO.setListExpressinfoAndExpressDTO(listExpressinfoAndExpressDTO);
			return expressinfoAndExpressVO;
		}
		return null;
	}
}
