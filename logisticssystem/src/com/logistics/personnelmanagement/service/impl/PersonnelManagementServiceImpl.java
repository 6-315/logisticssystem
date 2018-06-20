package com.logistics.personnelmanagement.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.logistics.domain.position;
import com.logistics.domain.staff_basicinfo;
import com.logistics.domain.unit;
import com.logistics.personnelmanagement.DTO.StaffManagerDTO;
import com.logistics.personnelmanagement.VO.StaffManagerVO;
import com.logistics.personnelmanagement.dao.PersonnelManagementDao;
import com.logistics.personnelmanagement.service.PersonnelManagementService;

import util.BuildUuid;
import util.TimeUtil;

public class PersonnelManagementServiceImpl implements PersonnelManagementService {
	/**
	 * 注入DAO层
	 */
	private PersonnelManagementDao personnelManagementDao;

	public void setPersonnelManagementDao(PersonnelManagementDao personnelManagementDao) {
		this.personnelManagementDao = personnelManagementDao;
	}

	/**
	 * 根据权限查人
	 */
	@SuppressWarnings("unchecked")
	@Override
	public StaffManagerVO getStaffManagerVO(StaffManagerVO staffManagerVO, staff_basicinfo staffBasicinfo) {
		String number = "";
		String table = "";
		if (staffBasicinfo.getStaff_id() != null && staffBasicinfo.getStaff_id().trim().length() > 0
				&& staffBasicinfo.getStaff_position() != null
				&& staffBasicinfo.getStaff_position().trim().length() > 0) {
			position positionNew = new position();
			List<StaffManagerDTO> listStaffManagerDTO = new ArrayList<>();
			List<unit> listUnit = new ArrayList<>();
			List<staff_basicinfo> listStaff = new ArrayList<>();
			positionNew = personnelManagementDao.getPosition(staffBasicinfo);
			System.out.println("cao:" + positionNew.getPosition_name());
			System.out.println("asdasdasdasdsa");
			if (positionNew != null) {
				if ("总公司".equals(positionNew.getPosition_name())) {
					System.out.println("我是总公司");
					number = "select count(*) from staff_basicinfo where 1 = 1 ";
					table = "from staff_basicinfo where 1=1";
				}
				if ("中转站管理员".equals(positionNew.getPosition_name())
						|| "配送点管理员".equals(positionNew.getPosition_name())) {
					System.out.println("我是中转站或者配点");
					if (staffManagerVO.getBelongUnit() != null) {
						number = "select count(*) from staff_basicinfo where staff_unit = '"
								+ staffManagerVO.getBelongUnit() + "'";
						table = "from staff_basicinfo where staff_unit = '" + staffManagerVO.getBelongUnit() + "'";
					} else {
						number = "select count(*) from staff_basicinfo where staff_unit = '"
								+ staffBasicinfo.getStaff_unit() + "'";
						table = "from staff_basicinfo where  staff_unit = '" + staffBasicinfo.getStaff_unit() + "'";
					}
				}
			} // 在这里添加查找，然后变成高亮
			if (staffManagerVO.getSearch() != null && staffManagerVO.getSearch().trim().length() > 0) {
				String search = "%" + staffManagerVO.getSearch() + "%";
				number = number + " and staff_num like '" + search + "' ";
				number = number + " and staff_name like '" + search + "' ";
				table = table + " and staff_num like '" + search + "'";
				table = table + " and staff_name like '" + search + "'";
			}
			if (staffManagerVO.getPosition() != null && staffManagerVO.getPosition().trim().length() > 0) {
				String position = staffManagerVO.getPosition();
				number = number + " and staff_position  '" + position + "' ";
				table = table + " and staff_position '" + position + "'";
			}
			if (positionNew.getPosition_name() != "中转站管理员" && staffManagerVO.getBelongUnit() != null
					&& staffManagerVO.getBelongUnit().trim().length() > 0) {
				String belongUnit = staffManagerVO.getBelongUnit();
				number = number + " and staff_unit like '" + belongUnit + "' ";
				table = table + " and staff_unit like '" + belongUnit + "'";
			}
			System.out.println("________:" + table);
			// 这里如果不加desc表示正序，如果加**/上desc表示倒序
			table = table + " order by staff_modifytime desc";
			int userInfoCount = personnelManagementDao.getCount(number);
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
			listStaff = (List<staff_basicinfo>) personnelManagementDao.queryForPage(table,
					staffManagerVO.getPageIndex(), staffManagerVO.getPageSize());
			/**
			 * 遍历所有人查他所在的单位
			 */
			for (staff_basicinfo staff_basicinfo : listStaff) {
				/**
				 * 遍历循环显示高亮
				 * 
				 */
				if (staffManagerVO.getSearch() != null && staffManagerVO.getSearch().trim().length() > 0) {
					staff_basicinfo.setStaff_num(staff_basicinfo.getStaff_num().replaceAll(staffManagerVO.getSearch(),
							"<mark>" + staffManagerVO.getSearch() + "</mark>"));
				}
				if (staffManagerVO.getSearch() != null && staffManagerVO.getSearch().trim().length() > 0) {
					staff_basicinfo.setStaff_name(staff_basicinfo.getStaff_name().replaceAll(staffManagerVO.getSearch(),
							"<mark>" + staffManagerVO.getSearch() + "</mark>"));
				}
				StaffManagerDTO staffManagerDTO = new StaffManagerDTO();
				System.out.println("这是什么：" + staff_basicinfo.getStaff_unit());
				listUnit = new ArrayList<>();
				listUnit = (List<unit>) personnelManagementDao
						.listObject("from unit where unit_id = '" + staff_basicinfo.getStaff_unit() + "'");
				if (listUnit.size() != 0) {
					staffManagerDTO.setStaffBasicInfo(staff_basicinfo);
					staffManagerDTO.setUnit(listUnit.get(0));
					listStaffManagerDTO.add(staffManagerDTO);
				}
			}
			if (staffManagerVO.getBelongUnit() == null && "中转站管理员".equals(positionNew.getPosition_name())) {
				listUnit = (List<unit>) personnelManagementDao
						.listObject("from unit where unit_superiorunit = '" + staffBasicinfo.getStaff_unit() + "'");
				System.out.println("+++++++++++++++++++++++++" + listUnit.size());
				for (unit unit : listUnit) {
					/**
					 * 
					 * 循环遍历显示高亮
					 */
					System.out.println("hoashdoiashdoasho");
					System.out.println("ddd" + unit.getUnit_id());
					StaffManagerDTO staffManagerDTO = new StaffManagerDTO();
					listStaff = new ArrayList<>();
					listStaff = (List<staff_basicinfo>) personnelManagementDao
							.listObject("from staff_basicinfo where staff_unit = '" + unit.getUnit_id() + "'");
					System.out.println("ooooooooooooooooooooooooo:" + listStaff);
					if (staffManagerVO.getSearch() != null && staffManagerVO.getSearch().trim().length() > 0) {
						listStaff.get(0).setStaff_num(listStaff.get(0).getStaff_num().replaceAll(
								staffManagerVO.getSearch(), "<mark>" + staffManagerVO.getSearch() + "</mark>"));
					}
					if (staffManagerVO.getSearch() != null && staffManagerVO.getSearch().trim().length() > 0) {
						listStaff.get(0).setStaff_name(listStaff.get(0).getStaff_name().replaceAll(
								staffManagerVO.getSearch(), "<mark>" + staffManagerVO.getSearch() + "</mark>"));
					}
					System.out.println("如果多的话：" + listStaff.size());
					if (listStaff.size() != 0) {
						if (listStaff.size() == 1) {
							staffManagerDTO.setUnit(unit);
							staffManagerDTO.setStaffBasicInfo(listStaff.get(0));
						} else {
							for (int i = 0; i < listStaff.size(); i++) {
								System.out.println("进来了吗" + i);
								StaffManagerDTO staffManagerDTO2 = new StaffManagerDTO();
								staffManagerDTO2.setStaffBasicInfo(listStaff.get(i));
								staffManagerDTO2.setUnit(unit);
								listStaffManagerDTO.add(staffManagerDTO2);
							}
						}
					}
					listStaffManagerDTO.add(staffManagerDTO);
				}
			}
			staffManagerVO.setListStaDTO(listStaffManagerDTO);
		}
		return staffManagerVO;
	}

