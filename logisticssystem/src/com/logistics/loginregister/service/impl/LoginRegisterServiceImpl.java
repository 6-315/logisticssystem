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
	public String addUserifo(userinfo userinfo) {
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

	/**
	 * 总公司能能查看所有的人
	 * 
	 */
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
			System.out.println("成功！" + unit.getUnit_id());
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
	 * 中转站管理员或者配送点管理员能所有员工信息
	 */
	@Override
	public StaffManagerVO getStaffManagerVOByZ(StaffManagerVO staffManagerVO, staff_basicinfo staffBasicinfo) {
		List<StaffManagerDTO> listStaffManagerDTO = new ArrayList<>();
		List<staff_basicinfo> listStaff = new ArrayList<>();
		List<unit> listUnit = new ArrayList<>();
		String Cxfenye = "select count(*) from unit where unit_admin ='" + staffBasicinfo.getStaff_id() + "' ";
		String Cxbiao = "from unit where unit_admin ='" + staffBasicinfo.getStaff_id() + "' ";
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
			System.out.println("成功！" + unit.getUnit_id());
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
	/**
	 * 
	 * position_id ：77e07c34-735f-45d4-a870-3e5bebe5ddc1总公司 position_id
	 * ：77e07c34-735f-45d4-a870-3e5bebe5ddc2中转站管理员 position_id
	 * ：77e07c34-735f-45d4-a870-3e5bebe5ddc3配送点管理员 position_id
	 * ：77e07c34-735f-45d4-a870-3e5bebe5ddc4配送员 position_id
	 * ：77e07c34-735f-45d4-a870-3e5bebe5ddc5司机 position_id
	 * ：77e07c34-735f-45d4-a870-3e5bebe5ddc6车队队长
	 */
	@Override
	public List<unit> getLowerUnit(staff_basicinfo staffBasicinfo) {
		List<unit> listUnit = new ArrayList<>();
		List<unit> listUnitByp = new ArrayList<>();
		if ("77e07c34-735f-45d4-a870-3e5bebe5ddc1".equals(staffBasicinfo.getStaff_position())) {
			listUnit = new ArrayList<>();
			listUnit = (List<unit>) loginRegisterDao.listObject("from unit where unit_type ='中转站'");
			listUnitByp = (List<unit>) loginRegisterDao.listObject("from unit where unit_type ='配送点'");
			listUnit.addAll(listUnitByp);
			return listUnit;
		}
		if ("77e07c34-735f-45d4-a870-3e5bebe5ddc2".equals(staffBasicinfo.getStaff_position())) {
			listUnit = new ArrayList<>();
			listUnit = (List<unit>) loginRegisterDao
					.listObject("from unit where unit_admin = '" + staffBasicinfo.getStaff_id() + "'");
			return listUnit;
		}
		return null;
	}

	/**
	 * 根据自身职位获取以下职位
	 */
	@Override
	public List<position> getLowerPosition(staff_basicinfo staffBasicinfo) {
		List<position> listPosition = new ArrayList<>();// 总职位
		List<position> listPositionByZ = new ArrayList<>();// 中转站管理员职位
		List<position> listPositionByP = new ArrayList<>();// 配送点管理员职位
		List<position> listPositionByPsy = new ArrayList<>();// 配送员职位
		List<position> listPositionByS = new ArrayList<>();// 司机职位
		List<position> listPositionByC = new ArrayList<>();// 车队队长职位
		// 总公司职位获取以下的所有职位
		if ("77e07c34-735f-45d4-a870-3e5bebe5ddc1".equals(staffBasicinfo.getStaff_position())) {
			System.out.println("我是总公司！");
			listPositionByZ = (List<position>) loginRegisterDao
					.listObject("from position where position_id = '77e07c34-735f-45d4-a870-3e5bebe5ddc2'");
			listPosition.addAll(listPositionByZ);
			listPositionByP = (List<position>) loginRegisterDao
					.listObject("from position where position_id = '77e07c34-735f-45d4-a870-3e5bebe5ddc3'");
			listPosition.addAll(listPositionByP);
			listPositionByPsy = (List<position>) loginRegisterDao
					.listObject("from position where position_id = '77e07c34-735f-45d4-a870-3e5bebe5ddc4'");
			listPosition.addAll(listPositionByPsy);
			listPositionByS = (List<position>) loginRegisterDao
					.listObject("from position where position_id = '77e07c34-735f-45d4-a870-3e5bebe5ddc5'");
			listPosition.addAll(listPositionByS);
			listPositionByC = (List<position>) loginRegisterDao
					.listObject("from position where position_id = '77e07c34-735f-45d4-a870-3e5bebe5ddc6'");
			listPosition.addAll(listPositionByC);
		}
		// 中转站管理员获取以下的所有职位
		if ("77e07c34-735f-45d4-a870-3e5bebe5ddc2".equals(staffBasicinfo.getStaff_position())) {
			System.out.println("我是中转站管理员");
			listPositionByP = (List<position>) loginRegisterDao
					.listObject("from position where position_id = '77e07c34-735f-45d4-a870-3e5bebe5ddc3'");
			listPosition.addAll(listPositionByP);
			listPositionByPsy = (List<position>) loginRegisterDao
					.listObject("from position where position_id = '77e07c34-735f-45d4-a870-3e5bebe5ddc4'");
			listPosition.addAll(listPositionByPsy);
			listPositionByS = (List<position>) loginRegisterDao
					.listObject("from position where position_id = '77e07c34-735f-45d4-a870-3e5bebe5ddc5'");
			listPosition.addAll(listPositionByS);
			listPositionByC = (List<position>) loginRegisterDao
					.listObject("from position where position_id = '77e07c34-735f-45d4-a870-3e5bebe5ddc6'");
			listPosition.addAll(listPositionByC);
		}
		if ("77e07c34-735f-45d4-a870-3e5bebe5ddc3".equals(staffBasicinfo.getStaff_position())) {
			System.out.println("我是配送点管理员");
			listPositionByPsy = (List<position>) loginRegisterDao
					.listObject("from position where position_id = '77e07c34-735f-45d4-a870-3e5bebe5ddc4'");
			listPosition.addAll(listPositionByPsy);
		}
		return listPosition;
	}

	/**
	 * 批量删除
	 */
	@Override
	public String deleteListStaff(String staffListIdS) {
		String[] delete = staffListIdS.split(",");
		staff_basicinfo staffBasicinfo = null;
		for (String id : delete) {
			staffBasicinfo = new staff_basicinfo();
			staffBasicinfo = loginRegisterDao.getstaffById(id);
			if (staffBasicinfo != null) {
				loginRegisterDao.removeObject(staffBasicinfo);
			}
		}
		return "Success";
	}

	/**
	 * 修改员工单位
	 */
	@Override
	public String updateStaffUnit(staff_basicinfo staffBasicinfo) {
		staff_basicinfo staffBasicinfoNew = new staff_basicinfo();
		staffBasicinfoNew = loginRegisterDao.getstaffBasicinfo(staffBasicinfo.getStaff_id());
		staffBasicinfoNew.setStaff_unit(staffBasicinfo.getStaff_unit());
		staffBasicinfo.setStaff_modifytime(TimeUtil.getStringSecond());
		loginRegisterDao.saveOrUpdateObject(staffBasicinfoNew);
		System.out.println("成功！");
		return "Success";
	}

	/**
	 * 修改员工职位
	 */
	@Override
	public String updateStaffPosition(staff_basicinfo staffBasicinfo) {
		staff_basicinfo staffBasicinfoNew = new staff_basicinfo();
		staffBasicinfoNew = loginRegisterDao.getstaffBasicinfo(staffBasicinfo.getStaff_id());
		staffBasicinfoNew.setStaff_position(staffBasicinfo.getStaff_position());
		staffBasicinfo.setStaff_modifytime(TimeUtil.getStringSecond());
		loginRegisterDao.saveOrUpdateObject(staffBasicinfoNew);
		System.out.println("成功！");
		return "Success";
	}

	/**
	 * 修改员工状态
	 */
	@Override
	public String updateStaffState(staff_basicinfo staffBasicinfo) {
		staff_basicinfo staffBasicinfoNew = new staff_basicinfo();
		staffBasicinfoNew = loginRegisterDao.getstaffBasicinfo(staffBasicinfo.getStaff_id());
		staffBasicinfoNew.setStaff_state(staffBasicinfo.getStaff_state());
		staffBasicinfo.setStaff_modifytime(TimeUtil.getStringSecond());
		loginRegisterDao.saveOrUpdateObject(staffBasicinfoNew);
		System.out.println("成功！");
		return "Success";
	}

	/**
	 * 添加员工
	 */
	@Override
	public String addStaff(staff_basicinfo staffBasicinfo) {
		staffBasicinfo.setStaff_id(BuildUuid.getUuid());
		staffBasicinfo.setStaff_createtime(TimeUtil.getStringSecond());
		staffBasicinfo.setStaff_modifytime(TimeUtil.getStringSecond());
		return "Success";
	}

}
