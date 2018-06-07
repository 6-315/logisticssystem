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
	public void addUserifo(userinfo userinfo) {
		List<userinfo> listuser = new ArrayList<>();
		listuser = (List<com.logistics.domain.userinfo>) loginRegisterDao
				.listObject("from userinfo where userinfo_phonenumber = '" + userinfo.getUserinfo_phonenumber() + "'");
		System.out.println("在哪：" + userinfo.getUserinfo_phonenumber());
		System.out.println("什么" + listuser);
		System.out.println("size:" + listuser.size());
		if (listuser.size() == 0) {
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
		List<userinfo> listuser = new ArrayList<>();
		listuser = (List<userinfo>) loginRegisterDao
				.listObject("from userinfo where userinfo_phonenumber = '" + username + "'");
		return listuser;
	}

	/**
	 * 判断username是否在员工表
	 */
	@Override
	public List<staff_basicinfo> getSizeBySat(String username) {
		List<staff_basicinfo> liststa = new ArrayList<>();
		liststa = (List<staff_basicinfo>) loginRegisterDao
				.listObject("from staff_basicinfo where staff_num = '" + username + "'");
		return liststa;
	}

	@Override
	public StaffManagerVO getStaffManagerVO(StaffManagerVO staffManagerVO) {
		List<StaffManagerDTO> listStaffManagerDTO = new ArrayList<>();
		List<staff_basicinfo> listStaff = new ArrayList<>();
		List<unit> listUnit = new ArrayList<>();
		String Cxfenye = "select count(*) from unit";
		String Cxbiao = "from unit";
		// 在这里添加查找，然后变成高亮
		if (staffManagerVO.getSearch() != null && staffManagerVO.getSearch().trim().length() > 0) {
			String search = "%" + staffManagerVO.getSearch() + "%";
			Cxfenye = Cxfenye + " and unit_name like '" + search + "' ";
			Cxbiao = Cxbiao + " and unit_name like '" + search + "'";
		}
		// 这里如果不加desc表示正序，如果加**/上desc表示倒序
		Cxbiao = Cxbiao + " order by unit_modifytime desc";
		int userInfoCount = loginRegisterDao.getCount(Cxfenye);
		// 设置总数量
		staffManagerVO.setTotalRecords(userInfoCount);
		// 设置总页数
		staffManagerVO.setTotalPages(((userInfoCount - 1) / staffManagerVO.getPageSize()) + 1);
		// 判断是否拥有上一页
		if (staffManagerVO.getPageIndex() <= 1) {
			staffManagerVO.setHavePrePage(false);
		} else {
			staffManagerVO.setHavePrePage(true);
		}
		// 判断是否拥有下一页
		if (staffManagerVO.getPageIndex() >= staffManagerVO.getTotalPages()) {
			staffManagerVO.setHaveNextPage(false);
		} else {
			staffManagerVO.setHaveNextPage(true);
		}
		listUnit = (List<unit>) loginRegisterDao.queryForPage(Cxbiao, staffManagerVO.getPageIndex(),
				staffManagerVO.getPageSize());
		System.out.println(listUnit.size());
		for (unit unit : listUnit) {
			System.out.println("成功！"+unit.getUnit_id());
			StaffManagerDTO taffManagerDTO = new StaffManagerDTO();
			listStaff = new ArrayList<>();
			listStaff = (List<staff_basicinfo>) loginRegisterDao
					.listObject("from staff_basicinfo where staff_unit = '" + unit.getUnit_id() + "'");
			taffManagerDTO.setUnit(unit);
			taffManagerDTO.setListStaff(listStaff);
			listStaffManagerDTO.add(taffManagerDTO);
			if (staffManagerVO.getSearch() != null && staffManagerVO.getSearch().trim().length() > 0) {
				unit.setUnit_name(unit.getUnit_name().replaceAll(staffManagerVO.getSearch(),
						"<mark>" + staffManagerVO.getSearch() + "</mark>"));
			}

		}
		staffManagerVO.setListStaDTO(listStaffManagerDTO);
		return staffManagerVO;
	}
/**
 * 更加session传过来的staff_id查询盖人所在的
 */

	@Override
	public List<unit> getLowerUnit(staff_basicinfo staffBasicinfo) {
		List<unit> listUnit = new ArrayList<>();
		position position = new position();
		position = loginRegisterDao.getPosition(staffBasicinfo);
		return null;
	}

}