	/**
	 * 
	 * position_id ：77e07c34-735f-45d4-a870-3e5bebe5ddc1总公司
	 * position_id：77e07c34-735f-45d4-a870-3e5bebe5ddc2中转站管理员
	 * position_id：77e07c34-735f-45d4-a870-3e5bebe5ddc3配送点管理员 position_id
	 * ：77e07c34-735f-45d4-a870-3e5bebe5ddc4配送员 position_id
	 * ：77e07c34-735f-45d4-a870-3e5bebe5ddc5司机 position_id
	 * ：77e07c34-735f-45d4-a870-3e5bebe5ddc6车队队长 根据session传回来的查单位
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<unit> getLowerUnit(staff_basicinfo staffBasicinfo) {
		if (staffBasicinfo.getStaff_id().trim().length() > 0
				&& staffBasicinfo.getStaff_position().trim().length() > 0) {
			List<unit> listUnit = new ArrayList<>();
			List<unit> listUnitByp = new ArrayList<>();
			position positionNew = new position();
			positionNew = personnelManagementDao.getPosition(staffBasicinfo);
			if ("总公司".equals(positionNew.getPosition_name())) {
				listUnit = new ArrayList<>();
				listUnit = (List<unit>) personnelManagementDao
						.listObject("from unit where unit_type ='中转站' or unit_type='配送点'");
				return listUnit;
			}
			if ("中转站管理员".equals(positionNew.getPosition_name())) {
				listUnit = new ArrayList<>();
				listUnit = (List<unit>) personnelManagementDao
						.listObject("from unit where unit_creator = '" + staffBasicinfo.getStaff_id() + "'");
				return listUnit;
			}
		}
		return null;
	}

	/**
	 * 根据自身职位获取以下职位
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<position> getLowerPosition(staff_basicinfo staffBasicinfo) {
		if (staffBasicinfo.getStaff_id().trim().length() > 0
				&& staffBasicinfo.getStaff_position().trim().length() > 0) {
			position positionNew = new position();
			positionNew = personnelManagementDao.getPosition(staffBasicinfo);
			List<position> listPosition = new ArrayList<>();// 总职位
			if ("总公司".equals(positionNew.getPosition_name())) {
				listPosition = new ArrayList<>();
				listPosition = (List<position>) personnelManagementDao.listObject(
						"from position where position_name = '中转站管理员' or position_name='车队管理员' or position_name='配送点管理员' or position_name='驾驶员' or position_name='配送员'");
				return listPosition;
			}
			/**
			 * 中转站管理员获取以下的所有职位
			 */
			if ("中转站管理员".equals(positionNew.getPosition_name())) {
				System.out.println("进来了吗");
				listPosition = new ArrayList<>();
				listPosition = (List<position>) personnelManagementDao.listObject(
						"from position where position_name = '车队管理员' or position_name= '配送点管理员' or position_name='驾驶员' or position_name='配送员'");
				return listPosition;
			}

