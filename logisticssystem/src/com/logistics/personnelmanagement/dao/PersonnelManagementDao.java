package com.logistics.personnelmanagement.dao;

import java.util.List;

import com.logistics.domain.position;
import com.logistics.domain.staff_basicinfo;
import com.logistics.domain.team;
import com.logistics.domain.unit;

/**
 * 人事管理DAO接口
 * 
 * @author LW
 * 
 */
public interface PersonnelManagementDao {
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
	 * 查找员工表是否有此人（批量删除）
	 * 
	 * @param id
	 * @return
	 */
	public staff_basicinfo getstaffById(String id);

	/**
	 * 查找员工表是否有此人（修改员工单位）
	 * 
	 * @param staff_id
	 * @return
	 */
	public staff_basicinfo getstaffBasicinfo(String staff_id);

	/**
	 * 查找什么职位
	 * 
	 * @param staffBasicinfo
	 * @return
	 */
	public position getPosition(staff_basicinfo staffBasicinfo);

	/**
	 * 获得作答员工工号
	 * 
	 * @return
	 */

	public String getstaffBasicinfoMaxNum();

	/**
	 * 
	 * @param trim
	 * @return
	 */
	public unit getUnitAdmin(staff_basicinfo staffBasicinfo);

	public unit gerUnitByID(String iD);

	public position getPositionByID(String positionNew);

	public team getTeam(String staff_id);
}
