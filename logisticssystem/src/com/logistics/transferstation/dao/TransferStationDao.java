package com.logistics.transferstation.dao;

import java.util.List;

import com.logistics.domain.driver;
import com.logistics.domain.position;
import com.logistics.domain.staff_basicinfo;
import com.logistics.domain.team;
import com.logistics.domain.unit;
import com.logistics.domain.vehicle;
/**
 * 中转站管理 的DAO接口
 * @author LW
 *
 */
public interface TransferStationDao {
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
	 * 根据Id查询中转站
	 * @param unitId
	 * @return
	 */
	public unit getTransferStationInfoById(String trim);
	/**
	 * 根据Id查询员工信息
	 * @param unitId
	 * @return
	 */
	public staff_basicinfo getBasicinfoById(String trim);
/**
 * 查询中转站最大编号
 * @param string 
 * @param trim
 * @return
 */


public String getTransferStationByNum(String unit_num);
/**
 * 根据id查询车队
 * @param trim
 * @return
 */
public team getTeamById(String trim);
/**
 * 根据id查询车辆
 */
public vehicle getVehicleById(String trim);
/**
 * 
 * 根据id查询司机
 * @return
 */
public driver getDriverById(String trim);
/**
 * 查询配送点最大编号
 * @param unit_num
 * @return
 */
String getDistributionByNum(String unit_num);

/**
 * 根据id查询职位
 * @param trim
 * @return
 */
position getPositionById(String trim);

/**
 * 根据车队队长id查询车队
 * @param trim
 * @return
 */
team getTeamByLeader(String trim);











	
}
