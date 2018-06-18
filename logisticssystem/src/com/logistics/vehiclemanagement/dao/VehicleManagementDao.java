package com.logistics.vehiclemanagement.dao;

import java.util.List;

import com.logistics.domain.staff_basicinfo;
import com.logistics.domain.team;
import com.logistics.domain.unit;
import com.logistics.domain.vehicle;
/**
 * 车辆管理DAO接口
 * @author LW
 *
 */
public interface VehicleManagementDao {
	/**
	 * 保存、更新对象
	 * 
	 * @author JXX
	 * @date 2018/04/12
	 * @param obj
	 * @modify JXX 2018/04/12
	 */
	public void saveOrUpdateObject(Object obj);

	/**
	 * 分页获取对象，这里是获取一页中的数据
	 * 
	 * @param hql
	 * @param offset
	 *            当前页
	 * @param length
	 *            获取每页记录数
	 * @return
	 */
	public List<?> queryForPage(String hql, int offset, int length);

	/**
	 * 获取对象总数量
	 * 
	 * @param hql
	 * @return
	 */
	public int getCount(String hql);

	/**
	 * 删除对象记录
	 * 
	 * @param obj
	 */
	public int removeObject(Object obj);

	/**
	 * 获取对象列表
	 */
	public List<?> listObject(String hql);

	/**
	 * 根据ID查询车辆
	 * @param vehicleinfo
	 * @return
	 */
	public vehicle getVehicleInfoById(String vehicleId);

	/**
	 * 根据购置人ID查询员工信息
	 * @param vehicle_acquisitionpeople
	 * @return
	 */
	public staff_basicinfo getStaffInfoById(String vehicle_acquisitionpeople);

	/**
	 * 根据单位ID查询单位信息
	 * @param vehicle_unit
	 * @return
	 */
	public unit getUnitInfoById(String vehicle_unit);

	/**
	 * 根据车队ID查询车队信息
	 * @param vehicle_team
	 * @return
	 */
	public team getTeamInfoById(String vehicle_team);

	/**
	 * 根据车牌号查询车辆信息
	 * @param vehicle_platenum
	 * @return
	 */
	public vehicle getVehicleInfoByPlateNumber(String vehicle_platenum);

	public String getMaxNumber(String hql);

	/**
	 * 
	 * @param trim
	 * @return
	 */
}
