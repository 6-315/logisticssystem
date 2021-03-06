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
		if (staffBasicinfo == null) {
			return null;
		}
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
				if ("总公司管理员".equals(positionNew.getPosition_name())) {
					System.out.println("我是总公司");
					number = "select count(*) from staff_basicinfo where 1 = 1 ";
					table = "from staff_basicinfo where 1=1";
					if (staffManagerVO.getBelongUnit() != null && staffManagerVO.getBelongUnit().trim().length() > 0) {
						String belongUnit = staffManagerVO.getBelongUnit();
						number = number + " and staff_unit = '" + belongUnit + "' ";
						table = table + " and staff_unit = '" + belongUnit + "'";
					}
				}
				if ("中转站管理员".equals(positionNew.getPosition_name())
						|| "配送点管理员".equals(positionNew.getPosition_name())) {
					System.out.println("我是中转站或者配点");
					if (staffManagerVO.getBelongUnit() != null && staffManagerVO.getBelongUnit().trim().length() > 0) {
						System.out.println("??????????????");
						number = "select count(*) from staff_basicinfo where staff_unit = '"
								+ staffManagerVO.getBelongUnit() + "'";
						table = "from staff_basicinfo where staff_unit = '" + staffManagerVO.getBelongUnit() + "'";
					} else {

						System.out.println("ooooooooooooooooo");
						number = "select count(*) from staff_basicinfo where staff_unit = '"
								+ staffBasicinfo.getStaff_unit() + "'  ";
						table = "from staff_basicinfo where  ( staff_unit = '" + staffBasicinfo.getStaff_unit() + "' ";
						List<unit> listUnitByDistribution = (List<unit>) personnelManagementDao.listObject(
								"from unit where unit_superiorunit ='" + staffBasicinfo.getStaff_unit() + "' ");
						System.out.println("llll+" + staffBasicinfo.getStaff_unit());
						if (listUnitByDistribution.size() > 0) {
							number = number + " or ( ";
							table = table + " or ( ";
							for (int i = 0; i < listUnitByDistribution.size(); i++) {
								if (listUnitByDistribution.get(i) != null) {
									if (listUnitByDistribution.get(i).getUnit_id() != null
											&& listUnitByDistribution.get(i).getUnit_id().trim().length() > 0) {
										number = number + " staff_unit='" + listUnitByDistribution.get(i).getUnit_id()
												+ "' ";
										table = table + " staff_unit='" + listUnitByDistribution.get(i).getUnit_id()
												+ "' ";
									}
									if (i < listUnitByDistribution.size() - 1) {
										number = number + " or  ";
										table = table + " or  ";
									}
								}
							}
							number = number + ")";
							table = table + ")";
						}
						number = number + ")";
						table = table + ")";
						/*
						 * else { System.out.println("plplplplplplp"); number = number + " 1!=1 "; table
						 * = table + "  1!=1 "; }
						 */

					}
				}
			} // 在这里添加查找，然后变成高亮
			if (staffManagerVO.getSearch() != null && staffManagerVO.getSearch().trim().length() > 0) {
				String search = "%" + staffManagerVO.getSearch() + "%";
				number = number + " and (staff_num like '" + search + "' or staff_name like '" + search + "')";
				// number = number + " and staff_name like '" + search + "' ";
				table = table + " and (staff_num like '" + search + "' or staff_name like '" + search + "') ";
				// table = table + " and staff_name like '" + search + "'";
			}
			System.out.println("--------------------------------position:" + staffManagerVO.getPosition());
			if (staffManagerVO.getPosition() != null && staffManagerVO.getPosition().trim().length() > 0) {
				System.out.println("进来吗");
				String position = staffManagerVO.getPosition();
				System.out.println("????????????????????????" + position);
				number = number + " and staff_position ='" + position + "' ";
				table = table + " and staff_position  ='" + position + "'";
			}
			if (staffManagerVO.getState() != null && staffManagerVO.getState().trim().length() > 0) {
				number = number + " and staff_state = '" + staffManagerVO.getState() + "' ";
				table = table + " and staff_state = '" + staffManagerVO.getState() + "'";
			}

			System.out.println("________:" + table);
			// 这里如果不加desc表示正序，如果加**/上desc表示倒序
			table = table + " order by staff_modifytime desc";
			System.out.println("???:" + table);
			System.out.println("????:" + number);
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
				position position = new position();
				position = personnelManagementDao.getPosition(staff_basicinfo);
				/**
				 * 遍历循环显示高亮
				 * 
				 */
				if (staffManagerVO.getSearch() != null && staffManagerVO.getSearch().trim().length() > 0) {
					staff_basicinfo.setStaff_num(staff_basicinfo.getStaff_num().replaceAll(staffManagerVO.getSearch(),
							"<span style='color: #ff5063;'>" + staffManagerVO.getSearch() + "</span>"));
				}
				if (staffManagerVO.getSearch() != null && staffManagerVO.getSearch().trim().length() > 0) {
					staff_basicinfo.setStaff_name(staff_basicinfo.getStaff_name().replaceAll(staffManagerVO.getSearch(),
							"<span style='color: #ff5063;'>" + staffManagerVO.getSearch() + "</span>"));
				}
				StaffManagerDTO staffManagerDTO = new StaffManagerDTO();
				System.out.println("这是什么：" + staff_basicinfo.getStaff_unit());
				listUnit = new ArrayList<>();
				listUnit = (List<unit>) personnelManagementDao
						.listObject("from unit where unit_id = '" + staff_basicinfo.getStaff_unit() + "'");
				if (listUnit.size() != 0) {
					staffManagerDTO.setPosition(position);
					staffManagerDTO.setStaffBasicInfo(staff_basicinfo);
					staffManagerDTO.setUnit(listUnit.get(0));
					listStaffManagerDTO.add(staffManagerDTO);
				}
			}

			/*
			 * if (staffManagerVO.getBelongUnit() == null &&
			 * "中转站管理员".equals(positionNew.getPosition_name())) {
			 * 
			 * listUnit = (List<unit>) personnelManagementDao
			 * .listObject("from unit where unit_superiorunit = '" +
			 * staffBasicinfo.getStaff_unit() + "'");
			 * System.out.println("+++++++++++++++++++++++++" + listUnit.size()); for (unit
			 * unit : listUnit) {
			 *//**
				 * 
				 * 循环遍历显示高亮
				 *//*
					 * System.out.println("hoashdoiashdoasho"); System.out.println("ddd" +
					 * unit.getUnit_id()); StaffManagerDTO staffManagerDTO = new StaffManagerDTO();
					 * listStaff = new ArrayList<>(); listStaff = (List<staff_basicinfo>)
					 * personnelManagementDao
					 * .listObject("from staff_basicinfo where staff_unit = '" + unit.getUnit_id() +
					 * "'"); if (staffManagerVO.getSearch() != null &&
					 * staffManagerVO.getSearch().trim().length() > 0) {
					 * listStaff.get(0).setStaff_num(listStaff.get(0).getStaff_num().replaceAll(
					 * staffManagerVO.getSearch(), "<span style='color: #ff5063;'>" +
					 * staffManagerVO.getSearch() + "</span>")); } if (staffManagerVO.getSearch() !=
					 * null && staffManagerVO.getSearch().trim().length() > 0) {
					 * listStaff.get(0).setStaff_name(listStaff.get(0).getStaff_name().replaceAll(
					 * staffManagerVO.getSearch(), "<span style='color: #ff5063;'>" +
					 * staffManagerVO.getSearch() + "</span>")); } System.out.println("如果多的话：" +
					 * listStaff.size()); if (listStaff.size() != 0) { if (listStaff.size() == 1) {
					 * position position = new position(); position =
					 * personnelManagementDao.getPosition(listStaff.get(0));
					 * staffManagerDTO.setPosition(position); staffManagerDTO.setUnit(unit);
					 * staffManagerDTO.setStaffBasicInfo(listStaff.get(0)); } else { for (int i = 0;
					 * i < listStaff.size(); i++) { position position = new position(); position =
					 * personnelManagementDao.getPosition(listStaff.get(i));
					 * staffManagerDTO.setPosition(position); StaffManagerDTO staffManagerDTO2 = new
					 * StaffManagerDTO(); staffManagerDTO2.setStaffBasicInfo(listStaff.get(i));
					 * staffManagerDTO2.setUnit(unit); listStaffManagerDTO.add(staffManagerDTO2); }
					 * } } listStaffManagerDTO.add(staffManagerDTO); } }
					 */
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
		if (staffBasicinfo == null) {
			return null;
		}
		if (staffBasicinfo.getStaff_id() != null && staffBasicinfo.getStaff_id().trim().length() > 0
				&& staffBasicinfo.getStaff_position() != null
				&& staffBasicinfo.getStaff_position().trim().length() > 0) {
			List<unit> listUnit = new ArrayList<>();
			position positionNew = new position();
			positionNew = personnelManagementDao.getPosition(staffBasicinfo);
			if ("总公司管理员".equals(positionNew.getPosition_name())) {
				System.out.println("????????");
				listUnit = new ArrayList<>();
				listUnit = (List<unit>) personnelManagementDao
						.listObject("from unit where unit_type ='中转站' or unit_type='配送点'");
				return listUnit;
			}
			if ("中转站管理员".equals(positionNew.getPosition_name())) {
				listUnit = new ArrayList<>();
				listUnit = (List<unit>) personnelManagementDao
						.listObject("from unit where unit_superiorunit = '" + staffBasicinfo.getStaff_unit() + "'");
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
		if (staffBasicinfo == null) {
			return null;
		}
		if (staffBasicinfo.getStaff_id() != null && staffBasicinfo.getStaff_id().trim().length() > 0
				&& staffBasicinfo.getStaff_position() != null
				&& staffBasicinfo.getStaff_position().trim().length() > 0) {
			position positionNew = new position();
			positionNew = personnelManagementDao.getPosition(staffBasicinfo);
			List<position> listPosition = new ArrayList<>();// 总职位
			if ("总公司管理员".equals(positionNew.getPosition_name())) {
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
			return "success";
		}
		return "error";
	}

	/**
	 * 修改员工信息
	 * 
	 * @return success
	 */
	@Override
	public String updateStaffInfo(staff_basicinfo staffBasicinfo) {
		if (staffBasicinfo == null) {
			return null;
		}
		if (staffBasicinfo.getStaff_id() != null && staffBasicinfo != null
				&& staffBasicinfo.getStaff_id().trim().length() > 0) {

			if (staffBasicinfo.getStaff_id().trim().length() > 0
					&& staffBasicinfo.getStaff_position().trim().length() > 0) {
				staff_basicinfo staffBasicinfoNew = new staff_basicinfo();
				unit Unit = new unit();

				staffBasicinfoNew = personnelManagementDao.getstaffBasicinfo(staffBasicinfo.getStaff_id());
				staffBasicinfoNew.setStaff_unit(staffBasicinfo.getStaff_unit());
				staffBasicinfoNew.setStaff_position(staffBasicinfo.getStaff_position());

				// 更改上级领导，根据输入的职位，查到此职位的所属单位，再查这个单位的上级单位，再查到谁是这个所属单位的人的信息，得到名字
				Unit = personnelManagementDao.getUnitAdmin(staffBasicinfoNew);
				staffBasicinfoNew.setStaff_superiorleader(Unit.getUnit_admin());
				staffBasicinfo.setStaff_modifytime(TimeUtil.getStringSecond());
				personnelManagementDao.saveOrUpdateObject(staffBasicinfoNew);
				System.out.println("成功！");
			}
			return "success";
		}
		return "error";
	}

	/**
	 * @return Success 修改员工状态
	 */
	@Override
	public String updateStaffState(staff_basicinfo staffBasicinfo) {
		if (staffBasicinfo.getStaff_id().trim().length() > 0 && staffBasicinfo.getStaff_state().trim().length() > 0) {
			staff_basicinfo staffBasicinfoNew = new staff_basicinfo();
			staffBasicinfoNew = personnelManagementDao.getstaffBasicinfo(staffBasicinfo.getStaff_id());
			if (staffBasicinfo.getStaff_unit() != null && staffBasicinfo.getStaff_unit().trim().length() > 0) {
				System.out.println("oooooooo" + staffBasicinfo.getStaff_unit());
				staffBasicinfoNew.setStaff_unit(staffBasicinfo.getStaff_unit());
				System.out.println("单位成功！");
			} else if (staffBasicinfo.getStaff_position() != null
					&& staffBasicinfo.getStaff_position().trim().length() > 0) {
				staffBasicinfoNew.setStaff_position(staffBasicinfo.getStaff_position());
				System.out.println("职位成功！");
			} else if (staffBasicinfo.getStaff_state() != null && staffBasicinfo.getStaff_state().trim().length() > 0) {
				staffBasicinfoNew.setStaff_state(staffBasicinfo.getStaff_state());
				System.out.println("状态成功！");
			}
			staffBasicinfoNew.setStaff_modifytime(TimeUtil.getStringSecond());
			personnelManagementDao.saveOrUpdateObject(staffBasicinfoNew);

		}
		return "success";
	}

	/**
	 * @return Success 添加员工
	 */
	@SuppressWarnings("unchecked")
	@Override
	public staff_basicinfo addStaff(staff_basicinfo staffBasicinfo, staff_basicinfo staffBasicSession) {
		if (staffBasicSession == null) {
			return null;
		}
		if (staffBasicinfo.getStaff_id() != null && staffBasicinfo.getStaff_id().trim().length() > 0) {
			staffBasicinfo.setStaff_modifytime(TimeUtil.getStringSecond());
			personnelManagementDao.saveOrUpdateObject(staffBasicinfo);
			return staffBasicinfo;
		}
		List<staff_basicinfo> staffBasicInfoNew = new ArrayList<>();
		if (staffBasicinfo.getStaff_name() != null && staffBasicinfo.getStaff_name().trim().length() > 0) {
			staff_basicinfo staff_basicinfo = new staff_basicinfo();
			String maxStaffNum = personnelManagementDao.getstaffBasicinfoMaxNum();
			if (staffBasicinfo != null) {
				if (maxStaffNum == null) {
					staff_basicinfo.setStaff_num(String.format("%08d", 1));
				} else {
					int tmpMaxNum = Integer.parseInt(maxStaffNum);
					int tmpNewNum = tmpMaxNum + 1;
					staff_basicinfo.setStaff_num(String.format("%08d", tmpNewNum));
				}
				staffBasicinfo.setStaff_id(BuildUuid.getUuid());
				staffBasicinfo.setStaff_num(staff_basicinfo.getStaff_num());
				staffBasicinfo.setStaff_state("在职");
				staffBasicinfo.setStaff_recruit(staffBasicSession.getStaff_id());
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
		}
		return null;
	}

	/**
	 * 根据ID 和查询该职位下的所有职位
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<position> getPositionById(staff_basicinfo staffBasicSession) {
		if (staffBasicSession == null) {
		}
		staff_basicinfo staffBasicInfo = new staff_basicinfo();
		staffBasicInfo = personnelManagementDao.getstaffById(staffBasicSession.getStaff_id());
		if (staffBasicInfo != null) {
			List<position> listPosition = new ArrayList<>();
			position positionNew = new position();
			positionNew = personnelManagementDao.getPosition(staffBasicInfo);
			if ("总公司管理员".equals(positionNew.getPosition_name())) {
				listPosition = (List<position>) personnelManagementDao.listObject(
						"from position where position_name = '中转站管理员' or position_name='车队管理员' or position_name='配送点管理员' or position_name='驾驶员' or position_name='配送员'");
				return listPosition;
			}
			if ("中转站管理员".equals(positionNew.getPosition_name())) {
				System.out.println("进来了吗");
				listPosition = (List<position>) personnelManagementDao.listObject(
						"from position where position_name = '车队队长' or position_name= '配送点管理员' or position_name='驾驶员' or position_name='配送员'");
				return listPosition;
			}

			if ("配送点管理员".equals(positionNew.getPosition_name())) {
				listPosition = (List<position>) personnelManagementDao
						.listObject("from position where position_name =  '配送员'");
				return listPosition;
			}

			return null;
		}
		return null;
	}

	/**
	 * x修改员工职位
	 */
	@Override
	public String updatePositionById(String iD, String positionNew) {
		System.out.println("kkkkkkkkkkkkkkk:" + iD);
		System.out.println("kkkkkkkkkkkkkkkuuuuuuuu:" + positionNew);
		if (iD == null || positionNew == null) {

			return "error";
		}
		System.out.println("uuuuuuuuuuuuuuuuuu");
		staff_basicinfo staffBasicInfo = new staff_basicinfo();
		staffBasicInfo = personnelManagementDao.getstaffById(iD);
		if (staffBasicInfo != null) {
			System.out.println("??????????????????????????????");
			staffBasicInfo.setStaff_position(positionNew);
			staffBasicInfo.setStaff_modifytime(TimeUtil.getStringSecond());
			personnelManagementDao.saveOrUpdateObject(staffBasicInfo);
			return "success";
		}
		return "error";
	}

	/**
	 * 修改员工单位
	 */
	@Override
	public String updateUnitById(String iD, String unitNew) {
		if (iD == null || unitNew == null) {
			return "erroe";
		}
		staff_basicinfo staffBasicInfo = new staff_basicinfo();
		staffBasicInfo = personnelManagementDao.getstaffById(iD);
		if (staffBasicInfo != null) {
			staffBasicInfo.setStaff_unit(unitNew);
			staffBasicInfo.setStaff_modifytime(TimeUtil.getStringSecond());
			personnelManagementDao.saveOrUpdateObject(staffBasicInfo);
			return "success";
		}
		return "erroe";
	}

	/**
	 * 根据ID查询这个人的ＤＴＯ
	 */
	@Override
	public StaffManagerDTO getStaffManagerDTO(String iD) {
		if (iD == null) {
			return null;

		}
		unit unitNew = new unit();
		staff_basicinfo staffNew = new staff_basicinfo();
		position positionNew = new position();
		unitNew = personnelManagementDao.gerUnitByID(iD);
		staffNew = personnelManagementDao.getstaffById(iD);
		positionNew = personnelManagementDao.getPosition(staffNew);
		if (unitNew != null && staffNew != null && positionNew != null) {
			StaffManagerDTO staffManagerDTO = new StaffManagerDTO();
			staffManagerDTO.setPosition(positionNew);
			staffManagerDTO.setStaffBasicInfo(staffNew);
			staffManagerDTO.setUnit(unitNew);
			return staffManagerDTO;
		}

		return null;
	}

}