			if ("配送点管理员".equals(positionNew.getPosition_name())) {
				listPosition = new ArrayList<>();
				listPosition = (List<position>) personnelManagementDao
						.listObject("from position where position_name =  '配送员'");
				return listPosition;
			}
		}
		return null;
	}

	/**
	 * 批量删除
	 */
	@Override
	public String removeListStaff(String staffListIdS) {
		if (staffListIdS.trim().length() > 0) {
			String[] delete = staffListIdS.split(",");
			staff_basicinfo staffBasicinfo = null;
			for (String id : delete) {
				staffBasicinfo = new staff_basicinfo();
				staffBasicinfo = personnelManagementDao.getstaffById(id);
				if (staffBasicinfo != null) {
					personnelManagementDao.removeObject(staffBasicinfo);
				}
			}
		}
		return "success";
	}

	/**
	 * 修改员工单位
	 * 
	 * @return Success
	 */
	@Override
	public String updateStaffUnit(staff_basicinfo staffBasicinfo) {
		if (staffBasicinfo.getStaff_id().trim().length() > 0 && staffBasicinfo.getStaff_unit().trim().length() > 0) {
			staff_basicinfo staffBasicinfoNew = new staff_basicinfo();
			staffBasicinfoNew = personnelManagementDao.getstaffBasicinfo(staffBasicinfo.getStaff_id());
			staffBasicinfoNew.setStaff_unit(staffBasicinfo.getStaff_unit());
			staffBasicinfo.setStaff_modifytime(TimeUtil.getStringSecond());
			personnelManagementDao.saveOrUpdateObject(staffBasicinfoNew);
			System.out.println("成功！");
		}
		return "success";
	}

	/**
	 * 修改员工职位
	 * 
	 * @return success
	 */
	@Override
	public String updateStaffPosition(staff_basicinfo staffBasicinfo) {
		if (staffBasicinfo.getStaff_id().trim().length() > 0
				&& staffBasicinfo.getStaff_position().trim().length() > 0) {
			staff_basicinfo staffBasicinfoNew = new staff_basicinfo();
			unit Unit=new unit();
			
			staffBasicinfoNew = personnelManagementDao.getstaffBasicinfo(staffBasicinfo.getStaff_id());		
			staffBasicinfoNew.setStaff_unit(staffBasicinfo.getStaff_unit());			
			staffBasicinfoNew.setStaff_position(staffBasicinfo.getStaff_position());
		
			//更改上级领导，根据输入的职位，查到此职位的所属单位，再查这个单位的上级单位，再查到谁是这个所属单位的人的信息，得到名字
			Unit=personnelManagementDao.getUnitAdmin(staffBasicinfoNew);
			staffBasicinfoNew.setStaff_superiorleader(Unit.getUnit_admin());
			staffBasicinfo.setStaff_modifytime(TimeUtil.getStringSecond());
			personnelManagementDao.saveOrUpdateObject(staffBasicinfoNew);
			System.out.println("成功！");
		}
		return "success";
	}

	/**
	 * @return Success 修改员工状态
	 */
	@Override
	public String updateStaffState(staff_basicinfo staffBasicinfo) {
		if (staffBasicinfo.getStaff_id().trim().length() > 0 && staffBasicinfo.getStaff_state().trim().length() > 0) {
			staff_basicinfo staffBasicinfoNew = new staff_basicinfo();
			staffBasicinfoNew = personnelManagementDao.getstaffBasicinfo(staffBasicinfo.getStaff_id());
			staffBasicinfoNew.setStaff_state(staffBasicinfo.getStaff_state());
			staffBasicinfo.setStaff_modifytime(TimeUtil.getStringSecond());
			personnelManagementDao.saveOrUpdateObject(staffBasicinfoNew);
			System.out.println("成功！");
		}
		return "success";
	}

	/**
	 * @return Success 添加员工
	 */
	@Override
	public staff_basicinfo addStaff(staff_basicinfo staffBasicinfo) {
		List<staff_basicinfo> staffBasicInfoNew = new ArrayList<>();
		staff_basicinfo staff_basicinfo = new staff_basicinfo();
		String maxStaffNum = personnelManagementDao.getstaffBasicinfoMaxNum();
		System.out.println("youyouyoyuyoyu" + maxStaffNum);
		if (staffBasicinfo != null) {
			if (maxStaffNum == null) {
				staff_basicinfo.setStaff_num(String.format("%08d", 1));
			} else {
				int tmpMaxNum = Integer.parseInt(maxStaffNum);
				int tmpNewNum = tmpMaxNum + 1;
				staff_basicinfo.setStaff_num(String.format("%08d", tmpNewNum));
			}
			System.out.println("11" + staff_basicinfo.getStaff_num());

			System.out.println("22" + staff_basicinfo.getStaff_num());
			staffBasicinfo.setStaff_id(BuildUuid.getUuid());
			staffBasicinfo.setStaff_num(staff_basicinfo.getStaff_num());
			staffBasicinfo.setStaff_password(staffBasicinfo.getStaff_num());
			staffBasicinfo.setStaff_createtime(TimeUtil.getStringSecond());
			staffBasicinfo.setStaff_modifytime(TimeUtil.getStringSecond());
			personnelManagementDao.saveOrUpdateObject(staffBasicinfo);
			staffBasicInfoNew = (List<staff_basicinfo>) personnelManagementDao
					.listObject("from staff_basicinfo where staff_num = '" + staffBasicinfo.getStaff_num() + "'");

			if (staffBasicInfoNew.size() > 0) {
				return staffBasicInfoNew.get(0);
			}
		}
		return null;
	}
}
