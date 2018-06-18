package com.logistics.personnelmanagement.dao;

import java.util.List;

import com.logistics.domain.position;
import com.logistics.domain.staff_basicinfo;

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
	public staff_basicinfo getstaffById(String id);
	public staff_basicinfo getstaffBasicinfo(String staff_id);
	public position getPosition(staff_basicinfo staffBasicinfo);
	/**
	 * 
	 * @param trim
	 * @return
	 */

}
